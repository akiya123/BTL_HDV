package com.mycompany.quanltthuvien.Controller;

import java.io.IOException;

import com.mycompany.quanltthuvien.Service.TaiKhoanService;

public class LoginController {
    TaiKhoanService tks = new TaiKhoanService();
    //Đăng nhập trả vể Role
    public String login(String username, String pass){
        try {
            if(tks.login(username, pass)){
                return tks.GetRole(username);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
