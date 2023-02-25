package ajh.notification.com;

import android.content.Context;

import androidx.core.app.NotificationManagerCompat;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.device.Device;
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
}
