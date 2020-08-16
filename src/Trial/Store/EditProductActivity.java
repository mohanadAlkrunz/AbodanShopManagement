/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial.Store;

import Main.MainActivity;
import static Trial.Store.ViewProductsActivity.productsList;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import model.Product;


/**
 *
 * @author DELL
 */
public class EditProductActivity extends javax.swing.JFrame {
  int f=0;
        int productID;

    /**
     * Creates new form ProductActivity
     */
    public EditProductActivity(int productID) {
        initComponents();
            this.productID=productID;
               UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
      //   System.out.println("ProductID is " +productID);
         showDefault(productID);

    }

    private void showDefault(int productID){
           try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s.execute("select ID,PRODUCT_NAME,PRODUCT_PRICE,PRODAUCT_SALE_PRICE,QUANTITY from Products");



        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query



         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {
                if(productID==rs.getInt("ID")){
                    productName.setText(rs.getString("PRODUCT_NAME"));
                     productPrice.setText(rs.getDouble("PRODUCT_PRICE")+"");
                     productSalePrice.setText(rs.getDouble("PRODAUCT_SALE_PRICE")+"");
                       quantity.setText(rs.getInt("QUANTITY")+"");

                }


     }

     con.commit();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        productPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        quantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        productSalePrice = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        deleteProduct = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("تعديل صنف");
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("بيانات الصنف");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(520, 80, 200, 100);

        jPanel2.setBackground(new java.awt.Color(0, 11, 106));
        jPanel2.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("محلات أبودان للأدوات الكهربائية");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(40, 20, 260, 29);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-conflict-48.png"))); // NOI18N
        jPanel2.add(jLabel9);
        jLabel9.setBounds(120, 50, 50, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(-10, 0, 910, 130);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("الاسم :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(680, 240, 80, 29);

        productName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        productName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        productName.setEnabled(false);
        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        jPanel1.add(productName);
        productName.setBounds(370, 230, 250, 40);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("سعر الجملة :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(650, 320, 110, 29);

        productPrice.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        productPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        productPrice.setEnabled(false);
        productPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPriceActionPerformed(evt);
            }
        });
        jPanel1.add(productPrice);
        productPrice.setBounds(470, 310, 150, 40);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("الكمية :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(680, 400, 80, 29);

        quantity.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        quantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantity.setEnabled(false);
        jPanel1.add(quantity);
        quantity.setBounds(470, 390, 150, 40);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("سعر البيع :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(330, 320, 110, 29);

        productSalePrice.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        productSalePrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        productSalePrice.setEnabled(false);
        productSalePrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productSalePriceActionPerformed(evt);
            }
        });
        jPanel1.add(productSalePrice);
        productSalePrice.setBounds(180, 310, 150, 40);

        save.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        save.setText("حفظ");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save);
        save.setBounds(60, 500, 160, 37);

        cancel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        cancel.setText("تعديل");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel);
        cancel.setBounds(230, 500, 160, 37);

        jPanel4.setBackground(new java.awt.Color(0, 11, 106));

        back.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setText("رجوع");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(30, 140, 110, 30);

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));

        deleteProduct.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        deleteProduct.setForeground(new java.awt.Color(255, 255, 255));
        deleteProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteProduct.setText("حذف الصنف");
        deleteProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteProductMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deleteProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(deleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(30, 180, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPriceActionPerformed

    private void productSalePriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productSalePriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productSalePriceActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        if(productName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "اسم المنتج مطلوب");
            return;
        }

        if(productPrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "سعر الجملة مطلوب");
            return;
        }

        if(productSalePrice.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "سعر البيع مطلوب");
            return;
        }

        if(quantity.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "الكمية مطلوبة");
            return;
        }



            try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s.execute("select ID,PRODUCT_NAME,PRODUCT_PRICE,PRODAUCT_SALE_PRICE,QUANTITY from Products");



        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query



         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {
                if(productID==rs.getInt("ID")){

                    rs.updateString("PRODUCT_NAME", productName.getText());
                    rs.updateDouble("PRODUCT_PRICE", Double.valueOf(productPrice.getText()));
                     rs.updateDouble("PRODAUCT_SALE_PRICE", Double.valueOf(productSalePrice.getText()));
                     rs.updateInt("QUANTITY", Integer.parseInt(quantity.getText()));
                     rs.updateRow();
                       JOptionPane.showMessageDialog(this, "تمت العملية بنجاح");
                }


     }

     con.commit();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }




//        productName.setText("");
//        productPrice.setText("");
//        productSalePrice.setText("");
//        quantity.setText("");



        // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed



        if(f==0){
           productName.setEnabled(true);
           productPrice.setEnabled(true);
            productSalePrice.setEnabled(true);
             quantity.setEnabled(true);
             f=1;
        }else if(f==1){
              productName.setEnabled(false);
           productPrice.setEnabled(false);
            productSalePrice.setEnabled(false);
             quantity.setEnabled(false);
             f=0;
        }


    }//GEN-LAST:event_cancelActionPerformed

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked



        // TODO add your handling code here:
       ViewProductsActivity vpa=new ViewProductsActivity();
       vpa.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_backMouseClicked

    private void deleteProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteProductMouseClicked
        // TODO add your handling code here:
       try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s.execute("select ID,PRODUCT_NAME,PRODUCT_PRICE,PRODAUCT_SALE_PRICE,QUANTITY from Products");



        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query



         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {
                if(productID==rs.getInt("ID")){

                    rs.deleteRow();
                    JOptionPane.showMessageDialog(this, "تمت العملية بنجاح");
                 productName.setEnabled(false);
           productPrice.setEnabled(false);
            productSalePrice.setEnabled(false);
             quantity.setEnabled(false);
             save.setEnabled(false);
             cancel.setEnabled(false);

                }


     }

     con.commit();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }


    }//GEN-LAST:event_deleteProductMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel deleteProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField productName;
    private javax.swing.JTextField productPrice;
    private javax.swing.JTextField productSalePrice;
    private javax.swing.JTextField quantity;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
