package com.example.reelrecordsv1;

public class LocalDB {
    //variable declarations
    public static String []usernameArray = new String[100];
    public static String []passwordArray = new String[100];
    private static int count = 0;
    public static void createAccount(String username, String password)
    {
        usernameArray[count] = username;
        passwordArray[count] = password;
        count++;
    }
    public static void currentUser()
    {
        //
    }
}
