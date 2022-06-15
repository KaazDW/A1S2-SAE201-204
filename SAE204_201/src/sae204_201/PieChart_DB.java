/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae204_201;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * VERSION FINALE
 *
 * @author p2100028
 */
public class PieChart_DB extends JFrame implements ActionListener {

    Connection conexao = null;
    java.sql.Statement pst = null;
    ResultSet rs = null;
    JMenuBar menubar;
    JMenuItem dbmenu;
    JMenu menu;
    JMenuItem pie;
    JMenuItem histogramme;

    public PieChart_DB(String title) throws ClassNotFoundException, SQLException {
        super(title);
        DefaultPieDataset dataPie = createDatasetPie();
        DefaultCategoryDataset dataHistogramme = createDatasetHisto();
        // JFreeChart chart = createPie(dataPie);
        JFreeChart chart = createHisto(dataHistogramme);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        //chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setChart(chart);
        setContentPane(chartPanel);

        //définition window settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        menubar = new JMenuBar();
        //MenuBar From DB - creation
        menu = new JMenu("From DB");
        dbmenu = new JMenuItem("DB menu");

        pie = new JMenuItem("PieChart");
        histogramme = new JMenuItem("Histogramme");

        //MenuBar From DB - initialisation et action listener
        menubar.add(menu);
        menubar.add(dbmenu);
        dbmenu.addActionListener(this);
        menu.add(pie);
        pie.addActionListener(this);
        menu.add(histogramme);
        histogramme.addActionListener(this);

        this.setJMenuBar(menubar);
//        this.setSize(300, 300);
        this.setLayout(null);
        this.setVisible(true);

    }

    private static DefaultPieDataset createDatasetPie() {

        Connection conn = null;

        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            // driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // driver parameters
            String url = "jdbc:mysql://iutbg-lamp.univ-lyon1.fr:3306/EDT";
            String user = "p2100028";
            String password = "12100028";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // requete sql
            String strInsert = "SELECT * FROM Groupes";
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

    private static DefaultCategoryDataset createDatasetHisto() {
        Connection conn = null;
        DefaultCategoryDataset data = new DefaultCategoryDataset();

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

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(req);

            while (rsLogin.next()) {
                //affichage
                System.out.println("---------------------------");
                System.out.println(rsLogin.getString(1));
                System.out.println(rsLogin.getString(2));
                // System.out.println("Id groupe "+rsLogin.getString("idGroupe"));
                // System.out.println("effectif "+rsLogin.getString("effectif"));
                // System.out.println("type "+rsLogin.getString("type"));
                // System.out.println("parent "+rsLogin.getString("parent"));

                //ajout valeur pour histogramme  
                data.addValue(Integer.parseInt(rsLogin.getString(1)), rsLogin.getString(2), "eff");
            }
            if (conn != null) {
                conn.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver non chargé !" + e);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return data;
    }

    private static JFreeChart createPie(DefaultPieDataset z) {

        //recup les données
        z = createDatasetPie();

        //creation du graph
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

    private static JFreeChart createHisto(DefaultCategoryDataset z) {
        //recup les données
        z = createDatasetHisto();
        //creation du graph
        JFreeChart lineChart = ChartFactory.createBarChart("titre", "groupe", "effectif", z, PlotOrientation.VERTICAL, true, true, false);
        return lineChart;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pie) {

            DefaultPieDataset dataPie = createDatasetPie();
            JFreeChart chart = createPie(dataPie);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.revalidate();
        }

        if (e.getSource() == histogramme) {

            DefaultPieDataset dataPie = createDatasetPie();
            DefaultCategoryDataset dataHistogramme = createDatasetHisto();
            JFreeChart chart = createHisto(dataHistogramme);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setChart(chart);
            setContentPane(chartPanel);
            this.revalidate();
        }

        if (e.getSource() == dbmenu) {
            try {
                Fenetre f = new Fenetre();
                f.setVisible(true);

            } catch (SQLException ex) {
                Logger.getLogger(PieChart_DB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PieChart_DB.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
