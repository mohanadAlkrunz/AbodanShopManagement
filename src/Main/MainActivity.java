/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Trial.Companies.ViewCompaniesActivity;
import Trial.Customer.BillActivity;
import Trial.Customer.EditCustomerActivity;
import Trial.Customer.ViewCustomersActivity;
import Trial.Store.AddProductActivity;
import Trial.Store.ViewProductsActivity;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Bill;
import model.Customer;
import model.Payment;

/**
 *
 * @author DELL
 */
public class MainActivity extends javax.swing.JFrame {

    static List<Bill> purchaseHistory = new ArrayList<>();
    Object[] columns = {"التاريخ", "المبلغ المتبقي", "المبلغ المدفوع", "المجموع", "اسم الزبون", "م.ز", "م"};
    String customerName;





      //  WeakReference<MainActivity> reference = new WeakReference<MainActivity>(this);

      //  new LoadImageTask(reference).execute(R.drawable.p1);





    public MainActivity() {
        initComponents();
//        getContentPane().setLayout(null);
        Font font = new Font("Arial", Font.PLAIN, 18);
        jTable2.setFont(font);
        jTable2.getTableHeader().setFont(font);

UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));

                 getDataToday();
//        try {
//            Notification.sendNotification("تنبيه", "هناك دفعات مستحقة لهذا اليوم", MessageType.WARNING);
////              Notification.sendNotification("تنبيه", "هناك دفعات مستحقة لهذا اليوم مهند الكرنز", MessageType.WARNING);
//        } catch (Exception e) {
//            System.out.println("hanood");
//        }
        getData();
 date.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                purchaseHistory.clear();
                    if(date.getText().length()<= 10){
                         purchaseHistory.clear();
                        getDataSpecific(date.getText());
                    }else{
                        purchaseHistory.clear();
                        getData();
                    }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 purchaseHistory.clear();
                  if(date.getText().length()<= 10){
                       purchaseHistory.clear();
                        getDataSpecific(date.getText());
                    }else{
                       purchaseHistory.clear();
                        getData();
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

                String value1 = model.getValueAt(index, 6).toString();
                String value2 = model.getValueAt(index, 5).toString();
                new BillActivity(Integer.parseInt(value1), Integer.parseInt(value2), false).setVisible(true);
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

        private void getDataToday() {
            int f1=0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
             String mToday=formatter.format(date);


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


                    if(mToday.equalsIgnoreCase(rs.getString("PAYMENT_DUE")) && rs.getString("STATE").equalsIgnoreCase("غير مدفوع")){
                            f1=1;
                            jPanel11.setBackground(Color.red);
                            goToAlarms.setForeground(Color.white);



                }else{
                        Color c=new Color(0, 11, 106);
                        jPanel11.setBackground(c);
                            goToAlarms.setForeground(Color.white);
                    }}
                displayData();
                con.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println("Error: " + e);
        }

        if(f1==1){
            loadAlarms();
            f1=0;
        }
    }

    private void loadAlarms(){



         try {
            Notification.sendNotification("تنبيه", "هناك دفعات مستحقة لهذا اليوم", MessageType.WARNING);
//              Notification.sendNotification("تنبيه", "هناك دفعات مستحقة لهذا اليوم مهند الكرنز", MessageType.WARNING);
        } catch (Exception e) {
            System.out.println("hanood");
        }
    }

    private void getDataSpecific(String date ){

          purchaseHistory.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL_AMOUNT,PAID,REMAIN,PURCHASE_DATE from PurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {
                        if(rs.getString("PURCHASE_DATE").contains(date)){
                               Bill bill = new Bill();
                    bill.setId(rs.getInt("ID"));
                    bill.setCUSTOMER_ID(rs.getInt("CUSTOMER_ID"));
                    bill.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
                    bill.setTOTAL_AMOUNT(rs.getDouble("TOTAL_AMOUNT"));

                    bill.setPaid(rs.getDouble("PAID"));

                    bill.setRemain(rs.getDouble("REMAIN"));
                    bill.setPURCHASE_DATE(rs.getString("PURCHASE_DATE"));

                    purchaseHistory.add(bill);
                        }


                }
                displayData();
                rs.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
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
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        addNewItem = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        goToStore = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        goToCustomers = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        goToCompanies = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        goToReports = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        goToAlarms = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        goToAlarms1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("محلات أبودان للأدوات الكهربائية");
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("الرئيسية");

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
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
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
        jPanel2.setBounds(0, 0, 880, 130);

        jPanel4.setBackground(new java.awt.Color(0, 11, 106));

        back.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setText("فاتورة جديدة");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(650, 200, 170, 30);

        jPanel5.setBackground(new java.awt.Color(0, 11, 106));

        addNewItem.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        addNewItem.setForeground(new java.awt.Color(255, 255, 255));
        addNewItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addNewItem.setText("صنف جديد");
        addNewItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNewItemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addNewItem, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addNewItem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(650, 240, 170, 30);

        jPanel6.setBackground(new java.awt.Color(0, 11, 106));

        goToStore.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        goToStore.setForeground(new java.awt.Color(255, 255, 255));
        goToStore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goToStore.setText("المخزن");
        goToStore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToStoreMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToStore, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToStore, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6);
        jPanel6.setBounds(650, 320, 170, 30);

        jPanel7.setBackground(new java.awt.Color(0, 11, 106));

        goToCustomers.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        goToCustomers.setForeground(new java.awt.Color(255, 255, 255));
        goToCustomers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goToCustomers.setText("الزبائن");
        goToCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToCustomersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7);
        jPanel7.setBounds(650, 360, 170, 30);

        jPanel8.setBackground(new java.awt.Color(250, 250, 250));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("التاريخ :");

        date.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        date.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "تاريخ الفاتورة", "المتبقي", "المبلغ المدفوع", "الاجمالي", "اسم الزبون", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setRowHeight(25);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMinWidth(110);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(110);
            jTable2.getColumnModel().getColumn(5).setMinWidth(25);
            jTable2.getColumnModel().getColumn(5).setMaxWidth(25);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8);
        jPanel8.setBounds(20, 190, 620, 370);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("جميع المبيعات");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(170, 150, 170, 29);

        jPanel9.setBackground(new java.awt.Color(0, 11, 106));

        goToCompanies.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        goToCompanies.setForeground(new java.awt.Color(255, 255, 255));
        goToCompanies.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goToCompanies.setText("الشركات");
        goToCompanies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToCompaniesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToCompanies, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToCompanies, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9);
        jPanel9.setBounds(650, 400, 170, 30);

        jPanel10.setBackground(new java.awt.Color(0, 11, 106));

        goToReports.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        goToReports.setForeground(new java.awt.Color(255, 255, 255));
        goToReports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goToReports.setText("تقارير");
        goToReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToReportsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToReports, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10);
        jPanel10.setBounds(650, 440, 170, 30);

        jPanel11.setBackground(new java.awt.Color(0, 11, 106));

        goToAlarms.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        goToAlarms.setForeground(new java.awt.Color(255, 255, 255));
        goToAlarms.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goToAlarms.setText("التنبيهات");
        goToAlarms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToAlarmsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToAlarms, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToAlarms, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel11);
        jPanel11.setBounds(650, 480, 170, 30);

        jPanel12.setBackground(new java.awt.Color(0, 11, 106));

        goToAlarms1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        goToAlarms1.setForeground(new java.awt.Color(255, 255, 255));
        goToAlarms1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goToAlarms1.setText("فاتورة شركة");
        goToAlarms1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToAlarms1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToAlarms1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(goToAlarms1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel12);
        jPanel12.setBounds(650, 280, 170, 30);

        jPanel13.setBackground(new java.awt.Color(0, 11, 106));

        logout.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logout.setText("تسجيل خروج");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        logout.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                logoutKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel13);
        jPanel13.setBounds(650, 520, 170, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goToAlarms1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToAlarms1MouseClicked
        // TODO add your handling code here:

        new AddNewCompanyBill().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_goToAlarms1MouseClicked

    private void goToReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToReportsMouseClicked
        // TODO add your handling code here:

        new Reports().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_goToReportsMouseClicked

    private void goToCompaniesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToCompaniesMouseClicked
        // TODO add your handling code here:
        ViewCompaniesActivity companiesActivity = new ViewCompaniesActivity();
        companiesActivity.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_goToCompaniesMouseClicked

    private void goToCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToCustomersMouseClicked

        ViewCustomersActivity customersActivity = new ViewCustomersActivity();
        customersActivity.setVisible(true);
        setVisible(false);

    }//GEN-LAST:event_goToCustomersMouseClicked

    private void goToStoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToStoreMouseClicked

        // TODO add your handling code here:
        ViewProductsActivity productsActivity = new ViewProductsActivity();
        productsActivity.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_goToStoreMouseClicked

    private void addNewItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewItemMouseClicked
        // TODO add your handling code here:

        AddProductActivity addProductActivity = new AddProductActivity();
        addProductActivity.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_addNewItemMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:

        AddNewBill addNewBill = new AddNewBill();
        addNewBill.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backMouseClicked

    private void goToAlarmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goToAlarmsMouseClicked
        // TODO add your handling code here:

        new AlarmsActivity().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_goToAlarmsMouseClicked

    private void logoutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logoutKeyPressed



    }//GEN-LAST:event_logoutKeyPressed

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        // TODO add your handling code here:


       try{
         Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    File f = new File("AboDan shops.accdb");
    String path= f.getAbsolutePath();
    //  now we can get the connection from the DriverManager
         Connection con = DriverManager.getConnection("jdbc:ucanaccess://"+path,"","");
         con.setAutoCommit(false);
        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

       s.execute("select USERNAME,PASSWORD,STATE from Login");



        ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query



         if (rs != null){ // if rs == null, then there is no ResultSet to view

            while ( rs.next())// this will step through our data row-by-row
            {

                rs.updateInt("STATE", 0);
                rs.updateRow();


     }

     con.commit();
     rs.close();
     con.close();
              }} catch (Exception e) {
            	e.printStackTrace();
           // System.out.println("Error: " + e);
        }

        setVisible(false);
        new LoginActivity().setVisible(true);


    }//GEN-LAST:event_logoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainActivity().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addNewItem;
    private javax.swing.JLabel back;
    private javax.swing.JTextField date;
    private javax.swing.JLabel goToAlarms;
    private javax.swing.JLabel goToAlarms1;
    private javax.swing.JLabel goToCompanies;
    private javax.swing.JLabel goToCustomers;
    private javax.swing.JLabel goToReports;
    private javax.swing.JLabel goToStore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JLabel logout;
    // End of variables declaration//GEN-END:variables

    private void getData() {

        purchaseHistory.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,CUSTOMER_ID,CUSTOMER_NAME,TOTAL_AMOUNT,PAID,REMAIN,PURCHASE_DATE from PurchaseHistory");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                    Bill bill = new Bill();
                    bill.setId(rs.getInt("ID"));
                    bill.setCUSTOMER_ID(rs.getInt("CUSTOMER_ID"));
                    bill.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
                    bill.setTOTAL_AMOUNT(rs.getDouble("TOTAL_AMOUNT"));

                    bill.setPaid(rs.getDouble("PAID"));

                    bill.setRemain(rs.getDouble("REMAIN"));
                    bill.setPURCHASE_DATE(rs.getString("PURCHASE_DATE"));

                    purchaseHistory.add(bill);

                }
                displayData();
                rs.close();
                con.commit();
                con.close();
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
        Object[] row = new Object[7];
        for (int i = 0; i < purchaseHistory.size(); i++) {

            row[0] = purchaseHistory.get(i).getPURCHASE_DATE();
            row[1] = purchaseHistory.get(i).getRemain() + "";
            row[2] = purchaseHistory.get(i).getPaid() + "";
            row[3] = purchaseHistory.get(i).getTOTAL_AMOUNT() + "";
            row[4] = purchaseHistory.get(i).getCUSTOMER_NAME();
            row[5] = purchaseHistory.get(i).getCUSTOMER_ID() + "";
            row[6] = purchaseHistory.get(i).getId() + "";
            model.addRow(row);

        }

        jTable2.setModel(model);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
      DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)  jTable2.getTableHeader().getDefaultRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(5).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(5).setMinWidth(30);
        jTable2.getColumnModel().getColumn(6).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(6).setMinWidth(30);

    }

}
