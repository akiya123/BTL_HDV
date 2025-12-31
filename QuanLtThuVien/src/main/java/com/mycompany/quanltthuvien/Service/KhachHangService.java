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

import com.mycompany.quanltthuvien.Model.KhachHang;

public class KhachHangService {
    HttpClient client = HttpClient.newHttpClient();
    String baseUrl = "http://localhost/API_THUVIEN/api/";
    HttpResponse<String> response;

    //Lấy tất cả khách hàng
    public ArrayList<KhachHang> GetAllKhachHang() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "KhachHang"))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maKH = obj.optString("MaKH", "");
            String tenKH = obj.optString("TenKH", "");
            String sdtKH = obj.optString("SdtKH", "");

            KhachHang khachHang = new KhachHang(maKH, tenKH, sdtKH);
            danhSachKhachHang.add(khachHang);
        }
        return danhSachKhachHang;
    }


    public static void main(String[] args) {
        try {
            KhachHangService khs = new KhachHangService();
            // khs.AddKhachHang(new KhachHang("KH001", "Nguyen Van A", "0123456789"));
            khs.GetKhachHangBySdt("0123456789");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    //Thêm khách hàng
    public boolean AddKhachHang(KhachHang khachHang) throws IOException, InterruptedException {
        try {
            String url = baseUrl + "KhachHang/AddKhachHang?" +
                "MaKH=" + URLEncoder.encode(khachHang.getMaKH(), StandardCharsets.UTF_8) +
                "&TenKH=" + URLEncoder.encode(khachHang.getTenKH(), StandardCharsets.UTF_8) +
                "&SdtKH=" + URLEncoder.encode(khachHang.getSdtKH(), StandardCharsets.UTF_8);

            System.out.println("URL POST: " + url);

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();
            
            response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status: " + response.statusCode());
            System.out.println("Response: " + response.body());
            
            if (response.statusCode() == 200) {
                String result = response.body().trim();
                return result.equalsIgnoreCase("true");
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //Sửa Khách hàng
    public boolean UpdateKhachHang(KhachHang khachHang) throws IOException, InterruptedException {
        try {
            String url = baseUrl + "KhachHang/UpdateKhachHang?" +
                "MaKH=" + URLEncoder.encode(khachHang.getMaKH(), StandardCharsets.UTF_8) +
                "&TenKH=" + URLEncoder.encode(khachHang.getTenKH(), StandardCharsets.UTF_8) +
                "&SdtKH=" + URLEncoder.encode(khachHang.getSdtKH(), StandardCharsets.UTF_8);


            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();
            
            response = client.send(putRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String result = response.body().trim();
                return result.equalsIgnoreCase("true");
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Xóa Khách hàng 
    public boolean DeleteKhachHang(String maKH) throws IOException, InterruptedException {
        try {
            String url = baseUrl + "KhachHang/DeleteKhachHang?MaKH=" + 
                        URLEncoder.encode(maKH, StandardCharsets.UTF_8);

            HttpRequest deleteRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();
            
            response = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                String result = response.body().trim();
                return result.equalsIgnoreCase("true");
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Tìm khách hàng theo mã 
    public KhachHang GetKhachHangByMa(String maKH) throws IOException, InterruptedException{
        try {
            String url = baseUrl + "KhachHang/GetKhachHangById?MaKH=" + 
                        URLEncoder.encode(maKH, StandardCharsets.UTF_8);


            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            
            response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            JSONObject obj = new JSONObject(response.body());

            String tenKH = obj.optString("TenKH", "");
            String sdtKH = obj.optString("SdtKH", "");

            return new KhachHang(maKH, tenKH, sdtKH);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //Tìm khách hàng theo tên
    public ArrayList<KhachHang> GetKhachHangByTen(String tenKH) throws IOException, InterruptedException {
        try {
        String url = baseUrl + "KhachHang/SearchKhachHangByName?keyword=" + 
                    URLEncoder.encode(tenKH, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maKH = obj.optString("MaKH", "");
            String tenKHResult = obj.optString("TenKH", "");
            String sdtKH = obj.optString("SdtKH", "");

            KhachHang khachHang = new KhachHang(maKH, tenKHResult, sdtKH);
            danhSachKhachHang.add(khachHang);
        }
        return danhSachKhachHang;
    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace();
        return new ArrayList<>();
    }
    }

    //Tìm khách theo số điện thoại
    public KhachHang GetKhachHangBySdt(String sdt) throws IOException, InterruptedException {
        try {
            String url = baseUrl + "KhachHang/SearchKhachHangBySDT?sdt=" + 
                    URLEncoder.encode(sdt, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        JSONObject obj = new JSONObject(response.body());

            String maKH = obj.optString("MaKH", "");
            String tenKHResult = obj.optString("TenKH", "");
            String sdtKH = obj.optString("SdtKH", "");

            KhachHang khachHang = new KhachHang(maKH, tenKHResult, sdtKH);
            
            return khachHang;
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
