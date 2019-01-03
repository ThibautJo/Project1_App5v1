package be.thomasmore.project1_app5v1;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.DragEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.MotionEvent;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Oefening5 extends AppCompatActivity {

    private static Context mContext;
    private Leerling leerling = new Leerling();
    private GestureDetector gestureDetector;

    private String woord;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    private String[] bitmapsString = new String[]{"juist", "juist", "juist", "fout"};

    private HashMap<String, Integer> aantalFouten = new HashMap<>();
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");
        aantalFouten = (HashMap<String , Integer>) getIntent().getSerializableExtra("map");
        aantalFouten.put("oefening5", 0);
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        //images ophalen en opvullen
        for (int i = 1; i < 4; i++) {
            int resId = getResources().getIdentifier("oef5_" + woord + "_" + i, "drawable", getContext().getPackageName());
            bitmaps.add(BitmapFactory.decodeResource(getResources(), resId));
            if (i == 3) {
                //het foute woord er ook inzetten
                resId = getResources().getIdentifier("oef5_" + woord + "_fout", "drawable", getContext().getPackageName());
                bitmaps.add(BitmapFactory.decodeResource(getResources(), resId));
            }
        }

        //bitmap array shuffelen --> zodat fout niet steeds zelfde plek
        shuffleBitmaps(bitmaps);


        //alle images setten naar bitmap
        //images ophalen en opvullen
        int i = 1;
        for (Bitmap map : bitmaps) {

            int viewID = getResources().getIdentifier("picture" + i, "id", getContext().getPackageName());
            ImageView imageView = findViewById(viewID);
            imageView.setTag(bitmapsString[i - 1]);
            imageView.setImageBitmap(map);

            i++;
        }

        //set image listeners onTouch
        findViewById(R.id.picture1).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.picture2).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.picture3).setOnTouchListener(new MyTouchListener());
        findViewById(R.id.picture4).setOnTouchListener(new MyTouchListener());

        //set listeners on layout
        findViewById(R.id.groepFotos).setOnDragListener(new MyDragListener());

        findViewById(R.id.groepFout).setOnDragListener(new MyDragListener());
        GradientDrawable gradientDrawable = (GradientDrawable) findViewById(R.id.groepFout).getBackground();
        gradientDrawable.setStroke(2, Color.RED);

        findViewById(R.id.groepJuist).setOnDragListener(new MyDragListener());
        gradientDrawable = (GradientDrawable) findViewById(R.id.groepJuist).getBackground();
        gradientDrawable.setStroke(2, Color.GREEN);

        //geluid afspelen
        playSound();


    }

    public void playSound() {
        if (mediaPlayer != null)
            mediaPlayer.stop();

        //gepaste audio afspelen
        int resRawId = getResources().getIdentifier("oef5_" + woord, "raw", getContext().getPackageName());
        mediaPlayer = MediaPlayer.create(getContext(), resRawId);

        mediaPlayer.start();
    }

    public void shuffleBitmaps(ArrayList<Bitmap> bitmaps) {

        //shuffelen --> voorbeeld oef 4

        // shuffelen van bitmaps array + bitmapString
        Random rnd = new Random();
        for (int i = bitmaps.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Bitmap a = bitmaps.get(index);
            bitmaps.set(index, bitmaps.get(i));
            bitmaps.set(i, a);

            String b = bitmapsString[index];
            bitmapsString[index] = bitmapsString[i];
            bitmapsString[i] = b;
        }

    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (gestureDetector.onTouchEvent(motionEvent)) {
                // when the user just clicks the image --> terugzetten
                Toast.makeText(getContext(), "Geklikt", Toast.LENGTH_SHORT).show();

            } else {
                // when the user drags the image
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                        view.startDrag(data, shadowBuilder, view, 0);
                        view.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        break;
                }
            }


            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = (event == null) ? 3432 : event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();

                    int LinearViewID = v.getId();
                    Boolean check = true;
                    Toast.makeText(getContext(), LinearViewID + " " + R.id.groepJuist, Toast.LENGTH_SHORT).show();

                    if (LinearViewID == R.id.groepJuist && ((LinearLayout) findViewById(LinearViewID)).getChildCount() == 3) {
                        Toast.makeText(getContext(), "groepjuist zit vol", Toast.LENGTH_SHORT).show();
                        check = false;
                    }
                    if (LinearViewID == R.id.groepFout && ((LinearLayout) findViewById(LinearViewID)).getChildCount() == 1) {
                        Toast.makeText(getContext(), "groepfout zit vol", Toast.LENGTH_SHORT).show();
                        check = false;
                    }

                    if (check) {
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                    }

                    view.setVisibility(View.VISIBLE);


                    //checken of alle linearviews goed gevuld zijn
                    if (((LinearLayout) findViewById(R.id.groepJuist)).getChildCount() == 3 && ((LinearLayout) findViewById(R.id.groepFout)).getChildCount() == 1) {
                        if (((LinearLayout) findViewById(R.id.groepFout)).getChildAt(0).getTag().equals("fout")) {

                            if (mediaPlayer != null)
                                mediaPlayer.stop();

                            //gepaste audio afspelen
                            int resRawId = getResources().getIdentifier("oef5_goed", "raw", getContext().getPackageName());
                            mediaPlayer = MediaPlayer.create(getContext(), resRawId);
                            mediaPlayer.start();

                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    // next activity
                                    nextActivity();
                                }
                            });

                        } else {
                            //oefening recreaten
                            if (mediaPlayer != null)
                                mediaPlayer.stop();

                            //gepaste audio afspelen
                            int resRawId = getResources().getIdentifier("oef5_fout", "raw", getContext().getPackageName());
                            mediaPlayer = MediaPlayer.create(getContext(), resRawId);
                            mediaPlayer.start();

                            if (!woord.equals("duikbril")){
                                aantalFouten.put("oefening5", aantalFouten.get("oefening5")+1);
                            }
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    Oefening5.this.recreate();
                                }
                            });
                        }
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                    View view2 = (View) event.getLocalState();

                    view2.setVisibility(View.VISIBLE);

                default:
                    break;
            }
            return true;
        }
    }

    private void nextActivity() {
        Intent intent = new Intent();

        switch (leerling.getGroepID()) {
            case 0:
                intent = new Intent(getContext(), Oefening61.class);
                break;
            case 1:
                intent = new Intent(getContext(), Oefening62.class);
                break;
            case 2:
                intent = new Intent(getContext(), Oefening63.class);
                break;
            default:
                break;
        }

        intent.putExtra("leerling", leerling);
        intent.putExtra("woord", woord);
        intent.putExtra("map", aantalFouten);

        startActivity(intent);
    }

    private class SingleTapConfirm extends SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        playSound();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
}
