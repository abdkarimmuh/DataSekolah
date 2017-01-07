/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import datasiswa.Auth;
import java.util.Scanner;

/**
 *
 * @author Karim
 */
public class Login {
    public static void login() {
        String user, pass;
        Scanner in = new Scanner(System.in);
        Auth auth = new Auth();
        
        System.out.print("Masukkan Username: ");
        user = in.nextLine();
        
        System.out.print("Masukkan Password: ");
        pass = in.nextLine();
        
        if (user.equals(auth.getUsername()) && pass.equals(auth.getPassword())){
            System.out.println("");
            Menu.showMenu();
        } else {
            System.out.println("Maaf username atau password anda salah");
        }
    }
}
