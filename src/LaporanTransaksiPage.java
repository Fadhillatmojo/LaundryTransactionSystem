import javax.swing.*;

public class LaporanTransaksiPage extends JFrame {
    private JPanel laporanTransaksiPage;
    private JLabel titleOwner;
    private JTable tableTransaksiTerbesar;
    private JTextField fieldTotalPenjualanMinggu;
    private JTextField fieldTotalPenjualanBulan;
    private JTable tableLaporanTransaksiJenis;
    private JButton cetakLaporanButton;

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
    }
}
