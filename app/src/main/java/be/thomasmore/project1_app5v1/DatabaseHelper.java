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
    private static final String DATABASE_NAME = "studenten";

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
        db.execSQL(CREATE_TABLE_LEERLING);

        String CREATE_TABLE_WOORDUITBREIDING = "CREATE TABLE woorduitbreiding (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woord TEXT," +
                "defenitie TEXT," +
                "contextZinJuist TEXT," +
                "contextZinFout TEXT," +
                "semantischWeb TEXT," +
                "tweelettergreep TEXT," +
                "startValue INTEGER)";
        db.execSQL(CREATE_TABLE_WOORDUITBREIDING);

        String CREATE_TABLE_CORRELATIE = "CREATE TABLE correlatie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "doelwoord TEXT," +
                "woordJuist1 TEXT," +
                "woordJuist2 TEXT," +
                "woordJuist3 TEXT," +
                "woordFout TEXT)";
        db.execSQL(CREATE_TABLE_CORRELATIE);

        //invoegen van gegevens
        insertCondities(db);
        insertKlassen(db);
        insertGroepen(db);
        insertLeerlingen(db);
        insertWoordUitbreiding(db);
        insertCorrelatie(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS conditie");
        db.execSQL("DROP TABLE IF EXISTS klas");
        db.execSQL("DROP TABLE IF EXISTS groep");
        db.execSQL("DROP TABLE IF EXISTS leerling");
        db.execSQL("DROP TABLE IF EXISTS woorduitbreiding");
        db.execSQL("DROP TABLE IF EXISTS correlatie");

        // Create tables again
        onCreate(db);
    }

    private void insertCondities(SQLiteDatabase db) {
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (0, 'duikbril','','');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (1, 'klimtouw', 'kroos', 'riet');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (2, 'val', 'compas', 'steil');");
        db.execSQL("INSERT INTO conditie (id, woord1, woord2, woord3) VALUES (3, 'zwaan', 'kamp', 'zaklamp');");
    }

    private void insertKlassen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (0, 'A', 1);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (1, 'B', 2);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (2, 'C', 2);");
        db.execSQL("INSERT INTO klas (id, naam, jaar) VALUES (3, 'C', 3);");
    }

    private void insertGroepen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (0, 1, 2, 3);"); // groep A
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (1, 3, 1, 2);"); // groep B
        db.execSQL("INSERT INTO groep (id, conditie_id1,conditie_id2,conditie_id3) VALUES (2, 2, 3, 1);"); // groep C
    }

    private void insertLeerlingen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO leerling (id, naam, voornaam, klasId, groepId) VALUES (0, 'Joukes', 'Thibaut', 4, 2 );");
        db.execSQL("INSERT INTO leerling (id, naam, voornaam, klasId, groepId) VALUES (1, 'Vansprengel', 'Thomas', 1, 1 );");
        db.execSQL("INSERT INTO leerling (id, naam, voornaam, klasId, groepId) VALUES (2, 'Greif', 'Matthias', 0, 2 );");
        db.execSQL("INSERT INTO leerling (id, naam, voornaam, klasId, groepId) VALUES (3, 'Bomhals', 'Thibaut', 2, 0 );");
        db.execSQL("INSERT INTO leerling (id, naam, voornaam, klasId, groepId) VALUES (4, 'Sledsens', 'Toon', 2, 0 );");
    }

    private void insertWoordUitbreiding(SQLiteDatabase db) {
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(1, 'De duikbril', 'Een duikbril is een bril voor onder water. Daarmee kun je onder water je ogen open houden.', " +
                "'Met zijn duikbril kan de jongen de vissen onder water goed bekijken.', 'Met een duikbril kan ik schrijven op papier.', " +
                "'Ogen, zwemmen, in de zee, schrijven', 'Duik-bril', 1);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(2, 'Het klimtouw', 'Een klimtouw is een touw waarin je omhoog kunt klimmen. ', " +
                "'In de turnzaal klim ik omhoog in het klimtouw. ', 'Ik wacht op de bus in het klimtouw.', " +
                "'Klimmen, sterk, de turnzaal, het zwembad', 'Klim-touw', 1);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(3, 'Het kroos', 'Kroos bestaat uit kleine, groene plantjes die op het water groeien. Je ziet het bijvoorbeeld in een sloot. ', " +
                "'De vijver is groen door het kroos. ', 'Oma en het kroos zitten in de auto.', " +
                "'Groen, in de vijver, de eend, de lamp', '', 2);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(4, 'Het riet', 'Riet lijkt op hoog gras. Het heeft lange stengels en groeit langs het water. ', " +
                "'De eenden zitten bij het water tussen het riet', 'Ik ga naar buiten met mijn jas en het riet aan.', " +
                "'De vijver, de eend, het bos, de bril', '', 1);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(5, 'De val', 'Als je een val maakt, val je op de grond.', " +
                "'Wat was dat een pijnlijke val!', 'Jan zit op de val aan tafel.', " +
                "'De pijn, naar voor, de pleister, de appel', '', 2);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(6, 'Het kompas', 'Met een kompas weet je waar je naartoe moet. De naald van het kompas geeft het noorden aan.', " +
                "'Omdat papa niet weet waar we naartoe moeten lopen, kijkt hij op zijn kompas. ', 'Mama belt met het kompas naar papa.', " +
                "'Wandelen, de rugzak, de landkaart, het bad', 'Kom-pas', 2);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(7, 'steil', 'Een steile berg gaat heel schuin omhoog of omlaag.', " +
                "'Jan loopt de steile berg omhoog.', 'Papa leest een steil verhaaltje voor.', " +
                "'De berg, beklimmen, de trap, de bloem', '', 2);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(8, 'De zwaan', 'Een zwaan is een grote vogel met een lange, kromme hals. Meestal zijn zwanen wit, maar soms zwart.', " +
                "'In de vijver in het park zwemt een witte zwaan.', 'De zwaan fietst in het park.', " +
                "'De vijver, vleugels, wit, het boek', '', 1);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(9, 'Het kamp', 'Een kamp is een plaats om buiten te wonen en te slapen, bijvoorbeeld in tenten.', " +
                "'De kinderen zitten te eten tussen de tenten van het kamp.', 'Jonas wast zich met het kamp.', " +
                "'De tent, kampvuur, in de slaapzak, de deur', '', 1);");
        db.execSQL("INSERT INTO woorduitbreiding (id, woord, defenitie, contextZinJuist, contextZinFout, semantischWeb, tweelettergreep, startValue) " +
                "VALUES(10, 'De zaklamp', 'Een zaklamp is een kleine lamp die je overal mee naartoe kunt nemen.', " +
                "'De jongen schijnt met de zaklamp in de donkere grot.', 'Jef opent de deur met de zaklamp.', " +
                "'Het licht, de batterij, in het donker, het paard', 'Zak-lamp', 2);");

    }

    private void insertCorrelatie(SQLiteDatabase db){
        db.execSQL("INSERT INTO correlatie VALUES (1, 'duikbril','ogen','zee', 'zwemmen', 'schrijven');");
        db.execSQL("INSERT INTO correlatie VALUES (2, 'klimtouw','klimmen','sterk', 'turnzaal', 'zwembad');");
        db.execSQL("INSERT INTO correlatie VALUES (3, 'kroos','groen','vijver', 'lamp', 'eend');");
        db.execSQL("INSERT INTO correlatie VALUES (4, 'riet','vijver','eend', 'bos', 'bril');");
        db.execSQL("INSERT INTO correlatie VALUES (5, 'val','pijn','voorval', 'pleister', 'appel');");
        db.execSQL("INSERT INTO correlatie VALUES (6, 'kompas','wandelen','rugzak', 'landkaart', 'bad');");
        db.execSQL("INSERT INTO correlatie VALUES (7, 'steil','berg','beklimmen', 'trap', 'bloem');");
        db.execSQL("INSERT INTO correlatie VALUES (8, 'zwaan','vijver','vleugels', 'wit', 'boek');");
        db.execSQL("INSERT INTO correlatie VALUES (9, 'kamp','tent','kampvuur', 'slaapzak', 'deur');");
        db.execSQL("INSERT INTO correlatie VALUES (10, 'zaklamp','licht','batterij', 'donker', 'paard');");

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

    public List<Leerling> leesAlleLeerlingenByKlasId(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "leerling",      // tabelnaam
                new String[]{"id", "naam", "voornaam", "punten", "klasId", "groepId"}, // kolommen
                "klasId = ?",  // selectie
                new String[]{String.valueOf(id)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        List<Leerling> lijst = new ArrayList<Leerling>();

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

    public Leerling getLeerling(int id) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "leerling",      // tabelnaam
                new String[]{"id", "naam", "voornaam", "punten", "klasId", "groepId"}, // kolommen
                "id = ?",  // selectie
                new String[]{String.valueOf(id)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);

        Leerling leerling = new Leerling();

        if (cursor.moveToFirst()) {
            leerling = new Leerling(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getInt(5));
        }

        cursor.close();
        db.close();
        return leerling;
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

    public Groep getGroep(int id) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "groep",      // tabelnaam
                new String[]{"id", "conditie_id1", "conditie_id2", "conditie_id3"}, // kolommen
                "id = ?",  // selectie
                new String[]{String.valueOf(id)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);

        Groep groep = new Groep();

        if (cursor.moveToFirst()) {
            groep = new Groep(cursor.getLong(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
        }

        cursor.close();
        db.close();
        return groep;
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

    public Conditie getConditie(int id) {


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "conditie",      // tabelnaam
                new String[]{"id", "woord1", "woord2", "woord3"}, // kolommen
                "id = ?",  // selectie
                new String[]{String.valueOf(id)}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);

        Conditie conditie = new Conditie();

        if (cursor.moveToFirst()) {
            conditie = new Conditie(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        cursor.close();
        db.close();
        return conditie;
    }

    public List<WoordUitbreiding> leesAlleWoordUitbreidingen() {
        List<WoordUitbreiding> lijst = new ArrayList<WoordUitbreiding>();

        String selectQuery = "SELECT * FROM woorduitbreiding";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WoordUitbreiding woorduitbreiding = new WoordUitbreiding(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
                lijst.add(woorduitbreiding);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public WoordUitbreiding getWoordUitbreidingen(String filter) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "woorduitbreiding",      // tabelnaam
                new String[]{"id", "woord", "defenitie", "contextZinJuist", "contextZinFout", "semantischWeb", "tweelettergreep", "startValue"}, // kolommen
                "woord LIKE ?",  // selectie
                new String[]{"%" + filter + "%"}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);

        WoordUitbreiding woorduitbreiding = new WoordUitbreiding();

        if (cursor.moveToFirst()) {
            woorduitbreiding = new WoordUitbreiding(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
        }

        cursor.close();
        db.close();
        return woorduitbreiding;
    }

    public List<Correlatie> leesAlleCorrelaties() {
        List<Correlatie> lijst = new ArrayList<Correlatie>();

        String selectQuery = "SELECT * FROM correlatie";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Correlatie correlatie = new Correlatie(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
                lijst.add(correlatie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lijst;
    }

    public Correlatie getCorrelatie(String filter) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "correlatie",      // tabelnaam
                new String[]{"id", "doelwoord", "woordJuist1", "woordJuist2", "woordJuist3", "woordFout"}, // kolommen
                "doelwoord LIKE ?",  // selectie
                new String[]{"%" + filter + "%"}, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);

        Correlatie correlatie = new Correlatie();

        if (cursor.moveToFirst()) {
            correlatie = new Correlatie(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }

        cursor.close();
        db.close();
        return correlatie;
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

    //
    // Delete queries
    //
    public boolean deleteKlas(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numrows = db.delete(
                "klas",
                "id = ?",
                new String[]{String.valueOf(id)});

        db.close();
        return numrows > 0;
    }

    public boolean deleteLeerling(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numrows = db.delete(
                "leerling",
                "id = ?",
                new String[]{String.valueOf(id)});

        db.close();
        return numrows > 0;
    }

}