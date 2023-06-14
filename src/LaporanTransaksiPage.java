import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class LaporanTransaksiPage extends JFrame {
    private JPanel laporanTransaksiPage;
    private JLabel titleOwner;
    private JTable tableTransaksiTerbesar;
    private JTextField fieldTotalPenjualanMinggu;
    private JTextField fieldTotalPenjualanBulan;
    private JTable tableLaporanTransaksiJenis;

    public LaporanTransaksiPage(String title){
        super(title);
        setContentPane(laporanTransaksiPage);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        // resizeable
        setResizable(false);

        //set posisi pada layar
        setLocationRelativeTo(null);

        // set visible agar window terlihat
        setVisible(true);

        // set size untuk window
        setSize(800,600);

        // panggil method transaksimingguan
        int transaksiMingguan = Conn.totalPemasukanMingguan();
        fieldTotalPenjualanMinggu.setText("Rp " + String.valueOf(transaksiMingguan));

        // panggil method transaksiBulanan
        int transaksiBulanan = Conn.totalPemasukanBulanan();
        fieldTotalPenjualanBulan.setText("Rp " + String.valueOf(transaksiBulanan));

        // isi dalam tabel 5 buah transaksi terbesar
        Conn.biggestTransaction(tableTransaksiTerbesar);

        // isi dalam tabel laporan transaksi perjenis
        Conn.transactionBasedfromJenis(tableLaporanTransaksiJenis);

    }
}
