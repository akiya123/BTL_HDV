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

import com.mycompany.quanltthuvien.Model.Sach;


public class SachService {
    HttpClient client = HttpClient.newHttpClient();
    String baseUrl = "http://localhost/API_THUVIEN/api/";
    HttpResponse<String> response;

    //Lấy tất cả sách
    public ArrayList<Sach> GetAllSach() throws IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "Sach"))
                .GET()
                .build();
        
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<Sach> danhSachSach = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maSach = obj.optString("MaSach", "");
            String tenSach = obj.optString("TenSach", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String tacGia = obj.optString("TacGia", "");
            String maTheLoai = obj.optString("MaTheLoai", "");

            Sach sach = new Sach(maSach, tenSach, soLuong, tacGia, maTheLoai);
            danhSachSach.add(sach);
        }
        return danhSachSach;
    }

    // Thêm sách
    public boolean AddSach(Sach sach) throws IOException, InterruptedException {
        String url = baseUrl + "Sach/AddSach?" +
        "MaSach=" + URLEncoder.encode(sach.getMaSach(), StandardCharsets.UTF_8) +
        "&TenSach=" + URLEncoder.encode(sach.getTenSach(), StandardCharsets.UTF_8) +
        "&SoLuong=" + sach.getSoLuong() +
        "&TacGia=" + URLEncoder.encode(sach.getTacGia(), StandardCharsets.UTF_8) +
        "&MaTheLoai=" + URLEncoder.encode(sach.getMaTheLoai(), StandardCharsets.UTF_8);

                System.err.println("URL PUT: " + url);
        HttpRequest postRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

         if( postResponse.statusCode() == 200 ) {
            return true;
        }
        return false;
    }

    // Xóa sách
    public boolean DeleteSach(String maSach) throws IOException, InterruptedException {
        String url = baseUrl + "Sach?MaSach=" + maSach;

        HttpRequest deleteRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

        if( deleteResponse.statusCode() == 200 ) {
            return true;
        }
        return false;
    }

    //sửa sách
    public boolean UpdateSach(Sach sach) throws IOException, InterruptedException {
        String url = baseUrl + "Sach/UpdateSach?" +
            "MaSach=" + URLEncoder.encode(sach.getMaSach(), StandardCharsets.UTF_8) +
            "&TenSach=" + URLEncoder.encode(sach.getTenSach(), StandardCharsets.UTF_8) +
            "&SoLuong=" + sach.getSoLuong() +
            "&TacGia=" + URLEncoder.encode(sach.getTacGia(), StandardCharsets.UTF_8) +
            "&MaTheLoai=" + URLEncoder.encode(sach.getMaTheLoai(), StandardCharsets.UTF_8);

        System.out.println("URL PUT: " + url);


        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());

        if( putResponse.statusCode() == 200 ) {
            return true;
        }
        return false;
    }

    //Tim kiếm sách theo tên // lỗi
    public ArrayList<Sach> TimKiemSachTheoTen(String tenSach) throws IOException, InterruptedException {
        String url = baseUrl + "Sach/SearchSachByName?TenSach=" + URLEncoder.encode(tenSach, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        ArrayList<Sach> danhSachSach = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maSach = obj.optString("MaSach", "");
            String tenSachResult = obj.optString("TenSach", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String tacGia = obj.optString("TacGia", "");
            String maTheLoai = obj.optString("MaTheLoai", "");

            Sach sach = new Sach(maSach, tenSachResult, soLuong, tacGia, maTheLoai);
            danhSachSach.add(sach);
        }
        return danhSachSach;
    }

    //Lấy sách theo mã
    public Sach GetSachByMa(String maSach) throws IOException, InterruptedException {
        String url = baseUrl + "Sach/GetSachById?MaSach=" + URLEncoder.encode(maSach, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        JSONObject obj = new JSONObject(response.body());

        String tenSach = obj.optString("TenSach", "");
        int soLuong = obj.optInt("SoLuong", 0);
        String tacGia = obj.optString("TacGia", "");
        String maTheLoai = obj.optString("MaTheLoai", "");

        return new Sach(maSach, tenSach, soLuong, tacGia, maTheLoai);
    }

    //Lấy theo thể loại
    public ArrayList<Sach> GetSachByTheLoai(String maTheLoai) throws IOException, InterruptedException {
        String url = baseUrl + "Sach/GetSachByTheLoai?MaTheLoai=" + URLEncoder.encode(maTheLoai, StandardCharsets.UTF_8);

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        ArrayList<Sach> danhSachSach = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String maSach = obj.optString("MaSach", "");
            String tenSach = obj.optString("TenSach", "");
            int soLuong = obj.optInt("SoLuong", 0);
            String tacGia = obj.optString("TacGia", "");

            Sach sach = new Sach(maSach, tenSach, soLuong, tacGia, maTheLoai);
            danhSachSach.add(sach);
        }
        return danhSachSach;
    }

    //Test
    public static void main(String[] args) {
        SachService sachService = new SachService();
        try {
            sachService.DeleteSach("S004");
            ArrayList<Sach> danhSachSach = sachService.TimKiemSachTheoTen("sao");
            for (Sach sach : danhSachSach) {
                System.out.println("Mã Sách: " + sach.getMaSach());
                System.out.println("Tên Sách: " + sach.getTenSach());
                System.out.println("Số Lượng: " + sach.getSoLuong());
                System.out.println("Tác Giả: " + sach.getTacGia());
                System.out.println("Mã Thể Loại: " + sach.getMaTheLoai());
                System.out.println("---------------------------");
            }
        } catch (IOException | InterruptedException e) {
            
        }
    }

}
