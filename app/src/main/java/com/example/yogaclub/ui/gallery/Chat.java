package com.example.yogaclub.ui.gallery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yogaclub.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chat extends AppCompatActivity {


    private EditText editText;
    //private Scaledrone scaledrone;
    ArrayList<Message> messages = new ArrayList<>();
    public static Friend friend;

    public static String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Yoga club chat");
        editText = (EditText) findViewById(R.id.editText);
        ListView messageList = findViewById(R.id.messages_view);
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        List<String> messagesList = new ArrayList<String>(Arrays.asList(TextUtils.split(save.getString("Messages", ""), "‚‗‚")));
        List<String> namesList = new ArrayList<String>(Arrays.asList(TextUtils.split(save.getString("Names", ""), "‚‗‚")));
        MessageAdapter adapter = new MessageAdapter(Chat.this);
        MyCursorAdapter.messages = new ArrayList<>();
        // устанавливаем для списка адаптер
        int nameNumber = (int) (Math.random() * ((Friends.friends.size() - 1) + 1));
        if (messagesList.size() == 0) {
            adapter.add(new Message("Hi! How are you doing?", false, Friends.friends.get(nameNumber)));
            MyCursorAdapter.messages.add(new Message("Hi! How are you doing?", false, Friends.friends.get(nameNumber)));
        }
        for (int i = 0; i < messagesList.size(); ++i) {
            String message = messagesList.get(i);
            String name = namesList.get(i);
            if (name.equals("Me")) {
                adapter.add(new Message(message, true, name));
                MyCursorAdapter.messages.add(new Message(message, true, name));
            } else {
                adapter.add(new Message(message, false, name));
                MyCursorAdapter.messages.add(new Message(message, false, name));
            }
        }
        messageList.setAdapter(adapter);
        ImageButton send = findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add(new Message(editText.getText().toString(), true, "Me"));
                MyCursorAdapter.messages.add(new Message(editText.getText().toString(), true, "Me"));
                messageList.setAdapter(adapter);
                editText.getText().clear();
                Random rand = new Random();
                int nameNumber = (int) (Math.random() * ((Friends.friends.size() - 1) + 1));
                int textNumber = (int) (Math.random() * ((Friends.texts.size() - 1) + 1));
                adapter.add(new Message(Friends.texts.get(textNumber), false, Friends.friends.get(nameNumber)));
                MyCursorAdapter.messages.add(new Message(Friends.texts.get(textNumber), false, Friends.friends.get(nameNumber)));
                messageList.setAdapter(adapter);
            }
        });

    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public static void setName(String friend) {
        name = friend;
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences save = getSharedPreferences("SAVE", 0);
        SharedPreferences.Editor editor = save.edit(); //создаём
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < MyCursorAdapter.messages.size(); ++i) {
            list.add(MyCursorAdapter.messages.get(i).getText());
        }
        String[] myStringList = list.toArray(new String[MyCursorAdapter.messages.size()]);
        editor.putString("Messages", TextUtils.join("‚‗‚", myStringList)).apply();
        editor.commit();
        SharedPreferences saveNames = getSharedPreferences("SAVE", 0);
        SharedPreferences.Editor editorNames = saveNames.edit();
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < MyCursorAdapter.messages.size(); ++i) {
            names.add(MyCursorAdapter.messages.get(i).name);
        }
        String[] myStringNames = names.toArray(new String[MyCursorAdapter.messages.size()]);
        editorNames.putString("Names", TextUtils.join("‚‗‚", myStringNames)).apply();
        editorNames.commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}