using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace API_QLYTHuVien.Controllers
{
    public class KhachHangController : ApiController
    {
        API_ThuVienEntities db = new API_ThuVienEntities();
        public KhachHangController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            db.Configuration.LazyLoadingEnabled = false;
        }

        [HttpGet]//lấy hết
        public IEnumerable<KhachHang> GetAllKhachHang()
        {
            return db.KhachHangs;
        }

        [HttpGet]//lấy theo mã
        public KhachHang GetKhachHangById(string MaKH)
        {
            return db.KhachHangs.Find(MaKH);
        }

        [HttpGet]//lấy theo tên
        public IEnumerable<KhachHang> SearchKhachHangByName(string keyword)
        {
            return db.KhachHangs.Where(kh => kh.TenKH.Contains(keyword));
        }

        [HttpGet]//lấy theo số điện thoại
        public KhachHang SearchKhachHangBySDT(string sdt)
        {
            return db.KhachHangs.Find(sdt);
        }

        [HttpPost]//Thêm khách hàng
        public bool AddKhachHang(string MaKH, string TenKH, string SDT)
        {
            KhachHang newKhachHang = new KhachHang
            {
                MaKH = MaKH,
                TenKH = TenKH,
                SdtKH = SDT,
            };
            if (db.KhachHangs.Find(MaKH) != null)
            {
                return false; // Trả về false nếu mã khách hàng đã tồn tại
            }
            db.KhachHangs.Add(newKhachHang);
            db.SaveChanges();
            return true;
        }

        [HttpPut]//Sửa khách hàng
        public bool UpdateKhachHang(string MaKH, string TenKH, string SDT)
        {
            KhachHang existingKhachHang = db.KhachHangs.Find(MaKH);
            if (existingKhachHang == null)
            {
                return false; // Trả về false nếu khách hàng không tồn tại
            }
            existingKhachHang.TenKH = TenKH;
            existingKhachHang.SdtKH = SDT;
            db.SaveChanges();
            return true;
        }

        [HttpDelete]//Xóa khách hàng
        public bool DeleteKhachHang(string MaKH)
        {
            KhachHang existingKhachHang = db.KhachHangs.Find(MaKH);
            if (existingKhachHang == null)
            {
                return false; // Trả về false nếu khách hàng không tồn tại
            }
            db.Muons.RemoveRange(db.Muons.Where(m => m.MaKH == MaKH));
            db.KhachHangs.Remove(existingKhachHang);
            db.SaveChanges();
            return true;
        }

    }
}
