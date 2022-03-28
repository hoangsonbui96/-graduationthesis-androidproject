package son.com.prm391x_project_2_sonbhfx04379;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class ServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                Toast.makeText(context, "Đang có cuộc gọi đến", Toast.LENGTH_SHORT).show();
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }
}
