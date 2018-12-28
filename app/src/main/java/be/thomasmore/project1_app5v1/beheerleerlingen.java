package be.thomasmore.project1_app5v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class beheerleerlingen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beheerleerlingen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonCreateLeerling = (Button) findViewById(R.id.buttonCreateLeerling);
        buttonCreateLeerling.setOnClickListener(new OnClickListenerCreateLeerling());

        countLeerlingen();
        leesLeerlingen();
    }

    public void countLeerlingen(){
        int leerlingCount = new TableControllerLeerling(this).count();

        TextView tvLeerlingCount = (TextView) findViewById(R.id.textViewLeerlingCount);
        tvLeerlingCount.setText(leerlingCount + " aantal leerlingen gevonden.");

    }

    public void leesLeerlingen(){
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecordsLeerling);
        linearLayoutRecords.removeAllViews();

        List<Leerling> leerlingen = new TableControllerLeerling(this).leesAlleLeerlingen();

        if (leerlingen.size() > 0) {

            for (Leerling obj : leerlingen) {

                Long id = obj.id;
                String leerlingVoornaam = obj.voornaam;
                String leerlingNaam = obj.naam;
                int leerlingPunten = obj.punten;
                int klasId = obj.klasId;
                int groepId = obj.groepId;

                String textViewContents = leerlingVoornaam + " " + leerlingNaam;

                TextView textViewLeerlingItem = new TextView(this);
                textViewLeerlingItem.setPadding(0, 10, 0, 10);
                textViewLeerlingItem.setText(textViewContents);
                textViewLeerlingItem.setTag(Long.toString(id));
                textViewLeerlingItem.setOnLongClickListener(new OnLongClickListenerLeerlingRecord());

                linearLayoutRecords.addView(textViewLeerlingItem);
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("Nog geen leerlingen.");

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
