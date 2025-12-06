using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace API_QLYTHuVien.Controllers
{
    public class SachController : ApiController
    {
        API_ThuVienEntities db = new API_ThuVienEntities();
        public SachController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            db.Configuration.LazyLoadingEnabled = false;
        }

        [HttpGet]//lấy hết
        public IEnumerable<Sach> GetAllSach()
        {
            return db.Saches;
        }

        [HttpGet]//lấy theo mã
        public Sach GetSachById(string MaSach)
        {
            return db.Saches.Find(MaSach);
        }

        [HttpGet] // lấy theo thể loại
        public IEnumerable<Sach> GetSachByTheLoai(string MaTheLoai)
        {
            return db.Saches.Where(s => s.MaTheLoai == MaTheLoai);
        }

        [HttpGet] // lấy theo tên
        public IEnumerable<Sach> SearchSachByName(string keyword)
        {
            if(string.IsNullOrWhiteSpace(keyword))
    {
                // Decide behavior: return empty set instead of all records
                return Enumerable.Empty<Sach>();
            }

            keyword = keyword.Trim().ToLower();

            // case-insensitive search translated to SQL by EF
            return db.Saches.Where(s => s.TenSach.ToLower().Contains(keyword));
        }

        [HttpPost]//Thêm sách
        public bool AddSach(string MaSach, string TenSach, string SoLuong, string TacGia, string MaTheLoai)
        {
            if (db.Saches.Any(s => s.MaSach == MaSach)) return false;
            if (!db.TheLoais.Any(t => t.MaTheLoai == MaTheLoai)) return false; // validate FK target

            if (!int.TryParse(SoLuong, out var qty)) return false;

            var newSach = new Sach { MaSach = MaSach, TenSach = TenSach, SoLuong = qty, TacGia = TacGia, MaTheLoai = MaTheLoai };
            db.Saches.Add(newSach);
            db.SaveChanges();
            return true;
        }

        [HttpPut]//Sửa sách
        public bool UpdateSach(string MaSach, string TenSach, string SoLuong, string TacGia, string MaTheLoai)
        {
            Sach existingSach = db.Saches.Find(MaSach);
            if (existingSach == null) return false;
            existingSach.TenSach = TenSach;
            existingSach.SoLuong = int.Parse(SoLuong);
            existingSach.TacGia = TacGia;
            existingSach.MaTheLoai = MaTheLoai;
            db.SaveChanges();
            return true;
        }

        [HttpDelete]//Xóa sách
        public bool DeleteSach(string MaSach)
        {
            Sach existingSach = db.Saches.Find(MaSach);
            if (existingSach == null)
            {
                return false; // Trả về false nếu sách không tồn tại
            }
            db.Saches.Remove(existingSach);
            db.SaveChanges();
            return true;
        }
    }
}
