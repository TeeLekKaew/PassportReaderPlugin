package th.co.bbtec.cordova.plugin;

import com.google.gson.Gson;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class PassportReader extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            JSONObject obj = args.getJSONObject(0);// .getString(0);
            Dog dog = new Dog(obj.getString("name"), obj.getInt("age"));


            this.coolMethod(dog, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(Dog dog, CallbackContext callbackContext) {
        if (dog != null) {
            Gson gson = new Gson();

            String stringDog = gson.toJson(dog);

            callbackContext.success(stringDog);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
