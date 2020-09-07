/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Aziz_YS
 */
public class koneksi {
    private static Connection con;
    public static Connection con2;
    public koneksi(){
        try{
            con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_restoran","root","");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Gagal Koneksi");
        }
    }
}