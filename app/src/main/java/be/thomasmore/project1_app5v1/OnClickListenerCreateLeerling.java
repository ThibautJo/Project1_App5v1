package be.thomasmore.project1_app5v1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class OnClickListenerCreateLeerling implements View.OnClickListener {
    Context context;
    Spinner spinner;
    public Klas selectedKlas = new Klas();

    @Override
    public void onClick(View view){
        context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.leerling_input_form, null, false);

        final EditText editTextLeerlingNaam = (EditText) formElementsView.findViewById(R.id.editTextLeerlingNaam);
        final EditText editTextLeerlingVoornaam = (EditText) formElementsView.findViewById(R.id.editTextLeerlingVoornaam);


        spinner = (Spinner) formElementsView.findViewById(R.id.editTextLeerlingKlas);
        loadSpinnerData();
        final ItemSelectedListener itemSelectedListener = new ItemSelectedListener();
        spinner.setOnItemSelectedListener(itemSelectedListener);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Maak leerling")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            public void onClick(DialogInterface dialog, int id) {

                                String leerlingNaam = editTextLeerlingNaam.getText().toString();
                                String leerlingVoornaam = editTextLeerlingVoornaam.getText().toString();
                                int leerlingKlasId = Math.toIntExact(itemSelectedListener.returnSelectedKlas().getId());

                                Leerling newLeerling = new Leerling();
                                newLeerling.voornaam = leerlingVoornaam;
                                newLeerling.naam = leerlingNaam;
                                newLeerling.punten = 0;
                                newLeerling.klasId = leerlingKlasId;
                                newLeerling.groepId = 0;


                                long newId = new TableControllerLeerling(context).insertLeerling(newLeerling);
                                ((beheerleerlingen) context).leesLeerlingen();
                                ((beheerleerlingen) context).countLeerlingen();

                                Toast.makeText(context, "Nieuwe leerling is aangemaakt.", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }

                        }).show();
    }

    private void loadSpinnerData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.context);
        List<Klas> klassen = databaseHelper.leesAlleKlassen();



        // Creating adapter for spinner
        ArrayAdapter<Klas> dataAdapter = new ArrayAdapter<Klas>(this.context, android.R.layout.simple_spinner_item, klassen);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        dataAdapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this.context));
    }
}
