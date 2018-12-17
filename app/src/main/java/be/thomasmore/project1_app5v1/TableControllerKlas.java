package be.thomasmore.project1_app5v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerKlas extends DatabaseHelper {
    public TableControllerKlas(Context context){
        super(context);
    }

    public long insertKlas(Klas klas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("naam", klas.getNaam());
        values.put("jaar", klas.getJaar());

        long id = db.insert("klas", null, values);

        db.close();
        return id;
    }

    public int count(){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM klas";
        int klasCount = db.rawQuery(sql,null).getCount();
        db.close();

        return klasCount;
    }

    public List<Klas> leesAlleKlassen() {
        List<Klas> lijst = new ArrayList<Klas>();

        String selectQuery = "SELECT  * FROM KLAS ORDER BY naam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Klas klasje = new Klas(cursor.getLong(0), cursor.getString(1), cursor.getInt(2));
                lijst.add(klasje);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public Klas readSingleRecord(long klasId) {

        Klas editKlas = null;

        String sql = "SELECT * FROM klas WHERE id = " + klasId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            Long id = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
            String naam = cursor.getString(cursor.getColumnIndex("naam"));
            int jaar = cursor.getInt(cursor.getColumnIndex("jaar"));

            editKlas = new Klas();
            editKlas.id = id;
            editKlas.naam = naam;
            editKlas.jaar = jaar;
        }

        cursor.close();
        db.close();

        return editKlas;

    }

    public boolean updateKlas(Klas klas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("naam", klas.getNaam());
        values.put("jaar", klas.getJaar());

        int numrows = db.update(
                "klas",
                values,
                "id = ?",
                new String[]{String.valueOf(klas.getId())});

        db.close();
        return numrows > 0;
    }
    public boolean delete(long id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("klas", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }

}

