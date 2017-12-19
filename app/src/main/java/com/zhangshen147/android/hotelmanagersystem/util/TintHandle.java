package com.zhangshen147.android.hotelmanagersystem.util;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by 张申 on 2017/12/19 0019.
 */


public class TintHandle {

    /**
     *
     * @param drawable 等待被染色的图片
     * @param tint     颜色
     * @return         染色后的图片
     */
    public static Drawable tintDrawable(Drawable drawable, int tint){
        Drawable wrapedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable,tint);

        return wrapedDrawable;
    }
}
