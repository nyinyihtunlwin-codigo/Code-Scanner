package projects.nyinyihtunlwin.codescanner.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.codescanner.R;

/**
 * Created by Dell on 2/2/2018.
 */

public class ScanCodeActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.btn_scan_with_back_camera)
    Button btnScanWithBackCamera;

    @BindView(R.id.btn_scan_with_front_camera)
    Button btnScanWithFrontCamera;

    @BindView(R.id.tv_result)
    TextView tvResult;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ScanCodeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnScanWithBackCamera.setOnClickListener(this);
        btnScanWithFrontCamera.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_with_back_camera:
                scanBarcodeBackCamera();
                break;
            case R.id.btn_scan_with_front_camera:
                scanBarcodeFrontCamera();
                break;
        }
    }

    public void scanBarcodeBackCamera() {
        new IntentIntegrator(this)
                .setCaptureActivity(ScannerActivity.class)
                .initiateScan();
    }

    public void scanBarcodeFrontCamera() {
        new IntentIntegrator(this)
                .setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT)
                .setCaptureActivity(ScannerActivity.class)
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                tvResult.setText("");
            } else {
                tvResult.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
