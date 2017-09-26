package com.michael.vincent.util;

import android.content.Context;
import android.view.View;

import com.michael.vincent.base.BaseBackActivity;


public class ActivityUtil {
    public interface OnActivityVisibilityChangedListener {
        public void onVisibilityChanged(int visibility);
    }

    public static void addOnActivityVisibilityChangedListener(View view, OnActivityVisibilityChangedListener listener) {
        final Context context = view.getContext();
        if (context != null && context instanceof BaseBackActivity) {
            final BaseBackActivity baseBackActivity = (BaseBackActivity) context;
            if (baseBackActivity != null) {
                baseBackActivity.addOnActivityVisibilityChangedListener(listener);
            }
        }
    }

    public static void removeOnActivityVisibilityChangedListener(View view, OnActivityVisibilityChangedListener listener) {
        final Context context = view.getContext();
        if (context != null && context instanceof BaseBackActivity) {
            final BaseBackActivity baseBackActivity = (BaseBackActivity) context;
            if (baseBackActivity != null) {
                baseBackActivity.removeOnActivityVisibilityChangedListener(listener);
            }
        }
    }
}
