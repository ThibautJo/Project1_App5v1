package be.thomasmore.project1_app5v1;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_teachingplaat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        ImageView img = (ImageView) findViewById(R.id.klikBomen);
        img.bringToFront();

        //afspelen van woord
        MediaPlayer player = MediaPlayer.create(this, R.raw.preteachsound);

        player.start();
    }


    // kind moet op juiste afbeelding klikken
    public void ImageClicked(View v) {

        // checken op tag, als het android:tag="fotoFout" is dan --> foute afbeelding

        if (v.getTag().equals("fotoJuist")) {//correct gekozen

            // iets leuk tonen (tijdelijk toast)
            Toast.makeText(this, "Juist gekozen", Toast.LENGTH_SHORT).show();

            // eventuele aantal setting instellen (leerling object aanpassen?)

            // next activity openen

        } else {
            Toast.makeText(this, "FOUT", Toast.LENGTH_SHORT).show();
        }

    }
}
