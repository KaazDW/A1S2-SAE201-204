/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import com.pi4j.io.i2c.I2CFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import ss2_IO_control.AnalogI2CInput;

/**
 * Cette classe est la classe principale de cette application car elle permet de
 * se connecter à la base de données puis generer plusieurs graphe aini qu'une
 * grille liees aux donnée du capteur.
 *
 * @author Zineb
 */
public class SonRPI extends JFrame implements ActionListener, FocusListener {

    java.sql.Statement pst = null;
    ResultSet rs = null;
    JMenuItem pie;
    JMenuItem histog;
    JMenuItem tab;
    JMenuItem courbe;
    JMenuItem run;
    JMenuItem stop;
    JMenuItem btn_exit;
    JMenuBar menubar;
    JMenu menu;

    /**
     * Constructeur qui permet de creer une fenetre principale avec un graph de
     * courbe, un menu qui nous permet de voir plusieurs graphique, ou bien de
     * voir une grille de données. Ce constructeur fait aussi appel a une
     * fonction qui permet de rajouter les valeurs issu du capteur de son a une
     * base de donnée
     *
     * @param title corespond aux titre de l'application
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException
     */
    public SonRPI(String title) throws ClassNotFoundException, SQLException, IOException, I2CFactory.UnsupportedBusNumberException {

        super(title);
        addRPI();
       // DefaultPieDataset datasetPie = createDatasetPie();
        // DefaultCategoryDataset datasetHisto = createDatasetHisto(); 
      //  JFreeChart chart = createPie(datasetPie);
        
         DefaultCategoryDataset datasetCourbe = createDatasetCourbe();
         JFreeChart chart = createCourbe(datasetCourbe);
            
         
        //JFreeChart chart=createHisto(datasetHisto);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(1000, 500));
        chartPanel.setChart(chart);
        setContentPane(chartPanel);

        menubar = new JMenuBar();
        menu = new JMenu("From DB");
        pie = new JMenuItem("pie");
        histog = new JMenuItem("histo");
        tab = new JMenuItem("tab");
        courbe = new JMenuItem("courbe");
        run = new JMenuItem("run");
        stop = new JMenuItem("stop");
        btn_exit = new JMenuItem("Exit");

        menu.add(pie);
        menu.add(histog);
        menu.add(tab);
        menu.add(courbe);
        menu.add(run);
        menu.add(stop);
        menubar.add(menu);

        this.setJMenuBar(menubar);
        this.setSize(1000, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //  this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        pie.addActionListener(this);
        histog.addActionListener(this);
        tab.addActionListener(this);
        courbe.addActionListener(this);
        run.addActionListener(this);
        stop.addActionListener(this);
        menubar.add(btn_exit);
        btn_exit.addActionListener(this);

    }

    /**
     * Cette fonction nous permet de recuperer toute les valeurs issu de notre
     * base de donnée utile pour creer le graph circulaire lie au capteur de
     * son.
     *
     * @return un dataset qui contient toute les valeurs issu de notre table de
     * base de donnée lie au capteur
     */
    private static DefaultPieDataset createDatasetPie() {

        Connection conn = null;
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            // driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // driver parameters
            String url = "jdbc:mysql://localhost:3306/Sae";
            String user = "admin0";
            String password = "0000";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // requete sql
            String strInsert = "SELECT * FROM sonsalle";

            //statement
            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {

                if (Integer.parseInt(rsLogin.getString("valSons")) > 0) {
                    dataset.setValue("" + Double.parseDouble(rsLogin.getString(3)) / 100 + "dB", Double.parseDouble(rsLogin.getString(4)));
                }

                //affichage
                System.out.println("---------------------------");
                System.out.println("id" + rsLogin.getString("id"));
                System.out.println("temps " + rsLogin.getString("temps"));
                System.out.println("valeurson " + rsLogin.getString("valSons"));
                System.out.println("pourcentage " + rsLogin.getString("pourcentage"));

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

    /**
     * Cette fonction nous permet de recuperer toute les valeurs issu de notre
     * base de donnée utile pour creer un histogramme lie au capteur de son.
     *
     * @return un dataset qui contient toute les valeurs issu de notre table de
     * base de donnée lie au capteur de son
     */
    private static DefaultCategoryDataset createDatasetHisto() {

        Connection conn = null;
        DefaultCategoryDataset d = new DefaultCategoryDataset();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // driver parameters
            String url = "jdbc:mysql://localhost:3306/Sae";
            String user = "admin0";
            String password = "0000";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //requete sql
            String strInsert = "SELECT * FROM sonsalle";

            // creation statement
            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {

                //affichage
                System.out.println("---------------------------");
                System.out.println(rsLogin.getString(2));
                System.out.println(rsLogin.getString(3));

                if (Integer.parseInt(rsLogin.getString("valSons")) > 0) {

                    d.addValue(Double.parseDouble(rsLogin.getString("valSons")) / 100, rsLogin.getString(1), "temps");
                }
            }
            if (conn != null) {
                conn.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver non chargé !" + e);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return d;
    }

    /**
     *
     * Cette fonction nous permet de recuperer toute les valeurs issu de notre
     * base de donnée utile pour creer une courbe lie au capteur de son.
     *
     * @return un dataset qui contient toute les valeurs issu de notre table de
     * base de donnée lie au capteur de son
     */
    private static DefaultCategoryDataset createDatasetCourbe() {

        Connection conn = null;
        DefaultCategoryDataset d = new DefaultCategoryDataset();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // driver parameters
            String url = "jdbc:mysql://localhost:3306/Sae";
            String user = "admin0";
            String password = "0000";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //requete sql
            String strInsert = "SELECT * FROM sonsalle";

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {

                //affichage
                System.out.println("---------------------------");
                System.out.println(rsLogin.getString(2));
                System.out.println(rsLogin.getString(3));

                if (Integer.parseInt(rsLogin.getString("valSons")) > 0) {
                    d.addValue(Double.parseDouble(rsLogin.getString(3)) / 100, "valeur du son", rsLogin.getString(1));
                }

            }
            if (conn != null) {
                conn.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver non chargé !" + e);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return d;
    }

    /**
     * Cette fonction permet d'ajouter tout 20 valeurs liées au capteur de son à
     * la base de donnée que nous avons créer.
     *
     * @throws IOException genere une exception d'entée ou sortie
     * @throws com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException genere
     * une exception lié au bus
     */
    private static void addRPI() throws IOException, I2CFactory.UnsupportedBusNumberException {

        AnalogI2CInput i;
        i = new AnalogI2CInput(0);

        Connection conn = null;

        for (int k = 0; k < 20; k++) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                // driver parameters
                String url = "jdbc:mysql://localhost:3306/Sae";
                String user = "admin0";
                String password = "0000";

                // create a connection to the database
                conn = DriverManager.getConnection(url, user, password);
                java.sql.Statement stLogin = conn.createStatement();

                // String temps= "UTC_TIME()";
                String temps = "CURTIME()";
                String valeurson = String.valueOf(i.getRowValue());
                String pourcentage = String.valueOf(i.getPourcentValue());

                //requete sql
                String addValue = "INSERT INTO sonsalle(id,temps,valSons,pourcentage) values ( NULL," + temps + ",'" + valeurson + "','" + pourcentage + "');";
                stLogin.executeUpdate(addValue);

            } catch (ClassNotFoundException e) {
                System.out.println("Driver non chargé !" + e);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    /**
     * Fonction qui rajoute des donnée à notre base de donnée en temps réel.
     *
     * @param u coorespond au donnée liée aux valeurs de son
     * @param q coorepond aux donnée liee au pourcentage de son
     * @throws IOException genere une exception liée aux entree ou sortie
     * @throws com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException
     */
    public static void addRPIThread(int u, int q) throws IOException, I2CFactory.UnsupportedBusNumberException {

        AnalogI2CInput i = new AnalogI2CInput(0);

        Connection conn = null;

        DefaultCategoryDataset d = new DefaultCategoryDataset();

        for (int k = 0; k < 20; k++) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                // driver parameters
                String url = "jdbc:mysql://localhost:3306/Sae";
                String user = "admin0";
                String password = "0000";

                // create a connection to the database
                conn = DriverManager.getConnection(url, user, password);
                java.sql.Statement stLogin = conn.createStatement();

                // String temps= "UTC_TIME()";
                String temps = "CURTIME()";
                String valeurson = String.valueOf(u);
                String pourcentage = String.valueOf(q);

                String addValue = "INSERT INTO sonsalle(id,temps,valSons,pourcentage) values ( NULL," + temps + ",'" + valeurson + "','" + pourcentage + "');";

                stLogin.executeUpdate(addValue);

            } catch (ClassNotFoundException e) {
                System.out.println("Driver non chargé !" + e);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    /**
     * Fonction qui creer un graph circulair(pie) en fonction de données qu'elle
     * recoit.
     *
     * @param z coorespond aux données liées à la base de données
     * @return un graph circulaire.
     */
    private static JFreeChart createPie(DefaultPieDataset z) {

        z = createDatasetPie();

        JFreeChart pieChart = ChartFactory.createPieChart3D("pourcentage valeur sons", z, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);

        ChartPanel c = new ChartPanel(pieChart);

        c.setPreferredSize(new java.awt.Dimension(1000, 500));

        return pieChart;

    }

    /**
     *
     * Fonction qui creer un histogramme en fonction de données qu'elle recoit.
     *
     * @param z coorespond aux données liées à la base de données du capteur du
     * son
     * @return un histogramme
     */
    private static JFreeChart createHisto(DefaultCategoryDataset z) {

        z = createDatasetHisto();
        JFreeChart lineChart = ChartFactory.createBarChart("Histogramme valeurs du son", "Différent Sons", "decibels", z, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel d = new ChartPanel(lineChart);
        d.setPreferredSize(new java.awt.Dimension(1000, 500));

        return lineChart;

    }

    /**
     * Fonction qui creer une courbe en fonction de données qu'elle recoit.
     *
     * @param z coorespond aux données liées à la base de données
     * @return une courbe
     */
    private static JFreeChart createCourbe(DefaultCategoryDataset z) {

        z = createDatasetCourbe();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Courbe valeurs du son",
                "nombre de valeurs", "valeurs du son en decibels",
                z,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel d = new ChartPanel(lineChart);
        d.setPreferredSize(new java.awt.Dimension(1000, 500));

        return lineChart;

    }

    /**
     * Fonction qui permet d'afficher different graph en fonction de quels
     * itemms du menu sur lesquels on clique.
     *
     * @param e coorespon à l'action de la souris sur un sous menu
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == pie) {

            DefaultPieDataset datasetPie = createDatasetPie();
            JFreeChart chart = createPie(datasetPie);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(1000, 500));
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
        }

        if (e.getSource() == histog) {

            DefaultCategoryDataset datasetHisto = createDatasetHisto();
            JFreeChart chart = createHisto(datasetHisto);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(1000, 500));
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
        }

        if (e.getSource() == tab) {
            FenetreRPI f = new FenetreRPI();
            f.setVisible(true);

        }
        if (e.getSource() == courbe) {

            DefaultCategoryDataset datasetCourbe = createDatasetCourbe();
            JFreeChart chart = createCourbe(datasetCourbe);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(1000, 500));
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
        }

        if (e.getSource() == btn_exit) {
            System.exit(0);
        }

        if (e.getSource() == run) {
            AnalogI2CInput.started = true;
            Collect c = new Collect();
            c.start();
            

        }

        if (e.getSource() == stop) {
            AnalogI2CInput.started = false;

        }

    }

    @Override
    public void focusGained(FocusEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

}
