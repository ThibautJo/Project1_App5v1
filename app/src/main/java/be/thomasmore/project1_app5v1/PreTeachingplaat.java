package be.thomasmore.project1_app5v1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PreTeachingplaat extends AppCompatActivity {

    private MediaPlayer audioPlayer;
    private Leerling leerling = new Leerling();
    private String woord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_teachingplaat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");

        //img settings (niet compleet)
        ImageView img = (ImageView) findViewById(R.id.klikBomen);
        img.bringToFront();

        //afspelen van woord
        playAudio();
    }


    // kind moet op juiste afbeelding klikken
    public void ImageClicked(View v) {

        // checken op tag, als het android:tag="fotoFout" is dan --> foute afbeelding

        if (v.getTag().equals("fotoJuist")) {//correct gekozen

            // iets leuk tonen (tijdelijk toast)
            Toast.makeText(this, "Juist gekozen", Toast.LENGTH_SHORT).show();

            // eventuele aantal setting instellen (leerling object aanpassen?)

            // next activity openen

            Intent intent = new Intent(this, Oefening1.class);
            intent.putExtra("leerling", leerling);
            intent.putExtra("woord", "duikbril");

            startActivity(intent);

        } else {
            Toast.makeText(this, "FOUT", Toast.LENGTH_SHORT).show();
        }

    }

    public void playAudio(){
        audioPlayer = MediaPlayer.create(this, R.raw.preteachsound);

        audioPlayer.start();
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
            playAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        audioPlayer.stop();
    }
}
