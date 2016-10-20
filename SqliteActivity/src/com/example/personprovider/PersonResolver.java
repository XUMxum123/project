package com.example.personprovider;

import com.example.sqliteactivity.Person;
import com.example.sqliteactivity.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

public class PersonResolver extends Activity{
	public final static String TAG = "PersonResolver";
	Button personQuery;
	ListView listPerson;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personresolver);
        personQuery = (Button)findViewById(R.id.personQuery);
        listPerson = (ListView)findViewById(R.id.listPerson);
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(
                Person.CONTENT_URI, null, null,
                null, null);
        if (cursor.moveToNext()){
        	Log.d(TAG, "ID:" + cursor.getString(cursor.getColumnIndex(Person.KEY_ID)) + "\n"
        			 + "Name:" + cursor.getString(cursor.getColumnIndex(Person.KEY_NAME)) + "\n"
        			 + "Age:" + cursor.getInt(cursor.getColumnIndex(Person.KEY_AGE)) + "\n"
        			);
        }
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                R.layout.list, cursor, new String[] {
//                        IProivderMetaData.BookTableMetaData.BOOK_ID,
//                        IProivderMetaData.BookTableMetaData.BOOK_NAME,
//                        IProivderMetaData.BookTableMetaData.BOOL_PUBLISHER },
//                new int[] { R.id.book_id, R.id.book_name, R.id.book_publisher });
//        setListAdapter(adapter);
    }

}
