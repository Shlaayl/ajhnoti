package ajh.notification.com;

import static androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale;


import android.Manifest;
import android.content.Context;


import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class ajhnoti extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if(action.equals("checkNotificationPermission")){
            JSONObject json = new JSONObject();
            json.put("checkNotificationPermission", checkNotificationPermission());
            callbackContext.success(json);
            return true;
        }else if(action.equals("requestNotificationPermission")){
            try{
                requestNotificationPermission();
                callbackContext.success("Succeed");
            } catch (Exception e) {
                e.printStackTrace();
                callbackContext.error(e.getMessage());
            }
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private  boolean checkNotificationPermission(){
        Context context=this.cordova.getActivity().getApplicationContext();
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    private  void requestNotificationPermission(){
        int PERMISSION_REQUEST_CODE =112;
        try {
                ActivityCompat.requestPermissions(this.cordova.getActivity(),
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_REQUEST_CODE);

        }catch (Exception e){
            throw e;
        }

    }
}
