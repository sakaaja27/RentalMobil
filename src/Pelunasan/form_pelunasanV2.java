/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pelunasan;

import DB_koneksi.DB;
import static Penyewaan.Sewav2.formatRupiah;
import static Penyewaan.Sewav2.removeDot;
import functions.pop_upDataberhasil;
import functions.pop_upnoData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class form_pelunasanV2 extends javax.swing.JFrame {

    String kd_penyewaan;
    datamaster data;

    public form_pelunasanV2(String kd_penyewaan, datamaster data) {
        this.data = data;
        this.kd_penyewaan = kd_penyewaan;
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);
        datatable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        label_kdTransaksi = new javax.swing.JLabel();
        label_tglSewa = new javax.swing.JLabel();
        label_uangDP = new javax.swing.JLabel();
        label_totalHarga = new javax.swing.JLabel();
        label_namaCustomer = new javax.swing.JLabel();
        BG = new javax.swing.JLabel();
        label_harusLunas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(238, 218, 222));
        jTextField1.setFont(new java.awt.Font("Dialog", 1, 19)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.setPreferredSize(new java.awt.Dimension(60, 15));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 378, 320, 40));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("*Total uang yang harus dilunasi");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 420, -1, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 20, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SIMPAN");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, 120, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SAVE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 540, 110, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SAVE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 536, 120, 50));

        label_kdTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_kdTransaksi.setForeground(new java.awt.Color(0, 0, 0));
        label_kdTransaksi.setToolTipText("");
        getContentPane().add(label_kdTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 181, 330, 30));

        label_tglSewa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_tglSewa.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(label_tglSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 240, 40));

        label_uangDP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_uangDP.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(label_uangDP, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 320, 30));

        label_totalHarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_totalHarga.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(label_totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 320, 30));

        label_namaCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        label_namaCustomer.setForeground(new java.awt.Color(0, 0, 0));
        label_namaCustomer.setText(" ");
        getContentPane().add(label_namaCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 280, 350, 30));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modal_pelunasan.png"))); // NOI18N
        getContentPane().add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        label_harusLunas.setBackground(new java.awt.Color(0, 0, 0));
        label_harusLunas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label_harusLunas.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(label_harusLunas, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 420, 160, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        this.dispose();

    }//GEN-LAST:event_jLabel4MouseClicked
    public void datatable() {
        try {
            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM penyewaan INNER JOIN customer ON customer.kd_customer = penyewaan.kd_customer WHERE kd_penyewaan ='" + kd_penyewaan + "'");
            while (res.next()) {
                label_kdTransaksi.setText(res.getString("kd_penyewaan"));
                label_tglSewa.setText(res.getString("tgl_transaksi"));
                label_totalHarga.setText(formatRupiah(res.getString("total_harga")));
                label_uangDP.setText(formatRupiah(res.getString("bayar")));
                label_namaCustomer.setText(res.getString("nama_lengkap"));
                jTextField1.setText(formatRupiah(String.valueOf(Integer.parseInt(removeDot(label_totalHarga.getText())) - Integer.parseInt(removeDot(label_uangDP.getText())))));
            }
            String harusLunas = String.valueOf(Integer.parseInt(removeDot(label_totalHarga.getText())) - Integer.parseInt(removeDot(label_uangDP.getText())));
            label_harusLunas.setText("Rp. " + formatRupiah(harusLunas));
        } catch (SQLException e) {
             pop_upnoData nodata = new pop_upnoData();
            nodata.setVisible(true);
        }
    }

    public static String formatRupiah(String val) {
        char[] valInArr = String.valueOf(val).toCharArray();
        int length = String.valueOf(val).length();
        char[] reversedValInArr = new char[length];
        for (int i = 0; i < length; i++) {
            reversedValInArr[i] = valInArr[length - (i + 1)];
        }
        int rupiahFormat = 3;
        String resultFormattedRupiah = "";
        if (length > rupiahFormat) {
            for (int i = length; i > 0; i--) {

                if (length > rupiahFormat) {
                    if (i % 3 == 0 && i < length) {
                        resultFormattedRupiah = resultFormattedRupiah.concat(".");
                        resultFormattedRupiah = resultFormattedRupiah.concat(String.valueOf(reversedValInArr[i - 1]));

                    } else {
                        resultFormattedRupiah = resultFormattedRupiah.concat(String.valueOf(reversedValInArr[i - 1]));
                    }
                } else {
                    if (i % 3 == 0) {
                        resultFormattedRupiah = resultFormattedRupiah.concat(".");
                        resultFormattedRupiah = resultFormattedRupiah.concat(String.valueOf(reversedValInArr[i - 1]));
                    } else {
                        resultFormattedRupiah = resultFormattedRupiah.concat(String.valueOf(reversedValInArr[i - 1]));
                    }
                }
            }
            return resultFormattedRupiah;
        } else {
            return String.valueOf(val);
        }
    }

    public static String removeDot(String num) {
        return num.replace(".", "");
    }
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        int uang_pelunasan = Integer.parseInt(removeDot(jTextField1.getText()));
//        if (uang_pelunasan >= Integer.parseInt(removeDot(label_harusLunas.getText().substring(4, label_harusLunas.getText().length())))) {

            try {
                String sql = "UPDATE penyewaan SET penyewaan.bayar = '" + (uang_pelunasan + Integer.parseInt(removeDot(label_uangDP.getText()))) + "' WHERE kd_penyewaan = '" + kd_penyewaan + "'";
                Statement statement = (Statement) DB.getConnection().createStatement();
                statement.executeUpdate(sql);
                pop_upDataberhasil dataubah = new pop_upDataberhasil();
                dataubah.setVisible(true);
                this.dispose();
                data.datatable();
                
                         PreparedStatement ps;
                ps = DB.getConnection().prepareStatement("SELECT * FROM log ORDER BY tgl_log DESC");
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    String activity = "Melakukan Pelunasan Transaksi";
                    ps = DB.getConnection().prepareStatement("CALL createLog('" + result.getString("kd_user") + "','" + activity + "');");
                    ps.executeUpdate();
                    break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Uang pelunasan kurang !");
//        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
//                String bayar = jTextField1.getText();

        try {

            int bayar = Integer.parseInt(removeDot(jTextField1.getText()));
            jTextField1.setText(formatRupiah(String.valueOf(bayar)));
//            int totalTransaksi = Integer.parseInt(removeDot(jTextField1.getText()));
//           
        } catch (Exception e) {
            System.out.println(e);
            jTextField1.setText(jTextField1.getText().substring(0, jTextField1.getText().length() - 1));
        }
    }//GEN-LAST:event_jTextField1KeyReleased

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
            java.util.logging.Logger.getLogger(form_pelunasanV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_pelunasanV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_pelunasanV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_pelunasanV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_pelunasanV2(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel label_harusLunas;
    private javax.swing.JLabel label_kdTransaksi;
    private javax.swing.JLabel label_namaCustomer;
    private javax.swing.JLabel label_tglSewa;
    private javax.swing.JLabel label_totalHarga;
    private javax.swing.JLabel label_uangDP;
    // End of variables declaration//GEN-END:variables
}
