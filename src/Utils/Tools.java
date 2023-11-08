/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Tools {
    public static void cout(String s, String end){
        System.out.printf("%s%s",s,end);
    }
    public static String input(String title, Scanner sc){
        Tools.cout(title," : ");
        String inp = sc.nextLine();
        return inp;
    }
    
    public static int input(String title, Scanner sc, int defaultValue){
        Tools.cout(title," : ");
        try{
            int inp = sc.nextInt();
            return inp;
        }
        catch(Exception e){
            return defaultValue;
        }
    }
    public static boolean compareDate(Date date1, Date date2){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date1).equals(sdf.format(date1));
    }
}
