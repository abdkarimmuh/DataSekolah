/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author Karim
 */
public class Menu {
    public static void showMenu() throws IOException {
      Scanner in = new Scanner(System.in);
      int menu = 0;
      while (menu != 4) {
        System.out.println("==============================");
        System.out.println("=         Menu Utama         =");
        System.out.println("==============================");
        System.out.println("1. Tambah Data");
        System.out.println("2. Lihat Data Siswa");
        System.out.println("3. Tentang");
        System.out.println("4. Keluar");
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
                  DataSiswa.getData();
                  break;
              case 3:
                  About.showAbout();
                  break;
              case 4:
                  System.out.println("KELUAR");
                  exit(0);
              default:
                  System.out.println("Masukan angka 1-4");
                  break;
          }
      }
    }
}
