package cn.alien95.resthttp.image.cache;

import cn.alien95.resthttp.image.callback.ImageCallback;

public class ImgRequest {

    public int inSampleSize;
    public int reqWidth;
    public int reqHeight;
    public boolean isControlWidthAndHeight = false;
    public String url;
    public ImageCallback callback;

    public ImgRequest(String url, ImageCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    public ImgRequest(String url, int inSampleSize, ImageCallback callback) {
        this.url = url;
        this.inSampleSize = inSampleSize;
        this.callback = callback;
    }

    public ImgRequest(String url, int reqWidth, int reqHeight, ImageCallback callback) {
        this.url = url;
        this.reqWidth = reqWidth;
        this.reqHeight = reqHeight;
        this.callback = callback;
        isControlWidthAndHeight = true;
    }
}