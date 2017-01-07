/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import datasiswa.GeneralData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Karim
 */
public class DataSiswa {
    //File Database
    static File FILES = new File("data.txt"); 
    static int ID_COUNT = 1;
    String nama, nis, grade;
    int tugas, kehadiran, uts, uas, na, nilai;
    
    public static void fileDatabase() {
        try {
            //Jika tidak ada file, maka dibuatkan file kosong
            FileWriter fw = new FileWriter(FILES.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");
            bw.close();
        } catch (Exception e) {}
    }
    
    /*
    Menghitung jumlah data
    */
    public static int dataCount() {
        try {
            Scanner in = new Scanner(FILES);
            ArrayList<String> datas = new ArrayList<>();
            while (in.hasNextLine()) {
                datas.add(in.nextLine());
            }
            return (datas.size() + 1) + ID_COUNT;
        } catch (Exception e) {return 0;}
    }
    
    /*
    Untuk menambahkan data
    */
    public static void setData() {
        DataSiswa ds = new DataSiswa();
        
        Scanner in = new Scanner(System.in);
        System.out.print("Nama Lengkap : ");
        ds.nama = in.nextLine();
        System.out.print("NIS : ");
        ds.nis = in.nextLine();
        
        try {
            checkValue(ds.tugas, "Tugas");
            ds.tugas = ds.nilai;
            checkValue(ds.kehadiran, "Kehadiran");
            ds.kehadiran = ds.nilai;
            checkValue(ds.uts, "UTS");
            ds.uts = ds.nilai;
            checkValue(ds.uas, "UAS");
            ds.uas = ds.nilai;
        } catch (Exception e) {
            System.out.println("Maaf anda harus memasukan angka");
            setData();
        }
        
        processValue(ds.tugas, ds.kehadiran, ds.uts, ds.uas);
        showValue();
        
    }
    
    /*
    Untuk mengecek nilai
    */
    private static int checkValue(int nilai, String kata){
        DataSiswa ds = new DataSiswa();
        do {
            System.out.print("Nilai " + kata + " (0-100) : ");
            Scanner in = new Scanner(System.in);
            nilai = in.nextInt();
            ds.nilai = nilai;
            if (nilai > 100) {
                System.out.println("Angka tidak boleh lebih dari 100 dan kurang dari 0");
            }
        } while (nilai > 100);
        return ds.nilai;
    }
    
    /*
    Untuk proses nilai
    */
    private static void processValue(int tugas, int kehadiran, int uts, int uas){
        DataSiswa ds = new DataSiswa();
        ds.na = (tugas+kehadiran+uts+uas)/4;
        /* 
        100 - 86 = A
        85 - 71 = B
        70 - 56 = C
        55 - 41 = D
        40 - 0 = E
        */
        if (ds.na > 85){
            ds.grade = "A";
        } else if (ds.na > 70) {
            ds.grade = "B";
        } else if (ds.na > 55) {
            ds.grade = "C";
        } else if (ds.na > 40) {
            ds.grade = "D";
        } else {
            ds.grade = "E";
        }        
    }
    
    private static void showValue(){
        DataSiswa ds = new DataSiswa();
        
        Scanner pil = new Scanner(System.in);
        String pilihan;
        System.out.println("Siswa yang bernama : " + ds.nama);
        System.out.println("Dengan NIS : " + ds.nis);
        
        System.out.println("Mendapatkan Nilai Akhir sebesar : " + ds.na +
                " dengan grade " + ds.grade);
        
        System.out.println("\n1. Simpan \n2. Tulis Ulang \n3. Kembali");
        System.out.print("=> ");
        pilihan = pil.nextLine();
        if (pilihan.equals("1")) {
            saveData();
            Menu.showMenu();
        } else if (pilihan.equals("2")) {
            setData();
        } else if (pilihan.equals("3")) {
            Menu.showMenu();
        } else if (pilihan.isEmpty()) {
            System.out.println("Maaf inputan tidak boleh kosong");
        } else if (!pilihan.matches("-?\\d+(\\.\\d+)?")){
            System.out.println("Inputan harus berupa angka");
        } else {
            System.out.println("Pilihan antara 1 sampai 3");
        }
    }
    
    /*
    Untuk menambahkan data
    */
    public static void saveData() {
        DataSiswa ds = new DataSiswa();
        
        try {
            //Cek File
            if (!FILES.exists()) {fileDatabase();}
            GeneralData siswa = new GeneralData(dataCount(), ds.nama, ds.nis, ds.na, ds.grade);
            PrintWriter out = new PrintWriter(new FileWriter(FILES.getAbsoluteFile(), true));
            out.write(
                siswa.getId() + " | " + 
                siswa.getNama() + " | " +
                siswa.getNis() + " | " +
                siswa.getNa() + " | " +
                siswa.getGrade() + "\n"
            );
            out.close();
        } catch (Exception e) {}
    }
    
    /*
    Mendapatkan semua data
    */
    public static void getData() {
        System.out.println("----------------------------------------------------------");
        System.out.println("| ID\t | Nama Lengkap\t\t | NIS\t | NA\t | Grade |");
        System.out.println("----------------------------------------------------------");
        try {
            //Cek File
            if (!FILES.exists()) {
                fileDatabase();
                System.out.println("Data belum ada.");
            } else {
                //jika ada, maka dilakukan pengecekan terhadap data
                Scanner in = new Scanner(FILES);
                ArrayList<String> datas = new ArrayList<>();
                while (in.hasNextLine()) {
                    datas.add(in.nextLine());
                }
                datas.forEach((t) -> {
                    if (t.length() > 0) {
                        String id = t.trim().split(Pattern.quote("|"))[0].trim();
                        String nama = t.trim().split(Pattern.quote("|"))[1].trim();
                        nama = (nama.length() >= 20) ? nama.substring(0, Math.min(nama.length(), 17)) + "..." : nama;
                        String nis = t.trim().split(Pattern.quote("|"))[2].trim();
                        String na = t.trim().split(Pattern.quote("|"))[3].trim();
                        String grade = t.trim().split(Pattern.quote("|"))[4].trim();
                        System.out.println("| " + id + "\t | " + nama + "\t | " + nis + "\t | " + na + "\t | " + grade + "\t|");
                    } else {
                        System.out.println("Data belum ada.");
                    }
                });
            }
        } catch (Exception e) {}
        System.out.println("--------------------------------------------------");
    }
    
    /*
    Untuk menghapus data
    */
    public static void remData() {
        try {
            if (FILES.exists()) {
                Scanner in = new Scanner(System.in);
                System.out.print("ID : ");
                String id = in.nextLine();
                File cadang = new File(FILES.getAbsolutePath() + ".tmp");
                BufferedReader reader = new BufferedReader(new FileReader(FILES));
                BufferedWriter writer = new BufferedWriter(new FileWriter(cadang));   
                String currentLine;
                while((currentLine = reader.readLine()) != null) {
                    if(currentLine.contains(id)) continue;
                    writer.write(currentLine + "\n");
                }
                writer.close(); 
                reader.close(); 
                cadang.renameTo(FILES);
            } else {
                System.out.println("Tidak ada data untuk dihapus.");
            }
        } catch (Exception e){}
    }
}
