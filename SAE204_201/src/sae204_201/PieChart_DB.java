/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYDataset;

/**
 * Cette classe nous permet de lire des donnée depuis la base EDT, sur le resau
 * de l'IUT
 *
 * @author p2100028
 */
public class PieChart_DB extends JFrame implements ActionListener, FocusListener {

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
     * fonction qui permet de rajouter les valeurs issu d'une base de donnée
     *
     * @param title corespond aux titre de l'application
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     * @throws com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException
     */
    public PieChart_DB(String title) throws ClassNotFoundException, SQLException {
        super(title);
        DefaultPieDataset dataset = createDatasetPie();
        DefaultPieDataset datasetPie = createDatasetPie();
        // DefaultCategoryDataset datasetHisto = createDatasetHisto(); 
        JFreeChart chart = createPie(datasetPie);
        //JFreeChart chart=createHisto(datasetHisto);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(1000, 500));
        chartPanel.setChart(chart);
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

            Class.forName("com.mysql.cj.jdbc.Driver");
            // db parameters
            String url = "jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/EDT";
            String user = "p2100028";
            String password = "12100028";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //requete sql
            String strInsert = "SELECT * FROM Groupes";
            String SQLeffectiftotal = "select  SUM(effectif) from Groupes where idGroupe like 'S_'";
            String test[] = {"S1", "S2", "S3", "S4"};

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);

            while (rsLogin.next()) {

                dataset.setValue(
                        rsLogin.getString("idGroupe"), Double.parseDouble(rsLogin.getString("effectif")));

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
            // db parameters
            String url = "jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/EDT";
            String user = "p2100028";
            String password = "12100028";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            //requete sql
            String strInsert = "SELECT * FROM Groupes";
            String req = "select effectif,idGroupe from Groupes where idGroupe like 'S_' ";
            String SQLeffectiftotal = "select  SUM(effectif) from Groupes where idGroupe like 'S_'";
            String test[] = {"S1", "S2", "S3", "S4"};

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(req);

            while (rsLogin.next()) {
                //affichage
                System.out.println("---------------------------");
                System.out.println(rsLogin.getString(1));
                System.out.println(rsLogin.getString(2));

                d.addValue(Integer.parseInt(rsLogin.getString(1)), "eff", rsLogin.getString(2));

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

        z = createDatasetHisto();
        JFreeChart lineChart = ChartFactory.createBarChart("titre", "groupe", "effectif", z, PlotOrientation.VERTICAL, true, true, false);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.

        if (e.getSource() == pie) {

            DefaultPieDataset datasetPie = createDatasetPie();

            JFreeChart chart = createPie(datasetPie);

            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
        }

        if (e.getSource() == histog) {

            DefaultCategoryDataset datasetHisto = createDatasetHisto();

            JFreeChart chart = createHisto(datasetHisto);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setPreferredSize(new Dimension(500, 270));
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.pack();
            this.revalidate();
        }

        if (e.getSource() == tab) {
            try {
                Fenetre f = new Fenetre();
                f.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(PieChart_DB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PieChart_DB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == courbe) {
            //on cree donnee pour pie

            //on cree donnée pour histo et courbe
            DefaultCategoryDataset datasetHisto = createDatasetHisto();

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
