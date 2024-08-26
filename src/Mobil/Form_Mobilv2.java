/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mobil;

import functions.pop_upTanyaClear;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import DB_koneksi.DB;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import DB_koneksi.DB;
import static Penyewaan.Sewav2.formatRupiah;
import static Penyewaan.Sewav2.removeDot;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import functions.pop_upClear;
import functions.pop_upDataGagal;
import functions.pop_upDataberhasil;
import functions.pop_upRequired;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author sakab
 */
public class Form_Mobilv2 extends javax.swing.JFrame {

    ArrayList<String> nm_pemilik, kd_pemilik;
    String id;
    String imagePath;
    String imagePathOld;
    Mobilv2 mobil;
    Boolean isBrowsePressed = false;

    /**
     * Creates new form Form_Mobil
     */
    public Form_Mobilv2(String id_mobil, Mobilv2 mobil) {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);

//        Image();
        initComponents();
//        this.setLocationRelativeTo(null);
        getPemilik();
//        tampilGambar(jLabel_foto,id_mobil);
        id = id_mobil;
        this.mobil = mobil;
        if (id_mobil != null) {
            getData(id_mobil);
            kodeMobil.setText(id_mobil);
        }

        //ini agar posisi center waktu buka
        this.setLocationRelativeTo(null);
    }

    void getData(String id_mobil) {
        PreparedStatement ts = null;
        try {

            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery("SELECT pemilik_mobil.nama_pemilik,mobil.* FROM mobil inner join pemilik_mobil ON pemilik_mobil.kd_pemilik = mobil.kd_pemilik where kd_mobil ='" + id_mobil + "'");

            while (res.next()) {

                namaMobil.setText(res.getString("nama_mobil"));
                nopolMobil.setText(res.getString("nopol"));

                tahun.setText(res.getString("tahun_produksi"));
                harga.setText(res.getString("harga_perhari"));
                String img_path = res.getString("gambar");
                this.imagePathOld = img_path;
                jLabel_file.setText(img_path);
                ImageIcon image = new ImageIcon(img_path);
                Image im = image.getImage();
                Image myimg = im.getScaledInstance(jLabel_foto.getWidth(), jLabel_foto.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myimg);
                jLabel_foto.setIcon(newImage);
                System.out.println(newImage);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }

    void getPemilik() {
        nm_pemilik = new ArrayList<String>();
        kd_pemilik = new ArrayList<String>();
        try {
            Statement state = DB.getConnection().createStatement();
            ResultSet res = state.executeQuery("select kd_pemilik, nama_pemilik from pemilik_mobil");
            while (res.next()) {
                jComboBox_pemilik.addItem(res.getString("nama_pemilik"));
                kd_pemilik.add(res.getString("kd_pemilik"));
            }

        } catch (Exception x) {
            JOptionPane.showMessageDialog(rootPane, x);

        }
    }
    
    public static String formatRupiah(int val) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        namaMobil = new javax.swing.JTextField();
        nopolMobil = new javax.swing.JTextField();
        tahun = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        jComboBox_pemilik = new javax.swing.JComboBox<>();
        jLabel_foto = new javax.swing.JLabel();
        jButton_hapus = new javax.swing.JLabel();
        jButton_browse = new javax.swing.JLabel();
        saveMobil = new javax.swing.JLabel();
        jLabel_file = new javax.swing.JLabel();
        kodeMobil = new javax.swing.JTextField();
        jLabel_back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("x");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 0, 30, 30));

        namaMobil.setBackground(new java.awt.Color(238, 218, 222));
        namaMobil.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        namaMobil.setBorder(null);
        namaMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaMobilActionPerformed(evt);
            }
        });
        getContentPane().add(namaMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 320, 30));

        nopolMobil.setBackground(new java.awt.Color(238, 218, 222));
        nopolMobil.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        nopolMobil.setBorder(null);
        nopolMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nopolMobilActionPerformed(evt);
            }
        });
        getContentPane().add(nopolMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 320, 40));

        tahun.setBackground(new java.awt.Color(238, 218, 222));
        tahun.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        tahun.setBorder(null);
        tahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahunActionPerformed(evt);
            }
        });
        getContentPane().add(tahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 320, 30));

        harga.setBackground(new java.awt.Color(238, 218, 222));
        harga.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        harga.setBorder(null);
        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });
        harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hargaKeyReleased(evt);
            }
        });
        getContentPane().add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 540, 320, 30));

        jComboBox_pemilik.setBackground(new java.awt.Color(238, 218, 222));
        jComboBox_pemilik.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        getContentPane().add(jComboBox_pemilik, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 320, 60));

        jLabel_foto.setBackground(new java.awt.Color(238, 218, 222));
        jLabel_foto.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel_foto.setOpaque(true);
        getContentPane().add(jLabel_foto, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 270, 200));

        jButton_hapus.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jButton_hapus.setForeground(new java.awt.Color(255, 255, 255));
        jButton_hapus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jButton_hapus.setText("Reset");
        jButton_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_hapusMouseClicked(evt);
            }
        });
        getContentPane().add(jButton_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 560, 130, 50));

        jButton_browse.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jButton_browse.setForeground(new java.awt.Color(255, 255, 255));
        jButton_browse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jButton_browse.setText("Pilih");
        jButton_browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_browseMouseClicked(evt);
            }
        });
        getContentPane().add(jButton_browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 130, 50));

        saveMobil.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        saveMobil.setForeground(new java.awt.Color(255, 255, 255));
        saveMobil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveMobil.setText("Simpan");
        saveMobil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMobilMouseClicked(evt);
            }
        });
        getContentPane().add(saveMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 560, 120, 50));

        jLabel_file.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel_file, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, -1, -1));

        kodeMobil.setBackground(new java.awt.Color(238, 218, 222));
        kodeMobil.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        kodeMobil.setEnabled(false);
        kodeMobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeMobilActionPerformed(evt);
            }
        });
        getContentPane().add(kodeMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 340, 50));

        jLabel_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modal_mobil.png"))); // NOI18N
        getContentPane().add(jLabel_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1040, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void namaMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaMobilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaMobilActionPerformed

    private void nopolMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nopolMobilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nopolMobilActionPerformed

    private void tahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tahunActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void jButton_browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_browseMouseClicked
        // TODO add your handling code here:
        System.out.println("Pressed Browse");
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jLabel_file.setText(path);
            jLabel_foto.setIcon(resizeImage(path));

            try {
                //                konversi blob gmbr ke imageicon
                byte[] img = Files.readAllBytes(selectedFile.toPath());
                ImageIcon imageIcon = new ImageIcon(img);

                // Set Ukuran gambar
                int labelWidth = 200;
                int labelHeigth = 400;

                // Size gmbr asli
                int imageWidth = imageIcon.getIconWidth();
                int imageHeigth = imageIcon.getIconHeight();

                // hitung skala untk size gmbr yg baru
                double scaleX = (double) labelWidth / (double) imageWidth;
                double scaleY = (double) labelHeigth / (double) imageHeigth;
                double scale = Math.min(scaleX, scaleY);

                //Ubah size gmbr dng skala yg sudah dihitung
                Image scaledImage = imageIcon.getImage().getScaledInstance((int) (scale * imageWidth), (int) (scale * imageHeigth), Image.SCALE_SMOOTH);

                //Tampilkan imageIcon ke Jlabel_foto
                jLabel_foto.setIcon(new ImageIcon(scaledImage));
                isBrowsePressed = true;
            } catch (Exception e) {

            }

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File");
        }
    }//GEN-LAST:event_jButton_browseMouseClicked

    private void saveMobilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMobilMouseClicked
        // TODO add your handling code here:
        String nama_mobil = namaMobil.getText();
        String nopol = nopolMobil.getText();
        String pemilik = kd_pemilik.get(jComboBox_pemilik.getSelectedIndex());
        String tahun_produksi = tahun.getText();
        String harga_perhari = removeDot(harga.getText());
        String imagePath = jLabel_file.getText();

//      validation
        if (nama_mobil.isEmpty() || nopol.isEmpty() || tahun_produksi.isEmpty() || harga_perhari.isEmpty() || imagePath.isEmpty()) {
            pop_upRequired required = new pop_upRequired();
            required.setVisible(true);
            return;
        }
        try {
            Statement statement = (Statement) DB.getConnection().createStatement();
            String sql = "";
            if (id != null) {
                System.out.println("Apakah tombol ditekan");
                System.out.println(this.isBrowsePressed);
                if (!this.isBrowsePressed) {
                    System.out.println("Tidak tertekan");
                    sql = "UPDATE mobil SET nama_mobil = '" + nama_mobil + "', nopol = '" + nopol + "',tahun_produksi = '" + tahun_produksi + "',kd_pemilik = '" + pemilik + "',harga_perhari = '" + harga_perhari + "' WHERE mobil.kd_mobil = '" + kodeMobil.getText() + "'";
                    pop_upDataberhasil data = new pop_upDataberhasil();
                    data.setVisible(true);
                    PreparedStatement ps;
                    ps = DB.getConnection().prepareStatement("SELECT * FROM log ORDER BY tgl_log DESC");
                    ResultSet result = ps.executeQuery();
                    while (result.next()) {
                        String activity = "Melakukan Perubahan data Mobil";
                        ps = DB.getConnection().prepareStatement("CALL createLog('" + result.getString("kd_user") + "','" + activity + "');");
                        ps.executeUpdate();
                        break;
                    }
                } else {

                    JFileChooser f = new JFileChooser();
                    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    f.showSaveDialog(null);
                    String new_path = f.getSelectedFile().toString();
                    File oldFile = new File(imagePathOld);
                    File newFile = new File(imagePath);
                    File newFileDir = null;
                    String ext = imagePath.substring(imagePath.lastIndexOf('.') + 1);
                    newFileDir = new File(new_path + "/" + nama_mobil + "." + ext);
                    Files.delete(oldFile.toPath());
                    Files.copy(newFile.toPath(), newFileDir.toPath());
                    String pathToInsert = newFileDir.toString().replace("\\", "/");
                    sql = "UPDATE mobil SET nama_mobil = '" + nama_mobil + "', nopol = '" + nopol + "',tahun_produksi = '" + tahun_produksi + "',kd_pemilik = '" + pemilik + "',harga_perhari = '" + harga_perhari + "',gambar = '" + pathToInsert + "' WHERE mobil.kd_mobil = '" + kodeMobil.getText() + "'";
                    pop_upDataberhasil data = new pop_upDataberhasil();
                    data.setVisible(true);
                    PreparedStatement ps;
                    ps = DB.getConnection().prepareStatement("SELECT * FROM log ORDER BY tgl_log DESC");
                    ResultSet result = ps.executeQuery();
                    while (result.next()) {
                        String activity = "Melakukan Perubahan data Mobil";
                        ps = DB.getConnection().prepareStatement("CALL createLog('" + result.getString("kd_user") + "','" + activity + "');");
                        ps.executeUpdate();
                        break;
                    }
                }
            } else {

                JFileChooser f = new JFileChooser();
                f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                f.showOpenDialog(null);
                String new_path = f.getSelectedFile().toString();
                File directory = new File(new_path);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File fileawal;
                File fileakhir;
                String ext = imagePath.substring(imagePath.lastIndexOf('.') + 1);
                fileawal = new File(imagePath);
                fileakhir = new File(new_path + "/" + nama_mobil + "." + ext);
                String pathToInsert = fileakhir.toString().replace("\\", "/");
                Files.copy(fileawal.toPath(), fileakhir.toPath());
                ResultSet res = statement.executeQuery("SELECT `genMobilID`() AS `mobilID`; ");
                String kdMobil = "";
                if (res.next()) {
                    kdMobil = res.getString("mobilID");
                }
                sql = "insert into mobil VALUES('" + kdMobil + "','" + nama_mobil + "', '" + nopol + "', '" + pemilik + "','" + tahun_produksi + "','" + harga_perhari + "', '" + pathToInsert + "', 'tersedia');";
                System.out.println(sql);

                PreparedStatement ps;
                ps = DB.getConnection().prepareStatement("SELECT * FROM log ORDER BY tgl_log DESC");
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    String activity = "Melakukan Penambahan data Mobil";
                    ps = DB.getConnection().prepareStatement("CALL createLog('" + result.getString("kd_user") + "','" + activity + "');");
                    ps.executeUpdate();
                    break;
                }
            }
            statement.executeUpdate(sql);
            pop_upDataberhasil data = new pop_upDataberhasil();
            data.setVisible(true);
            statement.close();
            mobil.datatable();

            this.dispose();

        } catch (Exception e) {
            System.out.println(e);
            pop_upDataGagal datagagal = new pop_upDataGagal();
            datagagal.setVisible(true);
        }
    }//GEN-LAST:event_saveMobilMouseClicked
    public void clear() {
        namaMobil.setText(null);
        nopolMobil.setText(null);
        jComboBox_pemilik.setSelectedItem(this);
        tahun.setText(null);
        imagePath = "";
        harga.setText(null);
        jLabel_foto.setText(null);
        jLabel_file.setText(null);
        jLabel_foto.setIcon(null);
//        getContentPane().add(jLabel_foto, null);

    }
    private void jButton_hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_hapusMouseClicked
        // TODO add your handling code here:
        pop_upTanyaClear clear = new pop_upTanyaClear(this);
        clear.setVisible(true);

//                namaMobil.setText(null);
//                nopolMobil.setText(null);
//                jComboBox_pemilik.setSelectedItem(this);
//                tahun.setText(null);
//                harga.setText(null);
//                pop_upClear clear = new pop_upClear();
//                clear.setVisible(true);

    }//GEN-LAST:event_jButton_hapusMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void kodeMobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeMobilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeMobilActionPerformed

    private void hargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaKeyReleased
        // TODO add your handling code here:
      
        
        try {
//              harga.setText(formatRupiah(Integer.parseInt(harga.getText())));
            int bayar = Integer.parseInt(removeDot(harga.getText()));
            harga.setText(formatRupiah(bayar));
           
           
            
        } catch (Exception e) {
            System.out.println(e);
            harga.setText(harga.getText().substring(0, harga.getText().length() - 1));
        }
    }//GEN-LAST:event_hargaKeyReleased
//    public void tampilGambar(JLabel jLabel_foto,String id_mobil){
//        try{
//            String sql = "SELECT gambar where kd_mobil ='" + id_mobil + "'";
//            Statement stn = DB.getConnection().createStatement();
//            ResultSet rs = stn.executeQuery(sql);
//            
//            if (rs.next()) {
//                byte[] img = rs.getBytes("gambar");
//                if (img != null) {
//                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(jLabel_foto.getWidth(), jLabel_foto.getHeight(), Image.SCALE_SMOOTH));
//                    jLabel_foto.setIcon(imageIcon);
//                }
//                else {
//                    ImageIcon defaultIcon = new ImageIcon(getClass().getResource("../images/icon_menu/mobil.png"));
//                    jLabel_foto.setIcon(defaultIcon);
//                }
//            }
//        } catch(Exception e){
//            
//        }
//    }

    public ImageIcon resizeImage(String imagePath) {
        ImageIcon imgIcon = new ImageIcon(imagePath);
        Image img = imgIcon.getImage();
        Image newImg = img.getScaledInstance(jLabel_foto.getWidth(), jLabel_foto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public String getImagePath() {

        return imagePath;

    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

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
            java.util.logging.Logger.getLogger(Form_Mobilv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Mobilv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Mobilv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Mobilv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Mobilv2(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jButton_browse;
    private javax.swing.JLabel jButton_hapus;
    private javax.swing.JComboBox<String> jComboBox_pemilik;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_back;
    private javax.swing.JLabel jLabel_file;
    private javax.swing.JLabel jLabel_foto;
    private javax.swing.JTextField kodeMobil;
    private javax.swing.JTextField namaMobil;
    private javax.swing.JTextField nopolMobil;
    private javax.swing.JLabel saveMobil;
    private javax.swing.JTextField tahun;
    // End of variables declaration//GEN-END:variables
}
