package com.notification.core.view.viewholders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.notification.core.R;
import com.notification.core.model.pojo.BaseRow;
import com.notification.core.model.pojo.SearchRow;
import com.notification.core.utils.DateTimeUtils;
import com.notification.core.utils.Utilities;

/**
 * Created by naimish on 11/12/2018
 */
public class SearchViewHolder extends BaseViewHolder<BaseRow> {

    Context context;
    private ImageView appIcon;
    private TextView appName, title, text;

    public SearchViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        appIcon = itemView.findViewById(R.id.app_icon);
        appName = itemView.findViewById(R.id.app_name);
        title = itemView.findViewById(R.id.title);
        text = itemView.findViewById(R.id.text);
    }

    @Override
    public void set(BaseRow baseRow) {
        SearchRow allSearchRow = (SearchRow) baseRow;

        appIcon.setImageDrawable(Utilities.getAppIconFromPackage(context, allSearchRow.getAppPackage()));
        appName.setText(Utilities.getAppNameFromPackage(context, allSearchRow.getAppPackage()));
        title.setText(allSearchRow.getTitle());
        text.setText(allSearchRow.getText());
    }
}
