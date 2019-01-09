package be.thomasmore.project1_app5v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

public class kiesGroep extends AppCompatActivity {

    private Leerling leerling = new Leerling();
    private HashMap<String, Integer> aantalFouten = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kies_groep);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // leerling declareren
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        aantalFouten = (HashMap<String , Integer>) getIntent().getSerializableExtra("map");


        toolbar.setTitle(leerling.getNaam() + " " +leerling.getVoornaam());

    }

    public void buttonClicked(View v){

        int groepID;

        if (v.getTag().equals("groep1")){
            groepID = 0;
        }
        else if(v.getTag().equals("groep2")){
            groepID = 1;
        }
        else { groepID = 2; }

        //leerling updaten &  next activity gaan
        leerling.setGroepID(groepID);
        Boolean check = new DatabaseHelper(getApplicationContext()).updateLeerling(leerling);

        if (check){
            Intent intent = new Intent(this, PreTeachingplaat.class);
            intent.putExtra("leerling", leerling);
            intent.putExtra("woord", "duikbril");
            intent.putExtra("map", aantalFouten);

            startActivity(intent);
        }


    }

}
