using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
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

        [HttpPost]//Thêm mượn
        public bool AddMuon(string MaMuon, string MaKH, string MaSach, DateTime NgayMuon, DateTime NgayTra)
        {
            Muon newMuon = new Muon
            {
                MaMuon = MaMuon,
                MaKH = MaKH,
                MaSach = MaSach,
                NgayMuon = NgayMuon,
                NgayTra = NgayTra
            };
            if (db.Muons.Find(MaMuon) != null)
            {
                return false; // Trả về false nếu mã mượn đã tồn tại
            }
            db.Muons.Add(newMuon);
            db.SaveChanges();
            return true;
        }

        [HttpDelete]//xoá mượn
        public bool DeleteMuon(string MaMuon)
        {
            Muon existingMuon = db.Muons.Find(MaMuon);
            if (existingMuon == null)
            {
                return false; // Trả về false nếu mã mượn không tồn tại
            }
            db.Muons.Remove(existingMuon);
            db.SaveChanges();
            return true;
        }
    }
}
