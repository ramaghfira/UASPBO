import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//interface
public class Mainan implements InputData {

    int id;
    String nama_mainan, jenis_mainan;
    int harga_mainan, id_mainan;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_mainan";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static ResultSet rs2;
    static Statement stmt2;
    static Connection conn2;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    // pengolahan database
    @Override
    public String tampilData() {
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);

            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // buat objek statement
            stmt = conn.createStatement();

            // buat query ke database
            String sql = "SELECT * FROM mainan";

            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            System.out.println("=======================");
            System.out.println(" D A T A  M A I N A N ");
            System.out.println("=======================");

            // perulangan
            while (rs.next()) {
                System.out.println("Id Mainan\t : " + rs.getInt("id_mainan"));
                System.out.println("Nama Mainan\t : " + rs.getString("nama_mainan"));
                System.out.println("Harga Mainan\t : " + rs.getString("harga_mainan"));
                System.out.println("Jenis Mainan\t : " + rs.getString("jenis_mainan") + "\n");
            }

            stmt.close();
            conn.close();

            // exception
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
            System.out.println("DELETE DATA Mainan");
            System.out.println("----------------------------");
            System.out.print("Enter the id Mainan that will be deleted : ");
            int id_mainan = Integer.parseInt(input.readLine());

            String sql = "DELETE FROM mainan WHERE id_mainan=%d";
            sql = String.format(sql, id_mainan);

            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Mainan Successfully deleted");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Void EditData() {
        // exception
        try {
            conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt2 = conn2.createStatement();

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("============================");
            System.out.println("EDIT DATA MAINAN");
            System.out.println("----------------------------");
            System.out.print("Enter the id Mainan that you want to edit : ");
            this.id_mainan = Integer.parseInt(input.readLine());

            String sql = "SELECT mainan.id_mainan FROM mainan WHERE mainan.id_mainan=%d";
            sql = String.format(sql, id_mainan);
            rs2 = stmt2.executeQuery(sql);
            while (rs2.next()) {
                int idMainan = rs2.getInt("id_mainan");
                if (id_mainan != idMainan) {
                    System.out.println("Data Not Available");
                } else {
                    tambah();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void tambah() {
        try {
            conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt2 = conn2.createStatement();
            System.out.println("============================");
            System.out.println("Please input edited data");
            System.out.println("");
            System.out.print("Name Mainan: ");
            String nama_mainan = input.readLine().trim();
            System.out.print("Price : ");
            String harga_mainan = input.readLine().trim();
            System.out.print("Jenis : ");
            String jenis_mainan = input.readLine().trim();

            String sql2 = "UPDATE mainan SET nama_mainan='%s', harga_mainan='%s', jenis_mainan='%s' WHERE id_mainan=%d";
            sql2 = String.format(sql2, nama_mainan, harga_mainan, jenis_mainan, id_mainan);

            stmt2.execute(sql2);
            System.out.println("----------------------------------");
            System.out.println("Data Mainan Edited Successfully");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void tambahData() {
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // ambil input dari user
            System.out.println();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("======================");
            System.out.println("INPUTKAN DATA MAINAN");
            System.out.println("---------------------");
            System.out.print("Id mainan : ");
            id = Integer.parseInt(input.readLine().trim());
            System.out.print("Name  : ");
            nama_mainan = input.readLine().trim();
            System.out.print("Price : ");
            harga_mainan = Integer.parseInt(input.readLine().trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
