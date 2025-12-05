using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace API_QLYTHuVien.Controllers
{
    public class LiSuGiaoDichController : ApiController
    {
        API_ThuVienEntities db = new API_ThuVienEntities();
        public LiSuGiaoDichController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            db.Configuration.LazyLoadingEnabled = false;
        }

        [HttpGet]//lấy hết
        public IEnumerable<LiSuGiaoDich> GetAllLSuGiaoDich()
        {
            return db.LiSuGiaoDiches.OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//lấy theo username
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByUsername(string Username)
        {
            return db.LiSuGiaoDiches.Where(ls => ls.Username == Username).OrderByDescending(ls => ls.NgayGD).ToList();
        }
        
        [HttpGet]//lấy theo mã sách
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByMaSach(string MaSach)
        {
            return db.LiSuGiaoDiches.Where(ls => ls.MaSach == MaSach).OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//lấy theo ngày giao dịch
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByNgayGD(DateTime NgayGDCuoi, DateTime NgayGDDau)
        {
            return db.LiSuGiaoDiches.Where(ls=> ls.NgayGD <= NgayGDCuoi && ls.NgayGD >= NgayGDDau).OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//Lấy theo trạng thái
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByTrangThai(string TrangThai)
        {
            return db.LiSuGiaoDiches.Where(ls => ls.TrangThai == TrangThai).OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpPost]
        public bool AddLiSuGiaoDich(string MaGD, string Username, DateTime NgayGD, string MaSach, string SoLuong, string TrangThai)
        {
            LiSuGiaoDich newLiSuGiaoDich = new LiSuGiaoDich
            {
                MaGD = MaGD,
                Username = Username,
                NgayGD = NgayGD,
                MaSach = MaSach,
                SoLuong = SoLuong,
                TrangThai = TrangThai
            };
            if(db.LiSuGiaoDiches.Any(ls => ls.MaGD == MaGD))
            {
                return false;
            }
            db.LiSuGiaoDiches.Add(newLiSuGiaoDich);
            db.SaveChanges();

            return true;
        }
        [HttpDelete]
        public bool DeleteLiSuGiaoDich(string MaGD)
        {
            LiSuGiaoDich existingLiSuGiaoDich = db.LiSuGiaoDiches.Find(MaGD);
            if (existingLiSuGiaoDich == null)
            {
                return false; // Trả về false nếu lịch sử giao dịch không tồn tại
            }
            db.LiSuGiaoDiches.Remove(existingLiSuGiaoDich);
            db.SaveChanges();
            return true;
        }
        [HttpDelete]
        public void DeleteLiSuGiaoDichByYear()
        {
            DateTime DateDelete = DateTime.Now;
            IEnumerable<LiSuGiaoDich> arrDelete = db.LiSuGiaoDiches
                .Where(ls => ls.NgayGD.HasValue && ls.NgayGD.Value.Year <= (DateDelete.Year - 3));
            db.LiSuGiaoDiches.RemoveRange(arrDelete);
            db.SaveChanges();
        }
    }
}
