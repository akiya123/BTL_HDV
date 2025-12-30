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

import com.mycompany.quanltthuvien.Model.TheLoai;

public class TheLoaiService {
    HttpClient client = HttpClient.newHttpClient();
    String baseUrl = "http://localhost/API_THUVIEN/api/";
    HttpResponse<String> response;

    //Lấy tất cả thể loại
    public ArrayList<TheLoai> GetAllTheLoai() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "TheLoai"))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<TheLoai> danhSachTheLoai = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maTheLoai = obj.optString("MaTheLoai", "");
            String tenTheLoai = obj.optString("TenTheLoai", "");

            TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
            danhSachTheLoai.add(theLoai);
        }
        return danhSachTheLoai;
    }

    // Thêm thể loại
    public boolean AddTheLoai(TheLoai theLoai) throws IOException, InterruptedException {
        String url = baseUrl + "TheLoai/AddTheLoai?" +
        "MaTheLoai=" + URLEncoder.encode(theLoai.getMaTheLoai(), StandardCharsets.UTF_8) +
        "&TenTheLoai=" + URLEncoder.encode(theLoai.getTenTheLoai(), StandardCharsets.UTF_8);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        
        response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }

    // Xóa thể loại
    public boolean DeleteTheLoai(String maTheLoai) throws IOException, InterruptedException {
        String url = baseUrl + "TheLoai/DeleteTheLoai?" +
        "MaTheLoai=" + maTheLoai;

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        
        response = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }

    // Sửa thể loại
    public boolean UpdateTheLoai(TheLoai theLoai) throws IOException, InterruptedException {
        String url = baseUrl + "TheLoai/UpdateTheLoai?" +
        "MaTheLoai=" + URLEncoder.encode(theLoai.getMaTheLoai(), StandardCharsets.UTF_8) +
        "&TenTheLoai=" + URLEncoder.encode(theLoai.getTenTheLoai(), StandardCharsets.UTF_8);

        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        
        response = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }

    // Tìm thể loại theo mã
    public TheLoai GetTheLoaiByMa(String maTheLoai) throws IOException, InterruptedException
    {
        String url = baseUrl + "TheLoai?MaTheLoai=" + URLEncoder.encode(maTheLoai, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        JSONObject obj = new JSONObject(response.body());

        String tenTheLoai = obj.optString("TenTheLoai", "");

        TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
        return theLoai;
    }

    //Tìm thể loại theo tên
    public ArrayList<TheLoai> GetTheLoaiByTen(String tenTheLoai) throws IOException, InterruptedException
    {
        String url = baseUrl + "TheLoai?TenTheLoai=" + URLEncoder.encode(tenTheLoai, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        ArrayList<TheLoai> danhSachTheLoai = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maTheLoai = obj.optString("MaTheLoai", "");
            tenTheLoai = obj.optString("TenTheLoai", "");

            TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
            danhSachTheLoai.add(theLoai);
        }
        
        return danhSachTheLoai;
    }
}
