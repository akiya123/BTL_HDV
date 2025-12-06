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

    //Lấy theo MaMuon // lỗi
    public Muon GetMuonByMaMuon(String MaMuon) throws IOException, InterruptedException{
        HttpRequest GRequest = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl+"Muon/GetMuonByMaMuon?MaMuon="+MaMuon))
            .GET()
            .build();

        System.out.println();
        response = client.send(GRequest, HttpResponse.BodyHandlers.ofString());
        
        JSONArray jsonArray = new JSONArray(response.body());
        
            JSONObject obj = jsonArray.getJSONObject(0);

            String MaKH = obj.optString("MaKH", "");
            String MaSach = obj.optString("MaSach", "");
            String NgayMuon = obj.optString("NgayMuon", "");
            String NgayTra = obj.optString("NgayTra", "");
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon,MaKH,MaSach,SoLuong,NgayMuon,NgayTra);
            
        return muon;
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

    //Lấy theo ngày trả // lỗi
        public ArrayList<Muon> GetMuonByNgayTra(String NgayTra) throws IOException, InterruptedException{
        HttpRequest GRequest = HttpRequest.newBuilder()
            .uri(URI.create(baseUrl+"Muon/GetMuonByNgayTra?NgayTra="+URLEncoder.encode(NgayTra, StandardCharsets.UTF_8)))
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
            int SoLuong = obj.optInt("SoLuong", 0);

            Muon muon = new Muon(MaMuon,MaKH,MaSach,SoLuong,NgayMuon,NgayTra);
            danhSachMuon.add(muon);
        }
        return danhSachMuon;
    }

    //Mượn sách // lỗi
    public boolean MuonSach(Muon muon, String username) throws IOException, InterruptedException{
        String url = baseUrl+"Muon/AddMuon?MaMuon="
            +URLEncoder.encode(muon.getMaMuon(), StandardCharsets.UTF_8)
            +URLEncoder.encode(muon.getMaKH(), StandardCharsets.UTF_8)
            +URLEncoder.encode(muon.getMaSach(), StandardCharsets.UTF_8)
            +muon.getSoLuong()
            +URLEncoder.encode(muon.getNgayMuon().toString(), StandardCharsets.UTF_8)
            +URLEncoder.encode(muon.getNgayTra().toString(), StandardCharsets.UTF_8)
            +URLEncoder.encode(username, StandardCharsets.UTF_8);

        HttpRequest PoRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();
        response = client.send(PoRequest, HttpResponse.BodyHandlers.ofString());
        if( response.statusCode() == 200 ) {
            return true;
        }
        return false;
    }

    //Trả sách // lỗi
    public boolean TraSach(String MaMuon, String username, int SoLuong) throws IOException, InterruptedException{
        String url = baseUrl+"Muon/AddMuon?MaMuon="
            +URLEncoder.encode(MaMuon, StandardCharsets.UTF_8)
            +URLEncoder.encode(username, StandardCharsets.UTF_8)
            +SoLuong;

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

    //test
    public static void main(String[] args) {
        try {
            MuonService m = new MuonService();
            // m.TraSach("M002", "Hoan2", 1);
            Muon arr = m.GetMuonByMaMuon("M002");
            Muon elem = arr;
            // for (Muon elem : arr) {
                System.out.println(elem.getMaMuon()+elem.getMaKH()+elem.getMaSach()+elem.getSoLuong()+elem.getNgayMuon()+elem.getNgayTra());
            // }
        } catch (Exception e) {
        }
    }
}
