/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;

/**
 *
 * @author Karim
 */
public class About {
    public static void showAbout() throws IOException{
        System.out.println("TENTANG KAMI \n==============================");
        System.out.println("Proyek ini dibuat untuk memenuhi tugas Pemograman Berorientasi Objek");
        System.out.println("Dibuat oleh :");
        System.out.println("1.Muhammad Abdul Karim (0110215053)");
        System.out.println("2.Hasnah Mujahidah Amatullah (0110115007)");
        System.out.println("3.Afifah Sausan (0110115016)");
        System.out.println("4.Muhammad Luqni Baehaqi (0110115024)");
        System.out.println("5.Muhammad Aslam Pangestu Idham (0110215002)");
        System.out.println("6.Muhammad Jaifer Rama Putra (0110215042)");
        System.out.println("\nPress any key to return to menu...");
        System.in.read();//pause console
    }
}
