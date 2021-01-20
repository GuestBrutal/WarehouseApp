/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkom;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kmbrps
 */
public class CorporateHolder extends SistemGudang{
    StringProperty contact;

    public CorporateHolder(Integer gudangID, String nama, String alamat, 
            String contact, ArrayList<Akun> akunn) {
        super(gudangID, nama, alamat, akunn);
        this.contact=new SimpleStringProperty(contact);
    }

    public CorporateHolder(Integer gudangID, String nama, String alamat, String contact, Akun akun) {
        super(gudangID, nama, alamat, akun);
        this.contact.set(contact);
    }

    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }
    public StringProperty contactProperty() {
        return contact;
    }
}
