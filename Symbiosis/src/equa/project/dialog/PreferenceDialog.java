/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equa.project.dialog;

import equa.project.ProjectRole;
import equa.gui.GraphicalPrefs;
import equa.gui.PreferenceOfAspect;
import java.awt.Frame;

/**
 *
 * @author frankpeeters
 */
public class PreferenceDialog extends javax.swing.JDialog {

    private ProjectRole projectRole;

    /**
     * Creates new form PreferenceDialog
     */
    public PreferenceDialog(java.awt.Frame parent, ProjectRole projectRole) {
        super(parent, true);
        this.projectRole = projectRole;
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

        jPanel1 = new javax.swing.JPanel();
        btRequirements = new javax.swing.JButton();
        btFactTypes = new javax.swing.JButton();
        btRoles = new javax.swing.JButton();
        btClassOperations = new javax.swing.JButton();
        btFactBreakdown = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btReset = new javax.swing.JButton();
        btReady = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(170, 300));

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 297));

        btRequirements.setText("Requirements");
        btRequirements.setName("btRequirements"); // NOI18N
        btRequirements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRequirementsActionPerformed(evt);
            }
        });

        btFactTypes.setText("Fact Types");
        btFactTypes.setName("btFactTypes"); // NOI18N
        btFactTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFactTypesActionPerformed(evt);
            }
        });

        btRoles.setText("Roles");
        btRoles.setName("btRoles"); // NOI18N
        btRoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRolesActionPerformed(evt);
            }
        });

        btClassOperations.setText("Operations");
        btClassOperations.setName("btClassOperations"); // NOI18N
        btClassOperations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClassOperationsActionPerformed(evt);
            }
        });

        btFactBreakdown.setText("Fact Breakdown");
        btFactBreakdown.setEnabled(false);
        btFactBreakdown.setName("btFactBreakdown"); // NOI18N
        btFactBreakdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFactBreakdownActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(183, 20));

        btReset.setText("Reset");
        btReset.setName("btReset"); // NOI18N
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });

        btReady.setText("Ready");
        btReady.setName("btReady"); // NOI18N
        btReady.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReadyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btReady, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReset)
                    .addComponent(btReady))
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btFactBreakdown, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRoles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btFactTypes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btRequirements, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btClassOperations, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btRequirements)
                .addGap(18, 18, 18)
                .addComponent(btFactTypes)
                .addGap(18, 18, 18)
                .addComponent(btRoles)
                .addGap(18, 18, 18)
                .addComponent(btClassOperations)
                .addGap(18, 18, 18)
                .addComponent(btFactBreakdown)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReadyActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btReadyActionPerformed

    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        projectRole.setPrefs(GraphicalPrefs.DEFAULT);
        setVisible(false);
    }//GEN-LAST:event_btResetActionPerformed

    private void btFactBreakdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFactBreakdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btFactBreakdownActionPerformed

    private void btClassOperationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClassOperationsActionPerformed
        showDialog("ClassOperations");
    }//GEN-LAST:event_btClassOperationsActionPerformed

    private void btRolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRolesActionPerformed
        showDialog("Roles");
    }//GEN-LAST:event_btRolesActionPerformed

    private void btFactTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFactTypesActionPerformed
        showDialog("FactTypes");
    }//GEN-LAST:event_btFactTypesActionPerformed

    private void btRequirementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRequirementsActionPerformed
        showDialog("Requirements");
    }//GEN-LAST:event_btRequirementsActionPerformed

    private void showDialog(String aspect) {
        PreferenceOfAspectDialog dialog = new PreferenceOfAspectDialog((Frame) getParent(), projectRole.getPrefs().getPreference(aspect), aspect);
        dialog.setVisible(true);
        if (dialog.isOk()) {
            projectRole.getPrefs().setPreference(aspect,
                new PreferenceOfAspect(dialog.getFontSize(), dialog.getFontColor(), dialog.getBackground()));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClassOperations;
    private javax.swing.JButton btFactBreakdown;
    private javax.swing.JButton btFactTypes;
    private javax.swing.JButton btReady;
    private javax.swing.JButton btRequirements;
    private javax.swing.JButton btReset;
    private javax.swing.JButton btRoles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
