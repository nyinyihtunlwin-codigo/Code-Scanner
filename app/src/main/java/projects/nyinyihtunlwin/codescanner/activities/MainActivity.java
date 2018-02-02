package projects.nyinyihtunlwin.codescanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.codescanner.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_scan_code)
    Button btnScanCode;

    @BindView(R.id.btn_generate_code)
    Button btnGenerateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnScanCode.setOnClickListener(this);
        btnGenerateCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_code:
                Intent intentScan = ScanCodeActivity.newIntent(getApplicationContext());
                startActivity(intentScan);
                break;
            case R.id.btn_generate_code:
                Intent intentGen = GenerateCodeActivity.newIntent(getApplicationContext());
                startActivity(intentGen);
                break;
        }
    }
}
