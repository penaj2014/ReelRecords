/*  Reel Records
*   Author: Jorge Pena
 */
package com.example.reelrecordsv1;
import java.util.ArrayList;

/*
LocalDB provides a shell for all the locally stored data pulled from HTTP requests done over REST API.
LocalBD also contains methods which allow data to be stored directly into fields.
 */
public class LocalDB {
    //variable declarations for log-in parameters
    public static String []usernameArray = new String[100];
    public static String []passwordArray = new String[100];
    public static String currentName = null;
    public static String temp = "temp";
    //variable declarations for cached data
    public static ArrayList<String> results = new ArrayList<>();
    public static ArrayList<String> images = new ArrayList<>();
    public static ArrayList<String> savedText = new ArrayList<>();
    public static ArrayList<String> savedImages = new ArrayList<>();
    private static int count = 0;

    /*
    createAccount()
    Description: Adds username and password strings to an array which authenticates a user account.
    @param string username              user account id
    @param string password              user account credentials
     */
    public static void createAccount(String username, String password)
    {
        usernameArray[count] = username;
        passwordArray[count] = password;
        count++;
    }

    /*
    currentUser()
    Description: Keeps track of the current user within the system.
    @param string s                 username logged into the account
     */
    public static void currentUser(String s)
    {
        currentName = s;
    }

    /*
    resultsArray()
    Description: Stores the data received from the JSON payload into an ArrayList<>
    @param ArrayList<String> s                    string text data for films
    @param ArrayList<String> t                    string text url for film posters
     */
    public static void resultsArray(ArrayList<String> s, ArrayList<String> t)
    {
        results = null; images = null;
        results = s; images = t;
    }

    /*
    savedArray()
    Description: Creates a local cache for saved film queries.
    @param string t                       stores the text of a film query
    @param string i                       stores the url text of a queried film poster
     */
    public static void savedArray(String t, String i)
    {
        savedText.add(t);
        savedImages.add(i);
    }

    /*
    deleteSavedArray()
    Description: Deletes an entry from a local cache for saved film queries.
    @param string t                      int value as a string pointing to the specified item's text to be deleted
    @param string i                      int value as a string pointing to the specified item's poster url text to be deleted
     */

    public static void deleteSavedArray(String t, String i)
    {
        savedText.remove(t);
        savedImages.remove(i);
    }
}
