package com.notification.core.view.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.notification.core.R;
import com.notification.core.model.pojo.BaseRow;
import com.notification.core.utils.Constants;
import com.notification.core.utils.Utilities;

/**
 * Created by anuragdalia on 26/08/18.
 */

public class TitleListAdViewHolder extends BannerAdViewHolder {

    Context context;

    public TitleListAdViewHolder(Context context, @NonNull View itemView) {
        super(context, itemView);
        this.context = context;
    }

    @Override
    protected String getAdUnitId() {
        return "";
    }
}
