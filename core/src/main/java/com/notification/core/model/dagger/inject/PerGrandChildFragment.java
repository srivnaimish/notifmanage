package com.notification.core.model.dagger.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by anuragdalia on 23/07/18.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerGrandChildFragment {
}
