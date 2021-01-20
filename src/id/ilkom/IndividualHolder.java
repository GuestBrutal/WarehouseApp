/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkom;

/**
 *
 * @author kmbrps
 */
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kmbrps
 */
public class IndividualHolder extends SistemGudang {

    StringProperty nik;
    StringProperty birthdate;

    public IndividualHolder(Integer gudangID, String nama,
            String alamat, String nik, String birthdate, ArrayList<Akun> akunn) {
        super(gudangID, nama, alamat, akunn);
        this.nik = new SimpleStringProperty(nik);
        this.birthdate = new SimpleStringProperty(birthdate);
    }

    public IndividualHolder(Integer gudangID, String nama, String alamat,
            String nik, String birthdate, Akun akun) {
        super(gudangID, nama, alamat, akun);
        this.nik = new SimpleStringProperty(nik);
        this.birthdate = new SimpleStringProperty(birthdate);
    }

    public String getNik() {
        return nik.get();
    }

    public void setNik(String nik) {
        this.nik.set(nik);
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public void setBirthdate(String birthdate) {
        this.birthdate.set(birthdate);
    }

    public StringProperty nikProperty() {
        return nik;
    }

    public StringProperty birthdateProperty() {
        return birthdate;
    }

    
}

