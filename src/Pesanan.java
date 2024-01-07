import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

//interface
public class Pesanan implements InputData {

    String nama_pelanggan, tgl_pesanan, jam_pesanan, nama_kasir, nama_mainan;
    int i = 1, no_pesanan, harga_mainan, id_mainan, kembalian, id_pelanggan, id_kasir;
    Boolean pilihan;

    Scanner input = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_mainan";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static Connection conn2;
    static Statement stmt2;
    static ResultSet rs2;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input2 = new BufferedReader(inputStreamReader);

    // pengolahan database
    @Override
    public String tampilData() {
        // exception
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);

            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // buat objek statement
            stmt = conn.createStatement();

            // buat query ke database
            String sql = "SELECT * FROM pesanan";

            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            System.out.println("===========================");
            System.out.println("| D A T A    P E S A N A N |");
            System.out.println("----------------------------");
            while (rs.next()) {
                System.out.println("No Order\t : " + rs.getInt("no_pesanan"));
                System.out.println("Date\t : " + rs.getString("tgl_pesanan"));
                System.out.println("Time\t : " + rs.getString("jam_pesanan"));
                System.out.println("Id Customer\t : " + rs.getString("id_pelanggan"));
                System.out.println("Id Cashier\t : " + rs.getString("id_kasir"));
                System.out.println("Total Price\t : " + rs.getString("total_bayar"));
                System.out.println("Return Money\t : " + rs.getString("kembalian"));
                System.out.println("Id Mainan\t : " + rs.getString("id_mainan"));
                System.out.println("Total Amount\t : " + rs.getString("total_harga"));
                System.out.println("Total Belanja\t : " + rs.getString("total_belanja") + "\n");
            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Void hapusData() {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("============================");
            System.out.println("DELETE DATA PESANAN");
            System.out.println("============================");
            System.out.print("Enter No. Pesanan that you want to delete: ");
            int no_pesanan = Integer.parseInt(input2.readLine());

            String sql = "DELETE FROM pesanan WHERE no_pesanan=%d";
            sql = String.format(sql, no_pesanan);

            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Pesanan Successfully Deleted");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Void EditData() {
        System.out.println("Sorry, completed orders cannot be edited");
        return null;
    }

    public String getNamaPelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
        return nama_pelanggan;
    }

    public String getNamaKasir(String nama_kasir) {
        this.nama_kasir = nama_kasir;
        return nama_kasir;
    }

    public String getNamaMainan(int id_mainan) {
        this.id_mainan = id_mainan;
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT mainan.nama_mainan FROM mainan WHERE mainan.id_mainan = %d";
            sql = String.format(sql, id_mainan);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.nama_mainan = rs.getString("nama_mainan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nama_mainan;
    }

    public String getJam(String jam_pesanan) {
        this.jam_pesanan = jam_pesanan;

        return jam_pesanan;
    }

    public int getNoPesanan(String jam_pesanan) {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT pesanan.no_pesanan FROM pesanan WHERE pesanan.jam_pesanan =jam_pesanan";
            sql = String.format(sql, jam_pesanan);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.no_pesanan = rs.getInt("no_pesanan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no_pesanan;
    }

    public int getIdPelanggan(String nama_pelanggan) {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT pelanggan.id_pelanggan FROM pelanggan WHERE pelanggan.nama_pelanggan = nama_pelanggan";
            sql = String.format(sql, nama_pelanggan);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.id_pelanggan = rs.getInt("id_pelanggan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_pelanggan;
    }

    public int getIdKasir(String nama_kasir) {
        this.nama_kasir = nama_kasir;
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT kasir.id_kasir FROM kasir WHERE kasir.nama_kasir = nama_kasir";
            sql = String.format(sql, nama_kasir);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.id_kasir = rs.getInt("id_kasir");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_kasir;
    }

    public int getHargaMainan(int id_mainan) {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT mainan.harga_mainan FROM mainan WHERE mainan.id_mainan=%d";
            sql = String.format(sql, id_mainan);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                harga_mainan = rs.getInt("harga_mainan");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return harga_mainan;
    }

    public int getNamaMainan(Integer integer) {
        return 0;
    }
}