package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class Oefening3 extends AppCompatActivity {

    private static Context mContext;
    private String woord, audio = "";

    private MediaPlayer mediaPlayer;

    private Leerling leerling = new Leerling();
    private WoordUitbreiding woordUitbreiding = new WoordUitbreiding();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen van Oefening 1
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");

        // uitbreiding woord opzoeken d.m.v. woord
        woordUitbreiding = new DatabaseHelper(getContext()).getWoordUitbreidingen(woord.toLowerCase());

        // views
        final ImageView imageViewJuist = (ImageView) findViewById(R.id.oef3Juist);
        final ImageView imageViewFout = (ImageView) findViewById(R.id.oef3Fout);
        final TextView textView = (TextView) findViewById(R.id.oef3WoordUitleg);


        // groene knop
        imageViewJuist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Als getvalue 1 is dan word juist zin éérst getoond | daarna checken of 1ste zin aan het afspelen is
                if (audio.substring(audio.length() - 1).equals("1")) {
                    //volgende zin of next activity
                    if (woordUitbreiding.getStartValue() == 1) {
                        //volgende zin
                        textView.setText(woordUitbreiding.getContextZinFout());
                        setAudio("oef3_" + woord.trim() + "_zin2");
                    } else {
                        // next activity
                        Toast.makeText(getContext(), "next activity", Toast.LENGTH_SHORT).show();
                        startNextActivity();
                    }
                } else {
                    playAudio();
                }
            }
        });

        // rode knop
        imageViewFout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Als getvalue 2 is dan word foute zin éérst getoond | daarna checken of 1ste zin aan het afspelen is
                if (audio.substring(audio.length() - 1).equals("2")) { // als men 1ste keer klikt
                    //volgende zin of next activity
                    if (woordUitbreiding.getStartValue() == 1) {
                        // 1ste zin dat afgespeeld was, was juiste zin en nu is 2de zin aan het afspelen is is correct aangeduid door gebruiker -> next activity
                        Toast.makeText(getContext(), "next activity", Toast.LENGTH_SHORT).show();
                        startNextActivity();
                    } else {
                        // volgende zin
                        textView.setText(woordUitbreiding.getContextZinJuist());
                        setAudio("oef3_" + woord.trim() + "_zin1");
                    }
                } else { // replay van zin
                    playAudio();
                }
            }
        });


        // geluid afspelen --> onstart is string audio gedeclareerd
        if (woordUitbreiding.getStartValue() == 1) {
            textView.setText(woordUitbreiding.getContextZinJuist());
            audio = "oef3_" + woord + "_zin1";
        } else { // foute zin éérst
            textView.setText(woordUitbreiding.getContextZinFout());
            audio = "oef3_" + woord + "_zin2";
        }

        playAudio();


    }

    public void startNextActivity() {
        Intent intent = new Intent(getApplicationContext(), Oefening4.class);
        intent.putExtra("woord", woord);
        intent.putExtra("leerling", leerling);

        startActivity(intent);
    }

    // deze functie word opgeroepen voor het afspelen van het 2 de woord
    public void setAudio(final String audioString) {

        audio = "oef3_volgende_zin";//standaard harcoded zin = goed
        playAudio(); // tussen zin word afgespeeld

        // oncompletelistener
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                audio = audioString; //2 de zin word afgespeeld

                playAudio();
            }
        });

    }

    public void playAudio() {
        if (mediaPlayer != null)
            mediaPlayer.stop();

        int audioFile = getContext().getResources().getIdentifier(audio + "", "raw", getContext().getPackageName());

        mediaPlayer = MediaPlayer.create(this, audioFile);
        mediaPlayer.start();
    }

    //getter
    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!audio.equals("") && audio != null)
            playAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }

}
