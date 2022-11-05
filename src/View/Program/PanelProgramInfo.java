/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Program;

import MOdel.Servants;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Administrator
 */
public class PanelProgramInfo extends javax.swing.JPanel {

    private final boolean isInput;
    private final VersionChange versionChange;
    private File dirCurr;
    private final Map<String, ShowConfig> showConfigs;
    private final JPopupMenu popupMenu;

    /**
     * Creates new form PanelProgramInfo
     *
     * @param isInput
     */
    public PanelProgramInfo(boolean isInput) {
        this.isInput = isInput;
        initComponents();
        this.versionChange = new VersionChange(txtF_major, txtF_minor, txtF_patcher, txtF_revision, isInput);
        this.dirCurr = FileSystemView.getFileSystemView().getHomeDirectory();
        this.showConfigs = new HashMap<>();
        this.popupMenu = new JPopupMenu();
        JMenuItem mnItemOpen = new JMenuItem("add");
        mnItemOpen.addActionListener((ActionEvent e) -> {
            addNewTabConfig();
        });
        JMenuItem mnItemRemove = new JMenuItem("Delete");
        mnItemRemove.addActionListener((ActionEvent e) -> {
            deleteTabConfigSelecteds();
        });
        this.popupMenu.add(mnItemOpen);
        this.popupMenu.add(mnItemRemove);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetail = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtF_major = new javax.swing.JFormattedTextField();
        txtF_minor = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtF_patcher = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtF_revision = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtProgram = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtProject = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tabPn = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtDetail.setColumns(10);
        txtDetail.setLineWrap(true);
        txtDetail.setRows(3);
        txtDetail.setTabSize(5);
        txtDetail.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtDetail);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("description");

        txtF_major.setEditable(false);
        txtF_major.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtF_major.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtF_major.setMinimumSize(new java.awt.Dimension(40, 22));
        txtF_major.setPreferredSize(new java.awt.Dimension(40, 22));

        txtF_minor.setEditable(false);
        txtF_minor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtF_minor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtF_minor.setMinimumSize(new java.awt.Dimension(40, 22));
        txtF_minor.setPreferredSize(new java.awt.Dimension(40, 22));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(".");

        txtF_patcher.setEditable(false);
        txtF_patcher.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtF_patcher.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtF_patcher.setMinimumSize(new java.awt.Dimension(40, 22));
        txtF_patcher.setPreferredSize(new java.awt.Dimension(40, 22));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(".");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(".");

        txtF_revision.setEditable(false);
        txtF_revision.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtF_revision.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtF_revision.setMinimumSize(new java.awt.Dimension(40, 22));
        txtF_revision.setPreferredSize(new java.awt.Dimension(40, 22));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Version");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Program");

        txtProgram.setEditable(false);
        txtProgram.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtProgram.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProgram.setToolTipText("");
        txtProgram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProgramMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtF_major, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF_minor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF_patcher, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtF_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProgram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(txtF_minor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtF_patcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtF_revision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtF_major, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        if(isInput){
            txtF_major.setText("1");
        }
        if(isInput){
            txtF_minor.setText("0");
        }
        if(isInput){
            txtF_patcher.setText("0");
        }
        if(isInput){
            txtF_revision.setText("0");
        }
        txtName.setEditable(isInput);

        txtProject.setEditable(false);
        txtProject.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProject.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProjectMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Project name");

        tabPn.setBackground(new java.awt.Color(204, 204, 255));
        tabPn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabPn.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabPn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabPn.setOpaque(true);
        tabPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabPnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPn, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPn, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtProject))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private void txtProgramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProgramMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() < 2 || !isInput) {
            return;
        }
        chosseProgramPath();
    }//GEN-LAST:event_txtProgramMouseClicked

    private void txtProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProjectMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() < 2 || !isInput) {
            return;
        }
        String project = JOptionPane.showInputDialog("Project name");
        if (project == null || !project.matches("^[a-z_A-Z]+.*$")) {
            return;
        }
        this.setProject(project);
        clearProgram();
        clearConfig();
        ShowConfig tabPublic = new ShowConfig(project, TAB_DEFAULT_NAME, isInput);
        addTabConfig(tabPublic);
    }//GEN-LAST:event_txtProjectMouseClicked

    private void tabPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPnMouseClicked
        // TODO add your handling code here:
        if (!txtProject.getText().isBlank()) {
            showPpMenu(evt, popupMenu);
        }
    }//GEN-LAST:event_tabPnMouseClicked

    private void showPpMenu(MouseEvent evt, JPopupMenu menu) {
        // TODO add your handling code here:
        if (evt.getButton() != MouseEvent.BUTTON3) {
            return;
        }
        menu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    private void chosseProgramPath() throws HeadlessException {
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(this.dirCurr);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setDialogTitle("Select file program");
        fc.setFileFilter(new FileNameExtensionFilter("C#", "exe"));
        fc.setFileFilter(new FileNameExtensionFilter("Python", "py"));
        fc.setFileFilter(new FileNameExtensionFilter("Java", "jar"));
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.txtProgram.setText(fc.getSelectedFile().getPath());
            this.dirCurr = fc.getCurrentDirectory();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabPn;
    private javax.swing.JTextArea txtDetail;
    private javax.swing.JFormattedTextField txtF_major;
    private javax.swing.JFormattedTextField txtF_minor;
    private javax.swing.JFormattedTextField txtF_patcher;
    private javax.swing.JFormattedTextField txtF_revision;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtProgram;
    private javax.swing.JTextField txtProject;
    // End of variables declaration//GEN-END:variables

    public String getProjectName() {
        return txtProject.getText();
    }

    public String getProgramName() {
        return txtName.getText();
    }

    public String getDetail() {
        return txtDetail.getText();
    }

    public String getProgramFile() {
        return txtProgram.getText();
    }

    public String getFolderSource() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<File> getProgramFilesConfig() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getVersion() {
        return "";
    }

    public void setProject(String name) {
        if (name == null) {
            return;
        }
        this.txtProject.setText(name);
    }

    private void clearProgram() {
        this.txtName.setText("");
        this.txtProgram.setText("");
        this.txtDetail.setText("");
    }

    private void clearConfig() {
        this.tabPn.removeAll();
        this.showConfigs.clear();
    }

    private void addTabConfig(ShowConfig showConfig) throws HeadlessException {
        if (!this.showConfigs.containsKey(showConfig.getName())) {
            this.tabPn.addTab(showConfig.getName(), showConfig);
            this.showConfigs.put(showConfig.getName(), showConfig);
            int index = this.tabPn.getTabCount() - 1;
            this.tabPn.setSelectedIndex(index);
        } else {
            JOptionPane.showMessageDialog(null, String.format("%s has exists", showConfig.getName()));
        }
    }

    private void addNewTabConfig() {
        String name = JOptionPane.showInputDialog("Folder name");
        if (name == null || !name.matches("^[a-z_A-Z]+.*$")) {
            return;
        }
        ShowConfig newTab = new ShowConfig(txtProject.getText(), name, isInput);
        addTabConfig(newTab);
    }

    private void deleteTabConfigSelecteds() {
        var componemt = this.tabPn.getSelectedComponent();
        if (componemt instanceof ShowConfig tab) {
            String tabName = tab.getName();
            if (tabName == null || tabName.equalsIgnoreCase(TAB_DEFAULT_NAME)) {
                return;
            }
            this.tabPn.remove(componemt);
            this.showConfigs.remove(tabName);
        }
    }
    private static final String TAB_DEFAULT_NAME = "Public";

    public Servants.ProgramParameter getProgramParameter() throws Exception {
        return new Servants.ProgramParameter(txtProject.getText(), txtName.getText(),
                versionChange.getVersion(), txtDetail.getText(), txtProgram.getText());
    }

    public List<Servants.ConfigFolderParameter> getProgramParameters() throws Exception {
        List<Servants.ConfigFolderParameter> rs = new ArrayList<>();
        for (ShowConfig config : this.showConfigs.values()) {
            rs.add(config.getConfigParamet());
        }
        return rs;
    }

    public Servants.ProjectParameter getProjectParameter() throws Exception {
        return new Servants.ProjectParameter(txtProject.getText(), 1);
    }
}