/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package rental;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import DB_koneksi.DB;
import Laporan.ModelData;
import Laporan.ModelDataSewa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import raven.chart.ModelChart;

/**
 *
 * @author sakab
 */
public class dashboardOwner extends javax.swing.JPanel {

    /**
     * Creates new form dashboardAdmin
     */
    public dashboardOwner() {
        initComponents();
        chart.setTitle("Laporan Keuangan");
        chart.addLegend("Keuangan", Color.decode("#7b4397"), Color.decode("#dc2430"));
        chartsewa.setTitle("Laporan Penyewaan");
        chartsewa.addLegend("Penyewaan", Color.decode("#7b4397"), Color.decode("#dc2430"));
        generateData();
        setData(getMobilLength(), getSupirLength(), getTransaksiLength());
        generateChart();
        generateChartperbulan();
    }

    public void setData(int mobilLength, int supirLength, int TransaksiLength) {
        total_mobil.setText(String.valueOf(mobilLength));
        total_sopir.setText(String.valueOf(supirLength));

        jml_perjalanan.setText(String.valueOf(TransaksiLength));
    }

    public void generateChartperbulan() {
        try {
            List<ModelData> lists = new ArrayList<>();
            DB.getConnection().createStatement();
            String sql = "select DATE_FORMAT(MAX(l.Date),'%M') as `Month`, count(l.kd_detail_sewa) as `Amount` from v_chartsewa l group by DATE_FORMAT(l.Date,'%m%Y') order by MAX(l.Date) DESC limit 7;";
            PreparedStatement p = DB.getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Month");
                double amount = r.getDouble("Amount");

                lists.add(new ModelData(month, amount));
            }
            r.close();
            p.close();
            //  Add Data to chart
            String[] shortMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            String[] amountPerMonth = new String[months.length];
            for (int i = 0; i < months.length; i++) {
//                monthsWithValue[i][0] = months[i];
                for (int j = 0; j < lists.size(); j++) {
                    if (months[i].equals(lists.get(j).getMonth())) {
                        amountPerMonth[i] = String.valueOf(lists.get(j).getAmount());
                        break;
                    } else {
                        amountPerMonth[i] = "0";
                    }
                }
            }
            System.out.println("Chart Sewa");
            for (int i = 0; i < amountPerMonth.length; i++) {

                System.out.println(shortMonths[i] + " " + amountPerMonth[i]);
                chartsewa.addData(new ModelChart(shortMonths[i], new double[]{Double.parseDouble(amountPerMonth[i])}));
            }
//            for (int i = lists.size() - 12; i >= 0; i--) {
//                ModelData d = lists.get(i);
//            }
            //  Start to show data with animation
            chartsewa.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void generateChart() {
        try {
            List<ModelData> lists = new ArrayList<>();
            DB.getConnection().createStatement();
            String sql = "select DATE_FORMAT(MAX(l.Date),'%M') as `Month`, SUM(l.subtotal) as `Amount` from v_laporankeuangan l group by DATE_FORMAT(l.Date,'%m%Y') order by MAX(l.Date) DESC limit 7;";
            PreparedStatement p = DB.getConnection().prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Month");
                double amount = r.getDouble("Amount");

                lists.add(new ModelData(month, amount));
            }
            r.close();
            p.close();
            //  Add Data to chart
            String[] shortMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

            String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            String[] amountPerMonth = new String[months.length];
            for (int i = 0; i < months.length; i++) {
//                monthsWithValue[i][0] = months[i];
                for (int j = 0; j < lists.size(); j++) {
                    if (months[i].equals(lists.get(j).getMonth())) {
                        amountPerMonth[i] = String.valueOf(lists.get(j).getAmount());
                        break;
                    } else {
                        amountPerMonth[i] = "0";
                    }
                }
            }
//            System.out.println("Chart Keuangan");
            for (int i = 0; i < amountPerMonth.length; i++) {
//                System.out.println(shortMonths[i] + " " + amountPerMonth[i]);
                chart.addData(new ModelChart(shortMonths[i], new double[]{Double.parseDouble(amountPerMonth[i])}));
            }
//            for (int i = lists.size() - 12; i >= 0; i--) {
//                ModelData d = lists.get(i);
//            }
            //  Start to show data with animation
            chart.start();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int getTransaksiLength() {
        int dataLength = 0;
        try {
            StringBuilder query = new StringBuilder("SELECT COUNT(status) as jml_perjalanan FROM detail_sewa where status = 'perjalanan';");
            Statement statement = (Statement) DB.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query.toString());
            result.next();

            dataLength = Integer.parseInt(result.getString("jml_perjalanan"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return dataLength;
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
//            generateTable(res);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//    public void generateCard(ResultSet res) {
//        try {
//
//            System.out.println(res.getString("gambar_mobil"));
//            ImageIcon image = new ImageIcon(res.getString("gambar_mobil"));
//            Image im = image.getImage();
//            Image myimg = im.getScaledInstance(gambar_mobil.getWidth(), gambar_mobil.getHeight(), Image.SCALE_SMOOTH);
//            ImageIcon newImage = new ImageIcon(myimg);
//            gambar_mobil.setIcon(newImage);
//            nama_customer.setText(res.getString("nama_customer"));
//            tanggal_sewa.setText(res.getString("tgl_sewa").split(" ")[0]);
//            nama_supir.setText(res.getString("nama_supir"));
//            status_bayar.setText(res.getString("status"));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
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
        total_mobil = new javax.swing.JLabel();
        jml_perjalanan = new javax.swing.JLabel();
        total_sopir = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        chart = new raven.chart.CurveLineChart();
        jPanel1 = new javax.swing.JPanel();
        chartsewa = new raven.chart.CurveLineChart();
        jLabel1 = new javax.swing.JLabel();

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
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        total_mobil.setFont(new java.awt.Font("Trebuchet MS", 1, 96)); // NOI18N
        total_mobil.setForeground(new java.awt.Color(255, 255, 255));
        total_mobil.setText("99");
        add(total_mobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 180, 180, 100));

        jml_perjalanan.setFont(new java.awt.Font("Trebuchet MS", 1, 96)); // NOI18N
        jml_perjalanan.setForeground(new java.awt.Color(255, 255, 255));
        jml_perjalanan.setText("99");
        add(jml_perjalanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 180, 100));

        total_sopir.setFont(new java.awt.Font("Trebuchet MS", 1, 96)); // NOI18N
        total_sopir.setForeground(new java.awt.Color(255, 255, 255));
        total_sopir.setText("99");
        add(total_sopir, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 180, 180, 100));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        chart.setBackground(new java.awt.Color(93, 13, 13));
        chart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51), 5));
        chart.setForeground(new java.awt.Color(82, 16, 16));
        chart.setFillColor(true);
        jPanel4.add(chart, java.awt.BorderLayout.CENTER);

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, 710, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(177, 0, 7), 5));
        jPanel1.setLayout(new java.awt.BorderLayout());

        chartsewa.setBackground(new java.awt.Color(153, 0, 0));
        chartsewa.setForeground(new java.awt.Color(204, 0, 0));
        chartsewa.setFillColor(true);
        jPanel1.add(chartsewa, java.awt.BorderLayout.CENTER);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 660, 400));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Content.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1490, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart chart;
    private raven.chart.CurveLineChart chartsewa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_sewa;
    private javax.swing.JLabel jml_perjalanan;
    private javax.swing.JLabel total_mobil;
    private javax.swing.JLabel total_sopir;
    // End of variables declaration//GEN-END:variables
}
