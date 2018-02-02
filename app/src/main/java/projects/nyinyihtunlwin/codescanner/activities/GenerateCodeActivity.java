package projects.nyinyihtunlwin.codescanner.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.codescanner.R;
import projects.nyinyihtunlwin.codescanner.dialogs.EncodeTypeAndSizeChooserDialog;

/**
 * Created by Dell on 2/2/2018.
 */

public class GenerateCodeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_generate_barcode)
    Button btnGenerateBarCode;

    @BindView(R.id.btn_generate_qr_code)
    Button btnGenerateQRCode;

    @BindView(R.id.et_input)
    EditText etInput;

    @BindView(R.id.iv_result_code)
    ImageView ivResultCode;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, GenerateCodeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_code);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGenerateBarCode.setOnClickListener(this);
        btnGenerateQRCode.setOnClickListener(this);

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
            case R.id.btn_generate_barcode:
                if (!etInput.getText().toString().equals("")) {
                    String str = etInput.getText().toString();
                    if (str.matches("[0-9]+")) {
                        EncodeTypeAndSizeChooserDialog dialog=new EncodeTypeAndSizeChooserDialog();
                        dialog.show(getSupportFragmentManager(),"choose");
                        //  generateBarcode(etInput.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Barcode can generate only numbers.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    etInput.setError("Enter text first!");
                }
                break;
            case R.id.btn_generate_qr_code:
                if (!etInput.getText().toString().equals("")) {
                    generateQRCode(etInput.getText().toString());

                } else {
                    etInput.setError("Enter text first!");
                }
                break;
        }
    }

    private void generateBarcode(String data) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.CODE_39, 600, 300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ivResultCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private void generateQRCode(String data) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ivResultCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}
