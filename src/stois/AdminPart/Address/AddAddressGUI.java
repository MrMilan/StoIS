/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois.AdminPart.Address;

import stois.AdminPart.Person.AddPersonGUI;
import Controller.AddressesJpaController;
import Controller.ToolsJpaController;
import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import entity.Addresses;
import entity.Tools;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.eclipse.persistence.internal.helper.Helper;
import stois.AdminPart.Person.AddPersonGUI;

/**
 *
 * @author Lukas
 */
public class AddAddressGUI extends javax.swing.JFrame {

    /**
     * Creates new form ToolGUI
     */
    
    private static EntityManagerFactory emf = null;
    private static List <Addresses> AddressesList = null;
    private static boolean find = false;
    
    // globalni prommenou kterou si pak predam do predchozioh okna
    public String ChosenAdress = null; 
    
    public AddAddressGUI(EntityManagerFactory emf) {
        this.emf = emf;
        initComponents();
        updateDatabase();
        setDefaultCloseOperation(AddAddressGUI.DISPOSE_ON_CLOSE);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextStreet = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        JButtAdd = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtChoose = new javax.swing.JButton();
        list1 = new java.awt.List();
        jLabel3 = new javax.swing.JLabel();
        jTextStreetNum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextZip = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextCity = new javax.swing.JTextField();
        jTextCountry = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Addresses");

        jTextStreet.setText("Street");

        jLabel2.setText("Addresses");

        JButtAdd.setText("ADD");
        JButtAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtAddMouseClicked(evt);
            }
        });

        jLabel4.setText("ADD");

        jButtChoose.setText("Choose");
        jButtChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtChooseMouseClicked(evt);
            }
        });

        jLabel3.setText("Street:");

        jTextStreetNum.setText("Street Number");
        jTextStreetNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextStreetNumActionPerformed(evt);
            }
        });

        jLabel6.setText("Street Number:");

        jLabel5.setText("Zip code:");

        jTextZip.setText("Zip code");
        jTextZip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextZipActionPerformed(evt);
            }
        });

        jLabel7.setText("City:");

        jLabel8.setText("Country:");

        jTextCity.setText("City");
        jTextCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCityActionPerformed(evt);
            }
        });

        jTextCountry.setText("Country");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(3, 3, 3)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextZip, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                                        .addComponent(jTextCity)
                                                        .addComponent(jTextCountry)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(22, 22, 22)
                                                    .addComponent(jTextStreetNum, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(jTextStreet)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(80, 80, 80)
                                            .addComponent(jLabel4)))
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jButtChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JButtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextStreetNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JButtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JButtAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JButtAddMouseClicked
        // TODO add your handling code here:
        // naplneni listu vsemci jiz vytvorenymi adresami   
        
        AddressesJpaController adrjc = new AddressesJpaController(emf); 
        AddressesList = adrjc.findAddressesEntities();
        
        // vytahnuti si dat z textfieldu
        String street = jTextStreet.getText();
        
        int streetNum  = Integer.valueOf(jTextStreetNum.getText());  
        
        
        // to same pro PSC
        int zipCode = Integer.parseInt(jTextZip.getText());  
        
        
        String city = jTextCity.getText();
        String country = jTextCountry.getText();
        
        
        
        // prochazeni jiz vytvorenych adres a budu porovnavat kjednotlive obejkty
        AddressesList.stream().forEach((curAddr) -> {
            if(curAddr.getStreetnumber()==streetNum && curAddr.getZipcode()==zipCode) {
                    Component frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,
                                "Address is already in the database",
                                "Add address error",
                                JOptionPane.ERROR_MESSAGE);
                    find = true;  
            }
        });
        
        // podminka pro pridani adresy
        
        if(find == false){
            Addresses newAdress = new Addresses(null, street, streetNum, zipCode, city, country, false);
            adrjc.create(newAdress);
        }
        else{
            find = false;
        }
        
        // updatovani databaze
        updateDatabase();
    }//GEN-LAST:event_JButtAddMouseClicked

    private void updateDatabase(){
    
        //vymazani listu
        list1.removeAll();
        
        AddressesJpaController adrjc = new AddressesJpaController(emf); 
        AddressesList = adrjc.findAddressesEntities();
        
        AddressesList.stream().forEach((curAddr) -> {
            list1.add(curAddr.toString());
        });
        
    }
    
    
    private void jButtChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtChooseMouseClicked
        // TODO add your handling code here:
        
        int index = list1.getSelectedIndex();
        AddPersonGUI mySuper = new AddPersonGUI(AddressesList.get(index), list1.getSelectedItem());   
        setVisible(false);
        //mySuper.setMyAddress(AddressesList.get(index-1));
        //mySuper.initText();
        //mySuper.initText(AddressesList.get(index-1));
       
    }//GEN-LAST:event_jButtChooseMouseClicked

    private void jTextStreetNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextStreetNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextStreetNumActionPerformed

    private void jTextZipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextZipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextZipActionPerformed

    private void jTextCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextCityActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(AddAddressGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddAddressGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddAddressGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddAddressGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteToolGUI(emf).setVisible(true);
            }
        });
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeleteToolGUI(emf).setVisible(true);
            }
        });
        */
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtAdd;
    private javax.swing.JButton jButtChoose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextCity;
    private javax.swing.JTextField jTextCountry;
    private javax.swing.JTextField jTextStreet;
    private javax.swing.JTextField jTextStreetNum;
    private javax.swing.JTextField jTextZip;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
