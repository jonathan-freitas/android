package br.com.senaigo.mobile.northwindtraders.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Danilo on 29/04/2016.
 */
public class M {
    public static byte[] ConvertPicture(Bitmap image){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,bos);
        return bos.toByteArray();
    }

    public static Bitmap ConvertBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
}
