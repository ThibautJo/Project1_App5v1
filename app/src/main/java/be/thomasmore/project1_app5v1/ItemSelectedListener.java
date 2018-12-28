package be.thomasmore.project1_app5v1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;


public class ItemSelectedListener extends OnLongClickListenerLeerlingRecord implements AdapterView.OnItemSelectedListener {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        selectedKlas = (Klas) parent.getSelectedItem();


    }

    public Klas returnSelectedKlas(){
        return selectedKlas;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
