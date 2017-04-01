package com.glexer.smartlamp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.glexer.smartlamp.zxing.CaptureActivity;
import com.glexer.smartlamp.eventbus.HttpEvent;
import com.glexer.smartlamp.eventbus.HttpEventType;
import com.glexer.smartlamp.volley.VolleyRequest;
import com.glexer.smartlamp.R;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class AddDeviceResultActivity extends AppCompatActivity {
    String snCode;
    int type; // type = 1,from Capture; type = 2, from EditSn
    int status = 0;

    ProgressBar loadingProgressBar;
    LinearLayout resultLayout;
    ImageView resultImg;
    TextView resultText;
    Button continueBtn;
    Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        snCode = intent.getStringExtra(getResources().getString(R.string.intent_sn));
        type = intent.getIntExtra(getResources().getString(R.string.intent_add_device_type), 1);
        setContentView(R.layout.activity_add_device_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadingProgressBar = (ProgressBar) findViewById(R.id.loading_progress);
        resultLayout = (LinearLayout) findViewById(R.id.add_device_result);
        resultImg = (ImageView) findViewById(R.id.resultPic);
        resultText = (TextView) findViewById(R.id.resultMsg);

        continueBtn = (Button) findViewById(R.id.goOnAdd);
        assert continueBtn != null;
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    Intent intent = new Intent(AddDeviceResultActivity.this, CaptureActivity.class);
                    intent.putExtra(getResources().getString(R.string.intent_title), getResources().getString(R.string.AddDevice));
                    intent.putExtra(getString(R.string.intent_camera_scan_type), CaptureActivity.SCAN_TYPE_NATIVE);
                    startActivity(intent);
                } else if (type == 2) {
                    if (status == 1) {
                        Intent intent = new Intent(AddDeviceResultActivity.this, EditSnActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(AddDeviceResultActivity.this, EditSnActivity.class);
                        intent.putExtra(getResources().getString(R.string.intent_sn), snCode);
                        startActivity(intent);
                    }
                }
            }
        });
        doneBtn = (Button) findViewById(R.id.done);
        assert doneBtn != null;
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        VolleyRequest.addDevice(snCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            supportFinishAfterTransition();
        }
        return true;
    }

    private void handleAddResult(boolean status, String message) {
        if (status) {
            resultImg.setImageResource(R.drawable.add_device_success);
            continueBtn.setText(getResources().getText(R.string.text_button_continue_add));
            doneBtn.setText(getResources().getText(R.string.text_button_complete));
        } else {
            resultImg.setImageResource(R.drawable.add_device_fail);
            continueBtn.setText(getResources().getText(R.string.text_button_re_add));
            doneBtn.setText(getResources().getText(R.string.text_button_cancel_add));
        }
        resultText.setText(message);
        loadingProgressBar.setVisibility(View.GONE);
        resultLayout.setVisibility(View.VISIBLE);
    }


    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(HttpEvent event) {
        if (event.getType() == HttpEventType.BindInternetDevice) {
            if (event.isSuccess()) {
                String data = (String) event.getData();
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(data);

                    int returncode = jsonObject.getInt("resultCode");
                    String result = jsonObject.getString("resultInfo");
                    if (returncode == 0) {
                        handleAddResult(true, result);
                    } else {
                        handleAddResult(false, result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
//                message = getString(R.string.gateway_add_request_wrong);
                handleAddResult(false, "message");
            }
        }
    }
}
