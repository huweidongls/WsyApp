package com.jiufang.wsyapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jiufang.wsyapp.R;

/**
 * Created by Administrator on 2019/10/24.
 */

public class GlideUtils {

    private static RequestOptions options = new RequestOptions().placeholder(R.mipmap.banner_default);

    public static void into(Context context, String url, ImageView imageView){

        Glide.with(context).load(url).apply(options).into(imageView);

    }

    public static void into(Context context, int id, ImageView imageView){

        Glide.with(context).load(id).apply(options).into(imageView);

    }

}
