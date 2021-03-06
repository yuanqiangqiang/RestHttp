package cn.lemon.resthttp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.alien95.resthttp.R;
import cn.lemon.resthttp.image.HttpRequestImage;
import cn.lemon.resthttp.image.callback.ImageCallback;


/**
 * Created by linlongxin on 2015/12/31.
 */
public class HttpImageView extends ImageView {

    private int inSimpleSize;
    private int loadImageId;
    private int failedImageId;
    private String mUrl;

    public HttpImageView(Context context) {
        this(context, null, 0);
    }

    public HttpImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HttpImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.HttpImageView);
        inSimpleSize = typedArray.getInteger(R.styleable.HttpImageView_compressSize, 1);
        loadImageId = typedArray.getResourceId(R.styleable.HttpImageView_loadImage, -1);
        failedImageId = typedArray.getResourceId(R.styleable.HttpImageView_failedImage, -1);
        typedArray.recycle();
    }

    /**
     * 设置图片网络连接地址
     */
    public void setImageUrl(String url) {
        mUrl = url;
        if (loadImageId != -1) {
            setImageResource(loadImageId);
        }
        if(inSimpleSize <= 1){
            HttpRequestImage.getInstance().requestImage(url, new ImageCallback() {
                @Override
                public void callback(Bitmap bitmap) {
                    setImageBitmap(bitmap);
                }

                @Override
                public void failure() {
                    if (failedImageId != -1)
                        setImageResource(failedImageId);
                }
            });
        }else {
            HttpRequestImage.getInstance().requestImageWithCompress(url,inSimpleSize, new ImageCallback() {
                @Override
                public void callback(Bitmap bitmap) {
                    setImageBitmap(bitmap);
                }

                @Override
                public void failure() {
                    if (failedImageId != -1)
                        setImageResource(failedImageId);
                }
            });
        }

    }

    /**
     * 设置图片网络地址
     * @param inSimpleSize 压缩参数
     */
    public void setImageUrlWithCompress(String url, int inSimpleSize) {
        mUrl = url;
        if (inSimpleSize < 1) {
            throw new IllegalArgumentException("inSampleSize must greater than one");
        }
        if (loadImageId != -1) {
            setImageResource(loadImageId);
        }
        HttpRequestImage.getInstance().requestImageWithCompress(url, inSimpleSize, new ImageCallback() {
            @Override
            public void callback(Bitmap bitmap) {
                setImageBitmap(bitmap);
            }

            @Override
            public void failure() {
                if (failedImageId != -1)
                    setImageResource(failedImageId);
            }
        });
    }

    /**
     * 设置图片压缩
     */
    public void setImageUrlWithCompress(String url, int reqWidth, int reqHeight) {
        mUrl = url;
        if (loadImageId != -1) {
            setImageResource(loadImageId);
        }
        HttpRequestImage.getInstance().requestImageWithCompress(url, reqWidth, reqHeight, new ImageCallback() {
            @Override
            public void callback(Bitmap bitmap) {
                setImageBitmap(bitmap);
            }

            @Override
            public void failure() {
                if (failedImageId != -1)
                    setImageResource(failedImageId);
            }
        });
    }

    public String getUrl(){
        return mUrl;
    }

    public void setInSimpleSize(int inSimpleSize) {
        this.inSimpleSize = inSimpleSize;
    }

    public void setFailedImageId(int failedImageId) {
        this.failedImageId = failedImageId;
    }

    public void setLoadImageId(int loadImageId) {
        this.loadImageId = loadImageId;
    }
}
