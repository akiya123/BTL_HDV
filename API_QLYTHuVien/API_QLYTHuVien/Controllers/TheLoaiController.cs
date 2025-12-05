using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace API_QLYTHuVien.Controllers
{
    public class TheLoaiController : ApiController
    {
        API_ThuVienEntities db = new API_ThuVienEntities();
        public TheLoaiController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            db.Configuration.LazyLoadingEnabled = false;
        }

        [HttpGet]//lấy hết
        public IEnumerable<TheLoai> GetAllTheLoai()
        {

            return db.TheLoais.ToList();
        }

        [HttpGet]//lấy theo mã
        public TheLoai GetTheLoaiById(string MaTheLoai)
        {
            return db.TheLoais.Find(MaTheLoai);
        }


        [HttpGet] // lấy theo tên
        public IEnumerable<TheLoai> SearchTheLoaiByName(string keyword)
        {
            return db.TheLoais.Where(s => s.TenTheLoai.Contains(keyword)).ToList();
        }

        [HttpPost]//Thêm thể loại
        public bool AddTheLoai(string MaTheLoai, string TenTheLoai)
        {
            TheLoai newTheLoai = new TheLoai
            {
                MaTheLoai = MaTheLoai,
                TenTheLoai = TenTheLoai
            };
            if (db.TheLoais.Find(MaTheLoai) != null)
            {
                return false; // Trả về false nếu mã thể loại đã tồn tại
            }
            db.TheLoais.Add(newTheLoai);
            db.SaveChanges();
            return true;
        }

        [HttpPut]//Sửa thể loại
        public bool UpdateTheLoai(string MaTheLoai, string TenTheLoai)
        {
            TheLoai existingTheLoai = db.TheLoais.Find(MaTheLoai);
            if (existingTheLoai == null)
            {
                return false; // Trả về false nếu thể loại không tồn tại
            }
            existingTheLoai.TenTheLoai = TenTheLoai;
            db.SaveChanges();
            return true;
        }

        [HttpDelete]//Xóa thể loại
        public bool DeleteTheLoai(string MaTheLoai)
        {
            TheLoai existingTheLoai = db.TheLoais.Find(MaTheLoai);
            if (existingTheLoai == null)
            {
                return false; // Trả về false nếu thể loại không tồn tại
            }
            db.TheLoais.Remove(existingTheLoai);
            db.SaveChanges();
            return true;
        }
    }
}
