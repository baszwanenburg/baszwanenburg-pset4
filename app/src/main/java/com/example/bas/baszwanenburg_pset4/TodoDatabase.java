package com.example.bas.baszwanenburg_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bas on 21/11/2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {
    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed BIT)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('Do laundry', 0)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('Get started', 0)");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('Moar items', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS todos");
        onCreate(sqLiteDatabase);
    }

    private static TodoDatabase instance;
    public static TodoDatabase getInstance(Context context) {
        if(instance == null) {
            instance = new TodoDatabase(context, "todo", null, 2);}

        return instance;
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM todos", null);

        return cursor;
    }

    public void insert(String title, int completed) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("completed", completed);

        db.insert("todos", null, contentValues);
    }

    public void update(long id, int completed) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("completed", completed);

        db.update("todos", contentValues, "_id = " + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("todos", "_id = " + id, null);
    }
}