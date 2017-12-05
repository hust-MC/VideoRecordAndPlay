package imooc.android.com.homework_video;

import android.media.MediaPlayer;
import android.view.Surface;

import java.io.IOException;

class VideoPlayer {
    private MediaPlayer mPlayer;
    private OnErrorListener mErrorListener;

    VideoPlayer(Surface surface) {
        mPlayer = new MediaPlayer();
        mPlayer.setSurface(surface);
    }

    void setSurface(Surface surface) {
        if (mPlayer != null) {
            mPlayer.setSurface(surface);
        }
    }

    void play(String url) {
        try {
            mPlayer.setDataSource(url);
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer player) {
                    mPlayer.start();
                }
            });
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer player, int i, int i1) {
                    if (mErrorListener != null) {
                        mErrorListener.onError();
                        return true;
                    }
                    return false;
                }
            });
            mPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    void resume() {
        if (mPlayer != null) {
            mPlayer.start();
        }
    }

    void setOnErrorListener(OnErrorListener listener) {
        mErrorListener = listener;
    }

    interface OnErrorListener {
        void onError();
    }
}
