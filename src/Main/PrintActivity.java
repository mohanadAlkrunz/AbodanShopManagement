/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import model.Product;

/**
 *
 * @author DELL
 */
public class PrintActivity extends javax.swing.JFrame {

    /**
     * Creates new form PrintActivity
     */






    public PrintActivity(List<Product> list,int bill_id,String name,String date,double total,double discount ,double paid,double remain) {
        initComponents();
UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
//        Product.buildInvoice(list).forEach(Product :: printInvoice);
//        jTextArea1.setText(Product.invoice);


//List<List<String>> rows = new ArrayList<>();
//
//
//
//        int nRow =tableModel.getRowCount(), nColumn=tableModel.getColumnCount();
//        Object [][] tableData= new Object[nRow][nColumn];
//
//        for(int i=0;i<nRow;i++){
//             List<String> row=new ArrayList<>();
//            for(int j=0;j<nColumn;j++){
//
//                row.add(tableModel.getValueAt(i, j).toString());
//
//            }
//            rows.add(row);
//            row.clear();
//        }
//        System.out.println(formatAsTable(rows));

//        jTextArea1.setText(formatAsTable(rows));

         jTextArea1.setText("   ----- محلات أبودان للأدوات الكهربائية -----");
 jTextArea1.setText(jTextArea1.getText()+"\n"+"                البريج بلوك 12             ");
  jTextArea1.setText(jTextArea1.getText()+"\n"+"                   0599908953               ");
   jTextArea1.setText(jTextArea1.getText()+"\n"+"                   فاتورة بيع             ");
   jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------");
    jTextArea1.setText(jTextArea1.getText()+"\n"+"الاسم :"
            +name
            +"             فاتورة رقم :"
                    + " "+bill_id);
     jTextArea1.setText(jTextArea1.getText()+"\n"+" التاريخ:"
             + " "+date);
    jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------");
     jTextArea1.setText(jTextArea1.getText()+"\n"+"اسم الصنف        سعرالوحدة     الكمية     الإجمالي");
  jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------");
     Product.buildInvoice(list).forEach(Product :: printInvoice);
        jTextArea1.setText(jTextArea1.getText()+"\n" + Product.invoice);
//             jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------");
//               jTextArea1.setText(jTextArea1.getText()+"\n"+"مفتاح مجوز "
//                       + "         "
//                       + "3"
//                        + "          "
//                       + "10"
//                         + "          "
//                       + "30");
//
//                jTextArea1.setText(jTextArea1.getText()+"\n"+"مفتاح سخن "
//                       + "         "
//                       + "4"
//                        + "          "
//                       + "10"
//                         + "          "
//                       + "40");
//
//                jTextArea1.setText(jTextArea1.getText()+"\n"+"مفتاح سخان "
//                       + "         "
//                       + "4"
//                        + "          "
//                       + "10"
//                         + "          "
//                       + "40");
//                jTextArea1.setText(jTextArea1.getText()+"\n"+"مفتاح سخان "
//                       + "         "
//                       + "4"
//                        + "          "
//                       + "10"
//                         + "          "
//                       + "40");
//                jTextArea1.setText(jTextArea1.getText()+"\n"+"مفتاح سخان "
//                       + "         "
//                       + "4"
//                        + "          "
//                       + "10"
//                         + "          "
//                       + "40");
//                jTextArea1.setText(jTextArea1.getText()+"\n"+"مفتاح سخان "
//                       + "         "
//                       + "4"
//                        + "          "
//                       + "10"
//                         + "          "
//                       + "40");
     jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------");
jTextArea1.setText(jTextArea1.getText()+"\n"+"المبلغ الإجمالي : "
        +  total);
     jTextArea1.setText(jTextArea1.getText()+"\n"+"الخصم: "
             + discount);
      jTextArea1.setText(jTextArea1.getText()+"\n"+"المبلغ المدفوع : "
              + paid);
       jTextArea1.setText(jTextArea1.getText()+"\n"+"المبلغ المتبقي : "
               + remain);
    jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------\n");
       jTextArea1.setText(jTextArea1.getText()+"\n"+"-------------------شكراً لثقتكم بنا---------------");
         jTextArea1.setText(jTextArea1.getText()+"\n"+"--------------------------------------------------");
               jTextArea1.setText(jTextArea1.getText()+"\n"+"    Mohanad For Systems & Projects 0599908953     ");


        jTextArea1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(100, 100));

        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setText("طباعة");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 204));
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            jTextArea1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(PrintActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PrintActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PrintActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PrintActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PrintActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PrintActivity().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
