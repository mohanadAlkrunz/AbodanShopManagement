/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trial.Companies;

import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author DELL
 */
public class AddCompanyActivity extends javax.swing.JFrame {

    /**
     * Creates new form AddCompanyActivity
     */
    public AddCompanyActivity() {
        initComponents();
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
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
        jLabel2 = new javax.swing.JLabel();
        companyName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        companyPhone = new javax.swing.JTextField();
        addCustomer = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("إضافة شركة جديدة");
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("إضافة شركة");

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

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("الاسم :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(680, 240, 80, 29);

        companyName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        companyName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(companyName);
        companyName.setBounds(370, 230, 250, 40);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("رقم الهاتف :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(650, 320, 110, 29);

        companyPhone.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        companyPhone.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        companyPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyPhoneActionPerformed(evt);
            }
        });
        jPanel1.add(companyPhone);
        companyPhone.setBounds(470, 310, 150, 40);

        addCustomer.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        addCustomer.setText("إضافة");
        addCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(addCustomer);
        addCustomer.setBounds(60, 440, 160, 37);

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

    private void companyPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_companyPhoneActionPerformed

    private void addCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerActionPerformed

        if(companyName.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "اسم الشركة مطلوب");
            return;
        }

        if(companyPhone.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "رقم الهاتف  مطلوب");
            return;
        }



              try {
    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // set this to a MS Access DB you have on your machine

    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();

          //  now we can get the connection from the DriverManager
          String database= "jdbc:ucanaccess://"+path;

  Connection con = DriverManager.getConnection( database ,"","");
 PreparedStatement ps = con.prepareStatement("insert into Companies (COMPANY_NAME,COMPANY_PHONE) values(?,?)");

       ps.setString(1,companyName.getText());
       ps.setString(2,companyPhone.getText());

      ps.addBatch();
      ps.clearParameters();

    con.setAutoCommit(false);

 Savepoint   point1=con.setSavepoint() ;
    int [] updateCounts=  ps.executeBatch();

      Savepoint   point2=con.setSavepoint() ;
      Statement s1 = con.createStatement();
      

// con.rollback(point2);

   con.commit();

                 JOptionPane.showMessageDialog(this, "تمت العملية بنجاح");
                   companyName.setText("");
                   companyPhone.setText("");

        }
            catch (Exception e) {
            	e.printStackTrace();

        }

    }//GEN-LAST:event_addCustomerActionPerformed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:

        ViewCompaniesActivity companiesActivity=new ViewCompaniesActivity();
        companiesActivity.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_backMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCustomer;
    private javax.swing.JLabel back;
    private javax.swing.JTextField companyName;
    private javax.swing.JTextField companyPhone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
