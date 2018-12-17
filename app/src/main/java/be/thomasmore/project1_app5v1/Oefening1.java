package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Oefening1 extends AppCompatActivity {

    public String Oefenwoord = "";
    public String Trainwoord = "";

    public Groep groep = new Groep();
    public Conditie conditie = new Conditie();

    public final DatabaseHelper db = new DatabaseHelper(this);
    MediaPlayer audioPlayer;

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
        // ######### (TEST GEGEVENS) (LEERLING NOG NIET BESCHIKBAAR) ###########
        Leerling leerling = db.leesAlleLeerlingen().get(0);

        groep = db.getGroep(leerling.getGroepID());
        // 1. oefenwoord --> oef 1 tot en met 6 --> dan de andere 3 woorden
        //conditie = db.getConditie(groep.getConditie_id1());
        conditie = db.getConditie(0); // oefenwoord 'duikbril'


        ImageView imageView = (ImageView) findViewById(R.id.oef1Foto);
        TextView textView = (TextView) findViewById(R.id.oef1Titel);
        Button btnNext = (Button) findViewById(R.id.btnNext);


        try {
            Context context = imageView.getContext();

            int id = context.getResources().getIdentifier(conditie.getWoord1(), "drawable", context.getPackageName());

            imageView.setImageResource(id);
            textView.setText(conditie.getWoord1());

            //afspelen audio
            int audioFile = getApplicationContext().getResources().getIdentifier("oef1_"+conditie.getWoord1(), "raw", getApplicationContext().getPackageName());
            audioPlayer = MediaPlayer.create(this, audioFile);

            audioPlayer.start();


        }
        catch (Exception e){
            // crash activity??
            // terug naar start?
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioPlayer.stop();

                Intent intent = new Intent(getApplicationContext(), Oefening2.class);
                intent.putExtra("woord", conditie.getWoord1());

                startActivity(intent);
            }
        });


    }

}
