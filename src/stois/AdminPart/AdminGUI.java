/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois.AdminPart;

/**
 *
 * @author Milhouse
 */
public class AdminGUI extends javax.swing.JFrame {

    /**
     * Creates new form AdminGUI
     */
    public AdminGUI() {
        initComponents();
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
        jButtUserAdd = new javax.swing.JButton();
        jButtUserDel = new javax.swing.JButton();
        jButtRepExp = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtUserList = new javax.swing.JButton();
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
        jButtToolList = new javax.swing.JButton();
        jButtDiagDel = new javax.swing.JButton();
        jButtDiagList = new javax.swing.JButton();
        jButtMaterList = new javax.swing.JButton();
        jButtOperList = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButtAdminPsw = new javax.swing.JButton();
        jButtAdminLogoff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtUserAdd.setText("Add user");
        jButtUserAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtUserAddActionPerformed(evt);
            }
        });

        jButtUserDel.setText("Delete user");
        jButtUserDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtUserDelActionPerformed(evt);
            }
        });

        jButtRepExp.setText("Export");
        jButtRepExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtRepExpActionPerformed(evt);
            }
        });

        jLabel1.setText("Users");

        jButtUserList.setText("User list");
        jButtUserList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtUserListMouseClicked(evt);
            }
        });

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

        jButtToolList.setText("Tool list");
        jButtToolList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtToolListMouseClicked(evt);
            }
        });

        jButtDiagDel.setText("Cancel diagnosis");
        jButtDiagDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtDiagDelMouseClicked(evt);
            }
        });

        jButtDiagList.setText("Diagnosis list");
        jButtDiagList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtDiagListMouseClicked(evt);
            }
        });

        jButtMaterList.setText("Material list");
        jButtMaterList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtMaterListActionPerformed(evt);
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

        jButtAdminLogoff.setText("Log off");
        jButtAdminLogoff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtAdminLogoffMouseClicked(evt);
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
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtMaterList, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtOperList, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtMaterDel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtOperDel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtMaterAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtOperAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtDiagList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtDiagDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(jButtDiagAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel3)
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(127, 127, 127))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addGap(16, 16, 16)
                                                            .addComponent(jButtAdminPsw)
                                                            .addGap(28, 28, 28)
                                                            .addComponent(jButtAdminLogoff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addGap(136, 136, 136)
                                                            .addComponent(jLabel1))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGap(17, 17, 17)
                                                            .addComponent(jButtUserAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jButtUserDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(96, 96, 96)
                                                        .addComponent(jButtUserList, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)))
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(52, 52, 52))
                                            .addComponent(jButtToolDel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(45, 45, 45))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButtToolAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButtRepExp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(1, 1, 1))
                                    .addComponent(jButtToolList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtToolAdd)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtRepExp)
                                .addGap(89, 89, 89)))
                        .addComponent(jButtToolDel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtToolList))
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtAdminPsw)
                            .addComponent(jButtAdminLogoff))
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtUserDel)
                            .addComponent(jButtUserAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtUserList)
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtDiagAdd)
                    .addComponent(jButtOperAdd)
                    .addComponent(jButtMaterAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtDiagDel)
                    .addComponent(jButtOperDel)
                    .addComponent(jButtMaterDel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtDiagList)
                        .addComponent(jButtOperList))
                    .addComponent(jButtMaterList))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtUserAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtUserAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtUserAddActionPerformed

    private void jButtUserDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtUserDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtUserDelActionPerformed

    private void jButtRepExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtRepExpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtRepExpActionPerformed

    private void jButtToolDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtToolDelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtToolDelActionPerformed

    private void jButtOperAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtOperAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtOperAddActionPerformed

    private void jButtMaterListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtMaterListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtMaterListActionPerformed

    private void jButtMaterAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtMaterAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtMaterAddMouseClicked

    private void jButtMaterDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtMaterDelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtMaterDelMouseClicked

    private void jButtUserListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtUserListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtUserListMouseClicked

    private void jButtToolAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtToolAddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtToolAddMouseClicked

    private void jButtToolListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtToolListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtToolListMouseClicked

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
    }//GEN-LAST:event_jButtDiagAddMouseClicked

    private void jButtDiagDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtDiagDelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtDiagDelMouseClicked

    private void jButtDiagListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtDiagListMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtDiagListMouseClicked

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtAdminLogoff;
    private javax.swing.JButton jButtAdminPsw;
    private javax.swing.JButton jButtDiagAdd;
    private javax.swing.JButton jButtDiagDel;
    private javax.swing.JButton jButtDiagList;
    private javax.swing.JButton jButtMaterAdd;
    private javax.swing.JButton jButtMaterDel;
    private javax.swing.JButton jButtMaterList;
    private javax.swing.JButton jButtOperAdd;
    private javax.swing.JButton jButtOperDel;
    private javax.swing.JButton jButtOperList;
    private javax.swing.JButton jButtRepExp;
    private javax.swing.JButton jButtToolAdd;
    private javax.swing.JButton jButtToolDel;
    private javax.swing.JButton jButtToolList;
    private javax.swing.JButton jButtUserAdd;
    private javax.swing.JButton jButtUserDel;
    private javax.swing.JButton jButtUserList;
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
