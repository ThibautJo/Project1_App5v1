package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Oefening4 extends AppCompatActivity implements View.OnClickListener {

    private static Context mContext;
    private Leerling leerling = new Leerling();
    private Correlatie correlatie = new Correlatie();
    private ArrayList<String> correlatieByIndex = new ArrayList<String>();

    private Map<String, Integer> map = new HashMap<String, Integer>();
    private String woord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        correlatie = new DatabaseHelper(getContext()).getCorrelatie(woord);
        // correkate in arraylist steken zodat we erdoor kunnen loopen
        correlatieByIndex = correlatie.getCorrelatieArraylist();

        //listeners
        ImageView imageView1 = findViewById(R.id.picture1);
        imageView1.setOnClickListener(this);
        ImageView imageView2 = findViewById(R.id.picture2);
        imageView2.setOnClickListener(this);
        ImageView imageView3 = findViewById(R.id.picture3);
        imageView3.setOnClickListener(this);
        ImageView imageView4 = findViewById(R.id.picture4);
        imageView4.setOnClickListener(this);

        // Getallen Array van 1-4 shuffelen
        int[] randomPictureIndex = new int[]{1,2,3,4};
        //volgorde shuffelen
        shuffleArray(randomPictureIndex);

        int i = 1;
        for (int index : randomPictureIndex){
            //de 4 fotos opvullen in map
            map.put("foto"+i, getResources().getIdentifier(correlatieByIndex.get(index-1), "drawable", getContext().getPackageName()));
            i++;
        }

        // afbeeldingen opvullen
        for (int index : randomPictureIndex) {
            int viewID = getResources().getIdentifier("picture"+index, "id", getContext().getPackageName());
            ImageView view = (ImageView) findViewById(viewID);
            view.setImageResource(map.get("foto"+index));
        }



    }

    public static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    public void onClick(View view) {
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.oef4_image_background);

        /*if (view instanceof RadioButton) {
            if (((RadioButton) view).isSelected()) {
                ((RadioButton)view).setSelected(false);
                ((RadioButton) view).setChecked(false);
            } else {
                ((RadioGroup) view.getParent()).clearCheck();
                ((RadioButton)view).setSelected(true);
                ((RadioButton) view).setChecked(true);
            }
        }*/
        if (view instanceof ImageView){
            ImageView imageView = (ImageView) view;
            GradientDrawable gradientDrawable = (GradientDrawable) imageView.getBackground();

            if (!view.getTag().equals("green")){

                gradientDrawable.setStroke(2, Color.GREEN);

                //
                //TODO toevoegen aan soort image array dat gecheckt word als er 3 afbeelding aangeduid zijn
                //


                view.setTag("green");
            }
            else {
                gradientDrawable.setStroke(2, Color.BLACK);

                //
                //TODO verwijderen van soort image array
                //

                view.setTag("black");
            }


        }

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public static Context getContext(){
        return mContext;
    }

}
