using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Text.RegularExpressions;
using System.Web.Http;

namespace API_QLYTHuVien.Controllers
{
    public class MuonController : ApiController
    {
        API_ThuVienEntities db = new API_ThuVienEntities();
        public MuonController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            db.Configuration.LazyLoadingEnabled = false;
        }
        [HttpGet]//lấy hết
        public IEnumerable<Muon> GetAllMuon()
        {
            return db.Muons;
        }

        [HttpGet]//lấy theo MaKH
        public IEnumerable<Muon> GetMuonByMaKH(string MaKH)
        {
            return db.Muons.Where(m => m.MaKH == MaKH);
        }
        [HttpGet]//lấy theo MaMuon
        public Muon GetMuonByMaMuon(string MaMuon)
        {
            return db.Muons.Find(MaMuon);
        }
        [HttpGet]//lấy theo MaSach
        public IEnumerable<Muon> GetMuonByMaSach(string MaSach)
        {
            return db.Muons.Where(m => m.MaSach == MaSach);
        }
        [HttpGet]//lấy theo NgayMuon
        public IEnumerable<Muon> GetMuonByNgayMuon(DateTime NgayMuon)
        {
            return db.Muons.Where(m => m.NgayMuon == NgayMuon);
        }
        [HttpGet]//lấy theo NgayTra
        public IEnumerable<Muon> GetMuonByNgayTra(DateTime NgayTra)
        {
            return db.Muons.Where(m => m.NgayTra == NgayTra);
        }
        public static string GenerateFourRandomDigits()
        {
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++)
            {
                // Next(0, 10) tạo số từ 0 đến 9
                sb.Append(random.Next(0, 10));
            }
            return sb.ToString();
        }

        [HttpPost]//Thêm mượn
        public bool AddMuon(string MaMuon, string MaKH, string MaSach,string SoLuong, DateTime NgayMuon, DateTime NgayTra, string username)
        {
            string MaGD;
            do
            {
                 MaGD = $"GD" + GenerateFourRandomDigits();
            } while (db.LiSuGiaoDich.Find(MaGD) != null);

            Sach sach = db.Saches.Find(MaSach);
            int soLuongMuon = int.Parse(SoLuong);

            if(sach.SoLuong < soLuongMuon)
            {
                return false;
            }
            Muon newMuon = new Muon
            {
                MaMuon = MaMuon,
                MaKH = MaKH,
                MaSach = MaSach,
                SoLuong = soLuongMuon,
                NgayMuon = NgayMuon,
                NgayTra = NgayTra
            };
            if (db.Muons.Find(MaMuon) != null)
            {
                return false; // Trả về false nếu mã mượn đã tồn tại
            }
            db.Muons.Add(newMuon);
            sach.SoLuong -= soLuongMuon;

            db.LiSuGiaoDich.Add(new LiSuGiaoDich
            {
                MaKH = MaKH,
                MaSach = MaSach,
                NgayGD = DateTime.Now,
                TrangThai = "Mượn",
                SoLuong = soLuongMuon,
                Username = username,
                MaGD= MaGD
            });
            db.SaveChanges();
            return true;
        }

        [HttpPut]//trả sách
        public bool TraSach(string MaMuon, string username, string SoLuong)
        {
            Muon existingMuon = db.Muons.Find(MaMuon);
            int soLuongTra = int.Parse(SoLuong);

            if (existingMuon == null)
            {
                return false; // Trả về false nếu mã mượn không tồn tại
            }
            if (soLuongTra > existingMuon.SoLuong)
            {
                return false; // Trả về false nếu số lượng trả lớn hơn số lượng mượn
            }

            db.LiSuGiaoDich.Add(new LiSuGiaoDich
            {
                MaKH = existingMuon.MaKH,
                MaSach = existingMuon.MaSach,
                NgayGD = DateTime.Now,
                TrangThai = "Trả",
                Username = username,
                SoLuong = soLuongTra
            });

            Sach sach = db.Saches.Find(existingMuon.MaSach);
            sach.SoLuong += soLuongTra;
            existingMuon.SoLuong -= soLuongTra;

            if (existingMuon.SoLuong == 0)
            {
                db.Muons.Remove(existingMuon);
            }
            db.SaveChanges();
            return true;
        }
    }
}
