/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InheritanceDialog.java
 *
 * Created on 7-nov-2012, 15:10:47
 */
package equa.configurator;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import equa.meta.ChangeNotAllowedException;
import equa.meta.objectmodel.FactType;
import equa.meta.objectmodel.ObjectModel;
import equa.meta.objectmodel.ObjectType;

/**
 *
 * @author frankpeeters
 */
public final class InheritanceDialog extends javax.swing.JDialog {

    private static final long serialVersionUID = 1L;
    public static final String NONE = "-none-";
    private ObjectModel om;
    private List<String> objectTypesAll;
    private ObjectTypeListModel subModel;
    private ObjectTypeListModel superModel;

    final class ObjectTypeListModel implements ListModel<String> {

        private List<String> objectTypes;
        private EventListenerList eventListenerList;

        public ObjectTypeListModel() {
            objectTypes = objectTypesAll;
            eventListenerList = new EventListenerList();
        }

        @Override
        public int getSize() {
            return objectTypes.size();
        }

        @Override
        public String getElementAt(int i) {
            if (i < 0 || i >= objectTypes.size()) {
                return null;
            } else {
                return objectTypes.get(i);
            }
        }

        @Override
        public void addListDataListener(ListDataListener ll) {
            eventListenerList.add(ListDataListener.class, ll);
        }

        @Override
        public void removeListDataListener(ListDataListener ll) {
            eventListenerList.remove(ListDataListener.class, ll);
        }

        public void fireListChanged() {
            EventListener[] listeners = eventListenerList.getListeners(ListDataListener.class);
            for (EventListener l : listeners) {
                ((ListDataListener) l).contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
            }
        }

        public void setObjectTypes(List<String> ots) {
            objectTypes = ots;
            //objectTypes.add(0, NONE);
            fireListChanged();
        }

        public void setObjectTypesAll() {
            objectTypes = objectTypesAll;
            fireListChanged();
        }
    }

    /**
     * Creates new form InheritanceDialog
     */
    public InheritanceDialog(java.awt.Frame parent, boolean modal, ObjectModel om) {
        super(parent, modal);
        initComponents();

        this.om = om;
        refreshObjectModel();
        subModel = new ObjectTypeListModel();
        listSubTypes.setModel(subModel);
        superModel = new ObjectTypeListModel();
        listSuperTypes.setModel(superModel);
    }

    void refreshObjectModel() {
        objectTypesAll = new ArrayList<>();
        //objectTypesAll.add(NONE);
        for (FactType ft : om.types()) {
            if (ft.isObjectType() && !ft.isGenerated()) {
                objectTypesAll.add(ft.getName());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btAddInheritance = new javax.swing.JButton();
        btClose = new javax.swing.JButton();
        btHelp = new javax.swing.JButton();
        spJustification = new javax.swing.JScrollPane();
        taJustification = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSubTypes = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listSuperTypes = new javax.swing.JList<>();
        btRemoveInheritance = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(equa.desktop.Symbiosis.class).getContext().getResourceMap(InheritanceDialog.class);
        btAddInheritance.setText(resourceMap.getString("btAddInheritance.text")); // NOI18N
        btAddInheritance.setName("btAddInheritance"); // NOI18N
        btAddInheritance.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddInheritanceActionPerformed(evt);
            }
        });

        btClose.setText(resourceMap.getString("btClose.text")); // NOI18N
        btClose.setName("btClose"); // NOI18N
        btClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCloseActionPerformed(evt);
            }
        });

        btHelp.setText(resourceMap.getString("btHelp.text")); // NOI18N
        btHelp.setName("btHelp"); // NOI18N
        btHelp.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHelpActionPerformed(evt);
            }
        });

        spJustification.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("spJustification.border.title"))); // NOI18N
        spJustification.setName("spJustification"); // NOI18N

        taJustification.setColumns(20);
        taJustification.setRows(5);
        taJustification.setName("taJustification"); // NOI18N
        spJustification.setViewportView(taJustification);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listSubTypes.setBorder(javax.swing.BorderFactory.createTitledBorder("Sub Type"));
        listSubTypes.setModel(new javax.swing.AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            @Override
			public int getSize() { return strings.length; }
            @Override
			public String getElementAt(int i) { return strings[i]; }
        });
        listSubTypes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listSubTypes.setName("listSubTypes"); // NOI18N
        jScrollPane1.setViewportView(listSubTypes);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        listSuperTypes.setBorder(javax.swing.BorderFactory.createTitledBorder("Super Type"));
        listSuperTypes.setModel(new javax.swing.AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            @Override
			public int getSize() { return strings.length; }
            @Override
			public String getElementAt(int i) { return strings[i]; }
        });
        listSuperTypes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listSuperTypes.setName("listSuperTypes"); // NOI18N
        jScrollPane2.setViewportView(listSuperTypes);

        btRemoveInheritance.setText(resourceMap.getString("btRemoveInheritance.text")); // NOI18N
        btRemoveInheritance.setName("btRemoveInheritance"); // NOI18N
        btRemoveInheritance.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveInheritanceActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(spJustification, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(btAddInheritance)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btRemoveInheritance)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 37, Short.MAX_VALUE)
                        .add(btClose)
                        .add(8, 8, 8)
                        .add(btHelp)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(72, 72, 72)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 115, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18))
                    .add(layout.createSequentialGroup()
                        .add(spJustification, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 162, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(87, 87, 87)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btAddInheritance)
                    .add(btClose)
                    .add(btHelp)
                    .add(btRemoveInheritance))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddInheritanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddInheritanceActionPerformed
        if (listSubTypes.isSelectionEmpty() || listSuperTypes.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a super and a sub type.");
            return;
        }

        ObjectType superType = om.getObjectType(listSuperTypes.getSelectedValue());
        ObjectType subType = om.getObjectType(listSubTypes.getSelectedValue());

//        ExternalInput externalInput = new ExternalInput(taJustification.getText(),
//                (ProjectRole) om.getProject().getCurrentUser());
        try {
            subType.addSuperType(superType);
        } catch (ChangeNotAllowedException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btAddInheritanceActionPerformed

    private void btCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCloseActionPerformed
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btCloseActionPerformed

    private void btHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHelpActionPerformed
        JOptionPane.showMessageDialog(this,
                "This dialog offers the opportunity to add or remove an inheritance "
                + "relation between two object types.\n"
                + "\n"
                + "please select a supertype and subtype and press the button Add or Remove Inheritance",
                "Help on adding or removing an inheritance relation", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btHelpActionPerformed

    private void btRemoveInheritanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveInheritanceActionPerformed
        if (listSubTypes.isSelectionEmpty() || listSuperTypes.isSelectionEmpty()) {
            return;
        }

        ObjectType superType = om.getObjectType(listSuperTypes.getSelectedValue());
        ObjectType subType = om.getObjectType(listSubTypes.getSelectedValue());

        try {
            if (subType.hasSuperType(superType)) {
                subType.removeSupertype(superType);
            } else {
                JOptionPane.showMessageDialog(this, superType.getName() + " is not a supertype of " + subType.getName());
            }
        } catch (ChangeNotAllowedException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btRemoveInheritanceActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddInheritance;
    private javax.swing.JButton btClose;
    private javax.swing.JButton btHelp;
    private javax.swing.JButton btRemoveInheritance;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listSubTypes;
    private javax.swing.JList<String> listSuperTypes;
    private javax.swing.JScrollPane spJustification;
    private javax.swing.JTextArea taJustification;
    // End of variables declaration//GEN-END:variables
}