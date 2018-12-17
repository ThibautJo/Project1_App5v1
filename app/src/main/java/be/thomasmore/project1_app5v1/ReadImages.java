package be.thomasmore.project1_app5v1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadImages {


    // drawable directory opslaan
    static final File dir = new File("/drawable");

    //opslaan van alle images nodig
    private ArrayList<Bitmap> imgFilesPath = new ArrayList<Bitmap>();
    private static String VOORZETSEL = "";


    public ReadImages() {
        // alle images worden opgrevraagd
    }

    // @Param voorzetsel
    // voorzetsel = het voorzetsel van de image (eerste gedeelte VOOR de '_' char)
    public ReadImages(String voorzetsel) {
        setVOORZETSEL(voorzetsel);
    }

    public static String getVOORZETSEL() {
        return VOORZETSEL;
    }

    public static void setVOORZETSEL(String VOORZETSEL) {
        ReadImages.VOORZETSEL = VOORZETSEL;
    }

    public ArrayList<Bitmap> getImgFile() {
        return imgFilesPath;
    }

    public void setImgFile(ArrayList<Bitmap> imgFile) {
        this.imgFilesPath = imgFile;
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


    public void ZoekImages(String[] args) {

        //eerst opvullen dan private variable opvullen met setter
        ArrayList<Bitmap> images = new ArrayList<Bitmap>();

        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER) ) {

                if(f.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

                    images.add(myBitmap);
                }

            }
        }

        // private var opvullen met setten
        setImgFile(images);
    }











}
