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

import com.mycompany.quanltthuvien.Model.Muon;

public class MuonService {
    HttpClient client = HttpClient.newHttpClient();
    String baseUrl = "http://localhost/API_THUVIEN/api/";
    HttpResponse<String> response;

    //Lấy hết
    public ArrayList<Muon> GetAllMuon() throws IOException, InterruptedException{
        HttpRequest GRequest = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl+"Muon"))
            .GET()
            .build();

        response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<Muon> danhSachMuon = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String MaMuon = obj.optString("MaMuon", "");
            String MaKH = obj.optString("MaKH", "");
            String MaSach = obj.optString("MaSach", "");
            String NgayMuon = obj.optString("NgayMuon", "");
            String NgayTra = obj.optString("NgayTra", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon,MaKH,MaSach,SoLuong,NgayMuon,NgayTra);
            danhSachMuon.add(muon);
        }
        return danhSachMuon;
    }

    //lấy theo MaKH
    public ArrayList<Muon> GetMuonByMaKH(String MaKH) throws IOException, InterruptedException{
        HttpRequest GRequest = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl+"Muon/GetMuonByMaKH?MaKH="+URLEncoder.encode(MaKH, StandardCharsets.UTF_8)))
            .GET()
            .build();
        
        response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());

        ArrayList<Muon> danhSachMuon = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String MaMuon = obj.optString("MaMuon", "");
            String MaSach = obj.optString("MaSach", "");
            String NgayMuon = obj.optString("NgayMuon", "");
            String NgayTra = obj.optString("NgayTra", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon,MaKH,MaSach,SoLuong,NgayMuon,NgayTra);
            danhSachMuon.add(muon);
        }
        return danhSachMuon;
    }

    //Lấy theo MaMuon
    public Muon GetMuonByMaMuon(String MaMuon) throws IOException, InterruptedException{
        try {
            String url = baseUrl + "Muon/GetMuonByMaMuon?MaMuon=" + 
                        URLEncoder.encode(MaMuon, StandardCharsets.UTF_8);
            
            HttpRequest GRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            System.out.println("URL: " + url);
            
            response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());

            // Parse trực tiếp JSONObject, KHÔNG phải JSONArray
            JSONObject obj = new JSONObject(response.body());

            String MaKH = obj.optString("MaKH", "");
            String MaSach = obj.optString("MaSach", "");
            String NgayMuon = obj.optString("NgayMuon", "");
            String NgayTra = obj.optString("NgayTra", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon, MaKH, MaSach, SoLuong, NgayMuon, NgayTra);
            
            return muon;
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    //Lấy theo MaSach
    public ArrayList<Muon> GetMuonByMaSach(String MaSach) throws IOException, InterruptedException{
        HttpRequest GRequest = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl+"Muon/GetMuonByMaSach?MaSach="+URLEncoder.encode(MaSach, StandardCharsets.UTF_8)))
            .GET()
            .build();

        response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<Muon> danhSachMuon = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String MaMuon = obj.optString("MaMuon", "");
            String MaKH = obj.optString("MaKH", "");
            String NgayMuon = obj.optString("NgayMuon", "");
            String NgayTra = obj.optString("NgayTra", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon,MaKH,MaSach,SoLuong,NgayMuon,NgayTra);
            danhSachMuon.add(muon);
        }
        return danhSachMuon;
    }

    //Lấy theo ngày mượn
    public ArrayList<Muon> GetMuonByNgayMuon(String NgayMuon) throws IOException, InterruptedException{
        HttpRequest GRequest = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl+"Muon/GetMuonByNgayMuon?NgayMuon="+URLEncoder.encode(NgayMuon, StandardCharsets.UTF_8)))
            .GET()
            .build();

        response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());
        
        ArrayList<Muon> danhSachMuon = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String MaMuon = obj.optString("MaMuon", "");
            String MaKH = obj.optString("MaKH", "");
            String MaSach = obj.optString("MaSach", "");
            String NgayTra = obj.optString("NgayTra", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon,MaKH,MaSach,SoLuong,NgayMuon,NgayTra);
            danhSachMuon.add(muon);
        }
        return danhSachMuon;
    }

    //Lấy theo ngày trả 
        public ArrayList<Muon> GetMuonByNgayTra(String NgayTra) throws IOException, InterruptedException{
    // Format: yyyy-MM-dd hoặc yyyy-MM-ddTHH:mm:ss
    String encodedDate = URLEncoder.encode(NgayTra, StandardCharsets.UTF_8);
    
    HttpRequest GRequest = HttpRequest.newBuilder()
        .uri(URI.create(baseUrl + "Muon/GetMuonByNgayTra?NgayTra=" + encodedDate))
        .GET()
        .build();

    response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());
    
    ArrayList<Muon> danhSachMuon = new ArrayList<>();
    
    try {
        JSONArray jsonArray = new JSONArray(response.body());
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            String MaMuon = obj.optString("MaMuon", "");
            String MaKH = obj.optString("MaKH", "");
            String MaSach = obj.optString("MaSach", "");
            String NgayMuon = obj.optString("NgayMuon", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon, MaKH, MaSach, SoLuong, NgayMuon, NgayTra);
            danhSachMuon.add(muon);
        }
    } catch (Exception e) {
        System.err.println("Parse error: " + e.getMessage());
    }
    
    return danhSachMuon;
}

    //Mượn sách 
public boolean MuonSach(Muon muon, String username) throws IOException, InterruptedException{
    String url = baseUrl+"Muon/AddMuon?MaMuon="
        + URLEncoder.encode(muon.getMaMuon(), StandardCharsets.UTF_8)
        + "&MaKH=" + URLEncoder.encode(muon.getMaKH(), StandardCharsets.UTF_8)
        + "&MaSach=" + URLEncoder.encode(muon.getMaSach(), StandardCharsets.UTF_8)
        + "&SoLuong=" + muon.getSoLuong()  // THÊM DẤU & Ở ĐÂY
        + "&NgayMuon=" + URLEncoder.encode(muon.getNgayMuon().toString(), StandardCharsets.UTF_8)
        + "&NgayTra=" + URLEncoder.encode(muon.getNgayTra().toString(), StandardCharsets.UTF_8)
        + "&username=" + URLEncoder.encode(username, StandardCharsets.UTF_8);

    HttpRequest postRequest = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .POST(HttpRequest.BodyPublishers.noBody())
        .build();
        
    response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
    
    System.out.println("Status: " + response.statusCode());
    System.out.println("Response: " + response.body());
    
    return response.statusCode() == 200 && response.body().trim().equalsIgnoreCase("true");
}

    //Trả sách
    public boolean TraSach(String MaMuon, String username, int SoLuong) throws IOException, InterruptedException{
        String url = baseUrl+"Muon/TraSach?MaMuon="
            +URLEncoder.encode(MaMuon, StandardCharsets.UTF_8)
            +"&Username="+URLEncoder.encode(username, StandardCharsets.UTF_8)
            +"&SoLuong="+SoLuong;

            System.out.println(url);
        HttpRequest PuRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .PUT(HttpRequest.BodyPublishers.noBody())
            .build();

        response = client.send(PuRequest, HttpResponse.BodyHandlers.ofString());
        if( response.statusCode() == 200 ) {
            return true;
        }
        return false;
    }

    //Mất sách
    public boolean MatSach(String MaMuon, String username, int SoLuong) throws IOException, InterruptedException{
        String url = baseUrl+"Muon/MatSach?MaMuon="
            +URLEncoder.encode(MaMuon, StandardCharsets.UTF_8)
            +"&Username="+URLEncoder.encode(username, StandardCharsets.UTF_8)
            +"&SoLuongMat="+SoLuong;
            
        HttpRequest PuRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .PUT(HttpRequest.BodyPublishers.noBody())
            .build();

        response = client.send(PuRequest, HttpResponse.BodyHandlers.ofString());
        if( response.statusCode() == 200 ) {
            return true;
        }
        return false;
    }
}
