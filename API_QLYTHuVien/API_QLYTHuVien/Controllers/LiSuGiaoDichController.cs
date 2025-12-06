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
            return db.LiSuGiaoDich.OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//lấy theo username
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByUsername(string Username)
        {
            return db.LiSuGiaoDich.Where(ls => ls.Username == Username).OrderByDescending(ls => ls.NgayGD).ToList();
        }
        
        [HttpGet]//lấy theo mã sách
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByMaSach(string MaSach)
        {
            return db.LiSuGiaoDich.Where(ls => ls.MaSach == MaSach).OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//lấy theo ngày giao dịch
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByNgayGD(DateTime NgayGDCuoi, DateTime NgayGDDau)
        {
            return db.LiSuGiaoDich.Where(ls=> ls.NgayGD <= NgayGDCuoi && ls.NgayGD >= NgayGDDau).OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//Lấy theo trạng thái
        public IEnumerable<LiSuGiaoDich> GetLSuGiaoDichByTrangThai(string TrangThai)
        {
            return db.LiSuGiaoDich.Where(ls => ls.TrangThai == TrangThai).OrderByDescending(ls => ls.NgayGD).ToList();
        }

        [HttpGet]//Lấy theo mã
        public bool GetLSuGiaoDichByMaGD(string MaGD)
        {
            db.LiSuGiaoDich.Find(MaGD);
            if (db.LiSuGiaoDich.Find(MaGD) == null)
            {
                return false;
            }
            return true;
        }

        [HttpDelete]
        public void DeleteLiSuGiaoDichByYear()
        {
            DateTime DateDelete = DateTime.Now;
            IEnumerable<LiSuGiaoDich> arrDelete = db.LiSuGiaoDich
                .Where(ls => ls.NgayGD.HasValue && ls.NgayGD.Value.Year <= (DateDelete.Year - 3));
            db.LiSuGiaoDich.RemoveRange(arrDelete);
            db.SaveChanges();
        }
    }
}
