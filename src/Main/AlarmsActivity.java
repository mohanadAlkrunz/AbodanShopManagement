/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
import java.util.Date;
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
import model.Bill;
import model.Payment;

/**
 *
 * @author DELL
 */
public class AlarmsActivity extends javax.swing.JFrame {


        Object[] columns = {"الحالة", "التاريخ", "قيمة الدفعة", "اسم الزبون", "م.ز", "م"};
            private static List<Payment> paymentsList = new ArrayList<>();

    public AlarmsActivity() {
        initComponents();

UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
          Font font = new Font("Arial", Font.PLAIN, 18);
          jTable2.setFont(font);
        jTable2.getTableHeader().setFont(font);
         DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)  jTable2.getTableHeader().getDefaultRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);
        dateTV.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                if(dateTV.getText().length() <= 10){
                    getSpecificDatePayments(dateTV.getText());
                }else{
                    getData();
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 if(dateTV.getText().length() == 10){
                    getSpecificDatePayments(dateTV.getText());
                }else{
                    getData();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
      getData();


        jTable2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = jTable2.getSelectedRow();

                TableModel model = jTable2.getModel();

                String value1 = model.getValueAt(index, 5).toString();
                String value2=model.getValueAt(index, 4).toString();
                String value3=model.getValueAt(index, 0).toString();





                    if(value3.equalsIgnoreCase("تم الدفع")){
                        JOptionPane.showMessageDialog(null, "تم الدفع");
                    }else{
                        String[] options = new String[2];
options[0] = new String(" سداد الاستحقاق");
options[1] = new String("حذف الاستحقاق");

                int s=JOptionPane.showOptionDialog(null, "رجاءً قم بتحديد الإجراء","تحديد الإجراء",0,JOptionPane.INFORMATION_MESSAGE,null,options,null);

                if(s==0){
                            Bill bill =new Bill();
                            bill.setCUSTOMER_ID(Integer.parseInt(value2));
                            bill.setTOTAL_AMOUNT(Double.valueOf(model.getValueAt(index, 2).toString()));
                            bill.setPaid(Double.valueOf(model.getValueAt(index, 2).toString()));
                            bill.setRemain(0);
                            bill.setDiscount(0);
                            bill.setPURCHASE_DATE((model.getValueAt(index, 1).toString()));
                            bill.setNote("دفعة");


                            try{
 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                File f = new File("AboDan shops.accdb");
                String path = f.getAbsolutePath();

                //  now we can get the connection from the DriverManager
                String database = "jdbc:ucanaccess://" + path;
                Connection con = DriverManager.getConnection(database, "", "");
                PreparedStatement ps = con.prepareStatement("insert into PurchaseHistory (CUSTOMER_ID,CUSTOMER_NAME,TOTAL_AMOUNT,PAID,REMAIN,PURCHASE_DATE,DISCOUNT,NOTE) values(?,?,?,?,?,?,?,?)");

                ps.setInt(1, Integer.parseInt(value2));
                ps.setString(2, model.getValueAt(index, 3).toString());
                ps.setDouble(3, bill.getTOTAL_AMOUNT() );
                ps.setDouble(4, bill.getPaid());
                ps.setDouble(5, 0);
                ps.setString(6, bill.getPURCHASE_DATE());
                ps.setDouble(7, 0);
                ps.setString(8, "دفعة");
                ps.addBatch();
                ps.clearParameters();

                con.setAutoCommit(false);

                Savepoint point1 = con.setSavepoint();
                int[] updateCounts = ps.executeBatch();

                Savepoint point2 = con.setSavepoint();

//            con.rollback(point2);
                ps.close();

                con.commit();
                con.close();

                Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con1.setAutoCommit(false);
        Statement s1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s1.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL,PAYMENT_DUE,STATE from Payments");



        ResultSet rs1 = s1.getResultSet(); // get any ResultSet that came from our query



         if (rs1 != null){ // if rs == null, then there is no ResultSet to view

            while ( rs1.next())// this will step through our data row-by-row
            {
                if(Integer.parseInt(value1)==rs1.getInt("ID")){
                        System.out.println("تم الدفع الأن");
                    rs1.updateString("STATE", "تم الدفع");
                    rs1.updateRow();
                    JOptionPane.showMessageDialog(null, "تمت العملية بنجاح");


                }


     }

     con1.commit();
     all.setSelected(true);
    getData();

         }
                 }catch(Exception e2){
                 e2.printStackTrace();
                 }


                    }else if(s==1){

                 try{
                  Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

                File f = new File("AboDan shops.accdb");
                String path = f.getAbsolutePath();

                //  now we can get the connection from the DriverManager
                String database = "jdbc:ucanaccess://" + path;

                          Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con1.setAutoCommit(false);
        Statement s1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s1.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL,PAYMENT_DUE,STATE from Payments");



        ResultSet rs1 = s1.getResultSet(); // get any ResultSet that came from our query



         if (rs1 != null){ // if rs == null, then there is no ResultSet to view

            while ( rs1.next())// this will step through our data row-by-row
            {
                if(Integer.parseInt(value1)==rs1.getInt("ID")){

                  rs1.deleteRow();
                    JOptionPane.showMessageDialog(null, "تمت العملية بنجاح");


                }


     }

     con1.commit();
     rs1.close();
     con1.close();
     all.setSelected(true);
     getData();

         }

                     }catch(Exception e3){
e3.printStackTrace();
                            }

                    }



            }}

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

        selectDate = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        specificDate = new javax.swing.JRadioButton();
        todayPayments = new javax.swing.JRadioButton();
        monthPayments = new javax.swing.JRadioButton();
        dateTV = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        all = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("سجل الدفعات");

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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("محلات أبودان للأدوات الكهربائية");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(40, 20, 260, 29);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-conflict-48.png"))); // NOI18N
        jPanel2.add(jLabel6);
        jLabel6.setBounds(120, 50, 50, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(-10, 0, 910, 130);

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

        specificDate.setBackground(new java.awt.Color(250, 250, 250));
        selectDate.add(specificDate);
        specificDate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        specificDate.setText("تاريخ محدد");
        specificDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        specificDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        specificDate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        specificDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specificDateActionPerformed(evt);
            }
        });
        jPanel1.add(specificDate);
        specificDate.setBounds(310, 200, 107, 40);

        todayPayments.setBackground(new java.awt.Color(250, 250, 250));
        selectDate.add(todayPayments);
        todayPayments.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        todayPayments.setText("هذا اليوم");
        todayPayments.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        todayPayments.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        todayPayments.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        todayPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayPaymentsActionPerformed(evt);
            }
        });
        jPanel1.add(todayPayments);
        todayPayments.setBounds(570, 200, 79, 40);

        monthPayments.setBackground(new java.awt.Color(250, 250, 250));
        selectDate.add(monthPayments);
        monthPayments.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        monthPayments.setText("هذا الشهر");
        monthPayments.setToolTipText("");
        monthPayments.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        monthPayments.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        monthPayments.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        monthPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthPaymentsActionPerformed(evt);
            }
        });
        jPanel1.add(monthPayments);
        monthPayments.setBounds(430, 200, 107, 40);

        dateTV.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        dateTV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        dateTV.setText("23/07/2020");
        dateTV.setEnabled(false);
        jPanel1.add(dateTV);
        dateTV.setBounds(160, 200, 140, 30);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "20-07-2018", null, "مهند الكرنز", null,  new Integer(1)},
                {null, "20-07-2019", null, "مهند الكرنز", null,  new Integer(2)},
                {null, "20-07-2019", null, "مهند الكرنز", null,  new Integer(3)},
                {null, "20-07-2019", null, "مهند الكرنز", null,  new Integer(4)}
            },
            new String [] {
                "الحالة", "تاريخ الاستحقاق", "الاجمالي", "اسم الزبون", "م.ز", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setRowHeight(25);
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(60, 260, 700, 310);

        all.setBackground(new java.awt.Color(250, 250, 250));
        selectDate.add(all);
        all.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        all.setSelected(true);
        all.setText("الكل");
        all.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        all.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        all.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allActionPerformed(evt);
            }
        });
        jPanel1.add(all);
        all.setBounds(650, 210, 107, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:

        MainActivity companiesActivity=new MainActivity();
        companiesActivity.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backMouseClicked

    private void monthPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthPaymentsActionPerformed
        // TODO add your handling code here:
        dateTV.setEnabled(false);
        getDataMonth();
    }//GEN-LAST:event_monthPaymentsActionPerformed

    private void todayPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayPaymentsActionPerformed
        // TODO add your handling code here:
dateTV.setEnabled(false);
        getDataToday();
    }//GEN-LAST:event_todayPaymentsActionPerformed

    private void specificDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specificDateActionPerformed
        // TODO add your handling code here:
        dateTV.setEnabled(true);
        getData();
    }//GEN-LAST:event_specificDateActionPerformed

    private void allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allActionPerformed


dateTV.setEnabled(false);
        // TODO add your handling code here:
        getData();
    }//GEN-LAST:event_allActionPerformed


      private void getData(){
          paymentsList.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL,PAYMENT_DUE,STATE from Payments");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

 if(rs.getString("STATE").equalsIgnoreCase("غير مدفوع")){

                        Payment payment=new Payment();
                        payment.setId(rs.getInt("ID"));
                        payment.setCustomerId(rs.getInt("CUSTOMER_ID"));
                        payment.setCustomerName(rs.getString("CUSTOMER_NAME"));
                        payment.setPaymentDue(rs.getString("PAYMENT_DUE"));
                        payment.setTotal(rs.getDouble("TOTAL"));
                        payment.setState(rs.getString("STATE"));

                        paymentsList.add(payment);
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

        Object[] row = new Object[6];
        for (int i = 0; i < paymentsList.size(); i++) {
            row[5] = paymentsList.get(i).getId();
            row[4] = paymentsList.get(i).getCustomerId();
            row[3] = paymentsList.get(i).getCustomerName() + "";
            row[2] = paymentsList.get(i).getTotal() + "";
            row[1] = paymentsList.get(i).getPaymentDue() + "";
             row[0] = paymentsList.get(i).getState()+ "";

            model.addRow(row);

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


        jTable2.getColumnModel().getColumn(4).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(4).setMinWidth(30);
        jTable2.getColumnModel().getColumn(5).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(5).setMinWidth(30);


    }

    private void  getSpecificDatePayments(String date){

            paymentsList.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL,PAYMENT_DUE,STATE from Payments");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {


                    if(rs.getString("PAYMENT_DUE").contains(date) && rs.getString("STATE").equalsIgnoreCase("غير مدفوع")) {

                        Payment payment=new Payment();
                        payment.setId(rs.getInt("ID"));
                        payment.setCustomerId(rs.getInt("CUSTOMER_ID"));
                        payment.setCustomerName(rs.getString("CUSTOMER_NAME"));
                        payment.setPaymentDue(rs.getString("PAYMENT_DUE"));
                        payment.setTotal(rs.getDouble("TOTAL"));
                        payment.setState(rs.getString("STATE"));

                        paymentsList.add(payment);


                }}
                displayData();
                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton all;
    private javax.swing.JLabel back;
    private javax.swing.JTextField dateTV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton monthPayments;
    private javax.swing.ButtonGroup selectDate;
    private javax.swing.JRadioButton specificDate;
    private javax.swing.JRadioButton todayPayments;
    // End of variables declaration//GEN-END:variables

    private void getDataMonth() {

              SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String month=formatter.format(date).substring(3);
          paymentsList.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL,PAYMENT_DUE,STATE from Payments");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {


                    if(month.equalsIgnoreCase(rs.getString("PAYMENT_DUE").substring(3))&&rs.getString("STATE").equalsIgnoreCase("غير مدفوع")){

                        Payment payment=new Payment();
                        payment.setId(rs.getInt("ID"));
                        payment.setCustomerId(rs.getInt("CUSTOMER_ID"));
                        payment.setCustomerName(rs.getString("CUSTOMER_NAME"));
                        payment.setPaymentDue(rs.getString("PAYMENT_DUE"));
                        payment.setTotal(rs.getDouble("TOTAL"));
                        payment.setState(rs.getString("STATE"));

                        paymentsList.add(payment);


                }}
                displayData();
                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }

    }

    private void getDataToday() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
             String mToday=formatter.format(date);
          paymentsList.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL,PAYMENT_DUE,STATE from Payments");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {


                    if(mToday.equalsIgnoreCase(rs.getString("PAYMENT_DUE"))&&rs.getString("STATE").equalsIgnoreCase("غير مدفوع")){

                        Payment payment=new Payment();
                        payment.setId(rs.getInt("ID"));
                        payment.setCustomerId(rs.getInt("CUSTOMER_ID"));
                        payment.setCustomerName(rs.getString("CUSTOMER_NAME"));
                        payment.setPaymentDue(rs.getString("PAYMENT_DUE"));
                        payment.setTotal(rs.getDouble("TOTAL"));
                        payment.setState(rs.getString("STATE"));

                        paymentsList.add(payment);


                }}
                displayData();
                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }
    }
}
