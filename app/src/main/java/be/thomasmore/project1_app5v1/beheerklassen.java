package be.thomasmore.project1_app5v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class beheerklassen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beheerklassen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button buttonCreateKlas = (Button) findViewById(R.id.buttonCreateKlas);
        buttonCreateKlas.setOnClickListener(new OnClickListenerCreateKlas());

        countKlassen();
        leesKlassen();

    }

    public void countKlassen(){
        int klasCount = new TableControllerKlas(this).count();

        TextView tvKlasCount = (TextView) findViewById(R.id.textViewKlasCount);
        tvKlasCount.setText(klasCount + " aantal klassen gevonden.");

    }

    public void leesKlassen(){
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecordsKlas);
        linearLayoutRecords.removeAllViews();


        List<Klas> klassen = new TableControllerKlas(this).leesAlleKlassen();

        if (klassen.size() > 0) {

            for (Klas obj : klassen) {

                Long id = obj.id;
                String klasNaam = obj.naam;
                int klasJaar = obj.jaar;

                String textViewContents = klasNaam + " | Jaar: " + klasJaar;

                TextView textViewKlasItem= new TextView(this);
                textViewKlasItem.setPadding(0, 10, 0, 10);
                textViewKlasItem.setText(textViewContents);
                textViewKlasItem.setTag(Long.toString(id));
                textViewKlasItem.setOnLongClickListener(new OnLongClickListenerKlasRecord());

                linearLayoutRecords.addView(textViewKlasItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Nog geen klassen.");

            linearLayoutRecords.addView(locationItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project1__app5v1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_page:
                Intent homeintent = new Intent(this, Project1_App5v1.class);
                startActivity(homeintent);
                return true;
            case R.id.beheer_klassen:
                Intent beheerklassenintent = new Intent(this, beheerklassen.class);
                startActivity(beheerklassenintent);
                return true;
            case R.id.beheer_leerlingen:
                Intent beheerleerlingenintent = new Intent(this, beheerleerlingen.class);
                startActivity(beheerleerlingenintent);
                return true;
            default:
                return false;
        }
    }

}
