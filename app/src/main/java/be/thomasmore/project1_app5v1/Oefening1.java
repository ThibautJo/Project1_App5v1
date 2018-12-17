package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;

public class Oefening1 extends AppCompatActivity {

    public String Oefenwoord = "";
    public String Trainwoord = "";

    public Groep groep = new Groep();
    public Conditie conditie = new Conditie();

    public final DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        // Initialisatie
        //

        // opzoeken welke groep & conditie men zit
        // (TEST GEGEVENS) (LEERLING NOG NIET BESCHIKBAAR)
        Leerling leerling = db.leesAlleLeerlingen().get(0);

        groep = db.getGroep(leerling.getGroepID());
        // 1ste conditie testen --> nadien 2 andere conditie
        conditie = db.getConditie(groep.getConditie_id1());


        ImageView imageView = (ImageView) findViewById(R.id.oef1Foto);
        TextView textView = (TextView) findViewById(R.id.oef1Titel);

        try {
            Context context = imageView.getContext();

            int id = context.getResources().getIdentifier(conditie.getWoord1(), "drawable", context.getPackageName());

            imageView.setImageResource(id);
            textView.setText(conditie.getWoord1());

        }
        catch (Exception e){

        }



        //
        // Uitvoering
        //








    }

}
