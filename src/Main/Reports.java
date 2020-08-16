/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.MainActivity.purchaseHistory;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import model.Bill;
import model.Company;

/**
 *
 * @author DELL
 */
public class Reports extends javax.swing.JFrame {

    int flag=0;

    /**
     * Creates new form Reports
     */
    public Reports() {
        initComponents();
UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
        flag=0;
        getTotalCompaniesDepts();
        getTotalCustomersDepts();
        getTotalCustomersPaid();
        getTotalCompaniesPaid();
        getTotalProfits();
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalCompaniesDepts = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        totalCustomersDepts = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        totalCustomersPaid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        totalProfits = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalCompainesPaid = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        months = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        year = new javax.swing.JTextField();
        fillter = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(100, 100));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("التقارير");

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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("تحديد السنة");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(400, 290, 70, 50);

        totalCompaniesDepts.setEditable(false);
        totalCompaniesDepts.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalCompaniesDepts.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalCompaniesDepts.setText("0");
        jPanel1.add(totalCompaniesDepts);
        totalCompaniesDepts.setBounds(490, 210, 120, 40);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("إجمالي ديون الزبائن");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(300, 220, 150, 30);

        totalCustomersDepts.setEditable(false);
        totalCustomersDepts.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalCustomersDepts.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalCustomersDepts.setText("0");
        jPanel1.add(totalCustomersDepts);
        totalCustomersDepts.setBounds(160, 210, 120, 40);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("إجمالي المقبوضات");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(620, 490, 150, 30);

        totalCustomersPaid.setEditable(false);
        totalCustomersPaid.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalCustomersPaid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalCustomersPaid.setText("0");
        jPanel1.add(totalCustomersPaid);
        totalCustomersPaid.setBounds(480, 480, 120, 40);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("إجمالي الأرباح");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(620, 370, 150, 30);

        totalProfits.setEditable(false);
        totalProfits.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalProfits.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalProfits.setText("0");
        jPanel1.add(totalProfits);
        totalProfits.setBounds(480, 360, 120, 40);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("إجمالي المدفوعات للشركات");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(610, 430, 160, 30);

        totalCompainesPaid.setEditable(false);
        totalCompainesPaid.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        totalCompainesPaid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalCompainesPaid.setText("0");
        jPanel1.add(totalCompainesPaid);
        totalCompainesPaid.setBounds(480, 420, 120, 40);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("إجمالي ديون الشركات");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(630, 210, 150, 50);

        months.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        months.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "الكل", "شهر يناير 1", "شهر فبراير 2", "شهر مارس 3", "شهر ابريل 4", "شهر مايو 5", "شهر يونيو 6", "شهر يوليو7", "شهر أغسطس8", "شهر سبتمبر9", "شهر أكتوبر10", "شهر نوفمبر 11", "شهر ديسمبر 12" }));
        jPanel1.add(months);
        months.setBounds(520, 300, 160, 28);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("تحديد الشهر");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(680, 290, 100, 50);

        year.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        year.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        year.setText("2020");
        jPanel1.add(year);
        year.setBounds(310, 290, 80, 40);

        fillter.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        fillter.setText("فلترة");
        fillter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillterActionPerformed(evt);
            }
        });
        jPanel1.add(fillter);
        fillter.setBounds(210, 300, 61, 30);

        jPanel5.setBackground(new java.awt.Color(0, 11, 106));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(0, 270, 820, 10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void fillterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillterActionPerformed
        // TODO add your handling code here:


        flag=1;
//        System.out.println( months.getSelectedIndex()+"");
        getTotalCustomersPaid();
        getTotalCompaniesPaid();
        getTotalProfits();
    }//GEN-LAST:event_fillterActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JButton fillter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JComboBox<String> months;
    private javax.swing.JTextField totalCompainesPaid;
    private javax.swing.JTextField totalCompaniesDepts;
    private javax.swing.JTextField totalCustomersDepts;
    private javax.swing.JTextField totalCustomersPaid;
    private javax.swing.JTextField totalProfits;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables

    private void getTotalCompaniesDepts() {


        double totalDepts=0;


             try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       s.execute("select TOTAL from Companies");
        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query
         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {
                if(rs.getDouble("TOTAL")<0){
                     totalDepts-=   rs.getDouble("TOTAL");
                }

     }
            totalCompaniesDepts.setText(totalDepts+"");
     con.commit();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }




    }

      private void getTotalCustomersDepts() {


        double totalDepts=0;


              try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
       s.execute("select TOTAL from Customers");
        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query
         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {
                if(rs.getDouble("TOTAL")<0){
                     totalDepts-=   rs.getDouble("TOTAL");
                }

     }
            totalCustomersDepts.setText(totalDepts+"");
     con.commit();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }


    }


      private void getTotalCustomersPaid(){

          double total=0;

          if(flag ==0){
               try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select PAID from PurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                   if(rs.getDouble("PAID")>0){
                       total+=rs.getDouble("PAID");
                   }

                }

                totalCustomersPaid.setText(total+"");
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }
          }else{

              String mMonth="";
              if(months.getSelectedIndex()<=9){
                  mMonth="0"+months.getSelectedIndex();
              }else{
                  mMonth=months.getSelectedIndex()+"";
              }

                   try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select PAID,PURCHASE_DATE from PurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                    if(!year.getText().isEmpty()   && months.getSelectedIndex() ==0 && year.getText()
                           .equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(6))){
                         total+=rs.getDouble("PAID");
                    } else if(!year.getText().isEmpty()){
                   if(rs.getDouble("PAID")>0 && mMonth.equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(3, 5))&& year.getText()
                           .equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(6))){
                       total+=rs.getDouble("PAID");

                   }}else{
                         if(rs.getDouble("PAID")>0 && mMonth.equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(3, 5))){
                       total+=rs.getDouble("PAID");

                   }
                    }

                }

                totalCustomersPaid.setText(total+"");
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }

          }


      }

      private void getTotalCompaniesPaid(){
          double total=0;

          if(flag==0){
                 try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select PAID from CompaniesPurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                   if(rs.getDouble("PAID")>0){
                       total+=rs.getDouble("PAID");
                   }

                }

                totalCompainesPaid.setText(total+"");
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }
          }else{
    String mMonth="";
              if(months.getSelectedIndex()<=9){
                  mMonth="0"+months.getSelectedIndex();
              }else{
                  mMonth=months.getSelectedIndex()+"";
              }

                   try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select PAID,PURCHASE_DATE from CompaniesPurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                    if(!year.getText().isEmpty()   && months.getSelectedIndex() ==0 && year.getText()
                           .equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(6))){
                         total+=rs.getDouble("PAID");
                    } else if(!year.getText().isEmpty()){
                   if(rs.getDouble("PAID")>0 && mMonth.equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(3, 5))&& year.getText()
                           .equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(6))){
                       total+=rs.getDouble("PAID");

                   }}else{
                         if(rs.getDouble("PAID")>0 && mMonth.equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(3, 5))){
                       total+=rs.getDouble("PAID");

                   }
                    }

                }

                totalCompainesPaid.setText(total+"");
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }
          }


      }

      private void getTotalProfits(){
            double total=0;

            if(flag ==0 ){
                  try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select PROFIT from  PurchaseProducts");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                   if(rs.getDouble("PROFIT")>0){
                       total+=rs.getDouble("PROFIT");
                   }

                }

                totalProfits.setText(total+"");
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }
            }else{

                 String mMonth="";
              if(months.getSelectedIndex()<=9){
                  mMonth="0"+months.getSelectedIndex();
              }else{
                  mMonth=months.getSelectedIndex()+"";
              }


    try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select PROFIT,PURCHASE_DATE from  PurchaseProducts");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                     if(!year.getText().isEmpty()   && months.getSelectedIndex() ==0 && year.getText()
                           .equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(6))){
                         total+=rs.getDouble("PROFIT");
                    } else if(!year.getText().isEmpty()){
                   if(rs.getDouble("PROFIT")>0 && mMonth.equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(3, 5))&& year.getText()
                           .equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(6))){
                       total+=rs.getDouble("PROFIT");

                   }}else{
                         if(rs.getDouble("PROFIT")>0 && mMonth.equalsIgnoreCase(rs.getString("PURCHASE_DATE").substring(3, 5))){
                       total+=rs.getDouble("PROFIT");

                   }
                    }

                }

                totalProfits.setText(total+"");
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }
            }

      }
}