/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytools;

/**
 *
 * @author MATIUS
 */
public class HelpCommand implements Command{

    @Override
    public void action() {
        System.out.println("Cara Penggunaan:");
        System.out.println("mytools [direktori file target] <flag> <argument>");
        System.out.println("Flag:");
        System.out.println("-h                                          Untuk menampilkan cara pennggunaan");
        System.out.println("-t <json atau text>                         Mengkonversi file menjadi file menjadi json atau text");
        System.out.println("-o <tempat meletakkan file output>          Menentukkan tempat meletakkan file output ");
    }
    
}
