package Penggunal;

public class Pegawai extends User {
    private int nip;
    private String jabatan;

    public Pegawai(int id, String nama, String username, String password, int nip, String jabatan) {

        super(id, nama, username, password);
        this.nip = nip;
        this.jabatan = jabatan;
    }

    @Override
    public String getRole() {
        return "Pegawai";
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", NIP: " + nip + ", Nama: " + getNama() + ", Jabatan: " + jabatan;
    }
}
