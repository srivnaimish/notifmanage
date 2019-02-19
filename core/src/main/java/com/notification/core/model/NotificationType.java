package com.notification.core.model;

import android.support.annotation.StringDef;

/**
 * Created by Naimish on 09/02/2019
 */
@StringDef({NotificationType.WEB_OPEN, NotificationType.APP_OPEN})
public @interface NotificationType {
    String WEB_OPEN = "webOpen";
    String APP_OPEN = "appOpen";
}
