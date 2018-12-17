package be.thomasmore.project1_app5v1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnLongClickListenerKlasRecord implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();


        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Pas klas aan")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        if (item == 0) {
                            editRecord(Long.parseLong(id));
                        }else if (item == 1) {
                            boolean deleteSuccessful = new TableControllerKlas(context).delete(Long.parseLong(id));

                            if (deleteSuccessful){
                                Toast.makeText(context, "Klas verwijdert", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Kon klas niet verwijderen", Toast.LENGTH_SHORT).show();
                            }

                            ((beheerklassen) context).countKlassen();
                            ((beheerklassen) context).leesKlassen();

                        }

                    }
                }).show();
        return false;
    }

    public void editRecord(final long klasId) {
        final TableControllerKlas tableControllerKlas = new TableControllerKlas(context);
        Klas editKlas = tableControllerKlas.readSingleRecord(klasId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.klas_input_form, null, false);


        final EditText editKlasNaam = (EditText) formElementsView.findViewById(R.id.editTextKlasNaam);
        final EditText editKlasJaar = (EditText) formElementsView.findViewById(R.id.editTextKlasJaar);

        editKlasNaam.setText(editKlas.naam);
        editKlasJaar.setText(String.valueOf(editKlas.jaar));

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Klas editKlas = new Klas();
                                editKlas.id = klasId;
                                editKlas.naam = editKlasNaam.getText().toString();
                                editKlas.jaar = Integer.parseInt(editKlasJaar.getText().toString());

                                boolean updateSuccessful = tableControllerKlas.updateKlas(editKlas);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Klas is aangepast.", Toast.LENGTH_SHORT).show();
                                    ((beheerklassen) context).countKlassen();
                                    ((beheerklassen) context).leesKlassen();
                                }else{
                                    Toast.makeText(context, "Klas kon niet aangepast worden.", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }).show();
    }
}
