package core.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.azmin.skelton.R;


public class ProgressDialogUtil {
    private static ProgressDialog pd;

    public static void showProgressDialog(Activity activity, boolean is_cancelable, String msg) {
        dismissProgressDialog();

        if (activity != null) {
            ProgressDialogUtil.pd = new ProgressDialog(activity, R.style.ProgressDialogTheme);
            ProgressDialogUtil.pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            ProgressDialogUtil.pd.setCancelable(is_cancelable);
            ProgressDialogUtil.pd.show();
        }

    }

    public static void showProgressDialog(Context context, boolean is_cancelable) {
        dismissProgressDialog();
        if (context != null) {
            ProgressDialogUtil.pd = new ProgressDialog(context, R.style.ProgressDialogTheme);
            ProgressDialogUtil.pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            ProgressDialogUtil.pd.setCancelable(is_cancelable);
            ProgressDialogUtil.pd.show();
        }
    }

    public static void dismissProgressDialog() {
        if ((null != ProgressDialogUtil.pd) && ProgressDialogUtil.pd.isShowing()) {
            ProgressDialogUtil.pd.cancel();
            ProgressDialogUtil.pd = null;
        }
    }
}
