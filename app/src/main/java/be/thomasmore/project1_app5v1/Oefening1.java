package be.thomasmore.project1_app5v1;

import android.app.Application;
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
import android.widget.Toast;

import java.util.HashMap;


public class Oefening1 extends AppCompatActivity {

    private static Context mContext;

    public Groep groep = new Groep();
    public Conditie conditie = new Conditie();

    public final DatabaseHelper db = new DatabaseHelper(this);
    private HashMap<String, Integer> aantalFouten = new HashMap<>();
    private MediaPlayer mediaPlayer;
    private Leerling leerling = new Leerling();
    private String woord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        aantalFouten = (HashMap<String, Integer>) getIntent().getSerializableExtra("map");

        toolbar.setTitle(leerling.getNaam() + " " +leerling.getVoornaam());

        ImageView imageView = (ImageView) findViewById(R.id.oef1Foto);
        TextView textView = (TextView) findViewById(R.id.oef1Titel);
        Button btnNext = (Button) findViewById(R.id.btnNext);


        try {

            int id = getResources().getIdentifier(woord, "drawable", getContext().getPackageName());

            imageView.setImageResource(id);
            textView.setText(woord);

            //afspelen audio
            playAudio();


        } catch (Exception e) {
            Toast.makeText(getContext(), "error bij laden", Toast.LENGTH_SHORT).show();
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Oefening2.class);
                intent.putExtra("woord", woord);
                intent.putExtra("leerling", leerling);
                intent.putExtra("map", aantalFouten);


                startActivity(intent);
            }
        });


    }

    public void playAudio(){
        if (mediaPlayer != null )
            mediaPlayer.release();

        int audioFile = getApplicationContext().getResources().getIdentifier("oef1_" + woord, "raw", getApplicationContext().getPackageName());
        mediaPlayer = MediaPlayer.create(this, audioFile);

        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    public static Context getContext(){
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
            playAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();

            mediaPlayer.release();
            mediaPlayer = null;
    }

}
