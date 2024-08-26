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
public class Pembatalanv2 extends javax.swing.JPanel {

    Dashboardadmin dashboard;

    public Pembatalanv2() {
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
//   

    public void generateTablePembatalan(String kd_penyewaan) {

        DefaultTableModel tbl = new DefaultTableModel();
       tbl.addColumn("Kode Pembatalan");
        tbl.addColumn("Nama Mobil");
        tbl.addColumn("Tgl. Sewa");
        tbl.addColumn("Tgl. Pembatalan");
        tbl.addColumn("Denda");
     

        jTable_detail.setModel(tbl);

        String sql = "SELECT * FROM v_pembatalan WHERE  kd_penyewaan= '" + kd_penyewaan + "'";
        System.out.println(sql);
        try {

            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("kd_pembatalan"),
                    res.getString("nama_mobil"),
                    res.getString("tgl_sewa"),
                    res.getString("tgl_pembatalan"),
                    res.getString("total_kerusakan")

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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_master = new javax.swing.JTable();
        jLabel_add = new javax.swing.JLabel();
        jTextField_search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_detail = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(243, 245, 250));
        setMinimumSize(new java.awt.Dimension(937, 510));
        setPreferredSize(new java.awt.Dimension(937, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_master.setBackground(new java.awt.Color(238, 218, 222));
        jTable_master.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 1320, 250));

        jLabel_add.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        jLabel_add.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_add.setText("Tambah");
        jLabel_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_addMouseClicked(evt);
            }
        });
        add(jLabel_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 120, 200, 70));

        jTextField_search.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextField_search.setForeground(new java.awt.Color(255, 0, 0));
        jTextField_search.setBorder(null);
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
        add(jTextField_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 280, 40));

        jTable_detail.setBackground(new java.awt.Color(238, 218, 222));
        jTable_detail.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTable_detail.setForeground(new java.awt.Color(51, 51, 51));
        jTable_detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Pembatalan", "Nama Mobil", "Tgl Sewa", "Tgl Pembatalan", "Denda"
            }
        ));
        jTable_detail.setRowHeight(40);
        jTable_detail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_detailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_detail);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 1340, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pembatalan.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_masterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_masterMouseClicked
        // TODO add your handling code here:
        int baris = jTable_master.rowAtPoint(evt.getPoint());
        String kd_penyewaan = jTable_master.getValueAt(baris, 0).toString();
        generateTablePembatalan(kd_penyewaan);

    }//GEN-LAST:event_jTable_masterMouseClicked

    private void jLabel_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_addMouseClicked
        // TODO add your handling code here:
        Form_Pembatalanv2 pembatalan = new Form_Pembatalanv2(this);
        pembatalan.setVisible(true);
    }//GEN-LAST:event_jLabel_addMouseClicked

    private void jTable_detailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_detailMouseClicked

    }//GEN-LAST:event_jTable_detailMouseClicked

    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        // TODO add your handling code here:
        generateTableMaster();
    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_add;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_detail;
    private javax.swing.JTable jTable_master;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables
}
