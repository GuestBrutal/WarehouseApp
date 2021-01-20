/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkom;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author kmbrps
 */
public class Akun {
    private IntegerProperty noMember;
    private StringProperty barang;

    public Akun(int noMember, String barang) {
        this.noMember=new SimpleIntegerProperty(noMember);
        this.barang=new SimpleStringProperty(barang);
    }

    public String getBarang() {
        return barang.get();
    }

    public void setBarang(String barang) {
        this.barang.set(barang);
    }

    public Integer getNoMember() {
        return noMember.get();
    }

    public void setNoMember(int noMember) {
        this.noMember.set(noMember);
    }
    
    public void deposite(String amt){
        this.barang.set(this.barang.get()+amt);
    }
    public void withdraw(String amt){
        this.barang.set(this.barang.get()+amt);
    }
    public IntegerProperty noMemberProperty(){
        return noMember;
    }
    public StringProperty barangProperty(){
        return barang;
    }
}

