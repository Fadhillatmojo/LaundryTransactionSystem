
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conn {
    static Connection con;
    public static  Connection getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/laundry","root","");
        }
        catch (Exception ex){
            System.out.println(""+ex);
        }
        return con;
    }

    // select user
    public static void getUser(){
        try {

            Statement perintah = getCon().createStatement();
            String query = "SELECT * FROM employee";
            ResultSet hasilSet = perintah.executeQuery(query);

            // rsult set
            while (hasilSet.next()){
                int idUser = hasilSet.getInt("id_user");
                String username = hasilSet.getString("username");
                String password = hasilSet.getString("password");

                //
                System.out.println("Id:" + idUser + " username:" + username + " password:" + password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // method boolean untuk mengambil data user
    public static boolean isUserCanLogin(String usn, String pass){
        try {
//            Statement perintah = getCon().createStatement();
//            String query = "SELECT username,password FROM employee WHERE username = ? AND password = ?";
//            ResultSet hasilSet = perintah.executeQuery(query);
            // result set
            PreparedStatement statement = getCon().prepareStatement("SELECT username,password FROM employee WHERE username = ? AND password = ?");
            statement.setString(1, usn);
            statement.setString(2, pass);

            try (ResultSet hasilSet = statement.executeQuery()){
                return hasilSet.next();
            }

        } catch (SQLException e){
            e.printStackTrace();

        }

        return false;

    }
    public static boolean isOwnerCanLogin(String usn, String pass){
        try {
            // result set
            PreparedStatement statement = getCon().prepareStatement("SELECT username_owner,password_owner FROM owner WHERE username_owner = ? AND password_owner = ?");
            statement.setString(1, usn);
            statement.setString(2, pass);

            try (ResultSet hasilSet = statement.executeQuery()){
                return hasilSet.next();
            }

        } catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }

    // method untuk register akun baru
    public static void registerKaryawan(String usn, String pass){
        try {
            // result set
            PreparedStatement statement = getCon().prepareStatement("INSERT INTO employee(username, password) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, usn);
            statement.setString(2, pass);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet hasilSet = statement.getGeneratedKeys();
                if (hasilSet.next()) {
                    int userId = hasilSet.getInt(1);
                    System.out.println("berhasil regist, user id: " + userId);
                }
            }

        } catch (SQLException e){
            e.printStackTrace();

        }
    }

    // method untuk menambahkan transaksi ke database
    public static int tambahTransaksi(String namaCustomer, String tanggal, String jenis, int berat, int jmlJas, int dalaman, int jmlSprei, int jmlBedCover, int diskon, String status){
        try{
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = dateFormat.parse(tanggal);
            Date sqlDate = new Date(utilDate.getTime());

            // result set
            PreparedStatement statement = getCon().prepareStatement("INSERT INTO laundrytransaction(customername, tanggal, jenis, berat, jumlahjas, jumlahdalaman, jumlahsprei, jumlahbedcover, diskon, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, namaCustomer);
            statement.setDate(2, sqlDate);
            statement.setString(3, jenis);
            statement.setInt(4, berat);
            statement.setInt(5, jmlJas);
            statement.setInt(6, dalaman);
            statement.setInt(7, jmlSprei);
            statement.setInt(8, jmlBedCover);
            statement.setInt(9, diskon);
            statement.setString(10, status);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet hasilSet = statement.getGeneratedKeys();
                if (hasilSet.next()) {
                    int transactionId = hasilSet.getInt(1);
                    System.out.println("berhasil add transaksi, id customer" + ", " + transactionId);
//                    getTransactionId(transactionId);
                    return transactionId;
                }
            }

        } catch (SQLException e){
            System.out.println("gagal");
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    // method untuk load table
    public static void loadTable(JTable table){
        try {
            PreparedStatement statement = getCon().prepareStatement("SELECT * FROM laundrytransaction");
            ResultSet hasilSet = statement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(hasilSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method untuk update data
    public static void updateData(JTable table, String customerName, String jenis, int berat, int jumlahJas, int jumlahDalaman, int jumlahSprei, int jumlahBedCover, String status, int idTransaksi){
        try {
            PreparedStatement statement = getCon().prepareStatement("UPDATE laundrytransaction SET customername = ?,jenis = ?,berat = ?, jumlahjas = ?,jumlahdalaman = ?,jumlahsprei = ?,jumlahbedcover = ?,status = ? WHERE idtransaction = ?");
            statement.setString(1, customerName);
            statement.setString(2, jenis);
            statement.setInt(3, berat);
            statement.setInt(4, jumlahJas);
            statement.setInt(5, jumlahDalaman);
            statement.setInt(6, jumlahSprei);
            statement.setInt(7, jumlahBedCover);
            statement.setString(8, status);
            statement.setInt(9, idTransaksi);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data diperbarui!");
            Conn.loadTable(table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // search table
    public static void searchData(JTable table, String customername){
        try {
            PreparedStatement statement = getCon().prepareStatement("SELECT * FROM laundrytransaction WHERE laundrytransaction.customername LIKE ?");
            statement.setString(1, "%" + customername + "%");
            ResultSet hasilSet = statement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(hasilSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method urutkan berdasarkanTanggal
    public static void sortTableByDate(JTable table){
        try {
            PreparedStatement statement = getCon().prepareStatement("SELECT * FROM laundrytransaction ORDER BY tanggal DESC");
            ResultSet hasilSet = statement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(hasilSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method mengambil jumlah transaksi 1 minggu terakhir
    public static int totalPemasukanMingguan(){
        String storedProcedure = "{ CALL calculateTotalPenjualanMingguan() }";
        try {
            CallableStatement statement = getCon().prepareCall(storedProcedure);
            ResultSet hasilSet = statement.executeQuery();
            if (hasilSet.next()) {
                int transaksiMingguan = hasilSet.getInt(1);
                return transaksiMingguan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // method mengambil jumlah transaksi 1 bulan terakhir
    public static int totalPemasukanBulanan(){
        String storedProcedure = "{ CALL calculateTotalPenjualanBulanan() }";
        try {
            CallableStatement statement = getCon().prepareCall(storedProcedure);
            ResultSet hasilSet = statement.executeQuery();
            if (hasilSet.next()) {
                int transaksiBulanan = hasilSet.getInt(1);
                return transaksiBulanan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
