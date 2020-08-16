/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial.Customer;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Bill;

/**
 *
 * @author DELL
 */
public class ViewPurchaseHistoryActivity extends javax.swing.JFrame {

    double total = 0;
    double totalPaid = 0;
    double mTotalRemain;
    int customerID;
    private static List<Bill> purchaseHistory = new ArrayList<>();
    Object[] columns = {"ملاحظات", "التاريخ", "الخصم", "المبلغ المتبقي", "المبلغ المدفوع", "المجموع", "اسم الزبون", "م.ز", "م"};

    /**
     * Creates new form ViewPurchaseHistoryActivity
     */
    public ViewPurchaseHistoryActivity(String customerName, int customerId) {
        initComponents();
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
        this.customerID = customerId;
        this.customerName.setText(customerName);
        Font font = new Font("Arial", Font.PLAIN, 18);
        jTable2.setFont(font);
        jTable2.getTableHeader().setFont(font);
 DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)  jTable2.getTableHeader().getDefaultRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);
        getData(0);

        jTable2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = jTable2.getSelectedRow();

                TableModel model = jTable2.getModel();

                String value1 = model.getValueAt(index, 8).toString();
                new BillActivity(Integer.parseInt(value1), customerID, false).setVisible(true);
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
        totalBalance = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        customerName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        editProfile = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        showPaymentsDue = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        paymentSchedule = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        addPayment = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        showAccountDetails = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("السجل");

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
        jPanel3.setBounds(650, 80, 200, 100);

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
        jPanel2.setBounds(-10, 0, 930, 130);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("إجمالي الحساب :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(180, 620, 140, 29);

        totalBalance.setEditable(false);
        totalBalance.setBackground(new java.awt.Color(255, 0, 0));
        totalBalance.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalBalance.setForeground(new java.awt.Color(255, 255, 255));
        totalBalance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalBalance.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(totalBalance);
        totalBalance.setBounds(30, 610, 140, 40);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "20-07-2018", null, null, null, "مهند الكرنز", null,  new Integer(1)},
                {null, "20-07-2019", null, null, null, "مهند الكرنز", null,  new Integer(2)},
                {null, "20-07-2019", null, null, null, "مهند الكرنز", null,  new Integer(3)},
                {null, "20-07-2019", null, null, null, "مهند الكرنز", null,  new Integer(4)}
            },
            new String [] {
                "الخصم", "تاريخ الفاتورة", "المتبقي", "المبلغ المدفوع", "الاجمالي", "اسم الزبون", "م.ز", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setRowHeight(25);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(6).setMinWidth(35);
            jTable2.getColumnModel().getColumn(6).setMaxWidth(35);
            jTable2.getColumnModel().getColumn(7).setMinWidth(30);
            jTable2.getColumnModel().getColumn(7).setMaxWidth(30);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(190, 270, 670, 270);

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

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("الاسم :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(750, 210, 80, 29);

        customerName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        customerName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        customerName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        customerName.setEnabled(false);
        jPanel1.add(customerName);
        customerName.setBounds(490, 200, 250, 40);

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));

        editProfile.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        editProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editProfile.setText("تعديل الملف الشخصي");
        editProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editProfileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(30, 180, 110, 30);

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));

        showPaymentsDue.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        showPaymentsDue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showPaymentsDue.setText("عرض الدفعات");
        showPaymentsDue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPaymentsDueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(showPaymentsDue, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showPaymentsDue, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(30, 300, 110, 30);

        jPanel7.setBackground(new java.awt.Color(153, 153, 153));

        paymentSchedule.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        paymentSchedule.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paymentSchedule.setText("جدولة دفعات");
        paymentSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paymentScheduleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paymentSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7);
        jPanel7.setBounds(30, 260, 110, 30);

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

        addPayment.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addPayment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addPayment.setText("إضافة دفعة");
        addPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPaymentMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(30, 220, 110, 30);

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));

        showAccountDetails.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        showAccountDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showAccountDetails.setText("عرض كشف حساب");
        showAccountDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showAccountDetailsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showAccountDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showAccountDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9);
        jPanel9.setBounds(30, 340, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked

        // TODO add your handling code here:
        ViewCustomersActivity customersActivity = new ViewCustomersActivity();
        customersActivity.setVisible(true);
        setVisible(false);

    }//GEN-LAST:event_backMouseClicked

    private void editProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileMouseClicked
        // TODO add your handling code here:

        new EditCustomerActivity(customerID).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_editProfileMouseClicked

    private void addPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPaymentMouseClicked

// TODO add your handling code here:
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        double payment = Double.valueOf(JOptionPane.showInputDialog(null, "أدخل قيمة الدفعة", "إضافة دفعة", JOptionPane.INFORMATION_MESSAGE));
        System.out.println(payment + "   >><<");

        if (payment > 0) {

            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                File f = new File("AboDan shops.accdb");
                String path = f.getAbsolutePath();

                //  now we can get the connection from the DriverManager
                String database = "jdbc:ucanaccess://" + path;

                Connection con = DriverManager.getConnection(database, "", "");
                PreparedStatement ps = con.prepareStatement("insert into PurchaseHistory (CUSTOMER_ID,CUSTOMER_NAME,TOTAL_AMOUNT,PAID,REMAIN,PURCHASE_DATE,DISCOUNT,NOTE) values(?,?,?,?,?,?,?,?)");

                ps.setInt(1, customerID);
                ps.setString(2, customerName.getText());
                ps.setDouble(3, payment);
                ps.setDouble(4, payment);
                ps.setDouble(5, 0);
                ps.setString(6, formatter.format(date));
                ps.setDouble(7, 0);
                ps.setString(8, "دفعة");
                ps.addBatch();
                ps.clearParameters();

                con.setAutoCommit(false);

                Savepoint point1 = con.setSavepoint();
                int[] updateCounts = ps.executeBatch();

                Savepoint point2 = con.setSavepoint();

                ps.close();

                con.commit();
                con.close();
//                getData(payment);
new ViewPurchaseHistoryActivity(customerName.getText(), customerID).setVisible(true);

setVisible(false);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "القيمة المدخلة غير صحيحة");
        }


    }//GEN-LAST:event_addPaymentMouseClicked

    private void paymentScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentScheduleMouseClicked
        // TODO add your handling code here:

        double value = Double.valueOf(JOptionPane.showInputDialog(null, "أدخل قيمة الدفعة", "إضافة دفعة", JOptionPane.INFORMATION_MESSAGE));

        if (value > 0) {

            String date = JOptionPane.showInputDialog(null, "أدخل التاريخ  مثال::  23/07/2020", "أدخل تاريخ الاستحقاق", JOptionPane.INFORMATION_MESSAGE);
            if (!date.isEmpty() && date.length() == 10) {

                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    // set this to a MS Access DB you have on your machine

                    File f = new File("AboDan shops.accdb");
                    String path = f.getAbsolutePath();

                    //  now we can get the connection from the DriverManager
                    String database = "jdbc:ucanaccess://" + path;

                    Connection con = DriverManager.getConnection(database, "", "");
                    PreparedStatement ps = con.prepareStatement("insert into Payments (CUSTOMER_NAME,TOTAL,CUSTOMER_ID,PAYMENT_DUE,STATE) values(?,?,?,?,?)");
                    System.out.println(customerName.getText() +" >>><<<<");
                    ps.setString(1, customerName.getText());
                    ps.setDouble(2, value);
                    ps.setInt(3, customerID);
                    ps.setString(4, date);
                    ps.setString(5, "غير مدفوع");

                    ps.addBatch();
                    ps.clearParameters();

                    con.setAutoCommit(false);

                    Savepoint point1 = con.setSavepoint();
                    int[] updateCounts = ps.executeBatch();

                    Savepoint point2 = con.setSavepoint();



                    con.commit();

                    JOptionPane.showMessageDialog(this, "تمت العملية بنجاح");


                } catch (Exception e) {
                    e.printStackTrace();
                    // System.out.println("Error: " + e);
                }

            } else {
                JOptionPane.showMessageDialog(null, "التاريخ المدخل خاطئ");
                paymentScheduleMouseClicked(evt);
            }

        } else {
            JOptionPane.showMessageDialog(null, "القيمة المدخلة خاطئة");
            paymentScheduleMouseClicked(evt);
        }


    }//GEN-LAST:event_paymentScheduleMouseClicked

    private void showPaymentsDueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPaymentsDueMouseClicked
        // TODO add your handling code here:

        new ViewPaymentsSchedule(customerID, customerName.getText()).setVisible(true);
        System.out.println("customerName.getText()");
        setVisible(false);

    }//GEN-LAST:event_showPaymentsDueMouseClicked

    private void showAccountDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showAccountDetailsMouseClicked
        // TODO add your handling code here:

        

    }//GEN-LAST:event_showAccountDetailsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addPayment;
    private javax.swing.JLabel back;
    private javax.swing.JTextField customerName;
    private javax.swing.JLabel editProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel paymentSchedule;
    private javax.swing.JLabel showAccountDetails;
    private javax.swing.JLabel showPaymentsDue;
    private javax.swing.JTextField totalBalance;
    // End of variables declaration//GEN-END:variables

    private void getData(double payment) {

          total = 0;
        totalPaid = 0;
        mTotalRemain=0;


        purchaseHistory.clear();
        double mPayment=payment;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL_AMOUNT,PAID,REMAIN,PURCHASE_DATE ,DISCOUNT,NOTE from PurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                    if (customerID == rs.getInt("CUSTOMER_ID")) {
                        Bill bill = new Bill();
                        bill.setId(rs.getInt("ID"));
                        bill.setCUSTOMER_ID(rs.getInt("CUSTOMER_ID"));
                        bill.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
                        bill.setTOTAL_AMOUNT(rs.getDouble("TOTAL_AMOUNT"));
                        bill.setPaid(rs.getDouble("PAID"));
                        bill.setRemain(rs.getDouble("REMAIN"));
                         bill.setNote(rs.getString("NOTE"));
                        if (rs.getString("NOTE").equalsIgnoreCase("دفعة")) {

                            if(mPayment ==0){
                                    totalPaid+=rs.getDouble("PAID");

                            }else if (mPayment >0){

                                    totalPaid += payment;
                                    mPayment=0;
                             }

                        } else {

                            total += rs.getDouble("TOTAL_AMOUNT");
                            totalPaid += rs.getDouble("PAID");
                        }

                        bill.setPURCHASE_DATE(rs.getString("PURCHASE_DATE"));

                        bill.setDiscount(rs.getDouble("DISCOUNT"));
                        purchaseHistory.add(bill);
                    }

                }
                displayData();
                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }

    }

    private void displayData() {

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
 Collections.reverse(purchaseHistory);
        Object[] row = new Object[9];
        for (int i = 0; i < purchaseHistory.size(); i++) {
            row[0] = purchaseHistory.get(i).getNote();
            row[1] = purchaseHistory.get(i).getPURCHASE_DATE();
            row[2] = purchaseHistory.get(i).getDiscount() + "";
            row[3] = purchaseHistory.get(i).getRemain() + "";
            row[4] = purchaseHistory.get(i).getPaid() + "";
            row[5] = purchaseHistory.get(i).getTOTAL_AMOUNT() + "";
            row[6] = purchaseHistory.get(i).getCUSTOMER_NAME();
            row[7] = purchaseHistory.get(i).getCUSTOMER_ID() + "";
            row[8] = purchaseHistory.get(i).getId() + "";
            model.addRow(row);

        }


        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,TOTAL from Customers");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {
                    if (customerID == rs.getInt("ID")) {
                        mTotalRemain = totalPaid - total;
                        System.out.println("paid ="+totalPaid);

                       System.out.println("total ="+total);

                            System.out.println("remain ="+mTotalRemain);
                        rs.updateDouble("TOTAL", mTotalRemain);
                        rs.updateRow();
                        totalBalance.setText(mTotalRemain + "");
        total = 0;
        totalPaid = 0;
        mTotalRemain=0;
                        System.out.println("DONE");
                    }

                }

                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }

        jTable2.setModel(model);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        jTable2.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(7).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(7).setMinWidth(30);
        jTable2.getColumnModel().getColumn(8).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(8).setMinWidth(30);


    }
}
