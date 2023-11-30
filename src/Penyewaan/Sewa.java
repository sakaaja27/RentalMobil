/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Penyewaan;

import DB_koneksi.DB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import rental.login;

/**
 *
 * @author sakab
 */
public class Sewa extends javax.swing.JPanel {
    ArrayList<String> idMobil = new ArrayList<>();
    ArrayList<String> namaMobil = new ArrayList<>();
    ArrayList<String> idSupir = new ArrayList<>();
    ArrayList<String> namaSupir = new ArrayList<>();
    ArrayList<String> idCustomer = new ArrayList<>();
    ArrayList<String> namaCustomer = new ArrayList<>();
    
    ArrayList<String> idMobilTable = new ArrayList<>(); 
    ArrayList<String> idSupirTable = new ArrayList<>();
    int ttlHarga = 0;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Sewa() {
        initComponents();
        clearForm();
        clearTable();
        setupTable();
        getCustomer();
        getSupir();
        getMobil();
        
        combo_mobil.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idMobil.get(combo_mobil.getSelectedIndex());
                try{
                    Statement state = DB.getConnection().createStatement();
                    ResultSet res = state.executeQuery("SELECT harga_perhari FROM mobil WHERE kd_mobil='"+ id+"'");
                    if(res.next()){
                        int hargaPerhari = res.getInt("harga_perhari");
                        int hargaSewa = hargaPerhari * Integer.parseInt(jSpinner_jmlHari.getValue().toString());
                        jTextField_hargaPerhari.setText(String.valueOf(hargaPerhari));
                        jLabel_hargaSewa.setText(String.valueOf(hargaSewa));
                        
                    }
                }catch(Exception x){
                    JOptionPane.showMessageDialog(null, x);
                }
            }
        });
        jCombobox_perjalanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jCombobox_perjalanan.getSelectedIndex() == 0){
                    jLabel_hargaSupir.setText("200000");
                }else if(jCombobox_perjalanan.getSelectedIndex() == 1){
                    jLabel_hargaSupir.setText("150000");
                }
            }
        });
        
    }
    
    void clearForm(){
        jDateChooser_tglSewa.setDate(new Date());
        jSpinner_jmlHari.setValue(1);
        jTextField_hargaPerhari.setText("0");
        
        supirTidak.setSelected(true);
        jLabel_supir.setVisible(false);
        jLabel_hargaSewa.setText("0");
        jCombobox_supir.setVisible(false);
        
        jLabel_perjalanan.setVisible(false);
        jCombobox_perjalanan.setVisible(false);
        jLabel_supir1.setVisible(false);
        jLabel24.setVisible(false);
        jLabel_hargaSupir.setVisible(false);
    }
    

    void clearTable(){
        DefaultTableModel tbl = (DefaultTableModel) jTable_sewa.getModel();
        tbl.getDataVector().removeAllElements();
        revalidate();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        combo_mobil = new javax.swing.JComboBox<>();
        jTextField_hargaPerhari = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSpinner_jmlHari = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        combo_customer = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        supirYa = new javax.swing.JRadioButton();
        supirTidak = new javax.swing.JRadioButton();
        jLabel_supir = new javax.swing.JLabel();
        jCombobox_supir = new javax.swing.JComboBox<>();
        jButton_tambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_sewa = new javax.swing.JTable();
        jLabel_hargaSewa = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel_kembalian = new javax.swing.JLabel();
        jTextField_bayar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel_totalHarga = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jDateChooser_tglSewa = new com.toedter.calendar.JDateChooser();
        jLabel_supir1 = new javax.swing.JLabel();
        jCombobox_perjalanan = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel_hargaSupir = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel_perjalanan = new javax.swing.JLabel();

        setBackground(new java.awt.Color(243, 245, 250));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(177, 16, 7));
        jLabel8.setText("Penyewaan");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel9.setText("Tanggal Sewa");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 59, -1, -1));

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel10.setText("Nama Customer");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 108, -1, -1));

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel11.setText("Harga Perhari");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, -1, -1));

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel12.setText("Nama Mobil");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 55, -1, -1));

        combo_mobil.setBackground(new java.awt.Color(238, 218, 222));
        combo_mobil.setForeground(new java.awt.Color(255, 0, 51));
        combo_mobil.setPreferredSize(new java.awt.Dimension(72, 25));
        add(combo_mobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 148, -1));

        jTextField_hargaPerhari.setBackground(new java.awt.Color(238, 218, 222));
        jTextField_hargaPerhari.setForeground(new java.awt.Color(255, 0, 51));
        jTextField_hargaPerhari.setEnabled(false);
        jTextField_hargaPerhari.setPreferredSize(new java.awt.Dimension(64, 25));
        add(jTextField_hargaPerhari, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 148, -1));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel13.setText("Harga Sewa");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, -1, -1));

        jSpinner_jmlHari.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinner_jmlHari.setPreferredSize(new java.awt.Dimension(64, 25));
        jSpinner_jmlHari.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner_jmlHariStateChanged(evt);
            }
        });
        add(jSpinner_jmlHari, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 96, -1));

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel14.setText("Hari");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        combo_customer.setBackground(new java.awt.Color(238, 218, 222));
        combo_customer.setForeground(new java.awt.Color(204, 0, 0));
        combo_customer.setPreferredSize(new java.awt.Dimension(72, 25));
        add(combo_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 106, 148, -1));

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel15.setText("Supir");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, -1, -1));

        buttonGroup1.add(supirYa);
        supirYa.setForeground(new java.awt.Color(255, 0, 51));
        supirYa.setText("Ya");
        supirYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supirYaActionPerformed(evt);
            }
        });
        add(supirYa, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 53, -1, -1));

        buttonGroup1.add(supirTidak);
        supirTidak.setForeground(new java.awt.Color(255, 0, 51));
        supirTidak.setText("Tidak");
        supirTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supirTidakActionPerformed(evt);
            }
        });
        add(supirTidak, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 53, -1, -1));

        jLabel_supir.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel_supir.setText("Nama Supir");
        add(jLabel_supir, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, -1, -1));

        jCombobox_supir.setBackground(new java.awt.Color(238, 218, 222));
        jCombobox_supir.setForeground(new java.awt.Color(255, 0, 51));
        jCombobox_supir.setPreferredSize(new java.awt.Dimension(72, 25));
        jCombobox_supir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCombobox_supirActionPerformed(evt);
            }
        });
        add(jCombobox_supir, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, 143, -1));

        jButton_tambah.setBackground(new java.awt.Color(177, 16, 7));
        jButton_tambah.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton_tambah.setForeground(new java.awt.Color(255, 255, 255));
        jButton_tambah.setText("Tambah");
        jButton_tambah.setPreferredSize(new java.awt.Dimension(73, 30));
        jButton_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tambahActionPerformed(evt);
            }
        });
        add(jButton_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 202, 105, -1));

        jTable_sewa.setBackground(new java.awt.Color(238, 218, 222));
        jTable_sewa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_sewa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_sewaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_sewa);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 238, 907, 134));

        jLabel_hargaSewa.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel_hargaSewa.setText("0");
        add(jLabel_hargaSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 110, -1));

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel18.setText("Total");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 398, -1, -1));

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel19.setText("Bayar");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 435, -1, -1));

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel20.setText("Kembalian");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 479, -1, -1));

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(177, 16, 7));
        jLabel21.setText("Rp.");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 398, -1, -1));

        jLabel_kembalian.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel_kembalian.setForeground(new java.awt.Color(177, 16, 7));
        jLabel_kembalian.setText("0");
        add(jLabel_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 477, 107, -1));

        jTextField_bayar.setBackground(new java.awt.Color(238, 218, 222));
        jTextField_bayar.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jTextField_bayar.setForeground(new java.awt.Color(177, 16, 7));
        jTextField_bayar.setText("0");
        jTextField_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_bayarActionPerformed(evt);
            }
        });
        jTextField_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_bayarKeyTyped(evt);
            }
        });
        add(jTextField_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 437, 134, -1));

        jButton1.setBackground(new java.awt.Color(204, 0, 51));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cetak Struk");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(831, 470, 106, 40));

        jLabel26.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(177, 16, 7));
        jLabel26.setText("Rp.");
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 440, -1, -1));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(177, 16, 7));
        jLabel27.setText("Rp.");
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 477, -1, -1));

        jLabel_totalHarga.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel_totalHarga.setForeground(new java.awt.Color(177, 16, 7));
        jLabel_totalHarga.setText("0");
        add(jLabel_totalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(176, 398, 107, -1));

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel23.setText("Rp.");
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, -1, -1));

        jDateChooser_tglSewa.setDateFormatString("dd-MM-yyyy HH:ii");
        jDateChooser_tglSewa.setPreferredSize(new java.awt.Dimension(88, 25));
        add(jDateChooser_tglSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 53, 148, -1));

        jLabel_supir1.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel_supir1.setText("Harga Supir");
        add(jLabel_supir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, -1, -1));

        jCombobox_perjalanan.setBackground(new java.awt.Color(238, 218, 222));
        jCombobox_perjalanan.setForeground(new java.awt.Color(255, 0, 51));
        jCombobox_perjalanan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Luar Kota", "Dalam Kota" }));
        jCombobox_perjalanan.setPreferredSize(new java.awt.Dimension(72, 25));
        add(jCombobox_perjalanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 130, 143, -1));

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel24.setText("Rp.");
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 170, -1, -1));

        jLabel_hargaSupir.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel_hargaSupir.setText("0");
        add(jLabel_hargaSupir, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 170, 110, -1));

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel16.setText("Jumlah hari");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        jLabel_perjalanan.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel_perjalanan.setText("Perjalanan");
        add(jLabel_perjalanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    void setupTable(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("ID Mobil");
        tbl.addColumn("Nama Mobil");
        tbl.addColumn("Harga Perhari");
        tbl.addColumn("Jml Hari");
        tbl.addColumn("Tgl Sewa");
        tbl.addColumn("Tgl Tenggat");
        tbl.addColumn("ID Supir");
        tbl.addColumn("Nama Supir");
        tbl.addColumn("Subtotal");
        
        jTable_sewa.setModel(tbl);
//        jTable_sewa.getColumnModel().getColumn(0).setMinWidth(0);
//        jTable_sewa.getColumnModel().getColumn(0).setMaxWidth(0);
//
//        jTable_sewa.getColumnModel().getColumn(6).setMinWidth(0);
//        jTable_sewa.getColumnModel().getColumn(6).setMaxWidth(0);
    }
    
    void getCustomer(){
        try{
            Statement state = DB.getConnection().createStatement();
            ResultSet res = state.executeQuery("SELECT kd_customer, nama_lengkap FROM customer");
            while(res.next()){
                namaCustomer.add(res.getString("nama_lengkap"));
                idCustomer.add(res.getString("kd_customer"));
            }
            System.out.println(namaCustomer);
            combo_customer.setModel(new DefaultComboBoxModel<>(namaCustomer.toArray(new String[0])));
        }catch(Exception x){
            JOptionPane.showMessageDialog(null, x);
        }
    }

    
    void getMobil(){
        try{
            Statement state = DB.getConnection().createStatement();
            ResultSet res = state.executeQuery("SELECT kd_mobil, nama_mobil, nopol FROM mobil WHERE status='tersedia'");
            while(res.next()){
                namaMobil.add(res.getString("nama_mobil") + " - " + res.getString("nopol"));
                idMobil.add(res.getString("kd_mobil"));
            }
            combo_mobil.setModel(new DefaultComboBoxModel<>(namaMobil.toArray(new String[0])));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void getSupir(){
        try{
            Statement state = DB.getConnection().createStatement();
            ResultSet res = state.executeQuery("SELECT kd_supir, nama_supir FROM supir WHERE status='bersedia'");
            while(res.next()){
                namaSupir.add(res.getString("nama_supir"));
                idSupir.add(res.getString("kd_supir"));
            }
            jCombobox_supir.setModel(new DefaultComboBoxModel<>(namaSupir.toArray(new String[0])));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    private void jButton_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tambahActionPerformed
        String id_mobil = idMobil.get(combo_mobil.getSelectedIndex());
        String nama_mobil = combo_mobil.getSelectedItem().toString();
        String harga_perhari = jTextField_hargaPerhari.getText();
        String jml_hari = jSpinner_jmlHari.getValue().toString();
        String id_supir;
        String nama_supir;
        
        if(Integer.parseInt(harga_perhari) > 0){
            if(supirYa.isSelected() && jLabel_hargaSupir.getText().equals("0")){
                JOptionPane.showMessageDialog(null, "Pilih Perjalanan Jika Menggunakan Supir");
            }else{
            idMobilTable.add(id_mobil);
            idMobil.remove(id_mobil);
            namaMobil.remove(nama_mobil);
            combo_mobil.setModel(new DefaultComboBoxModel<>(namaMobil.toArray(new String[0])));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime((java.util.Date)jDateChooser_tglSewa.getDate());
            calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(jml_hari));

            String tglSewa = sdf.format((java.util.Date)jDateChooser_tglSewa.getDate());
            String tglKembali = sdf.format(calendar.getTime());

            int hargaSupir = 0;
            if(supirYa.isSelected() && jCombobox_supir.getSelectedItem() != null){
                id_supir = idSupir.get(jCombobox_supir.getSelectedIndex());
                nama_supir = jCombobox_supir.getSelectedItem().toString();
                idSupirTable.add(id_supir);
                idSupir.remove(id_supir);
                namaSupir.remove(nama_supir);
                jCombobox_supir.setModel(new DefaultComboBoxModel<>(namaSupir.toArray(new String[0])));
                hargaSupir = Integer.parseInt(jLabel_hargaSupir.getText());
            }else{
                hargaSupir = 0;
                id_supir = null;
                nama_supir = "Tanpa Supir";
            }
            int hargaSewa = Integer.parseInt(jLabel_hargaSewa.getText());
            String subttl = String.valueOf(hargaSewa + hargaSupir);

            
                String[] data = { id_mobil, nama_mobil, harga_perhari, jml_hari, tglSewa,tglKembali, id_supir, nama_supir, subttl};
                DefaultTableModel tbl = (DefaultTableModel) jTable_sewa.getModel();
                tbl.addRow(data);
                ttlHarga += Integer.parseInt(subttl);
                jLabel_totalHarga.setText(String.valueOf(ttlHarga));
                clearForm();    
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih Mobil");
        }
    }//GEN-LAST:event_jButton_tambahActionPerformed

    private void jCombobox_supirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCombobox_supirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCombobox_supirActionPerformed

    private void jTextField_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_bayarActionPerformed

    private void jSpinner_jmlHariStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner_jmlHariStateChanged
        int jml_hari = Integer.parseInt(jSpinner_jmlHari.getValue().toString());
        int harga_sewa = Integer.parseInt(jTextField_hargaPerhari.getText()) * jml_hari;
        jLabel_hargaSewa.setText(String.valueOf(harga_sewa));
    }//GEN-LAST:event_jSpinner_jmlHariStateChanged

    private void supirYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supirYaActionPerformed
        jLabel_supir.setVisible(true);
        jCombobox_supir.setVisible(true);
        jLabel_perjalanan.setVisible(true);
        jCombobox_perjalanan.setVisible(true);
        jLabel_supir1.setVisible(true);
        jLabel24.setVisible(true);
        jLabel_hargaSupir.setVisible(true);
    }//GEN-LAST:event_supirYaActionPerformed

    private void supirTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supirTidakActionPerformed
        jLabel_supir.setVisible(false);
        jCombobox_supir.setVisible(false);
        jLabel_perjalanan.setVisible(false);
        jCombobox_perjalanan.setVisible(false);
        jLabel_supir1.setVisible(false);
        jLabel24.setVisible(false);
        jLabel_hargaSupir.setVisible(false);
    }//GEN-LAST:event_supirTidakActionPerformed

    private void jTable_sewaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_sewaMouseClicked
        int baris = jTable_sewa.rowAtPoint(evt.getPoint());
        DefaultTableModel tbl = (DefaultTableModel) jTable_sewa.getModel();
        if(tbl.getValueAt(baris, 6) != null ){
            idSupir.add(tbl.getValueAt(baris, 6).toString());
            namaSupir.add(tbl.getValueAt(baris, 7).toString());
            jCombobox_supir.setModel(new DefaultComboBoxModel<>(namaSupir.toArray(new String[0])));
            idSupirTable.remove(tbl.getValueAt(baris, 6));
        }
        
        idMobil.add(idMobilTable.get(baris));
        namaMobil.add(tbl.getValueAt(baris, 1).toString());
        
        int minSubttl = Integer.parseInt(tbl.getValueAt(baris,8).toString());
        ttlHarga -= minSubttl;
        jLabel_totalHarga.setText(String.valueOf(ttlHarga));
        
        combo_mobil.setModel(new DefaultComboBoxModel<>(namaMobil.toArray(new String[0])));
        idMobilTable.remove(baris);
        tbl.removeRow(baris);
    }//GEN-LAST:event_jTable_sewaMouseClicked

    private void jTextField_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_bayarKeyTyped
        if(!jTextField_bayar.getText().equals("") && Integer.parseInt(jTextField_bayar.getText()) > 0){
            int totalTransaksi = Integer.parseInt(jLabel_totalHarga.getText());
            int bayar = Integer.parseInt(jTextField_bayar.getText());
            int kembali = bayar - totalTransaksi;
            jLabel_kembalian.setText(String.valueOf(kembali));    
        }
    }//GEN-LAST:event_jTextField_bayarKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel tbl = (DefaultTableModel) jTable_sewa.getModel();
        int rowCount = tbl.getRowCount();
        if(rowCount > 0){
            try{
                Statement state = DB.getConnection().createStatement();
                ResultSet res = state.executeQuery("SELECT genSewaID() AS kd_sewa");
                if(res.next()){
                    String kd_sewa = res.getString("kd_sewa");
                    String kdUser = login.idUser;
                    String kdCustomer = idCustomer.get(combo_customer.getSelectedIndex());
                    String totalHarga = jLabel_totalHarga.getText();
                    String bayar = jTextField_bayar.getText();
                    String tglTransaksi = sdf.format(new Date());
                    
                    String sql = "INSERT INTO penyewaan VALUES('"+ kd_sewa +"','"+ kdUser +"','"+ kdCustomer +"','"+ totalHarga +"','"+ bayar +"','"+ tglTransaksi +"'); ";
                    state.executeUpdate(sql);

                    for(int i = 0; i < rowCount; i++){
                        ResultSet resDtl = state.executeQuery("SELECT genDetailSewaID() AS kd_detail");
                        if(resDtl.next()){
                            String kd_detail = resDtl.getString("kd_detail");
                            String kd_mobil = tbl.getValueAt(i, 0).toString();
                            String harga_perhari = tbl.getValueAt(i, 2).toString();
                            String jml_hari = tbl.getValueAt(i, 3).toString();
                            String tgl_sewa = tbl.getValueAt(i, 4).toString();
                            String tgl_tenggat = tbl.getValueAt(i, 5).toString();
                            String kd_supir = (tbl.getValueAt(i, 6) != null) ? "'" + tbl.getValueAt(i, 6).toString() + "'" : null;
                            String subttl = tbl.getValueAt(i, 8).toString();
                            state.executeUpdate("INSERT INTO detail_sewa VALUES('"+ kd_detail +"','"+ kd_sewa +"', '"+ kd_mobil +"',IFNULL("+ kd_supir +", NULL),'"+ harga_perhari +"','"+ jml_hari +"','"+ tgl_sewa +"','"+ tgl_tenggat +"', null, '"+subttl +"', 'diproses');");                          
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data");
                    clearForm();
                    clearTable();
                }else{
                    System.out.println("gagal");
                }
            }catch(Exception x){
                System.out.println(x);
                JOptionPane.showMessageDialog(null, x);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> combo_customer;
    private javax.swing.JComboBox<String> combo_mobil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_tambah;
    private javax.swing.JComboBox<String> jCombobox_perjalanan;
    private javax.swing.JComboBox<String> jCombobox_supir;
    private com.toedter.calendar.JDateChooser jDateChooser_tglSewa;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_hargaSewa;
    private javax.swing.JLabel jLabel_hargaSupir;
    private javax.swing.JLabel jLabel_kembalian;
    private javax.swing.JLabel jLabel_perjalanan;
    private javax.swing.JLabel jLabel_supir;
    private javax.swing.JLabel jLabel_supir1;
    private javax.swing.JLabel jLabel_totalHarga;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner_jmlHari;
    private javax.swing.JTable jTable_sewa;
    private javax.swing.JTextField jTextField_bayar;
    private javax.swing.JTextField jTextField_hargaPerhari;
    private javax.swing.JRadioButton supirTidak;
    private javax.swing.JRadioButton supirYa;
    // End of variables declaration//GEN-END:variables
}
