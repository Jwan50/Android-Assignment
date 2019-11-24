package com.example.casper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class CardsDbHelper extends SQLiteOpenHelper {


    public CardsDbHelper(@Nullable Context context) {
        super(context,"CardsData.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating text and deadline of the tasks table in the firebase database
        String SQL_CreateCardsTable = "CREATE TABLE cards(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "task TEXT NOT NULL," +
                "deadline TEXT)";
        db.execSQL(SQL_CreateCardsTable);

    }

    @Override
    // will recreate the cards table by dropping the old version of data to a new data table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cards");
        onCreate(db);

    }
    // adding data from user to the table
    public boolean insertCard(String task, String deadline){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task",task);
        contentValues.put("deadline",deadline);
        long status =db.insert("cards",null, contentValues);
        if (status == -1){
            return false;
        }
        return true;
    }
    // deleting card by user
    public void deleteCard(String t){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "task"+ " = ?";
        String[] args = {t};
        db.delete("cards",sql,args);

    }

    // to get all the recorded card from database
    public Cursor getAllCards(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM cards",null);
        return res;

    }
}
