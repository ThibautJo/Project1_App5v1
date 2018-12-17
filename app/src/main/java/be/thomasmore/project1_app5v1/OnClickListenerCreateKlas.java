package be.thomasmore.project1_app5v1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OnClickListenerCreateKlas implements View.OnClickListener {
    Context context;
    @Override
    public void onClick(View view){
        context = view.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.klas_input_form, null, false);

        final EditText editTextKlasNaam = (EditText) formElementsView.findViewById(R.id.editTextKlasNaam);
        final EditText editTextKlasJaar = (EditText) formElementsView.findViewById(R.id.editTextKlasJaar);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Maak Klas")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                String klasNaam = editTextKlasNaam.getText().toString();
                                int klasJaar = Integer.parseInt(editTextKlasJaar.getText().toString());

                                Klas newKlas = new Klas();
                                newKlas.naam = klasNaam;
                                newKlas.jaar = klasJaar;


                                long newId = new TableControllerKlas(context).insertKlas(newKlas);
                                ((beheerklassen) context).leesKlassen();
                                ((beheerklassen) context).countKlassen();

                                Toast.makeText(context, "Nieuwe klas is aangemaakt.", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }

                        }).show();
    }
}
