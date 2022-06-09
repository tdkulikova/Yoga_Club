package com.example.yogaclub.ui.gallery;

public class Message {
    private String text; // message body
    //private MemberData memberData; // data of the user that sent this message
    private boolean belongsToCurrentUser; // is this message sent by us?

    String name;

    public Message(String text /*MemberData memberData*/, boolean belongsToCurrentUser, String name) {
        this.text = text;
        //this.memberData = memberData;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.name = name;
    }

    public String getText() {
        return text;
    }

   /* public MemberData getMemberData() {
        return memberData;
    }*/

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
