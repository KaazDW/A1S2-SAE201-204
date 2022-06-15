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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

/**
 *
 * @author p2100028
 */
public class JFreeChartExample extends JFrame {

   private static final long serialVersionUID = 1L;

  public JFreeChartExample(String applicationTitle, String chartTitle) {
      //super(applicationTitle);
   /*    super(applicationTitle);
      
      
        //Creates a sample dataset
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("Chrome", 29);
        dataSet.setValue("InternetExplorer", 36);
        dataSet.setValue("Firefox", 35);
        
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("menu");
        
        JMenuItem pie = new JMenuItem("pie");
        JMenuItem histog = new JMenuItem("histo");
        
        menu.add(pie);
        menu.add(histog);
        
        menubar.add(menu);
        this.setJMenuBar(menubar);
        this.setSize(300,300);
        this.setLayout(null);
        this.setVisible(true);

        HistogramDataset dataset = new HistogramDataset();
         //dataset.setType(HistogramType.RELATIVE_FREQUENCY);
         double[] values = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
         dataset.addSeries("H1", values, 10, 0.0, 10.0); 
         
         JFreeChart histo = ChartFactory.createHistogram(chartTitle, "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false);
   
        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createPieChart3D(chartTitle, dataSet, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);

        // Adding chart into a chart panel
        ChartPanel c = new ChartPanel(pieChart);
          c = new ChartPanel(histo);
       
        // settind default size
        c.setPreferredSize(new java.awt.Dimension(500, 370));
        //h.setPreferredSize(new java.awt.Dimension(500, 370));
        // add to contentPane
        setContentPane(c);
        //setContentPane(h);


 */
       
       
          Connection conn = null;
          String i, ii="", iii,iiii;
          
          
           
         
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
             String test[]={"S1", "S2","S3","S4"};

            java.sql.Statement stLogin = conn.createStatement();
            ResultSet rsLogin = stLogin.executeQuery(strInsert);
            
            DefaultPieDataset dataset = new DefaultPieDataset( );
        
        
      //  DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            while (rsLogin.next()) {
               String ligne[]={rsLogin.getString("idGroupe"),rsLogin.getString("effectif"),rsLogin.getString("type"),
                    rsLogin.getString("parent")};
             //   model.addRow(ligne);
             
               dataset.setValue( 
         rsLogin.getString( "idGroupe" ) ,Double.parseDouble( rsLogin.getString( "effectif" )) );
      
             
                  System.out.println("---------------------------");
                System.out.println("Id groupe "+rsLogin.getString("idGroupe"));
                System.out.println("effectif "+rsLogin.getString("effectif"));
                System.out.println("type "+rsLogin.getString("type"));
                System.out.println("parent "+rsLogin.getString("parent"));
            
     
 
            }
           //  DefaultPieDataset dataSet = new DefaultPieDataset();
      
            
            //JFreeChart pieChart = ChartFactory.createPieChart3D(chartTitle, dataSet, true, true, false);
            
            JFreeChart chart = ChartFactory.createPieChart(
         "Graphe",   // chart title           
         dataset,          // data           
         true,             // include legend          
         true,           
         false );
            
            
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);
        
        ChartPanel c = new ChartPanel(chart);
         
         c.setPreferredSize(new java.awt.Dimension(500, 370));
         c.setVisible(true);
         setContentPane(c);
             
         
       // rsLogin.close();
   
   
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
