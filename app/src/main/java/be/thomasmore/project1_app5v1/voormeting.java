package be.thomasmore.project1_app5v1;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class voormeting extends AppCompatActivity {

    private Leerling leerling = new Leerling();
    private MediaPlayer audioPlayer;

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

        toolbar.setTitle(leerling.getNaam() + " " +leerling.getVoornaam());


        //afspelen van woord
        playAudio();


    }

    // kind moet op juiste afbeelding klikken
    public void ImageClicked(View v) {

        // checken op tag, als het android:tag="fotoFout" is dan --> foute afbeelding

        if (!v.getTag().equals("fotoFout")) {//correct gekozen

            // iets leuk tonen (tijdelijk toast)
            Toast.makeText(this, "Juist gekozen", Toast.LENGTH_SHORT).show();

            // eventuele aantal setting instellen (leerling object aanpassen?)

            // next activity openen  + eventueel bundle setten om gegevens door te geven

            Intent intent = new Intent(this, kiesGroep.class);
            intent.putExtra("leerling", leerling);

            startActivity(intent);
        } else {
            Toast.makeText(this, "FOUT", Toast.LENGTH_SHORT).show();
        }
    }

    public void playAudio() {
        audioPlayer = MediaPlayer.create(this, R.raw.duikbril);
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
