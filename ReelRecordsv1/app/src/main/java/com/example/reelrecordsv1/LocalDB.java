package com.example.reelrecordsv1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LocalDB {
    //variable declarations
    public static String []usernameArray = new String[100];
    public static String []passwordArray = new String[100];
    public static ArrayList<String> results = new ArrayList<>();
    public static ArrayList<String> images = new ArrayList<>();
    public static ArrayList<String> savedText = new ArrayList<>();
    public static ArrayList<String> savedImages = new ArrayList<>();
    public static String currentName = null;
    public static String temp = "temp";
    private static int count = 0;
    public static void createAccount(String username, String password)
    {
        usernameArray[count] = username;
        passwordArray[count] = password;
        count++;
    }
    public static void currentUser(String s)
    {
        currentName = s;
    }
    public static void resultsArray(ArrayList<String> s, ArrayList<String> t)
    {
        results = null; images = null;
        results = s; images = t;
    }
    public static void savedArray(String t, String i)
    {
        savedText.add(t);
        savedImages.add(i);
    }
    public static void deleteSavedArray(String t, String i)
    {
        savedText.remove(t);
        savedImages.remove(i);
    }
}
