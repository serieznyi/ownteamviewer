/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.mainframe;

/**
 *
 * @author serieznyi
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
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

        startPanel1 = new myconnector.mainframe.StartPanel();
        workingPanel1 = new myconnector.mainframe.WorkingPanel();

        setLayout(new java.awt.CardLayout());
        add(startPanel1, "card2");
        add(workingPanel1, "card3");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private myconnector.mainframe.StartPanel startPanel1;
    private myconnector.mainframe.WorkingPanel workingPanel1;
    // End of variables declaration//GEN-END:variables

    WorkingPanel getWorkingPanel() {
        return this.workingPanel1;
    }
}