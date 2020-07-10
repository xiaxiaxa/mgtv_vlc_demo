package com.mgtv.vlc.player;

import momo.cn.edu.fjnu.androidutils.data.CommonValues;

public class ConstData {
    public static final int DB_VERSION = 1;
    public static final String DB_DIRECTORY = CommonValues.application.getFilesDir().getPath();
    public static final String DB_NAME = "vlcplayer.db";
    public interface IntentKey{
        String VIDEO_URL = "video_url";
        String INSTALL_TIME = "install_time";
    }
}
