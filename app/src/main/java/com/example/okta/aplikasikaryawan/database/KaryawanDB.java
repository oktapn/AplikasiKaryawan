package com.example.okta.aplikasikaryawan.database;

public class KaryawanDB {
    private int id;
    private int nik;
    private String nama;
    private String ttl;
    private String jeniskelamin;
    private String alamat;
    private String hobi;

    public KaryawanDB() {
    }

    public KaryawanDB( int nik, String nama, String ttl, String jeniskelamin, String alamat, String hobi) {
        this.nik = nik;
        this.nama = nama;
        this.ttl = ttl;
        this.jeniskelamin = jeniskelamin;
        this.alamat = alamat;
        this.hobi = hobi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }
}
