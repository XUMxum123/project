package com.example.sqliteactivity;

import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
//import android.content.ContentValues;
//import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
//import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SqliteActivity extends Activity {
	private DBManager mgr;  
    private ListView listView; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		listView = (ListView) findViewById(R.id.listView);  
        //初始化DBManager  
        mgr = new DBManager(this);
/*		SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
		db.execSQL("DROP TABLE IF EXISTS person"); 
        Person person = new Person();  
        person.name = "john";  
        person.age = 30; 
        db.execSQL("INSERT INTO person VALUES (NULL, ?, ?)", new Object[]{person.name, person.age});
        person.name = "david";  
        person.age = 33;  
        
        //ContentValues以键值对的形式存放数据  
        ContentValues cv = new ContentValues();  
        cv.put("name", person.name);  
        cv.put("age", person.age);  
        //插入ContentValues中的数据  
        db.insert("person", null, cv);  
          
        cv = new ContentValues();  
        cv.put("age", 35);  
        //更新数据  
        db.update("person", cv, "name = ?", new String[]{"john"});  
          
        Cursor c = db.rawQuery("SELECT * FROM person WHERE age >= ?", new String[]{"33"});  
        while (c.moveToNext()) {  
            int _id = c.getInt(c.getColumnIndex("_id"));  
            String name = c.getString(c.getColumnIndex("name"));  
            int age = c.getInt(c.getColumnIndex("age"));  
            Log.i("db", "_id=>" + _id + ", name=>" + name + ", age=>" + age);  
        }  
        c.close();  
          
        //删除数据  
        db.delete("person", "age < ?", new String[]{"35"});  
          
        //关闭当前数据库  
        db.close();  
          
        //删除test.db数据库  
        //deleteDatabase("test.db"); 
*/	}
	
	@Override  
	protected void onDestroy() {  
	        super.onDestroy();  
	        //应用的最后一个Activity关闭时应释放DB  
	        mgr.closeDB();  
	} 
	
    public void add(View view) {  
        ArrayList<Person> persons = new ArrayList<Person>();  
          
        Person person1 = new Person("Ella", 22, "lively girl");  
        Person person2 = new Person("Jenny", 22, "beautiful girl");  
        Person person3 = new Person("Jessica", 23, "sexy girl");  
        Person person4 = new Person("Kelly", 23, "hot baby");  
        Person person5 = new Person("Jane", 25, "a pretty woman");  
          
        persons.add(person1);  
        persons.add(person2);  
        persons.add(person3);  
        persons.add(person4);  
        persons.add(person5);  
          
        mgr.add(persons);  
    }  
      
    public void update(View view) {  
        Person person = new Person();  
        person.name = "Jane";  
        person.age = 30;  
        mgr.updateAge(person);  
    }  
      
    public void delete(View view) {  
        Person person = new Person();  
        person.age = 30;  
        mgr.deleteOldPerson(person);  
    }  
      
    public void query(View view) {  
        List<Person> persons = mgr.query();  
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();  
        for (Person person : persons) {  
            HashMap<String, String> map = new HashMap<String, String>();  
            map.put("name", person.name);  
            map.put("info", person.age + " years old, " + person.info);  
            list.add(map);  
        }  
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,  
                    new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});  
        listView.setAdapter(adapter);  
    }  
      
    @SuppressWarnings("deprecation")
	public void queryTheCursor(View view) {  
        Cursor c = mgr.queryTheCursor();  
        startManagingCursor(c); //托付给activity根据自己的生命周期去管理Cursor的生命周期  
        CursorWrapper cursorWrapper = new CursorWrapper(c) {  
            @Override  
            public String getString(int columnIndex) {  
                //将简介前加上年龄  
                if (getColumnName(columnIndex).equals("info")) {  
                    int age = getInt(getColumnIndex("age"));  
                    return age + " years old, " + super.getString(columnIndex);  
                }  
                return super.getString(columnIndex);  
            }  
        };  
        //确保查询结果中有"_id"列  
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,   
                cursorWrapper, new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});  
        ListView listView = (ListView) findViewById(R.id.listView);  
        listView.setAdapter(adapter);  
    } 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
		return true;
	}

}
