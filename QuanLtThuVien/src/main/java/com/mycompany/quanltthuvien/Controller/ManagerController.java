package com.mycompany.quanltthuvien.Controller;

import java.util.ArrayList;

import com.mycompany.quanltthuvien.Model.KhachHang;
import com.mycompany.quanltthuvien.Model.Muon;
import com.mycompany.quanltthuvien.Service.KhachHangService;
import com.mycompany.quanltthuvien.Service.MuonService;

public class ManagerController {
   
    //____________// #khách hàng
    KhachHangService khs = new KhachHangService();
    
    //Lấy hết khách hàng
    public ArrayList<KhachHang> GetAllKhachHang() {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            list = khs.GetAllKhachHang();
            return list;
        } catch (Exception e) {
        }
        return list;
    }
    
    //Thêm khách hàng
    public boolean AddKhachHang(KhachHang khachHang) {
        try {
            return khs.AddKhachHang(khachHang);
        } catch (Exception e) {
        }
        return false;
    }

    //Sửa khách hàng
    public boolean UpdateKhachHang(KhachHang khachHang) {
        try {
            return khs.UpdateKhachHang(khachHang);
        } catch (Exception e) {
        }
        return false;
    }

    //Xóa khách hàng
    public boolean DeleteKhachHang(String MaKH) {
        try {
            return khs.DeleteKhachHang(MaKH);
        } catch (Exception e) {
        }
        return false;
    }

    //Tìm theo mã
    public KhachHang GetKhachHangByMa(String MaKH) {
        try {
            return khs.GetKhachHangByMa(MaKH);
        } catch (Exception e) {
        }
        return null;
    }

    //Tìm theo tên
    public ArrayList<KhachHang> GetKhachHangByTen(String TenKH) {
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            list = khs.GetKhachHangByTen(TenKH);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Tìm theo số điện thoại
    public KhachHang GetKhachHangBySdt(String SDT) {
        try {
            return khs.GetKhachHangBySdt(SDT);
        } catch (Exception e) {
        }
        return null;
    }

    //_____________?? #Mượn
    MuonService ms = new MuonService();

    //Lấy hết mượn
    public ArrayList<Muon> GetAllMuon() {
        ArrayList<Muon> list = new ArrayList<>();
        try {
            list = ms.GetAllMuon();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy mượn theo mã khách hàng
    public ArrayList<Muon> GetMuonByMa(String MaKH) {
        ArrayList<Muon> arr = new ArrayList<>();
        try {
            arr = ms.GetMuonByMaKH(MaKH);
            return arr;
        } catch (Exception e) {
        }
        return arr;
    }

    //Lấy theo mã mượn
    public Muon GetMuonByMaMuon(String MaMuon) {
        try {
            return ms.GetMuonByMaMuon(MaMuon);
        } catch (Exception e) {
        }
        return null;
    }

    //Lấy theo mã sách
    public ArrayList<Muon> GetMuonByMaSach(String MaSach) {
        ArrayList<Muon> arr = new ArrayList<>();
        try {
            arr = ms.GetMuonByMaSach(MaSach);
            return arr;
        } catch (Exception e) {
        }
        return arr;
    }

    //Lấy theo ngày mượn
    public ArrayList<Muon> GetMuonByNgayMuon(String NgayMuon) {
        ArrayList<Muon> arr = new ArrayList<>();
        try {
            arr = ms.GetMuonByNgayMuon(NgayMuon);
            return arr;
        } catch (Exception e) {
        }
        return arr;
    }
}
