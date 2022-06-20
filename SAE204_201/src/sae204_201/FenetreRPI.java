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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zineb Cette class permet de generer une fenetre sur laquelle on
 * pourra effectuer tout les fonctionnalité lié à une base de données.
 */
public class FenetreRPI extends javax.swing.JFrame {

    java.sql.Statement stLogin;
    ResultSet rsLogin;
    DefaultTableModel model;

    /**
     * Constructeur qui permet de initialiser une fenetre avec une grille et
     * different boutons lié à la gestion de la base de donnée
     */
    public FenetreRPI() {
        initComponents();

        Connection conn = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Sae";
            String user = "admin0";
            String password = "0000";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // requete sql
            String strInsert = "SELECT * FROM sonsalle";

            //statement
            stLogin = conn.createStatement();
            rsLogin = stLogin.executeQuery(strInsert);

            model = (DefaultTableModel) jTable1.getModel();

            while (rsLogin.next()) {
                String ligne[] = {rsLogin.getString("id"), rsLogin.getString("temps"), rsLogin.getString("valSons"),
                    rsLogin.getString("pourcentage")};
                model.addRow(ligne);

                //affichage
                System.out.println("---------------------------");
                System.out.println("id " + rsLogin.getString(1));
                System.out.println("temps" + rsLogin.getString(2));
                System.out.println("valSons " + rsLogin.getString(3));
                System.out.println("pourcentage" + rsLogin.getString(4));

            }

            rsLogin.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver non chargé !" + e);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        SuppButton = new javax.swing.JButton();
        idUI = new javax.swing.JTextField();
        tempsUI = new javax.swing.JTextField();
        valUI = new javax.swing.JTextField();
        pourUI = new javax.swing.JTextField();
        ajoutButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        quit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        nameUI = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        passwordUI = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        choixUI = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "temps", "valSons", "pourcentage"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        SuppButton.setText("SUPP");
        SuppButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppButtonActionPerformed(evt);
            }
        });

        idUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idUIActionPerformed(evt);
            }
        });

        ajoutButton.setText("ajouter");
        ajoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("id");

        jLabel2.setText("temps");

        jLabel3.setText("valSons");

        jLabel4.setText("pourcentage");

        quit.setText("QUIT");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });

        jLabel5.setText("Name");

        nameUI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameUIActionPerformed(evt);
            }
        });

        jLabel6.setText("ADD USER");

        jLabel7.setText("Password");

        jLabel8.setText("Role");

        choixUI.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(SuppButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(quit)
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(idUI, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tempsUI, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(valUI, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(pourUI, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(ajoutButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameUI, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(passwordUI)
                                        .addComponent(choixUI, 0, 119, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(addButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SuppButton)
                    .addComponent(quit))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(idUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempsUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(valUI, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pourUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ajoutButton)
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nameUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(passwordUI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choixUI, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idUIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idUIActionPerformed

    /**
     * Fonction qui permet de supprimer un element de la base de donnée lié aux
     * capteur de sons
     *
     * @param evt correspond à un click sur un bouton
     */
    private void SuppButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppButtonActionPerformed
        try {
            // TODO add your handling code here:
            int numRowsSelected = jTable1.getSelectedRow();

            if (numRowsSelected == -1) {
                JOptionPane.showMessageDialog(this, "Selectionner une ligne");
                return;
            }

            String id = jTable1.getValueAt(numRowsSelected, 0).toString();
            //supp de la base
            stLogin.executeUpdate("DELETE FROM sonsalle where id=" + id);

            //supp de l'interface
            model.removeRow(numRowsSelected);
        } catch (SQLException ex) {
            Logger.getLogger(FenetreRPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SuppButtonActionPerformed

    /**
     * Fonction qui permet d'ajouter un element de la base de donnée lié aux
     * capteur de sons
     *
     * @param evt correspond à un click sur un bouton
     */
    private void ajoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutButtonActionPerformed
        // TODO add your handling code here:
        try {
            // ajout dans bd
            stLogin.executeUpdate("INSERT INTO sonsalle (id,temps,valSons,pourcentage) values (" + idUI.getText() + ",'" + tempsUI.getText() + "','"
                    + valUI.getText() + "','" + pourUI.getText() + "');");

            // ajout dans interface
            String[] ligne = {"" + idUI.getText(), tempsUI.getText(), valUI.getText(), pourUI.getText()};
            model.addRow(ligne);
        } catch (Exception ex) {

            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ajoutButtonActionPerformed

    /**
     * Fonction qui permet de quitter la fenetre
     *
     * @param evt correspond à un click sur un bouton
     */
    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.revalidate();

    }//GEN-LAST:event_quitActionPerformed

    private void nameUIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameUIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameUIActionPerformed

    /**
     * Fonction quii permet de créer certain utilisateurs avec certains droits
     *
     * @param evt correspond à un click sur un bouton
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:

        String choix = choixUI.getSelectedItem().toString();

        if (choix == "1") {
            try {
                stLogin.executeUpdate("CREATE USER " + nameUI.getText() + " IDENTIFIED BY '" + passwordUI.getText() + "';");
                stLogin.executeUpdate("GRANT ALL PRIVILEGES ON Sae.* TO '" + nameUI.getText() + "' WITH GRANT OPTION;");
                // stLogin.executeUpdate("INSERT INTO usertable (id,username,password,droit) values (0,'" + nameUI.getText()+ "','" + passwordUI.getText()+   
                //       "','" + choix +"');");
            } catch (SQLException ex) {
                Logger.getLogger(FenetreRPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (choix == "2") {
            try {
                stLogin.executeUpdate("CREATE USER " + nameUI.getText() + " IDENTIFIED BY '" + passwordUI.getText() + "';");
                stLogin.executeUpdate("GRANT INSERT, UPDATE, SELECT PRIVILEGES ON Sae.* TO '" + nameUI.getText() + "' WITH GRANT OPTION;");
                // stLogin.executeUpdate("INSERT INTO usertable (id,username,password,droit) values (0,'" + nameUI.getText()+ "','" + passwordUI.getText()+   
                //        "','" + choix +"');");
            } catch (SQLException ex) {
                Logger.getLogger(FenetreRPI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (choix == "3") {
            try {
                stLogin.executeUpdate("CREATE USER " + nameUI.getText() + " IDENTIFIED BY '" + passwordUI.getText() + "';");
                stLogin.executeUpdate("GRANT SELECT ON Sae.* TO '" + nameUI.getText() + "' ;");
                stLogin.executeUpdate("INSERT INTO usertable (id,username,password,droit) values ('" + rsLogin.getString("id") + 3 + "','" + nameUI.getText() + "','" + passwordUI.getText()
                        + "','3');");
            } catch (SQLException ex) {
                Logger.getLogger(FenetreRPI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_addButtonActionPerformed

    /**Fonction principale qui permet de lancer la fenetre
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreRPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreRPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreRPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreRPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreRPI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SuppButton;
    private javax.swing.JButton addButton;
    private javax.swing.JButton ajoutButton;
    private javax.swing.JComboBox<String> choixUI;
    private javax.swing.JTextField idUI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nameUI;
    private javax.swing.JTextField passwordUI;
    private javax.swing.JTextField pourUI;
    private javax.swing.JButton quit;
    private javax.swing.JTextField tempsUI;
    private javax.swing.JTextField valUI;
    // End of variables declaration//GEN-END:variables
}
