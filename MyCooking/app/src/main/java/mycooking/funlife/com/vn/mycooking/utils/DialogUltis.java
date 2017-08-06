package mycooking.funlife.com.vn.mycooking.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import mycooking.funlife.com.vn.mycooking.R;

/**
 * Created by funlife on 6/30/17.
 */

public class DialogUltis {
    public static void showLoadingDialog(Context context,boolean check) {
       MaterialDialog mMaterialDialog = new MaterialDialog.Builder(context)
                .title(R.string.loading)
                .content(R.string.please_wait)
                .progress(true, 0)
                .progressIndeterminateStyle(true)
                .cancelable(false)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {

                    }
                })
                .build();
        if(check) {
            mMaterialDialog.show();
        }else {
            mMaterialDialog.dismiss();
        }

        mMaterialDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
            }
        });
    }
}
