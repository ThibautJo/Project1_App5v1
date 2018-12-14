package be.thomasmore.project1_app5v1.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONDITIE = "CREATE TABLE conditie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord1 TEXT," +
                "woord2 TEXT ," +
                "woord3 TEXT)";
        db.execSQL(CREATE_TABLE_CONDITIE);

        String CREATE_TABLE_GROEP = "CREATE TABLE groep (" +
                "id INTEGER PRIMARY KEY," +
                "conditie_id1 INTEGER," +
                "conditie_id2 INTEGER," +
                "conditie_id3 INTEGER," +
                "FOREIGN KEY(conditie_id1) REFERENCES conditie(id)," +
                "FOREIGN KEY(conditie_id2) REFERENCES conditie(id)," +
                "FOREIGN KEY(conditie_id3) REFERENCES conditie(id))";
        db.execSQL(CREATE_TABLE_GROEP);

        String CREATE_TABLE_KLAS = "CREATE TABLE klas (" +
                "id INTEGER PRIMARY KEY," +
                "naam TEXT," +
                "jaar INTEGER)";
        db.execSQL(CREATE_TABLE_KLAS);

        String CREATE_TABLE_LEERLING = "CREATE TABLE leerling (" +
                "id INTEGER PRIMARY KEY," +
                "naam TEXT," +
                "voornaam TEXT," +
                "geboortedatum DATE," +
                "klasId INTEGER," +
                "groepId INTEGER," +
                "punten INTEGER," +
                "FOREIGN KEY(klasId) REFERENCES klas(id)," +
                "FOREIGN KEY(groepId) REFERENCES groep(id))";
        ;
        db.execSQL(CREATE_TABLE_LEERLING);

        //invoegen van gegevens
        insertCondities(db);
        insertKlassen(db);
        insertGroepen(db);
        insertLeerlingen(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS conditie");
        db.execSQL("DROP TABLE IF EXISTS klas");
        db.execSQL("DROP TABLE IF EXISTS groep");
        db.execSQL("DROP TABLE IF EXISTS leerling");

        // Create tables again
        onCreate(db);
    }

    private void insertCondities(SQLiteDatabase db) {
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (0, 'Duikbril');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (1, 'Klimtouw', 'Kroos', 'Riet');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (2, 'Val', 'Compas', 'Steil');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (3, 'Zwaan', 'Kamp', 'Zaklamp');");
    }

    private void insertKlassen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (0, 'ITF', 1);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (1, 'Boekhouden', 2);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (2, 'Marketing', 2);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (3, 'ITF', 3);");
    }

    private void insertGroepen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (0, 1, 2, 3);"); // groep A
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (1, 3, 1, 2);"); // groep B
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (2, 2, 3, 1);"); // groep C
    }

    private void insertLeerlingen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO klas (id, naam, voornaam, klasId, groepId) VALUES (0, 'Joukes', 'Thibaut', 4, 2 );");
        ;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, klasId, groepId) VALUES (0, 'Vansprengel', 'Thomas', 1, 1 );");
        ;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, klasId, groepId) VALUES (0, 'Greif', 'Matthias', 0, 2 );");
        ;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, klasId, groepId) VALUES (0, 'Bomhals', 'Thibaut', 2, 0 );");
        ;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, klasId, groepId) VALUES (0, 'Sledsens', 'Toon', 2, 0 );");
        ;
    }


    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------

    //
    // Read query's
    //
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

    public List<Groep> leesAlleGroep() {
        List<Groep> lijst = new ArrayList<Groep>();

        String selectQuery = "SELECT  * FROM GROEP";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Groep groep = new Groep(cursor.getLong(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
                lijst.add(groep);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public List<Conditie> leesAlleCondities() {
        List<Conditie> lijst = new ArrayList<Conditie>();

        String selectQuery = "SELECT  * FROM CONDITIE";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Conditie conditie = new Conditie(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                lijst.add(conditie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    //
    // Insert queries
    //
    public long insertKlas(Klas klas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", klas.getNaam());
        values.put("jaar", klas.getJaar());

        long id = db.insert("klas", null, values);

        db.close();
        return id;
    }
    public long insertLeerling(Leerling leerling) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", leerling.getNaam());
        values.put("voornaam", leerling.getVoornaam());
        values.put("punten", leerling.getPunten());
        values.put("kladId", leerling.getKlasID());
        values.put("groepId", leerling.getGroepID());

        long id = db.insert("leerling", null, values);

        db.close();
        return id;
    }

    //
    // Update queries
    //
    public boolean updateKlas(Klas klas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", klas.getNaam());
        values.put("jaar", klas.getJaar());

        int numrows = db.update(
                "klas",
                values,
                "id = ?",
                new String[] { String.valueOf(klas.getId()) });

        db.close();
        return numrows > 0;
    }
    public boolean updateLeerling(Leerling leerling) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", leerling.getNaam());
        values.put("voornaam", leerling.getVoornaam());
        values.put("punten", leerling.getPunten());
        values.put("kladId", leerling.getKlasID());
        values.put("groepId", leerling.getGroepID());

        int numrows = db.update(
                "leerling",
                values,
                "id = ?",
                new String[] { String.valueOf(leerling.getId()) });

        db.close();
        return numrows > 0;
    }

    //
    // Delete queries
    //
    public boolean deleteKlas(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numrows = db.delete(
                "klas",
                "id = ?",
                new String[] { String.valueOf(id) });

        db.close();
        return numrows > 0;
    }
    public boolean deleteLeerling(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numrows = db.delete(
                "leerling",
                "id = ?",
                new String[] { String.valueOf(id) });

        db.close();
        return numrows > 0;
    }

}