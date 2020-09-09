package caskylarlcmpt276_1191e1_omega.sfu.surrey.cs.csil_git1.sudokuvocab.model;

import android.content.Context;
import android.media.MediaPlayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;

public class music {

    // play with the tag, pause / stop must with the same tag
    private static String tag;
    private static MediaPlayer mp =null;
    public static void play(Context context,int resource,int startPoint,String label){
        if (mp!=null && mp.isPlaying()){
            stop(label);
        }
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.seekTo(startPoint);
        mp.start();
        tag = label;
    }

    public static void stop(String label) {
        if(TextUtils.equals(label,tag) && mp!= null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public static int pause(String label){
        if (TextUtils.equals(label,tag) && mp != null){
            mp.pause();
            return mp.getCurrentPosition();
        }
        return 0;
    }
}