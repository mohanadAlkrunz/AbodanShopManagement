/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial.Store;

import Main.MainActivity;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Product;

/**
 *
 * @author DELL
 */
public class ViewProductsActivity extends javax.swing.JFrame {

     static  List<Product> productsList=new ArrayList<>();
Object[] columns = {"الكمية", "سعر البيع","سعر الجملة","اسم الصنف","م"};

    /**
     * Creates new form ViewProductsActivity
     */
    public ViewProductsActivity() {
        initComponents();
   UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
        Font font = new Font("Arial", Font.PLAIN,18);
jTable2.setFont(font);
         jTable2.getTableHeader().setFont(font);



getData();
 DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)  jTable2.getTableHeader().getDefaultRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);
 jTable2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                   int index = jTable2.getSelectedRow();

       TableModel model = jTable2.getModel();

       String value1 = model.getValueAt(index, 4).toString();
                EditProductActivity epa =new EditProductActivity(Integer.parseInt(value1));
                epa.setVisible(true);
                setVisible(false);


            }

            @Override
            public void mousePressed(MouseEvent e) {
             }

            @Override
            public void mouseReleased(MouseEvent e) {
             }

            @Override
            public void mouseEntered(MouseEvent e) {
             }

            @Override
            public void mouseExited(MouseEvent e) {
             }
        });

 productName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                getDataSpecific(productName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                if(productName.getText().isEmpty()){
                    getData();
                }else{
                    getDataSpecific(productName.getText());
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    private void getDataSpecific(String  name){

        productsList.clear();

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
                if(rs.getString("PRODUCT_NAME").contains(name)){
          Product product=new Product();
          product.setId(rs.getInt("ID"));
          product.setName(rs.getString("PRODUCT_NAME"));
          product.setOriginalPrice(rs.getDouble("PRODUCT_PRICE"));
           product.setSalePrice(rs.getDouble("PRODAUCT_SALE_PRICE"));
            product.setQuantity(rs.getInt("QUANTITY"));
            productsList.add(product);
                }

     }
            displayData();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        productName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("المخزن");
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("المخزن");

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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("محلات أبودان للأدوات الكهربائية");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(40, 20, 260, 29);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-conflict-48.png"))); // NOI18N
        jPanel2.add(jLabel5);
        jLabel5.setBounds(120, 50, 50, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(-10, 0, 910, 130);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("اسم المنتج:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(630, 200, 100, 29);

        productName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        productName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(productName);
        productName.setBounds(370, 190, 250, 40);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(120),  new Double(5.0),  new Double(3.0), "مفتاح مجوز",  new Integer(1)},
                { new Integer(3),  new Double(2.5),  new Double(1.5), "سلك بندل",  new Integer(2)},
                { new Integer(50),  new Double(6.0),  new Double(4.0), "لمبة 8 واط",  new Integer(3)},
                { new Integer(3),  new Double(100.0),  new Double(70.0), "نجفة فاخر",  new Integer(4)}
            },
            new String [] {
                "الكمية", "سعر البيع", "سعر الجملة", "اسم الصنف", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setRowHeight(25);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(4).setMinWidth(25);
            jTable2.getColumnModel().getColumn(4).setMaxWidth(25);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(70, 260, 670, 270);

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
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(30, 140, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked


            new MainActivity ().setVisible(true);
            this.setVisible(false);
    }//GEN-LAST:event_backMouseClicked



    private   void getData(){

         productsList.clear();

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

          Product product=new Product();
          product.setId(rs.getInt("ID"));
          product.setName(rs.getString("PRODUCT_NAME"));
          product.setOriginalPrice(rs.getDouble("PRODUCT_PRICE"));
           product.setSalePrice(rs.getDouble("PRODAUCT_SALE_PRICE"));
            product.setQuantity(rs.getInt("QUANTITY"));
            productsList.add(product);


     }
            displayData();
     con.commit();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }

    }


    private   void  displayData( ) {
          DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);

          Object[] row = new Object[5];
            for(int i=0;i<productsList.size();i++){
                  row[4]=productsList.get(i).getId()+"";
                  row[3]=productsList.get(i).getName();
                  row[2]=productsList.get(i).getOriginalPrice();
                  row[1]=productsList.get(i).getSalePrice();
                  row[0]=productsList.get(i).getQuantity()+"";
                    model.addRow(row);
                    System.out.println(productsList.get(i).getName());
                  }

           jTable2.setModel(model);
              DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
jTable2.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
 jTable2.getColumnModel().getColumn(4).setMaxWidth(30);
  jTable2.getColumnModel().getColumn(4).setMinWidth(30);

    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField productName;
    // End of variables declaration//GEN-END:variables
}
