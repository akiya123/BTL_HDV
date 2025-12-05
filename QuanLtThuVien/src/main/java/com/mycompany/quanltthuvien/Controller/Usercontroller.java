/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanltthuvien.Controller;

import com.mycompany.quanltthuvien.Model;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Legion
 */
public class Usercontroller {
    private static final String API_URL = "http://localhost/API_THUVIEN";
    HttpClient client = HttpClient.newHttpClient();

    public boolean Login(String username, String pass){
        TaiKhoan tk = new TaiKhoan(username, pass);
        tk.setUsername(username);
        tk.setPass(pass);

        
        return true;
    }
}
