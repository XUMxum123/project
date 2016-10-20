package com.example.personprovider;

import com.example.sqliteactivity.DBHelper;
import com.example.sqliteactivity.Person;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.util.Log;

public class PersonProvider extends ContentProvider{
	
	public static final String TAG = "PersonProvider";
	private DBHelper dbOpenHelper;
	private SQLiteDatabase dbOpen;
	private static final int MULTIPLE_PEOPLE = 1;
    private static final int SINGLE_PEOPLE = 2;
    private static final UriMatcher uriMatcher ;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Person.AUTHORITY, Person.PATH_MULTIPLE, MULTIPLE_PEOPLE);
        uriMatcher.addURI(Person.AUTHORITY, Person.PATH_SINGLE, SINGLE_PEOPLE);
    }
   
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		Context context = getContext();
		dbOpenHelper = new DBHelper(context);
		dbOpen = dbOpenHelper.getWritableDatabase();
		if(dbOpen == null){
			return false;
		}else{
			return true;
		}		
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		    Log.d(TAG, uri.toString());
	        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        qb.setTables(DBHelper.DATABASE_NAME);
	        switch (uriMatcher.match(uri)){
	            case SINGLE_PEOPLE:
	                qb.appendWhere(Person.KEY_ID+"="+uri.getPathSegments().get(1));
	                break;
	            default:
	                break;
	        }
	        Cursor cursor = qb.query(dbOpen,
	                projection,
	                selection,
	                selectionArgs,
	                null,
	                null,
	                sortOrder);
	        cursor.setNotificationUri(getContext().getContentResolver(), uri);
	        return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		  Log.d(TAG, uri.toString());
		  switch (uriMatcher.match(uri)){
          case MULTIPLE_PEOPLE:
              return Person.MIME_TYPE_MULTIPLE;
          case SINGLE_PEOPLE:
              return Person.MIME_TYPE_SINGLE;
          default:
              throw new IllegalArgumentException("Unkown uro:"+uri);
      }
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Log.d(TAG, uri.toString());
		long id = dbOpen.insert(DBHelper.DATABASE_NAME, null, values);
        if(id>0){
            Uri newUri = ContentUris.withAppendedId(Person.CONTENT_URI,id);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("failed to insert row into " + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		Log.d(TAG, uri.toString());
		int count = 0;
        switch (uriMatcher.match(uri)){
            case MULTIPLE_PEOPLE:
                count = dbOpen.delete(DBHelper.DATABASE_NAME, selection, selectionArgs);
                break;
            case SINGLE_PEOPLE:
                String segment = uri.getPathSegments().get(1);
                count = dbOpen.delete(DBHelper.DATABASE_NAME, Person.KEY_ID + "=" + segment, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		 Log.d(TAG, uri.toString());
		 int count;
	        switch (uriMatcher.match(uri)){
	            case MULTIPLE_PEOPLE:
	                count = dbOpen.update(DBHelper.DATABASE_NAME, values, selection, selectionArgs);
	                break;
	            case SINGLE_PEOPLE:
	                String segment = uri.getPathSegments().get(1);
	                count = dbOpen.update(DBHelper.DATABASE_NAME, values, Person.KEY_ID + "=" + segment, selectionArgs);
	                break;
	            default:
	                throw new IllegalArgumentException("Unknow URI: " + uri);
	        }
	        getContext().getContentResolver().notifyChange(uri, null);
	        return count;
	}

}
