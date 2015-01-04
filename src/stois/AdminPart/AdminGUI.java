/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois.AdminPart;

import entity.Users;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import stois.AdminPart.User.AddUserGUI;
import stois.AdminPart.User.ChangePasswordGUI;
import stois.login.Loginform;

/**
 *
 * @author Milhouse
 */
public class AdminGUI extends javax.swing.JFrame {

    /**
     * Creates new form AdminGUI
     */
    private EntityManagerFactory emf = null;
    private static Users userEntity = new Users();

//    public AdminGUI() {
//        initComponents();
//        try {
//            emf = Persistence.createEntityManagerFactory("StoISPU");
//        } catch (Exception e) {
//            System.err.println("No connection to database");
//        } 
//        setDefaultCloseOperation(AdminGUI.DISPOSE_ON_CLOSE);
//    }
    public AdminGUI(EntityManagerFactory emf, Users user) {
        this.emf = emf;
        this.userEntity = user;
        initComponents();
        setDefaultCloseOperation(AdminGUI.EXIT_ON_CLOSE);
    }

    public AdminGUI() {
    }

    public Users getUser() {
        return this.userEntity;
    }

    public void setUser(Users user) {
        this.userEntity = user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jButtPersonAdd = new javax.swing.JButton();
        jButtPersonEdit = new javax.swing.JButton();
        jButtRepExp = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtMaterAdd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtToolAdd = new javax.swing.JButton();
        jButtMaterDel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtOperAdd = new javax.swing.JButton();
        jButtOperDel = new javax.swing.JButton();
        jButtDiagAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButtToolDel = new javax.swing.JButton();
        jButtDiagDel = new javax.swing.JButton();
        jButtOperList = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButtAdminPsw = new javax.swing.JButton();
        jButtAdminLogoff = new javax.swing.JButton();
        jBtnUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StoIS Admin Portal");

        jButtPersonAdd.setText("Add person");
        jButtPersonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtPersonAddActionPerformed(evt);
            }
        });

        jButtPersonEdit.setText("Edit person");
        jButtPersonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtPersonEditActionPerformed(evt);
            }
        });

        jButtRepExp.setText("Export");
        jButtRepExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtRepExpActionPerformed(evt);
            }
        });

        jLabel1.setText("Persons");

        jLabel2.setText("Reports");

        jButtMaterAdd.setText("Add material");
        jButtMaterAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtMaterAddMouseClicked(evt);
            }
        });

        jLabel3.setText("Materials");

        jLabel4.setText("Tools");

        jButtToolAdd.setText("Add tool");
        jButtToolAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtToolAddMouseClicked(evt);
            }
        });

        jButtMaterDel.setText("Cancel material");
        jButtMaterDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtMaterDelMouseClicked(evt);
            }
        });

        jLabel5.setText("Operations");

        jButtOperAdd.setText("Add operation");
        jButtOperAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtOperAddActionPerformed(evt);
            }
        });

        jButtOperDel.setText("Cancel operation");
        jButtOperDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtOperDelMouseClicked(evt);
            }
        });

        jButtDiagAdd.setText("Add diagnosis");
        jButtDiagAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtDiagAddMouseClicked(evt);
            }
        });

        jLabel6.setText("Diagnosis");

        jButtToolDel.setText("Cancel tool");
        jButtToolDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtToolDelActionPerformed(evt);
            }
        });

        jButtDiagDel.setText("Cancel diagnosis");
        jButtDiagDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtDiagDelMouseClicked(evt);
            }
        });
        jButtDiagDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtDiagDelActionPerformed(evt);
            }
        });

        jButtOperList.setText("Operation list");
        jButtOperList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtOperListMouseClicked(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Admin");

        jButtAdminPsw.setText("Change password");
        jButtAdminPsw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtAdminPswMouseClicked(evt);
            }
        });
        jButtAdminPsw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtAdminPswActionPerformed(evt);
            }
        });

        jButtAdminLogoff.setText("Log off");
        jButtAdminLogoff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtAdminLogoffMouseClicked(evt);
            }
        });
        jButtAdminLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtAdminLogoffActionPerformed(evt);
            }
        });

        jBtnUser.setText("Add username");
        jBtnUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel3)
                                .addGap(142, 142, 142)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jButtAdminPsw)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButtAdminLogoff, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jButtPersonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtPersonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(42, 42, 42))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(187, 187, 187)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(177, 177, 177)
                                                .addComponent(jLabel1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(171, 171, 171)
                                                .addComponent(jBtnUser)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtToolAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtRepExp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtToolDel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel4)
                                .addGap(71, 71, 71)))))
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtMaterDel, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jButtMaterAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtOperDel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtOperList, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtOperAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtDiagDel, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtDiagAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtAdminPsw)
                                .addComponent(jButtAdminLogoff))
                            .addGap(14, 14, 14)
                            .addComponent(jBtnUser)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtPersonAdd)
                                .addComponent(jButtPersonEdit))
                            .addGap(24, 24, 24)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtRepExp)
                                .addGap(66, 66, 66)))
                        .addComponent(jButtToolAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtToolDel)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButtOperAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtOperDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtOperList))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButtMaterAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtMaterDel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButtDiagAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtDiagDel)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtPersonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtPersonAddActionPerformed
        // TODO add your handling code here:
        stois.AdminPart.Person.AddPersonGUI addPerson = new stois.AdminPart.Person.AddPersonGUI(emf);
        addPerson.setVisible(true);
    }//GEN-LAST:event_jButtPersonAddActionPerformed

    private void jButtPersonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtPersonEditActionPerformed
        // TODO add your handling code here:
        stois.AdminPart.Person.EditPersonGUI1 editPerson = new stois.AdminPart.Person.EditPersonGUI1(emf);
        editPerson.setVisible(true);
    }//GEN-LAST:event_jButtPersonEditActionPerformed

    private void jButtRepExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtRepExpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtRepExpActionPerformed

    private void jButtToolDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtToolDelActionPerformed
        // TODO add your handling code here:
        stois.AdminPart.Tool.DeleteToolGUI deleteTool = new stois.AdminPart.Tool.DeleteToolGUI(emf);
        deleteTool.setVisible(true);
    }//GEN-LAST:event_jButtToolDelActionPerformed

    private void jButtOperAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtOperAddActionPerformed
        // TODO add your handling code here:
        stois.AdminPart.Operation.AddOperationlGUI addOper = new stois.AdminPart.Operation.AddOperationlGUI(emf);
        addOper.setVisible(true);
    }//GEN-LAST:event_jButtOperAddActionPerformed

    private void jButtMaterAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtMaterAddMouseClicked
        // TODO add your handling code here:
        stois.AdminPart.Material.AddMaterialGUI addMater = new stois.AdminPart.Material.AddMaterialGUI(emf);
        addMater.setVisible(true);

    }//GEN-LAST:event_jButtMaterAddMouseClicked

    private void jButtMaterDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtMaterDelMouseClicked
        // TODO add your handling code here:
        stois.AdminPart.Material.DeleteMaterialGUI deleteMater = new stois.AdminPart.Material.DeleteMaterialGUI(emf);
        deleteMater.setVisible(true);

    }//GEN-LAST:event_jButtMaterDelMouseClicked

    private void jButtToolAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtToolAddMouseClicked
        // TODO add your handling code here:
        stois.AdminPart.Tool.AddToolGUI addTool = new stois.AdminPart.Tool.AddToolGUI(emf);
        addTool.setVisible(true);

    }//GEN-LAST:event_jButtToolAddMouseClicked

    private void jButtAdminPswMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtAdminPswMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtAdminPswMouseClicked

    private void jButtAdminLogoffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtAdminLogoffMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtAdminLogoffMouseClicked

    private void jButtOperDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtOperDelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtOperDelMouseClicked

    private void jButtOperListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtOperListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtOperListMouseClicked

    private void jButtDiagAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtDiagAddMouseClicked
        // TODO add your handling code here:
        stois.AdminPart.Diagnosis.AddDiagnosislGUI addDiag = new stois.AdminPart.Diagnosis.AddDiagnosislGUI(emf);
        addDiag.setVisible(true);

    }//GEN-LAST:event_jButtDiagAddMouseClicked

    private void jButtDiagDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtDiagDelMouseClicked
        // TODO add your handling code here:
        stois.AdminPart.Diagnosis.DeleteDiagnosisGUI delDiag = new stois.AdminPart.Diagnosis.DeleteDiagnosisGUI(emf);
        delDiag.setVisible(true);
    }//GEN-LAST:event_jButtDiagDelMouseClicked

    private void jButtDiagDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtDiagDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtDiagDelActionPerformed

    private void jButtAdminPswActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtAdminPswActionPerformed
        // TODO add your handling code here:
        stois.AdminPart.User.ChangePasswordGUI pawd = new ChangePasswordGUI(emf, userEntity);
        pawd.setVisible(true);
    }//GEN-LAST:event_jButtAdminPswActionPerformed

    private void jBtnUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUserActionPerformed
        // TODO add your handling code here:
        stois.AdminPart.User.AddUserGUI auD = new AddUserGUI(emf);
        auD.setVisible(true);
    }//GEN-LAST:event_jBtnUserActionPerformed

    private void jButtAdminLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtAdminLogoffActionPerformed
        // TODO add your handling code here:
        stois.login.Loginform lf = new Loginform(emf);
        lf.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButtAdminLogoffActionPerformed

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
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new AdminGUI().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnUser;
    private javax.swing.JButton jButtAdminLogoff;
    private javax.swing.JButton jButtAdminPsw;
    private javax.swing.JButton jButtDiagAdd;
    private javax.swing.JButton jButtDiagDel;
    private javax.swing.JButton jButtMaterAdd;
    private javax.swing.JButton jButtMaterDel;
    private javax.swing.JButton jButtOperAdd;
    private javax.swing.JButton jButtOperDel;
    private javax.swing.JButton jButtOperList;
    private javax.swing.JButton jButtPersonAdd;
    private javax.swing.JButton jButtPersonEdit;
    private javax.swing.JButton jButtRepExp;
    private javax.swing.JButton jButtToolAdd;
    private javax.swing.JButton jButtToolDel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
