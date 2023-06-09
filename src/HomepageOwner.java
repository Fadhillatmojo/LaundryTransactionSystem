import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepageOwner extends JFrame{
    private JPanel mainPanel;
    private JLabel gambarMesinCuci;
    private JButton tambahTransaksiButton;
    private JButton logOutButton;
    private JLabel titleOwner;
    private JButton buttonLihatTransaksi;
    private JButton buttonLaporanKeuangan;
    private JButton buttonDaftarHarga;
    private JButton buttonUpdateTransaksi;

    // constructor
    public HomepageOwner(String title){
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // mengatur lebar dan panjang gambar
        gambarMesinCuci.setSize(300, 300);

        // set size untuk ukuran window
        setSize(500, 400);

        // visible true agar dapat terlihat
        setVisible(true);

        tambahTransaksiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToAddTransactionPage();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToLoginPage();
            }
        });
        buttonLaporanKeuangan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("laporan keuangan button was clicked");
                redirectToLaporanTransaksiPage();
            }

        });
        buttonLihatTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("semua transaksi button tertekan");
            }
        });
        buttonDaftarHarga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToDaftarHargaPage();
            }
        });
        buttonLihatTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToLihatTransaksiPage();
            }
        });
        buttonUpdateTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToUpdateTransaksiPage();
            }
        });
    }

    // method
    public void redirectToAddTransactionPage(){
        // direct ke home page
        AddTransactionPage transactionPage = new AddTransactionPage("Add Transaction Page");
    }

    public void redirectToLoginPage(){
        // direct ke login page
        Login loginPage = new Login("Login");
        dispose();
    }

    public void redirectToDaftarHargaPage(){
        // direct ke daftar harga page
        DaftarHarga daftarHarga = new DaftarHarga("Daftar Harga Laundry");
    }

    public void redirectToLihatTransaksiPage(){
        // direct to lihat transaksi page
        LihatTransaksiPage lihatTransaksiPage = new LihatTransaksiPage("Lihat Transaksi");
    }
    public void redirectToUpdateTransaksiPage(){
        // direct to update transaksi page
        UpdateTransaksiPage updateTransaksiPage = new UpdateTransaksiPage("Update Transaksi");
    }

    public void redirectToLaporanTransaksiPage(){
        // direct to laporan transaksi page
        LaporanTransaksiPage laporanTransaksiPage = new LaporanTransaksiPage("Laporan Transaksi");
    }
}
