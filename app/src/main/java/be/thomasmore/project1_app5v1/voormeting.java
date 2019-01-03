package be.thomasmore.project1_app5v1;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class voormeting extends AppCompatActivity {

    private String[] voormetingWoorden = new String[]{"duikbril", "klimtouw", "kroos", "riet", "val", "kompas", "steil", "zwaan", "kamp", "zaklamp"};
    private int voormetingIndex = 0; // index 0 telt niet mee voor punten --> oefenwoord
    private String[] voormetingVolgorde = new String[]{"juist","fout","fout","fout"};
    private HashMap<String, Integer> aantalFouten = new HashMap<>();

    private static Context mContext;
    private Leerling leerling = new Leerling();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voormeting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // initialiseren voor men toolbar setsupportaction doet --> anders kan men dit later niet meer veranderen
        toolbar.setTitle(""); // als dit niet gezet is pakt men de standaard text in string xml file
        setSupportActionBar(toolbar);

        // leerling declareren
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        mContext = getApplicationContext();
        aantalFouten.put("voormeting", 0);

        toolbar.setTitle(leerling.getNaam() + " " +leerling.getVoornaam());

        //afbeeldingen opvullen
        afbeeldingenOpvullen();


        //Listeners
        ((ImageView) findViewById(R.id.foto1)).setOnClickListener(new MyImageOnclickListener());
        ((ImageView) findViewById(R.id.foto2)).setOnClickListener(new MyImageOnclickListener());
        ((ImageView) findViewById(R.id.foto3)).setOnClickListener(new MyImageOnclickListener());
        ((ImageView) findViewById(R.id.foto4)).setOnClickListener(new MyImageOnclickListener());


    }

    public void afbeeldingenOpvullen(){

        //texttitel aanpassen
        TextView textView = findViewById(R.id.voormetingTitel);
        textView.setText(voormetingWoorden[voormetingIndex]);

        // afbeeldingen opvullen
        voormetingVolgorde = new String[]{"juist","fout","fout","fout"};
        ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
        for (int i = 1; i <= 4; i++){
            // éérste voormeting is de juiste
            int resID = getResources().getIdentifier("voormeting_"+voormetingWoorden[voormetingIndex]+"_"+i, "drawable", getContext().getPackageName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID);
            bitmaps.add(bitmap);
        }

        //shuffelen
        shuffleBitmaps(bitmaps);

        //images setten
        int i = 1;
        for (Bitmap bitmap : bitmaps){
            int viewID = getResources().getIdentifier("foto"+i, "id", getContext().getPackageName());
            ImageView imageView = findViewById(viewID);
            imageView.setTag(voormetingVolgorde[i-1]);
            imageView.setImageBitmap(bitmap);

            i++;
        }

        //afspelen van woord
        playAudio(voormetingWoorden[voormetingIndex]);

    }

    public class MyImageOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            // volgende woord tonen, ondanks het goed of fout is
            // fout en goed moet geregistreerd worden

            if (!view.getTag().equals("juist")){
                //puntverdeling
                if (!voormetingWoorden[voormetingIndex].equals("duikbril")){
                    aantalFouten.put("voormeting", aantalFouten.get(1)+1);
                }
            }

            if (voormetingIndex < voormetingWoorden.length-1){
                voormetingIndex += 1;
                afbeeldingenOpvullen();
            }
            else {
                activityNext();
            }

        }
    }

    // kind moet op juiste afbeelding klikken
    public void activityNext() {

        Intent intent = new Intent(this, kiesGroep.class);
        intent.putExtra("leerling", leerling);
        intent.putExtra("map", aantalFouten);

        startActivity(intent);
    }

    public void playAudio(String audioFile) {
        if (mediaPlayer != null)
            mediaPlayer.stop();

        int resID = getResources().getIdentifier(audioFile, "raw", getContext().getPackageName());
        mediaPlayer = MediaPlayer.create(this, resID);
        mediaPlayer.start();
    }

    public void shuffleBitmaps(ArrayList<Bitmap> bitmaps) {

        //TODO shuffelen --> voorbeeld oef 4

        // shuffelen van bitmaps array + bitmapString
        Random rnd = new Random();
        for (int i = bitmaps.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Bitmap a = bitmaps.get(index);
            bitmaps.set(index, bitmaps.get(i));
            bitmaps.set(i, a);

            String b = voormetingVolgorde[index];
            voormetingVolgorde[index] = voormetingVolgorde[i];
            voormetingVolgorde[i] = b;
        }

    }

    //getter
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
}
