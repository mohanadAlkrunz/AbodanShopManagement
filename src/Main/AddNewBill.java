package Main;

import Trial.Store.EditProductActivity;
import java.awt.Component;
import java.awt.ComponentOrientation;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Product;

public class AddNewBill extends javax.swing.JFrame {

    double mTotalBefore, mTotalAfter;
    double mDiscountAmount;
    double mPaid;
    double mRemain;
    Object[] columns = {"الإجمالي", "الكمية", "سعرالوحدة", "اسم الصنف", "م"};
    DefaultListModel<String> listModel = new DefaultListModel<>();
    static List<Product> productsList = new ArrayList<>();
    static List<String> searchList = new ArrayList<>();
    static List<Product> billProducts = new ArrayList<>();
    static int customerID;
    static int bill_ID;
    static String mCustomerName = "";

    public AddNewBill() {
        initComponents();

        Font font = new Font("Arial", Font.PLAIN, 18);
        jTable2.setFont(font);
        jTable2.getTableHeader().setFont(font);
UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
        jTable2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = jTable2.getSelectedRow();

                TableModel model = jTable2.getModel();

                String value1 = model.getValueAt(index, 4).toString();

                int s = JOptionPane.showConfirmDialog(null, "هل انت متأكد من حذف هذا الصنف من الفاتورة", "تحذير", JOptionPane.YES_NO_OPTION);

                if (s == 0) {
                    for (int i = 0; i < billProducts.size(); i++) {
                        if (Integer.parseInt(value1) == billProducts.get(i).getId()) {
                            billProducts.remove(i);
                            updateTable();
                            System.out.println("Done");
                        }
                    }
                } else {
                    System.out.println("NO");
                }

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
        jList1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        addToArrayList();
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchList.clear();
                String s = jTextField1.getText();
                if (!s.isEmpty()) {
                    for (int i = 0; i < listModel.size(); i++) {
                        if (listModel.get(i).contains(s)) {
                            searchList.add(listModel.get(i));
                        }
                    }
                    listModel.clear();
                    for (int i = 0; i < searchList.size(); i++) {
                        listModel.addElement(searchList.get(i));
                    }

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchList.clear();
                String s = jTextField1.getText();
                if (!s.isEmpty()) {
                    for (int i = 0; i < listModel.size(); i++) {
                        if (listModel.get(i).contains(s)) {
                            searchList.add(listModel.get(i));
                        }
                    }
                    listModel.clear();
                    for (int i = 0; i < searchList.size(); i++) {
                        listModel.addElement(searchList.get(i));
                    }

                } else {
                    displayData();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("3");
            }
        });

        jList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int flag =0;
                if (jList1.getSelectedValue() == null) {
                    return;
                }
                for(int i=0;i<billProducts.size();i++){
                     if(jList1.getSelectedValue().equals(billProducts.get(i).getName()) ){
                         flag=1;
                         break;
                     }else{
                         flag=0;
                     }
                }

                if(flag==0){
                     double quantity = 0;
                String t = JOptionPane.showInputDialog(null,
                        "أدخل الكمية  " + jList1.getSelectedValue(), "أدخل الكمية",
                        JOptionPane.QUESTION_MESSAGE);

                if (t != null && !t.isEmpty()) {
                    quantity = Double.valueOf(t);

                    if (quantity > 0) {
                        for (int i = 0; i < productsList.size(); i++) {
                            if (productsList.get(i).getName().equalsIgnoreCase(jList1.getSelectedValue())) {

                                int id = productsList.get(i).getId();

                                try {
                                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                                    File f = new File("AboDan shops.accdb");
                                    String path = f.getAbsolutePath();
                                    //  now we can get the connection from the DriverManager
                                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
                                    con.setAutoCommit(false);
                                    Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

                                    s.execute("select ID,PRODUCT_NAME,PRODUCT_PRICE,PRODAUCT_SALE_PRICE,QUANTITY from Products");

                                    ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

                                    if (rs != null) { // if rs == null, then there is no ResultSet to view

                                        while (rs.next())// this will step through our data row-by-row
                                        {
                                            if (rs.getInt("ID") == id) {
                                                if (quantity < rs.getDouble("QUANTITY")) {

                                                    billProducts.add(productsList.get(i));

                                                    billProducts.get(billProducts.size() - 1).setQuantity((int) quantity);
                                                    updateTable();
                                                    displayData();

                                                } else {
                                                    JOptionPane.showMessageDialog(rootPane, "الكمية المدخلة خاطئة");
                                                }
                                            }

                                        }
                                        s.close();
                                        displayData();
                                        con.commit();
                                        con.close();
                                    }
                                } catch (Exception d) {
                                    d.printStackTrace();

                                }
                            }
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(rootPane, "الكمية خاطئة");

                }
                }else{

                    JOptionPane.showMessageDialog(null, "هذا الصنف موجود مسبقاً");
                }

            }

        });

        discountAmount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                mDiscountAmount = Double.valueOf(discountAmount.getText());
                if (percentDiscount.isSelected()) {
                    mTotalAfter = mTotalBefore - (mTotalBefore * (mDiscountAmount / 100));
                    updateTotalAfter();
                } else if (shiqelDiscount.isSelected()) {
                    mTotalAfter = mTotalBefore - mDiscountAmount;
                    updateTotalAfter();
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!discountAmount.getText().isEmpty()) {
                    mDiscountAmount = Double.valueOf(discountAmount.getText());
                    if (percentDiscount.isSelected()) {
                        mTotalAfter = mTotalBefore - (mTotalBefore * (mDiscountAmount / 100));
                        updateTotalAfter();
                    } else if (shiqelDiscount.isSelected()) {
                        mTotalAfter = mTotalBefore - mDiscountAmount;
                        updateTotalAfter();
                    }
                } else {
                    mTotalAfter = mTotalBefore;
                    updateTotalAfter();
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        paid.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                if (Double.valueOf(paid.getText()) != 0) {
                    mPaid = Double.valueOf(paid.getText());
                    mRemain = mTotalAfter - mPaid;
                    remain.setText(mRemain + "");
                } else if (Double.valueOf(paid.getText()) == 0) {
                    mRemain = mTotalAfter;
                    remain.setText(mRemain + "");
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                if (!paid.getText().isEmpty()) {
                    if (Double.valueOf(paid.getText()) != 0) {
                        mPaid = Double.valueOf(paid.getText());
                        mRemain = mTotalAfter - mPaid;
                        remain.setText(mRemain + "");
                    } else if (Double.valueOf(paid.getText()) == 0) {
                        mRemain = mTotalAfter;
                        remain.setText(mRemain + "");
                    }

                } else {
                    mRemain = mTotalAfter;
                    remain.setText(mRemain + "");
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

    }

    private void updateTotalAfter() {

        totalAfterDiscount.setText(mTotalAfter + "");
        if (paid.getText().isEmpty() || Double.valueOf(paid.getText()) == 0) {
            mRemain = mTotalAfter;
            remain.setText(mRemain + "");
        } else {
            mRemain = mTotalAfter - Double.valueOf(paid.getText());
            remain.setText(mRemain + "");
        }

    }

    private void updateTable() {

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        Object[] row = new Object[5];
        for (int i = 0; i < billProducts.size(); i++) {
            row[4] = billProducts.get(i).getId() + "";
            row[3] = billProducts.get(i).getName();
            row[1] = billProducts.get(i).getQuantity();
            row[2] = billProducts.get(i).getSalePrice();
            row[0] = billProducts.get(i).getQuantity() * billProducts.get(i).getSalePrice();
            model.addRow(row);

        }

        jTable2.setModel(model);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
 DefaultTableCellRenderer renderer=(DefaultTableCellRenderer)  jTable2.getTableHeader().getDefaultRenderer();
      renderer.setHorizontalAlignment(JLabel.CENTER);
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        jTable2.getColumnModel().getColumn(4).setMaxWidth(30);
        jTable2.getColumnModel().getColumn(4).setMinWidth(30);
        updateTotal();
    }

    private void updateTotal() {
        double totalBefore = 0;
        for (int i = 0; i < (jTable2.getModel().getRowCount()); i++) {
            totalBefore += (double) jTable2.getModel().getValueAt(i, 0);
        }
        mTotalBefore = totalBefore;
        totalBeforeDiscount.setText(mTotalBefore + "");

        if (!discountAmount.getText().isEmpty()) {
            mDiscountAmount = Double.valueOf(discountAmount.getText());
            if (percentDiscount.isSelected()) {
                mTotalAfter = mTotalBefore - (mTotalBefore * (mDiscountAmount / 100));
                updateTotalAfter();
            } else if (shiqelDiscount.isSelected()) {
                mTotalAfter = mTotalBefore - mDiscountAmount;
                updateTotalAfter();
            } else {
                mTotalAfter = mTotalBefore;
                updateTotalAfter();
            }
        } else {
            mTotalAfter = mTotalBefore;
            updateTotalAfter();
        }

        if (paid.getText().isEmpty() || Double.valueOf(paid.getText()) == 0) {
            mRemain = mTotalAfter;
            remain.setText(mRemain + "");
        }

    }

    private void addToArrayList() {

        productsList.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,PRODUCT_NAME,PRODUCT_PRICE,PRODAUCT_SALE_PRICE,QUANTITY from Products");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                    Product product = new Product();
                    product.setId(rs.getInt("ID"));
                    product.setName(rs.getString("PRODUCT_NAME"));
                    product.setOriginalPrice(rs.getDouble("PRODUCT_PRICE"));
                    product.setSalePrice(rs.getDouble("PRODAUCT_SALE_PRICE"));
                    product.setQuantity(rs.getInt("QUANTITY"));
                    productsList.add(product);

                }
                displayData();
                s.close();
                con.commit();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

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

        customerType = new javax.swing.ButtonGroup();
        discountType = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>(listModel);
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        specificCustomer = new javax.swing.JRadioButton();
        generalCustomer = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        shiqelDiscount = new javax.swing.JRadioButton();
        percentDiscount = new javax.swing.JRadioButton();
        discountAmount = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        totalBeforeDiscount = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        totalAfterDiscount = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        paid = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        remain = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        saveNewBill = new javax.swing.JLabel();
        customerName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("فاتورة جديدة");
        setLocation(new java.awt.Point(0, 0));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setLayout(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 11, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("فاتورة جديدة");

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
        jPanel3.setBounds(1080, 70, 230, 100);

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
        jPanel2.setBounds(-10, 0, 1450, 130);

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

        jList1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(1050, 240, 260, 430);

        jTextField1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(1050, 200, 220, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("نسبة الخصم :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(940, 320, 100, 22);

        specificCustomer.setBackground(new java.awt.Color(250, 250, 250));
        customerType.add(specificCustomer);
        specificCustomer.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        specificCustomer.setText("زبون خاص");
        specificCustomer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        specificCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        specificCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specificCustomerActionPerformed(evt);
            }
        });
        jPanel1.add(specificCustomer);
        specificCustomer.setBounds(830, 240, 107, 25);

        generalCustomer.setBackground(new java.awt.Color(250, 250, 250));
        customerType.add(generalCustomer);
        generalCustomer.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        generalCustomer.setSelected(true);
        generalCustomer.setText("زبون عام");
        generalCustomer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        generalCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(generalCustomer);
        generalCustomer.setBounds(930, 240, 107, 25);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("البحث");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(1270, 200, 50, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("تحديد الزبون :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(940, 210, 100, 19);

        shiqelDiscount.setBackground(new java.awt.Color(250, 250, 250));
        discountType.add(shiqelDiscount);
        shiqelDiscount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        shiqelDiscount.setText("بالشيكل (ش)");
        shiqelDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        shiqelDiscount.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        shiqelDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiqelDiscountActionPerformed(evt);
            }
        });
        jPanel1.add(shiqelDiscount);
        shiqelDiscount.setBounds(830, 350, 110, 31);

        percentDiscount.setBackground(new java.awt.Color(250, 250, 250));
        discountType.add(percentDiscount);
        percentDiscount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        percentDiscount.setText("مئوية %");
        percentDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        percentDiscount.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        percentDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentDiscountActionPerformed(evt);
            }
        });
        jPanel1.add(percentDiscount);
        percentDiscount.setBounds(890, 350, 150, 31);

        discountAmount.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        discountAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        discountAmount.setText("0");
        discountAmount.setEnabled(false);
        jPanel1.add(discountAmount);
        discountAmount.setBounds(960, 390, 70, 30);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الإجمالي", "الكمية", "سعر الوحدة", "اسم الصنف", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(25);
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(150, 180, 680, 410);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("إجمالي المبلغ");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(890, 640, 110, 30);

        totalBeforeDiscount.setBackground(new java.awt.Color(51, 255, 51));
        totalBeforeDiscount.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        totalBeforeDiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalBeforeDiscount.setText("0");
        totalBeforeDiscount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalBeforeDiscount.setEnabled(false);
        jPanel1.add(totalBeforeDiscount);
        totalBeforeDiscount.setBounds(780, 630, 130, 40);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("إجمالي المبلغ بعد الخصم");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(610, 640, 150, 30);

        totalAfterDiscount.setBackground(new java.awt.Color(51, 255, 51));
        totalAfterDiscount.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        totalAfterDiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalAfterDiscount.setText("0");
        totalAfterDiscount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalAfterDiscount.setEnabled(false);
        jPanel1.add(totalAfterDiscount);
        totalAfterDiscount.setBounds(470, 630, 130, 40);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("المبلغ المدفوع");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(370, 640, 90, 30);

        paid.setBackground(new java.awt.Color(255, 255, 51));
        paid.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        paid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        paid.setText("0");
        paid.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(paid);
        paid.setBounds(240, 630, 130, 40);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("المبلغ المتبقي");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(140, 640, 90, 30);

        remain.setBackground(new java.awt.Color(255, 51, 0));
        remain.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        remain.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        remain.setText("0");
        remain.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        remain.setEnabled(false);
        jPanel1.add(remain);
        remain.setBounds(10, 630, 130, 40);

        jPanel5.setBackground(new java.awt.Color(102, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        saveNewBill.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saveNewBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveNewBill.setText("حفظ");
        saveNewBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveNewBillMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveNewBill, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saveNewBill, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(30, 510, 110, 40);

        customerName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        customerName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customerName.setText("زبون عام");
        customerName.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        customerName.setEnabled(false);
        jPanel1.add(customerName);
        customerName.setBounds(840, 280, 190, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1418, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:

        MainActivity mainActivity = new MainActivity();
        mainActivity.setVisible(true);
        this.setVisible(false);
        billProducts.clear();

    }//GEN-LAST:event_backMouseClicked

    private void shiqelDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiqelDiscountActionPerformed
        // TODO add your handling code here:

        discountAmount.setEnabled(true);
    }//GEN-LAST:event_shiqelDiscountActionPerformed

    private void percentDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentDiscountActionPerformed
        // TODO add your handling code here:
        discountAmount.setEnabled(true);

    }//GEN-LAST:event_percentDiscountActionPerformed

    public static void selectCustomer(int mId, String mName) {
        customerID = mId;
        mCustomerName = mName;
        customerName.setText(mCustomerName);

    }

    private void specificCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specificCustomerActionPerformed

        new ViewCustomers().setVisible(true);


    }//GEN-LAST:event_specificCustomerActionPerformed

    private void saveNewBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveNewBillMouseClicked

        if(billProducts.size() ==0){
            JOptionPane.showMessageDialog(null  , "الفاتورة فارغة");
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();

            //  now we can get the connection from the DriverManager
            String database = "jdbc:ucanaccess://" + path;

            Connection con = DriverManager.getConnection(database, "", "");
            PreparedStatement ps = con.prepareStatement("insert into PurchaseHistory (CUSTOMER_ID,CUSTOMER_NAME,TOTAL_AMOUNT,PAID,REMAIN,PURCHASE_DATE,DISCOUNT,NOTE) values(?,?,?,?,?,?,?,?)");

            if (generalCustomer.isSelected()) {
                ps.setInt(1, 000);
                ps.setString(2, "زبون عام");
            } else {
                ps.setInt(1, customerID);
                ps.setString(2, mCustomerName);
            }

            ps.setDouble(3, mTotalAfter);
            if (paid.getText().isEmpty()) {
                paid.setText(0 + "");
                ps.setDouble(4, Double.valueOf(paid.getText()));
            } else {
                ps.setDouble(4, Double.valueOf(paid.getText()));
            }

            ps.setDouble(5, mRemain);
            ps.setString(6, formatter.format(date));

            if (percentDiscount.isSelected() && mDiscountAmount != 0) {

                ps.setDouble(7, mTotalAfter * (mDiscountAmount / 100));

            } else {
                ps.setDouble(7, mDiscountAmount);
            }
             ps.setString(8, "--");
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
            //  now we can get the connection from the DriverManager
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con1.setAutoCommit(false);
            Statement s = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            s.execute("select ID from PurchaseHistory");
            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query
            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.last())// this will step through our data row-by-row
                {

                    bill_ID = rs.getInt("ID");

                    Connection con2 = DriverManager.getConnection(database, "", "");
                    con2.setAutoCommit(false);
                    PreparedStatement ps2 = con2.prepareStatement("insert into PurchaseProducts (PURCHASE_ID,PRODUCT_NAME,PRICE,QUANTITY,TOTAL,PROFIT,PURCHASE_DATE) values(?,?,?,?,?,?,?)");

                    for (int i = 0; i < billProducts.size(); i++) {

                        ps2.setInt(1, bill_ID);
                        ps2.setString(2, billProducts.get(i).getName());
                        ps2.setDouble(3, billProducts.get(i).getSalePrice());
                        ps2.setInt(4, billProducts.get(i).getQuantity());
                        ps2.setDouble(5, billProducts.get(i).getQuantity() * billProducts.get(i).getSalePrice());
                         ps2.setDouble(6, billProducts.get(i).getQuantity() * (billProducts.get(i).getSalePrice()-billProducts.get(i).getOriginalPrice()));
                         ps2.setString(7, formatter.format(date));

                         ps2.addBatch();
                        ps2.clearParameters();

                        Connection con3 = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
                        con3.setAutoCommit(false);
                        Statement s3 = con3.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        s3.execute("select ID,QUANTITY from Products");
                        ResultSet rs3 = s3.getResultSet(); // get any Result

                        if (rs3 != null) {

                            while (rs3.next()) {
//
                                if (billProducts.get(i).getId() == rs3.getInt("ID")) {
                                    rs3.updateInt("QUANTITY", rs3.getInt("QUANTITY") - billProducts.get(i).getQuantity());
                                    rs3.updateRow();

                                    break;
                                }

                            }

                        }

                        con3.commit();
                        rs3.close();
                        con3.close();
                    }

                    Savepoint point3 = con2.setSavepoint();

                    int[] updateCounts2 = ps2.executeBatch();

//                    con2.rollback(point3);
                    con2.commit();
                    ps2.close();
                    con2.close();

                    break;
                }
                ps.close();
                con1.close();
            }

            JOptionPane.showMessageDialog(null, "تمت العملية بنجاح");
            int t =JOptionPane.showConfirmDialog(null, "هل تريد طباعة الفاتورة ؟");
            if(t==0){
                System.out.println("Yes");
                new MainActivity().setVisible(true);
                new PrintActivity(billProducts,bill_ID,customerName.getText(),formatter.format(date),mTotalAfter,mDiscountAmount,mPaid,mRemain).setVisible(true);
                dispose();
                }else{
                 System.out.println("NO");
                   MainActivity activity = new MainActivity();
            activity.setVisible(true);
//                     setVisible(false);
            dispose();

            }

            billProducts.clear();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }//GEN-LAST:event_saveNewBillMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private static javax.swing.JTextField customerName;
    private javax.swing.ButtonGroup customerType;
    private javax.swing.JTextField discountAmount;
    private javax.swing.ButtonGroup discountType;
    private javax.swing.JRadioButton generalCustomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField paid;
    private javax.swing.JRadioButton percentDiscount;
    private javax.swing.JTextField remain;
    private javax.swing.JLabel saveNewBill;
    private javax.swing.JRadioButton shiqelDiscount;
    private javax.swing.JRadioButton specificCustomer;
    private javax.swing.JTextField totalAfterDiscount;
    private javax.swing.JTextField totalBeforeDiscount;
    // End of variables declaration//GEN-END:variables

    private void displayData() {

        listModel.clear();
        for (int i = 0; i < productsList.size(); i++) {
            listModel.addElement(productsList.get(i).getName());
        }

    }
}
