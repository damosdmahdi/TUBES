import java.time.LocalTime;

public class Shift {
    private int idShift;
    private String namaShift;
    private LocalTime jamMulai;
    private LocalTime jamSelesai;

    public Shift(int idShift, String namaShift, LocalTime jamMulai, LocalTime jamSelesai) {
        this.idShift = idShift;
        this.namaShift = namaShift;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public int getIdShift() {
        return idShift;
    }

    public String getNamaShift() {
        return namaShift;
    }

    public LocalTime getJamMulai() {
        return jamMulai;
    }

    public LocalTime getJamSelesai() {
        return jamSelesai;
    }

    public void setNamaShift(String namaShift) {
        this.namaShift = namaShift;
    }

    public void setJamMulai(LocalTime jamMulai) {
        this.jamMulai = jamMulai;
    }

    public void setJamSelesai(LocalTime jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    @Override
    public String toString() {
        return "ID: " + idShift + ", Nama: '" + namaShift + "', Jam: " + jamMulai + " - " + jamSelesai;
    }
}
