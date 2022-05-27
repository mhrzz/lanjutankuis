/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssd;

/**
 *
 * @author Elgi
 */
public class ssd {
    private int id;
    private String merk;
    private String ukuran;
    private String tanggal;
    private String harga;

    public ssd(int id, String merk, String ukuran, String tanggal, String harga) {
        this.id = id;
        this.merk = merk;
        this.ukuran = ukuran;
        this.tanggal = tanggal;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getMerk() {
        return merk;
    }

    public String getUkuran() {
        return ukuran;
    }

    public String getTanggal() {
        return tanggal;
    } 

    public String getHarga() {
        return harga;
    }
    
    
}
