/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.kasir;

import koneksi.koneksi;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static koneksi.koneksi.con2;
import main.login;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Raynold
 */
public class formKasir extends javax.swing.JFrame {

    Statement stt;
    Statement stt2;
    ResultSet rss;
    koneksi koneksi;
    DefaultTableModel model;
    String tgl;

    // Variable IReport
    JasperReport JasRep;
    JasperPrint JasPri;
    Map param = new HashMap();
    JasperDesign JasDes;
    
    public formKasir() {
        koneksi = new koneksi();
        initComponents();
        home.setVisible(true);
        makanan.setVisible(false);
        transaksi.setVisible(false);
        
        loadData_menu();
        loadData_transaksi();
        IDOtomatis();
        tglskrg();
        kode_makanan();
        this.setLocationRelativeTo(null);
        this.setTitle("Form Kasir");
    }

    public JTextField id_kasir(){
        return txt_idKasir;
    }
    
    private void loadData_menu(){
        Object header[] = {
            "ID PAKET","NAMA PAKET","KETERANGAN","HARGA"
        };
        DefaultTableModel data = new DefaultTableModel(null, header);
        tbl_makanan.setModel(data);
        String sql = "SELECT * FROM tbl_menu";
        try {
            stt = koneksi.con2.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                String k1 = rss.getString(1);
                String k2 = rss.getString(2);
                String k3 = rss.getString(3);
                String k4 = rss.getString(4);
                String k[] = {k1,k2,k3,k4};
                data.addRow(k);
            } 
        } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void loadData_transaksi(){
        Object header[] = {
            "ID TRANSAKSI","ID KARYAWAN","PAKET","JUMLAH","TOTAL HARGA", "TANGGAL"
        };
        DefaultTableModel data = new DefaultTableModel(null, header);
        tbl_transaksi.setModel(data);
        String sql = "SELECT tbl_transaksi.ID_TRANSAKSI, tbl_transaksi.ID_KARYAWAN, tbl_menu.NAMA_PAKET, "
                + "tbl_transaksi.JUMLAH,tbl_transaksi.TOTAL_HARGA, tbl_transaksi.TANGGAL FROM "
                + "tbl_transaksi INNER JOIN tbl_menu ON tbl_transaksi.ID_PAKET = tbl_menu.ID_PAKET;";
        try {
            stt = koneksi.con2.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                String k1 = rss.getString(1);
                String k2 = rss.getString(2);
                String k3 = rss.getString(3);
                String k4 = rss.getString(4);
                String k5 = rss.getString(5);
                String k6 = rss.getString(6);
                String k[] = {k1,k2,k3,k4,k5,k6};
                data.addRow(k);
            } 
        } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void IDOtomatis() {
        try {
            stt=koneksi.con2.createStatement();
            String sql="SELECT * FROM tbl_transaksi order by ID_TRANSAKSI desc";
            rss=stt.executeQuery(sql);
            if(rss.next()) {
                String ID=rss.getString("ID_TRANSAKSI");
                
                txt_idTransaksi.setText("" + (Integer.parseInt(ID) + 1));
            } else {
                txt_idTransaksi.setText("1");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Insert
    public void tambah_menu(int total) {
        try {
            txt_totHarga.setText(total+"");
            String sql = "INSERT INTO tbl_transaksi ("
                    + "ID_TRANSAKSI,ID_PAKET,ID_KARYAWAN,JUMLAH,TOTAL_HARGA,TANGGAL"
                    + ") VALUES ('"
                    +txt_idTransaksi.getText()+"','"
                    +cmb_kdMakan.getSelectedItem().toString()+"','"
                    +txt_idKasir.getText()+"','"
                    +txt_jumlah_t.getText()+"','"
                    +total+"','"
                    +tgl+"')";
            
            stt.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Menu Berhasil Dimasukan");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Delete 
    private void hapus_transaksi(){
        try {
            stt = koneksi.con2.createStatement();
            String sql_delete = "Delete FROM tbl_transaksi WHERE ID_TRANSAKSI='"+txt_idSelection.getText()+"'";
            stt.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data berhasil di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void tglskrg(){
        Date skrg= new Date();
        SimpleDateFormat format= new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd");
        tgl = format2.format(skrg);
        txt_tglTransaksi.setText(format.format(skrg));
    }
    
    public void kode_makanan(){
        String sql = "SELECT * FROM tbl_menu";
        try {
            stt = con2.createStatement();
            rss = stt.executeQuery(sql);
            while(rss.next()){
                cmb_kdMakan.addItem(rss.getString("id_paket"));
            }
        } catch (Exception e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        transaksi = new javax.swing.JPanel();
        txt_idKasir = new javax.swing.JTextField();
        txt_tglTransaksi = new javax.swing.JTextField();
        txt_idTransaksi = new javax.swing.JTextField();
        cmb_kdMakan = new javax.swing.JComboBox();
        txt_namaPaket = new javax.swing.JTextField();
        txt_harga_t = new javax.swing.JTextField();
        txt_jumlah_t = new javax.swing.JTextField();
        txt_totHarga = new javax.swing.JTextField();
        btn_pesan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        txt_idSelection = new javax.swing.JTextField();
        btn_cetakStruk = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        makanan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_makanan = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu_logout = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu_transaksi = new javax.swing.JMenuItem();
        jMenuLaporanTrans = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu_paket = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        home.setPreferredSize(new java.awt.Dimension(600, 500));
        home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel2.setText("LEVEL USER KASIR");
        home.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        getContentPane().add(home, "card3");

        transaksi.setPreferredSize(new java.awt.Dimension(600, 600));
        transaksi.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idKasir.setEditable(false);
        transaksi.add(txt_idKasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 80, -1));

        txt_tglTransaksi.setEditable(false);
        txt_tglTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tglTransaksiActionPerformed(evt);
            }
        });
        transaksi.add(txt_tglTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 250, -1));

        txt_idTransaksi.setEditable(false);
        txt_idTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idTransaksiActionPerformed(evt);
            }
        });
        transaksi.add(txt_idTransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 93, -1));

        cmb_kdMakan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_kdMakanItemStateChanged(evt);
            }
        });
        transaksi.add(cmb_kdMakan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 250, -1));

        txt_namaPaket.setEnabled(false);
        transaksi.add(txt_namaPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 250, -1));

        txt_harga_t.setEnabled(false);
        transaksi.add(txt_harga_t, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 250, -1));

        txt_jumlah_t.setText("1");
        txt_jumlah_t.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jumlah_tKeyTyped(evt);
            }
        });
        transaksi.add(txt_jumlah_t, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 250, -1));

        txt_totHarga.setEditable(false);
        txt_totHarga.setEnabled(false);
        transaksi.add(txt_totHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 250, -1));

        btn_pesan.setText("Pesan");
        btn_pesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesanActionPerformed(evt);
            }
        });
        transaksi.add(btn_pesan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        transaksi.add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, -1, -1));

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_transaksi);

        transaksi.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 580, 95));

        txt_idSelection.setEditable(false);
        txt_idSelection.setText("ID Selection");
        txt_idSelection.setEnabled(false);
        transaksi.add(txt_idSelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, -1));

        btn_cetakStruk.setText("Cetak Struk Pelanggan");
        btn_cetakStruk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetakStrukActionPerformed(evt);
            }
        });
        transaksi.add(btn_cetakStruk, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, -1, -1));
        transaksi.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 380, 10));

        jLabel6.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel6.setText("FORM TRANSAKSI MAKANAN");
        transaksi.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jLabel12.setText("Tanggal Transaksi");
        transaksi.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));

        jLabel13.setText("Id Transaksi");
        transaksi.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        jLabel14.setText("Kode Paket Makanan");
        transaksi.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jLabel15.setText("Nama Paket");
        transaksi.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jLabel16.setText("Harga");
        transaksi.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jLabel17.setText("Jumlah");
        transaksi.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        jLabel7.setText("Id Karyawan");
        transaksi.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, -1, -1));

        jLabel3.setText("Total Harga");
        transaksi.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        getContentPane().add(transaksi, "card4");

        makanan.setPreferredSize(new java.awt.Dimension(600, 500));
        makanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel1.setText("FORM PAKET MAKANAN");
        makanan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 34, -1, -1));

        tbl_makanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_makanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_makananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_makanan);

        makanan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 570, 140));

        getContentPane().add(makanan, "card2");

        jMenu1.setText("Profile");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenu_logout.setText("Log Out");
        jMenu_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_logoutActionPerformed(evt);
            }
        });
        jMenu1.add(jMenu_logout);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Transaksi");

        jMenu_transaksi.setText("Transaksi Penjualan");
        jMenu_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_transaksiActionPerformed(evt);
            }
        });
        jMenu3.add(jMenu_transaksi);

        jMenuLaporanTrans.setText("Laporan Penjualan");
        jMenuLaporanTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLaporanTransActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuLaporanTrans);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Makanan");

        jMenu_paket.setText("Daftar Paket Makanan");
        jMenu_paket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_paketActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu_paket);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu_paketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_paketActionPerformed
        home.setVisible(false);
        transaksi.setVisible(false);
        makanan.setVisible(true);
    }//GEN-LAST:event_jMenu_paketActionPerformed

    private void tbl_makananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_makananMouseClicked
    }//GEN-LAST:event_tbl_makananMouseClicked

    private void jMenu_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_transaksiActionPerformed
        transaksi.setVisible(true);
        makanan.setVisible(false);
        home.setVisible(false);
    }//GEN-LAST:event_jMenu_transaksiActionPerformed

    private void cmb_kdMakanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_kdMakanItemStateChanged
        ResultSet rs2;
        String sql = "SELECT * FROM tbl_menu WHERE ID_PAKET = '"+cmb_kdMakan.getSelectedItem().toString()+"'";
        try {
            stt = con2.createStatement();
            rs2 = stt.executeQuery(sql);
            if(rs2.next()){
                txt_namaPaket.setText(rs2.getString("NAMA_PAKET"));
                txt_harga_t.setText(rs2.getString("HARGA"));
            }
            rs2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmb_kdMakanItemStateChanged

    private void btn_pesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesanActionPerformed
        String id = "ID Selection";
        if(btn_hapus.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane, "Data tidak boleh kosong");
        } else {
            try {
                if(txt_idTransaksi.getText().equals("")
                    ||txt_namaPaket.getText().equals("")
                    ||txt_idKasir.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Mohon diisi data yang kosong",
                        "Pesan", JOptionPane.WARNING_MESSAGE);
                } else {
                    int Confirm = JOptionPane.showOptionDialog(this, "Ingin simpan data?", "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(Confirm == JOptionPane.YES_OPTION){
                        int tot = Integer.parseInt(txt_harga_t.getText()) * Integer.parseInt(txt_jumlah_t.getText());
                        tambah_menu(tot);
                        JOptionPane.showMessageDialog(this, "Barang Berhasil Dibeli");
                        IDOtomatis();
                        loadData_transaksi();
                        txt_idSelection.setText(id);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_pesanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        String id = "ID Selection";
        try {
            stt = con2.createStatement();
            if(txt_idSelection.getText().equals(id)){
                JOptionPane.showMessageDialog(this, "Anda Belum Memilih Tabel");
            } else {
                int Confirm = JOptionPane.showOptionDialog(this, "Ingin hapus data transaksi ke-"
                        +txt_idSelection.getText()+"?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(Confirm == JOptionPane.YES_OPTION){
                    hapus_transaksi();
                    loadData_transaksi();
                    IDOtomatis();
                    txt_idSelection.setText(id);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void jMenu_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_logoutActionPerformed
        login lgn = new login();
        this.dispose();
        lgn.setVisible(true);
    }//GEN-LAST:event_jMenu_logoutActionPerformed

    private void txt_tglTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tglTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tglTransaksiActionPerformed

    private void btn_cetakStrukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetakStrukActionPerformed
        String x = txt_idTransaksi.getText();
        if (txt_idSelection.getText().equals("ID Selection")) {
            int y = Integer.parseInt(x)-1;
            x = y+"";
        } else {
            x = txt_idSelection.getText();
        }
        
        int Confirm = JOptionPane.showOptionDialog(this, "Cetak Struk dengan ID ke-"
                +x+"?", "Konfirmasi", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, null, null);    
        
        if(Confirm == JOptionPane.YES_OPTION){
            struk(x);
        }                
    }//GEN-LAST:event_btn_cetakStrukActionPerformed

    public void struk(String x){
        try {
            File file = new File("src\\report/Struk_pelanggan.jrxml");
//            InputStream file = getClass().getResourceAsStream("/report/Struk_pelanggan.jrxml");
            JasDes = JRXmlLoader.load(file);
            HashMap map = new HashMap();
            map.put("ID", x);
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, map, koneksi.con2);
            JasperViewer.viewReport(JasPri, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jMenuLaporanTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuLaporanTransActionPerformed
        try {
            File file = new File("src\\report/Laporan_transaksi.jrxml");
//            InputStream file = getClass().getResourceAsStream("/report/Laporan_transaksi.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, koneksi.con2);
            JasperViewer.viewReport(JasPri, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jMenuLaporanTransActionPerformed

    private void txt_idTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idTransaksiActionPerformed

    private void txt_jumlah_tKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlah_tKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0') && (c <= '9') && txt_jumlah_t.getText().length() < 10
            || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
        }
    }//GEN-LAST:event_txt_jumlah_tKeyTyped

    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        // TODO add your handling code here:
        int bar = tbl_transaksi.getSelectedRow();       
        String x = tbl_transaksi.getValueAt(bar, 0).toString();
        txt_idSelection.setText(x);
    }//GEN-LAST:event_tbl_transaksiMouseClicked
            
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
            java.util.logging.Logger.getLogger(formKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cetakStruk;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_pesan;
    private javax.swing.JComboBox cmb_kdMakan;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuLaporanTrans;
    private javax.swing.JMenuItem jMenu_logout;
    private javax.swing.JMenuItem jMenu_paket;
    private javax.swing.JMenuItem jMenu_transaksi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel makanan;
    private javax.swing.JTable tbl_makanan;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JPanel transaksi;
    private javax.swing.JTextField txt_harga_t;
    private javax.swing.JTextField txt_idKasir;
    private javax.swing.JTextField txt_idSelection;
    private javax.swing.JTextField txt_idTransaksi;
    private javax.swing.JTextField txt_jumlah_t;
    private javax.swing.JTextField txt_namaPaket;
    private javax.swing.JTextField txt_tglTransaksi;
    private javax.swing.JTextField txt_totHarga;
    // End of variables declaration//GEN-END:variables
}
