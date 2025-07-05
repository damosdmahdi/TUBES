package Penggunal;

public abstract class User {
    private int id;
    private String nama;
    private String username;
    private String password;

    public User(int id, String nama, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + " (" + getRole() + ")";
    }
}
