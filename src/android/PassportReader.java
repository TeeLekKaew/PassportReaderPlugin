package th.co.bbtec.cordova.plugin;

import com.google.gson.Gson;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;

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
        }else if (action.equals("readPassport")) {

            JSONObject obj = args.getJSONObject(0);// .getString(0);
            PassportData passportData = new PassportData(
                obj.getString("id"),
                obj.getString("passportNumber"),
                obj.getString("expirationDate"),
                obj.getString("birthDate"));


            this.readPassport(passportData, callbackContext);
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

    private void readPassport(PassportData passportData, CallbackContext callbackContext) {
        if (passportData != null) {
            Tag tag = passportData.getTag();

            if (Arrays.asList(tag.getTechList()).contains("android.nfc.tech.IsoDep")) {
                // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                // String passportNumber = preferences.getString(KEY_PASSPORT_NUMBER, null);
                // String expirationDate = convertDate(preferences.getString(KEY_EXPIRATION_DATE, null));
                // String birthDate = convertDate(preferences.getString(KEY_BIRTH_DATE, null));
                if (passportData.getPassportNumber() != null && !passportData.getPassportNumber().isEmpty()
                        && passportData.getExpirationDate() != null && !passportData.getExpirationDate().isEmpty()
                        && passportData.getBirthDate() != null && !passportData.getBirthDate().isEmpty()) {
            
                    BACKeySpec bacKey = new BACKey(passportNumber, birthDate, expirationDate);
                    // new ReadTask(IsoDep.get(tag), bacKey).execute();
                    // mainLayout.setVisibility(View.GONE);
                    // loadingLayout.setVisibility(View.VISIBLE);
                    callbackContext.success("Passport No. : " + passportData.getPassportNumber());
                } else {
                    callbackContext.error("Please provide details to read passport");
                    // Snackbar.make(passportNumberView, R.string.error_input, Snackbar.LENGTH_SHORT).show();
                }
            }else{
                // Log.e(TAG, "onNewIntent > NOT contain android.nfc.tech.IsoDep");
                callbackContext.error("NOT contain android.nfc.tech.IsoDep");
            }

            
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }


}

class PassportData {
    private Tag tag;
    private String id;
    private String passportNumber;
    private String expirationDate;
    private String birthDate;

    public PassportData(String id, String passportNumber, String expirationDate, String birthDate) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.expirationDate = expirationDate;
        this.birthDate = birthDate;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
class Dog {
    private String name;
    private Integer age;

    public Dog(String name, Integer age) {
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

