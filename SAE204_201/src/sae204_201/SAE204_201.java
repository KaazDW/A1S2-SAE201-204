/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.beans.Statement;
import java.io.IOException;
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
import ss2_IO_control.AnalogI2CInput;

/**
 * Class principale qui execute le programme 
 * @author p2100028
 */
public class SAE204_201 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        // TODO code application logic here

        // affichage donnée tableau
        //Fenetre f = new Fenetre();
        // f.setVisible(true);
        
        
        //affichage donnée Groupe
       // PieChart_DB o = new PieChart_DB("pie");
        //o.setVisible(true);

        //affichage données test sur RPI
        //RPI y = new RPI("test");
        // y.setVisible(true);
        
        
        //affichage donnée sur rpi avec capteur
       // SonRPI y = new SonRPI("test");
       // y.setVisible(true);
        
       Login l = new Login();
       l.setVisible(true);
        
        //affichage donne capteur
        //  AnalogI2CInput  i ;
        // i = new AnalogI2CInput(0);
        // i.runDemoConsole();
    }

}
