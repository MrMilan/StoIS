/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois.AdminPart.Report;

import Controller.ActionsJpaController;
import Controller.DiagnosisJpaController;
import Controller.ReportsJpaController;
import entity.Actions;
import entity.Diagnosis;
import entity.Persons;
import entity.Reports;
import java.awt.Component;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Milhouse
 */
public class AddReportGUI extends javax.swing.JFrame {

    private EntityManagerFactory emf = null;
    private static List<Actions> actionList = null;
    private static List<Diagnosis> diagnoseList = null;

    /**
     * Creates new form AddReport
     */
    public AddReportGUI(EntityManagerFactory emf) {
        this.emf = emf;
        initComponents();
        getDataFromDatabase();
        setDefaultCloseOperation(AddReportGUI.DISPOSE_ON_CLOSE);
    }

    private void getDataFromDatabase() {
        listActions.removeAll();
        listDiagnosis.removeAll();
//        ActionsJpaController ajc = new ActionsJpaController(emf);
//        actionList = ajc.findActionsOperations();
//        actionList.stream().forEach((curAct) -> {
//            listActions.add(curAct.getOperationsOperationsid().toString());
//        });

        DiagnosisJpaController djc = new DiagnosisJpaController(emf);
        diagnoseList = djc.findDiagnosisEntities();
        diagnoseList.stream().forEach((curDct) -> {
            listDiagnosis.add(curDct.toString());
        });

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
        jSeparator1 = new javax.swing.JSeparator();
        jTxtFNote = new javax.swing.JTextField();
        listDiagnosis = new java.awt.List();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        listActions = new java.awt.List();
        jLabel4 = new javax.swing.JLabel();
        jBtnCreateReport = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Create Report");

        jTxtFNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFNoteActionPerformed(evt);
            }
        });

        jLabel2.setText("Note");

        jLabel3.setText("Actions list");

        jLabel4.setText("Diagnosis list");

        jBtnCreateReport.setText("Create report");
        jBtnCreateReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnCreateReportMouseClicked(evt);
            }
        });
        jBtnCreateReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCreateReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtFNote, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jBtnCreateReport))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listActions, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(3, 3, 3)
                        .addComponent(jTxtFNote, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(listActions, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(listDiagnosis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jBtnCreateReport)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxtFNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFNoteActionPerformed

    private void jBtnCreateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCreateReportActionPerformed
        // TODO add your handling code here:

        createReport();
    }//GEN-LAST:event_jBtnCreateReportActionPerformed

    private void jBtnCreateReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCreateReportMouseClicked
        // TODO add your handling code here:

        createReport();
    }//GEN-LAST:event_jBtnCreateReportMouseClicked

    private void createReport() {

        String note = jTxtFNote.getText();
        Actions akcicka = actionList.get(listActions.getSelectedIndex());
        ActionsJpaController ajc = new ActionsJpaController(emf);
        List<Actions> acters = ajc.findActers(akcicka.getOperationsOperationsid().getOperationsid());

        Diagnosis diagnos = diagnoseList.get(listDiagnosis.getSelectedIndex());

        Reports reportek = new Reports();
        reportek.setDate(Date.from(Instant.now()));
        reportek.setNote(note);
        reportek.setDiagnosisDiagnoseid(diagnos);
        //        reportek.setActionsCollection(acters);

        ReportsJpaController rjp = new ReportsJpaController(emf);
        try {
            rjp.create(reportek);
        } catch (Exception e) {
            Component frame = new JFrame();
            JOptionPane.showMessageDialog(frame,
                    "Connection to database or creating report in database failed",
                    "Inane error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

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
            java.util.logging.Logger.getLogger(AddReportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddReportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddReportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddReportGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddReportGUI().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCreateReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTxtFNote;
    private java.awt.List listActions;
    private java.awt.List listDiagnosis;
    // End of variables declaration//GEN-END:variables
}
