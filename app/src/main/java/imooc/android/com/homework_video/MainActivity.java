package imooc.android.com.homework_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final int REQUEST_FILE = 100;
    private Button mPlayBtn;
    private Button mRecordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayBtn = findViewById(R.id.bt_play);
        mRecordBtn = findViewById(R.id.bt_record);

        mPlayBtn.setOnClickListener(this);
        mRecordBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_FILE) {
            if (resultCode == RESULT_OK) {
                Intent intent = new Intent(this, VideoPlayActivity.class);
                intent.putExtras(data.getExtras());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_play:
                Intent intentPlay = new Intent(this, FileManagerActivity.class);
                startActivityForResult(intentPlay, REQUEST_FILE);
                break;
            case R.id.bt_record:
                Intent intentRecord = new Intent(this, VideoRecorderActivity.class);
                startActivity(intentRecord);
                break;
        }
    }
}
