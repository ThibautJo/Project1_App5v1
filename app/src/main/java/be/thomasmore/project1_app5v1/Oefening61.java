package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.HashMap;

public class Oefening61 extends AppCompatActivity {

    private static Context mContext;
    private Leerling leerling = new Leerling();

    private String woord;

    private HashMap<String, Integer> aantalFouten = new HashMap<>();

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening61);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        aantalFouten = (HashMap<String, Integer>) getIntent().getSerializableExtra("map");
        if (aantalFouten.containsKey("oefening61")) {
            aantalFouten.put("oefening61", aantalFouten.get("oefening61"));
        } else {
            aantalFouten.put("oefening61", 0);
        }

        //set titel
        TextView textView = (TextView) findViewById(R.id.oef61Titel);
        textView.setText(woord);

        int resId = getResources().getIdentifier(woord, "drawable", getContext().getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.oef61DoelwoordAfbeelding);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        imageView.setImageBitmap(bitmap);

        ImageView imageViewSad = (ImageView) findViewById(R.id.sad);
        imageViewSad.setTag("sad");
        ImageView imageViewHappy = (ImageView) findViewById(R.id.happy);
        imageViewHappy.setTag("happy");

        imageViewSad.setOnClickListener(new MyOnclickListener());
        imageViewHappy.setOnClickListener(new MyOnclickListener());

        playSound();


    }

    public void playSound() {
        if (mediaPlayer != null )
            mediaPlayer.release();

        int resRawId = getResources().getIdentifier("oef61_" + woord, "raw", getContext().getPackageName()); // oef61_woord --> audio geluid
        mediaPlayer = MediaPlayer.create(getContext(), resRawId);

        mediaPlayer.start();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    public class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (view.getTag().equals("sad")) {
                //sad face geklikt
                if (!woord.equals("duikbril")) {
                    aantalFouten.put("oefening61", aantalFouten.get("oefening61")); // 0 = sad face
                }

            } else {
                //happy face geklikt
                if (!woord.equals("duikbril")) {
                    aantalFouten.put("oefening61", aantalFouten.get("oefening61") + 1); // aantal keren happy face
                }
            }

            nextActivity();

        }
    }

    private void nextActivity() {

        Groep groep = new DatabaseHelper(getContext()).getGroep(leerling.groepId);
        Conditie con1 = new DatabaseHelper(getContext()).getConditie(groep.getConditie_id1());
        Conditie con2 = new DatabaseHelper(getContext()).getConditie(groep.getConditie_id2());

        //welke conditie zit men?
        if (woord.equals("duikbril")) {
            woord = con1.getWoord1();
        } else {
            if (woord.equals(con1.getWoord1()) || woord.equals(con1.getWoord2()) || woord.equals(con1.getWoord3())) {
                // woord zit in conditie1
                if (woord.equals(con1.getWoord1())) {
                    woord = con1.getWoord2();
                } else if (woord.equals(con1.getWoord2())) {
                    woord = con1.getWoord3();
                } else {
                    // éérste woord van conditie 2 beginnen
                    woord = con2.getWoord1();
                }
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
