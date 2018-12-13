package be.thomasmore.project1_app5v1;

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
                "FOREIGN KEY(conditie_id1) REFERENCES conditie(id),"+
                "FOREIGN KEY(conditie_id2) REFERENCES conditie(id)," +
                "FOREIGN KEY(conditie_id3) REFERENCES conditie(id))";
        db.execSQL(CREATE_TABLE_GROEP);

        String CREATE_TABLE_KLAS = "CREATE TABLE klas (" +
                "id INTEGER PRIMARY KEY," +
                "naam TEXT,"+
                "jaar INTEGER)";
        db.execSQL(CREATE_TABLE_KLAS);

        String CREATE_TABLE_LEERLING = "CREATE TABLE leerling (" +
                "id INTEGER PRIMARY KEY," +
                "naam TEXT,"+
                "voornaam TEXT,"+
                "geboortedatum DATE,"+
                "klasId INTEGER,"+
                "groepId INTEGER," +
                "FOREIGN KEY(klasId) REFERENCES klas(id)," +
                "FOREIGN KEY(groepId) REFERENCES groep(id))";;
        db.execSQL(CREATE_TABLE_LEERLING);

        //invoegen van gegevens
        insertConditie(db);
        insertKlas(db);
        insertGroep(db);
        insertLeerling(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS conditie");
        db.execSQL("DROP TABLE IF EXISTS klas");
        db.execSQL("DROP TABLE IF EXISTS groep");
        db.execSQL("DROP TABLE IF EXISTS leerling");

        // Create tables again
        onCreate(db);
    }

    private void insertConditie(SQLiteDatabase db) {
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (0, 'Duikbril');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (1, 'Klimtouw', 'Kroos', 'Riet');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (2, 'Val', 'Compas', 'Steil');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (3, 'Zwaan', 'Kamp', 'Zaklamp');");
    }
    private void insertKlas(SQLiteDatabase db) {
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (0, 'ITF', 1);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (1, 'Boekhouden', 2);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (2, 'Marketing', 2);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (3, 'ITF', 3);");
    }
    private void insertGroep(SQLiteDatabase db) {
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (0, 1, 2, 3);"); // groep A
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (1, 3, 1, 2);"); // groep B
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (2, 2, 3, 1);"); // groep C
    }
    private void insertLeerling(SQLiteDatabase db) {
        db.execSQL("INSERT INTO klas (id, naam, voornaam, geboortedatum, klasId, groepId) VALUES (0, 'Joukes', 'Thibaut', '25/04/1997', 4, 2 );");;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, geboortedatum, klasId, groepId) VALUES (0, 'Vansprengel', 'Thomas', '9/11/1998', 1, 1 );");;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, geboortedatum, klasId, groepId) VALUES (0, 'Greif', 'Matthias', '5/03/1997', 0, 2 );");;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, geboortedatum, klasId, groepId) VALUES (0, 'Bomhals', 'Thibaut', '17/06/1997', 2, 0 );");;
        db.execSQL("INSERT INTO klas (id, naam, voornaam, geboortedatum, klasId, groepId) VALUES (0, 'Sledsens', 'Toon', '2/09/1998', 2, 0 );");;
    }



    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------


}