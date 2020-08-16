/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial.Companies;

import Main.MainActivity;
import Trial.Customer.ViewPurchaseHistoryActivity;
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
import model.Company;
import model.Customer;

/**
 *
 * @author DELL
 */
public class ViewCompaniesActivity extends javax.swing.JFrame {

     static  List<Company> companiesList=new ArrayList<>();
Object[] columns = {"الإجمالي","رقم الهاتف","اسم الشركة","م"};



    public ViewCompaniesActivity() {
        initComponents();
UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));

        Font font = new Font("Arial", Font.PLAIN,18);
jTable2.setFont(font);
         jTable2.getTableHeader().setFont(font);
          DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)  jTable2.getTableHeader().getDefaultRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);

getData();
    companyName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                    getDataSpecific(companyName.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                    if(companyName.getText().isEmpty()){
                        getData();
                    }else{
                                           getDataSpecific(companyName.getText());

                    }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
 jTable2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                   int index = jTable2.getSelectedRow();

       TableModel model = jTable2.getModel();

       String value1 = model.getValueAt(index, 3).toString();
            //    JOptionPane.showMessageDialog(rootPane, value1+" MOHANAD");
                ViewPurchaseHistoryCompanyActivity purchaseHistoryActivity=new ViewPurchaseHistoryCompanyActivity(model.getValueAt(index, 2).toString(), Integer.parseInt(value1));
                purchaseHistoryActivity.setVisible(true);
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
    }


   private void getDataSpecific(String name){

       companiesList.clear();



    double total=0,totalPaid=0;
          try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s.execute("select ID,COMPANY_NAME,COMPANY_PHONE,TOTAL from Companies");



        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query



         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {

                if(rs.getString("COMPANY_NAME").contains(name)){
          Company company=new Company();
          company.setId(rs.getInt("ID"));
          company.setName(rs.getString("COMPANY_NAME"));
          company.setPhone(rs.getString("COMPANY_PHONE"));


           try{
              Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con1.setAutoCommit(false);
            Statement s1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s1.execute("select COMPANY_ID,TOTAL_AMOUNT,PAID from CompaniesPurchaseHistory");

            ResultSet rs1 = s1.getResultSet(); // get any ResultSet that came from our query

            if (rs1 != null) { // if rs == null, then there is no ResultSet to view

                while (rs1.next())// this will step through our data row-by-row
                {

                    if (company.getId() == rs1.getInt("COMPANY_ID")) {


                        total += rs1.getDouble("TOTAL_AMOUNT");
                        totalPaid += rs1.getDouble("PAID");


                    }

                }
                displayData();
                con1.commit();
                con1.close();
            }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }
            company.setTotal(totalPaid-total);
            rs.updateDouble("TOTAL", totalPaid-total);
            totalPaid=0;
            total=0;
            companiesList.add(company);


     }
            displayData();
     con.commit();
            } }} catch (Exception e) {
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
        companyName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        newCustomer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("الشركات");

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
        jLabel2.setText("الاسم :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(630, 200, 80, 29);

        companyName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        companyName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(companyName);
        companyName.setBounds(370, 190, 250, 40);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Double(-150.0), "0599908953", "مهندالكرنز",  new Integer(1)},
                { new Double(-200.0), "0597228002", "اياد الكرنز",  new Integer(2)},
                { new Double(-130.5), "0599123456", "عاصف ابودان",  new Integer(3)},
                { new Double(-101.0), "0599123456", "محمد ابودان",  new Integer(4)}
            },
            new String [] {
                "اجمالي الحساب", "رقم الهاتف", "اسم الشركة", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setRowHeight(25);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(3).setMinWidth(25);
            jTable2.getColumnModel().getColumn(3).setMaxWidth(25);
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

        jPanel5.setBackground(new java.awt.Color(0, 11, 106));

        newCustomer.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        newCustomer.setForeground(new java.awt.Color(255, 255, 255));
        newCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newCustomer.setText("إضافة شركة جديدة");
        newCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newCustomerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(newCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(30, 180, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
           this.setVisible(false);
            new MainActivity ().setVisible(true);
    }//GEN-LAST:event_backMouseClicked

    private void newCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newCustomerMouseClicked
        // TODO add your handling code here:

        AddCompanyActivity addCompanyActivity = new AddCompanyActivity();
        addCompanyActivity.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_newCustomerMouseClicked


     private   void getData(){

         companiesList.clear();




    double total=0,totalPaid=0;
          try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s.execute("select ID,COMPANY_NAME,COMPANY_PHONE,TOTAL from Companies");



        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query



         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {

          Company company=new Company();
          company.setId(rs.getInt("ID"));
          company.setName(rs.getString("COMPANY_NAME"));
          company.setPhone(rs.getString("COMPANY_PHONE"));


           try{
              Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con1.setAutoCommit(false);
            Statement s1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s1.execute("select COMPANY_ID,TOTAL_AMOUNT,PAID from CompaniesPurchaseHistory");

            ResultSet rs1 = s1.getResultSet(); // get any ResultSet that came from our query

            if (rs1 != null) { // if rs == null, then there is no ResultSet to view

                while (rs1.next())// this will step through our data row-by-row
                {

                    if (company.getId() == rs1.getInt("COMPANY_ID")) {


                        total += rs1.getDouble("TOTAL_AMOUNT");
                        totalPaid += rs1.getDouble("PAID");


                    }

                }
                displayData();
                con1.commit();
                con1.close();
            }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }
            company.setTotal(totalPaid-total);
            rs.updateDouble("TOTAL", totalPaid-total);
            totalPaid=0;
            total=0;
            companiesList.add(company);


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

          Object[] row = new Object[4];
            for(int i=0;i<companiesList.size();i++){
                  row[3]=companiesList.get(i).getId()+"";
                  row[2]=companiesList.get(i).getName();
                  row[1]=companiesList.get(i).getPhone();
                   row[0]=companiesList.get(i).getTotal()+"";
                    model.addRow(row);
                    System.out.println(companiesList.get(i).getName());
                  }

           jTable2.setModel(model);
              DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
jTable2.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
jTable2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
//jTable2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
 jTable2.getColumnModel().getColumn(3).setMaxWidth(30);
  jTable2.getColumnModel().getColumn(3).setMinWidth(30);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JTextField companyName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel newCustomer;
    // End of variables declaration//GEN-END:variables
}
