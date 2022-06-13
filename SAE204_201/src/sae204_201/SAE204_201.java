/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author p2100028
 */
public class SAE204_201 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here

        /*try
{
Class.forName("com.mysql.jdbc.Driver");
}
catch(ClassNotFoundException e)
{
System.err.println(" Erreur de chargement du driver : " + e) ;
}
         */

 /*            
       Connection conn = null;
try {
    Class.forName("com.mysql.jdbc.Driver");
    // db parameters
    String url       = "jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/EDT";
    String user      = "p2100028";
    String password  = "12100028";
	
    // create a connection to the database
    conn = DriverManager.getConnection(url, user, password);
    // more processing here
    // ...	
} catch(SQLException e) {
   System.out.println(e.getMessage());
} finally {
         */
        JFreeChartExample chart = new JFreeChartExample("base de donnée", "test");
        chart.pack();
        chart.setVisible(true);

 
        Connection conn = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // db parameters
            String url = "jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/EDT";
            String user = "p2100028";
            String password = "12100028";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            // more processing here
            // ...

            String strInsert = "SELECT * FROM Groupes";
             String SQLeffectiftotal = "select  SUM(effectif) from Groupes where idGroupe like 'S_'";

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {
                  System.out.println("---------------------------");
                System.out.println("Id groupe "+rsLogin.getString("idGroupe"));
                System.out.println("effectif "+rsLogin.getString("effectif"));
                System.out.println("type "+rsLogin.getString("type"));
                System.out.println("parent "+rsLogin.getString("parent"));
      
 
            }
           rsLogin.close();
   
   
            if (conn != null) {
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non chargé !" + e);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
  

}
