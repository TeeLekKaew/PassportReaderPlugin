package th.co.bbtec.cordova.plugin;

import android.nfc.Tag;

public class PassportData {
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
