/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Pelunasan;

import DB_koneksi.DB;
import actionbutton_pelunasan.TableActionEditorPelunasan;
import actionbutton_pelunasan.TableActionEventPelunasan;
import actionbutton_pelunasan.tabelActionRenderPelunasan;
import functions.pop_upnoData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class datamaster extends javax.swing.JPanel {

    public datamaster() {
        initComponents();
        datatable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_master = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_search.setBackground(new java.awt.Color(238, 218, 222));
        jTextField_search.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jTextField_search.setForeground(new java.awt.Color(255, 0, 0));
        jTextField_search.setBorder(null);
        jTextField_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchKeyReleased(evt);
            }
        });
        add(jTextField_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 510, 50));

        jTable_master.setBackground(new java.awt.Color(238, 218, 222));
        jTable_master.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jTable_master.setForeground(new java.awt.Color(51, 51, 51));
        jTable_master.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_master.setCellSelectionEnabled(true);
        jTable_master.setGridColor(new java.awt.Color(204, 0, 0));
        jTable_master.setRowHeight(60);
        jTable_master.setUpdateSelectionOnSort(false);
        jTable_master.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_masterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_master);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 1350, 380));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pelunasan_master.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1463, 936));
    }// </editor-fold>//GEN-END:initComponents

    public void datatable() {
        TableActionEventPelunasan event = new TableActionEventPelunasan() {
            @Override
            public void onPelunasan(int row) {
                TableModel table = jTable_master.getModel();
                form_pelunasanV2 from = new form_pelunasanV2(table.getValueAt(row, 0).toString(), getThis());
                from.setVisible(true);
            }

            private datamaster getThis() { // create a method to return the outer class instance
                return datamaster.this;
            }

        };

        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Kode Transaksi");
        tbl.addColumn("Kode Penyewaan");
        tbl.addColumn("Nama Customer");
        tbl.addColumn("Total Harga");
        tbl.addColumn("Uang DP");
//        tbl.addColumn("Tgl Transaksi");
        tbl.addColumn("Action");
        jTable_master.setModel(tbl);
        jTable_master.getColumnModel().getColumn(0).setMinWidth(0);
        jTable_master.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable_master.getColumnModel().getColumn(5).setCellRenderer(new tabelActionRenderPelunasan());
        jTable_master.getColumnModel().getColumn(5).setCellEditor(new TableActionEditorPelunasan(event));

        String cari = jTextField_search.getText();

        ArrayList<String> filter = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM penyewaan INNER JOIN customer ON customer.kd_customer = penyewaan.kd_customer WHERE bayar < total_harga ");
        if (!cari.isEmpty()) {
            sql.append("AND penyewaan.kd_penyewaan LIKE '%" + cari + "%'");
            System.out.println(sql);
        }

        String newSql = sql.toString();
        try {
            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery(newSql);
            while (res.next()) {
                tbl.addRow(new Object[]{
                    res.getString("kd_penyewaan"),
                    res.getString("kd_penyewaan"),
                    res.getString("nama_lengkap"),
                    res.getString("total_harga"),
                    res.getString("bayar"), //                    res.getString("tgl_transaksi")
                });
                jTable_master.setModel(tbl);
            }
        } catch (Exception e) {
//            pop_upnoData nodata = new pop_upnoData();
//            nodata.setVisible(true);
        }

    }
    private void jTextField_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchKeyReleased
        datatable();
    }//GEN-LAST:event_jTextField_searchKeyReleased

    private void jTable_masterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_masterMouseClicked
        // TODO add your handling code here:
        //        int baris = jTableMobil.rowAtPoint(evt.getPoint());
        //        String id_mobil = jTableMobil.getValueAt(baris, 0).toString();
        //        Form_Mobil frm_add = new Form_Mobil(id_mobil,this);
        //        frm_add.setVisible(true);
    }//GEN-LAST:event_jTable_masterMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_master;
    private javax.swing.JTextField jTextField_search;
    // End of variables declaration//GEN-END:variables
}
