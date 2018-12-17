package be.thomasmore.project1_app5v1;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


public class voormeting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voormeting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //afspelen van woord
        MediaPlayer player = MediaPlayer.create(this, R.raw.duikbril);

        player.start();


    }

    // kind moet op juiste afbeelding klikken
    public void ImageClicked(View v) {

        // checken op tag, als het android:tag="fotoFout" is dan --> foute afbeelding

        if (!v.getTag().equals("fotoFout")) {//correct gekozen

            // iets leuk tonen (tijdelijk toast)
            Toast.makeText(this, "Juist gekozen", Toast.LENGTH_SHORT).show();

            // eventuele aantal setting instellen (leerling object aanpassen?)

            // next activity openen  + eventueel bundle setten om gegevens door te geven

            Intent intent = new Intent(this, PreTeachingplaat.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "FOUT", Toast.LENGTH_SHORT).show();
        }

    }

}
