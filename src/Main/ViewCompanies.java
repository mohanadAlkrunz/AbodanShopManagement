/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.awt.ComponentOrientation;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;
import model.Company;
import model.Customer;

/**
 *
 * @author DELL
 */
public class ViewCompanies extends javax.swing.JFrame {

     DefaultListModel<String> listModel = new DefaultListModel<>();
    static List<Company> companiesList = new ArrayList<>();
    static List<String> searchList=new ArrayList<>();
    int id;
    String name="";

    /**
     * Creates new form ViewCompanies
     */
    public ViewCompanies() {
        initComponents();

           jList1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        addToArrayList();
UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
                "Arial", Font.BOLD, 18)));
        jList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (jList1.getSelectedValue() == null) {
                    return;
                }

                        for(int i=0;i<companiesList.size();i++){
                            if(companiesList.get(i).getName().equals(jList1.getSelectedValue())){
                                id=companiesList.get(i).getId();
                                name=jList1.getSelectedValue();
                                System.out.println(name+"  "+id);

                        }

                }
            }

        });

           search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                    searchList.clear();
        String s = search.getText();
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
        String s = search.getText();
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

        }else{
            displayData();
        }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                    System.out.println("3");
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
        search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>(listModel);
        selected = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(100, 100));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        search.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        search.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("ابحث حسب الاسم");

        jList1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jList1);

        selected.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        selected.setText("تحديد");
        selected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(selected, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selected, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedActionPerformed
        // TODO add your handling code here:

        AddNewCompanyBill.selectCompany(id, name);
        setVisible(false);
    }//GEN-LAST:event_selectedActionPerformed

     private void addToArrayList() {

        companiesList.clear();

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            File f = new File("AboDan shops.accdb");
            String path = f.getAbsolutePath();
            //  now we can get the connection from the DriverManager
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + path, "", "");
            con.setAutoCommit(false);
            Statement s = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            s.execute("select ID,COMPANY_NAME,COMPANY_PHONE,TOTAL from Companies");

            ResultSet rs = s.getResultSet(); // get any ResultSet that came from our query

            if (rs != null) { // if rs == null, then there is no ResultSet to view

                while (rs.next())// this will step through our data row-by-row
                {

                    Company company = new Company();
                    company.setId(rs.getInt("ID"));
                    company.setName(rs.getString("COMPANY_NAME"));
                    company.setPhone(rs.getString("COMPANY_PHONE"));

                    company.setTotal(rs.getDouble("TOTAL"));
                    companiesList.add(company);

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

        listModel.clear();
        for (int i = 0; i < companiesList.size(); i++) {
            listModel.addElement(companiesList.get(i).getName());
        }


    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search;
    private javax.swing.JButton selected;
    // End of variables declaration//GEN-END:variables
}
