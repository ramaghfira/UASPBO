import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//inheritance
public class Girl extends Mainan {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_mainan";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public Girl(int i, String nama_mainan, int harga_mainan) {
        this.id = i;
        this.nama_mainan = nama_mainan;
        this.harga_mainan = harga_mainan;
        this.jenis_mainan = "Girl";
    }

    // pengelolaan database
    public void tambahGirl() {
        // exception
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // query simpan
            String sql = "INSERT INTO mainan (id_mainan, nama_mainan, harga_mainan,jenis_mainan) VALUE('%s', '%s', '%s', '%s')";
            sql = String.format(sql, id, nama_mainan, harga_mainan, jenis_mainan);

            // simpan buku
            stmt.execute(sql);
            System.out.println("-----------------------------------");
            System.out.println("| Data Mainan Berhasil Ditambahkan |");
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

