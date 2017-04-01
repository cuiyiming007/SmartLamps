package com.glexer.smartlamp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glexer.smartlamp.R;
import com.glexer.smartlamp.zxing.CaptureActivity;

public class EditSnActivity extends AppCompatActivity {

    String snCode = "";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra(getResources().getString(R.string.intent_sn))) {
            snCode = intent.getStringExtra(getResources().getString(R.string.intent_sn));
        }
        setContentView(R.layout.activity_edit_sn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = (EditText) findViewById(R.id.seriesNumberEt);
        assert editText != null;
        editText.setText(snCode);
        editText.setSelection(snCode.length());

        Button button = (Button) findViewById(R.id.searchBtn);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snCode = editText.getText().toString();
                if (snCode.isEmpty()) {
                    Toast.makeText(EditSnActivity.this, getString(R.string.msg_sn_null_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (snCode.length() != 16) {
                    Toast.makeText(EditSnActivity.this, getString(R.string.msg_sn_length_error), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(EditSnActivity.this, AddDeviceResultActivity.class);
                intent.putExtra(getResources().getString(R.string.intent_sn), snCode);
                intent.putExtra(getResources().getString(R.string.intent_add_device_type), 2);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_sn, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_capture_sn:
                Intent intent = new Intent(EditSnActivity.this, CaptureActivity.class);
                intent.putExtra(getResources().getString(R.string.intent_title), getResources().getString(R.string.AddDevice));
                intent.putExtra(getString(R.string.intent_camera_scan_type), CaptureActivity.SCAN_TYPE_NATIVE);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (!getSupportFragmentManager().popBackStackImmediate()) {
            supportFinishAfterTransition();
        }
        return true;
    }

}
