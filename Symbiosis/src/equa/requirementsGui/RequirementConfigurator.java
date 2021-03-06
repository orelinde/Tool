package equa.requirementsGui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.vlsolutions.swing.docking.DockKey;
import com.vlsolutions.swing.docking.Dockable;

import equa.controller.IView;
import equa.controller.ProjectController;
import equa.controller.SwingProjectController;
import equa.meta.ChangeNotAllowedException;
import equa.meta.objectmodel.FactType;
import equa.meta.objectmodel.ObjectRole;
import equa.meta.requirements.ActionRequirement;
import equa.meta.requirements.FactRequirement;
import equa.meta.requirements.QualityAttribute;
import equa.meta.requirements.Requirement;
import equa.meta.requirements.RequirementFilter;
import equa.meta.requirements.RequirementModel;
import equa.meta.requirements.RuleRequirement;
import equa.meta.traceability.AddRejectedState;
import equa.meta.traceability.AddedState;
import equa.meta.traceability.ApprovedState;
import equa.meta.traceability.Category;
import equa.meta.traceability.ChangeRejectedState;
import equa.meta.traceability.ChangedState;
import equa.meta.traceability.ExternalInput;
import equa.meta.traceability.Impact;
import equa.meta.traceability.RemoveRejectedState;
import equa.meta.traceability.RemovedState;
import equa.project.Project;
import equa.project.ProjectRole;
import equa.project.dialog.EnterRequirementDialog;
import equa.gui.swing.SwingUtils;
import equa.gui.GraphicalPrefs;
import equa.gui.PreferenceOfAspect;
import equa.meta.traceability.SystemCategory;
import fontys.observer.PropertyListener;
import java.awt.Color;
import java.awt.Font;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.ListSelectionModel;

public class RequirementConfigurator extends JPanel implements IView, Dockable, PropertyListener {

    private static final long serialVersionUID = 1L;
    public static final int CATEGORY = 0;
    public static final int REVIEW_STATE = 1;
    public static final int KIND = 2;
    public static final int REVIEW_IMPACT = 3;
    public static final int OTHER_FILTER = 4;
    private ProjectController projectController;
    private Project project;
    private ArrayList<Requirement> filteredRequirements;
    private final DockKey dockKey;
    private JTable[] filterTables;
    private JTable tblRequirements;
    private JPopupMenu requirementPopupMenu;
    private JMenuItem mntmAddRequirement;
    private JMenuItem mntmEditRequirement;
    private JMenuItem mntmApproveRequirement;
    private JMenuItem mntmRejectRequirement;
    private JMenuItem mntmRemoveRequirement;
    private JMenuItem mntmRollbackRequirement;
    private JMenuItem mntmDecompose;
    private JMenuItem mntmActionRuleAssignment;
    private JMenuItem mntmEventRuleAssignment;
    private JMenuItem mntmInitializeAssignment;
    private JScrollPane spRequirements;
    private JScrollPane spCategories;

    private JPanel panelCategories;
    private JPanel panelState;
    private JPanel panelKind;
    private JPanel panelImpact;
    private JPanel panelOther;
    private JPanel panelRequirements;

    public RequirementConfigurator(ProjectController controller) {
        dockKey = new DockKey("ReqModel Configurator");
        dockKey.setCloseEnabled(false);
        filteredRequirements = new ArrayList<>();
        setProjectController(controller);
    }

    public final void setProjectController(ProjectController controller) {
        if (projectController == null) {
            projectController = controller;
            project = projectController.getProject();
            projectController.addView(this);
            initView();
        } else if (!controller.getProject().equals(project)) {
            project = controller.getProject();
        }
        initCategories();
        deselectSYS();
        refresh();
    }

    @Override
    public void refresh() {
        selectFilteredRequirements();
        SwingUtils.resize(tblRequirements);
        //tblRequirements.setVisible(true);
        //spRequirements.updateUI();
        initPopup();
    }

    private void initPopup() {
        ProjectRole currentUser = projectController.getCurrentUser();

        if (currentUser != null) {

            Project project = projectController.getProject();
            mntmAddRequirement.setEnabled(true);

            Requirement req = getSelectedRequirement();
            if (req != null) {
                mntmEditRequirement.setEnabled(req.isChangeable(currentUser));
                mntmApproveRequirement.setEnabled(req.isApprovable(currentUser));
                mntmRejectRequirement.setEnabled(req.isRejectable(currentUser));
                mntmRemoveRequirement.setEnabled(req.isRemovable(currentUser));
                mntmRollbackRequirement.setEnabled(req.isRollBackable(currentUser));

                if (req.getCategory().isOwner(currentUser) && req.getReviewState() instanceof RemovedState) {
                    mntmApproveRequirement.setText("Acccept Remove");
                } else {
                    mntmApproveRequirement.setText("Accept");
                }
                if (req.isManuallyCreated() && (req instanceof RuleRequirement)) {
                    mntmActionRuleAssignment.setEnabled(true);
                    mntmEventRuleAssignment.setEnabled(true);
                    mntmInitializeAssignment.setEnabled(true);
                } else {
                    mntmActionRuleAssignment.setEnabled(false);
                    mntmEventRuleAssignment.setEnabled(false);
                    mntmInitializeAssignment.setEnabled(false);
                }
            } else {
                mntmEditRequirement.setEnabled(false);
                mntmApproveRequirement.setEnabled(false);
                mntmRejectRequirement.setEnabled(false);
                mntmRemoveRequirement.setEnabled(false);
                mntmRollbackRequirement.setEnabled(false);
            }
            boolean isProjectMember = project.isLoggedUserProjectMember();
            mntmDecompose.setEnabled(isProjectMember);

        } else {
            mntmAddRequirement.setEnabled(false);
            mntmEditRequirement.setEnabled(false);
            mntmDecompose.setEnabled(false);
            mntmApproveRequirement.setEnabled(false);
            mntmRejectRequirement.setEnabled(false);
            mntmRemoveRequirement.setEnabled(false);
            mntmRollbackRequirement.setEnabled(false);
        }
    }

    private void initFilters() {
        filterTables = new JTable[5];

        panelCategories = new JPanel();
        panelKind = new JPanel();
        panelState = new JPanel();
        panelImpact = new JPanel();
        panelOther = new JPanel();

        //***** Categories ***************************************************************
        spCategories = new JScrollPane();
        spCategories.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GroupLayout gl_panelCategories = new GroupLayout(panelCategories);
        gl_panelCategories.setHorizontalGroup(
            gl_panelCategories.createParallelGroup(Alignment.LEADING)
            .addComponent(spCategories, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE));
        gl_panelCategories.setVerticalGroup(
            gl_panelCategories.createParallelGroup(Alignment.LEADING)
            .addComponent(spCategories, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE));
        panelCategories.setLayout(gl_panelCategories);

        //***** Requirement Kind *********************************************************
        JScrollPane spKind = new JScrollPane();
        GroupLayout gl_panelKind = new GroupLayout(panelKind);
        gl_panelKind.setHorizontalGroup(
            gl_panelKind.createParallelGroup(Alignment.LEADING)
            .addComponent(spKind, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE));
        /*.addContainerGap(116, Short.MAX_VALUE))*/
        gl_panelKind.setVerticalGroup(
            gl_panelKind.createParallelGroup(Alignment.LEADING)
            .addComponent(spKind, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE));
        List<RequirementFilter> reqFilters = new ArrayList<>();
        reqFilters.add(ActionRequirement.getRequirementFilter());
        reqFilters.add(FactRequirement.getRequirementFilter());
        reqFilters.add(RuleRequirement.getRequirementFilter());
        reqFilters.add(QualityAttribute.getRequirementFilter());
        panelKind.setLayout(gl_panelKind);
        filterTables[KIND] = new JTable(new FilterTableModel(this, "ReqKind", reqFilters));
        spKind.setViewportView(filterTables[KIND]);

        // ***** Review State ************************************************************
        JScrollPane spState = new JScrollPane();
        GroupLayout gl_panelState = new GroupLayout(panelState);
        gl_panelState.setHorizontalGroup(
            gl_panelState.createParallelGroup(Alignment.LEADING)
            .addComponent(spState, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE));
        gl_panelState.setVerticalGroup(
            gl_panelState.createParallelGroup(Alignment.LEADING)
            .addComponent(spState, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE));
        panelState.setLayout(gl_panelState);
        List<RequirementFilter> reviewFilters = new ArrayList<>();
        reviewFilters.add(AddedState.getRequirementFilter());
        reviewFilters.add(ApprovedState.getRequirementFilter());
        reviewFilters.add(RemovedState.getRequirementFilter());
        reviewFilters.add(ChangedState.getRequirementFilter());
        reviewFilters.add(AddRejectedState.getRequirementFilter());
        reviewFilters.add(ChangeRejectedState.getRequirementFilter());
        reviewFilters.add(RemoveRejectedState.getRequirementFilter());

        filterTables[REVIEW_STATE] = new JTable(new FilterTableModel(this, "ReviewState", reviewFilters));
        spState.setViewportView(filterTables[REVIEW_STATE]);

        //***** Review Impact ************************************************************
        JScrollPane spImpact = new JScrollPane();
        GroupLayout gl_panelImpact = new GroupLayout(panelImpact);
        gl_panelImpact.setHorizontalGroup(
            gl_panelImpact.createParallelGroup(Alignment.LEADING)
            .addComponent(spImpact, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE));
        gl_panelImpact.setVerticalGroup(
            gl_panelImpact.createParallelGroup(Alignment.LEADING)
            .addComponent(spImpact, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE));
        panelImpact.setLayout(gl_panelImpact);
        ArrayList<RequirementFilter> impacts = new ArrayList<>();
        impacts.add(Impact.UNDEFINED);
        impacts.add(Impact.ZERO);
        impacts.add(Impact.LIGHT);
        impacts.add(Impact.NORMAL);
        impacts.add(Impact.SERIOUS);
        filterTables[REVIEW_IMPACT] = new JTable(new FilterTableModel(this, "ReviewImpact", impacts));
        spImpact.setViewportView(filterTables[REVIEW_IMPACT]);

        // ***** Other Filters **********************************************************
        JScrollPane spOther = new JScrollPane();
        GroupLayout gl_panelOther = new GroupLayout(panelOther);
        gl_panelOther.setHorizontalGroup(
            gl_panelOther.createParallelGroup(Alignment.LEADING)
            .addComponent(spOther, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE));
        gl_panelOther.setVerticalGroup(
            gl_panelOther.createParallelGroup(Alignment.LEADING)
            .addComponent(spOther, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE));
        panelOther.setLayout(gl_panelOther);

        ArrayList<RequirementFilter> others = new ArrayList<>();
        others.add(new RequirementFilter() {

            @Override
            public boolean acccepts(Requirement requirement) {
                return requirement.isRealized();
            }

            @Override
            public String toString() {
                return "Realized";
            }
        });

        others.add(new RequirementFilter() {

            @Override
            public boolean acccepts(Requirement requirement) {
                return !requirement.isRealized();
            }

            @Override
            public String toString() {
                return "Unrealized";
            }
        });

        others.add(new RequirementFilter() {

            @Override
            public boolean acccepts(Requirement requirement) {
                return requirement.getCategory().isOwner(projectController.getCurrentUser());
            }

            @Override
            public String toString() {
                return "Owner";
            }
        });

        others.add(new RequirementFilter() {

            @Override
            public boolean acccepts(Requirement requirement) {
                return !requirement.getCategory().isOwner(projectController.getCurrentUser());
            }

            @Override
            public String toString() {
                return "NotOwner";
            }
        });

        filterTables[OTHER_FILTER] = new JTable(new FilterTableModel(this, "OtherFilter", others));
        spOther.setViewportView(filterTables[OTHER_FILTER]);

        // init of filterTables[0], see initCategories()
        for (int i = 1; i < filterTables.length; i++) {
            filterTables[i].setFont(new java.awt.Font("Lucida Grande", 0, 12));
            filterTables[i].getColumnModel().getColumn(0).setPreferredWidth(8);
        }

        this.add(panelCategories);
        this.add(panelState);
        this.add(panelKind);
        this.add(panelImpact);
        this.add(panelOther);

    }

    private void initView() {
        initFilters();

        setMinimumSize(new Dimension(737, 1500));

        panelRequirements = new JPanel();
        panelRequirements.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
            "Requirements", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tblRequirements = new RequirementsTable(new RequirementsTableModel(filteredRequirements, project));
        tblRequirements.setFont(new java.awt.Font("Lucida Grande", 0, 12));
        tblRequirements.setBackground(Color.white);
        tblRequirements.setForeground(Color.black);
        panelRequirements.setLayout(new BoxLayout(panelRequirements, BoxLayout.X_AXIS));
        spRequirements = new JScrollPane(tblRequirements, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spRequirements.setMinimumSize(new Dimension(1500, 1500));
        panelRequirements.add(spRequirements);

        tblRequirements.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        TableColumnModel tcm = tblRequirements.getColumnModel();
        tcm.getColumn(0).setMinWidth(40); // realized
        tcm.getColumn(1).setMinWidth(70); // name
        tcm.getColumn(2).setMinWidth(70); // kind
        tcm.getColumn(3).setMinWidth(50); // revstate
//        tcm.getColumn(4).setMinWidth(70); // review
        tcm.getColumn(4).setMinWidth(670); // text
        tblRequirements.setPreferredScrollableViewportSize(null);
        tblRequirements.setAutoResizeMode(AUTO_RESIZE_OFF);
        tblRequirements.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.add(panelRequirements);

        // ***** LAYOUT *****************************************************************
        SpringLayout springLayout = new SpringLayout();

        springLayout.putConstraint(SpringLayout.NORTH, panelCategories, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, panelCategories, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, panelCategories, 84, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, panelCategories, 130, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, panelState, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, panelState, 135, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, panelState, 84, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, panelState, 265, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, panelKind, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, panelKind, 270, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, panelKind, 84, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, panelKind, 400, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, panelImpact, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, panelImpact, 405, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, panelImpact, 84, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, panelImpact, 535, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, panelOther, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, panelOther, 540, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, panelOther, 84, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, panelOther, 670, SpringLayout.WEST, this);

        springLayout.putConstraint(SpringLayout.NORTH, panelRequirements, 85, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, panelRequirements, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, panelRequirements, 0, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, panelRequirements, 0, SpringLayout.EAST, this);

        setLayout(springLayout);

        // ***** POPUP MENU *************************************************************
        requirementPopupMenu = new JPopupMenu();
        addPopup(tblRequirements, requirementPopupMenu);

        mntmDecompose = new JMenuItem("Fact Breakdown");
        mntmDecompose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decomposeSelection(tblRequirements.getSelectedRows());
            }
        });
        requirementPopupMenu.add(mntmDecompose);

        mntmActionRuleAssignment = new JMenuItem("Action Rule Assignment");
        mntmActionRuleAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tblRequirements.getSelectedRows();
                if (selectedRows.length == 0) {
                    return;
                }
                Requirement req = filteredRequirements.get(selectedRows[0]);
                if (req instanceof RuleRequirement) {
                    ActionRuleAssignmentDialog dialog;
                    dialog = new ActionRuleAssignmentDialog(null, true, projectController.getProject().getObjectModel());
                    dialog.setVisible(true);
                }

            }
        });
        requirementPopupMenu.add(mntmActionRuleAssignment);

        mntmEventRuleAssignment = new JMenuItem("Event Rule Assignment");
        mntmEventRuleAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tblRequirements.getSelectedRows();
                if (selectedRows.length == 0) {
                    return;
                }
                Requirement req = filteredRequirements.get(selectedRows[0]);
                if (req instanceof RuleRequirement) {
                    EventRuleAssignmentDialog dialog;
                    dialog = new EventRuleAssignmentDialog(null, true, projectController.getProject().getObjectModel());
                    dialog.setTitle(req.getText());
                    dialog.setVisible(true);
                    FactType changingFT = dialog.getEventSource();
                    if (changingFT != null) {

                        FactType booleanFT = null;
                        String eventHandler = dialog.getEventHandler();
                        boolean negation = false;
                        ObjectRole role = dialog.getResponsibleRole();
                        try {
                            if (role.isMultiple()) {
                                role.addEvent(req, booleanFT, negation, dialog.checkExtend(),
                                    dialog.checkRemove(), false, eventHandler);
                            } else {
                                role.addEvent(req, booleanFT, negation, false,
                                    dialog.checkRemove(), dialog.checkUpdate(), eventHandler);
                            }
                        } catch (ChangeNotAllowedException ex) {
                            JOptionPane.showMessageDialog(RequirementConfigurator.this, ex.getMessage());
                        }
                    }
                }

            }
        });
        requirementPopupMenu.add(mntmEventRuleAssignment);

        mntmInitializeAssignment = new JMenuItem("Initialize Unregistered OT");
        mntmInitializeAssignment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tblRequirements.getSelectedRows();
                if (selectedRows.length == 0) {
                    return;
                }
                Requirement req = filteredRequirements.get(selectedRows[0]);
                if (req instanceof RuleRequirement) {
                    InitializeDialog dialog;
                    dialog = new InitializeDialog(null, projectController.getProject().getObjectModel(), req);
                    dialog.setVisible(true);
                }
            }
        });
        requirementPopupMenu.add(mntmInitializeAssignment);

        requirementPopupMenu.add(new JSeparator());

        mntmAddRequirement = new JMenuItem("Add Requirement");
        mntmAddRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEnterRequirementDialog();
            }
        });
        requirementPopupMenu.add(mntmAddRequirement);

        mntmEditRequirement = new JMenuItem("Edit Requirement");
        mntmEditRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChangeRequirementDialog();
            }
        });
        requirementPopupMenu.add(mntmEditRequirement);

        requirementPopupMenu.add(new JSeparator());

        mntmApproveRequirement = new JMenuItem("Approve");
        mntmApproveRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveRequirement(tblRequirements.getSelectedRows());
                projectController.refreshViews();
            }
        });
        requirementPopupMenu.add(mntmApproveRequirement);

        mntmRejectRequirement = new JMenuItem("Reject");
        mntmRejectRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rejectRequirement(tblRequirements.getSelectedRows());
                projectController.refreshViews();
            }
        });
        requirementPopupMenu.add(mntmRejectRequirement);

        mntmRemoveRequirement = new JMenuItem("Remove");
        mntmRemoveRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRequirement(tblRequirements.getSelectedRows());
                projectController.refreshViews();
            }
        });
        requirementPopupMenu.add(mntmRemoveRequirement);

        mntmRollbackRequirement = new JMenuItem("Rollback");
        mntmRollbackRequirement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollBackRequirement(tblRequirements.getSelectedRows());
                projectController.refreshViews();
            }
        });
        requirementPopupMenu.add(mntmRollbackRequirement);
    }

    public void initPrefs(GraphicalPrefs prefs) {
        PreferenceOfAspect pref = prefs.getPreference("Requirements");
        tblRequirements.setFont(new java.awt.Font("Lucida Grande", Font.PLAIN, pref.getFontSize()));
        tblRequirements.setBackground(pref.getBackground());
        tblRequirements.setForeground(pref.getFontColor());
    }

    private void initCategories() {
        ArrayList<RequirementFilter> categories;
        categories = new ArrayList<>(projectController.getProject().getCategories());
        filterTables[CATEGORY] = new JTable(new FilterTableModel(this, "Categories", categories));
        filterTables[CATEGORY].setFont(new java.awt.Font("Lucida Grande", 0, 12));
        filterTables[CATEGORY].getColumnModel().getColumn(0).setPreferredWidth(8);
        spCategories.setViewportView(filterTables[CATEGORY]);
    }

    private boolean filtered(Requirement req) {
        for (JTable table : filterTables) {
            FilterTableModel ftm = (FilterTableModel) table.getModel();
            if (!ftm.accepts(req)) {
                return false;
            }
        }
        return true;
    }

    private void deselectSYS() {
        FilterTableModel catFilterTableModel = (FilterTableModel) filterTables[CATEGORY].getModel();
        for (int row = 0; row < catFilterTableModel.getRowCount(); row++) {
            Category cat = (Category) catFilterTableModel.getValueAt(row, 1);
            if (cat.equals(SystemCategory.SYSTEM)) {
                catFilterTableModel.setValueAt(Boolean.FALSE, row, 0);
            } else {
                catFilterTableModel.setValueAt(Boolean.TRUE, row, 0);
            }

        }

    }

    public class RequirementsTable extends JTable {

        private static final long serialVersionUID = 1L;

        public RequirementsTable(TableModel model) {
            super(model);
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            super.valueChanged(e);
            initPopup();
        }
    }

    void rollBackRequirement(int[] selectedRows) {
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "No requirements selected");
        } else {
            for (int i = 0; i < selectedRows.length; i++) {
                Requirement r = filteredRequirements.get(selectedRows[i]);
                try {
                    r.getReviewState().rollBack(projectController.getCurrentUser());
                } catch (ChangeNotAllowedException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }
    }

    void removeRequirement(int[] selectedRows) {
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "No requirements selected");
        } else {
            for (int i = 0; i < selectedRows.length; i++) {
                Requirement r = filteredRequirements.get(selectedRows[i]);
                try {
                    r.getReviewState().remove(new ExternalInput("No comment", projectController.getCurrentUser()));
                } catch (ChangeNotAllowedException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
        }
    }

    void rejectRequirement(int[] selectedRows) {
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "No requirements selected");
        } else {
            for (int i = 0; i < selectedRows.length; i++) {
                Requirement r = filteredRequirements.get(selectedRows[i]);
                if (r.getCategory().isOwner(projectController.getCurrentUser())) {
                    try {
                        r.getReviewState().reject(new ExternalInput("No comment", projectController.getCurrentUser()));
                    } catch (ChangeNotAllowedException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        }
    }

    void approveRequirement(int[] selectedRows) {
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "No requirements selected");
        } else {
            for (int i = 0; i < selectedRows.length; i++) {
                Requirement r = filteredRequirements.get(selectedRows[i]);
                if (r.getCategory().isOwner(projectController.getCurrentUser())) {
                    try {
                        r.getReviewState().approve(new ExternalInput("No comment", projectController.getCurrentUser()));

                    } catch (ChangeNotAllowedException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            }
        }
    }

    void decomposeSelection(int[] selectedRows) {
        if (projectController instanceof SwingProjectController) {
            SwingProjectController controller = (SwingProjectController) projectController;

            controller.getDesktop().breakdownSelected();
        } else {
            ArrayList<FactRequirement> decomposeRequirements = new ArrayList<>();

            for (int i = 0; i < selectedRows.length; i++) {
                Requirement req = filteredRequirements.get(i);
                if (req instanceof FactRequirement) {
                    if (!req.isRealized() && req.isApproved()) {
                        decomposeRequirements.add((FactRequirement) req);
                    }
                }
            }

            if (decomposeRequirements.size() > 0) {
//                MultipleFactBreakdown decomposition = new MultipleFactBreakdown(projectController, decomposeRequirements);
//                Point point = this.getLocation();
//                point.setLocation(point.x + 25, point.y + 25);
//                decomposition.setLocation(point);
                //decomposition.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No fact requirements found that are APPROVED and realized");
            }
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public DockKey getDockKey() {
        return dockKey;
    }

    private Requirement getSelectedRequirement() {
        int[] rows = tblRequirements.getSelectedRows();

        if (rows.length == 0 || rows.length > 1) {
            return null;
        } else {
            return filteredRequirements.get(rows[0]);
        }
    }

    private void selectFilteredRequirements() {
        Project p = projectController.getProject();
        if (p == null) {
            return;
        }
        RequirementModel rm = p.getRequirementModel();
        Iterator<Requirement> iterator = rm.requirements();
        filteredRequirements = new ArrayList<>();
        while (iterator.hasNext()) {
            Requirement req = iterator.next();
            if (filtered(req)) {
                filteredRequirements.add(req);
            }
        }
        tblRequirements.setModel(new RequirementsTableModel(filteredRequirements, project));
    }

    public void openEnterRequirementDialog() {
        EnterRequirementDialog requirementDialog = new EnterRequirementDialog(projectController);
        Point point = this.getLocation();
        point.setLocation(point.x + 25, point.y + 25);
        requirementDialog.setLocation(point);
        requirementDialog.setVisible(true);
    }

    public void openChangeRequirementDialog() {
        Point point = this.getLocation();
        point.setLocation(point.x + 25, point.y + 25);
        RequirementsTableModel model = (RequirementsTableModel) tblRequirements.getModel();

        Requirement requirement = model.getRequirementAt(tblRequirements.getSelectedRow());

        if (requirement != null && requirement.isChangeable(projectController.getCurrentUser())) {
            EnterRequirementDialog requirementDialog = new EnterRequirementDialog(projectController);
            requirementDialog.changeRequirement(requirement);
            requirementDialog.setLocation(point);
            requirementDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No requirement selected.");
        }
    }

    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    public ArrayList<FactRequirement> getBreakdownReadyFactRequirements() {
        int[] selectedRows = tblRequirements.getSelectedRows();
        ArrayList<FactRequirement> decomposeRequirements = new ArrayList<>();

        for (int i = 0; i < selectedRows.length; i++) {
            Requirement req = filteredRequirements.get(selectedRows[i]);
            if (req instanceof FactRequirement) {
                if (!req.isRealized() && req.isApproved()) {
                    decomposeRequirements.add((FactRequirement) req);
                }
            }
        }
        return decomposeRequirements;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        initPopup();
    }
}
