import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static List<Pegawai> dbPegawai = new ArrayList<>();
    private static List<Shift> dbShift = new ArrayList<>();
    private static List<Absensi> dbAbsensi = new ArrayList<>();
    private static List<Laporan> dbLaporan = new ArrayList<>();
    private static List<User> dbUser = new ArrayList<>();

    private static AtomicInteger userIdCounter = new AtomicInteger(0);
    private static AtomicInteger shiftIdCounter = new AtomicInteger(1);
    private static AtomicInteger absensiIdCounter = new AtomicInteger(1);
    private static AtomicInteger laporanIdCounter = new AtomicInteger(1);

    public static void main(String[] args) {

        inisialisasiData();

        Scanner scanner = new Scanner(System.in);
        int pilihanUtama = -1;

        while (pilihanUtama != 0) {
            System.out.println(" TUBES PBO SISTEM ABSENSI PEGAWAI ");
            System.out.println("Masuk sebagai:");
            System.out.println("1. Admin");
            System.out.println("2. Pegawai");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilihan Anda: ");
            try {
                pilihanUtama = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanUtama) {
                    case 1:
                        menuAdmin(scanner);
                        break;
                    case 2:
                        menuPegawai(scanner);
                        break;
                    case 0:
                        System.out.println("Terima kasih telah menggunakan aplikasi.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Masukkan harus berupa angka!");
                scanner.nextLine();
            }
        }
        // scanner.close();

    }

    private static void inisialisasiData() {
        // Admin
        Admin admin = new Admin(userIdCounter.getAndIncrement(), "Admin Utama", "admin", "admin123");
        dbUser.add(admin);

        // Shift
        Shift shiftPagi = new Shift(shiftIdCounter.getAndIncrement(), "Pagi", LocalTime.of(8, 0), LocalTime.of(17, 0));
        Shift shiftMalam = new Shift(shiftIdCounter.getAndIncrement(), "Malam", LocalTime.of(20, 0),
                LocalTime.of(5, 0));
        dbShift.add(shiftPagi);
        dbShift.add(shiftMalam);

        // Pegawai
        Pegawai p1 = new Pegawai(userIdCounter.getAndIncrement(), "Budi Santoso", "budi", "budi123", 1001, "Developer");
        Pegawai p2 = new Pegawai(userIdCounter.getAndIncrement(), "Cindy Pratama", "cindy", "cindy123", 1002,
                "UI/UX Designer");
        dbPegawai.add(p1);
        dbPegawai.add(p2);
        dbUser.addAll(dbPegawai);
    }

    private static void menuAdmin(Scanner scanner) {
        Admin admin = (Admin) dbUser.stream().filter(u -> u instanceof Admin).findFirst().orElse(null);
        if (admin == null) {
            System.out.println("Tidak ada user Admin yang terdaftar.");
            return;
        }

        // --- BAGIAN YANG DITAMBAHKAN ---
        System.out.print("Masukkan password untuk " + admin.getNama() + ": ");
        String passwordInput = scanner.nextLine();

        if (!admin.getPassword().equals(passwordInput)) {
            System.out.println("Password salah! Akses ditolak.");
            return; // Hentikan method jika password salah
        }
        // --- AKHIR BAGIAN YANG DITAMBAHKAN ---

        System.out.println("\nLogin berhasil! Selamat datang, " + admin.getNama() + ".");
        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("1. Tambah Pegawai (Create)");
            System.out.println("2. Lihat Semua Pegawai (Read)");
            System.out.println("3. Ubah Data Pegawai (Update)");
            System.out.println("4. Hapus Pegawai (Delete)");
            System.out.println("5. Buat Laporan Absensi");
            System.out.println("0. Logout");
            System.out.print("Pilihan Anda: ");

            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
                switch (pilihan) {
                    case 1:
                        admin.createPegawai(scanner, dbPegawai, userIdCounter);
                        break;
                    case 2:
                        admin.readPegawai(dbPegawai);
                        break;
                    case 3:
                        admin.readPegawai(dbPegawai);
                        System.out.print("Pilih ID pegawai yang akan diubah: ");
                        int idUpdate = scanner.nextInt();
                        scanner.nextLine();
                        Pegawai pUpdate = dbPegawai.stream().filter(p -> p.getId() == idUpdate).findFirst()
                                .orElse(null);
                        if (pUpdate != null)
                            admin.updatePegawai(scanner, pUpdate);
                        else
                            System.out.println("Pegawai tidak ditemukan!");
                        break;
                    case 4:
                        admin.readPegawai(dbPegawai);
                        System.out.print("Pilih ID pegawai yang akan dihapus: ");
                        int idDelete = scanner.nextInt();
                        scanner.nextLine();
                        Pegawai pDelete = dbPegawai.stream().filter(p -> p.getId() == idDelete).findFirst()
                                .orElse(null);
                        if (pDelete != null)
                            admin.deletePegawai(pDelete, dbPegawai);
                        else
                            System.out.println("Pegawai tidak ditemukan!");
                        break;
                    case 5:
                        admin.generateLaporan(scanner, dbAbsensi, dbPegawai, dbLaporan, laporanIdCounter);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Terjadi error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void menuPegawai(Scanner scanner) {
        System.out.println("\n--- Silakan Pilih Akun Anda ---");
        if (dbPegawai.isEmpty()) {
            System.out.println("Tidak ada data pegawai yang terdaftar.");
            return;
        }

        for (int i = 0; i < dbPegawai.size(); i++) {
            System.out.println((i + 1) + ". " + dbPegawai.get(i).getNama());
        }
        System.out.print("Pilihan Anda: ");

        try {
            int pilihanUser = scanner.nextInt();
            scanner.nextLine();

            if (pilihanUser < 1 || pilihanUser > dbPegawai.size()) {
                System.out.println("Pilihan tidak valid.");
                return;
            }

            Pegawai pegawaiDipilih = dbPegawai.get(pilihanUser - 1);

            // --- BAGIAN YANG DITAMBAHKAN ---
            System.out.print("Masukkan password untuk " + pegawaiDipilih.getNama() + ": ");
            String passwordInput = scanner.nextLine();

            if (!pegawaiDipilih.getPassword().equals(passwordInput)) {
                System.out.println("Password salah! Akses ditolak.");
                return; // Hentikan method jika password salah
            }
            // --- AKHIR BAGIAN YANG DITAMBAHKAN ---

            System.out.println("\nLogin berhasil! Selamat datang, " + pegawaiDipilih.getNama() + ".");
            int pilihanMenu = -1;
            while (pilihanMenu != 0) {
                System.out.println("\n--- Menu Absensi ---");
                System.out.println("1. Rekam Absensi Masuk");
                System.out.println("2. Rekam Absensi Keluar");
                System.out.println("0. Logout");
                System.out.print("Pilihan Anda: ");

                pilihanMenu = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanMenu) {
                    case 1:
                        rekamAbsensiMasuk(scanner, pegawaiDipilih);
                        break;
                    case 2:
                        rekamAbsensiKeluar(pegawaiDipilih);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                        break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("ERROR: Masukkan harus berupa angka!");
            scanner.nextLine();
        }
    }

    private static void rekamAbsensiMasuk(Scanner scanner, Pegawai p) {
        System.out.println("\n--- Rekam Absensi Masuk ---");
        System.out.println("Pilih shift hari ini:");
        for (int i = 0; i < dbShift.size(); i++) {
            System.out.println((i + 1) + ". " + dbShift.get(i).getNamaShift());
        }
        System.out.print("Pilihan Shift: ");
        int pilihanShift = scanner.nextInt();
        scanner.nextLine();
        Shift shiftHariIni = dbShift.get(pilihanShift - 1);

        LocalTime jamMasuk = LocalTime.now();
        String status = jamMasuk.isAfter(shiftHariIni.getJamMulai()) ? "Terlambat" : "Hadir";

        Absensi absensiBaru = new Absensi(absensiIdCounter.getAndIncrement(), LocalDate.now(), jamMasuk, status, p,
                shiftHariIni);
        dbAbsensi.add(absensiBaru);
        System.out.println("SUKSES: Absensi masuk berhasil direkam pada jam " + jamMasuk);
        System.out.println(absensiBaru);
    }

    private static void rekamAbsensiKeluar(Pegawai p) {
        System.out.println("\n--- Rekam Absensi Keluar ---");
        Absensi absensiHariIni = dbAbsensi.stream()
                .filter(abs -> abs.getPegawai().equals(p) && abs.getTanggal().equals(LocalDate.now())
                        && abs.getJamKeluar() == null)
                .findFirst().orElse(null);

        if (absensiHariIni == null) {
            System.out.println("Anda belum melakukan absensi masuk hari ini atau sudah merekam jam keluar.");
            return;
        }

        LocalTime jamKeluar = LocalTime.now();
        absensiHariIni.setJamKeluar(jamKeluar);
        System.out.println("SUKSES: Absensi keluar berhasil direkam pada jam " + jamKeluar);
        System.out.println(absensiHariIni);
    }
}
