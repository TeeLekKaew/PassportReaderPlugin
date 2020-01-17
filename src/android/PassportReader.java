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
            Dog1 dog = new Dog1(obj.getString("name"), obj.getInt("age"));


            this.coolMethod(dog, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(Dog1 dog, CallbackContext callbackContext) {
        if (dog != null) {
            Gson gson = new Gson();

            String stringDog = gson.toJson(dog.toString(), Dog.class);

            callbackContext.success(stringDog);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}

class Dog1 {
    private String name;
    private Integer age;

    public Dog1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
