/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Karim
 */
public class Menu {
    public static void showMenu() {
      Scanner in = new Scanner(System.in);
      int menu = 0;
      do {
        System.out.println("==============================");
        System.out.println("=         Menu Utama         =");
        System.out.println("==============================");
        System.out.println("1. Tambah Data");
        System.out.println("2. Hapus Data");
        System.out.println("3. Lihat Data Siswa");
        System.out.println("4. Tentang");
        System.out.println("5. Keluar");
        System.out.println("=============================");
        System.out.print("Pilih Menu : ");
        try {
            menu = Integer.valueOf(in.nextLine());
        } catch (Exception e) {
            System.err.println("Harus angka");
            showMenu();
            break;
        }
        switch (menu) {
            case 1:
                DataSiswa.setData();
                break;
            case 2:
                DataSiswa.removeData();
                break;
            case 3:
                DataSiswa.getData();
                break;
            case 4:
                About.getAbout();
                break;
            case 5:
                System.out.println("KELUAR");
                break;
            default :
                System.out.println("Masukan angka 1-4");
        }
      } while (menu != 5);
    }
}
