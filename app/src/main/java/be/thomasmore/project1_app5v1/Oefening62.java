package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class Oefening62 extends AppCompatActivity {

    private static Context mContext;
    private Leerling leerling = new Leerling();

    private String woord;

    private MediaPlayer mediaPlayer;

    private HashMap<String, Integer> aantalFouten = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening62);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        aantalFouten = (HashMap<String , Integer>) getIntent().getSerializableExtra("map");

        //seettext
        TextView textView = (TextView) findViewById(R.id.oef62titel);
        textView.setText(woord);

        //set picture
        int resID = getResources().getIdentifier(woord, "drawable", getContext().getPackageName());
        ImageView imageView = (ImageView)findViewById(R.id.oef62_doelwoord);
        imageView.setImageResource(resID);

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
            }
        });

        //replay sound
        findViewById(R.id.replaySound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });


        playSound();

    }

    public void playSound() {
        if (mediaPlayer != null )
            mediaPlayer.release();

        //gepaste audio afspelen
        int resRawId = getResources().getIdentifier("oef62_" + woord, "raw", getContext().getPackageName()); // oef61_woord --> audio geluid
        mediaPlayer = MediaPlayer.create(getContext(), resRawId);

        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    private void nextActivity() {

        Groep groep = new DatabaseHelper(getContext()).getGroep(leerling.getGroepID());

        Conditie con2 = new DatabaseHelper(getContext()).getConditie(groep.getConditie_id2());
        Conditie con3 = new DatabaseHelper(getContext()).getConditie(groep.getConditie_id3());

        if (woord.equals(con2.getWoord1()) || woord.equals(con2.getWoord2()) || woord.equals(con2.getWoord3())) {
            // woord zit in coditie 2
            if (woord.equals(con2.getWoord1())) {
                woord = con2.getWoord2();
            } else if (woord.equals(con2.getWoord2())) {
                woord = con2.getWoord3();
            } else {
                woord = con3.getWoord1();
            }
        }


        //TODO intent declareren naar next activity
        Intent intent = new Intent(getContext(), Oefening1.class);

        intent.putExtra("leerling", leerling);
        intent.putExtra("woord", woord);
        intent.putExtra("map", aantalFouten);

        startActivity(intent);
    }

    public static Context getContext() {
        return mContext;
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null )
            mediaPlayer.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null)
            playSound();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mediaPlayer.release();
        mediaPlayer = null;
    }
}
