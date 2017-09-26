package com.michael.vincent.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.michael.vincent.util.ActivityUtil;

import java.util.HashSet;

public class BaseBackActivity extends Activity {

    protected boolean finishByBase = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        LLog.i(Tags.ACTIVITY_LIFE, "----- BaseBackActivity onCreate");
//        super.onCreate(savedInstanceState);
//        initActionBar();
//        initSlide();
//        if(!checkAllPermissions()){
//            return;
//        }
//        toCheckRegionAvailable(this);
    }

//    private void toCheckRegionAvailable(final Context context) {
//        // check is region available
//        HostHelper.isRegionHostAvailable(new HostHelper.OnRegionHostAvailable() {
//            @Override
//            public void Available(boolean isAvailable) {
//                if (!isAvailable) {
//                    if(context instanceof Activity){
//                        ((Activity)context).overridePendingTransition(0, 0);
//                    }
//                    startActivity(new Intent(context, NoContentActivity.class));
//                    finishByBase();
//                }
//            }
//        });
//
//    }

//    private void finishByBase() {
//        finishByBase = true;
//        finish();
//    }

    // ==================================================================
    // if has actionBar, init title
//    private void initActionBar() {
//        final ActionBar actionBar = getActionBar();
//        if (actionBar != null) {
//            actionBar.setCustomView(R.layout.actionbar_custom);
//            final TextView textView = (TextView) actionBar.getCustomView();
//            if (textView != null) {
//                textView.setOnClickListener(new OnDoubleClickListener(this));
//            }
//        }
//    }

    // reset title resource
//    public void setActionBarTitle(int resId) {
//        setActionBarTitle(getString(resId));
//    }

    // reset title string
//    public void setActionBarTitle(String name) {
//        final ActionBar actionBar = getActionBar();
//        if (actionBar != null) {
//            View view = actionBar.getCustomView();
//            if (view != null) {
//                TextView textView = (TextView) view;
//                textView.setText(name);
//            }
//        }
//    }

    @Override
    protected void onResume() {
//        LLog.i(Tags.ACTIVITY_LIFE, "----- BaseBackActivity onResume");
//        if (!isMainEntrance()) {
//            // 提示更新数
//            notifyUserWallpaperUpdate();
//            // 清除图标上的角标
//            NotificationSubcriptUtils.clearNotificationSubscript();
//        }
//        // 清除通知栏消息
//        NoticeManager.cancelAll(this);
//        super.onResume();
//        if(!checkAllPermissions()){
//            return;
//        }
        dispatchVisibility(View.VISIBLE);
    }

//    private boolean isMainEntrance() {
//        if (this instanceof HomeActivity || this instanceof FullWallpapersActivity || this instanceof AllWallpapersInThemeActivity){
//            return true;
//        }
//        return false;
//    }

    // Dynamic permissions check
//    private boolean checkAllPermissions() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (PermissionCheckerUtil.lacksPermissions()) {
//                // if lack permissions jump to permission Activity
//                Intent intent = getIntent();
//                Class clazz = getClass();
//                PermissionActivity.startPermissionActivity(this, intent, clazz);
//                finishByBase = true;
//                finish();
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * toast提示用户壁纸更新
     * 前提:
     * 1.有壁纸跟新
     * 2．只会提示一次
     */
//    public void notifyUserWallpaperUpdate() {
//        // 判断是否已经提示过了
//        if (!PreferenceUtil.isUpdateNotified()) {
//            // 是否有跟新
//            int updateNum = NotificationSubcriptUtils.getNotificationSubscript();
//            if (updateNum > 0) {
//                    PreferenceUtil.setisUpdateNotified(true);
//                    ToastUtil.showUpdateNoticeToast(BaseBackActivity.this, updateNum);
//            }
//        }
//    }

//    public boolean isFinishByBase() {
//        return finishByBase;
//    }

//    @Override
    // on home click once
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//        }
//        return true;
//    }

    // on actionBar click double
//    public void onActionBarDoubleClick(View view) {
        // do nothing
        // maybe implement by subclass
//    }
    // ==================================================================



    // ==================================================================
    // slide back
//    private LeSlideInterface mSlideInterface;
//
//    private void initSlide() {
//        LeSlideConfig leSlideConfig = new LeSlideConfig.Builder().edge(true).build();
//        mSlideInterface = LeSlide.attach(this, leSlideConfig);
//        mSlideInterface.setEnableSlideToOpen(false);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        if (mSlideInterface != null) {
//            mSlideInterface.onRestoreInstanceState(savedInstanceState);
//        }
//    }
//
//    public void disableSlideBack() {
//        lockSlide();
//        mSlideInterface = null;
//    }
//
//    public void updateLockState(int position) {
//        if (position == 0) {
//            unlockSlide();
//        } else {
//            lockSlide();
//        }
//    }
//
//    private void lockSlide() {
//        if (mSlideInterface != null) {
//            mSlideInterface.lock();
//        }
//    }
//
//    private void unlockSlide() {
//        if (mSlideInterface != null) {
//            mSlideInterface.unlock();
//        }
//    }
//
//    private boolean isSliding() {
//        // if is sliding, pressing back hash no effect
//        if (mSlideInterface != null && mSlideInterface.isSliding()) {
//            return true;
//        }
//        return false;
//    }

    /*
    public void disableSlideBack() {
    }

    public void updateLockState(int position) {
    }
    */
    // ==================================================================



    // ==================================================================
    // if after user changing system region, the system reboot
    // maybe the snippet will be never invoked

    // onStart, check region
    // onPause, save region
//    @Override
//    protected void onStart() {
//        LLog.i(Tags.ACTIVITY_LIFE, "----- BaseBackActivity onStart");
//        WallpaperApplication.checkRegion();
//        super.onStart();
//    }

    @Override
    protected void onPause() {
//        LLog.i(Tags.ACTIVITY_LIFE, "----- BaseBackActivity onPause");
//        WallpaperApplication.saveRegion();
//        releaseReuseBitmaps();
//        super.onPause();
//        handleIsFinishing();
        dispatchVisibility(View.INVISIBLE);
    }

    /**
     * set invisible
     * so when you clearImage onPause
     * user can't see the empty layout
     */
//    private void handleIsFinishing() {
//        if (isFinishing()) {
//            // clear content view
//            LLog.i(Tags.THUMBNAIL_VISIBILITY, "onPause clear content view");
//            setVisible(false);
//        }
//    }

//    @Override
//    protected void onStop() {
//        LLog.i(Tags.ACTIVITY_LIFE, "----- BaseBackActivity onStop");
//        super.onStop();
//    }

//    @Override
//    protected void onDestroy() {
//        LLog.i(Tags.ACTIVITY_LIFE, "----- BaseBackActivity onDestroy");
//        super.onDestroy();
//    }
    // ==================================================================



    // ==================================================================
//    @Override
//    public void onBackPressed() {
//        if (isSliding()) {
//            return;
//        }
//        super.onBackPressed();
//    }


//    private void releaseReuseBitmaps() {
//        /*
//        // release all thumbnails
//        ReuseThumbnailManager.releaseAllInBitmaps();
//        // release all fullWallpapers
//        ReuseFullWallpaperManager.releaseAllInBitmaps();
//        // release all topics
//        ReuseTopicManager.releaseAllInBitmaps();
//        // release all icons
//        ReuseIconManager.releaseAllInBitmaps();
//        */
//        // release background
//        BackgroundInBitmapManager.clearBackgroundUrl();
//    }
    // ==================================================================



    // ======================================================================================
    private HashSet<ActivityUtil.OnActivityVisibilityChangedListener> onActivityVisibilityChangedListeners = new HashSet<ActivityUtil.OnActivityVisibilityChangedListener>();

    public void addOnActivityVisibilityChangedListener(ActivityUtil.OnActivityVisibilityChangedListener listener) {
        if (onActivityVisibilityChangedListeners != null) {
            onActivityVisibilityChangedListeners.add(listener);
        }
    }

    public void removeOnActivityVisibilityChangedListener(ActivityUtil.OnActivityVisibilityChangedListener listener) {
        if (onActivityVisibilityChangedListeners != null) {
            onActivityVisibilityChangedListeners.remove(listener);
        }
    }

    private void dispatchVisibility(final int visibility) {
        if (onActivityVisibilityChangedListeners != null && onActivityVisibilityChangedListeners.size() > 0) {
            for (ActivityUtil.OnActivityVisibilityChangedListener listener : onActivityVisibilityChangedListeners) {
                listener.onVisibilityChanged(visibility);
            }
        }
    }
    // ======================================================================================

}
