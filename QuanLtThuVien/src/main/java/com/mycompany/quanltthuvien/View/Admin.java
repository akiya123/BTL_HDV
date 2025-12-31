/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanltthuvien.View;

import com.mycompany.quanltthuvien.Model.*;
import com.mycompany.quanltthuvien.Service.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Legion
 */
public class Admin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Admin.class.getName());
    

    private SachService sachService;
    private TheLoaiService theLoaiService;
    private TaiKhoanService taiKhoanService;
    private LiSuGDService liSuGDService;

    public Admin() {
        initComponents();
        setSize(1024, 768);
        setLocationRelativeTo(null);
        

        sachService = new SachService();
        theLoaiService = new TheLoaiService();
        taiKhoanService = new TaiKhoanService();
        liSuGDService = new LiSuGDService();
        

        loadAllData();
        

        addTableListeners();
    }
    
    private void loadAllData() {
        loadSachData();
        loadTheLoaiData();
        loadTaiKhoanData();
    }
 private void loadSachData() {
        try {
            List<Sach> dsSach = sachService.GetAllSach();
            DefaultTableModel model = (DefaultTableModel) Sach_tbSach.getModel();
            model.setRowCount(0);
            
            for (Sach sach : dsSach) {
                model.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getSoLuong(),
                    sach.getTacGia(),
                    sach.getMaTheLoai()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sách: " + e.getMessage());
        }
    }
    
    private void Sach_butThemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String tenSach = Sach_txtTenSach.getText().trim();
            String soLuongStr = Sach_txtMaSach.getText().trim();
            String theLoai = jTextPane1.getText().trim();
            String tacGia = Sach_txtTacGia.getText().trim();
            
            if (tenSach.isEmpty() || soLuongStr.isEmpty() || theLoai.isEmpty() || tacGia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }
            
            int soLuong;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số dương!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
                return;
            }
            
            Sach sach = new Sach();
            sach.setTenSach(tenSach);
            sach.setSoLuong(soLuong);
            sach.setMaTheLoai(theLoai);
            sach.setTacGia(tacGia);
            
            if (sachService.themSach(sach)) {
                JOptionPane.showMessageDialog(this, "Thêm sách thành công!");
                loadSachData();
                clearSachForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm sách thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void Sach_butSuaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = Sach_tbSach.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần sửa!");
                return;
            }
            
            String maSach = Sach_tbSach.getValueAt(selectedRow, 0).toString();
            String tenSach = Sach_txtTenSach.getText().trim();
            String soLuongStr = Sach_txtMaSach.getText().trim();
            String theLoai = jTextPane1.getText().trim();
            String tacGia = Sach_txtTacGia.getText().trim();
            
            if (tenSach.isEmpty() || soLuongStr.isEmpty() || theLoai.isEmpty() || tacGia.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }
            
            int soLuong;
            try {
                soLuong = Integer.parseInt(soLuongStr);
                if (soLuong < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số dương!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên!");
                return;
            }
            
            Sach sach = new Sach(maSach, tenSach, soLuong, tacGia, theLoai);
            
            if (sachService.suaSach(sach)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sách thành công!");
                loadSachData();
                clearSachForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sách thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void Sach_butXoaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = Sach_tbSach.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sách cần xóa!");
                return;
            }
            
            String maSach = Sach_tbSach.getValueAt(selectedRow, 0).toString();
            String tenSach = Sach_tbSach.getValueAt(selectedRow, 1).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa sách '" + tenSach + "'?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (sachService.xoaSach(maSach)) {
                    JOptionPane.showMessageDialog(this, "Xóa sách thành công!");
                    loadSachData();
                    clearSachForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa sách thất bại!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void Sach_butTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String keyword = Sach_tfTimKiem.getText().trim();
            String searchType = Sach_cbTimTheo.getSelectedItem().toString();
            
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
                return;
            }
            
            List<Sach> dsSach = null;
            if (searchType.contains("tên")) {
                dsSach = sachService.timKiemTheoTen(keyword);
            } else if (searchType.contains("mã")) {
                dsSach = sachService.timKiemTheoMa(keyword);
            } else if (searchType.contains("thể loại")) {
                dsSach = sachService.timKiemTheoTheLoai(keyword);
            }
            
            DefaultTableModel model = (DefaultTableModel) Sach_tbSach.getModel();
            model.setRowCount(0);
            
            if (dsSach != null && !dsSach.isEmpty()) {
                for (Sach sach : dsSach) {
                    model.addRow(new Object[]{
                        sach.getMaSach(),
                        sach.getTenSach(),
                        sach.getSoLuong(),
                        sach.getTacGia(),
                        sach.getMaTheLoai()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void Sach_butLamMoiActionPerformed(java.awt.event.ActionEvent evt) {
        clearSachForm();
        loadSachData();
    }
    
    private void clearSachForm() {
        Sach_txtTenSach.setText("");
        Sach_txtMaSach.setText("");
        jTextPane1.setText("");
        Sach_txtTacGia.setText("");
        Sach_tfTimKiem.setText("");
    }

    
    private void loadTheLoaiData() {
        try {
            List<TheLoai> dsTheLoai = theLoaiService.getAllTheLoai();
            DefaultTableModel model = (DefaultTableModel) TheLoai_tbTheLoai.getModel();
            model.setRowCount(0);
            
            for (TheLoai tl : dsTheLoai) {
                model.addRow(new Object[]{
                    tl.getMaTheLoai(),
                    tl.getTenTheLoai()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu thể loại: " + e.getMessage());
        }
    }
    
    private void TheLoai_butThemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String tenTheLoai = TheLoai_txtTheLoai.getText().trim();
            
            if (tenTheLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thể loại!");
                return;
            }
            
            TheLoai theLoai = new TheLoai();
            theLoai.setTenTheLoai(tenTheLoai);
            
            if (theLoaiService.themTheLoai(theLoai)) {
                JOptionPane.showMessageDialog(this, "Thêm thể loại thành công!");
                loadTheLoaiData();
                clearTheLoaiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thể loại thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void TheLoai_butSuaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = TheLoai_tbTheLoai.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại cần sửa!");
                return;
            }
            
            String maTheLoai = TheLoai_tbTheLoai.getValueAt(selectedRow, 0).toString();
            String tenTheLoai = TheLoai_txtTheLoai.getText().trim();
            
            if (tenTheLoai.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thể loại!");
                return;
            }
            
            TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
            
            if (theLoaiService.suaTheLoai(theLoai)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thể loại thành công!");
                loadTheLoaiData();
                clearTheLoaiForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thể loại thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void TheLoai_butXoaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = TheLoai_tbTheLoai.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại cần xóa!");
                return;
            }
            
            String maTheLoai = TheLoai_tbTheLoai.getValueAt(selectedRow, 0).toString();
            String tenTheLoai = TheLoai_tbTheLoai.getValueAt(selectedRow, 1).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa thể loại '" + tenTheLoai + "'?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (theLoaiService.xoaTheLoai(maTheLoai)) {
                    JOptionPane.showMessageDialog(this, "Xóa thể loại thành công!");
                    loadTheLoaiData();
                    clearTheLoaiForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thể loại thất bại!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void TheLoai_butTimKiemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String keyword = TheLoai_tfTimKiem.getText().trim();
            String searchType = TheLoai_cbTimTheo.getSelectedItem().toString();
            
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!");
                return;
            }
            
            List<TheLoai> dsTheLoai = null;
            if (searchType.contains("mã")) {
                dsTheLoai = theLoaiService.timKiemTheoMa(keyword);
            } else if (searchType.contains("tên")) {
                dsTheLoai = theLoaiService.timKiemTheoTen(keyword);
            }
            
            DefaultTableModel model = (DefaultTableModel) TheLoai_tbTheLoai.getModel();
            model.setRowCount(0);
            
            if (dsTheLoai != null && !dsTheLoai.isEmpty()) {
                for (TheLoai tl : dsTheLoai) {
                    model.addRow(new Object[]{
                        tl.getMaTheLoai(),
                        tl.getTenTheLoai()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void clearTheLoaiForm() {
        TheLoai_txtTheLoai.setText("");
        TheLoai_tfTimKiem.setText("");
    }


    
    private void loadTaiKhoanData() {
        try {
            List<TaiKhoan> dsTaiKhoan = taiKhoanService.getAllTaiKhoan();
            DefaultTableModel model = (DefaultTableModel) TaiKhoan_tbTaiKhoan.getModel();
            model.setRowCount(0);
            
            for (TaiKhoan tk : dsTaiKhoan) {
                model.addRow(new Object[]{
                    tk.getUsername(),
                    tk.getPassword(),
                    tk.getSoDienThoai(),
                    tk.getTenKhachHang()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu tài khoản: " + e.getMessage());
        }
    }
    
    private void TaiKhoan__butThemActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String username = TaiKhoan_txtTenTaiKhoan.getText().trim();
            String password = TaiKhoan_txtMatKhau.getText().trim();
            String tenKH = TaiKhoan_txtTenTK.getText().trim();
            String sdt = TaiKhoan_txtSDT.getText().trim();
            
            if (username.isEmpty() || password.isEmpty() || tenKH.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }
            
            if (!sdt.matches("\\d{10,11}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ (10-11 chữ số)!");
                return;
            }
            
            TaiKhoan taiKhoan = new TaiKhoan(username, password, sdt, tenKH);
            
            if (taiKhoanService.themTaiKhoan(taiKhoan)) {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
                loadTaiKhoanData();
                clearTaiKhoanForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thất bại! Username có thể đã tồn tại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void TaiKhoan__butSuaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = TaiKhoan_tbTaiKhoan.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần sửa!");
                return;
            }
            
            String username = TaiKhoan_txtTenTaiKhoan.getText().trim();
            String password = TaiKhoan_txtMatKhau.getText().trim();
            String tenKH = TaiKhoan_txtTenTK.getText().trim();
            String sdt = TaiKhoan_txtSDT.getText().trim();
            
            if (username.isEmpty() || password.isEmpty() || tenKH.isEmpty() || sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!");
                return;
            }
            
            if (!sdt.matches("\\d{10,11}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ (10-11 chữ số)!");
                return;
            }
            
            TaiKhoan taiKhoan = new TaiKhoan(username, password, sdt, tenKH);
            
            if (taiKhoanService.suaTaiKhoan(taiKhoan)) {
                JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công!");
                loadTaiKhoanData();
                clearTaiKhoanForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void TaiKhoan__butXoaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = TaiKhoan_tbTaiKhoan.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa!");
                return;
            }
            
            String username = TaiKhoan_tbTaiKhoan.getValueAt(selectedRow, 0).toString();
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc chắn muốn xóa tài khoản '" + username + "'?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (taiKhoanService.xoaTaiKhoan(username)) {
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thành công!");
                    loadTaiKhoanData();
                    clearTaiKhoanForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa tài khoản thất bại!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void Sach_butTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String keyword = Sach_tfTimKiem1.getText().trim();
            
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản!");
                return;
            }
            
            List<TaiKhoan> dsTaiKhoan = taiKhoanService.timKiemTheoUsername(keyword);
            
            DefaultTableModel model = (DefaultTableModel) TaiKhoan_tbTaiKhoan.getModel();
            model.setRowCount(0);
            
            if (dsTaiKhoan != null && !dsTaiKhoan.isEmpty()) {
                for (TaiKhoan tk : dsTaiKhoan) {
                    model.addRow(new Object[]{
                        tk.getUsername(),
                        tk.getPassword(),
                        tk.getSoDienThoai(),
                        tk.getTenKhachHang()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void Sach_butHienThi1ActionPerformed(java.awt.event.ActionEvent evt) {
        loadTaiKhoanData();
        clearTaiKhoanForm();
    }
    
    private void clearTaiKhoanForm() {
        TaiKhoan_txtTenTaiKhoan.setText("");
        TaiKhoan_txtMatKhau.setText("");
        TaiKhoan_txtTenTK.setText("");
        TaiKhoan_txtSDT.setText("");
        Sach_tfTimKiem1.setText("");
    }


    
    private void TruyVan_btActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Lấy thông tin ngày tháng năm mượn và trả
            String ngayMuon = TruyVan_txtNgayMuon.getText().trim();
            String thangMuon = TruyVan_txtThangMuon.getText().trim();
            String namMuon = TruyVan_txtNamMuon.getText().trim();
            
            String ngayTra = TruyVan_txtNgayTra.getText().trim();
            String thangTra = TruyVan_txtThangTra.getText().trim();
            String namTra = TruyVan_txtNamTra.getText().trim();
            
            if (ngayMuon.isEmpty() || thangMuon.isEmpty() || namMuon.isEmpty() ||
                ngayTra.isEmpty() || thangTra.isEmpty() || namTra.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin ngày!");
                return;
            }
            
            // Tạo chuỗi ngày theo format yyyy-MM-dd
            String tuNgay = String.format("%s-%s-%s", namMuon, 
                thangMuon.length() == 1 ? "0" + thangMuon : thangMuon,
                ngayMuon.length() == 1 ? "0" + ngayMuon : ngayMuon);
            
            String denNgay = String.format("%s-%s-%s", namTra,
                thangTra.length() == 1 ? "0" + thangTra : thangTra,
                ngayTra.length() == 1 ? "0" + ngayTra : ngayTra);
            
            // Gọi service để lấy dữ liệu
            List<LiSuGiaoDich> dsLichSu = liSuGDService.truyVanTheoKhoangThoiGian(tuNgay, denNgay);
            
            DefaultTableModel model = (DefaultTableModel) TruyVan_Lsu.getModel();
            model.setRowCount(0);
            
            if (dsLichSu != null && !dsLichSu.isEmpty()) {
                for (LiSuGiaoDich ls : dsLichSu) {
                    model.addRow(new Object[]{
                        ls.getMaGiaoDich(),
                        ls.getUsername(),
                        ls.getMaKhachHang(),
                        ls.getNgayGiaoDich(),
                        ls.getSoLuong(),
                        ls.getTrangThai()
                    });
                }
                JOptionPane.showMessageDialog(this, "Tìm thấy " + dsLichSu.size() + " giao dịch!");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giao dịch nào trong khoảng thời gian này!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }
    
    private void TruyVan_txtNamMuonActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống hoặc thêm validation
    }

    private void TruyVan_txtNgayMuonActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống hoặc thêm validation
    }

    private void TruyVan_txtThangMuonActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống hoặc thêm validation
    }

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống
    }

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống
    }

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống
    }

    private void TruyVan_txtNgayTraActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống
    }

    private void TruyVan_txtThangTraActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống
    }

    private void TruyVan_txtNamTraActionPerformed(java.awt.event.ActionEvent evt) {
        // Có thể để trống
    }

    // ==================== TABLE LISTENERS ====================
    
    private void addTableListeners() {
        // Listener cho bảng Sách - Click để điền form
        Sach_tbSach.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = Sach_tbSach.getSelectedRow();
                if (row != -1) {
                    Sach_txtTenSach.setText(Sach_tbSach.getValueAt(row, 1).toString());
                    Sach_txtMaSach.setText(Sach_tbSach.getValueAt(row, 2).toString());
                    Sach_txtTacGia.setText(Sach_tbSach.getValueAt(row, 3).toString());
                    jTextPane1.setText(Sach_tbSach.getValueAt(row, 4).toString());
                }
            }
        });
        
        // Listener cho bảng Thể loại
        TheLoai_tbTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TheLoai_tbTheLoai.getSelectedRow();
                if (row != -1) {
                    TheLoai_txtTheLoai.setText(TheLoai_tbTheLoai.getValueAt(row, 1).toString());
                }
            }
        });
        
        // Listener cho bảng Tài khoản
        TaiKhoan_tbTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = TaiKhoan_tbTaiKhoan.getSelectedRow();
                if (row != -1) {
                    TaiKhoan_txtTenTaiKhoan.setText(TaiKhoan_tbTaiKhoan.getValueAt(row, 0).toString());
                    TaiKhoan_txtMatKhau.setText(TaiKhoan_tbTaiKhoan.getValueAt(row, 1).toString());
                    TaiKhoan_txtSDT.setText(TaiKhoan_tbTaiKhoan.getValueAt(row, 2).toString());
                    TaiKhoan_txtTenTK.setText(TaiKhoan_tbTaiKhoan.getValueAt(row, 3).toString());
                }
            }
        });
    }


    
    private void Logout(java.awt.event.ActionEvent evt) {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn đăng xuất?", 
            "Xác nhận đăng xuất", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            Login lg = new Login();
            lg.setVisible(true);
            this.dispose();
        }
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
        Sach_butLamMoi = new javax.swing.JButton();
        Sach_butSua = new javax.swing.JButton();
        Sach_butHienThi = new javax.swing.JButton();
        Sach_cbTimTheo = new javax.swing.JComboBox<>();
        jScrollPane17 = new javax.swing.JScrollPane();
        Sach_txtTacGia = new javax.swing.JTextPane();
        Sach_lbTenSach1 = new javax.swing.JLabel();
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
        TheLoai_butHienThi = new javax.swing.JButton();
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
        Sach_tfTimKiem1 = new javax.swing.JTextPane();
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
        jLabel2 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        TruyVan_txtNgayTra = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TruyVan_txtThangTra = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TruyVan_txtNamTra = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TruyVan_btLayThongTin = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        Sach.setDoubleBuffered(false);

        Sach_lbTenSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbTenSach.setText("Tên Sách:");

        Sach_lbSoLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbSoLuong.setText("Số lượng:");

        jScrollPane1.setViewportView(Sach_txtTenSach);

        jScrollPane2.setViewportView(Sach_txtSoLuong);

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
        jScrollPane3.setViewportView(Sach_tbSach);

        Sach_lbTheLoai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbTheLoai.setText("Thể loại:");

        Sach_butThem.setLabel("Thêm");
        Sach_butThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butThemActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(Sach_txtTheLoai);

        jScrollPane6.setViewportView(Sach_tfTimKiem);

        Sach_butXoa.setText("Xóa");
        Sach_butXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butXoaActionPerformed(evt);
            }
        });

        Sach_butTimKiem.setText("Tìm kiếm");
        Sach_butTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butTimKiemActionPerformed(evt);
            }
        });

        Sach_butLamMoi.setText("Làm mới");
        Sach_butLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butLamMoiActionPerformed(evt);
            }
        });

        Sach_butSua.setText("Sửa");
        Sach_butSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sach_butSuaActionPerformed(evt);
            }
        });

        Sach_butHienThi.setText("Hiển thị");

        Sach_cbTimTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tim theo tên", "Tim theo mã", "Tim theo thể loại" }));

        jScrollPane17.setViewportView(Sach_txtTacGia);

        Sach_lbTenSach1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Sach_lbTenSach1.setText("Tác giả:");

        javax.swing.GroupLayout SachLayout = new javax.swing.GroupLayout(Sach);
        Sach.setLayout(SachLayout);
        SachLayout.setHorizontalGroup(
            SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SachLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SachLayout.createSequentialGroup()
                        .addComponent(Sach_lbTenSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(SachLayout.createSequentialGroup()
                        .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SachLayout.createSequentialGroup()
                                .addComponent(Sach_lbTenSach, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SachLayout.createSequentialGroup()
                                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Sach_lbTheLoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Sach_lbSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5))))
                        .addGap(64, 64, 64)
                        .addComponent(Sach_butThem)
                        .addGap(18, 18, 18)
                        .addComponent(Sach_butSua)
                        .addGap(18, 18, 18)
                        .addComponent(Sach_butXoa)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SachLayout.createSequentialGroup()
                        .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(SachLayout.createSequentialGroup()
                                .addComponent(Sach_cbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Sach_butTimKiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Sach_butHienThi))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(Sach_butLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        SachLayout.setVerticalGroup(
            SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SachLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SachLayout.createSequentialGroup()
                        .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(Sach_lbTenSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Sach_butThem)
                                .addComponent(Sach_butXoa)
                                .addComponent(Sach_butSua)))
                        .addGap(18, 18, 18)
                        .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(Sach_lbSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(Sach_lbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane17)
                    .addComponent(Sach_lbTenSach1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Sach_butTimKiem)
                        .addComponent(Sach_butHienThi))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sach_cbTimTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Sach_butLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

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

        TheLoai_butHienThi.setText("Hiển thị");

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
                        .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TheLoai_butXoa)
                            .addComponent(TheLoai_butHienThi))))
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
                    .addGroup(TheLoaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TheLoai_butTimKiem)
                        .addComponent(TheLoai_butHienThi))
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

        jScrollPane13.setViewportView(Sach_tfTimKiem1);

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
                "Username", "Mật khẩu", "Số điện thoại", "Tên khách hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setText("/");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("/");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("/");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

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
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TruyVan_txtNgayMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(6, 6, 6)
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TruyVan_txtThangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addGap(6, 6, 6)
                                .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TruyVan_txtNamMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(TruyVan_btLayThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(LichSuGiaoDichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(LichSuGiaoDichLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(TruyVan_txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TruyVan_txtThangMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TruyVan_txtNamMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TruyVan_txtNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(22, 22, 22)))
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 52, Short.MAX_VALUE))
        );

        AdminCT.addTab("Truy Vấn", LichSuGiaoDich);
        LichSuGiaoDich.getAccessibleContext().setAccessibleName("");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 128)); // NOI18N
        jButton1.setText("Đăng xuất");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout(evt);
            }
        });
        AdminCT.addTab("Đăng xuất", jButton1);

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

    }//GEN-LAST:event_Sach_butThemActionPerformed

    private void Sach_butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butXoaActionPerformed

    }//GEN-LAST:event_Sach_butXoaActionPerformed

    private void Sach_butTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sach_butTimKiemActionPerformed

    private void Sach_butLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butLamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sach_butLamMoiActionPerformed

    private void Sach_butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sach_butSuaActionPerformed

    private void TheLoai_butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TheLoai_butThemActionPerformed

    private void TheLoai_butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TheLoai_butSuaActionPerformed

    private void TheLoai_butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TheLoai_butXoaActionPerformed

    private void TheLoai_butTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TheLoai_butTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TheLoai_butTimKiemActionPerformed

    private void TaiKhoan__butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaiKhoan__butThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaiKhoan__butThemActionPerformed

    private void TaiKhoan__butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaiKhoan__butSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaiKhoan__butSuaActionPerformed

    private void TaiKhoan__butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaiKhoan__butXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaiKhoan__butXoaActionPerformed

    private void Sach_butTimKiem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sach_butTimKiem1ActionPerformed
        // TODO add your handling code here:
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

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

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
    private javax.swing.JTextPane Sach_tfTimKiem;
    private javax.swing.JTextPane Sach_tfTimKiem1;
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
    private javax.swing.JPanel TheLoai;
    private javax.swing.JButton TheLoai_butHienThi;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
