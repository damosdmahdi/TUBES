package Penggunal;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import absensi.Absensi;
import laporan.Laporan;

public interface CRUD {
    void createPegawai(Scanner scanner, List<Pegawai> dbPegawai, AtomicInteger idCounter);

    void readPegawai(List<Pegawai> dbPegawai);

    void updatePegawai(Scanner scanner, Pegawai p);

    void deletePegawai(Pegawai p, List<Pegawai> dbPegawai);
}