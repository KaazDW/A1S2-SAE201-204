/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.general.DefaultPieDataset;

/**
 * VERSION FINALE
 * @author Zineb
 */
public class RPI {
    public RPI(){
        
        
        
    }
    
      private static DefaultPieDataset createDatasetPie() {

        Connection conn = null;

        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            // driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // driver parameters
            String url = "jdbc:mysql:172.20.10.10:3306/SAE";
            String user = "admin0";
            String password = "0000";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // requete sql
            String strInsert = "SELECT * FROM table1";
            String SQLeffectiftotal = "select  SUM(effectif) from Groupes where idGroupe like 'S_'";

            //statement
            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {
                dataset.setValue(
                        rsLogin.getString("idGroupe"), Double.parseDouble(rsLogin.getString("effectif")));

                //affichage
                System.out.println("---------------------------");
                System.out.println("Id groupe " + rsLogin.getString("idGroupe"));
                System.out.println("effectif " + rsLogin.getString("effectif"));
                System.out.println("type " + rsLogin.getString("type"));
                System.out.println("parent " + rsLogin.getString("parent"));

            }
            if (conn != null) {
                conn.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver non chargé !" + e);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return dataset;
    }
}
