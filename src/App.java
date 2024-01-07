import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db_mainan";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input2 = new BufferedReader(inputStreamReader);

    public static void main(String[] args) throws IOException {

        // method date
        Date date = new Date();

        // collection framework
        Queue<Integer> antrian = new LinkedList<>();

        try (Scanner input = new Scanner(System.in)) {
            Kasir kasir = new Kasir();
            Mainan mainan = new Mainan();
            Pelanggan pelanggan = new Pelanggan();
            Pesanan pesanan = new Pesanan();

            System.out.println("------NIMINOT TOYSTORE CASHIER------");
            System.out.println("====================================");
            Boolean pilihan2, pilihan3, pilihan4, pilihan5, pilihan7, pilihan8, pilihan10;
            String ok;
            int pilihan6;
            int nomor = 1;

            // percabangan
            do {
                System.out.println("Choice : ");
                System.out.println("1. Admin \n2. Transaction \n3. Exit");
                System.out.println("What do you want to manage : ");
                int pilihan = input.nextInt();

                // percabangan
                if (pilihan == 1) {
                    do {
                        System.out.println("=============================");
                        System.out.println("------------ADMIN------------");
                        System.out.println("1. Data Kasir \n2. Data Mainan \n3. Data Customer \n4. Data Order");
                        System.out.println("Choose the data you will manage : ");
                        int pilihanMenuKelola = input.nextInt();
                        if (pilihanMenuKelola == 1) {
                            do {
                                System.out.println("=============================");
                                System.out.println("-----------CASHIER-----------");
                                System.out.println(
                                        "1. Add Data Kasir \n2. Delete Data Kasir \n3. Edit Data Kasir \n4. Show Data Kasir");
                                System.out.println("Choose the menu you will manage : ");
                                int pilihanMenuDatabase = input.nextInt();
                                if (pilihanMenuDatabase == 1) {
                                    kasir.tambahData();
                                } else if (pilihanMenuDatabase == 2) {
                                    kasir.hapusData();
                                } else if (pilihanMenuDatabase == 3) {
                                    kasir.EditData();
                                } else if (pilihanMenuDatabase == 4) {
                                    kasir.tampilData();
                                } else {
                                    System.out.println("Sorry, the menu you are looking for is not available");
                                }
                                System.out.println("Do you want to process the cashier data again (true/false) : ");
                                pilihan4 = input.nextBoolean();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            } while (pilihan4 == true);
                        } else if (pilihanMenuKelola == 2) {
                            do {
                                System.out.println("=============================");
                                System.out.println("------------MAINAN-----------");
                                System.out.println(
                                        "1. Add Data Mainan \n2. Delete Data Mainan \n3. Edit Data Mainan \n4. Show Data Mainan");
                                System.out.println("Choose the menu you will manage : ");
                                int pilihanMenuDatabase = input.nextInt();
                                if (pilihanMenuDatabase == 1) {
                                    System.out.println("1. Boy \n2. Girl");
                                    System.out.println("=============================");
                                    System.out.println("Choose the category you will manage : ");
                                    pilihan6 = input.nextInt();
                                    if (pilihan6 == 1) {
                                        mainan.tambahData();
                                        Boy boy = new Boy(mainan.id, mainan.nama_mainan,
                                                mainan.harga_mainan);
                                        boy.tambahBoy();
                                    } else {
                                        mainan.tambahData();
                                        Girl girl = new Girl(mainan.id, mainan.nama_mainan,
                                                mainan.harga_mainan);
                                        girl.tambahGirl();
                                    }
                                } else if (pilihanMenuDatabase == 2) {
                                    mainan.hapusData();
                                } else if (pilihanMenuDatabase == 3) {
                                    mainan.EditData();
                                } else if (pilihanMenuDatabase == 4) {
                                    mainan.tampilData();
                                } else {
                                    System.out.println("Sorry, the menu you are looking for is not available");
                                }
                                System.out.println("Do you want to process the Mainan data again (true/false) : ");
                                pilihan5 = input.nextBoolean();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            } while (pilihan5 == true);
                        } else if (pilihanMenuKelola == 3) {
                            do {
                                System.out.println("=============================");
                                System.out.println("-----------CUSTOMER----------");
                                System.out.println(
                                        "1. Add Data Customer \n2. Delete Data Customer \n3. Edit Data Customer \n4. Show Data Customer");
                                System.out.println("Choose the menu you will manage : ");
                                int pilihanMenuDatabase = input.nextInt();
                                if (pilihanMenuDatabase == 1) {
                                    pelanggan.tambahData();
                                } else if (pilihanMenuDatabase == 2) {
                                    pelanggan.hapusData();
                                } else if (pilihanMenuDatabase == 3) {
                                    pelanggan.EditData();
                                } else if (pilihanMenuDatabase == 4) {
                                    pelanggan.tampilData();
                                } else {
                                    System.out.println("Sorry, the menu you are looking for is not available");
                                }
                                System.out.println("Do you want to process the Customer data again (true/false) : ");
                                pilihan7 = input.nextBoolean();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            } while (pilihan7 == true);
                        } else if (pilihanMenuKelola == 4) {
                            do {
                                System.out.println("=============================");
                                System.out.println("------------ORDER------------");
                                System.out.println(
                                        "1. Delete Data Order \n2. Edit Data Order \n3. Show Data Order");
                                System.out.println("Choose the menu you will manage : ");
                                int pilihanMenuDatabase = input.nextInt();
                                if (pilihanMenuDatabase == 1) {
                                    pesanan.hapusData();
                                } else if (pilihanMenuDatabase == 2) {
                                    pesanan.EditData();
                                } else if (pilihanMenuDatabase == 3) {
                                    pesanan.tampilData();
                                } else {
                                    System.out.println("Sorry, the menu you are looking for is not available");
                                }
                                System.out.println("Do you want to process the Order data again (true/false ) : ");
                                pilihan10 = input.nextBoolean();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            } while (pilihan10 == true);
                        } else {
                            System.out.println("Sorry, the menu you are looking for is not available");
                        }
                        System.out.println("Do you want to display the manage database menu again (true/false) : ");
                        pilihan3 = input.nextBoolean();
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                    } while (pilihan3 == true);
                } else if (pilihan == 2) {
                    do {
                        System.out.println("Please fill in the order input : ");
                        pelanggan.tampilData();
                        System.out.println("====================================");
                        System.out.print("Customer : ");
                        String nama_pelanggan = input.next();
                        pesanan.getNamaPelanggan(nama_pelanggan);
                        pesanan.getIdPelanggan(nama_pelanggan);
                        kasir.tampilData();
                        System.out.println("====================================");
                        System.out.print("Cashier : ");
                        String nama_kasir = input.next();
                        pesanan.getNamaKasir(nama_kasir);
                        pesanan.getIdKasir(nama_kasir);
                        System.out.println("====================================");
                        antrian.add(nomor);
                        System.out.println("Queue : " + nomor + "\n");

                        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");
                        String tgl_pesanan, jam_pesanan;
                        tgl_pesanan = ft.format(date);
                        System.out.println("Date : " + tgl_pesanan);
                        SimpleDateFormat ft2 = new SimpleDateFormat("hh:mm:ss a zzz");
                        jam_pesanan = ft2.format(date);
                        pesanan.getJam(jam_pesanan);
                        System.out.println("Time : " + jam_pesanan + "\n");
                        System.out.println("Please select the toy you want to buy");
                        mainan.tampilData();
                        int jumlah_pesanan, total_harga, total_bayar, kembalian;

                        ArrayList<Integer> totalHarga = new ArrayList<Integer>();
                        ArrayList<Integer> idMainan = new ArrayList<Integer>();
                        ArrayList<Integer> hargaMainan = new ArrayList<Integer>();
                        ArrayList<Integer> jumlahPesanan = new ArrayList<Integer>();
                        int id_mainan;

                        // proses matematika
                        do {
                            System.out.println("===================================");
                            System.out.println("Purchased Toys");
                            System.out.print("Id Mainan: ");
                            id_mainan = Integer.parseInt(input2.readLine());
                            idMainan.add(id_mainan);
                            System.out.print("Quantity : ");
                            jumlah_pesanan = Integer.parseInt(input2.readLine());
                            jumlahPesanan.add(jumlah_pesanan);
                            int harga_mainan = pesanan.getHargaMainan(id_mainan);
                            hargaMainan.add(harga_mainan);
                            total_harga = pesanan.harga_mainan * jumlah_pesanan;
                            totalHarga.add(total_harga);
                            System.out.println("Total Price : " + total_harga + "\n");
                            System.out.print("Do you want to add another toys (true/false): ");
                            pilihan8 = input.nextBoolean();
                        } while (pilihan8 == true);
                        nomor = nomor + 1;
                        int total = 0;
                        for (int i = 0; i < totalHarga.size(); i++) {
                            total = total + totalHarga.get(i);
                        }
                        System.out.println("Total : " + total);
                        System.out.print("Payment : ");
                        total_bayar = input.nextInt();
                        if (total_bayar < total) {
                            do {
                                System.out.println("Sorry, you don't have enough money, try again");
                                System.out.print("Payment : ");
                                total_bayar = input.nextInt();
                            } while (total_bayar < total);
                        }
                        kembalian = total_bayar - total;
                        pesanan.kembalian = kembalian;
                        System.out.println("Return Money : " + kembalian);

                        System.out.println("Your Payment is SUCCESS");
                        pesanan.getNoPesanan(jam_pesanan);

                        System.out.println("---------------------------------------------------------");
                        System.out.println("|       N I M I N O T      T O Y      S T O R E         |");
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Queue : " + nomor + "\n");
                        System.out.println(tgl_pesanan);
                        System.out.println(jam_pesanan);
                        System.out.println("---------------------------------------------------------");

                        for (int i = 0; i < idMainan.size(); i++) {
                            System.out
                                    .println(i + 1 + ". " + pesanan.getNamaMainan(idMainan.get(i)) + "\n\t"
                                            + jumlahPesanan.get(i)
                                            + " x " + hargaMainan.get(i) + "\t Rp " + totalHarga.get(i));
                        }
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Total\t\t: Rp " + total);
                        System.out.println("Payment\t\t: Rp " + total_bayar);
                        System.out.println("Return\t\t: Rp " + pesanan.kembalian);
                        System.out.println("---------------------------------------------------------");
                        System.out.println("-- \t            T H A N K  Y O U             \t --");
                        System.out.println("---------------------------------------------------------");

                        // exception
                        try {
                            conn = DriverManager.getConnection(DB_URL, USER, PASS);
                            stmt = conn.createStatement();

                            // query simpan
                            for (int i = 0; i < idMainan.size(); i++) {
                                String sql = "INSERT INTO pesanan (tgl_pesanan, jam_pesanan, id_pelanggan, id_kasir, id_mainan, total_harga, total_belanja, total_bayar, kembalian) VALUE('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
                                sql = String.format(sql, tgl_pesanan, jam_pesanan, pesanan.id_pelanggan,
                                        pesanan.id_kasir,
                                        idMainan.get(i), total_harga, total,
                                        total_bayar,
                                        kembalian);
                                // simpan
                                stmt.execute(sql);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        totalHarga.clear();
                        idMainan.clear();
                        jumlahPesanan.clear();
                        System.out.println("Do you want to transaction again (ok/tidak): ");
                        ok = input.next();

                        // method string
                    } while (ok.equalsIgnoreCase("ok"));

                } else if (pilihan == 3) {
                    System.out.println("Program Complete");
                    System.exit(0);
                } else {
                    System.out.println("Sorry menu not available!");
                }
                System.out.println("=============================");
                System.out.println("Do you want to go back to the main menu (true/false) : ");
                pilihan2 = input.nextBoolean();
                System.out.println("===================================");
                System.out.print("\033[H\033[2J");
                System.out.flush();

            } while (pilihan2 == true);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}