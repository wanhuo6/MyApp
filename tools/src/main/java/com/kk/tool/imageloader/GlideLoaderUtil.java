package com.kk.tool.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kk.tool.R;

import java.io.File;

import static com.bumptech.glide.Glide.with;

/**
 * <font size="3" color="green"><b>.</b></font><p>
 * <p>
 * <font size="2" color="green"><b>返回：.</b></font><p>
 * <p>
 * <font size="1">Created on 2016-07-27.</font><p>
 * <p>
 * <font size="1">@author LuoShuiquan.</font>
 */
public class GlideLoaderUtil {

    /**
     * 加载指定宽高的图片，使用时只注意ImageView的宽高即可，底层自动获取需要加载的宽高
     *
     * @param mContext
     * @param url
     * @param defaultId
     * @param iv
     */
    public static void loadFullWidthImage(final Context mContext, String url, final int defaultId, final ImageView iv) {
        if (TextUtils.isEmpty(url)) {
            iv.setImageResource(defaultId == -1 ? R.drawable.bg_gray_shape : defaultId);
            return;
        }

        with(mContext).load(url).asBitmap().dontAnimate().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                int swidth = mContext.getResources().getDisplayMetrics().widthPixels;
                float width_rate = (float) width / swidth;
                ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
                layoutParams.height = (int) (height / width_rate);
                iv.setLayoutParams(layoutParams);
                iv.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                iv.setImageResource(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId);
            }
        });
    }

    /**
     * 加载普通图片
     *
     * @param mContext
     * @param url
     * @param defaultId 为-1时加载灰色图片
     * @param iv
     */
    public static void loadNormalImage(Context mContext, String url, int defaultId, ImageView iv) {
        loadNormalImage(mContext, url, defaultId, iv, true, null);
    }

    /**
     * 如果需要在图片加载成功之后做回调用此方法
     *
     * @param mContext
     * @param url
     * @param defaultId
     * @param iv
     * @param cache
     * @param callback
     */
    public static void loadNormalImage(Context mContext, String url, final int defaultId, final ImageView iv, boolean cache, final LoadImageCallback callback) {
        if (TextUtils.isEmpty(url)) {
            iv.setImageResource(defaultId == -1 ? R.drawable.ic_kk_default_square_small : defaultId);
            return;
        }
        with(mContext)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(defaultId == -1 ? R.drawable.ic_kk_default_square_small : defaultId)
                .error(defaultId == -1 ? R.drawable.ic_kk_default_square_small : defaultId)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        if (bitmap != null) {
                            iv.setImageBitmap(bitmap);
                            if (callback != null && iv.getDrawable() != null) {
                                callback.onBitmapLoaded(bitmap);
                            }
                        }
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        iv.setImageResource(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId);
                    }
                });
    }


    /**
     * 加载用户正常方形头像时使用该函数
     *
     * @param mContext
     * @param avatarUrl
     * @param iv
     */
    public static void loadSquareAvatar(Context mContext, String avatarUrl, int defaultId, ImageView iv) {
        if (TextUtils.isEmpty(avatarUrl)) {
            iv.setImageResource(defaultId == -1 ? R.drawable.ic_default_user_avatar : defaultId);
            return;
        }

        with(mContext)
                .load(avatarUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(defaultId == -1 ? R.drawable.ic_default_user_avatar : defaultId)
                .error(defaultId == -1 ? R.drawable.ic_default_user_avatar : defaultId)
                .into(iv);
    }


    public static void loadCenterCrop(Context mContext, String url, int defaultId, ImageView iv) {
        if (mContext == null || iv == null) return;//2016/6/6  HongyangJia
        if (TextUtils.isEmpty(url)) {
            iv.setImageResource(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId);
            return;
        }
        with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .placeholder(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId)
                .error(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId)
                .into(iv);
    }


    /**
     * 加载圆角矩形图片
     *
     * @param mContext
     * @param url
     */
    public static void loadRoundCornerImage(Context mContext, String url, int defaultId, int radiusDp, ImageView image) {

        if (TextUtils.isEmpty(url)) {
            image.setImageResource(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId);
            return;
        }

        with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new GlideRoundTransform(mContext, radiusDp))
                .placeholder(defaultId)
                .error(defaultId)
                .into(image);
    }

    public static void loadImageWithPath(Context mContext, String path, int defaultId, ImageView image) {
        with(mContext)
                .load(new File(path))
                .centerCrop()
                .thumbnail(0.1f)
                .placeholder(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId)
                .error(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId)
                .into(image);
    }

    /**
     * 加载圆形图片
     *
     * @param mContext
     * @param url
     */
    public static void loadCircleImage(Context mContext, String url, int defaultId, ImageView image, boolean isUser) {

        if (TextUtils.isEmpty(url)) {
            if (isUser) {
                image.setImageResource(R.drawable.ic_default_user_avatar);
                return;
            }
            image.setImageResource(R.drawable.ic_default_user_avatar);
            return;
        }

        with(mContext)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .transform(new CircleTransform(mContext))
                .placeholder(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId)
                .error(defaultId == -1 ? R.drawable.bg_transparent_shape : defaultId)
                .into(image);
    }




}
