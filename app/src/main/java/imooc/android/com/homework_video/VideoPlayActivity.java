package imooc.android.com.homework_video;


import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Toast;

import imooc.android.com.homework_video.VideoPlayer.OnErrorListener;

public class VideoPlayActivity extends Activity {

    private TextureView mTextureView;
    private VideoPlayer mVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        final String url = getIntent().getStringExtra(FileManagerActivity.RESULT_FILE_PATH);
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(this, "视频路径错误", Toast.LENGTH_SHORT).show();
            finish();
        }

        mTextureView = findViewById(R.id.video_play_view);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture texture, int i, int i1) {
                Log.d("IMOOC", "onSurfaceTextureAvailable");
                if (mVideoPlayer == null) {
                    mVideoPlayer = new VideoPlayer(new Surface(texture));
                    mVideoPlayer.setOnErrorListener(new OnErrorListener() {
                        @Override
                        public void onError() {
                            Toast.makeText(VideoPlayActivity.this, "视频播放失败", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    mVideoPlayer.play(url);
                } else {
                    mVideoPlayer.setSurface(new Surface(texture));
                }

            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture texture, int i, int i1) {
                Log.d("IMOOC", "onSurfaceTextureSizeChanged");
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture texture) {
                Log.d("IMOOC", "onSurfaceTextureDestroyed");
                if (mVideoPlayer != null) {
                    mVideoPlayer.setSurface(null);
                    return true;
                }
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture texture) {
                Log.d("IMOOC", "onSurfaceTextureUpdated");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("IMOOC", "onPause");
        if (mVideoPlayer != null) {
            mVideoPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("IMOOC", "onResume");
        if (mVideoPlayer != null) {
            mVideoPlayer.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("IMOOC", "onDestroy");
        if (mVideoPlayer != null) {
            mVideoPlayer.stop();
        }
    }
}
