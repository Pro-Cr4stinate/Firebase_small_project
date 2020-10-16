package com.example.walkingwalking;

public class Users {
    String Usersid;
    String Usernames;

    public Users() {
    }

    public Users(String usersid, String usernames) {
        this.Usersid = usersid;
        this.Usernames = usernames;
    }

    public String getUsersid() {
        return Usersid;
    }

    public String getUsernames() {
        return Usernames;
    }
}
