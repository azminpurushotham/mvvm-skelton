package core.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.azmin.skelton.R;

public class ToastUtil {

    public static void showSuccessToast(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View layout = inflater.inflate(R.layout.toast_success_layout, null);
        ImageView image = (ImageView) layout.findViewById(R.id.toastImage);
        image.setImageResource(R.drawable.toasticon_done);
        TextView text = (TextView) layout.findViewById(R.id.toastMessage);
        text.setText(msg);

        Toast toast = new Toast(context.getApplicationContext());
        //toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    public static void showFailureToast(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_failure_layout, null);
        ImageView image = (ImageView) layout.findViewById(R.id.toastImage);
        image.setImageResource(R.drawable.toasticon_close);
        TextView text = (TextView) layout.findViewById(R.id.toastMessage);
        text.setText(msg);

        Toast toast = new Toast(context.getApplicationContext());
        //toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void showWarningToast(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_warning_layout, null);
        ImageView image = (ImageView) layout.findViewById(R.id.toastImage);
        image.setImageResource(R.drawable.toasticon_no_authority);
        TextView text = (TextView) layout.findViewById(R.id.toastMessage);
        text.setText(msg);

        Toast toast = new Toast(context.getApplicationContext());
        //toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void showInfoToast(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_info_layout, null);
        ImageView image = (ImageView) layout.findViewById(R.id.toastImage);
        image.setImageResource(R.drawable.toasticon_warning);
        TextView text = (TextView) layout.findViewById(R.id.toastMessage);
        text.setText(msg);

        Toast toast = new Toast(context.getApplicationContext());
        //toast.setGravity(Gravity.NO_GRAVITY, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}

