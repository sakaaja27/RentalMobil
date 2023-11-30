/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package rental;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import DB_koneksi.DB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author sakab
 */
public class dashboardAdmin extends javax.swing.JPanel {

    /**
     * Creates new form dashboardAdmin
     */
    public dashboardAdmin() {
        initComponents();
        generateData();
        setData(getMobilLength(), getSupirLength());
        generateChart();
    }

    public void setData(int mobilLength, int supirLength) {
        total_mobil.setText(String.valueOf(mobilLength));
        total_supir.setText(String.valueOf(supirLength));
    }

    public void generateChart() {
        try {

            DefaultCategoryDataset chart = new DefaultCategoryDataset();

            for (int i = 0; i < 12; i++) {
                Statement statement = (Statement) DB.getConnection().createStatement();
                String total = "0";
                System.out.println(total);
                ResultSet result = statement.executeQuery("select count(v_detailsewa.kd_detail_sewa) as jml_sewa from v_detailsewa where tgl_sewa BETWEEN '2023-+" + (i + 1) + "-01' AND '2023-" + (i + 1) + "-31'");
                result.next();
                total = result.getString("jml_sewa");
                chart.setValue(Integer.parseInt(total), "Penyewaan perbulan", String.valueOf(i + 1));
                System.out.println("select count(v_detailsewa.kd_detail_sewa) as jml_sewa from v_detailsewa where tgl_sewa BETWEEN '2023-" + (i + 1) + "-01' AND '2023-" + (i + 1) + "-31'");
//                result.updateString("jml_sewa", "0");
                statement.clearBatch();
                result.close();
            }

            JFreeChart barChart = ChartFactory.createBarChart("Penyewaan perbulan", "", "", chart, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot barchrt = barChart.getCategoryPlot();
            barchrt.setRangeGridlinePaint(Color.ORANGE);
            ChartPanel barPanel = new ChartPanel(barChart);
            jPanel4.removeAll();
//        barPanel.setSize();
            jPanel4.add(barPanel, BorderLayout.CENTER);
            jPanel4.revalidate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int getMobilLength() {
        int dataLength = 0;
        try {
            StringBuilder query = new StringBuilder("SELECT COUNT(mobil.kd_mobil) AS total_mobil FROM mobil");
            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query.toString());
            result.next();
            dataLength = Integer.parseInt(result.getString("total_mobil"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return dataLength;
    }

    public int getSupirLength() {
        int dataLength = 0;
        try {
            StringBuilder query = new StringBuilder("SELECT COUNT(supir.kd_supir) AS total_supir FROM supir");
            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query.toString());
            result.next();
            dataLength = Integer.parseInt(result.getString("total_supir"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return dataLength;
    }

    public void generateData() {
        try {
            SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
            final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
            Date date = new Date();
            String now = dtf.format(date);
            Date yesterday = new Date(date.getTime() - MILLIS_IN_A_DAY);
            Date tomorrow = new Date(date.getTime() + MILLIS_IN_A_DAY);
            String yesterdayFormat = dtf.format(yesterday);
            String tomorrowFormat = dtf.format(tomorrow);
            System.out.println(yesterdayFormat + " " + tomorrowFormat);
//        System.out.println(dtf.format(date));
            StringBuilder query = new StringBuilder("SELECT * FROM v_detailsewa WHERE tgl_sewa BETWEEN ");
            query.append("'" + now + " 00:00:00' AND '" + tomorrowFormat + " 00:00:00' ORDER BY tgl_sewa DESC");
            System.out.println(query);
            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet res = statement.executeQuery(query.toString());
            generateTable(res);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void generateCard(ResultSet res) {
        try {

            System.out.println(res.getString("gambar_mobil"));
            ImageIcon image = new ImageIcon(res.getString("gambar_mobil"));
            Image im = image.getImage();
            Image myimg = im.getScaledInstance(gambar_mobil.getWidth(), gambar_mobil.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon newImage = new ImageIcon(myimg);
            gambar_mobil.setIcon(newImage);
            nama_customer.setText(res.getString("nama_customer"));
            tanggal_sewa.setText(res.getString("tgl_sewa").split(" ")[0]);
            nama_supir.setText(res.getString("nama_supir"));
            status_bayar.setText(res.getString("status"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void generateTable(ResultSet res) {
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("nama customer");
        tbl.addColumn("merek mobil");
        tbl.addColumn("nama supir");
        tbl.addColumn("jml_hari");
        tbl.addColumn("tgl_sewa");
        tbl_sewa.setModel(tbl);

        try {
            int i = 0;
            System.out.println(res);
            while (res.next()) {
                if (i == 0) {
                    generateCard(res);
                }
                tbl.addRow(new Object[]{
                    res.getString("nama_customer"),
                    res.getString("nama_mobil"),
                    res.getString("nama_supir"),
                    res.getString("jml_hari"),
                    res.getString("tgl_sewa"), //                     res.getString("gambar"),
                //                    res.getString("status")
                });
                tbl_sewa.setModel(tbl);
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_sewa = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        icon_mobil = new javax.swing.JLabel();
        total_mobil = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        total_supir = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        icon_supir = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        gambar_mobil = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        nama_customer = new javax.swing.JLabel();
        tanggal_sewa = new javax.swing.JLabel();
        nama_supir = new javax.swing.JLabel();
        status_bayar = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_sewa = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();

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
        jScrollPane1.setViewportView(jTable_sewa);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(177, 16, 7));
        jLabel8.setText("Dashboard");

        jPanel1.setBackground(new java.awt.Color(177, 16, 7));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 100));

        icon_mobil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_mobil.setMaximumSize(new java.awt.Dimension(28, 23));
        icon_mobil.setMinimumSize(new java.awt.Dimension(28, 23));
        icon_mobil.setPreferredSize(new java.awt.Dimension(28, 23));

        total_mobil.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        total_mobil.setForeground(new java.awt.Color(255, 255, 255));
        total_mobil.setText("14");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mobil Tersedia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(icon_mobil, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(total_mobil)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icon_mobil, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_mobil, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        ImageIcon image = new ImageIcon(getClass().getResource("/images/icon_menu/mobil_1.png"));
        Image im = image.getImage();
        icon_mobil.setSize(45, 40);
        Image myimg = im.getScaledInstance(icon_mobil.getWidth(), icon_mobil.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImage = new ImageIcon(myimg);
        icon_mobil.setIcon(newImage);

        jPanel2.setBackground(new java.awt.Color(177, 16, 7));
        jPanel2.setPreferredSize(new java.awt.Dimension(150, 100));

        total_supir.setFont(new java.awt.Font("Trebuchet MS", 1, 48)); // NOI18N
        total_supir.setForeground(new java.awt.Color(255, 255, 255));
        total_supir.setText("14");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Supir Tersedia");

        icon_supir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon_supir.setMaximumSize(new java.awt.Dimension(28, 23));
        icon_supir.setMinimumSize(new java.awt.Dimension(28, 23));
        icon_supir.setPreferredSize(new java.awt.Dimension(28, 23));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(icon_supir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(total_supir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(icon_supir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total_supir, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        image = new ImageIcon(getClass().getResource("/images/icon_menu/grup.png"));
        im = image.getImage();
        icon_supir.setSize(45, 38);
        myimg = im.getScaledInstance(icon_supir.getWidth(), icon_supir.getHeight(), Image.SCALE_SMOOTH);
        newImage = new ImageIcon(myimg);
        icon_supir.setIcon(newImage);

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(177, 16, 7));
        jLabel9.setText("Penyewaan Hari Ini");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 250));

        gambar_mobil.setBackground(new java.awt.Color(238, 218, 222));
        gambar_mobil.setRequestFocusEnabled(false);
        gambar_mobil.setVerifyInputWhenFocusTarget(false);

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel10.setText("Nama");

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel11.setText("Tanggal");

        jLabel12.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel12.setText("Supir");

        jLabel13.setFont(new java.awt.Font("Noto Sans", 1, 16)); // NOI18N
        jLabel13.setText("Status");

        nama_customer.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        nama_customer.setForeground(new java.awt.Color(177, 16, 7));
        nama_customer.setText("nama_customer");

        tanggal_sewa.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        tanggal_sewa.setForeground(new java.awt.Color(177, 16, 7));
        tanggal_sewa.setText("tanggal_sewa");

        nama_supir.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        nama_supir.setForeground(new java.awt.Color(177, 16, 7));
        nama_supir.setText("nama_supir");

        status_bayar.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        status_bayar.setForeground(new java.awt.Color(177, 16, 7));
        status_bayar.setText("status_bayar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gambar_mobil, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_customer)
                            .addComponent(tanggal_sewa)
                            .addComponent(nama_supir)
                            .addComponent(status_bayar)))
                    .addComponent(jLabel12))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(gambar_mobil, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nama_customer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tanggal_sewa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(nama_supir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(status_bayar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        //jPanel3.add(gambar_mobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 190, 120));
        gambar_mobil.getAccessibleContext().setAccessibleDescription("");
        gambar_mobil.setSize(200, 152);

        tbl_sewa.setBackground(new java.awt.Color(238, 218, 222));
        tbl_sewa.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_sewa);

        jPanel4.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel gambar_mobil;
    private javax.swing.JLabel icon_mobil;
    private javax.swing.JLabel icon_supir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_sewa;
    private javax.swing.JLabel nama_customer;
    private javax.swing.JLabel nama_supir;
    private javax.swing.JLabel status_bayar;
    private javax.swing.JLabel tanggal_sewa;
    private javax.swing.JTable tbl_sewa;
    private javax.swing.JLabel total_mobil;
    private javax.swing.JLabel total_supir;
    // End of variables declaration//GEN-END:variables
}
