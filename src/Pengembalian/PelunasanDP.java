/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pengembalian;

import DB_koneksi.DB;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PelunasanDP extends javax.swing.JFrame {

    String idTrans;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    Locale ind = new Locale("in", "ID");
    NumberFormat curr = NumberFormat.getCurrencyInstance(ind);
    Detail_kembali detail_kembali;

    public PelunasanDP( String kd_transaksi, Detail_kembali detail_kembali) {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        

        initComponents();
        this.detail_kembali = detail_kembali;

        this.idTrans = kd_transaksi;
        getData();
        
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_customer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_kdSewa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_totalHarga = new javax.swing.JTextField();
        jButton_bayar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField_uangMuka = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_kurangnya = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_bayar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 153), 5, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(177, 0, 7));
        jLabel9.setText("Form Pelunasan");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("X");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 6, 10, -1));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel1.setText("Kode Penyewaan");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jTextField_customer.setEnabled(false);
        jTextField_customer.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel1.add(jTextField_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 201, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel3.setText("Nama Penyewa");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jTextField_kdSewa.setEnabled(false);
        jTextField_kdSewa.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel1.add(jTextField_kdSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 201, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel5.setText("Total Harga");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jTextField_totalHarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_totalHarga.setEnabled(false);
        jTextField_totalHarga.setMinimumSize(new java.awt.Dimension(64, 30));
        jTextField_totalHarga.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel1.add(jTextField_totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 201, -1));

        jButton_bayar.setBackground(new java.awt.Color(204, 0, 51));
        jButton_bayar.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton_bayar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_bayar.setText("Bayar");
        jButton_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bayarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 106, 40));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 8, 20, 20));

        jTextField_uangMuka.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_uangMuka.setEnabled(false);
        jTextField_uangMuka.setMinimumSize(new java.awt.Dimension(64, 30));
        jTextField_uangMuka.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel1.add(jTextField_uangMuka, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 201, -1));

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel7.setText("Uang Muka");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, -1, -1));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel10.setText("Kurang");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jTextField_kurangnya.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_kurangnya.setEnabled(false);
        jTextField_kurangnya.setMinimumSize(new java.awt.Dimension(64, 30));
        jTextField_kurangnya.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel1.add(jTextField_kurangnya, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 201, -1));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel11.setText("Bayar");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, -1, -1));

        jTextField_bayar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_bayar.setMinimumSize(new java.awt.Dimension(64, 30));
        jTextField_bayar.setPreferredSize(new java.awt.Dimension(64, 30));
        jPanel1.add(jTextField_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 201, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bayarActionPerformed
        try {
            int newBayar = Integer.parseInt(jTextField_uangMuka.getText()) + Integer.parseInt(jTextField_bayar.getText());
            int total = Integer.parseInt(jTextField_totalHarga.getText());
            Statement state = DB.getConnection().createStatement();
            state.executeUpdate("UPDATE penyewaan SET bayar='"+ String.valueOf(newBayar) +"' WHERE kd_penyewaan='"+idTrans +"'");
            if(newBayar > total){
                int kembalian = newBayar - total;
                JOptionPane.showMessageDialog(rootPane, "Kembalian Anda: " + curr.format(kembalian));
            }
            JOptionPane.showMessageDialog(rootPane, "Berhasil Membayar Transaksi");
            detail_kembali.refreshTable();
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_jButton_bayarActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    void getData() {
        try{
            Statement state = DB.getConnection().createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM v_pelunasan WHERE kd_penyewaan = '"+ idTrans +"'");
            if(res.next()){
                jTextField_kdSewa.setText(res.getString("kd_penyewaan"));
                jTextField_customer.setText(res.getString("nama_customer"));
                jTextField_totalHarga.setText(res.getString("total_harga"));
                jTextField_uangMuka.setText(res.getString("bayar"));
                jTextField_kurangnya.setText(res.getString("kurangBayar"));
            }
        }catch(Exception x){
            JOptionPane.showMessageDialog(rootPane, x);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PelunasanDP(null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_bayar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_bayar;
    private javax.swing.JTextField jTextField_customer;
    private javax.swing.JTextField jTextField_kdSewa;
    private javax.swing.JTextField jTextField_kurangnya;
    private javax.swing.JTextField jTextField_totalHarga;
    private javax.swing.JTextField jTextField_uangMuka;
    // End of variables declaration//GEN-END:variables

    
}
