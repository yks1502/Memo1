package com.example.memo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by YUNKYUSEOK on 2017-06-30.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MEMO (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Memo memo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", memo.title);
        values.put("content", memo.content);

        db.insert("MEMO", null, values);
        db.close();
    }

    public void update(Memo memo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", memo.title);
        values.put("content", memo.content);
        db.update("MEMO", values, "id = ?", new String[] {String.valueOf(memo.id)});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM MEMO WHERE id = " + id + ";");
        db.close();
    }

    public ArrayList<Memo> getList() {
        ArrayList<Memo> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("MEMO", new String[] {"id", "title", "content"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idIndex = (int) cursor.getInt(cursor.getColumnIndex("id"));
            int titleIndex = cursor.getColumnIndex("title");
            int contentIndex = cursor.getColumnIndex("content");
            result.add(new Memo(cursor.getString(titleIndex), cursor.getString(contentIndex), idIndex));
        }
        cursor.close();
        db.close();
        return result;
    }
}
