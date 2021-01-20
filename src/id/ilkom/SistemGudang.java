    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkom;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kmbrps
 */
public abstract class SistemGudang {
    private IntegerProperty gudangID;
    private StringProperty nama;
    private StringProperty alamat;
    private IntegerProperty noAkunn;
    private ArrayList<Akun> akunn;

    public SistemGudang(Integer gudangID, String nama, String alamat,
            ArrayList<Akun> akunn) {
        this.gudangID =new SimpleIntegerProperty(gudangID);
        this.nama = new SimpleStringProperty(nama);
        this.alamat =new SimpleStringProperty(alamat);
        this.akunn = akunn;
        this.noAkunn=new SimpleIntegerProperty(akunn.size());
    }
    public SistemGudang(Integer gudangID, String nama, String alamat, Akun akun) {
        akunn = new ArrayList<>();
        this.gudangID=new SimpleIntegerProperty(gudangID);
        this.nama=new SimpleStringProperty(nama);
        this.alamat=new SimpleStringProperty(alamat);
        this.akunn.add(akun);
        this.noAkunn=new SimpleIntegerProperty(akunn.size());
    }

    public Integer getGudangID() {
        return gudangID.get();
    }

    public void setGudangID(Integer gudangID) {
        this.gudangID.set(gudangID);
    }

    public String getNama() {
        return nama.get();
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public String getAlamat() {
        return alamat.get();
    }

    public void setAlamat(String alamat) {
        this.alamat.set(alamat);
    }

    public Integer getNoAkunn() {
        return noAkunn.get();
    }

    public ArrayList<Akun> getAkunn() {
        return akunn;
    }

    public void setAkunn(ArrayList<Akun> akunn) {
        this.akunn = akunn;
    }
    
    public IntegerProperty gudangIDProperty(){
        return gudangID;
    }
    public StringProperty namaProperty(){
        return nama;
    }
    public StringProperty alamatProperty(){
        return alamat;
    }
    public IntegerProperty noAkunnProperty(){
        return noAkunn;
    }
}