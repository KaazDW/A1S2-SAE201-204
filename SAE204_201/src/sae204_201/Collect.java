/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ss2_IO_control.AnalogI2CInput;

/**
 *
 * @author Zineb 
 * 
 * Cette classe permet de generer une fonction qui permet de lire
 * des données en temps reelles grace aux threads Elle se compose d'une seule
 * fonction run() qui permet de retourner des données issus du capteur de son et
 * fait un appel à la classe SonRPI qui genere un ensempble de graphe.
 * 
 * 
 */
public class Collect extends Thread {

    /**
     * Cette fonction est une fonction de type void Elle permet de retourner des
     * données issus du capteur de son et fait un appel à la classe SonRPI qui
     * genere un ensempble de graphe. Elle genere 2 exception : IOException
     * genere une exception d'entree ou sortie Et une exception liée à un numero
     * de bus (si il est mauvais)
     */
    
    public void run() {

        AnalogI2CInput an = null;
        try {
            an = new AnalogI2CInput(0);
        } catch (IOException ex) {
            Logger.getLogger(Collect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (I2CFactory.UnsupportedBusNumberException ex) {
            Logger.getLogger(Collect.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (AnalogI2CInput.started) {

            try {
                Thread.sleep(5000);
                an.getRowValue();
                SonRPI s = new SonRPI("Capteur de Son");
                s.addRPIThread(an.getRowValue(), an.getPourcentValue());

            } catch (Exception ex) {

                System.out.println("erreur thread" + ex);
            }

        }

    }

}
