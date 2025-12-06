using API_QLYTHuVien.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace API_QLYTHuVien.Controllers
{
    public class TaiKhoanController : ApiController
    {
        API_ThuVienEntities db = new API_ThuVienEntities();

        public TaiKhoanController()
        {
            db.Configuration.ProxyCreationEnabled = false;
            db.Configuration.LazyLoadingEnabled = false;
        }

        [HttpGet]//lấy hết
        public IEnumerable<TaiKhoan> GetAllTaiKhoan()
        {
            return db.TaiKhoans.ToList();
        }
        [HttpGet]//Lấy theo username
        public bool GetTaiKhoanByUsername(string username)
        {
            TaiKhoan tks = db.TaiKhoans.FirstOrDefault(tk => tk.Username == username);
            if(tks == null)
            {
                return false;
            }
            return true;
        }

        [HttpGet]//Đăng nhập
        public bool Login(string username, string password)
        {
            var user = db.TaiKhoans.FirstOrDefault(tk => tk.Username == username && tk.Pass == password);
            if (user == null)
            {
                return false;
            }
            return true;
        }

        [HttpPost]//Thêm tài khoản
        public bool AddTaiKhoan(string Username, string Pass, string TenTK, string SdtTK,string Role)
        {
            TaiKhoan newTaiKhoan = new TaiKhoan
            {
                Username = Username,
                Pass = Pass,
                TenTK = TenTK,
                SdtTK = SdtTK,
                Role = Role
            };
            db.TaiKhoans.Add(newTaiKhoan);
            db.SaveChanges();
            return true;
        }
        
        
        [HttpPut]//Đổi mật khẩu
        public bool UpdateTaiKhoan(string Username, string Pass, string TenTK, string SdtTK, string Role)
        {
            TaiKhoan existingTaiKhoan = db.TaiKhoans.Find(Username);
            if (existingTaiKhoan == null)
            {
                return false;
            }

            existingTaiKhoan.Pass = Pass;
            existingTaiKhoan.TenTK = TenTK;
            existingTaiKhoan.SdtTK = SdtTK;
            existingTaiKhoan.Role = Role ;

            db.SaveChanges();
            return true;
        }
        [HttpDelete]
        public bool DeleteTaiKhoan(string Username)
        {
            if (Username == "admin")
            {
                return false; // Không cho phép xóa tài khoản admin
            }
            TaiKhoan existingTaiKhoan = db.TaiKhoans.Find(Username);
            if (existingTaiKhoan == null)
            {
                return false; // Trả về false nếu tài khoản không tồn tại
            }
            db.TaiKhoans.Remove(existingTaiKhoan);
            db.SaveChanges();
            return true;
        }
    }
}
