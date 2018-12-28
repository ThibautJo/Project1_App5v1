package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ImageView;

import java.security.PublicKey;

public class Oefening61 extends AppCompatActivity {

    private static Context mContext;
    private Leerling leerling = new Leerling();

    private String woord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening61);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();

        // woord + leerling ophalen en ophalen van correlatie van het woord
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");

        int resId = getResources().getIdentifier(woord, "drawable", getContext().getPackageName());
        ImageView imageView = findViewById(R.id.oef61DoelwoordAfbeelding);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        imageView.setImageBitmap(bitmap);

        ImageView imageViewSad = findViewById(R.id.sad);
        imageViewSad.setTag("sad");
        ImageView imageViewHappy = findViewById(R.id.happy);
        imageViewHappy.setTag("happy");

        imageViewSad.setOnClickListener(new MyOnclickListener());
        imageViewHappy.setOnClickListener(new MyOnclickListener());


        //TODO gepaste audio afspelen
        int resRawId = getResources().getIdentifier("oef61_"+woord, "raw", getContext().getPackageName()); // oef61_woord --> audio geluid
        MediaPlayer mediaPlayer;

    }

    public class MyOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            if (view.getTag().equals("sad")){
                // sad face geklikt

            }
            else {
                // happy face geklikt

            }

        }
    }

    public static Context getContext(){
        return mContext;
    }

}
