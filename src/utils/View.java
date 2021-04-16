/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Emp.EmpDAO;
import Emp.EmpDTO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUYVU
 */
public class View extends javax.swing.JFrame {

    EmpDAO empDAO;
    EmpDTO empDTO;
    DefaultTableModel model;
    boolean addNew = false;
    Vector header = new Vector();
    Vector emp = new Vector();
    ArrayList<EmpDTO> data = new ArrayList<>();
//    ArrayList<EmpDTO> list = new ArrayList<>();
    //SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    //ArrayList<EmpDTO> remove = new ArrayList<>();

    public View() {
        initComponents();
        header.add("EmpID");
        header.add("FullName");
        header.add("Phone");
        header.add("Email");

        emp = new Vector();
        empDAO = new EmpDAO();

        model = (DefaultTableModel) (tblEmp.getModel());

        loadData();
        
        txtAddress.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    txtDOB.requestFocus();
                    e.consume();
                }
            }
        });
    }

    void loadData() {
        model.setRowCount(0);
        for (EmpDTO e : data) {
            if (e.isIsDelete() == false) {
                Vector row = new Vector();
                row = new Vector();
                row.add(e.getEmpID());
                row.add(e.getFullname());
                row.add(e.getPhone());
                row.add(e.getEmail());
                emp.add(row);
            }
        }

        model.setDataVector(emp, header);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void setNone() {
        txtEmpID.setText("");
        txtEmpID.setEditable(true);
        txtEmpID.setEnabled(true);
        txtEmpID.requestFocus();
        lbEmpID.setText("");
        txtFullname.setText("");
        lbFullname.setText("");
        txtPhone.setText("");
        lbPhone.setText("");
        txtEmail.setText("");
        lbEmail.setText("");
        txtAddress.setText("");
        lbAddress.setText("");
        txtDOB.setText("");
        lbDOB.setText("");
        tblEmp.clearSelection();
    }

    private String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    private void setField(EmpDTO emp) {
        txtEmpID.setText(emp.getEmpID());
        txtFullname.setText(emp.getFullname());
        txtPhone.setText(emp.getPhone());
        txtEmail.setText(emp.getEmail());
        txtAddress.setText(emp.getAddress());
        txtDOB.setText(convertDateToString(emp.getDateOfBirth()));
    }

    public boolean validData() {
        //check ID
        String empID = txtEmpID.getText().trim();
        if (empID.equals("")) {
            lbEmpID.setText("Cannot blank");
            txtEmpID.requestFocus();
            return false;
        } else if (empID.length() > 10 || empID.length() < 0) {
            lbEmpID.setText("Length must be in range 1-10");
            txtEmpID.requestFocus();
            return false;
        } else if (!empID.matches("^[^@#$]*$")) {
            lbEmpID.setText("Emp ID is not contains special characters (@, #, $)");
            txtEmpID.requestFocus();
            return false;
        } else {
            lbEmpID.setText("");
            lbFullname.setText("");
            lbPhone.setText("");
            lbEmail.setText("");
            lbAddress.setText("");
            lbDOB.setText("");
        }
        //check Fullname
        String fullname = txtFullname.getText().trim();
        if (fullname.equals("")) {
            lbFullname.setText("Cannot blank");
            txtFullname.requestFocus();
            return false;
        } else if (fullname.length() > 30 || fullname.length() < 0) {
            lbFullname.setText("Length must be in range 1-30");
            txtFullname.requestFocus();
            return false;
        } else {
            lbEmpID.setText("");
            lbFullname.setText("");
            lbPhone.setText("");
            lbEmail.setText("");
            lbAddress.setText("");
            lbDOB.setText("");
        }
        //check Phone
        String phone = txtPhone.getText().trim();
        if (phone.equals("")) {
            lbPhone.setText("Cannot blank");
            txtPhone.requestFocus();
            return false;
        } else if (phone.length() > 15 || phone.length() < 0) {
            lbPhone.setText("Length must be in range 1-15");
            txtPhone.requestFocus();
            return false;
        } else if (!phone.matches("\\d+")) {
            lbPhone.setText("Phone only number");
            txtPhone.requestFocus();
            return false;
        } else {
            lbEmpID.setText("");
            lbFullname.setText("");
            lbPhone.setText("");
            lbEmail.setText("");
            lbAddress.setText("");
            lbDOB.setText("");
        }
        //check address
        String address = txtAddress.getText().trim();
        if (address.equals("")) {
            lbAddress.setText("Cannot blank");
            txtAddress.requestFocus();
            return false;
        } else if (address.length() > 300 || address.length() < 0) {
            lbAddress.setText("Length must be in range 1-300");
            txtAddress.requestFocus();
            return false;
        } else {
            lbEmpID.setText("");
            lbFullname.setText("");
            lbPhone.setText("");
            lbEmail.setText("");
            lbAddress.setText("");
            lbDOB.setText("");
        }
        //check email
        String email = txtEmail.getText().trim();
        if (!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?")) {
            lbEmail.setText("Email must be in form abc123@def.xyz(.hkt)");
            txtEmail.requestFocus();
            return false;
        } else if (email.equals("")) {
            lbEmail.setText("Cannot blank");
            txtEmail.requestFocus();
            return false;
        } else if (email.length() > 30 || email.length() < 0) {
            lbEmail.setText("Must be in range of 1-30");
            txtEmail.requestFocus();
            return false;
        } else {
            lbEmpID.setText("");
            lbFullname.setText("");
            lbPhone.setText("");
            lbEmail.setText("");
            lbAddress.setText("");
            lbDOB.setText("");
        }

        //check dateOfBirth
        String dateOfBirth = txtDOB.getText().trim();
        if (!dateOfBirth.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            lbDOB.setText("Input Date format MM/dd/yyyy. Only number");
            txtDOB.requestFocus();
            return false;
        }

        String[] dateComponent = dateOfBirth.split("/");
        int day, month, year;
        try {
            day = Integer.parseInt(dateComponent[1]);
            month = Integer.parseInt(dateComponent[0]);
            year = Integer.parseInt(dateComponent[2]);
        } catch (NumberFormatException e) {
            lbDOB.setText("Please input for day, month and year");
            txtDOB.requestFocus();
            return false;
        }
        if (!validDateOfBirth(day, month, year)) {
            lbDOB.setText("Invalid date. Please check again. Date format MM/dd/yyyy");
            txtDOB.requestFocus();
            return false;
        }

        if (Calendar.getInstance().get(Calendar.YEAR) - year < 18 || Calendar.getInstance().get(Calendar.YEAR) - year > 60) {
            lbDOB.setText("Employee must have age from 18 to 60");
            txtDOB.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validDateOfBirth(int day, int month, int year) {
        int maxd = 31;
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            return false;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxd = 30;
        } else if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                maxd = 29;
            } else {
                maxd = 28;
            }
        }
        return day <= maxd;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmp = new javax.swing.JTable();
        lbEmp = new javax.swing.JLabel();
        btnGetAll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblEmpID = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lbaaAddress = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        txtEmpID = new javax.swing.JTextField();
        txtFullname = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        txtFindByID = new javax.swing.JButton();
        scrDescription = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        btnNew = new javax.swing.JButton();
        lbEmpID = new javax.swing.JLabel();
        lbFullname = new javax.swing.JLabel();
        lbPhone = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        lbAddress = new javax.swing.JLabel();
        lbDOB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "EmpID", "Fullname", "Phone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmp.getTableHeader().setReorderingAllowed(false);
        tblEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEmp);

        lbEmp.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbEmp.setForeground(new java.awt.Color(0, 0, 255));
        lbEmp.setText("Emp Management");

        btnGetAll.setText("Get all");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Emp's Detail"));
        jPanel1.setToolTipText("");
        jPanel1.setFocusable(false);
        jPanel1.setName(""); // NOI18N

        lblEmpID.setText("EmpID:");

        lblFullname.setText("Fullname:");

        lblPhone.setText("Phone:");

        lblEmail.setText("Email:");

        lbaaAddress.setText("Address");

        lblDOB.setText("DOB");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        txtFindByID.setText("Find By EmpID ");
        txtFindByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindByIDActionPerformed(evt);
            }
        });

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        scrDescription.setViewportView(txtAddress);

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        lbEmpID.setForeground(new java.awt.Color(255, 0, 51));

        lbFullname.setForeground(new java.awt.Color(255, 0, 0));

        lbPhone.setForeground(new java.awt.Color(255, 0, 0));

        lbEmail.setForeground(new java.awt.Color(255, 0, 0));

        lbAddress.setForeground(new java.awt.Color(255, 0, 0));

        lbDOB.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDOB)
                                    .addComponent(lbaaAddress)
                                    .addComponent(lblPhone)
                                    .addComponent(lblEmail))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(5, 5, 5)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(btnCreate)
                                                    .addGap(47, 47, 47)
                                                    .addComponent(btnUpdate))
                                                .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(9, 9, 9)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 15, Short.MAX_VALUE))))
                            .addComponent(lbPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFullname, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmpID, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFullname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDOB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEmpID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNew)
                        .addGap(285, 285, 285)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFindByID)
                    .addComponent(btnRemove)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmpID)
                    .addComponent(txtEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFindByID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEmpID, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFullname)
                    .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lbFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone))
                .addGap(2, 2, 2)
                .addComponent(lbPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addGap(8, 8, 8)
                .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbaaAddress))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDOB)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnRemove))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(lbEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(504, 504, 504))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btnGetAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbEmp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnGetAll)
                        .addGap(0, 33, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpMouseClicked
        this.addNew = false;
        int row = tblEmp.getSelectedRow();
        String empID = (String) tblEmp.getValueAt(row, 0);
        try {
            EmpDTO emp = empDAO.findByEmpID(empID);
            txtEmpID.setEnabled(false);
            setField(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblEmpMouseClicked

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        // TODO add your handling code here:
        setNone();
        loadData();
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        if (!validData()) {
            return;
        }

        String empID = txtEmpID.getText().trim();
        String fullname = txtFullname.getText().trim();
        String phone = txtPhone.getText().trim();
        String email = txtEmail.getText().trim();
        String address = txtAddress.getText().trim();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfBirth = null;
        try {
            dateOfBirth = formatter.parse(txtDOB.getText().trim());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        boolean isDelete = false;
        EmpDTO emp = new EmpDTO(empID, fullname, phone, email, address, dateOfBirth, isDelete);
        this.addNew = true;

        try {
            if (empDAO.findByEmpID(empID) != null) {
                JOptionPane.showMessageDialog(this, "EmpID is existed");
                return;
            } else {
                addNew = empDAO.createEmp(emp);
                data.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (addNew) {
            JOptionPane.showMessageDialog(this, "Create successfull");
            setNone();
            addNew = false;
        } else {
            JOptionPane.showMessageDialog(this, "Cannot create");
        }
        loadData();
        tblEmp.updateUI();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:

        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add employee first!");
            return;
        }

        int row = tblEmp.getSelectedRow();
        if (row >= 0) {
            if (!validData()) {
                return;
            }

            String empID = txtEmpID.getText().trim();
            txtEmpID.setEnabled(false);
            String fullname = txtFullname.getText().trim();
            String phone = txtPhone.getText().trim();
            String email = txtEmail.getText().trim();
            String address = txtAddress.getText().trim();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            Date dateOfBirth = null;
            try {
                dateOfBirth = formatter.parse(txtDOB.getText().trim());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            boolean isDelete = false;
            EmpDTO emp = new EmpDTO(empID, fullname, phone, email, address, dateOfBirth, isDelete);

            boolean isUpdate = false;
            try {
                isUpdate = empDAO.updateEmp(emp);
                System.out.println(emp);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isUpdate) {
                JOptionPane.showMessageDialog(this, "Update successfull");
                setNone();
                data.set(row, emp);
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Update fail");
                setNone();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Choose employee to update");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add employee first!");
            return;
        }

        int row = tblEmp.getSelectedRow();
        if (row >= 0) {
            String empID = txtEmpID.getText().trim();
            txtEmpID.setEnabled(false);

            boolean isRemoved = false;
            if (JOptionPane.showConfirmDialog(this, "Do you want to remove?")
                    == JOptionPane.OK_OPTION) {

                try {
                    isRemoved = empDAO.removeEmp(empID);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (isRemoved) {
                    JOptionPane.showMessageDialog(this, "Remove successful");
                    setNone();
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(this, "Remove fail");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Choose employee to remove");
        }
        tblEmp.updateUI();
    }//GEN-LAST:event_btnRemoveActionPerformed
    
    private void txtFindByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindByIDActionPerformed
        // TODO add your handling code here:
        String empID = txtEmpID.getText().trim();
        EmpDTO emp = null;

        emp = empDAO.findByEmpID(empID);
        if (emp != null) {
            if (emp.isIsDelete() == true) {
                JOptionPane.showMessageDialog(this, "Employee was removed");
                setNone();
                txtEmpID.setEnabled(true);
                return;
            }
        }

        if (emp != null) {
            //txtEmpID.setEnabled(false);
            for (int i = 0; i < data.size(); i++) {
//                System.out.println(data.get(i).getEmpID());
                if (empID.equals(data.get(i).getEmpID())) {
//                    tblEmp.setRowSelectionInterval(i, i);
                    //model.setRowCount(0);
                    Vector row = new Vector();
                    row = new Vector();
                    row.add(emp.getEmpID());
                    row.add(emp.getFullname());
                    row.add(emp.getPhone());
                    row.add(emp.getEmail());
                    model.addRow(row);
                    tblEmp.updateUI();
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "EmpID not founded");
        }
    }//GEN-LAST:event_txtFindByIDActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        loadData();
        setNone();
    }//GEN-LAST:event_btnNewActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to exit? ", "Exit", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAddress;
    private javax.swing.JLabel lbDOB;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEmp;
    private javax.swing.JLabel lbEmpID;
    private javax.swing.JLabel lbFullname;
    private javax.swing.JLabel lbPhone;
    private javax.swing.JLabel lbaaAddress;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmpID;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JScrollPane scrDescription;
    private javax.swing.JTable tblEmp;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmpID;
    private javax.swing.JButton txtFindByID;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}