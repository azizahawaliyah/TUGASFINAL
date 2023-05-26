/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membuatcrudmvcjava.Controller;

import javax.swing.JOptionPane;
import membuatcrudmvcjava.DAO.DAO_Member;
import membuatcrudmvcjava.DAOImplement.Implement_Member;
import membuatcrudmvcjava.Model.Model_Member;
import membuatcrudmvcjava.Model.Tabel_Model_Member;
import membuatcrudmvcjava.View.View_Member;
import java.util.List;
/**
 *
 * @author VEBRI DEVELOPER
 */
public class Controller_Member {
    
    View_Member frame_member;
    Implement_Member implemet_member;
    List<Model_Member> list_member;
    
    public Controller_Member(View_Member frame_member){
        this.frame_member = frame_member;
        implemet_member = new DAO_Member();
        list_member = implemet_member.getALL();
    }
    
    //Tombol Reset
    public void reset(){
        frame_member.getTxtIDKode().setText("");
        frame_member.getTxtNamaPelanggan().setText("");
        frame_member.getTxtNoTelp().setText("");
        frame_member.getTxtAlamat().setText("");
        frame_member.getTxtPaketPelanggan().setSelectedItem("--- Pilih Paket ---");
        frame_member.getTxtCariData().setText("");
    }
    
    //Tampil Data Ke Tabel
    public void isiTable(){
        list_member = implemet_member.getALL();
        Tabel_Model_Member tmb = new Tabel_Model_Member(list_member);
        frame_member.getTabelDataMember().setModel(tmb);
    }
    
    //Menampilkan Data Ke Form Ketika Data DI Klik
    public void isiField(int row){
        frame_member.getTxtIDKode().setText(list_member.get(row).getId().toString());
        frame_member.getTxtNamaPelanggan().setText(list_member.get(row).getNama());
        frame_member.getTxtNoTelp().setText(list_member.get(row).getNo_telp());
        frame_member.getTxtAlamat().setText(list_member.get(row).getAlamat());
        frame_member.getTxtPaketPelanggan().setSelectedItem(list_member.get(row).getPaket());
    }
    
    //Insert Data Dari Form Ke Database
    public void insert(){
        if(!frame_member.getTxtNamaPelanggan().getText().trim().isEmpty()& !frame_member.getTxtNoTelp().getText().trim().isEmpty()& !frame_member.getTxtAlamat().getText().trim().isEmpty()){
            Model_Member b = new Model_Member();
            b.setNama(frame_member.getTxtNamaPelanggan().getText());
            b.setNo_telp(frame_member.getTxtNoTelp().getText());
            b.setAlamat(frame_member.getTxtAlamat().getText());
            b.setPaket(frame_member.getTxtPaketPelanggan().getSelectedItem().toString());
            
            implemet_member.insert(b);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Simpan");
        } else {
            JOptionPane.showMessageDialog(frame_member, "Data Tidak Boleh Kosong");
        }
    }
    
    //Update Data Dari Tabel Ke Database
    public void update(){
        if(!frame_member.getTxtIDKode().getText().trim().isEmpty()){
            Model_Member b = new Model_Member();
            b.setNama(frame_member.getTxtNamaPelanggan().getText());
            b.setNo_telp(frame_member.getTxtNoTelp().getText());
            b.setAlamat(frame_member.getTxtAlamat().getText());
            b.setPaket(frame_member.getTxtPaketPelanggan().getSelectedItem().toString());
            b.setId(Integer.parseInt(frame_member.getTxtIDKode().getText()));
            
            implemet_member.update(b);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Update");
        } else {
            JOptionPane.showMessageDialog(frame_member, "Silahkan Pilih Data Yang Akan Di Update");
        }
    }
    
    //Hapus Data Pada Tabel
    public void delete(){
        if(!frame_member.getTxtIDKode().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame_member.getTxtIDKode().getText());
            implemet_member.delete(id);
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
        } else {
            JOptionPane.showMessageDialog(frame_member, "Silahkan Pilih Data Yang Akan Di Hapus");
        }
    }
    
    //Cari Data
    public void isiTableCariNama(){
        list_member = implemet_member.getCariNama(frame_member.getTxtCariData().getText());
        Tabel_Model_Member tmb = new Tabel_Model_Member(list_member);
        frame_member.getTabelDataMember().setModel(tmb);
    }
    
    public void carinama(){
        if(!frame_member.getTxtCariData().getText().trim().isEmpty()){
            implemet_member.getCariNama(frame_member.getTxtCariData().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame_member, "Silahkan Pilih Data !!!");
        }
    }
}
