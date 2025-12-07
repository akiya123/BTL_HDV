package com.mycompany.quanltthuvien.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mycompany.quanltthuvien.Model.TaiKhoan;

public class TaiKhoanService {
    HttpClient client = HttpClient.newHttpClient();
    String baseUrl = "http://localhost/API_THUVIEN/api/";
    HttpResponse<String> response;


    //Lấy theo username
    public String GetRole(String username) throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "TaiKhoan/GetTaiKhoanByUsername?username=" + URLEncoder.encode(username, StandardCharsets.UTF_8)))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        JSONObject obj = new JSONObject(response.body());
            String role = obj.optString("Role", "");

        return role;
    }

    //Lấy tất cả tài khoản
    public ArrayList<TaiKhoan> GetAllTaiKhoan() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "TaiKhoan"))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<TaiKhoan> danhSachTaiKhoan = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            
            String username = obj.optString("Username", "");
            String password = obj.optString("Pass", "");
            String role = obj.optString("Role", "");
            String sdtTK = obj.optString("SdtTK", "");
            String tenTK = obj.optString("TenTK", "");
            
            TaiKhoan tk = new TaiKhoan(username, password, role, sdtTK, tenTK);
            danhSachTaiKhoan.add(tk);
        }
        
        return danhSachTaiKhoan;
    }


    // Đăng nhập
    public boolean login(String username, String password) throws IOException, InterruptedException {
        // Encode tham số
        String url = baseUrl + "TaiKhoan/Login?" +
                    "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) +
                    "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
        
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            String result = response.body().trim();
            return result.equalsIgnoreCase("true");
        }
        
        return false;
    }

    //Thêm tài khoản
    public boolean addTaiKhoan(TaiKhoan tk) throws IOException, InterruptedException {
       HttpRequest gRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "TaiKhoan?Username="+tk.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        response = client.send(gRequest, HttpResponse.BodyHandlers.ofString());
        if(response.body().equals("true")){
            return false; // Tài khoản đã tồn tại
        }
       String url = baseUrl + "TaiKhoan/AddTaiKhoan?" +
                    "Username=" + URLEncoder.encode(tk.getUsername(), StandardCharsets.UTF_8) +
                    "&Pass=" + URLEncoder.encode(tk.getPass(), StandardCharsets.UTF_8) +
                    "&TenTK=" + URLEncoder.encode(tk.getTenTK(), StandardCharsets.UTF_8) +
                    "&SdtTK=" + URLEncoder.encode(tk.getSdtTK(), StandardCharsets.UTF_8) +
                    "&Role=" + URLEncoder.encode(tk.getRole(), StandardCharsets.UTF_8);
        
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        
        
        if (postResponse.statusCode() == 200) {
            String result = postResponse.body().trim();
            return result.equalsIgnoreCase("true");
        }
        
        return false;
    }

    //Xóa tài khoản
    public boolean deleteTaiKhoan(String username) throws IOException, InterruptedException {

        String url = baseUrl + "TaiKhoan/DeleteTaiKhoan?Username=" + URLEncoder.encode(username, StandardCharsets.UTF_8);
        
        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        
        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        
        if (deleteResponse.statusCode() == 200) {
            String result = deleteResponse.body().trim();
            return result.equalsIgnoreCase("true");
        }
        
        return false;
    }

    //Sửa tài khoản
    public boolean updateTaiKhoan(TaiKhoan tk) throws IOException, InterruptedException {
        HttpRequest gRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "TaiKhoan?Username="+tk.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        response = client.send(gRequest, HttpResponse.BodyHandlers.ofString());
        if(response.body().equals("false")){
            return false; // Tài khoản không tồn tại
        }
        String url = baseUrl + "TaiKhoan/UpdateTaiKhoan?" +
                     "Username=" + URLEncoder.encode(tk.getUsername(), StandardCharsets.UTF_8.toString()) +
                     "&Pass=" + URLEncoder.encode(tk.getPass(), StandardCharsets.UTF_8.toString()) +
                     "&TenTK=" + URLEncoder.encode(tk.getTenTK(), StandardCharsets.UTF_8.toString()) +
                     "&SdtTK=" + URLEncoder.encode(tk.getSdtTK(), StandardCharsets.UTF_8.toString()) +
                     "&Role=" + URLEncoder.encode(tk.getRole(), StandardCharsets.UTF_8.toString());

        System.out.println("URL PUT: " + url);
        
        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody()) 
                .build();
        
        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("Mã trạng thái: " + putResponse.statusCode());
        
        if (putResponse.statusCode() == 200) {
            String result = putResponse.body().trim();
            return result.equalsIgnoreCase("true"); 
        }
        
        return false;
    }
}
