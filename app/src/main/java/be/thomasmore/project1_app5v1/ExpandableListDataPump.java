package be.thomasmore.project1_app5v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> klas1 = new ArrayList<String>();
        klas1.add("klaseenThomas Vansprengel");
        klas1.add("klaseenThibaut Joukes");
        klas1.add("klaseenMatthias Greif");

        List<String> klas2 = new ArrayList<String>();
        klas1.add("klastweeThomas Vansprengel");
        klas1.add("klastweeThibaut Joukes");
        klas1.add("klastweeMatthias Greif");

        List<String> klas3 = new ArrayList<String>();
        klas1.add("klasdrieThomas Vansprengel");
        klas1.add("klasdrieThibaut Joukes");
        klas1.add("klasdrieMatthias Greif");

        expandableListDetail.put("Klas 1A", klas1);
        expandableListDetail.put("Klas 2A", klas2);
        expandableListDetail.put("Klas 3A", klas3);
        return expandableListDetail;
    }
}