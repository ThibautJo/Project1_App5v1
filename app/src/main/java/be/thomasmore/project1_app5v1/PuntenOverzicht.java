package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class puntenVerdeling{
    private String oefeningNaam;
    private Integer aantalFout;

    public puntenVerdeling() {
    }

    public puntenVerdeling(String oefeningNaam, Integer aantalFout) {
        this.oefeningNaam = oefeningNaam;
        this.aantalFout = aantalFout;
    }

    public String getOefeningNaam() {
        return oefeningNaam;
    }

    public void setOefeningNaam(String oefeningNaam) {
        this.oefeningNaam = oefeningNaam;
    }

    public Integer getAantalFout() {
        return aantalFout;
    }

    public void setAantalFout(Integer aantalFout) {
        this.aantalFout = aantalFout;
    }

    @Override
    public String toString() {
        return aantalFout + " fouten bij " + oefeningNaam;
    }
}

public class PuntenOverzicht extends AppCompatActivity {

    private Leerling leerling = new Leerling();

    private HashMap<String, Integer> aantalFouten = new HashMap<>();

    private List<puntenVerdeling> punten = new ArrayList<>();



    private static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punten_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        aantalFouten = (HashMap<String , Integer>) getIntent().getSerializableExtra("map");

        mContext = getApplicationContext();

        TextView textView = (TextView) findViewById(R.id.leerlingNaam);
        textView.setText(leerling.getVoornaam() + " " + leerling.getNaam());

        Iterator it = aantalFouten.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            puntenVerdeling punt = new puntenVerdeling(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()));
            punten.add(punt);
        }

        ArrayAdapter<puntenVerdeling> adapter = new ArrayAdapter<puntenVerdeling>(this, android.R.layout.simple_list_item_1, punten);

        ListView listView = (ListView) findViewById(R.id.lijstPunten);
        listView.setAdapter(adapter);


        //btn next
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Project1_App5v1.class);
                leerling = new Leerling();

                startActivity(intent);
            }
        });

    }

    //getter
    public static Context getContext() {
        return mContext;
    }


}
