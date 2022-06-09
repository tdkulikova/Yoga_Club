package com.example.yogaclub.ui.gallery;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.yogaclub.R;
import com.example.yogaclub.ui.home.MainActivityVideo;

import java.util.ArrayList;

public class MyCursorAdapter extends SimpleCursorAdapter {
    private final int layout_;
    String[] from;
    int[] to;
    ListView listView;
    EditText edit2;
    String SENT_SMS = "SENT_SMS";
    String DELIVER_SMS = "DELIVER_SMS";
    Intent sent_sms = new Intent(SENT_SMS);
    Intent deliver_sms = new Intent(DELIVER_SMS);
    PendingIntent sent, delivered;

    public static ArrayList<Message> messages = new ArrayList<Message>();

    public MyCursorAdapter(Context context, int layout, Cursor
            c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        layout_ = layout;
    }

    @Override
    public void bindView(View view, Context _context, Cursor cursor2) {
        int currentPos = cursor2.getInt((int)cursor2.getColumnIndex("_id"));
        String name = cursor2.getString((int) cursor2.getColumnIndex("Name"));
        String phone = cursor2.getString((int)cursor2.getColumnIndex("Phone"));
        listView = Friends.listView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new
                        AlertDialog.Builder(_context);
                dialog.setMessage("Enter new value:");
                dialog.setTitle("Changing the item");
                LayoutInflater inflater = new
                        LayoutInflater(_context) {
                            @Override
                            public LayoutInflater cloneInContext(Context context) {
                                return null;
                            }
                        };
                View dialogView = Friends.dialogView;
                dialog.setView(dialogView);
                EditText editName = dialogView.findViewById(R.id.editTextName);
                EditText editPhone = dialogView.findViewById(R.id.editTextPhone);
                //edit2.setText(text.getText().toString());
                dialog.setNeutralButton("OK", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int i) {
                                SQLiteDatabase db =
                                        _context.openOrCreateDatabase("DBName", Context.MODE_PRIVATE, null);
                                db.execSQL("UPDATE MyTable SET Name='" + editName.getText().toString() + "' WHERE _id=" + currentPos + "");
                                db.execSQL("UPDATE MyTable SET Phone='" + editPhone.getText().toString() + "' WHERE _id=" + currentPos + "");
                                Friends.friends.remove(currentPos);
                                Cursor cursor = db.rawQuery("SELECT * FROM Mytable", null);
                                from = new String[]{"Name", "Phone"};
                                to = new int[]{R.id.name, R.id.phone};
                                MyCursorAdapter scAdapter = new
                                        MyCursorAdapter(_context, R.layout.list_item5, cursor, from, to);
                                listView.setAdapter(scAdapter);
                                db.close();
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        TextView nameText = view.findViewById(R.id.name);
        nameText.setText(name);
        TextView phoneText = view.findViewById(R.id.phone);
        phoneText.setText(phone);
        ImageButton butPhone = view.findViewById(R.id.phoneButton);
        ImageButton butSms = view.findViewById(R.id.smsButton);
        ImageButton butDel = view.findViewById(R.id.deleteButton);
        butPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDialer(phone);
            }

            public void launchDialer(String number) {
                String numberToDial = "tel:" + number;
                _context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(numberToDial)));
            }
        });
        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =
                        _context.openOrCreateDatabase("DBName", Context.MODE_PRIVATE, null);
                db.execSQL("DELETE FROM MyTable WHERE _id=" + currentPos + "");
                Cursor cursor = db.rawQuery("SELECT * FROM Mytable", null);
                from = new String[]{"Name", "Phone"};
                to = new int[]{R.id.name, R.id.phone};
                MyCursorAdapter scAdapter = new
                        MyCursorAdapter(_context, R.layout.list_item5, cursor, from, to);
                for (int i = 0; i < Friends.friends.size(); ++i) {
                    if (Friends.friends.get(i) == name) {
                        Friends.friends.remove(i);
                    }
                }
                listView.setAdapter(scAdapter);
                db.close();

            }
        });
        sent = PendingIntent.getBroadcast(_context, 0, sent_sms, 0 );
        delivered = PendingIntent.getBroadcast(_context, 0, deliver_sms, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor1,
                        ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout_, parent, false);
        return view;
    }
}
