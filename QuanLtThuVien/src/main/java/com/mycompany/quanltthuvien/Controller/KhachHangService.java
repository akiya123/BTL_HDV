package com.mycompany.quanltthuvien.Controller;

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

    //Thêm sách
    public boolean AddKhachHang(KhachHang khachHang) throws IOException, InterruptedException {
        String url = baseUrl + "KhachHang/AddKhachHang?" +
        "MaKH=" + URLEncoder.encode(khachHang.getMaKH(), StandardCharsets.UTF_8) +
        "&TenKH=" + URLEncoder.encode(khachHang.getTenKH(), StandardCharsets.UTF_8) +
        "&SdtKH=" + URLEncoder.encode(khachHang.getSdtKH(), StandardCharsets.UTF_8);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        
        response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }

    //Sửa Khách hàng
    public boolean UpdateKhachHang(KhachHang khachHang) throws IOException, InterruptedException {
        String url = baseUrl + "KhachHang/UpdateKhachHang?" +
        "MaKH=" + URLEncoder.encode(khachHang.getMaKH(), StandardCharsets.UTF_8) +
        "&TenKH=" + URLEncoder.encode(khachHang.getTenKH(), StandardCharsets.UTF_8) +
        "&SdtKH=" + URLEncoder.encode(khachHang.getSdtKH(), StandardCharsets.UTF_8);

        System.err.println("URL PUT: " + url);

        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        
        response = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }

    // Xóa Khách hàng
    public boolean DeleteKhachHang(String maKH) throws IOException, InterruptedException {
        String url = baseUrl + "KhachHang?MaKH=" + URLEncoder.encode(maKH, StandardCharsets.UTF_8);


        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();
        
        response = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode() == 200;
    }

    // Tìm khách hàng theo mã
    public KhachHang GetKhachHangByMa(String maKH) throws IOException, InterruptedException{
        String url = baseUrl + "KhachHang?MaKH=" + URLEncoder.encode(maKH, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        JSONObject obj = new JSONObject(response.body());

        String tenKH = obj.optString("TenKH", "");
        String sdtKH = obj.optString("SdtKH", "");

        KhachHang khachHang = new KhachHang(maKH, tenKH, sdtKH);
        return khachHang;
    }

    //Tìm khách hàng theo tên // lỗi
    public ArrayList<KhachHang> TimKiemKhachHangTheoTen(String tenKH) throws IOException, InterruptedException {
        String url = baseUrl + "KhachHang/SearchKhachHangByName?TenKH=" + URLEncoder.encode(tenKH, StandardCharsets.UTF_8);

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
    }

    //Tìm khách theo số điện thoại
    public KhachHang TimKiemKhachHangTheoSDT(String sdtKH) throws IOException, InterruptedException {
        String url = baseUrl + "KhachHang?SdtKH=" + URLEncoder.encode(sdtKH, StandardCharsets.UTF_8);

    HttpRequest getRequest = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

    String body = response.body();
    if (body == null || body.isEmpty() || body.equals("null")) {
        return null;
    }

    // Nếu trả về mảng
    if (body.trim().startsWith("[")) {
        JSONArray arr = new JSONArray(body);
        if (arr.length() == 0) return null;
        JSONObject obj = arr.getJSONObject(0);
        String maKH = obj.optString("MaKH", "");
        String tenKH = obj.optString("TenKH", "");
        String sdt = obj.optString("SdtKH", "");
        if (maKH.isEmpty()) return null;
        return new KhachHang(maKH, tenKH, sdt);
    }

    // Nếu trả về object
    JSONObject obj = new JSONObject(body);
    String maKH = obj.optString("MaKH", "");
    String tenKH = obj.optString("TenKH", "");
    String sdt = obj.optString("SdtKH", "");
    if (maKH.isEmpty()) return null;
    return new KhachHang(maKH, tenKH, sdt);
    }
}
