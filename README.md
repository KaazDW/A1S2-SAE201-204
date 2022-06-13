# A1S2-SAE201-204

### Set Up MARIA DB
> https://info.blaisepascal.fr/rpi-installer-un-sgbd
> https://raspberrytips.com/install-mariadb-raspberry-pi/

### Set Up Postgres
> https://opensource.com/article/17/10/set-postgres-database-your-raspberry-pi



iutbg-lamp.univ-lyon1.fr/edt
installer wpagui
sudo wpa-gui








### JFreeChart DOC
> https://codes-sources.commentcamarche.net/source/51950-creer-des-graphiques-utilisation-de-jfreechart
<br />
Exemple de code JFreeChart : <br />
- https://www.javatips.net/blog/jfreechart-example

```
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


public class JFreeChartExample extends JFrame {

  private static final long serialVersionUID = 1L;

  public JFreeChartExample(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        //Creates a sample dataset
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("Chrome", 29);
        dataSet.setValue("InternetExplorer", 36);
        dataSet.setValue("Firefox", 35);
       
        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createPieChart3D(chartTitle, dataSet, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
       
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
       
        // add to contentPane
        setContentPane(chartPanel);
    }
    public static void main(String[] args) {
      JFreeChartExample chart = new JFreeChartExample("Browser Usage Statistics", "Which Browser are you using?");
        chart.pack();
        chart.setVisible(true);
    }
}
```
