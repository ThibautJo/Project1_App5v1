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

public class Oefening62 extends AppCompatActivity {

    private static Context mContext;
    private Leerling leerling = new Leerling();

    private String woord;

    private MediaPlayer mediaPlayer;

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

        //set picture
        int resID = getResources().getIdentifier(woord, "drawable", getContext().getPackageName());
        ImageView imageView = findViewById(R.id.oef62_doelwoord);
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
        if (mediaPlayer != null)
            mediaPlayer.stop();

        //TODO gepaste audio afspelen
        int resRawId = getResources().getIdentifier("oef62_" + woord, "raw", getContext().getPackageName()); // oef61_woord --> audio geluid
        mediaPlayer = MediaPlayer.create(getContext(), resRawId);

        mediaPlayer.start();
    }

    private void nextActivity() {

        Groep groep = new DatabaseHelper(getContext()).getGroep(0);

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

        startActivity(intent);
    }

    public static Context getContext() {
        return mContext;
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
        playSound();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
}
