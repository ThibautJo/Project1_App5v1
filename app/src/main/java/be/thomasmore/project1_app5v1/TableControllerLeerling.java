package be.thomasmore.project1_app5v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerLeerling extends DatabaseHelper {
    public TableControllerLeerling(Context context){
        super(context);
    }

    public long insertLeerling(Leerling leerling) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("naam", leerling.getNaam());
        values.put("voornaam", leerling.getVoornaam());
        values.put("punten", leerling.getPunten());
        values.put("klasId", leerling.getKlasID());
        values.put("groepId", leerling.getGroepID());

        long id = db.insert("leerling", null, values);

        db.close();
        return id;
    }

    public int count(){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM leerling";
        int leerlingCount = db.rawQuery(sql,null).getCount();
        db.close();

        return leerlingCount;
    }

    public List<Leerling> leesAlleLeerlingen() {
        List<Leerling> lijst = new ArrayList<Leerling>();

        String selectQuery = "SELECT  * FROM LEERLING ORDER BY naam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Leerling leerling = new Leerling(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
                lijst.add(leerling);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public Leerling readSingleRecord(long leerlingId) {

        Leerling editLeerling = null;

        String sql = "SELECT * FROM leerling WHERE id = " + leerlingId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            Long id = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
            String naam = cursor.getString(cursor.getColumnIndex("naam"));
            String voornaam = cursor.getString(cursor.getColumnIndex("voornaam"));
            int punten = cursor.getInt(cursor.getColumnIndex("punten"));
            int klasid = cursor.getInt(cursor.getColumnIndex("klasId"));
            int groepid = cursor.getInt(cursor.getColumnIndex("groepId"));

            editLeerling = new Leerling();
            editLeerling.id = id;
            editLeerling.naam = naam;
            editLeerling.voornaam = voornaam;
            editLeerling.punten = punten;
            editLeerling.klasId = klasid;
            editLeerling.groepId = groepid;
        }

        cursor.close();
        db.close();

        return editLeerling;

    }

    public boolean updateLeerling(Leerling leerling) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("naam", leerling.getNaam());
        values.put("voornaam", leerling.getVoornaam());
        values.put("punten", leerling.getPunten());
        values.put("klasId", leerling.getKlasID());
        values.put("groepId", leerling.getGroepID());

        int numrows = db.update(
                "leerling",
                values,
                "id = ?",
                new String[]{String.valueOf(leerling.getId())});

        db.close();
        return numrows > 0;
    }
    public boolean delete(long id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("leerling", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;
    }


}

