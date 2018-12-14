package be.thomasmore.project1_app5v1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListKlassen extends Fragment {

    private DatabaseHelper dataBaseHelper;
    private List<Klas> listKlassen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_listklassen, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listKlassenView);
        listKlassen = new ArrayList<>();
        dataBaseHelper = new DatabaseHelper(getContext());


        listKlassen = dataBaseHelper.leesAlleKlassen();
        ArrayAdapter<Klas> adapter = new ArrayAdapter<Klas>(getContext(),android.R.layout.simple_list_item_1, listKlassen);

        listView.setAdapter(adapter);

        return view;
    }
}
