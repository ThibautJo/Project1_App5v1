package be.thomasmore.project1_app5v1;

import android.content.Context;
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

import org.w3c.dom.Text;

import java.util.List;

public class Oefening3 extends AppCompatActivity {

    private static Context mContext;
    private String woord;

    private MediaPlayer audioPlayer = new MediaPlayer();
    private MediaPlayer mediaPlayer2 = new MediaPlayer();

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

        mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // aangepaste string speelt af
                int audioFile = getContext().getResources().getIdentifier("oef3_duikbril_zin2", "raw", getContext().getPackageName());
                mediaPlayer.create(getContext(), audioFile);

                mediaPlayer.start();
            }
        });

        // groene knop
        imageViewJuist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // nieuwe audio file creeren + nieuwe onlistener


                if (woordUitbreiding.getStartValue() == 1){ // eerste zin = juist

                    // deze media player gebruiken om direct 2de zin af te laten spelen
                    int audioFile = getContext().getResources().getIdentifier("oef3_volgende_zin", "raw", getContext().getPackageName());

                    mediaPlayer2.create(getContext(), audioFile);
                    mediaPlayer2.start();

                    //string aanpassen van geluid dat afgespeeld moet worden

                }
                else{
                    // fout probeer nog eens geluid afspelen
                    playAudio("oef3_fout_1", mediaPlayer2); //hardcoded
                    // juiste zin terug afspelen
                    // --> eventueel string aanpassen van geluid dat moet afgespeeld worden
                }

            }
        });

        // rode knop
        imageViewFout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // fout probeer nog eens geluid afspelen

            }
        });


        // executions

        // geluid afspelen
        playAudio("oef3_duikbril_zin1", audioPlayer);// hardcoded



    }

    public void playAudio(String audio, MediaPlayer player){

        // views
        final ImageView imageViewJuist = (ImageView) findViewById(R.id.oef3Juist);
        final ImageView imageViewFout = (ImageView) findViewById(R.id.oef3Fout);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                // Image click enablen
                imageViewJuist.setClickable(true);
                imageViewFout.setClickable(true);
            }
        });
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // Image click Disablen als geluid speelt
                imageViewJuist.setClickable(false);
                imageViewFout.setClickable(false);
            }
        });

         int audioFile = getContext().getResources().getIdentifier(audio+"", "raw", getContext().getPackageName());

        player.create(this, audioFile);
        player.start();
    }

    //getter
    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // titel text opvullen
        TextView textView = (TextView) findViewById(R.id.oef3WoordUitleg);

        // Juist of foute zin laten zien a.d.h.v. startValue
        if (woordUitbreiding.getStartValue() == 1){ // juiste Zin éérst
            textView.setText(woordUitbreiding.getContextZinJuist());
        }
        else { // foute zin éérst
            textView.setText(woordUitbreiding.getContextZinFout());
        }


        // Image click disablen --> pas enable na geluid afgespeeld is
        ImageView imageViewJuist = (ImageView) findViewById(R.id.oef3Juist);
        ImageView imageViewFout = (ImageView) findViewById(R.id.oef3Fout);
        imageViewJuist.setClickable(false);
        imageViewFout.setClickable(false);


    }

    @Override
    protected void onPause() {
        super.onPause();
        audioPlayer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!audioPlayer.isPlaying())
        {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        audioPlayer.stop();
    }

}
