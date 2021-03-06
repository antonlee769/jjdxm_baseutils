package com.dou361.baseutils.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.PowerManager;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网科技控股有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/12/8
 * <p/>
 * 描 述：多媒体工具类
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class MediaUtils {

    private MediaUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param bMute 值为true时为关闭背景音乐。
     */
    @TargetApi(Build.VERSION_CODES.FROYO)
    public static boolean muteAudioFocus(boolean bMute) {
        boolean bool = false;
        AudioManager am = (AudioManager) UtilsManager.getInstance().getAppContext().getSystemService(Context.AUDIO_SERVICE);
        if (bMute) {
            int result = am.requestAudioFocus(null, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            bool = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        } else {
            int result = am.abandonAudioFocus(null);
            bool = result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
        }
        return bool;
    }

    /**
     * 获得亮屏操作对象，保持常亮调用wakeLock.acquire();恢复正常调用wakeLock.release();
     */
    public static PowerManager.WakeLock getWakeLock() {
        PowerManager pm = (PowerManager) UtilsManager.getInstance().getAppContext().getSystemService(Context.POWER_SERVICE);
        return pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
    }

}
