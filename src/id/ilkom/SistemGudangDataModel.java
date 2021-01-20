/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ilkom;

import id.ilkom.db.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kmbrps
 */
public class SistemGudangDataModel {
    public final Connection conn;

    public SistemGudangDataModel(String driver) throws SQLException {
        this.conn = DBHelper.getConnection(driver);
    }
    public void addSistemGudang(IndividualHolder gudang) throws SQLException{
        String insertGudang = "INSERT INTO sistem_gudang (gudang_id, nama, alamat)"
                + " VALUES (?,?,?)";
        String insertIndividual = "INSERT INTO individual_holder (gudang_id, nik, birthdate)"
                + " VALUES (?,?,?)";
        String insertAkun = "INSERT INTO akun (no_member, barang, gudang_id)"
                + " VALUES (?,?,?)";
        PreparedStatement stmtGudang = conn.prepareStatement(insertGudang);
        stmtGudang.setInt(1, gudang.getGudangID());
        stmtGudang.setString(2, gudang.getNama());
        stmtGudang.setString(3, gudang.getAlamat());
        stmtGudang.execute();
        
        PreparedStatement stmtIndividual = conn.prepareStatement(insertIndividual);
        stmtIndividual.setInt(1, gudang.getGudangID());
        stmtIndividual.setString(2, gudang.getNik());
        stmtIndividual.setString(3, gudang.getBirthdate());
        stmtIndividual.execute();
        
        PreparedStatement stmtAkun = conn.prepareStatement(insertAkun);
        stmtAkun.setInt(1, gudang.getAkunn().get(0).getNoMember());
        stmtAkun.setString(2, gudang.getAkunn().get(0).getBarang());
        stmtAkun.setInt(3, gudang.getGudangID());
        stmtAkun.execute();
        
    }
    public void addSistemGudang(CorporateHolder gudang) throws SQLException{
        String insertGudang = "INSERT INTO sistem_gudang (gudang_id, nama, alamat)"
                + " VALUES (?,?,?)";
        String insertCorporate = "INSERT INTO corporate_holder (gudang_id, contact)"
                + " VALUES (?,?)";
        String insertAkun = "INSERT INTO akun (no_member, barang, gudang_id)"
                + " VALUES (?,?,?)";
        PreparedStatement stmtGudang = conn.prepareStatement(insertGudang);
        stmtGudang.setInt(1, gudang.getGudangID());
        stmtGudang.setString(2, gudang.getNama());
        stmtGudang.setString(3, gudang.getAlamat());
        stmtGudang.execute();
        
        PreparedStatement stmtIndividual = conn.prepareStatement(insertCorporate);
        stmtIndividual.setInt(1, gudang.getGudangID());
        stmtIndividual.setString(2, gudang.getContact());
        stmtIndividual.execute();
        
        PreparedStatement stmtAkun = conn.prepareStatement(insertAkun);
        stmtAkun.setInt(1, gudang.getAkunn().get(0).getNoMember());
        stmtAkun.setString(2, gudang.getAkunn().get(0).getBarang());
        stmtAkun.setInt(3, gudang.getGudangID());
        stmtAkun.execute();
    }
    public ObservableList<IndividualHolder> getIndividualHolders(){
        ObservableList<IndividualHolder> data = FXCollections.observableArrayList();
        String sql="SELECT `gudang_id`, `nama`,`alamat`, `nik`, `birthdate` "
                + "FROM `sistem_gudang` NATURAL JOIN `individual_holder` "
                + "ORDER BY name";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlAkun = "SELECT no_member, barang "
                    + "FROM akun WHERE gudang_id="+rs.getInt(1);
                ResultSet rsAkun = conn.createStatement().executeQuery(sqlAkun);
                ArrayList<Akun> dataAkun = new ArrayList<>();
                while (rsAkun.next()){
                    dataAkun.add(new Akun(rsAkun.getInt(1),rsAkun.getString(2)));
                }
                data.add(new IndividualHolder(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), dataAkun));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SistemGudangDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    public ObservableList<CorporateHolder> getCorporateHolders(){
        ObservableList<CorporateHolder> data = FXCollections.observableArrayList();
        String sql="SELECT `gudang_id`, `nama`,`alamat`, `contact` "
                + "FROM `sistem_gudang` NATURAL JOIN `corporate_holder` "
                + "ORDER BY name";
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                String sqlAkun = "SELECT no_member, barang "
                    + "FROM akun WHERE gudang_id="+rs.getInt(1);
                ResultSet rsAkun = conn.createStatement().executeQuery(sqlAkun);
                ArrayList<Akun> dataAkun = new ArrayList<>();
                while (rsAkun.next()){
                    dataAkun.add(new Akun(rsAkun.getInt(1),rsAkun.getString(2)));
                }
                data.add(new CorporateHolder(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), dataAkun));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SistemGudangDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return data;
    }
    public ObservableList<Akun> getAkunn(int gudangID){
        ObservableList<Akun> data = FXCollections.observableArrayList();
        String sql="SELECT `no_member`, `barang` "
                + "FROM `akun` "
                + "WHERE gudang_id="+gudangID;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                data.add(new Akun(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SistemGudangDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    public int nextSistemGudangID() throws SQLException{
        String sql="SELECT MAX(gudang_id) from sistem_gudang";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)==0?1000001:rs.getInt(1)+1;
            }
        return 1000001;
    }
    public int nextNomorMember(int gudangID) throws SQLException{
        String sql="SELECT MAX(no_member) FROM akun WHERE gudang_id="+gudangID;
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()){
                return rs.getInt(1)+1;
            }
        return 0;
    }
    
    public void addAkun(int gudangID, Akun ak) throws SQLException{
        String insertGudang = "INSERT INTO akun (gudang_id, no_member, barang)"
                + " VALUES (?,?,?)";
  
        PreparedStatement stmtGudang = conn.prepareStatement(insertGudang);
        stmtGudang.setInt(1, gudangID);
        stmtGudang.setInt(2, ak.getNoMember());
        stmtGudang.setString(3, ak.getBarang());
        stmtGudang.execute();
        
    }

}
