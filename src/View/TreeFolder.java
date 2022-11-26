/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Communicate.Cmd.Cmd;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrator
 */
public class TreeFolder extends javax.swing.JPanel {

    private FileNode rootNode;
    private final DefaultTreeModel treeModel;
    private final JPopupMenu popupMenu;
    private Thread thread;
    private File dirCurr;

    /**
     * Creates new form TreeFolder
     *
     */
    public TreeFolder() {
        initComponents();
        this.treeModel = (DefaultTreeModel) this.jTree.getModel();
        this.popupMenu = new JPopupMenu();
        JMenuItem mnItemResfresh = new JMenuItem("Refresh");
        this.dirCurr = FileSystemView.getFileSystemView().getHomeDirectory();
        mnItemResfresh.addActionListener((ActionEvent e) -> {
            refreshSelectNode();
        });
        JMenuItem mnItemOpen = new JMenuItem("Open");
        mnItemOpen.addActionListener((ActionEvent e) -> {
            openSelectedNode();
        });
        JMenuItem mnItemRemove = new JMenuItem("Delete");
        mnItemRemove.addActionListener((ActionEvent e) -> {
            deleteSelectedNode();
        });
        this.popupMenu.add(mnItemResfresh);
        this.popupMenu.add(mnItemOpen);
        this.popupMenu.add(mnItemRemove);
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }

    public List<File> getAllFile() {
        if (rootNode == null) {
            return null;
        }
        List<File> files = new ArrayList<>();
        getChildren(this.rootNode, files);
        return files;
    }

    private void getChildren(FileNode fileNode, List<File> files) {
        if (!fileNode.hasLoadFolder) {
            fileNode.refresh();
        }
        var children = fileNode.children();
        while (children.hasMoreElements()) {
            FileNode node = (FileNode) children.nextElement();
            if (node.isLeaf()) {
                files.add(node.getFile());
            } else {
                getChildren(node, files);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTree.setDoubleBuffered(true);
        jTree.addTreeExpansionListener(new javax.swing.event.TreeExpansionListener() {
            public void treeCollapsed(javax.swing.event.TreeExpansionEvent evt) {
            }
            public void treeExpanded(javax.swing.event.TreeExpansionEvent evt) {
                jTreeTreeExpanded(evt);
            }
        });
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeMouseClicked(evt);
            }
        });
        jTree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTreeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMouseClicked
        // TODO add your handling code here:
        if (this.rootNode == null) {
            return;
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            showPpMenu(evt, popupMenu);
        } else if (evt.getButton() == MouseEvent.BUTTON1) {
            if (evt.getClickCount() > 1) {
                openSelectedNode();
            } else if (this.jTree.getSelectionCount() > 1 && !evt.isControlDown()) {
                jTree.clearSelection();
            }
        }
    }//GEN-LAST:event_jTreeMouseClicked

    private void jTreeTreeExpanded(javax.swing.event.TreeExpansionEvent evt) {//GEN-FIRST:event_jTreeTreeExpanded
        FileNode fileNode = (FileNode) evt.getPath().getLastPathComponent();
        if (fileNode.hasLoadFolder) {
            return;
        }
        reloadNode(fileNode);
    }//GEN-LAST:event_jTreeTreeExpanded

    private void jTreeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTreeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            deleteSelectedNode();
        }
    }//GEN-LAST:event_jTreeKeyPressed

    private void reloadNode(FileNode fileNode) {
        // TODO add your handling code here:
        if (this.thread != null && this.thread.isAlive() || fileNode == null || fileNode.isLeaf()) {
            return;
        }
        this.thread = new Thread() {
            private Component component;

            @Override
            public void run() {
                this.component.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                try {
                    fileNode.refresh();
                    treeModel.reload(fileNode);
                } finally {
                    this.component.setCursor(Cursor.getDefaultCursor());
                }
            }

            public Thread setComponent(Component Component) {
                this.component = Component;
                return this;
            }

        }.setComponent(this.jTree);
        this.thread.start();
    }

    private void openSelectedNode() {
        if (hasSelected()) {
            return;
        }
        FileNode fileNode = (FileNode) this.jTree.getSelectionPath().getLastPathComponent();
        new Cmd().sendCommand(fileNode.getFile().getPath());
    }

    private boolean hasSelected() {
        return this.rootNode == null || this.jTree.getSelectionCount() <= 0;
    }

    private void showPpMenu(MouseEvent evt, JPopupMenu menu) {
        // TODO add your handling code here:
        menu.show(evt.getComponent(), evt.getX(), evt.getY());
    }

    public JFileChooser chosseFileRoot() throws HeadlessException {
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(this.dirCurr);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Select folder");
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.dirCurr = fc.getCurrentDirectory();
            this.setRoot(fc.getSelectedFile());
            return fc;
        }
        return null;
    }

    public void setRoot(File root) {
        if (root == null || !root.exists()) {
            return;
        }
        this.rootNode = new FileNode(root);
        this.treeModel.setRoot(this.rootNode);
        reloadNode(this.rootNode);
    }

    public void deleteSelectedNode() {
        if (hasSelected()) {
            return;
        }
        if (this.thread != null && this.thread.isAlive()) {
            return;
        }
        this.thread = new Thread() {
            private Component component;

            @Override
            public void run() {
                this.component.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                List<TreePath> parents = new ArrayList();
                try {
                    for (TreePath treePath : jTree.getSelectionPaths()) {
                        var parent = treePath.getParentPath();
                        if (parent == null) {
                            continue;
                        }
                        if (parents.contains(treePath)) {
                            parents.remove(treePath);
                        }
                        var fileNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                        var parentPath = (DefaultMutableTreeNode) parent.getLastPathComponent();
                        parentPath.remove(fileNode);
                        if (!parents.contains(parent)) {
                            parents.add(parent);
                        }
                    }
                    for (TreePath parent : parents) {
                        treeModel.reload((TreeNode) parent.getLastPathComponent());
                        jTree.expandPath(parent);
                    }
                } finally {
                    this.component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }

            public Thread setComponent(Component Component) {
                this.component = Component;
                return this;
            }

        }.setComponent(this.jTree);
        this.thread.start();
    }

    private void refreshSelectNode() {
        if (hasSelected()) {
            return;
        }
        for (TreePath treePath : this.jTree.getSelectionPaths()) {
            if (treePath == null) {
                continue;
            }
            reloadNode((FileNode) treePath.getLastPathComponent());
        }
    }

    public void clear() {
        ((DefaultMutableTreeNode) this.treeModel.getRoot()).removeAllChildren();
        this.treeModel.reload();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables

    class FileNode extends DefaultMutableTreeNode {

        private final File root;
        private boolean hasLoadFolder;

        public FileNode(File file) {
            super(file.getName());
            this.root = file;
            this.hasLoadFolder = false;
            if (file.isDirectory()) {
                this.add(new DefaultMutableTreeNode("..."));
            }
        }

        public void refresh() {
            if (!this.root.isDirectory()) {
                return;
            }
            this.removeAllChildren();
            for (File file : this.root.listFiles()) {
                this.add(new FileNode(file));
            }
            hasLoadFolder = true;
        }

        public File getFile() {
            return root;
        }

        public void add(FileNode newChild) {
            if (newChild == null) {
                return;
            }
            super.add(newChild);
        }

    }
}
