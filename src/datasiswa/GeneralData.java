/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasiswa;

/**
 *
 * @author Karim
 */
public class GeneralData {
    private int id, na;
    private String nama, nis, grade;
    
    public GeneralData(int id, String nama, String nis, int na, String grade){
        this.id = id;
        this.nama = nama;
        this.nis = nis;
        this.na = na;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getNa() {
        return na;
    }

    public String getNama() {
        return nama;
    }

    public String getNis() {
        return nis;
    }

    public String getGrade() {
        return grade;
    }    
    
}
