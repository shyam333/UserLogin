package helloworld.demo.com.userlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shyamramesh on 08/05/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users";
    public static final String TABLE_NAME = "user_details";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    private static final int DATABASE_VERSION = 1;

    public static final String CREATE_QUERY = " CREATE TABLE " + TABLE_NAME + " ( " +
            COL_1 + " INTEGER PRIMARY KEY ," +
            COL_2 + " TEXT ," +
            COL_3 + " TEXT ," +
            COL_4 + " TEXT )";

    private String SELECT_QUERY = " SELECT " +
            COL_1 + " , " +
            COL_2 + " , " +
            COL_3 + " , " +
            COL_4 +
            " FROM " + TABLE_NAME + "  WHERE " + COL_3 + " = ? ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL_2, contact.getName());
        contentvalues.put(COL_3, contact.getEmail());
        contentvalues.put(COL_4, contact.getPassword());
        long result = db.insert(TABLE_NAME,null,contentvalues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public String checkEmail(String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(SELECT_QUERY, new String[]{Email});
            if(cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                return cursor.getString(cursor.getColumnIndex(COL_3));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean checkUser(String email,String password)
    {
        {
            String[] coloumns = {
                    COL_1
            };

            SQLiteDatabase db = this.getWritableDatabase();

            String selection = COL_3 + " =?" + " AND " + COL_4 + " =?";

            String[] selectionArgs = {email,password};

            Cursor cursor = db.query(TABLE_NAME,
                    coloumns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);

            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            if(cursorCount > 0)
            {
                return true;
            }
            return false;
        }
    }


}