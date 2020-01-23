package th.co.bbtec.cordova.plugin;

import com.google.gson.Gson;



import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.jmrtd.BACKey;
import org.jmrtd.BACKeySpec;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Locale;


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
//            Tag tag = passportData.getTag();

//            if (Arrays.asList(tag.getTechList()).contains("android.nfc.tech.IsoDep")) {
//            if (Arrays.asList(tag.getTechList()).contains("android.nfc.tech.IsoDep")) {
                // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                 String passportNumber = passportData.getPassportNumber();
                 String expirationDate = convertDate(passportData.getExpirationDate());
                 String birthDate = convertDate(passportData.getBirthDate());

                if (passportData.getPassportNumber() != null && !passportData.getPassportNumber().isEmpty()
                        && passportData.getExpirationDate() != null && !passportData.getExpirationDate().isEmpty()
                        && passportData.getBirthDate() != null && !passportData.getBirthDate().isEmpty()) {

                    BACKeySpec bacKey = new BACKey(passportNumber, birthDate, expirationDate);
//                     new ReadTask(IsoDep.get(tag), bacKey).execute();
                    // mainLayout.setVisibility(View.GONE);
                    // loadingLayout.setVisibility(View.VISIBLE);
                    callbackContext.success("Passport No. : " + passportData.getPassportNumber());
                } else {
                    callbackContext.error("Please provide details to read passport");
                    // Snackbar.make(passportNumberView, R.string.error_input, Snackbar.LENGTH_SHORT).show();
                }
//            }else{
//                // Log.e(TAG, "onNewIntent > NOT contain android.nfc.tech.IsoDep");
//                callbackContext.error("NOT contain android.nfc.tech.IsoDep");
//            }


        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private static String convertDate(String input) {
        if (input == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyMMdd", Locale.US)
                    .format(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(input));
        } catch (ParseException e) {
            return null;
        }
    }

//    private class ReadTask extends AsyncTask<Void, Void, Exception> {
//
//        private IsoDep isoDep;
//        private BACKeySpec bacKey;
//
//        public ReadTask(IsoDep isoDep, BACKeySpec bacKey) {
//            this.isoDep = isoDep;
//            this.bacKey = bacKey;
//        }
//
//        private COMFile comFile;
//        private SODFile sodFile;
//        private DG1File dg1File;
//        private DG2File dg2File;
//        private String imageBase64;
//        private Bitmap bitmap;
//
//        @Override
//        protected Exception doInBackground(Void... params) {
//            try {
//
//                CardService cardService = CardService.getInstance(isoDep);
//                cardService.open();
//
//                PassportService service = new PassportService(cardService);
//                service.open();
//
//                boolean paceSucceeded = false;
////                try {
//
//                CardAccessFile cardAccessFile = new CardAccessFile(service.getInputStream(PassportService.EF_CARD_ACCESS));
//
//                Collection<PACEInfo> paceInfos = cardAccessFile.getPACEInfos();
//
//                if (paceInfos != null && paceInfos.size() > 0) {
//                    PACEInfo paceInfo = paceInfos.iterator().next();
//                    service.doPACE(bacKey, paceInfo.getObjectIdentifier(), PACEInfo.toParameterSpec(paceInfo.getParameterId()));
//                    paceSucceeded = true;
//                } else {
//                    paceSucceeded = true;
//                }
////                } catch (Exception e) {
////                    Log.e(TAG, "ReadTask > Exception : " + e.getMessage());
////                }
//
//                service.sendSelectApplet(paceSucceeded);
//
//                if (!paceSucceeded) {
//                    try {
//                        service.getInputStream(PassportService.EF_COM).read();
//                    } catch (Exception e) {
//                        service.doBAC(bacKey);
//                    }
//                }
//
//                LDS lds = new LDS();
//
//                CardFileInputStream comIn = service.getInputStream(PassportService.EF_COM);
//                lds.add(PassportService.EF_COM, comIn, comIn.getLength());
//                comFile = lds.getCOMFile();
//
//                CardFileInputStream sodIn = service.getInputStream(PassportService.EF_SOD);
//                lds.add(PassportService.EF_SOD, sodIn, sodIn.getLength());
//                sodFile = lds.getSODFile();
//
//                CardFileInputStream dg1In = service.getInputStream(PassportService.EF_DG1);
//                lds.add(PassportService.EF_DG1, dg1In, dg1In.getLength());
//                dg1File = lds.getDG1File();
//
//                CardFileInputStream dg2In = service.getInputStream(PassportService.EF_DG2);
//                lds.add(PassportService.EF_DG2, dg2In, dg2In.getLength());
//                dg2File = lds.getDG2File();
//
//                List<FaceImageInfo> allFaceImageInfos = new ArrayList<>();
//                List<FaceInfo> faceInfos = dg2File.getFaceInfos();
//                for (FaceInfo faceInfo : faceInfos) {
//                    allFaceImageInfos.addAll(faceInfo.getFaceImageInfos());
//                }
//
//                if (!allFaceImageInfos.isEmpty()) {
//                    FaceImageInfo faceImageInfo = allFaceImageInfos.iterator().next();
//
//                    int imageLength = faceImageInfo.getImageLength();
//                    DataInputStream dataInputStream = new DataInputStream(faceImageInfo.getImageInputStream());
//                    byte[] buffer = new byte[imageLength];
//                    dataInputStream.readFully(buffer, 0, imageLength);
//                    InputStream inputStream = new ByteArrayInputStream(buffer, 0, imageLength);
//
//                    bitmap = ImageUtil.decodeImage(
//                            MainActivity.this, faceImageInfo.getMimeType(), inputStream);
//                    imageBase64 = Base64.encodeToString(buffer, Base64.DEFAULT);
//                }
//
//            } catch (Exception e) {
//
//                return e;
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Exception result) {
//            mainLayout.setVisibility(View.VISIBLE);
//            loadingLayout.setVisibility(View.GONE);
//
//            if (result == null) {
//
//
//                Intent intent;
//                if (getCallingActivity() != null) {
//                    intent = new Intent();
//                } else {
//                    intent = new Intent(MainActivity.this, ResultActivity.class);
//                }
//
//                MRZInfo mrzInfo = dg1File.getMRZInfo();
//
//                intent.putExtra(ResultActivity.KEY_FIRST_NAME, mrzInfo.getSecondaryIdentifier().replace("<", ""));
//                intent.putExtra(ResultActivity.KEY_LAST_NAME, mrzInfo.getPrimaryIdentifier().replace("<", ""));
//                intent.putExtra(ResultActivity.KEY_GENDER, mrzInfo.getGender().toString());
//                intent.putExtra(ResultActivity.KEY_STATE, mrzInfo.getIssuingState());
//                intent.putExtra(ResultActivity.KEY_NATIONALITY, mrzInfo.getNationality());
//
//                if (bitmap != null) {
//                    if (encodePhotoToBase64) {
//                        intent.putExtra(ResultActivity.KEY_PHOTO_BASE64, imageBase64);
//                    } else {
//                        double ratio = 320.0 / bitmap.getHeight();
//                        int targetHeight = (int) (bitmap.getHeight() * ratio);
//                        int targetWidth = (int) (bitmap.getWidth() * ratio);
//
//                        intent.putExtra(ResultActivity.KEY_PHOTO,
//                                Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false));
//                    }
//                }
//
//                if (getCallingActivity() != null) {
//                    setResult(Activity.RESULT_OK, intent);
//                    finish();
//                } else {
//                    startActivity(intent);
//                }
//
//            } else {
//
//                Snackbar.make(passportNumberView, exceptionStack(result), Snackbar.LENGTH_LONG).show();
//            }
//        }
//
//    }

    private static String exceptionStack(Throwable exception) {
        StringBuilder s = new StringBuilder();
        String exceptionMsg = exception.getMessage();
        if (exceptionMsg != null) {
            s.append(exceptionMsg);
            s.append(" - ");
        }
        s.append(exception.getClass().getSimpleName());
        StackTraceElement[] stack = exception.getStackTrace();

        if (stack.length > 0) {
            int count = 3;
            boolean first = true;
            boolean skip = false;
            String file = "";
            s.append(" (");
            for (StackTraceElement element : stack) {
                if (count > 0 && element.getClassName().startsWith("com.tananaev")) {
                    if (!first) {
                        s.append(" < ");
                    } else {
                        first = false;
                    }

                    if (skip) {
                        s.append("... < ");
                        skip = false;
                    }

                    if (file.equals(element.getFileName())) {
                        s.append("*");
                    } else {
                        file = element.getFileName();
                        s.append(file.substring(0, file.length() - 5)); // remove ".java"
                        count -= 1;
                    }
                    s.append(":").append(element.getLineNumber());
                } else {
                    skip = true;
                }
            }
            if (skip) {
                if (!first) {
                    s.append(" < ");
                }
                s.append("...");
            }
            s.append(")");
        }
        return s.toString();
    }


}

//class PassportData {
//    private Tag tag;
//    private String id;
//    private String passportNumber;
//    private String expirationDate;
//    private String birthDate;
//
//    public PassportData(String id, String passportNumber, String expirationDate, String birthDate) {
//        this.id = id;
//        this.passportNumber = passportNumber;
//        this.expirationDate = expirationDate;
//        this.birthDate = birthDate;
//    }
//
//    public Tag getTag() {
//        return tag;
//    }
//
//    public void setTag(Tag tag) {
//        this.tag = tag;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPassportNumber() {
//        return passportNumber;
//    }
//
//    public void setPassportNumber(String passportNumber) {
//        this.passportNumber = passportNumber;
//    }
//
//    public String getExpirationDate() {
//        return expirationDate;
//    }
//
//    public void setExpirationDate(String expirationDate) {
//        this.expirationDate = expirationDate;
//    }
//
//    public String getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(String birthDate) {
//        this.birthDate = birthDate;
//    }
//
//}
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

