package com.kompesavengers.buets.api;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by triforce on 12/04/15.
 */
public class ImageGetter {

    public static final String URL_PREFIX = "http://www.yigitozkavci.com/buets/assets/img/logos/";

    private Context context;
    private String url;
    private ImageView imageView;

    public ImageGetter(Context context)
    {
        this.context = context;
    }

    public ImageGetter setURL(String url)
    {
        this.url = url;
        return this;
    }

    public ImageGetter setImageView(ImageView view)
    {
        this.imageView = view;
        return this;
    }

    public void execute()
    {
        try {
            InputStream stream = new URL(url).openStream();
            final Bitmap bmp = BitmapFactory.decodeStream(stream);
            if(imageView != null)
            {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bmp);
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
