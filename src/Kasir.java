import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//interface
public class Kasir implements InputData {

    String nama_kasir, username, password;
    int id_kasir;

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
        // exception
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);

            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // buat objek statement
            stmt = conn.createStatement();

            // buat query ke database
            String sql = "SELECT * FROM kasir";

            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            System.out.println("=======================");
            System.out.println("|  D A T A  K A S I R  |");
            System.out.println("-----------------------");
            while (rs.next()) {
                System.out.println("Id cashier\t : " + rs.getInt("id_kasir"));
                System.out.println("Nama cashier\t : " + rs.getString("nama_kasir"));
                System.out.println("Username\t : " + rs.getString("username") + "\n");
            }
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
            System.out.println("DELETE DATA CASHIER");
            System.out.println("----------------------------");
            System.out.print("Enter the cashier id that will be deleted : ");
            int id_kasir = Integer.parseInt(input.readLine());

            String sql = "DELETE FROM kasir WHERE id_kasir=%d";
            sql = String.format(sql, id_kasir);

            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Cashier Successfully Deleted");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see IData#EditData()
     */
    @Override
    public Void EditData() {
        // exception
        try {
            conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt2 = conn2.createStatement();

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("============================");
            System.out.println("EDIT DATA CASHIER");
            System.out.println("----------------------------");
            System.out.print("Enter the cashier ID that you want to edit : ");
            this.id_kasir = Integer.parseInt(input.readLine());

            String sql = "SELECT kasir.id_kasir FROM kasir WHERE kasir.id_kasir=%d";
            sql = String.format(sql, id_kasir);
            rs2 = stmt2.executeQuery(sql);
            while (rs2.next()) {
                int idKasir = rs2.getInt("id_kasir");
                if (id_kasir != idKasir) {
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
            System.out.println("----------------------------");
            System.out.print("Name Cashier: ");
            String nama_kasir = input.readLine().trim();
            System.out.print("Username : ");
            String username = input.readLine().trim();
            System.out.print("Password : ");
            String password = input.readLine().trim();

            String sql2 = "UPDATE kasir SET nama_kasir='%s', username='%s', password='%s' WHERE id_kasir=%d";
            sql2 = String.format(sql2, nama_kasir, username, password, this.id_kasir);
            stmt2.execute(sql2);

            System.out.println("----------------------------------");
            System.out.println("Cashier Data Edited Successfully");
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
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("======================");
            System.out.println("INPUTKAN DATA CASHIER");
            System.out.println("----------------------");
            System.out.print("Id Cashier : ");
            String id_kasir = input.readLine().trim();
            System.out.print("Nama Cashier : ");
            String nama_kasir = input.readLine().trim();
            System.out.print("Username : ");
            String username = input.readLine().trim();
            System.out.print("Password : ");
            String password = input.readLine().trim();

            // query simpan
            String sql = "INSERT INTO kasir (id_kasir, nama_kasir, username, password) VALUE('%s', '%s', '%s', '%s')";
            sql = String.format(sql, id_kasir, nama_kasir, username, password);

            // simpan buku
            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Cashier Data Added Successfully");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}