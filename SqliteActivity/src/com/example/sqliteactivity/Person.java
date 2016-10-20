package com.example.sqliteactivity;

import android.net.Uri;

public class Person {
	
	public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
    public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
    public static final String MIME_ITEM = "vnd.example.person";
    
    public static final String MIME_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MIME_ITEM ;
    public static final String MIME_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MIME_ITEM ;
    
    public static final String AUTHORITY = "com.example.personprovider";
    public static final String PATH_SINGLE = "person/#";
    public static final String PATH_MULTIPLE = "person";
    public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
    

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static final String KEY_INFO = "info";
	
    /*
     * maybe fixed ....
     * */
	public int _id;  
    public String name;  
    public int age;  
    public String info;  
      
    public Person() {  
    }  
      
    public Person(String name, int age, String info) {  
        this.name = name;  
        this.age = age;  
        this.info = info;  
    }  
}
