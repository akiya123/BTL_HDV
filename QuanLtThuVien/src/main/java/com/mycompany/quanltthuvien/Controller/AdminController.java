package com.mycompany.quanltthuvien.Controller;

import java.util.ArrayList;

import com.mycompany.quanltthuvien.Model.LiSuGiaoDich;
import com.mycompany.quanltthuvien.Model.Sach;
import com.mycompany.quanltthuvien.Model.TaiKhoan;
import com.mycompany.quanltthuvien.Model.TheLoai;
import com.mycompany.quanltthuvien.Service.LiSuGDService;
import com.mycompany.quanltthuvien.Service.SachService;
import com.mycompany.quanltthuvien.Service.TaiKhoanService;
import com.mycompany.quanltthuvien.Service.TheLoaiService;

public class AdminController {

    //____________// $Random mã
    public String RandomMa() {
        String Ma = "";
        for(int i = 0; i < 6; i++) {
            int random = (int) (Math.random() * 10);
            Ma += random;
        }
        return Ma;
    }

    //_________________// #Sách
    SachService ss = new SachService();

    //Lấy hết sách
    public ArrayList<Sach> GetAllSach() {
        ArrayList<Sach> list = new ArrayList<>();
        try {
            list = ss.GetAllSach();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Thêm sách
    public boolean AddSach(Sach sach) {
        try {
            ArrayList<Sach> list = GetAllSach();
            for (Sach s : list) {
                sach.setMaSach("S"+RandomMa());
                if(!s.getMaSach().equals(sach.getMaSach())) {
                    break;
                }
            }
            System.out.println("Debug MaSach:");
            System.out.println(sach.getMaSach());          
            return ss.AddSach(sach);
        } catch (Exception e) {
            System.out.println("Lỗi ở AdminController - AddSach");
        }
        return false;
    }
    
    //Thêm số sách
    public boolean AddMoreSach(String MaSach, int SoLuong){
        try {
            return ss.AddMoreSach(MaSach, SoLuong);
        } catch (Exception e) {
        }
        return false;
    }

    //Xóa sách
    public boolean DeleteSach(String maSach) {
        try {
            return ss.DeleteSach(maSach);
        } catch (Exception e) {
        }
        return false;
    }

    //Sửa sách
    public boolean UpdateSach(Sach sach) {
        try {
            return ss.UpdateSach(sach);
        } catch (Exception e) {
        }
        return false;
    }

    //Tìm kiếm theo tên
    public ArrayList<Sach> GetSachByTenSach(String tenSach) {
        ArrayList<Sach> list = new ArrayList<>();
        try {
            list = ss.GetSachByTenSach(tenSach);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Tìm kiếm theo mã
    public Sach GetSachByMa(String maSach) {
        try {
            return ss.GetSachByMa(maSach);
        } catch (Exception e) {
        }
        return null;
    }

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

    //______________// #Thể loại
    TheLoaiService tls = new TheLoaiService();
    //Lấy thể loại theo tên
    public ArrayList<TheLoai> GetTheLoaiByTen(String TenTheLoai) {
        ArrayList<TheLoai> list = new ArrayList<>();
        try {
            list = tls.GetTheLoaiByTen(TenTheLoai);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy hết thể loại
    public ArrayList<TheLoai> GetAllTheLoai() {
        ArrayList<TheLoai> list = new ArrayList<>();
        try {
            list = tls.GetAllTheLoai();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Thêm thể loại
    public boolean AddTheLoai(TheLoai theLoai) {
        try {
           ArrayList<TheLoai> list = GetAllTheLoai();
            for (TheLoai tl : list) {
                theLoai.setMaTheLoai("TL"+RandomMa());
                if(!tl.getMaTheLoai().equals(theLoai.getMaTheLoai())) {
                    break;
                }
            }
            System.out.println("Debug MaSach:");
            System.out.println(theLoai.getMaTheLoai());
            return tls.AddTheLoai(theLoai);
        } catch (Exception e) {
            System.out.println("Lỗi ở AdminController - AddTheLoai");
        }
        return false;
    }

    //Xóa thể loại
    public boolean DeleteTheLoai(String maTheLoai) {
        try {
            return tls.DeleteTheLoai(maTheLoai);
        } catch (Exception e) {
        }
        return false;
    }

    //Sửa thể loại
    public boolean UpdateTheLoai(TheLoai theLoai) {
        try {
            return tls.UpdateTheLoai(theLoai);
        } catch (Exception e) {
            System.out.println("Lỗi ở AdminController - UpdateTheLoai");
        }
        return false;
    }

    //Tìm thể loại theo mã
    public TheLoai GetTheLoaiByMa(String maTheLoai) {
        try {
            return tls.GetTheLoaiByMa(maTheLoai);
        } catch (Exception e) {
        }
        return null;
    }

    //______________// #Tài khoản
    TaiKhoanService tks = new TaiKhoanService();
    //Thêm tài khoản mới
    public boolean AddTaiKhoanManager(String username,String Pass,String TenTK,String SdtTK) {
        TaiKhoan TK = new TaiKhoan(username, Pass, "MG", SdtTK, TenTK);

        try {
            return tks.addTaiKhoan(TK);
        } catch (Exception e) {
        }
        return false;
    }

    //Xóa tài khoản
    public boolean DeleteTaiKhoan(String username) {
        try {
            return tks.deleteTaiKhoan(username);
        } catch (Exception e) {
        }
        return false;
    }

    public TaiKhoan GetTaiKhoan(String username) {
        try {
            return tks.GetTaiKhoan(username);
        } catch (Exception e) {
        }
        return null;
    }

    //Sửa tài khoản
    public boolean UpdateTaiKhoan(String username,String Pass, String ComfirmPass,String TenTK,String SdtTK) {
        if(!Pass.equals(ComfirmPass)){
            return false;
        }
        TaiKhoan TK = new TaiKhoan(username, Pass, "MG", SdtTK, TenTK);
        try {
            return tks.updateTaiKhoan(TK);
        } catch (Exception e) {
        }
        return false;
    }

    //Lấy danh sách tài khoản
    public ArrayList<TaiKhoan> GetAllTaiKhoan() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        try {
            list = tks.GetAllTaiKhoan();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //______________// #Lịch sử giao dịch
    LiSuGDService lgs = new LiSuGDService();

    //Lấy lịch sử giao dịch
    public ArrayList<LiSuGiaoDich> GetAllLichSuGiaoDich() {
        ArrayList<LiSuGiaoDich> list = new ArrayList<>();
        try {
            list = lgs.GetAllLiSuGD();
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy theo tài khoản
    public ArrayList<LiSuGiaoDich> GetLiSuGDByTaiKhoan(String username) {
        ArrayList<LiSuGiaoDich> list = new ArrayList<>();
        try {
            list = lgs.GetLiSuGDByTaiKhoan(username);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy theo mã sách
    public ArrayList<LiSuGiaoDich> GetLiSuGDByMaSach(String maSach) {
        ArrayList<LiSuGiaoDich> list = new ArrayList<>();
        try {
            list = lgs.GetLiSuGDByMaSach(maSach);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy theo trạng thái
    public ArrayList<LiSuGiaoDich> GetLiSuGDByTrangThai(String trangThai) {
        ArrayList<LiSuGiaoDich> list = new ArrayList<>();
        try {
            list = lgs.GetLiSuGDByTrangThai(trangThai);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Lấy theo ngày giao dịch
    public ArrayList<LiSuGiaoDich> GetLiSuGDByNgayGD(String NgayGDCuoi, String NgayGDDau) {
        ArrayList<LiSuGiaoDich> list = new ArrayList<>();
        try {
            list = lgs.GetLiSuGDByNgayGD(NgayGDCuoi, NgayGDDau);
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    //Xóa lịch sử giao dịch
    public boolean DeleteLiSuGD() {
        try {
            lgs.DeleteLiSuGD();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
