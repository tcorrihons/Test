package hins.clay.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.main);

        Drawable d = getResources().getDrawable(R.drawable.test);
        Bitmap bmp = ((BitmapDrawable) d).getBitmap();

        ImageView image1 = (ImageView) findViewById(R.id.image1);
        image1.setImageBitmap(bmp);

        int width = bmp.getWidth();
        int height = bmp.getHeight();

        int src[] = new int[width * height];
        int dst[] = new int[width * height];

        bmp.getPixels(src, 0, width, 0, 0, width, height);

        nativeFilter(src, dst, width, height);

        Bitmap resultBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        resultBmp.setPixels(dst, 0, width, 0, 0, width, height);

        ImageView image2 = (ImageView) findViewById(R.id.image2);
        image2.setImageBitmap(resultBmp);
    }

    static {
        System.loadLibrary("filter");
    }

    private static native void nativeFilter(int src[], int dst[], int width, int height);
}
