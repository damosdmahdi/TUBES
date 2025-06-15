import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Admin extends User {
    public Admin(int id, String nama, String username, String password) {
        super(id, nama, username, password);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    public void createPegawai(Scanner scanner, List<Pegawai> dbPegawai, AtomicInteger idCounter) {
        System.out.println("\n--- Menambah Pegawai Baru ---");
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("NIP: ");
        int nip = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Jabatan: ");
        String jabatan = scanner.nextLine();

        Pegawai baru = new Pegawai(idCounter.getAndIncrement(), nama, username, password, nip, jabatan);
        dbPegawai.add(baru);
        System.out.println("SUKSES: Pegawai baru berhasil ditambahkan!");
    }

    public void readPegawai(List<Pegawai> dbPegawai) {
        System.out.println("\n--- Daftar Semua Pegawai ---");
        if (dbPegawai.isEmpty()) {
            System.out.println("Tidak ada data pegawai.");
            return;
        }
        dbPegawai.forEach(System.out::println);
    }

    public void updatePegawai(Scanner scanner, Pegawai p) {
        System.out.println("\n--- Mengubah Data Pegawai: " + p.getNama() + " ---");
        System.out.print("Jabatan baru (kosongkan jika tidak berubah): ");
        String jabatanBaru = scanner.nextLine();
        if (!jabatanBaru.trim().isEmpty()) {
            p.setJabatan(jabatanBaru);
            System.out.println("SUKSES: Jabatan berhasil diubah.");
        } else {
            System.out.println("Tidak ada perubahan dilakukan.");
        }
    }

    public void deletePegawai(Pegawai p, List<Pegawai> dbPegawai) {
        dbPegawai.remove(p);
        System.out.println("\nSUKSES: Pegawai '" + p.getNama() + "' berhasil dihapus.");
    }

    public void kelolaShift() {
        System.out.println("Fitur kelola shift belum diimplementasikan sepenuhnya.");
    }

    public void generateLaporan(Scanner scanner, List<Absensi> dbAbsensi, List<Pegawai> dbPegawai,
            List<Laporan> dbLaporan, AtomicInteger idCounter) {
        System.out.println("\n--- Membuat Laporan Absensi ---");
        if (dbPegawai.isEmpty()) {
            System.out.println("Tidak ada data pegawai untuk dibuatkan laporan.");
            return;
        }

        System.out.println("Pilih Pegawai:");
        for (int i = 0; i < dbPegawai.size(); i++) {
            System.out.println((i + 1) + ". " + dbPegawai.get(i).getNama());
        }
        System.out.print("Pilihan: ");
        int pilihanPegawai = scanner.nextInt();
        Pegawai p = dbPegawai.get(pilihanPegawai - 1);

        System.out.print("Masukkan Bulan (1-12): ");
        int bulan = scanner.nextInt();
        System.out.print("Masukkan Tahun (contoh: 2025): ");
        int tahun = scanner.nextInt();
        scanner.nextLine();
        long totalHadir = dbAbsensi.stream()
                .filter(abs -> abs.getPegawai().equals(p))
                .filter(abs -> abs.getTanggal().getMonthValue() == bulan)
                .filter(abs -> abs.getTanggal().getYear() == tahun)
                .filter(abs -> abs.getStatus().equalsIgnoreCase("Hadir")
                        || abs.getStatus().equalsIgnoreCase("Terlambat"))
                .count();

        Laporan laporanBaru = new Laporan(idCounter.getAndIncrement(), bulan, tahun, (int) totalHadir, p);
        dbLaporan.add(laporanBaru);
        System.out.println("SUKSES: Laporan berhasil dibuat!");
        System.out.println(laporanBaru);
    }
}
