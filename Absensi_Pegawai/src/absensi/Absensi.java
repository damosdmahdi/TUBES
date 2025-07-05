package absensi;

import java.time.LocalDate;
import java.time.LocalTime;
import Penggunal.Pegawai;

public class Absensi {
    private int id;
    private LocalDate tanggal;
    private LocalTime jamMasuk;
    private LocalTime jamKeluar;
    private Pegawai pegawai;

    public Absensi(int id, LocalDate tanggal, LocalTime jamMasuk, Pegawai pegawai) {
        this.id = id;
        this.tanggal = tanggal;
        this.jamMasuk = jamMasuk;
        this.pegawai = pegawai;
    }

    public int getId() {
        return id;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public LocalTime getJamMasuk() {
        return jamMasuk;
    }

    public LocalTime getJamKeluar() {
        return jamKeluar;
    }

    public void setJamKeluar(LocalTime jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tgl: " + tanggal + ", Pegawai: " + pegawai.getNama() + ", Masuk: " + jamMasuk
                + ", Keluar: " + jamKeluar;
    }
}