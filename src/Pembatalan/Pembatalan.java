/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pembatalan;

import DB_koneksi.DB;
import Penyewaan.SewaMaster;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import rental.Dashboardadmin;

/**
 *
 * @author sakab
 */
public class Pembatalan extends javax.swing.JPanel {

    Dashboardadmin dashboard;

    public Pembatalan() {
        initComponents();
        this.dashboard = dashboard;
        generateTableMaster();

    }

    public void generateTableMaster() {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("Total Harga");
        tbl.addColumn("Tanggal Transaksi");

        jTable_master.setModel(tbl);
        jTable_master.getColumnModel().getColumn(0).setMinWidth(0);
        jTable_master.getColumnModel().getColumn(0).setMaxWidth(0);

        String cari = jTextField_search.getText();

        StringBuilder sql = new StringBuilder("SELECT * FROM v_master_pembatalan");

        String newSql = sql.toString().concat(" WHERE v_master_pembatalan.kd_penyewaan LIKE '%" + cari + "%'");

        try {

            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery(newSql);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("kd_penyewaan"),
                    res.getString("kd_penyewaan"),
                    res.getString("nama_customer"),
                    res.getString("total_harga"),
                    res.getString("tgl_transaksi")
                });
            }
            jTable_master.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
//    private void getDetailSewa(){
//         int baris = jTable_master.rowAtPoint(evt.getPoint());
//String kd_penyewaan = jTable_master.getValueAt(baris,0).toString();
//          StringBuilder sql = new StringBuilder("SELECT * FROM v_data_penyewaan");
//       
//         try {
//
//            Statement statement = (Statement) DB.getConnection().createStatement();
//            ResultSet res = statement.executeQuery(newSql);
//            while (res.next()) {
//                tbl.addRow(new Object[]{
//                    res.getString("kd_penyewaan"),
//                    res.getString("nama_customer"),                 
//                    res.getString("total_harga"),
//                    res.getString("tgl_transaksi")
//                });
//            }
//            jTable_master.setModel(tbl);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//    }
//    private void getDataSewa(String kd_detail_sewa){
//        int baris = jTable_master.rowAtPoint(evt.getPoint());
//                String kd_detail_sewa = jTable_master.getValueAt(baris, 0).toString();
//    }

    public void generateTablePembatalan(String kd_penyewaan) {

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Kode Pembatalan");
        tbl.addColumn("Tanggal Pembatalan");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("Nama Mobil");

        jTable_detail.setModel(tbl);

        String sql = "SELECT * FROM v_pembatalan WHERE  kd_penyewaan= '" + kd_penyewaan + "'";
        System.out.println(sql);
        try {

            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("kd_pembatalan"),
                    res.getString("tgl_pembatalan"),
                    res.getString("nama_customer"),
                    res.getString("nama_mobil")

                });
            }
            jTable_detail.setModel(tbl);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_detail = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_master = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButton_add = new javax.swing.JButton();
        Filter = new javax.swing.JComboBox<>();
        jTextField_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton_add1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(243, 245, 250));
        setMinimumSize(new java.awt.Dimension(937, 510));
        setPreferredSize(new java.awt.Dimension(937, 510));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(177, 16, 7));
        jLabel8.setText("Table Detail");

        jTable_detail.setBackground(new java.awt.Color(238, 218, 222));
        jTable_detail.setForeground(new java.awt.Color(51, 51, 51));
        jTable_detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Pembatalan", "Tanggal Pembatalan", "Nama Customer", "Nama Mobil"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_detail.setRowHeight(40);
        jTable_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_detailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_detail);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(177, 16, 7));
        jLabel9.setText("Pembatalan");

        jTable_master.setBackground(new java.awt.Color(238, 218, 222));
        jTable_master.setForeground(new java.awt.Color(51, 51, 51));
        jTable_master.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Transaksi", "Nama Customer", "Total Harga", "Tgl. Pesanan"
            }
        ));
        jTable_master.setRowHeight(40);
        jTable_master.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_masterMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_master);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(177, 16, 7));
        jLabel10.setText("Table Master");

        jButton_add.setBackground(new java.awt.Color(255, 0, 51));
        jButton_add.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton_add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_add.setText("Add");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        Filter.setBackground(new java.awt.Color(238, 218, 222));
        Filter.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        Filter.setForeground(new java.awt.Color(255, 51, 51));
        Filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filter", "A-Z", "Z-A" }));
        Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterActionPerformed(evt);
            }
        });

        jTextField_search.setBackground(new java.awt.Color(252, 252, 252));
        jTextField_search.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jTextField_search.setForeground(new java.awt.Color(51, 51, 51));
        jTextField_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        jTextField_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_searchFocusLost(evt);
            }
        });
        jTextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_searchActionPerformed(evt);
            }
        });
        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setText("Search");

        jButton_add1.setBackground(new java.awt.Color(255, 0, 51));
        jButton_add1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton_add1.setForeground(new java.awt.Color(255, 255, 255));
        jButton_add1.setText("Print");
        jButton_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(148, 148, 148)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_add1)
                            .addGap(55, 55, 55)
                            .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_add))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(781, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_add)
                                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_add1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel9)
                    .addContainerGap(465, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_detailMouseClicked

    }//GEN-LAST:event_jTable_detailMouseClicked

    private void jTable_masterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_masterMouseClicked
        // TODO add your handling code here:
        int baris = jTable_master.rowAtPoint(evt.getPoint());
        String kd_penyewaan = jTable_master.getValueAt(baris, 0).toString();
        generateTablePembatalan(kd_penyewaan);


    }//GEN-LAST:event_jTable_masterMouseClicked

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        // TODO add your handling code here:
        Form_Pembatalan pembatalan = new Form_Pembatalan();
        pembatalan.setVisible(true);
    }//GEN-LAST:event_jButton_addActionPerformed

    private void FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_FilterActionPerformed

    private void jTextField_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_searchFocusGained

    }//GEN-LAST:event_jTextField_searchFocusGained

    private void jTextField_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_searchFocusLost

    }//GEN-LAST:event_jTextField_searchFocusLost

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed


    }//GEN-LAST:event_jTextField_searchActionPerformed

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        generateTableMaster();


    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void jButton_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_add1ActionPerformed
        // TODO add your handling code here:
        try {
            if (jTextField_search.getText() == null) {
                System.out.println("errror");
            } else {
                String report = ("D:\\Kuliah\\Rental Mobill\\RentalMobil-Syncrhonized\\"
                        + "RentalMobil\\src\\Penyewaan\\reportSewa.jrxml");
                HashMap hash = new HashMap();
                hash.put("kd_penyewaan", jTextField_search.getText());
                System.out.println(hash);
                JasperReport jas = JasperCompileManager.compileReport(report);
                JasperPrint jPrint = JasperFillManager.fillReport(jas, hash, DB.getConnection());
                JasperViewer.viewReport(jPrint, false);
            }
        } catch (JRException notReport) {
            System.out.println("Report salah mas " + notReport);
        } catch (SQLException ex) {
            Logger.getLogger(SewaMaster.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_add1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Filter;
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_add1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_detail;
    private javax.swing.JTable jTable_master;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables
}
