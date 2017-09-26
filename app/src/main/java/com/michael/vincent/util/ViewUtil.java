
package com.michael.vincent.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class ViewUtil {

    public static boolean isActivityFinishing(View view) {
        final Context context = view.getContext();
        if (context!= null && context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (activity != null) {
                final boolean isFinishing = activity.isFinishing();
//                LLog.i(Tags.ICON_VISIBILITY, System.identityHashCode(view) + "view's activity isFinishing = " + isFinishing);
                return isFinishing;
            }
        }
        return false;
    }
}
