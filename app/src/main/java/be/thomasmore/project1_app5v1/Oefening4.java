package be.thomasmore.project1_app5v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Oefening4 extends AppCompatActivity implements View.OnClickListener {

    private static Context mContext;
    private Leerling leerling = new Leerling();
    private Correlatie correlatie = new Correlatie();
    private ArrayList<String> correlatieByIndex = new ArrayList<String>();

    private Map<String, Integer> map = new HashMap<String, Integer>();
    private String woord;
    private ArrayList<String> woordenAangeduid = new ArrayList<String>();

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        correlatie = new DatabaseHelper(getContext()).getCorrelatie(woord);

        TextView textView = findViewById(R.id.oef4Titel);
        textView.setText(woord);

        // correkate in arraylist steken zodat we erdoor kunnen loopen
        correlatieByIndex = correlatie.getCorrelatieArraylist();

        //listeners
        ImageView imageView1 = findViewById(R.id.picture1);
        imageView1.setOnClickListener(this);
        ImageView imageView2 = findViewById(R.id.picture2);
        imageView2.setOnClickListener(this);
        ImageView imageView3 = findViewById(R.id.picture3);
        imageView3.setOnClickListener(this);
        ImageView imageView4 = findViewById(R.id.picture4);
        imageView4.setOnClickListener(this);

        // Getallen Array van 1-4 shuffelen
        int[] randomPictureIndex = new int[]{1,2,3,4};
        //volgorde shuffelen
        shuffleArray(randomPictureIndex);


        for (int i = 1; i <= randomPictureIndex.length; i++){
            //de 4 fotos opvullen in map
            map.put("foto"+i, getResources().getIdentifier(correlatieByIndex.get(i-1), "drawable", getContext().getPackageName()));
        }

        // afbeeldingen opvullen
        int i = 1;
        for (int index : randomPictureIndex) {
            int viewID = getResources().getIdentifier("picture"+index, "id", getContext().getPackageName());
            ImageView view = (ImageView) findViewById(viewID);
            view.setTag(R.id.oef4PictureText, correlatieByIndex.get(i-1));
            view.setImageResource(map.get("foto"+i));

            i++;
        }

        //intro geluid afspelen
        playSound();



    }

    public static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    public void onClick(View view) {

        Toast.makeText(getContext(), map.get("foto"+1).toString(), Toast.LENGTH_SHORT).show();

        if (view instanceof ImageView){
            ImageView imageView = (ImageView) view;
            GradientDrawable gradientDrawable = (GradientDrawable) imageView.getBackground();

            if (view.getTag(R.id.oef4Kleur) == null || !view.getTag(R.id.oef4Kleur).equals("green")){

                gradientDrawable.setStroke(2, Color.GREEN);
                view.setTag(R.id.oef4Kleur, "green");
                //
                //TODO toevoegen aan soort image array dat gecheckt word als er 3 afbeelding aangeduid zijn
                //
                woordenAangeduid.add(view.getTag(R.id.oef4PictureText).toString());
                if (woordenAangeduid.size() >= 3){
                    // checken als alle woorden juist zijn en doorgaan naar volgende activity...
                    if (!woordenAangeduid.contains(correlatie.getWoordFout().trim())){

                        if (mediaPlayer != null)
                            mediaPlayer.stop();

                        //gepaste audio afspelen
                        int resRawId = getResources().getIdentifier("oef4_goed", "raw", getContext().getPackageName());
                        mediaPlayer = MediaPlayer.create(getContext(), resRawId);
                        mediaPlayer.start();

                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Intent intent = new Intent(getContext(), Oefening5.class);
                                intent.putExtra("leerling", leerling);
                                intent.putExtra("woord", woord);

                                startActivity(intent);
                            }
                        });
                    }
                    else {
                        // resetten van alle woorden...
                        // activity recreaten
                        if (mediaPlayer != null)
                            mediaPlayer.stop();

                        //gepaste audio afspelen
                        int resRawId = getResources().getIdentifier("oef4_fout", "raw", getContext().getPackageName());
                        mediaPlayer = MediaPlayer.create(getContext(), resRawId);
                        mediaPlayer.start();

                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Oefening4.this.recreate();
                            }
                        });


                    }
                }

            }
            else {
                gradientDrawable.setStroke(2, Color.BLACK);
                view.setTag(R.id.oef4Kleur,"black");
                //
                //TODO verwijderen van soort image array
                //
                if (woordenAangeduid.size() > 0) {
                    String text = view.getTag(R.id.oef4PictureText).toString();
                    int i = 0;
                    for (String value : woordenAangeduid) {
                        if (text.equals(value)) {
                            //verwijderen
                            woordenAangeduid.remove(i);
                            break;
                        }
                        i++;
                    }
                }

            }


        }

    }

    public void playSound() {
        if (mediaPlayer != null)
            mediaPlayer.stop();

        //TODO gepaste audio afspelen
        int resRawId = getResources().getIdentifier("oef4_intro", "raw", getContext().getPackageName());
        mediaPlayer = MediaPlayer.create(getContext(), resRawId);

        mediaPlayer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        woordenAangeduid.clear();
        //alle images terug black achtergrond zetten
        //picture 1...4
        for (int i = 1; i < 5; i++){
            int viewID = getResources().getIdentifier("picture"+i, "id", getContext().getPackageName());
            ImageView view = (ImageView) findViewById(viewID);
            GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
            gradientDrawable.setStroke(2, Color.BLACK);
            view.setTag(R.id.oef4Kleur, "black");
        }



    }

    public static Context getContext(){
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
}
