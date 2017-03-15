package com.soft.test.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.soft.test.R;


/**
 * Desc:   Activity切换动画
 * Time:   2016-10-07 19:08
 * Author: chende
 */

public class AnimUtils {

    /**
     * 打开新页面-淡入
     *
     * @param context
     * @param clazz
     */
    public static void toFadeIn(Context context, Class clazz) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(new Intent(context, clazz));
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 打开新页面-淡入
     */
    public static void toFadeIn(Context context, Class clazz, boolean finishSelf) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(new Intent(context, clazz));
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finishSelf) {
            mActivity.finish();
        }
    }

    /**
     * 打开新页面-淡入
     */
    public static void toFadeIn(Context context, Intent intent, boolean finishSelf) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (finishSelf) {
            mActivity.finish();
        }
    }

    /**
     * 关闭页面-淡出
     *
     * @param context
     */
    public static void toFadeOut(Context context) {
        Activity mActivity = (Activity) context;
        mActivity.finish();
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 关闭页面-淡出
     */
    public static void toFadeOut(Context context, Intent intent) {
        Activity mActivity = (Activity) context;
        if (intent != null) {
            mActivity.setResult(Activity.RESULT_OK, intent);
        } else {
            mActivity.setResult(Activity.RESULT_OK);
        }
        mActivity.finish();
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 左移动画
     */
    public static void toLeftAnim(Context context, Class clazz) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(new Intent(context, clazz));
        mActivity.overridePendingTransition(R.anim.right_to_current, R.anim.curent_to_left);
    }

    /**
     * 左移动画
     */
    public static void toLeftAnim(Context context, Class clazz, boolean finishSelf) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(new Intent(context, clazz));
        mActivity.overridePendingTransition(R.anim.right_to_current, R.anim.curent_to_left);
        if (finishSelf) {
            mActivity.finish();
        }
    }

    public static void toLeftAnim(Context context, Intent intent) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.right_to_current, R.anim.curent_to_left);
    }

    /**
     * 左移动画
     */
    public static void toLeftAnim(Context context, Intent intent, boolean finishSelf) {
        Activity mActivity = (Activity) context;
        mActivity.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.right_to_current, R.anim.curent_to_left);
        if (finishSelf) {
            mActivity.finish();
        }
    }

    /**
     * 左移动画
     */
    public static void toLeftAnimForResult(Context context, Intent intent, int requestCode) {
        Activity mActivity = (Activity) context;
        mActivity.startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.right_to_current, R.anim.curent_to_left);
    }

    /**
     * 右移ForResult
     */
    public static void toRightForResult(Context context, Intent intent) {
        Activity mActivity = (Activity) context;
        if (intent != null) {
            mActivity.setResult(Activity.RESULT_OK, intent);
        } else {
            mActivity.setResult(Activity.RESULT_OK);
        }
        mActivity.finish();
        mActivity.overridePendingTransition(R.anim.left_to_current, R.anim.curent_to_right);
    }

    /**
     * 右移动画
     */
    public static void toRightAnim(Context context) {
        Activity mActivity = (Activity) context;
        mActivity.finish();
        mActivity.overridePendingTransition(R.anim.left_to_current, R.anim.curent_to_right);
    }

    public static void shakeAnim(View view) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(4));
        translateAnimation.setDuration(400);
        view.startAnimation(translateAnimation);
    }
}
