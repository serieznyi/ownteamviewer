/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.mainframe;

/**
 *
 * @author serieznyi
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame1
     */
    public MainFrame() {
        initComponents();
        
        this.setLocation(400, 150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalPanel1 = new myconnector.mainframe.GlobalPanel();
        statusBar1 = new myconnector.mainframe.StatusBar();
        menuBar1 = new myconnector.mainframe.MenuBar();
        jMenu3 = new javax.swing.JMenu();
        menu_item_exit = new javax.swing.JMenuItem();
        menu_item_about = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu3.setText("Приложение");

        menu_item_exit.setText("Выход");
        menu_item_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_exitActionPerformed(evt);
            }
        });
        jMenu3.add(menu_item_exit);

        menuBar1.add(jMenu3);

        menu_item_about.setText("Помощь");

        jMenuItem2.setText("О программе");
        menu_item_about.add(jMenuItem2);

        menuBar1.add(menu_item_about);

        setJMenuBar(menuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(globalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statusBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_item_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_exitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_item_exitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private myconnector.mainframe.GlobalPanel globalPanel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem2;
    private myconnector.mainframe.MenuBar menuBar1;
    private javax.swing.JMenu menu_item_about;
    private javax.swing.JMenuItem menu_item_exit;
    private myconnector.mainframe.StatusBar statusBar1;
    // End of variables declaration//GEN-END:variables
}
