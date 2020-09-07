/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.admin;

import koneksi.koneksi;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.login;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Raynold
 */
public class formAdmin extends javax.swing.JFrame {
    
    koneksi koneksi;
    Statement st;
    Statement stt;
    ResultSet rs;
    DefaultTableModel model;
    
    // Variable IReport
    JasperReport JasRep;
    JasperPrint JasPri;
    Map param = new HashMap();
    JasperDesign JasDes;
    
    
    public formAdmin() {
        koneksi = new koneksi();
        initComponents();
        home.setVisible(true);
        makanan.setVisible(false);
        karyawan.setVisible(false);
        loadData_menu();
        loadData_karyawan();
        IDMenu();
        
        this.setLocationRelativeTo(null);
        this.setTitle("Form Admin");
    }

    private void loadData_menu(){
        Object header[] = {
            "ID PAKET","NAMA PAKET","KETERANGAN","HARGA"
        };
        DefaultTableModel data = new DefaultTableModel(null, header);
        tbl_makanan.setModel(data);
        String sql = "SELECT * FROM tbl_menu;";
        try {
            st = koneksi.con2.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String k1 = rs.getString(1);
                String k2 = rs.getString(2);
                String k3 = rs.getString(3);
                String k4 = rs.getString(4);
                String k[] = {k1,k2,k3,k4};
                data.addRow(k);
            } 
        } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e+" "+st);
        }
    }
    private void IDMenu() {
        try {
            st=koneksi.con2.createStatement();
            String sql="SELECT * FROM tbl_menu order by ID_PAKET desc";
            rs=st.executeQuery(sql);
            if(rs.next()) {
                String ID=rs.getString("ID_PAKET").substring(1);
                String NO = "" + (Integer.parseInt(ID) + 1);
                String Nol = "";
                if(NO.length()==1) {
                    Nol="00";
                } else if(NO.length()==2) {
                    Nol="0";
                }
                txt_idMakanan.setText("P" + Nol + NO);
            } else {
                txt_idMakanan.setText("P001");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Input
    public void tambah_menu() {
        try {
            //Query Input ke Tabel USer
            String sql="INSERT INTO tbl_menu values"
                + "('"+txt_idMakanan.getText()
                +"','"+txt_namaPaket.getText()
                +"','"+txt_keterangan.getText()
                +"','"+txt_harga.getText()
                +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Menu Berhasil Dimasukan");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Update
    private void edit_menu(){
        try {
            // Query Input ke Table User
            String sql_edit = "UPDATE tbl_menu SET "
                +"NAMA_PAKET = '"+txt_namaPaket.getText()+"',"
                +"KETERANGAN = '"+txt_keterangan.getText()+"',"
                +"HARGA = '"+txt_harga.getText()+"' "
                +"WHERE ID_PAKET = '"+txt_idMakanan.getText()+"';";
            st.execute(sql_edit);
            JOptionPane.showMessageDialog(null, "Data berhasil di Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Delete 
    private void hapus_menu(){
        try {
            stt = koneksi.con2.createStatement();
            String sql_delete = "Delete FROM tbl_menu WHERE ID_PAKET='"+txt_idMakanan.getText()+"'";
            stt.executeUpdate(sql_delete);
            JOptionPane.showMessageDialog(null, "Data berhasil di Hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void loadData_karyawan(){
        Object header[] = {
            "ID KARYAWAN","LEVEL","NAMA KARYAWAN","JENIS KELAMIN","NO HANDPHONE", "USERNAME", "PASSWORD"
        };
        DefaultTableModel data = new DefaultTableModel(null, header);
        tbl_user.setModel(data);
        String sql = "SELECT tbl_karyawan.ID_KARYAWAN, tbl_level.LEVEL, tbl_karyawan.NAMA_USER, "
                + "tbl_karyawan.JK,tbl_karyawan.NOPE, tbl_karyawan.USERNAME, tbl_karyawan.PASSWORD FROM "
                + "tbl_karyawan INNER JOIN tbl_level ON tbl_karyawan.ID_LEVEL = tbl_level.ID_LEVEL;";
        try {
            st = koneksi.con2.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String k1 = rs.getString(1);
                String k2 = rs.getString(2);
                String k3 = rs.getString(3);
                String k4 = rs.getString(4);
                if (k4.equals("L")) {
                    k4 = "Laki-laki";
                } else if(k4.equals("P")){
                    k4 = "Perempuan";
                }
                String k5 = rs.getString(5);
                String k6 = rs.getString(6);
                String k7 = rs.getString(7);
                String k[] = {k1,k2,k3,k4,k5,k6,k7};
                data.addRow(k);
            } 
        } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        home = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        makanan = new javax.swing.JPanel();
        txt_idMakanan = new javax.swing.JTextField();
        txt_namaPaket = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_keterangan = new javax.swing.JTextArea();
        txt_harga = new javax.swing.JTextField();
        btn_simpan_m = new javax.swing.JButton();
        btn_ubah_m = new javax.swing.JButton();
        btn_hapus_m = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_makanan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        karyawan = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_user = new javax.swing.JTable();
        btn_cetak_user = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu_logOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu_daftarPaket = new javax.swing.JMenuItem();
        jMenu_transaksi = new javax.swing.JMenuItem();
        jMenu_lapPenjualan = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        home.setPreferredSize(new java.awt.Dimension(600, 500));
        home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel7.setText("LEVEL USER ADMIN");
        home.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, -1));

        getContentPane().add(home, "card3");

        makanan.setPreferredSize(new java.awt.Dimension(600, 500));
        makanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_idMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idMakananActionPerformed(evt);
            }
        });
        makanan.add(txt_idMakanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 110, 220, 30));
        makanan.add(txt_namaPaket, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 144, 220, -1));

        txt_keterangan.setColumns(20);
        txt_keterangan.setRows(5);
        jScrollPane3.setViewportView(txt_keterangan);

        makanan.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 176, 220, 70));

        txt_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hargaActionPerformed(evt);
            }
        });
        txt_harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hargaKeyTyped(evt);
            }
        });
        makanan.add(txt_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 220, 30));

        btn_simpan_m.setText("Simpan");
        btn_simpan_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpan_mActionPerformed(evt);
            }
        });
        makanan.add(btn_simpan_m, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));

        btn_ubah_m.setText("Ubah");
        btn_ubah_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubah_mActionPerformed(evt);
            }
        });
        makanan.add(btn_ubah_m, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 66, -1));

        btn_hapus_m.setText("Hapus");
        btn_hapus_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus_mActionPerformed(evt);
            }
        });
        makanan.add(btn_hapus_m, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, -1, -1));

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

        makanan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 315, 509, 179));

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel1.setText("FORM PAKET MAKANAN");
        makanan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 34, -1, -1));

        jLabel4.setText("Harga");
        makanan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        jLabel2.setText("ID Paket Makanan");
        makanan.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 113, -1, -1));

        jLabel3.setText("Nama Paket Makanan");
        makanan.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 141, -1, -1));

        jLabel5.setText("Keterangan");
        makanan.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 182, -1, -1));

        getContentPane().add(makanan, "card2");

        karyawan.setPreferredSize(new java.awt.Dimension(600, 500));
        karyawan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tekton Pro", 0, 18)); // NOI18N
        jLabel6.setText("FORM DAFTAR USER");
        karyawan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        tbl_user.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_userMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_user);

        karyawan.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 580, 230));

        btn_cetak_user.setText("Cetak");
        btn_cetak_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cetak_userActionPerformed(evt);
            }
        });
        karyawan.add(btn_cetak_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        getContentPane().add(karyawan, "card4");

        jMenu1.setText("Profile");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenu_logOut.setText("Log Out");
        jMenu_logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_logOutActionPerformed(evt);
            }
        });
        jMenu1.add(jMenu_logOut);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Menu Admin");

        jMenu_daftarPaket.setText("Daftar Paket Makanan");
        jMenu_daftarPaket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_daftarPaketActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu_daftarPaket);

        jMenu_transaksi.setText("Lihat User");
        jMenu_transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_transaksiActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu_transaksi);

        jMenu_lapPenjualan.setText("Laporan Penjualan");
        jMenu_lapPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_lapPenjualanActionPerformed(evt);
            }
        });
        jMenu2.add(jMenu_lapPenjualan);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu_daftarPaketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_daftarPaketActionPerformed
        makanan.setVisible(true);
        karyawan.setVisible(false);
        home.setVisible(false);
    }//GEN-LAST:event_jMenu_daftarPaketActionPerformed

    private void tbl_makananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_makananMouseClicked
        
        int bar = tbl_makanan.getSelectedRow();       
        txt_idMakanan.setText(tbl_makanan.getValueAt(bar, 0).toString());
        txt_namaPaket.setText(tbl_makanan.getValueAt(bar, 1).toString());
        txt_keterangan.setText(tbl_makanan.getValueAt(bar, 2).toString());
        txt_harga.setText(tbl_makanan.getValueAt(bar, 3).toString());

        txt_idMakanan.setEnabled(false);
        btn_simpan_m.setText("Batal");
    }//GEN-LAST:event_tbl_makananMouseClicked

    public void clear(){
        txt_harga.setText("");
        txt_idMakanan.setText("");
        txt_keterangan.setText("");
        txt_namaPaket.setText("");
        txt_idMakanan.setEnabled(true);
    }

    private void jMenu_transaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_transaksiActionPerformed
        karyawan.setVisible(true);
        makanan.setVisible(false);
        home.setVisible(false);
    }//GEN-LAST:event_jMenu_transaksiActionPerformed

    private void jMenu_logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_logOutActionPerformed
        login lgn = new login();
        this.setVisible(false);
        lgn.setVisible(true);
    }//GEN-LAST:event_jMenu_logOutActionPerformed

    private void btn_cetak_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cetak_userActionPerformed
        try {
            File file = new File("src\\report/Daftar_user.jrxml");
//            InputStream file = getClass().getResourceAsStream("/report/Daftar_user.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, koneksi.con2);
            JasperViewer.viewReport(JasPri, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_cetak_userActionPerformed

    private void jMenu_lapPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_lapPenjualanActionPerformed
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
    }//GEN-LAST:event_jMenu_lapPenjualanActionPerformed

    // field txt_harga hanya bisa input key 0 s/d 9 dengan jumlah karakter hingga 10 karakter
    private void txt_hargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hargaKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0')
            && (c <= '9') && txt_harga.getText().length() < 10
            || (c == KeyEvent.VK_BACK_SPACE)
            || (c == KeyEvent.VK_DELETE))) {
        getToolkit().beep();
        evt.consume();
        }
    }//GEN-LAST:event_txt_hargaKeyTyped

    private void txt_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hargaActionPerformed

    private void btn_ubah_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubah_mActionPerformed
        int row = tbl_makanan.getSelectedRow();
        try {
            if(row == -1){
                JOptionPane.showMessageDialog(this, "Mohon pilih tabel terlebih dahulu","Pesan",
                    JOptionPane.WARNING_MESSAGE);
            } else if(!tbl_makanan.getValueAt(row, 0).toString().equals(txt_idMakanan.getText())) {
                JOptionPane.showMessageDialog(this, "Mohon pilih tabel terlebih dahulu","Pesan",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int Confirm = JOptionPane.showOptionDialog(this, "Ingin ubah data?", "Konfirmasi",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(Confirm == JOptionPane.YES_OPTION){
                    edit_menu();
                    loadData_menu();
                    clear();
                    btn_simpan_m.setText("Simpan");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_ubah_mActionPerformed

    private void txt_idMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idMakananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idMakananActionPerformed

    private void btn_hapus_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus_mActionPerformed
        int row = tbl_makanan.getSelectedRow();
        try {
            if(row == -1){
                JOptionPane.showMessageDialog(this, "Mohon pilih tabel terlebih dahulu","Pesan",
                    JOptionPane.WARNING_MESSAGE);
            } else if(!tbl_makanan.getValueAt(row, 0).toString().equals(txt_idMakanan.getText())) {
                JOptionPane.showMessageDialog(this, "Mohon pilih tabel terlebih dahulu","Pesan",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int Confirm = JOptionPane.showOptionDialog(this, "Ingin hapus data?", "Konfirmasi",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(Confirm == JOptionPane.YES_OPTION){
                    hapus_menu();
                    loadData_menu();
                    clear();
                    btn_simpan_m.setText("Simpan");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_hapus_mActionPerformed

    private void btn_simpan_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpan_mActionPerformed
        if(btn_simpan_m.getText().equals("Batal")){
            clear();
            IDMenu();
            btn_simpan_m.setText("Simpan");
            tbl_makanan.clearSelection();
        } else {
            if(txt_idMakanan.getText().equals("")
                ||txt_namaPaket.getText().equals("")
                ||txt_keterangan.getText().equals("")
                ||txt_harga.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Mohon diisi data yang kosong",
                    "Pesan", JOptionPane.WARNING_MESSAGE);
            } else {
                int Confirm = JOptionPane.showOptionDialog(this, "Ingin simpan data?", "Konfirmasi", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(Confirm == JOptionPane.YES_OPTION){
                    tambah_menu();
                    loadData_menu();
                    clear();
                }
            }
        }
    }//GEN-LAST:event_btn_simpan_mActionPerformed

    private void tbl_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_userMouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_tbl_userMouseClicked
    
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
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cetak_user;
    private javax.swing.JButton btn_hapus_m;
    private javax.swing.JButton btn_simpan_m;
    private javax.swing.JButton btn_ubah_m;
    private javax.swing.JPanel home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenu_daftarPaket;
    private javax.swing.JMenuItem jMenu_lapPenjualan;
    private javax.swing.JMenuItem jMenu_logOut;
    private javax.swing.JMenuItem jMenu_transaksi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel karyawan;
    private javax.swing.JPanel makanan;
    private javax.swing.JTable tbl_makanan;
    private javax.swing.JTable tbl_user;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_idMakanan;
    private javax.swing.JTextArea txt_keterangan;
    private javax.swing.JTextField txt_namaPaket;
    // End of variables declaration//GEN-END:variables
}
