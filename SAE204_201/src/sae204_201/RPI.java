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
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * 
 *
 * @author Zineb
 * Classe qui permet de tester la lecture ainsi que l'ecriture de donnée  sur une base de donnée creer sur le RPI
 */
public class RPI extends JFrame implements ActionListener, FocusListener {

    Connection conexao = null;
    java.sql.Statement pst = null;
    ResultSet rs = null;
    JMenuItem pie;
    JMenuItem histog;
    JMenuItem tab;
    JMenuItem courbe;
    JMenuBar menubar;
    JMenu menu;
    
    /**
     * Constructeur qui permet de creer une fenetre principale avec un graph de
     * courbe, un menu qui nous permet de voir plusieurs graphique, ou bien de
     * voir une grille de données. Ce constructeur fait aussi appel a une
     * fonction qui permet de rajouter les valeurs issu d'une
     * base de donnée
     *
     * @param title corespond aux titre de l'application
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException
     */

    public RPI(String title) throws ClassNotFoundException, SQLException {

        super(title);

        //on cree donnee pour pie
        DefaultPieDataset datasetPie = createDatasetPie();

        //on cree donnée pour histo
        // DefaultCategoryDataset datasetHisto = createDatasetHisto();
        //on cree le graphe Pie
        JFreeChart chart = createPie(datasetPie);

        //on cree le graphe Histo
        //JFreeChart chart=createHisto(datasetHisto);
        //on cree un panel pour afficher le graph
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(500, 270));

        //on ajoute la graph au panel
        chartPanel.setChart(chart);

        //on affiche le graph
        setContentPane(chartPanel);

        menubar = new JMenuBar();
        menu = new JMenu("menu");

        pie = new JMenuItem("pie");
        histog = new JMenuItem("histo");
        tab = new JMenuItem("tab");
        courbe = new JMenuItem("courbe");

        menu.add(pie);
        menu.add(histog);
        menu.add(tab);
        menu.add(courbe);

        menubar.add(menu);

        this.setJMenuBar(menubar);
        this.setSize(300, 300);
        this.setLayout(null);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pie.addActionListener(this);
        histog.addActionListener(this);
        tab.addActionListener(this);
        courbe.addActionListener(this);

    }

     /**
     * Cette fonction nous permet de recuperer toute les valeurs issu de notre
     * base de donnée utile pour creer le graph circulaire 
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
            String strInsert = "SELECT * FROM table1";
            //String SQLeffectiftotal = "select  SUM(effectif) from Groupes where idGroupe like 'S_'";

            //statement
            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {

                //recup données de la table
                dataset.setValue(
                        rsLogin.getString("nom"), Double.parseDouble(rsLogin.getString(4)));

                //affichage
                System.out.println("---------------------------");
                System.out.println("nom " + rsLogin.getString("nom"));
                System.out.println("ville " + rsLogin.getString("ville"));
                System.out.println("dep " + rsLogin.getString("dep"));
                System.out.println("age " + rsLogin.getString("age"));

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
     * base de donnée utile pour creer un histogramme 
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
            // more processing here
            // ...

            //requete sql
            String strInsert = "SELECT * FROM table1";

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {

                //affichage
                System.out.println("---------------------------");
                System.out.println(rsLogin.getString(1));
                System.out.println(rsLogin.getString(4));

                //recup les donnée de la table
                d.addValue(Integer.parseInt(rsLogin.getString("age")), rsLogin.getString(1), "nom");

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
     * Fonction qui creer un graph circulair(pie) en fonction de données qu'elle
     * recoit.
     *
     * @param z coorespond aux données liées à la base de données
     * @return un graph circulaire.
     */
    private static JFreeChart createPie(DefaultPieDataset z) {

        //on recup les donées
        z = createDatasetPie();

        JFreeChart pieChart = ChartFactory.createPieChart3D("pie", z, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);

        // Adding chart into a chart panel
        ChartPanel c = new ChartPanel(pieChart);

        // settind default size
        c.setPreferredSize(new java.awt.Dimension(500, 370));

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

        //on recup les donées
        z = createDatasetHisto();

        JFreeChart lineChart = ChartFactory.createBarChart("titre", "age", "nom", z, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel d = new ChartPanel(lineChart);
        d.setPreferredSize(new java.awt.Dimension(500, 370));

        return lineChart;

    }

    
     /**
     * Fonction qui creer une courbe en fonction de données qu'elle recoit.
     *
     * @param z coorespond aux données liées à la base de données
     * @return une courbe
     */
    private static JFreeChart createCourbe(DefaultCategoryDataset z) {

        z = createDatasetHisto();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "titre",
                "Years", "Number of Schools",
                z,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel d = new ChartPanel(lineChart);
        d.setPreferredSize(new java.awt.Dimension(500, 370));

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

            //on cree donnee pour pie
            DefaultPieDataset datasetPie = createDatasetPie();

            //on cree donnée pour histo
            DefaultCategoryDataset datasetHisto = createDatasetHisto();

            //on cree le graphe Pie
            JFreeChart chart = createPie(datasetPie);

            //on cree le graphe Histo
            //JFreeChart chart=createHisto(datasetHisto);
            //on cree un panel pour afficher le graph
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(500, 270));

            //on ajoute la graph au panel
            chartPanel.setChart(chart);

            //on affiche le graph
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
        }

        if (e.getSource() == histog) {
            //on cree donnee pour pie
            DefaultPieDataset datasetPie = createDatasetPie();

            //on cree donnée pour histo
            DefaultCategoryDataset datasetHisto = createDatasetHisto();

            //on cree le graphe Pie
            //JFreeChart chart = createPie(datasetPie);
            //on cree le graphe Histo
            JFreeChart chart = createHisto(datasetHisto);

            //on cree un panel pour afficher le graph
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(500, 270));

            //on ajoute la graph au panel
            chartPanel.setChart(chart);

            //on affiche le graph
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();

        }

        if (e.getSource() == tab) {
            FenetreRPI f = new FenetreRPI();
            f.setVisible(true);

        }
        if(e.getSource()==courbe){
            //on cree donnee pour pie
            DefaultPieDataset datasetPie = createDatasetPie();

            //on cree donnée pour histo et courbe
            DefaultCategoryDataset datasetHisto = createDatasetHisto();
    
            //on cree le graphe Pie
            //JFreeChart chart = createPie(datasetPie);
            
            //on cree le graphe Histo
           // JFreeChart chart = createHisto(datasetHisto);
           
             //on cree le graphe courbe
            JFreeChart chart = createCourbe(datasetHisto);
           

            //on cree un panel pour afficher le graph
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(500, 270));

            //on ajoute la graph au panel
            chartPanel.setChart(chart);

            //on affiche le graph
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
          
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
