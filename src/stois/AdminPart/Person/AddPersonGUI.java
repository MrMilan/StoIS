/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois.AdminPart.Person;

import Controller.InsurancesJpaController;
import Controller.PersonsJpaController;
import entity.Insurances;
import entity.Persons;
import java.awt.Component;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lukas
 */
public class AddPersonGUI extends javax.swing.JFrame {
    
    /**
     * Creates new form ToolGUI
     */
    private static EntityManagerFactory emf = null;
    private static List <Insurances> findInsurances = null;
    private static List <Persons> findPersons = null;
    private static boolean find = false;
    
    
    public AddPersonGUI(EntityManagerFactory emf) {
        this.emf = emf;
        initComponents();
        initInsurance();
        setDefaultCloseOperation(AddPersonGUI.DISPOSE_ON_CLOSE);
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
        jFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jButtConfirm = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBoxAdmin = new javax.swing.JCheckBox();
        jCheckBoxDoctor = new javax.swing.JCheckBox();
        jCheckBoxNurse = new javax.swing.JCheckBox();
        jCheckBoxPatient = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jSurname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboGender = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButtonAddress = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboInsur = new javax.swing.JComboBox();
        jBirthNumber = new javax.swing.JTextField();
        jTextAddress = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Add person");

        jFirstName.setText("User name");

        jLabel2.setText("First name:");

        jLabel3.setText("User role:");

        jButtConfirm.setText("Confirm");
        jButtConfirm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtConfirmMouseClicked(evt);
            }
        });

        jCheckBoxAdmin.setText("Admin");

        jCheckBoxDoctor.setText("Doctor");

        jCheckBoxNurse.setText("Nurse");

        jCheckBoxPatient.setText("Patient");

        jLabel4.setText("Surname:");

        jSurname.setText("Surname");

        jLabel5.setText("Gender:");

        jComboGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));

        jLabel6.setText("Birthnumber:");

        jLabel7.setText("Address:");

        jButtonAddress.setText("jButton2");
        jButtonAddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddressMouseClicked(evt);
            }
        });

        jLabel8.setText("Insurance:");

        jComboInsur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Insurance" }));

        jBirthNumber.setText("Birthnumber");

        jTextAddress.setText("Address");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSurname)
                                            .addComponent(jFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                            .addComponent(jComboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBirthNumber))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonAddress)
                                        .addGap(38, 38, 38)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jCheckBoxAdmin)
                                    .addComponent(jCheckBoxDoctor)
                                    .addComponent(jCheckBoxNurse)
                                    .addComponent(jCheckBoxPatient))
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboInsur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(jButtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBoxAdmin)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxDoctor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxNurse)
                    .addComponent(jBirthNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxPatient)
                    .addComponent(jLabel7)
                    .addComponent(jButtonAddress)
                    .addComponent(jTextAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jComboInsur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButtConfirmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtConfirmMouseClicked
        // TODO add your handling code here:
        
        PersonsJpaController perjc = new PersonsJpaController(emf); 
        
        String firstName = jFirstName.getText();
        String surName = jSurname.getText();
        String birthNum = jBirthNumber.getText();
        
        int numGen = jComboGender.getSelectedIndex();
        boolean gen = numGen == 0 ? true : false;
        
        // nalezeni pojistovny
        
        Insurances insurObject = (Insurances)jComboInsur.getSelectedItem();
        Integer insurID = insurObject.getInsuranceid();
        System.out.println(insurID);
        
        // zkontrolovani jestli neni jiz tento uzivtael uveden v databazi
        // naplneni listu 
        findPersons = perjc.findPersonsEntities();
        
        
        findPersons.stream().forEach((per) -> {
            if (per.getBirthnumber().equals(birthNum)){
                Component frame = new JFrame();
                        JOptionPane.showMessageDialog(frame,
                                "Person is already in the database",
                                "Add person error",
                                JOptionPane.ERROR_MESSAGE);
                find = true;        
            }   
            
        });
        
            if (find == false){
        // vytvoreni osoby
                Persons blb = new Persons(null,firstName,surName,gen,birthNum,false,false,findInsurances.get(insurID));
                perjc.create(blb);
              
        // prirazeni role osobe 
                boolean boolAdmin = jCheckBoxAdmin.isSelected();
                boolean boolDoc = jCheckBoxDoctor.isSelected();
                boolean boolNurse = jCheckBoxNurse.isSelected();
                boolean boolPatient = jCheckBoxPatient.isSelected();
            }
            
            else {
                find = false; 
            }
        
       
    }//GEN-LAST:event_jButtConfirmMouseClicked

    private void jButtonAddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddressMouseClicked
        // TODO add your handling code here:
        
        // zavolame si novy okno, kde budou vypsany vsechny asdresy a dotycny si zvoli, popripade tam pripda novou
        
        stois.AdminPart.Address.AddAddressGUI addAddresses = new stois.AdminPart.Address.AddAddressGUI(emf);
        addAddresses.setVisible(true);
        
        jTextAddress.setText(addAddresses.ChosenAdress);
        
    }//GEN-LAST:event_jButtonAddressMouseClicked

    private void initInsurance(){
        
        jComboInsur.removeAllItems();
        
        InsurancesJpaController ijc = new InsurancesJpaController(emf);
        
        findInsurances = ijc.findInsurancesEntities();
        
        findInsurances.stream().forEach((insur) -> {
            System.out.println(insur.toString());                               // vypsani pomoci toString
            jComboInsur.addItem(insur);                              // naplneni comboBoxu
        });
        
    
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jBirthNumber;
    private javax.swing.JButton jButtConfirm;
    private javax.swing.JButton jButtonAddress;
    private javax.swing.JCheckBox jCheckBoxAdmin;
    private javax.swing.JCheckBox jCheckBoxDoctor;
    private javax.swing.JCheckBox jCheckBoxNurse;
    private javax.swing.JCheckBox jCheckBoxPatient;
    private javax.swing.JComboBox jComboGender;
    private javax.swing.JComboBox jComboInsur;
    private javax.swing.JTextField jFirstName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jSurname;
    private javax.swing.JTextField jTextAddress;
    // End of variables declaration//GEN-END:variables
}