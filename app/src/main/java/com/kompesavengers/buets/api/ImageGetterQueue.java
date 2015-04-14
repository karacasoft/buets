package com.kompesavengers.buets.api;

import android.os.Handler;
import android.os.HandlerThread;

import java.util.ArrayList;

/**
 * Created by Karaca on 4/14/2015.
 */
public class ImageGetterQueue {

    private Handler handler;
    private HandlerThread handlerThread;

    private ArrayList<ImageGetter> imageGetters;

    public ImageGetterQueue()
    {
        handlerThread = new HandlerThread("IMAGE_GETTER_HANDLER");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public void addImageGetter(ImageGetter imageGetter)
    {
        this.imageGetters.add(imageGetter);
    }

    public ArrayList<ImageGetter> getImageGetters() {
        return imageGetters;
    }

    //TODO create an execute method to get images and also cache them.

}
