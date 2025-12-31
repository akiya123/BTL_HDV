package com.mycompany.quanltthuvien.Controller;

import java.io.IOException;
import java.util.ArrayList;

import com.mycompany.quanltthuvien.Model.KhachHang;
import com.mycompany.quanltthuvien.Model.Muon;
import com.mycompany.quanltthuvien.Model.Sach;
import com.mycompany.quanltthuvien.Model.TheLoai;
import com.mycompany.quanltthuvien.Service.KhachHangService;
import com.mycompany.quanltthuvien.Service.MuonService;
import com.mycompany.quanltthuvien.Service.SachService;
import com.mycompany.quanltthuvien.Service.TheLoaiService;

public class ManagerController {

    //____________// $Random mã
    public String RandomMa() {
        String Ma = "";
        for(int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
            Ma += random;
        }
        return Ma;
    }
    //_____________//#Thể loại
    TheLoaiService tl = new TheLoaiService();
    //Lấy thể loại theo tên
    public ArrayList<TheLoai> GetTheLoaiByTen(String TenTheLoai) {
        ArrayList<TheLoai> list = new ArrayList<>();
        try {
            list = tl.GetTheLoaiByTen(TenTheLoai);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy hết thể loại
    public ArrayList<TheLoai> GetAllTheLoai() {
        ArrayList<TheLoai> list = new ArrayList<>();
        try {
            list = tl.GetAllTheLoai();
            return list;
        } catch (Exception e) {
        }
        return list;
    }
    //Tìm theo mã
    public TheLoai GetTheLoaiByMa(String MaTheLoai) {
                try {
                    return tl.GetTheLoaiByMa(MaTheLoai);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
    }

    //_____________//#Sách
    SachService ss = new SachService();

    //Lấy theo thể loại
    public ArrayList<Sach> GetSachByTheLoai(String maTheLoai) {
        ArrayList<Sach> list = new ArrayList<>();
        try {
            list = ss.GetSachByTheLoai(maTheLoai);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Hủy sách
    public boolean RemoveSomeSach(String MaSach, int SoLuongXoa, String username) {
        try {
            return ss.RemoveSomeSach(MaSach, SoLuongXoa,username);
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<Sach> GetAllSach() {
        ArrayList<Sach> list = new ArrayList<>();
        try {
            list = ss.GetAllSach();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    public Sach GetSachByMa(String MaSach) {
        try {
            return ss.GetSachByMa(MaSach);
        } catch (Exception e) {
        }
        return null;
    }
    
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
            do {
                khachHang.setMaKH("KH"+RandomMa());             
            } while (khs.GetKhachHangByMa(khachHang.getMaKH()) != null);
            return khs.AddKhachHang(khachHang);
        } catch (Exception e) {
        }
        return false;
    }

    public static void main(String[] args) {
        ManagerController mc = new ManagerController();
        System.out.println(mc.AddKhachHang(new KhachHang("", "Nguyen Van B", "0987654321")));
        System.out.println("Done");
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

    //Lấy theo ngày trả
    public ArrayList<Muon> GetMuonByNgayTra(String NgayTra) {
        ArrayList<Muon> arr = new ArrayList<>();
        try {
            arr = ms.GetMuonByNgayTra(NgayTra);
            return arr;
        } catch (Exception e) {
        }
        return arr;
    }

    //Thêm mượn
    public boolean MuonSach(Muon muon, String username) {
        try {
            do {
                muon.setMaMuon("M"+RandomMa());             
            } while (ms.GetMuonByMaMuon(muon.getMaMuon()) != null);
            return ms.MuonSach(muon, username);
        } catch (Exception e) {
        }
        return false;
    }

    //Trả sách
    public boolean TraSach(String MaMuon, String username,int SoLuong) {
        try {
            return ms.TraSach(MaMuon, username,SoLuong);
        } catch (Exception e) {
        }
        return false;
    }

    //Mất sách
    public boolean MatSach(String MaMuon, String username, int SoLuong) {
        try {
            return ms.MatSach(MaMuon, username,SoLuong);
        } catch (Exception e) {
        }
        return false;
    }
}
