package com.example.yogaclub.ui.gallery;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import com.example.yogaclub.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Friends extends ListActivity {

    private EditText edit1;
    Integer i;
    String[] from;
    int[] to;
    static ListView listView;
    Cursor cursor;
    static int currentPos;
    static View dialogView;
    public static ArrayList<String> friends = new ArrayList<>();
    public static ArrayList<String> texts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery);
        setInitialTexts();
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        friends = new ArrayList<String>(Arrays.asList(TextUtils.split(save.getString("Friends", ""), "‚‗‚")));
        from = new String[]{"Name", "Phone"};
        to = new int[]{R.id.name, R.id.phone};
        Button btnadd = findViewById(R.id.buttonAdd);
        Button buttonBack = findViewById(R.id.buttonBack);
        ImageButton butSms = findViewById(R.id.smsButton);
        dialogView = getLayoutInflater().inflate(R.layout.dialog, null);
        //Button butDel = findViewById(R.id.butDel);
        SQLiteDatabase db = openOrCreateDatabase("DBName", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyTable ( _id Integer PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Phone VARCHAR);");
        cursor = db.rawQuery("SELECT * FROM Mytable", null);
        i = cursor.getCount() + 1;
        if (cursor.getCount() > 0) {
            MyCursorAdapter scAdapter = new
                    MyCursorAdapter(Friends.this, R.layout.list_item5, cursor, from, to);
            listView = getListView();
            listView.setAdapter(scAdapter);
        }
        //registerForContextMenu(getListView());
        Context context = Friends.this;
        db.close();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new
                        AlertDialog.Builder(Friends.this);
                dialog.setMessage("Enter information about your friend");
                dialog.setTitle("Adding new friend");
                View dialogView = getLayoutInflater().inflate(R.layout.dialog, null);
                Friends.dialogView = dialogView;
                dialog.setView(dialogView);
                EditText editName = dialogView.findViewById(R.id.editTextName);
                EditText editPhone = dialogView.findViewById(R.id.editTextPhone);
                dialog.setNeutralButton("OK", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int i) {
                                SQLiteDatabase db =
                                        openOrCreateDatabase("DBName", MODE_PRIVATE, null);
                                Cursor cursor2 = db.rawQuery("SELECT * FROM Mytable", null);
                                i = cursor2.getCount() + 1;
                                for (int k = 1; k <= i; k++) {
                                    Cursor cursor3 = db.rawQuery("SELECT * FROM Mytable WHERE _id=" + k + "", null);
                                    if (cursor3.getCount() == 0) {
                                        i = k;
                                        break;
                                    }
                                }
                                String name = editName.getText().toString();
                                String phone = editPhone.getText().toString();
                                db.execSQL("INSERT INTO MyTable VALUES ('" + i + "','" + name + "','" + phone + "');");
                                friends.add(name);
                                Cursor cursor = db.rawQuery("SELECT * FROM Mytable", null);
                                MyCursorAdapter scAdapter = new
                                        MyCursorAdapter(Friends.this, R.layout.list_item5, cursor, from, to);
                                listView = getListView();
                                listView.setAdapter(scAdapter);
                                db.close();
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        butSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (friends.size() != 0) {
                    Intent intent = new Intent(Friends.this, Chat.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void setInitialTexts() {
        texts.add("Let's meditate together today");
        texts.add("Glad to hear from you");
        texts.add("What types of yoga do you like?");
        texts.add("I like Vinyasa yoga!");
        texts.add("You are amazing!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        SharedPreferences.Editor editor = save.edit(); //создаём
        ArrayList<String> list = new ArrayList<>(friends);
        String[] myStringList = list.toArray(new String[MyCursorAdapter.messages.size()]);
        editor.putString("Friends", TextUtils.join("‚‗‚", myStringList)).apply();
        editor.commit();
    }

}