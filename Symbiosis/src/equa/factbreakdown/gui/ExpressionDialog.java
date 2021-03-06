/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ExpressionDialog.java
 *
 * Created on 3-mei-2011, 13:58:58
 */
package equa.factbreakdown.gui;

import javax.swing.JOptionPane;

import equa.controller.IView;

/**
 *
 * @author FrankP
 */
public class ExpressionDialog extends javax.swing.JDialog implements IView {

    private static final long serialVersionUID = 1L;
    private boolean objectExpression;

    /**
     * Creates new form ExpressionDialog
     */
    public ExpressionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        objectExpression = false;
        taExpression.setLineWrap(true);
        //setLocation(parent.getLocationOnScreen());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taExpression = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btFactType = new javax.swing.JButton();
        okObjectType = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(equa.desktop.Symbiosis.class).getContext().getResourceMap(ExpressionDialog.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        taExpression.setColumns(20);
        taExpression.setFont(resourceMap.getFont("taExpression.font")); // NOI18N
        taExpression.setLineWrap(true);
        taExpression.setRows(5);
        taExpression.setWrapStyleWord(true);
        taExpression.setMinimumSize(new java.awt.Dimension(0, 0));
        taExpression.setName("taExpression"); // NOI18N
        taExpression.setPreferredSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setViewportView(taExpression);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        btFactType.setText(resourceMap.getString("btFactType.text")); // NOI18N
        btFactType.setToolTipText(resourceMap.getString("btFactType.toolTipText")); // NOI18N
        btFactType.setName("btFactType"); // NOI18N
        btFactType.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFactTypeActionPerformed(evt);
            }
        });

        okObjectType.setText(resourceMap.getString("okObjectType.text")); // NOI18N
        okObjectType.setToolTipText(resourceMap.getString("okObjectType.toolTipText")); // NOI18N
        okObjectType.setName("okObjectType"); // NOI18N
        okObjectType.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                okObjectTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btFactType)
                        .addGap(18, 18, 18)
                        .addComponent(okObjectType))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okObjectType)
                    .addComponent(btFactType))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFactTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFactTypeActionPerformed
        if (getExpression().isEmpty()) {
            JOptionPane.showMessageDialog(getOwner(), "please fill in an expression");
            return;
        }
        setVisible(false);

    }//GEN-LAST:event_btFactTypeActionPerformed

    private void okObjectTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okObjectTypeActionPerformed
        if (getExpression().isEmpty()) {
            JOptionPane.showMessageDialog(getOwner(), "please fill in an expression");
            return;
        }
        objectExpression = true;
        setVisible(false);
    }//GEN-LAST:event_okObjectTypeActionPerformed

    public String getExpression() {
        return taExpression.getText();
    }

    public boolean isObjectExpression() {
        return objectExpression;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFactType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okObjectType;
    private javax.swing.JTextArea taExpression;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refresh() {
        // TODO Auto-generated method stub

    }
}
