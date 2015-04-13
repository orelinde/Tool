/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SubsitutionPanel.java
 *
 * Created on 17-mei-2011, 16:34:54
 */
package equa.factbreakdown.gui;

/**
 *
 * @author FrankP
 */
public class SubstitutionMatchPanel extends MatchPanel {

    private static final long serialVersionUID = 1L;
    private final String originalRoleName;
    private ConstantMatchPanel next;
    private final ConstantMatchPanel previous;
    private int roleNumber;

    /**
     * Creates new form SubsitutionPanel
     */
    public SubstitutionMatchPanel(int nr, String roleName, String typeName, ConstantMatchPanel previous) {
        initComponents();

        this.roleNumber = nr;
        originalRoleName = roleName;
        next = null;
        this.previous = previous;
        tfRoleName.setText(roleName);

        tfRoleName.setColumns(roleName.length());
        tfTypeName.setText(typeName);
        tfTypeName.setColumns(typeName.length());
        tfValue.select(0, 0);

        btSwap.setVisible(false);
    }

    @Override
    public String getText() {
        return tfValue.getText().trim();
    }

    public void setNext(ConstantMatchPanel panel) {
        next = panel;
    }

    @Override
    public ConstantMatchPanel getNext() {
        return next;
    }

    public void setSwapVisible() {
        btSwap.setVisible(true);
    }

    @Override
    public ConstantMatchPanel getPrevious() {
        return previous;
    }

    public String getRoleName() {
        return tfRoleName.getText().trim();
    }

    public boolean isRoleNameChanged() {
        return !originalRoleName.equals(tfRoleName.getText());
    }

    public int getRoleNumber() {
        return roleNumber;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnSubstitution = new javax.swing.JPanel();
        tfValue = new javax.swing.JTextField();
        pnDefinedRole = new javax.swing.JPanel();
        tfRoleName = new javax.swing.JTextField();
        tfTypeName = new javax.swing.JTextField();
        pnChange = new javax.swing.JPanel();
        pnMoveText = new javax.swing.JPanel();
        btLeft = new javax.swing.JButton();
        btRight = new javax.swing.JButton();
        btSwap = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(61, 62));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(250, 40));
        setLayout(new java.awt.BorderLayout());

        pnSubstitution.setName("pnSubstitution"); // NOI18N
        pnSubstitution.setPreferredSize(new java.awt.Dimension(130, 60));
        pnSubstitution.setLayout(new java.awt.GridLayout(0, 1, 0, 1));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(SubstitutionMatchPanel.class);
        tfValue.setFont(resourceMap.getFont("value.font")); // NOI18N
        tfValue.setText(resourceMap.getString("value.text")); // NOI18N
        tfValue.setToolTipText(resourceMap.getString("value.toolTipText")); // NOI18N
        tfValue.setName("value"); // NOI18N
        tfValue.setPreferredSize(new java.awt.Dimension(14, 15));
        tfValue.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tfValueMouseDragged(evt);
            }
        });
        tfValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValueActionPerformed(evt);
            }
        });
        pnSubstitution.add(tfValue);

        pnDefinedRole.setName("pnDefinedRole"); // NOI18N
        pnDefinedRole.setPreferredSize(new java.awt.Dimension(130, 30));
        pnDefinedRole.setLayout(new java.awt.BorderLayout());

        tfRoleName.setText(resourceMap.getString("tfRoleName.text")); // NOI18N
        tfRoleName.setToolTipText(resourceMap.getString("tfRoleName.toolTipText")); // NOI18N
        tfRoleName.setMinimumSize(new java.awt.Dimension(20, 20));
        tfRoleName.setName("tfRoleName"); // NOI18N
        tfRoleName.setPreferredSize(new java.awt.Dimension(74, 15));
        tfRoleName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfRoleNameMouseClicked(evt);
            }
        });
        pnDefinedRole.add(tfRoleName, java.awt.BorderLayout.WEST);

        tfTypeName.setEditable(false);
        tfTypeName.setForeground(resourceMap.getColor("tfTypeName.foreground")); // NOI18N
        tfTypeName.setText(resourceMap.getString("tfTypeName.text")); // NOI18N
        tfTypeName.setName("tfTypeName"); // NOI18N
        tfTypeName.setPreferredSize(new java.awt.Dimension(41, 15));
        pnDefinedRole.add(tfTypeName, java.awt.BorderLayout.CENTER);

        pnSubstitution.add(pnDefinedRole);

        add(pnSubstitution, java.awt.BorderLayout.CENTER);

        pnChange.setMinimumSize(new java.awt.Dimension(30, 40));
        pnChange.setName("pnChange"); // NOI18N
        pnChange.setPreferredSize(new java.awt.Dimension(30, 40));
        pnChange.setLayout(new java.awt.GridLayout(2, 1));

        pnMoveText.setMinimumSize(new java.awt.Dimension(0, 0));
        pnMoveText.setName("pnMoveText"); // NOI18N
        pnMoveText.setPreferredSize(new java.awt.Dimension(32, 20));
        pnMoveText.setLayout(new java.awt.BorderLayout());

        btLeft.setText(resourceMap.getString("btLeft.text")); // NOI18N
        btLeft.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btLeft.setName("btLeft"); // NOI18N
        btLeft.setPreferredSize(new java.awt.Dimension(15, 30));
        btLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLeftActionPerformed(evt);
            }
        });
        pnMoveText.add(btLeft, java.awt.BorderLayout.WEST);

        btRight.setText(resourceMap.getString("btRight.text")); // NOI18N
        btRight.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btRight.setName("btRight"); // NOI18N
        btRight.setPreferredSize(new java.awt.Dimension(15, 30));
        btRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRightActionPerformed(evt);
            }
        });
        pnMoveText.add(btRight, java.awt.BorderLayout.EAST);

        pnChange.add(pnMoveText);

        btSwap.setFont(resourceMap.getFont("btSwap.font")); // NOI18N
        btSwap.setText(resourceMap.getString("btSwap.text")); // NOI18N
        btSwap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btSwap.setName("btSwap"); // NOI18N
        btSwap.setPreferredSize(new java.awt.Dimension(30, 30));
        btSwap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSwapActionPerformed(evt);
            }
        });
        pnChange.add(btSwap);

        add(pnChange, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void tfValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValueActionPerformed
        moveTextRight();
    }//GEN-LAST:event_tfValueActionPerformed

    private void moveTextRight() {
        int caretPosition = tfValue.getCaretPosition();
        String value = tfValue.getText();
        if (caretPosition >= value.length()) {
            return;
        }
        while (caretPosition > 0 && Character.isWhitespace(value.charAt(caretPosition - 1))) {
            caretPosition--;
        }
        String toMove = value.substring(caretPosition);
        setText(value.substring(0, caretPosition));
        next.appendFrontEnd(toMove);
        refreshLayout();
    }

    private void moveTextLeft() {
        int caretPosition = tfValue.getCaretPosition();
        String value = tfValue.getText();
        int valueLength = value.length();
        if (caretPosition == 0) {
            return;
        }
        while (caretPosition < valueLength && Character.isWhitespace(value.charAt(caretPosition))) {
            caretPosition++;
        }
        String toMove = value.substring(0, caretPosition);
        setText(value.substring(caretPosition));
        previous.appendBackEnd(toMove);
        refreshLayout();
    }

    private void tfValueMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfValueMouseDragged
//        if (!moved && tfValue.getCaretPosition() >= tfValue.getSelectionStart()
//                && tfValue.getSelectionStart() > 0) {
//            moveText();
//        }
    }//GEN-LAST:event_tfValueMouseDragged

    private void btSwapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSwapActionPerformed
        if (next.getNext() == null) {
            return;
        }
        SubstitutionMatchPanel right = (SubstitutionMatchPanel) next.getNext();
        int roleNumberCopy = roleNumber;
        String roleNameCopy = tfRoleName.getText();
        String typeNameCopy = tfTypeName.getText();

        roleNumber = right.roleNumber;
        tfRoleName.setText(right.tfRoleName.getText());
        tfTypeName.setText(right.tfTypeName.getText());

        right.roleNumber = roleNumberCopy;
        right.tfRoleName.setText(roleNameCopy);
        right.tfTypeName.setText(typeNameCopy);

        System.out.println("Switch between role " + right.roleNumber + " and "
            + roleNumber + ".");
        refreshLayout();

    }//GEN-LAST:event_btSwapActionPerformed

    private void tfRoleNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfRoleNameMouseClicked
        if (tfRoleName.getText().isEmpty()) {
            tfRoleName.setColumns(6);
            refreshLayout();
        }
    }//GEN-LAST:event_tfRoleNameMouseClicked

    private void btRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRightActionPerformed
        moveTextRight();
    }//GEN-LAST:event_btRightActionPerformed

    private void btLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLeftActionPerformed
        moveTextLeft();
    }//GEN-LAST:event_btLeftActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLeft;
    private javax.swing.JButton btRight;
    private javax.swing.JButton btSwap;
    private javax.swing.JPanel pnChange;
    private javax.swing.JPanel pnDefinedRole;
    private javax.swing.JPanel pnMoveText;
    private javax.swing.JPanel pnSubstitution;
    private javax.swing.JTextField tfRoleName;
    private javax.swing.JTextField tfTypeName;
    private javax.swing.JTextField tfValue;
    // End of variables declaration//GEN-END:variables

    @Override
    public void appendFrontEnd(String toMove) {
        tfValue.setText(toMove + getText());
    }

    @Override
    public void appendBackEnd(String toMove) {
        tfValue.setText(getText() + toMove);

    }

    public void setText(String expression) {
        tfValue.setText(expression);
        // tfValue.setColumns(tfValue.getText().length());
        refreshLayout();
    }
}