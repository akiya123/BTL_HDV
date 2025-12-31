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

import com.mycompany.quanltthuvien.Model.LiSuGiaoDich;

public class LiSuGDService {
    HttpClient client = HttpClient.newHttpClient();
    String baseUrl = "http://localhost/API_THUVIEN/api/";
    HttpResponse<String> response;

    //Lấy tất cả lịch sử giao dịch
    public ArrayList<LiSuGiaoDich> GetAllLiSuGD() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "LiSuGiaoDich"))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<LiSuGiaoDich> danhSachLiSuGD = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maGD = obj.optString("MaGD", "");
            String username = obj.optString("Username", "");
            String maKH = obj.optString("MaKH", "");
            String ngayGD = obj.optString("NgayGD", "");
            String maSach = obj.optString("MaSach", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String trangThai = obj.optString("TrangThai", "");

            LiSuGiaoDich liSuGiaoDich = new LiSuGiaoDich(maGD, username, maKH, ngayGD, maSach, soLuong, trangThai);
            danhSachLiSuGD.add(liSuGiaoDich);

        }
        return danhSachLiSuGD;
    }

    //Lấy theo TaiKhoan
    public ArrayList<LiSuGiaoDich> GetLiSuGDByTaiKhoan(String username) throws IOException, InterruptedException {
        String url = baseUrl + "LiSuGiaoDich/GetLSuGiaoDichByUsername?Username=" + URLEncoder.encode(username, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<LiSuGiaoDich> danhSachLiSuGD = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maGD = obj.optString("MaGD", "");
            String maKH = obj.optString("MaKH", "");
            String ngayGD = obj.optString("NgayGD", "");
            String maSach = obj.optString("MaSach", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String trangThai = obj.optString("TrangThai", "");

            LiSuGiaoDich liSuGiaoDich = new LiSuGiaoDich(maGD, username, maKH, ngayGD, maSach, soLuong, trangThai);
            danhSachLiSuGD.add(liSuGiaoDich);

        }
        return danhSachLiSuGD;
    }

    //Lấy theo Mã sách
    public ArrayList<LiSuGiaoDich> GetLiSuGDByMaSach(String maSach) throws IOException, InterruptedException {
        String url = baseUrl + "LiSuGiaoDich/GetLSuGiaoDichByMaSach?MaSach=" + URLEncoder.encode(maSach, StandardCharsets.UTF_8);;

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<LiSuGiaoDich> danhSachLiSuGD = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maGD = obj.optString("MaGD", "");
            String username = obj.optString("Username", "");
            String maKH = obj.optString("MaKH", "");
            String ngayGD = obj.optString("NgayGD", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String trangThai = obj.optString("TrangThai", "");

            LiSuGiaoDich liSuGiaoDich = new LiSuGiaoDich(maGD, username, maKH, ngayGD, maSach, soLuong, trangThai);
            danhSachLiSuGD.add(liSuGiaoDich);

        }
        return danhSachLiSuGD;
    }

    //Lấy theo ngày giao dịch
    public ArrayList<LiSuGiaoDich> GetLiSuGDByNgayGD(String NgayGDCuoi, String NgayGDDau) throws IOException, InterruptedException {
        
        String url = baseUrl + "LiSuGiaoDich/GetLSuGiaoDichByNgayGD?NgayGDCuoi=" 
        +NgayGDCuoi
        + "&NgayGDDau=" + NgayGDDau;
            System.out.println(url);
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<LiSuGiaoDich> danhSachLiSuGD = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maGD = obj.optString("MaGD", "");
            String username = obj.optString("Username", "");
            String maKH = obj.optString("MaKH", "");
            String ngayGD = obj.optString("NgayGD", "");
            String maSach = obj.optString("MaSach", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String trangThai = obj.optString("TrangThai", "");

            LiSuGiaoDich liSuGiaoDich = new LiSuGiaoDich(maGD, username, maKH, ngayGD, maSach, soLuong, trangThai);
            danhSachLiSuGD.add(liSuGiaoDich);
        }
        return danhSachLiSuGD;
    }

    //Lây theo trạng thái
    public ArrayList<LiSuGiaoDich> GetLiSuGDByTrangThai(String trangThai) throws IOException, InterruptedException {
        String url = baseUrl + "LiSuGiaoDich/GetLSuGiaoDichByTrangThai?TrangThai=" + URLEncoder.encode(trangThai, StandardCharsets.UTF_8);;

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<LiSuGiaoDich> danhSachLiSuGD = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maGD = obj.optString("MaGD", "");
            String username = obj.optString("Username", "");
            String maKH = obj.optString("MaKH", "");
            String ngayGD = obj.optString("NgayGD", "");
            String maSach = obj.optString("MaSach", "");
            int soLuong = obj.optInt("SoLuong", 0);

            LiSuGiaoDich liSuGiaoDich = new LiSuGiaoDich(maGD, username, maKH, ngayGD, maSach, soLuong, trangThai);
            danhSachLiSuGD.add(liSuGiaoDich);
        }
        return danhSachLiSuGD;
    }

    //Xóa lịch sử giao dịch 3 năm một lần
    public void DeleteLiSuGD() throws IOException, InterruptedException{
        String url = baseUrl+"LiSuGiaoDich/DeleteLiSuGiaoDichByYear";

        HttpRequest delRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .DELETE()
            .build();
        response = client.send(delRequest, HttpResponse.BodyHandlers.ofString());
    }
}
