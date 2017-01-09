/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import datasiswa.GeneralData;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    static File FILES = new File("data_siswa.txt"); 
    static int ID_COUNT = 0;
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
    
    public static void setData() throws IOException{
        DataSiswa ds = new DataSiswa();
        ds.inputData();
    }
    
    /*
    Untuk menambahkan data
    */
    public void inputData() throws IOException {
               
        Scanner in = new Scanner(System.in);
        System.out.print("Nama Lengkap : ");
        nama = in.nextLine();
        System.out.print("NIS : ");
        nis = in.nextLine();
        
        try {
            checkValue(tugas, "Tugas");
            tugas = nilai;
            checkValue(kehadiran, "Kehadiran");
            kehadiran = nilai;
            checkValue(uts, "UTS");
            uts = nilai;
            checkValue(uas, "UAS");
            uas = nilai;
        } catch (Exception e) {
            System.out.println("Maaf anda harus memasukan angka");
            inputData();
        }
        
        processValue(tugas, kehadiran, uts, uas);
        showValue();
        
    }
    
    /*
    Untuk mengecek nilai
    */
    private int checkValue(int nilai, String kata){
        do {
            System.out.print("Nilai " + kata + " (0-100) : ");
            Scanner in = new Scanner(System.in);
            nilai = in.nextInt();
            this.nilai = nilai;
            if (nilai > 100) {
                System.out.println("Angka tidak boleh lebih dari 100 dan kurang dari 0");
            }
        } while (nilai > 100);
        return this.nilai;
    }
    
    /*
    Untuk proses nilai
    */
    private void processValue(int tugas, int kehadiran, int uts, int uas){
        this.na = (tugas+kehadiran+uts+uas)/4;
        /* 
        100 - 86 = A
        85 - 71 = B
        70 - 56 = C
        55 - 41 = D
        40 - 0 = E
        */
        if (na > 85){
            grade = "A";
        } else if (na > 70) {
            grade = "B";
        } else if (na > 55) {
            grade = "C";
        } else if (na > 40) {
            grade = "D";
        } else {
            grade = "E";
        }        
    }
    
    private void showValue() throws IOException{
               
        Scanner pil = new Scanner(System.in);
        String pilihan;
        System.out.println("\n===========================");
        System.out.println("Siswa yang bernama : " + this.nama);
        System.out.println("Dengan NIS : " + this.nis);
        
        System.out.println("Mendapatkan Nilai Akhir sebesar : " + this.na +
                " dengan grade " + this.grade);
        
        System.out.println("\n1. Simpan \n2. Tulis Ulang \n3. Kembali");
        System.out.print("=> ");
        pilihan = pil.nextLine();
        if (pilihan.equals("1")) {
            saveData();
            Menu.showMenu();
        } else if (pilihan.equals("2")) {
            inputData();
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
    public void saveData() {
              
        try {
            //Cek File
            if (!FILES.exists()) {fileDatabase();}
            GeneralData siswa = new GeneralData(dataCount(), this.nama, this.nis, this.na, this.grade);
            PrintWriter out = new PrintWriter(new FileWriter(FILES.getAbsoluteFile(), true));
            out.write(
                siswa.getId() + " | " + 
                siswa.getNama() + " | " +
                siswa.getNis() + " | " +
                siswa.getNa() + " | " +
                siswa.getGrade() + "\n\r"
            );
            out.close();
        } catch (Exception e) {}
    }
    
    /*
    Mendapatkan semua data
    */
    public static void getData() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("| ID\t | Nama Lengkap\t\t | NIS\t\t | NA\t | Grade |");
        System.out.println("------------------------------------------------------------------");
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
                        System.out.println("| " + id + "\t | " + nama + "\t | " + nis + "\t | " + na + "\t |   " + grade + "   |");
                    } else {
                        System.out.println("Data belum ada.");
                    }
                });
            }
        } catch (Exception e) {}
        System.out.println("------------------------------------------------------------------");
    }
}
