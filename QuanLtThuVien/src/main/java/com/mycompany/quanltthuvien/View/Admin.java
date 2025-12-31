/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanltthuvien.View;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.quanltthuvien.Controller.AdminController;
import com.mycompany.quanltthuvien.Model.LiSuGiaoDich;
import com.mycompany.quanltthuvien.Model.Sach;
import com.mycompany.quanltthuvien.Model.TaiKhoan;
import com.mycompany.quanltthuvien.Model.TheLoai;
import com.mycompany.quanltthuvien.Service.TaiKhoanService;

/**
 *
 * @author Legion
 */
public class Admin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Admin.class.getName());

    /**
     * Creates new form Admin
     */
    AdminController adminController = new AdminController();
    
    public Admin() {
        initComponents();
        setSize(1024, 768); // hoặc kích thước bạn muốn
        setLocationRelativeTo(null);
        LoadSachTable(adminController.GetAllSach());
        LoadSachTheLoai(adminController.GetAllTheLoai());
        LoadTheLoaiTable(adminController.GetAllTheLoai());
        LoadTaiKhoan(adminController.GetAllTaiKhoan());
        LoaTryVan(adminController.GetAllLichSuGiaoDich());
        LocalDate date = LocalDate.now();
        TruyVan_txtNgayMuon.setText(date.getDayOfMonth()+"");
        TruyVan_txtThangMuon.setText(date.getMonthValue()+"");
        TruyVan_txtNamMuon.setText((date.getYear()-3)+"");

        TruyVan_txtNgayTra.setText(date.getDayOfMonth()+"");
        TruyVan_txtThangTra.setText(date.getMonthValue()+"");
        TruyVan_txtNamTra.setText(date.getYear()+"");
    }

    TaiKhoan tk = new TaiKhoan("null ", "null", "null", "0912345678", "null");
    
    public void GetUsername(String usernameLogin) throws InterruptedException, IOException {
            TaiKhoanService taiKhoanService = new TaiKhoanService();
            tk = taiKhoanService.GetTaiKhoan(usernameLogin);
            info_TenTK.setText(tk.getTenTK());
            info_SDT.setText(tk.getSdtTK());
    }

    private void LoadSachTable(ArrayList<Sach> listSach) {
        DefaultTableModel model = (DefaultTableModel) Sach_tbSach.getModel();
        model.setRowCount(0);
        for(int i = 0; i < listSach.size(); i++){
            model.addRow(new Object[]{
                listSach.get(i).getMaSach(),
                listSach.get(i).getTenSach(),
                listSach.get(i).getSoLuong(),
                listSach.get(i).getTacGia(),
                adminController.GetTheLoaiByMa(listSach.get(i).getMaTheLoai()).getTenTheLoai()
            });
        }
    }

    private String GetMaTheLoai(String TheLoai){

        for (TheLoai tl : adminController.GetAllTheLoai()) {
            if(tl.getTenTheLoai().equals(TheLoai)){
                return tl.getMaTheLoai();
            }
            
        }
        return "EROR";
    }

    private void LoadSachTheLoai(ArrayList<TheLoai> listSach){
        DefaultTableModel model = (DefaultTableModel) Sach_tbTheLoai.getModel();
        model.setRowCount(0);
        for(int i = 0; i < listSach.size(); i++){
            model.addRow(new Object[]{
                listSach.get(i).getTenTheLoai()
            });
        }
    }
    private void LoadTheLoaiTable(ArrayList<TheLoai> listTheLoai) {
        DefaultTableModel model = (DefaultTableModel) TheLoai_tbTheLoai.getModel();
        model.setRowCount(0);
        for(int i = 0; i < listTheLoai.size(); i++){
            model.addRow(new Object[]{
                listTheLoai.get(i).getMaTheLoai(),
                listTheLoai.get(i).getTenTheLoai()
            });
        }
    }

    private String vaitro(String role){
        if(role.trim().equals("AD")){
            return "Admin";
        }else if(role.trim().equals("MG")) {
            return "Mangager";
        }
        return "EROR";
    }

    private void LoadTaiKhoan(ArrayList<TaiKhoan> listTaiKhoan){
        DefaultTableModel model = (DefaultTableModel) TaiKhoan_tbTaiKhoan.getModel();
        model.setRowCount(0);
        for(int i = 0; i < listTaiKhoan.size(); i++){
            model.addRow(new Object[]{
                listTaiKhoan.get(i).getUsername(),
                listTaiKhoan.get(i).getPass(),
                listTaiKhoan.get(i).getSdtTK(),
                vaitro(listTaiKhoan.get(i).getRole())
            });
        }
    }

    private void LoaTryVan(ArrayList<LiSuGiaoDich> listTV){
        DefaultTableModel model = (DefaultTableModel) TruyVan_Lsu.getModel();
        model.setRowCount(0);
        for(int i = 0; i < listTV.size(); i++){
            model.addRow(new Object[]{
                listTV.get(i).getMaGD(),
                listTV.get(i).getUsername(),
                listTV.get(i).getMaKH(),
                listTV.get(i).getNgayGD(),
                listTV.get(i).getSoLuong(),
                listTV.get(i).getTrangThai()
            });
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

        AdminCT = new javax.swing.JTabbedPane();
        Sach = new javax.swing.JPanel();
        Sach_lbTenSach = new javax.swing.JLabel();
        Sach_lbSoLuong = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Sach_txtTenSach = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Sach_txtSoLuong = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        Sach_tbSach = new javax.swing.JTable();
        Sach_lbTheLoai = new javax.swing.JLabel();
        Sach_butThem = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Sach_txtTheLoai = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        Sach_tfTimKiem = new javax.swing.JTextPane();
        Sach_butXoa = new javax.swing.JButton();
        Sach_butTimKiem = new javax.swing.JButton();
        Sach_butSua = new javax.swing.JButton();
        Sach_butHienThi = new javax.swing.JButton();
        Sach_cbTimTheo = new javax.swing.JComboBox<>();
        jScrollPane17 = new javax.swing.JScrollPane();
        Sach_txtTacGia = new javax.swing.JTextPane();
        Sach_lbTenSach1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Sach_tbTheLoai = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        Sach_TxtTenTheloai = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Sach_butLamMoi = new javax.swing.JButton();
        TheLoai = new javax.swing.JPanel();
        TheLoai_butThem = new javax.swing.JButton();
        TheLoai_butSua = new javax.swing.JButton();
        TheLoai_butXoa = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TheLoai_txtTheLoai = new javax.swing.JTextPane();
        TheLoai_lbTheLoai = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TheLoai_tfTimKiem = new javax.swing.JTextPane();
        TheLoai_butTimKiem = new javax.swing.JButton();
        TheLoai_cbTimTheo = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        TheLoai_tbTheLoai = new javax.swing.JTable();
        TaiKhoan = new javax.swing.JPanel();
        TaiKhoan_lbTenTaiKhoan = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TaiKhoan_txtTenTaiKhoan = new javax.swing.JTextPane();
        TaiKhoan__butThem = new javax.swing.JButton();
        TaiKhoan__butSua = new javax.swing.JButton();
        TaiKhoan__butXoa = new javax.swing.JButton();
        TaiKhoan_lbMatKhau = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TaiKhoan_txtMatKhau = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        TaiKhoan_txtUsername = new javax.swing.JTextPane();
        Sach_butTimKiem1 = new javax.swing.JButton();
        Sach_butHienThi1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TaiKhoan_tbTaiKhoan = new javax.swing.JTable();
        TaiKhoan_lbTimTheoTenTK = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        TaiKhoan_txtTenTK = new javax.swing.JTextPane();
        TaiKhoan_lbMatKhau1 = new javax.swing.JLabel();
        TaiKhoan_lbMatKhau2 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        TaiKhoan_txtSDT = new javax.swing.JTextPane();
        LichSuGiaoDich = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TruyVan_Lsu = new javax.swing.JTable();
        TruyVan_bt = new javax.swing.JButton();
        TruyVan_txtNamMuon = new javax.swing.JTextField();
        TruyVan_txtNgayMuon = new javax.swing.JTextField();
        TruyVan_txtThangMuon = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TruyVan_txtNgayTra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TruyVan_txtThangTra = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TruyVan_txtNamTra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TruyVan_btLayThongTin = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        info_TenTK = new javax.swing.JTextField();
        info_SDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        Sach.setDoubleBuffered(false);
        Sach.setLayout(null);

        Sach_lbTenSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbTenSach.setText("Tên Sách:");
        Sach.add(Sach_lbTenSach);
        Sach_lbTenSach.setBounds(30, 22, 75, 22);

        Sach_lbSoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbSoLuong.setText("Số lượng:");
        Sach.add(Sach_lbSoLuong);
        Sach_lbSoLuong.setBounds(30, 62, 75, 22);

        jScrollPane1.setViewportView(Sach_txtTenSach);

        Sach.add(jScrollPane1);
        jScrollPane1.setBounds(111, 22, 160, 22);

        jScrollPane2.setViewportView(Sach_txtSoLuong);

        Sach.add(jScrollPane2);
        jScrollPane2.setBounds(111, 62, 160, 22);

        Sach_tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Số lượng", "Tác giả", "Thể loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        Sach_tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SachSelect(evt);
            }
        });
        jScrollPane3.setViewportView(Sach_tbSach);

        Sach.add(jScrollPane3);
        jScrollPane3.setBounds(30, 228, 753, 440);

        Sach_lbTheLoai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbTheLoai.setText("Thể loại:");
        Sach.add(Sach_lbTheLoai);
        Sach_lbTheLoai.setBounds(30, 102, 75, 22);

        Sach_butThem.setLabel("Thêm");
        Sach_butThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butThemActionPerformed(evt);
            }
        });
        Sach.add(Sach_butThem);
        Sach_butThem.setBounds(335, 21, 72, 23);

        Sach_txtTheLoai.setEditable(false);
        jScrollPane5.setViewportView(Sach_txtTheLoai);

        Sach.add(jScrollPane5);
        jScrollPane5.setBounds(111, 102, 160, 22);

        jScrollPane6.setViewportView(Sach_tfTimKiem);

        Sach.add(jScrollPane6);
        jScrollPane6.setBounds(166, 179, 285, 22);

        Sach_butXoa.setText("Xóa");
        Sach_butXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butXoaActionPerformed(evt);
            }
        });
        Sach.add(Sach_butXoa);
        Sach_butXoa.setBounds(515, 21, 72, 23);

        Sach_butTimKiem.setText("Tìm kiếm");
        Sach_butTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butTimKiemActionPerformed(evt);
            }
        });
        Sach.add(Sach_butTimKiem);
        Sach_butTimKiem.setBounds(469, 179, 79, 23);

        Sach_butSua.setText("Sửa");
        Sach_butSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butSuaActionPerformed(evt);
            }
        });
        Sach.add(Sach_butSua);
        Sach_butSua.setBounds(425, 21, 72, 23);

        Sach_butHienThi.setText("Hiển thị");
        Sach.add(Sach_butHienThi);
        Sach_butHienThi.setBounds(1247, 179, 72, 23);

        Sach_cbTimTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo tên", "Tìm theo mã", "Tìm theo thể loại" }));
        Sach.add(Sach_cbTimTheo);
        Sach_cbTimTheo.setBounds(30, 179, 124, 22);

        jScrollPane17.setViewportView(Sach_txtTacGia);

        Sach.add(jScrollPane17);
        jScrollPane17.setBounds(111, 136, 160, 22);

        Sach_lbTenSach1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbTenSach1.setText("Tác giả:");
        Sach.add(Sach_lbTenSach1);
        Sach_lbTenSach1.setBounds(30, 136, 75, 22);

        Sach_tbTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tên thể loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(Sach_tbTheLoai);

        Sach.add(jScrollPane4);
        jScrollPane4.setBounds(820, 180, 170, 402);

        jLabel8.setText("Danh sách thể loại");
        Sach.add(jLabel8);
        jLabel8.setBounds(830, 160, 140, 16);

        jButton2.setText("Lấy thể loại");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Sach.add(jButton2);
        jButton2.setBounds(850, 130, 110, 23);

        Sach_TxtTenTheloai.setText("Nhập tên thể loại");
        Sach_TxtTenTheloai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sach_TxtTenTheloaiMouseClicked(evt);
            }
        });
        Sach.add(Sach_TxtTenTheloai);
        Sach_TxtTenTheloai.setBounds(810, 22, 190, 30);

        jButton1.setText("Tìm kếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Sach.add(jButton1);
        jButton1.setBounds(850, 60, 110, 30);

        Sach_butLamMoi.setText("làm mới");
        Sach_butLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butLamMoiActionPerformed(evt);
            }
        });
        Sach.add(Sach_butLamMoi);
        Sach_butLamMoi.setBounds(340, 130, 75, 23);

        AdminCT.addTab("Sách", Sach);
        Sach.getAccessibleContext().setAccessibleName("");

        TheLoai.setDoubleBuffered(false);

        TheLoai_butThem.setLabel("Thêm");
        TheLoai_butThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TheLoai_butThemActionPerformed(evt);
            }
        });

        TheLoai_butSua.setText("Sửa");
        TheLoai_butSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TheLoai_butSuaActionPerformed(evt);
            }
        });

        TheLoai_butXoa.setText("Xóa");
        TheLoai_butXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TheLoai_butXoaActionPerformed(evt);
            }
        });

        jScrollPane9.setViewportView(TheLoai_txtTheLoai);

        TheLoai_lbTheLoai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TheLoai_lbTheLoai.setText("Thể loại:");

        jScrollPane10.setViewportView(TheLoai_tfTimKiem);

        TheLoai_butTimKiem.setText("Tìm kiếm");
        TheLoai_butTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TheLoai_butTimKiemActionPerformed(evt);
            }
        });

        TheLoai_cbTimTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm theo mã", "Tìm theo tên" }));

        TheLoai_tbTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã thể loại", "Tên thể loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TheLoai_tbTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TheLoaiSelect(evt);
            }
        });
        jScrollPane8.setViewportView(TheLoai_tbTheLoai);

        javax.swing.GroupLayout TheLoaiLayout = new javax.swing.GroupLayout(TheLoai);
        TheLoai.setLayout(TheLoaiLayout);
        TheLoaiLayout.setHorizontalGroup(
            TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TheLoaiLayout.createSequentialGroup()
                .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TheLoaiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TheLoaiLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(TheLoaiLayout.createSequentialGroup()
                                .addComponent(TheLoai_lbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TheLoai_butThem))
                            .addGroup(TheLoaiLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(TheLoai_cbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10)))
                        .addGap(18, 18, 18)
                        .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TheLoai_butSua)
                            .addComponent(TheLoai_butTimKiem))
                        .addGap(18, 18, 18)
                        .addComponent(TheLoai_butXoa)))
                .addContainerGap(411, Short.MAX_VALUE))
        );
        TheLoaiLayout.setVerticalGroup(
            TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TheLoaiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TheLoai_butThem)
                        .addComponent(TheLoai_butXoa)
                        .addComponent(TheLoai_butSua))
                    .addComponent(TheLoai_lbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TheLoai_butTimKiem)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TheLoai_cbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        AdminCT.addTab("Thể loại", TheLoai);

        TaiKhoan.setDoubleBuffered(false);

        TaiKhoan_lbTenTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoan_lbTenTaiKhoan.setText("Tên tài khoản:");

        jScrollPane11.setViewportView(TaiKhoan_txtTenTaiKhoan);

        TaiKhoan__butThem.setLabel("Thêm");
        TaiKhoan__butThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaiKhoan__butThemActionPerformed(evt);
            }
        });

        TaiKhoan__butSua.setText("Sửa");
        TaiKhoan__butSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaiKhoan__butSuaActionPerformed(evt);
            }
        });

        TaiKhoan__butXoa.setText("Xóa");
        TaiKhoan__butXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaiKhoan__butXoaActionPerformed(evt);
            }
        });

        TaiKhoan_lbMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoan_lbMatKhau.setText("Mật khẩu:");

        jScrollPane12.setViewportView(TaiKhoan_txtMatKhau);

        jScrollPane13.setViewportView(TaiKhoan_txtUsername);

        Sach_butTimKiem1.setText("Tìm kiếm");
        Sach_butTimKiem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butTimKiem1ActionPerformed(evt);
            }
        });

        Sach_butHienThi1.setText("Hiển thị");
        Sach_butHienThi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butHienThi1ActionPerformed(evt);
            }
        });

        TaiKhoan_tbTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Mật khẩu", "Số điện thoại", "Vai trò"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TaiKhoan_tbTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoan_tbTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TaiKhoan_tbTaiKhoan);

        TaiKhoan_lbTimTheoTenTK.setText("Tìm theo tên tài khoản");

        jScrollPane15.setViewportView(TaiKhoan_txtTenTK);

        TaiKhoan_lbMatKhau1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoan_lbMatKhau1.setText("Tên:");

        TaiKhoan_lbMatKhau2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaiKhoan_lbMatKhau2.setText("Số điện thoại");

        jScrollPane16.setViewportView(TaiKhoan_txtSDT);

        javax.swing.GroupLayout TaiKhoanLayout = new javax.swing.GroupLayout(TaiKhoan);
        TaiKhoan.setLayout(TaiKhoanLayout);
        TaiKhoanLayout.setHorizontalGroup(
            TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaiKhoanLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TaiKhoanLayout.createSequentialGroup()
                        .addComponent(TaiKhoan_lbMatKhau2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(TaiKhoanLayout.createSequentialGroup()
                        .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TaiKhoanLayout.createSequentialGroup()
                                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TaiKhoanLayout.createSequentialGroup()
                                        .addComponent(TaiKhoan_lbTimTheoTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane13))
                                    .addGroup(TaiKhoanLayout.createSequentialGroup()
                                        .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(TaiKhoan_lbTenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                            .addComponent(TaiKhoan_lbMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(TaiKhoan_lbMatKhau1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                            .addComponent(jScrollPane12)
                                            .addComponent(jScrollPane15)
                                            .addComponent(jScrollPane16))))
                                .addGap(18, 18, 18)
                                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(TaiKhoanLayout.createSequentialGroup()
                                        .addComponent(TaiKhoan__butThem)
                                        .addGap(18, 18, 18)
                                        .addComponent(TaiKhoan__butSua)
                                        .addGap(18, 18, 18)
                                        .addComponent(TaiKhoan__butXoa))
                                    .addComponent(Sach_butTimKiem1))
                                .addGap(133, 133, 133)
                                .addComponent(Sach_butHienThi1))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69))))
        );
        TaiKhoanLayout.setVerticalGroup(
            TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TaiKhoanLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane11)
                        .addComponent(TaiKhoan_lbTenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TaiKhoan__butThem)
                        .addComponent(TaiKhoan__butXoa)
                        .addComponent(TaiKhoan__butSua)))
                .addGap(18, 18, 18)
                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12)
                    .addComponent(TaiKhoan_lbMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TaiKhoan_lbMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TaiKhoanLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(TaiKhoan_lbMatKhau2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TaiKhoanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Sach_butHienThi1)
                .addGap(1, 1, 1)
                .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sach_butTimKiem1)
                    .addGroup(TaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TaiKhoan_lbTimTheoTenTK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        AdminCT.addTab("Quản lý tài khoản", TaiKhoan);

        LichSuGiaoDich.setDoubleBuffered(false);

        TruyVan_Lsu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã giao dịch", "Tên tài khoản", "Mã khách hàng", "Ngày giao dịch", "Số lượng", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(TruyVan_Lsu);

        TruyVan_bt.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        TruyVan_bt.setText("Truy vấn");
        TruyVan_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_btActionPerformed(evt);
            }
        });

        TruyVan_txtNamMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_txtNamMuonActionPerformed(evt);
            }
        });

        TruyVan_txtNgayMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_txtNgayMuonActionPerformed(evt);
            }
        });

        TruyVan_txtThangMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_txtThangMuonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("/");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("/");

        TruyVan_txtNgayTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_txtNgayTraActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setText("/");

        TruyVan_txtThangTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_txtThangTraActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setText("/");

        TruyVan_txtNamTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TruyVan_txtNamTraActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setText("đến");

        TruyVan_btLayThongTin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TruyVan_btLayThongTin.setText("Lấy thông tin");

        javax.swing.GroupLayout LichSuGiaoDichLayout = new javax.swing.GroupLayout(LichSuGiaoDich);
        LichSuGiaoDich.setLayout(LichSuGiaoDichLayout);
        LichSuGiaoDichLayout.setHorizontalGroup(
            LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(TruyVan_txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                .addComponent(TruyVan_txtThangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1)
                                .addGap(6, 6, 6)
                                .addComponent(TruyVan_txtNamMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TruyVan_btLayThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(TruyVan_txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(6, 6, 6)
                        .addComponent(TruyVan_txtThangTra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(TruyVan_txtNamTra, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(TruyVan_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 48, Short.MAX_VALUE))
        );
        LichSuGiaoDichLayout.setVerticalGroup(
            LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TruyVan_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(TruyVan_btLayThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(TruyVan_txtThangTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(TruyVan_txtNamTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TruyVan_txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TruyVan_txtThangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TruyVan_txtNamMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TruyVan_txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(22, 22, 22)))
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 52, Short.MAX_VALUE))
        );

        AdminCT.addTab("Truy Vấn", LichSuGiaoDich);
        LichSuGiaoDich.getAccessibleContext().setAccessibleName("");

        jPanel1.setLayout(null);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton3.setText("Đăng Xuất");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(740, 440, 142, 39);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel9.setText("Tên tài khoản:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(70, 160, 222, 48);

        info_TenTK.setEditable(false);
        info_TenTK.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        info_TenTK.setText("jTextField1");
        jPanel1.add(info_TenTK);
        info_TenTK.setBounds(310, 160, 600, 54);

        info_SDT.setEditable(false);
        info_SDT.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        info_SDT.setText("jTextField1");
        jPanel1.add(info_SDT);
        info_SDT.setBounds(310, 270, 600, 54);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel10.setText("Số điện thoại:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(70, 270, 217, 48);

        AdminCT.addTab("Thông tin tài khoản", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminCT)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminCT)
        );

        AdminCT.getAccessibleContext().setAccessibleName("Admin");
        AdminCT.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Sach_butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butThemActionPerformed
        // TODO add your handling code here:
        if(SachIsEmty()){
            return;
        }
        String tenSach = Sach_txtTenSach.getText();
        int soLuong = Integer.parseInt(Sach_txtSoLuong.getText());
        String theLoai = GetMaTheLoai(Sach_txtTheLoai.getText());
        
        String tacGia = Sach_txtTacGia.getText();
        ArrayList<Sach> listSach = adminController.GetAllSach();
         for(Sach sach : listSach){
            if(sach.getTenSach().equals(tenSach)&& sach.getTacGia().equals(tacGia)&& sach.getMaTheLoai().equals(theLoai)){
                int confirm = JOptionPane.showConfirmDialog(this, "Đầu sách đã tồn tại bạn có muốn thêm số lượng?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    adminController.AddMoreSach(sach.getMaSach(), soLuong);
                }else{
                    adminController.AddSach(new Sach(" ",tenSach,soLuong, tacGia,theLoai));
                }
                break;
            }
        }
        LoadSachTable(adminController.GetAllSach());
    }//GEN-LAST:event_Sach_butThemActionPerformed

    private void Sach_butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butXoaActionPerformed
        // TODO add your handling code here:
        if(SachIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần xóa");
            return;
        }
        System.out.println(MaSachVoid);
        adminController.DeleteSach(MaSachVoid);
        LoadSachTable(adminController.GetAllSach());
    }//GEN-LAST:event_Sach_butXoaActionPerformed

    private boolean SachIsEmty(){
        if(Sach_txtTenSach.getText().equals("") || Sach_txtSoLuong.getText().equals("") || Sach_txtTheLoai.getText().equals("") || Sach_txtTacGia.getText().equals("")){
            return true;
        }
        return false;
    }
    private void Sach_butTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butTimKiemActionPerformed
        // TODO add your handling code here:
        String keyword = Sach_tfTimKiem.getText();
        if(keyword.equals("")){
            LoadSachTable(adminController.GetAllSach());
            return;
        }
        //Combo box
        String searchBy = Sach_cbTimTheo.getSelectedItem().toString();
        ArrayList<Sach> result = new ArrayList<>();
        if(searchBy.equals("Tìm theo tên")){
            result = adminController.GetSachByTenSach(keyword);
        }else if(searchBy.equals("Tìm theo mã")){
            MaSachVoid = keyword;
            Sach sach = adminController.GetSachByMa(keyword);
            if(sach != null){
                result.add(sach);
            }
        }else if(searchBy.equals("Tìm theo thể loại")){
            result = adminController.GetSachByTheLoai(keyword);
        }
        LoadSachTable(result);
    }//GEN-LAST:event_Sach_butTimKiemActionPerformed

    private boolean TheLoaiIsEmty(){
        if(TheLoai_txtTheLoai.getText().equals("")){
            return true;
        }
        return false;
    }
    String MaTheLoaiVoid = "";
    private void TheLoai_butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butThemActionPerformed
        // TODO add your handling code here:
        if(TheLoaiIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thể loại");
            return;
        }

        ArrayList<TheLoai> listTheLoai = adminController.GetAllTheLoai();;
        for(TheLoai tl : listTheLoai){
            if(tl.getTenTheLoai().trim().toLowerCase().equals(TheLoai_txtTheLoai.getText().trim().toLowerCase())){
                JOptionPane.showMessageDialog(this, "Thể loại đã tồn tại");
                return;
            }
        }
        adminController.AddTheLoai(new TheLoai(" ", TheLoai_txtTheLoai.getText()));
        LoadTheLoaiTable(adminController.GetAllTheLoai());

    }//GEN-LAST:event_TheLoai_butThemActionPerformed

    private void TheLoai_butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butSuaActionPerformed
        // TODO add your handling code here:
        if(TheLoaiIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thể loại");
            return;
        }
        for (TheLoai elem : adminController.GetAllTheLoai()) {
            if (elem.getTenTheLoai().trim().toLowerCase().equals(TheLoai_txtTheLoai.getText().trim().toLowerCase())) {
                JOptionPane.showMessageDialog(this, "Thể loại đã tồn tại");
                return;
            }
            
        }
        adminController.UpdateTheLoai(new TheLoai(MaTheLoaiVoid, TheLoai_txtTheLoai.getText()));
        LoadTheLoaiTable(adminController.GetAllTheLoai());
    }//GEN-LAST:event_TheLoai_butSuaActionPerformed

    private void TheLoai_butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butXoaActionPerformed
        // TODO add your handling code here:
        if(TheLoaiIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại cần xóa");
            return;
        }
        adminController.DeleteTheLoai(MaTheLoaiVoid);
        LoadTheLoaiTable(adminController.GetAllTheLoai());
    }//GEN-LAST:event_TheLoai_butXoaActionPerformed

    private void TheLoai_butTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butTimKiemActionPerformed
        // TODO add your handling code here:
        String keyword = TheLoai_tfTimKiem.getText();
        if(keyword.equals("")){
            LoadTheLoaiTable(adminController.GetAllTheLoai());
            return;
        }
        String searchBy = TheLoai_cbTimTheo.getSelectedItem().toString();
        ArrayList<TheLoai> result = new ArrayList<>();
        if(searchBy.equals("Tìm theo tên")){
            for(TheLoai tl : adminController.GetAllTheLoai()){
                if(tl.getTenTheLoai().trim().toLowerCase().contains(keyword.trim().toLowerCase())){
                    result.add(tl);
                }
            }
        }else if(searchBy.equals("Tìm theo mã")){
            MaTheLoaiVoid = keyword;
            TheLoai tl = adminController.GetTheLoaiByMa(keyword);
            if(tl != null){
                result.add(tl);
            }
        }
        LoadTheLoaiTable(result);
    }//GEN-LAST:event_TheLoai_butTimKiemActionPerformed

    public boolean TaiKhoanIsEmty(){
        if(TaiKhoan_txtTenTaiKhoan.getText().equals("") || 
        TaiKhoan_txtMatKhau.getText().equals("") || 
        TaiKhoan_txtTenTK.getText().equals("") || 
        TaiKhoan_txtSDT.getText().equals("")){
            return true;
        }
        return false;
    }
    public void TaiKhoanClear(){
        TaiKhoan_txtTenTaiKhoan.setText("");
        TaiKhoan_txtMatKhau.setText("");
        TaiKhoan_txtTenTK.setText("");
        TaiKhoan_txtSDT.setText("");
    }
    private void TaiKhoan__butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaiKhoan__butThemActionPerformed
        // TODO add your handling code here:
        if(TaiKhoanIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin");
            return;
        }
        String usnername = TaiKhoan_txtTenTaiKhoan.getText();
        String pass = TaiKhoan_txtMatKhau.getText();
        String tenTK = TaiKhoan_txtTenTK.getText();
        String sdtTK = TaiKhoan_txtSDT.getText();
        if(!sdtTK.matches("\\d+")){
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số");
            return;
        }
        //Số điện thoại đủ 10 số
        if(sdtTK.length() != 10){
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 số");
            return;
        }
        
        if(!adminController.addTaiKhoan(usnername, pass , tenTK, sdtTK)){
            JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại");
            TaiKhoanClear();
            return;
        }
        JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công");
        LoadTaiKhoan(adminController.GetAllTaiKhoan());
        TaiKhoanClear();
        
    }//GEN-LAST:event_TaiKhoan__butThemActionPerformed

    private void TaiKhoan__butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaiKhoan__butSuaActionPerformed
        // TODO add your handling code here:
        if(TaiKhoanIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin");
            return;
        }
        String usnername = TaiKhoan_txtTenTaiKhoan.getText();
        String pass = TaiKhoan_txtMatKhau.getText();
        String tenTK = TaiKhoan_txtTenTK.getText();
        String sdtTK = TaiKhoan_txtSDT.getText();
        if(!sdtTK.matches("\\d+")){
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là số");
            return;
        }
        //Số điện thoại đủ 10 số
        if(sdtTK.length() != 10){
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 số");
            return;
        }
        if(!adminController.UpdateTaiKhoan(usnername, pass , tenTK, sdtTK)){
            JOptionPane.showMessageDialog(this, "Sửa tài khoản thất bại");
            return;
        }
        JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công");
        LoadTaiKhoan(adminController.GetAllTaiKhoan());
        TaiKhoanClear();
    }//GEN-LAST:event_TaiKhoan__butSuaActionPerformed

    private void TaiKhoan__butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaiKhoan__butXoaActionPerformed
        // TODO add your handling code here:
        if(TaiKhoanIsEmty()){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin");
            return;
        }
        String usnername = TaiKhoan_txtTenTaiKhoan.getText();
        if(!adminController.DeleteTaiKhoan(usnername)){
            JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại");
            TaiKhoanClear();
            return;
        }
        JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công");
        LoadTaiKhoan(adminController.GetAllTaiKhoan());
        TaiKhoanClear();
    }//GEN-LAST:event_TaiKhoan__butXoaActionPerformed

    private void Sach_butTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butTimKiem1ActionPerformed
        // TODO add your handling code here:
        String usnername = TaiKhoan_txtUsername.getText();
        if(usnername.equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập");
            return;
        }

        TaiKhoan tk = adminController.GetTaiKhoan(usnername);
        if(tk == null){
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không tồn tại");
            return;
        }
        TaiKhoan_txtTenTaiKhoan.setText(tk.getUsername());
        TaiKhoan_txtMatKhau.setText(tk.getPass());
        TaiKhoan_txtTenTK.setText(tk.getTenTK());
        TaiKhoan_txtSDT.setText(tk.getSdtTK());
    }//GEN-LAST:event_Sach_butTimKiem1ActionPerformed

    private void Sach_butHienThi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butHienThi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sach_butHienThi1ActionPerformed

    private void TruyVan_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_btActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_btActionPerformed

    private void TruyVan_txtNamMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_txtNamMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_txtNamMuonActionPerformed

    private void TruyVan_txtNgayMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_txtNgayMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_txtNgayMuonActionPerformed

    private void TruyVan_txtThangMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_txtThangMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_txtThangMuonActionPerformed

    private void TruyVan_txtNgayTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_txtNgayTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_txtNgayTraActionPerformed

    private void TruyVan_txtThangTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_txtThangTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_txtThangTraActionPerformed

    private void TruyVan_txtNamTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TruyVan_txtNamTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TruyVan_txtNamTraActionPerformed

    private void Logout(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout
        // TODO add your handling code here:
        Login lg = new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Logout

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String TenTL= Sach_tbTheLoai.getValueAt(Sach_tbTheLoai.getSelectedRow(), 0).toString();
        Sach_txtTheLoai.setText(TenTL);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Sach_TxtTenTheloaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sach_TxtTenTheloaiMouseClicked
        // TODO add your handling code here:
        Sach_txtTheLoai.setText("");
    }//GEN-LAST:event_Sach_TxtTenTheloaiMouseClicked

    private void Sach_butLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butLamMoiActionPerformed
        // TODO add your handling code here:
        Sach_txtTenSach.setText("");
        Sach_txtSoLuong.setText("");
        Sach_txtTheLoai.setText("");
        Sach_txtTacGia.setText("");

    }//GEN-LAST:event_Sach_butLamMoiActionPerformed

    private String MaSachVoid = "";
    private void SachSelect(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SachSelect
        // TODO add your handling code here:
        int selectedRow = Sach_tbSach.getSelectedRow();
        if (selectedRow >= 0) {
            MaSachVoid = Sach_tbSach.getValueAt(selectedRow, 0).toString();
            Sach_txtTenSach.setText(Sach_tbSach.getValueAt(selectedRow, 1).toString());
            Sach_txtSoLuong.setText(Sach_tbSach.getValueAt(selectedRow,2).toString());
            Sach_txtTheLoai.setText(Sach_tbSach.getValueAt(selectedRow, 4).toString());
            Sach_txtTacGia.setText(Sach_tbSach.getValueAt(selectedRow, 3).toString());
        }
    }//GEN-LAST:event_SachSelect

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String theloai = Sach_TxtTenTheloai.getText().trim().toLowerCase();
        ArrayList<TheLoai> listSachTheLoai = new ArrayList<>() ;
        for (TheLoai elem : adminController.GetAllTheLoai()) {
            if (elem.getTenTheLoai().trim().toLowerCase().contains(theloai)) {
                listSachTheLoai.add(elem);
            }
        }
        LoadSachTheLoai(listSachTheLoai);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void TheLoaiSelect(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TheLoaiSelect
        // TODO add your handling code here:
        int selectedRow = TheLoai_tbTheLoai.getSelectedRow();
        if (selectedRow >= 0) {
            MaTheLoaiVoid = TheLoai_tbTheLoai.getValueAt(selectedRow, 0).toString();
            TheLoai_txtTheLoai.setText(TheLoai_tbTheLoai.getValueAt(selectedRow, 1).toString());
        }
    }//GEN-LAST:event_TheLoaiSelect

    private void Sach_butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butSuaActionPerformed
        // TODO add your handling code here:
        if(SachIsEmty()){
            JOptionPane.showMessageDialog(this, "Thông tin bị trống");
            return;
        }
        String TheLoai = "";
        for(TheLoai elem : adminController.GetAllTheLoai()){
            if(elem.getTenTheLoai().trim().equals(Sach_txtTheLoai.getText().trim())){
                TheLoai = elem.getMaTheLoai();
            }
        }
        if(TheLoai.isEmpty()){
            JOptionPane.showMessageDialog(this, "Thể loại không tồn tại");
            return;
        }
        Sach sach = new Sach(
            MaSachVoid,
            Sach_txtTenSach.getText(),
            Integer.parseInt(Sach_txtSoLuong.getText()),
            Sach_txtTacGia.getText(),
            TheLoai
        );

        if(adminController.UpdateSach(sach)){
            JOptionPane.showMessageDialog(this, "Sửa sách thành công");
            LoadSachTable(adminController.GetAllSach());
            Sach_txtTenSach.setText("");
            Sach_txtSoLuong.setText("");
            Sach_txtTheLoai.setText("");
            Sach_txtTacGia.setText("");
        }else{
            JOptionPane.showMessageDialog(this, "Sửa sách thất bại");
        }
    }//GEN-LAST:event_Sach_butSuaActionPerformed

    private void TaiKhoan_tbTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoan_tbTaiKhoanMouseClicked
        // TODO add your handling code here:
        int selectedRow = TaiKhoan_tbTaiKhoan.getSelectedRow();
        if (selectedRow >= 0) {
            TaiKhoan_txtTenTaiKhoan.setText(TaiKhoan_tbTaiKhoan.getValueAt(selectedRow, 0).toString());
            TaiKhoan_txtMatKhau.setText(TaiKhoan_tbTaiKhoan.getValueAt(selectedRow, 1).toString());
            TaiKhoan_txtTenTK.setText(adminController.GetTaiKhoan(TaiKhoan_txtTenTaiKhoan.getText()).getTenTK());
            TaiKhoan_txtSDT.setText(TaiKhoan_tbTaiKhoan.getValueAt(selectedRow, 2).toString());
        }
    }//GEN-LAST:event_TaiKhoan_tbTaiKhoanMouseClicked

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Admin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane AdminCT;
    private javax.swing.JPanel LichSuGiaoDich;
    private javax.swing.JPanel Sach;
    private javax.swing.JTextField Sach_TxtTenTheloai;
    private javax.swing.JButton Sach_butHienThi;
    private javax.swing.JButton Sach_butHienThi1;
    private javax.swing.JButton Sach_butLamMoi;
    private javax.swing.JButton Sach_butSua;
    private javax.swing.JButton Sach_butThem;
    private javax.swing.JButton Sach_butTimKiem;
    private javax.swing.JButton Sach_butTimKiem1;
    private javax.swing.JButton Sach_butXoa;
    private javax.swing.JComboBox<String> Sach_cbTimTheo;
    private javax.swing.JLabel Sach_lbSoLuong;
    private javax.swing.JLabel Sach_lbTenSach;
    private javax.swing.JLabel Sach_lbTenSach1;
    private javax.swing.JLabel Sach_lbTheLoai;
    private javax.swing.JTable Sach_tbSach;
    private javax.swing.JTable Sach_tbTheLoai;
    private javax.swing.JTextPane Sach_tfTimKiem;
    private javax.swing.JTextPane Sach_txtSoLuong;
    private javax.swing.JTextPane Sach_txtTacGia;
    private javax.swing.JTextPane Sach_txtTenSach;
    private javax.swing.JTextPane Sach_txtTheLoai;
    private javax.swing.JPanel TaiKhoan;
    private javax.swing.JButton TaiKhoan__butSua;
    private javax.swing.JButton TaiKhoan__butThem;
    private javax.swing.JButton TaiKhoan__butXoa;
    private javax.swing.JLabel TaiKhoan_lbMatKhau;
    private javax.swing.JLabel TaiKhoan_lbMatKhau1;
    private javax.swing.JLabel TaiKhoan_lbMatKhau2;
    private javax.swing.JLabel TaiKhoan_lbTenTaiKhoan;
    private javax.swing.JLabel TaiKhoan_lbTimTheoTenTK;
    private javax.swing.JTable TaiKhoan_tbTaiKhoan;
    private javax.swing.JTextPane TaiKhoan_txtMatKhau;
    private javax.swing.JTextPane TaiKhoan_txtSDT;
    private javax.swing.JTextPane TaiKhoan_txtTenTK;
    private javax.swing.JTextPane TaiKhoan_txtTenTaiKhoan;
    private javax.swing.JTextPane TaiKhoan_txtUsername;
    private javax.swing.JPanel TheLoai;
    private javax.swing.JButton TheLoai_butSua;
    private javax.swing.JButton TheLoai_butThem;
    private javax.swing.JButton TheLoai_butTimKiem;
    private javax.swing.JButton TheLoai_butXoa;
    private javax.swing.JComboBox<String> TheLoai_cbTimTheo;
    private javax.swing.JLabel TheLoai_lbTheLoai;
    private javax.swing.JTable TheLoai_tbTheLoai;
    private javax.swing.JTextPane TheLoai_tfTimKiem;
    private javax.swing.JTextPane TheLoai_txtTheLoai;
    private javax.swing.JTable TruyVan_Lsu;
    private javax.swing.JButton TruyVan_bt;
    private javax.swing.JButton TruyVan_btLayThongTin;
    private javax.swing.JTextField TruyVan_txtNamMuon;
    private javax.swing.JTextField TruyVan_txtNamTra;
    private javax.swing.JTextField TruyVan_txtNgayMuon;
    private javax.swing.JTextField TruyVan_txtNgayTra;
    private javax.swing.JTextField TruyVan_txtThangMuon;
    private javax.swing.JTextField TruyVan_txtThangTra;
    private javax.swing.JTextField info_SDT;
    private javax.swing.JTextField info_TenTK;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
