package laporan;

import Penggunal.Pegawai;

public class Laporan {
    private int id;
    private int bulan;
    private int tahun;
    private int totalHadir;
    private Pegawai pegawai;

    public Laporan(int id, int bulan, int tahun, int totalHadir, Pegawai pegawai) {
        this.id = id;
        this.bulan = bulan;
        this.tahun = tahun;
        this.totalHadir = totalHadir;
        this.pegawai = pegawai;
    }

    public int getId() {
        return id;
    }

    public int getBulan() {
        return bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public int getTotalHadir() {
        return totalHadir;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    @Override
    public String toString() {
        return "Laporan ID: " + id + ", Periode: " + bulan + "/" + tahun +
                ", Pegawai: " + pegawai.getNama() + ", Total Hadir: " + totalHadir + " hari";
    }
}
