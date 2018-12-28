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

import java.util.ArrayList;
import java.util.List;

public class OnLongClickListenerLeerlingRecord implements View.OnLongClickListener {

    Context context;
    String id;
    Spinner spinner;
    public Klas selectedKlas;


    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();


        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Pas leerling aan")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        if (item == 0) {
                            editRecord(Long.parseLong(id));
                        }else if (item == 1) {
                            boolean deleteSuccessful = new TableControllerLeerling(context).delete(Long.parseLong(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Leerling verwijderd", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Kon leerling niet verwijderen", Toast.LENGTH_SHORT).show();
                            }

                            ((beheerleerlingen) context).countLeerlingen();
                            ((beheerleerlingen) context).leesLeerlingen();

                        }

                    }
                }).show();
        return false;
    }
    /**
     * Function to load the spinner data from SQLite database
     * */
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


    public void editRecord(final long leerlingId) {
        final TableControllerLeerling tableControllerLeerling = new TableControllerLeerling(context);
        Leerling editLeerling = tableControllerLeerling.readSingleRecord(leerlingId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.leerling_input_form, null, false);


        final EditText editLeerlingNaam = (EditText) formElementsView.findViewById(R.id.editTextLeerlingNaam);
        final EditText editLeerlingVoornaam = (EditText) formElementsView.findViewById(R.id.editTextLeerlingVoornaam);

        spinner = (Spinner) formElementsView.findViewById(R.id.editTextLeerlingKlas);
        loadSpinnerData();
        spinner.setOnItemSelectedListener(new ItemSelectedListener());



        spinner.setSelection(editLeerling.klasId);
        editLeerlingNaam.setText(editLeerling.naam);
        editLeerlingVoornaam.setText(editLeerling.voornaam);
        final ItemSelectedListener itemSelectedListener = new ItemSelectedListener();





        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            public void onClick(DialogInterface dialog, int id) {
                                Leerling editLeerling = new Leerling();
                                editLeerling.id = leerlingId;
                                editLeerling.voornaam = editLeerlingVoornaam.getText().toString();
                                editLeerling.naam = editLeerlingNaam.getText().toString();
                                editLeerling.klasId = Math.toIntExact(itemSelectedListener.returnSelectedKlas().id);

                                boolean updateSuccessful = tableControllerLeerling.updateLeerling(editLeerling);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Leerling is aangepast.", Toast.LENGTH_SHORT).show();
                                    ((beheerleerlingen) context).countLeerlingen();
                                    ((beheerleerlingen) context).leesLeerlingen();
                                }else{
                                    Toast.makeText(context, "Leerling kon niet aangepast worden.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }).show();
    }
}
