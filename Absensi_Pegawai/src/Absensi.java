import java.time.LocalDate;
import java.time.LocalTime;

public class Absensi {
    private int id;
    private LocalDate tanggal;
    private LocalTime jamMasuk;
    private LocalTime jamKeluar;
    private String status;
    private Pegawai pegawai;
    private Shift shift;

    public Absensi(int id, LocalDate tanggal, LocalTime jamMasuk, String status, Pegawai pegawai, Shift shift) {
        this.id = id;
        this.tanggal = tanggal;
        this.jamMasuk = jamMasuk;
        this.status = status;
        this.pegawai = pegawai;
        this.shift = shift;
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

    public String getStatus() {
        return status;
    }

    public Shift getShift() {
        return shift;
    }

    public void setJamKeluar(LocalTime jamKeluar) {
        this.jamKeluar = jamKeluar;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Tgl: " + tanggal + ", Pegawai: " + pegawai.getNama() + ", Masuk: " + jamMasuk
                + ", Keluar: " + jamKeluar + ", Status: " + status;
    }
}
