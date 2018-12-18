package be.thomasmore.project1_app5v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListLeerlingen extends Fragment {

    private DatabaseHelper dataBaseHelper;
    private List<Leerling> listLeerlingen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listleerlingen, container, false);


        return view;
    }

    public void VulLijst(long klasid) {

        ListView listView = (ListView) getView().findViewById(R.id.listLeerlingenView);
        listLeerlingen = new ArrayList<>();
        dataBaseHelper = new DatabaseHelper(getContext());
        AdapterView.OnItemClickListener clickListener = null;

        //invullen
        listLeerlingen = dataBaseHelper.leesAlleLeerlingenByKlasId(klasid);

        ArrayAdapter<Leerling> adapter = new ArrayAdapter<Leerling>(getContext(), android.R.layout.simple_list_item_1, listLeerlingen);

        clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), "Leerling met id " + listLeerlingen.get(position).getId(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getContext(), voormeting.class);
                Leerling leerling = dataBaseHelper.getLeerling(Integer.parseInt(listLeerlingen.get(position).getId().toString()));

                intent.putExtra("leerling", leerling);
                startActivity(intent);
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickListener);
    }
}
