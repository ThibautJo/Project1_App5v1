package be.thomasmore.project1_app5v1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnClickListenerCreateLeerling implements View.OnClickListener {
    Context context;
    @Override
    public void onClick(View view){
        context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.leerling_input_form, null, false);

        final EditText editTextLeerlingNaam = (EditText) formElementsView.findViewById(R.id.editTextLeerlingNaam);
        final EditText editTextLeerlingVoornaam = (EditText) formElementsView.findViewById(R.id.editTextLeerlingVoornaam);
        //final EditText editTextLeerlingKlasid = (EditText) formElementsView.findViewById(R.id.editTextLeerlingKlasid);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Maak leerling")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String leerlingNaam = editTextLeerlingNaam.getText().toString();
                                String leerlingVoornaam = editTextLeerlingVoornaam.getText().toString();
                                //int leerlingKlasId = Integer.parseInt(editTextLeerlingKlasid.getText().toString());

                                Leerling newLeerling = new Leerling();
                                newLeerling.voornaam = leerlingVoornaam;
                                newLeerling.naam = leerlingNaam;
                                newLeerling.punten = 0;
                                //newLeerling.klasId = leerlingKlasId;
                                newLeerling.groepId = 0;


                                long newId = new TableControllerLeerling(context).insertLeerling(newLeerling);
                                ((beheerleerlingen) context).leesLeerlingen();
                                ((beheerleerlingen) context).countLeerlingen();

                                Toast.makeText(context, "Nieuwe leerling is aangemaakt.", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }

                        }).show();
    }
}
