package be.thomasmore.project1_app5v1;

import android.content.Intent;
import android.os.Bundle;
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

public class ListKlassen extends Fragment {

    private DatabaseHelper dataBaseHelper;
    private List<Klas> listKlassen;

    public static Klas clickedKlas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listklassen, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listKlassenView);
        listKlassen = new ArrayList<>();
        dataBaseHelper = new DatabaseHelper(getContext());
        AdapterView.OnItemClickListener clickListener = null;


        listKlassen = dataBaseHelper.leesAlleKlassen();
        ArrayAdapter<Klas> adapter = new ArrayAdapter<Klas>(getContext(), android.R.layout.simple_list_item_1, listKlassen);


        clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), "Klas met id " + listKlassen.get(position).getId(),
                        Toast.LENGTH_LONG).show();

                ListLeerlingen listLeerlingenFragment = (ListLeerlingen)
                        getFragmentManager().findFragmentById(R.id.listLeerlingen);

                listLeerlingenFragment.VulLijst(listKlassen.get(position).getId());

            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickListener);

        return view;


    }
}
