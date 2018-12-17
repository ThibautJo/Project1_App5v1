package be.thomasmore.project1_app5v1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadImages {


    // drawable directory opslaan
    static final File dir = new File("android.resource://be.thomasmore.project1_app5v1/drawable");

    //opslaan van alle images nodig
    private static String VOORZETSEL = "";


    public ReadImages() {
        // alle images worden opgrevraagd
    }

    public String getVOORZETSEL() {
        return VOORZETSEL;
    }

    public void setVOORZETSEL(String VOORZETSEL) {
        ReadImages.VOORZETSEL = VOORZETSEL;
    }



    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "jpg"
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.startsWith(VOORZETSEL) && name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };


    public ArrayList<Bitmap> ZoekImages(String voorzetsel) {

        setVOORZETSEL(voorzetsel);

        //eerst opvullen dan private variable opvullen met setter
        ArrayList<Bitmap> images = new ArrayList<Bitmap>();

        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER) ) {

                try{
                    Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

                    images.add(myBitmap);
                }
                catch (Exception e){

                }

            }
        }

        // private var opvullen met setten
        return images;
    }











}
