package projects.nyinyihtunlwin.codescanner.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import projects.nyinyihtunlwin.codescanner.R;

/**
 * Created by Dell on 2/2/2018.
 */

public class EncodeTypeAndSizeChooserDialog extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.btn_ok)
    TextView btnOk;

    @BindView(R.id.btn_cancel)
    TextView btnCancel;

    @BindView(R.id.et_width)
    EditText etWidth;

    @BindView(R.id.et_height)
    EditText etHeight;

    @BindView(R.id.sp_encode)
    AppCompatSpinner spEncode;

    private TapListener tapListener;

    public interface TapListener {
        void onTapOk(int endcodeType, String width, String height);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_encode_type_and_size_chooser, container, false);
        ButterKnife.bind(this, view);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ok:
                int encodeType = 0;
                String width = "";
                String height = "";
                tapListener.onTapOk(encodeType, width, height);
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    public void setTapListener(TapListener tapListener) {
        this.tapListener = tapListener;
    }
}
