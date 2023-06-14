import Transaction.TransactionBiasa;
import Transaction.TransactionHanyaSetrika;
import Transaction.TransactionKilat;
import com.raven.datechooser.DateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTransactionPage extends JFrame implements ButtonBatal{
    private JPanel mainPanelTransaction;
    private JLabel labelTitle;
    private JTextField fieldNamaCustomer;
    private JComboBox comboBoxJenis;
    private JTextField fieldBerat;
    private JTextField jumlahJas;
    private JTextField jumlahDalaman;
    private JTextField jumlahSprei;
    private JTextField jumlahBedCover;
    private JLabel labelHargaTotal;
    private JPanel panelButton;
    private JPanel panelTambahan;
    private JButton tambahTransaksiButton;
    private JTextArea textAreaStruk;
    private JButton kosongkanFormButton;
    private JTextField fieldDiskon;
    private JTextField fieldTanggal;
    private JButton buttonGenerateTotal;
    private JComboBox comboBoxStatus;
    private JButton buttonCancel;
    private JButton button1;
    private DateChooser dateChooser = new DateChooser();

    // constructor
    public AddTransactionPage(String title){
        super(title);
        setContentPane(mainPanelTransaction);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        // resizeable
        setResizable(false);

        //set posisi pada layar
        setLocationRelativeTo(null);
        // set visible agar window terlihat
        setVisible(true);

        // set size untuk window
        setSize(600,600);

        // set default input
        fieldBerat.setText("0");
        jumlahJas.setText("0");
        jumlahDalaman.setText("0");
        jumlahSprei.setText("0");
        jumlahBedCover.setText("0");
        fieldDiskon.setText("0");


        tambahTransaksiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if (comboBoxJenis.getSelectedIndex() == 0 && !fieldNamaCustomer.getText().isEmpty()) {
                        int beratLaundry = Integer.parseInt(fieldBerat.getText());
                        int jmlJas = Integer.parseInt(jumlahJas.getText());
                        int jmlDalaman = Integer.parseInt(jumlahDalaman.getText());
                        int jmlSprei = Integer.parseInt(jumlahSprei.getText());
                        int jmlBedCover = Integer.parseInt(jumlahBedCover.getText());
                        int jmlDiskon = Integer.parseInt(fieldDiskon.getText());
                        String status = (String) comboBoxStatus.getSelectedItem();
                        String jenis = getJenis();
                        int idTransaction = Conn.tambahTransaksi(fieldNamaCustomer.getText(), fieldTanggal.getText(), jenis, beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon, status);
                        TransactionBiasa transactionBiasa = new TransactionBiasa("Transaksi Laundry Biasa", fieldNamaCustomer.getText(), fieldTanggal.getText(), "Laundry " + jenis + " (3 Hari)", beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon, status, idTransaction);

                    } else if (comboBoxJenis.getSelectedIndex() == 1 && !fieldNamaCustomer.getText().isEmpty()) {
                        int beratLaundry = Integer.parseInt(fieldBerat.getText());
                        int jmlJas = Integer.parseInt(jumlahJas.getText());
                        int jmlDalaman = Integer.parseInt(jumlahDalaman.getText());
                        int jmlSprei = Integer.parseInt(jumlahSprei.getText());
                        int jmlBedCover = Integer.parseInt(jumlahBedCover.getText());
                        int jmlDiskon = Integer.parseInt(fieldDiskon.getText());
                        String status = (String) comboBoxStatus.getSelectedItem();
                        String jenis = getJenis();
                        int idTransaction = Conn.tambahTransaksi(fieldNamaCustomer.getText(), fieldTanggal.getText(), jenis, beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon, status);
                        TransactionKilat transactionKilat = new TransactionKilat("Transaksi Laundry Kilat", fieldNamaCustomer.getText(), fieldTanggal.getText(), "Laundry "+ jenis +" (12 jam)", beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon, status, idTransaction);
                    } else if (comboBoxJenis.getSelectedIndex() == 2 && !fieldNamaCustomer.getText().isEmpty()){
                        int beratLaundry = Integer.parseInt(fieldBerat.getText());
                        int jmlJas = Integer.parseInt(jumlahJas.getText());
                        int jmlDalaman = Integer.parseInt(jumlahDalaman.getText());
                        int jmlSprei = Integer.parseInt(jumlahSprei.getText());
                        int jmlBedCover = Integer.parseInt(jumlahBedCover.getText());
                        int jmlDiskon = Integer.parseInt(fieldDiskon.getText());
                        String status = (String) comboBoxStatus.getSelectedItem();
                        String jenis = getJenis();
                        int idTransaction = Conn.tambahTransaksi(fieldNamaCustomer.getText(), fieldTanggal.getText(), jenis, beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon, status);
                        TransactionHanyaSetrika transactionHanyaSetrika = new TransactionHanyaSetrika("Transaksi Laundry Hanya Setrika", fieldNamaCustomer.getText(), fieldTanggal.getText(), "Hanya " + jenis +" (1 hari)", beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon, status, idTransaction);

                    } else {
                        JOptionPane.showMessageDialog(null, "Username Kosong!");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Isi form dengan benar!");
                }
            }
        });

        // method showStruk

        dateChooser.setTextRefernce(fieldTanggal);
        buttonGenerateTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int beratLaundry = Integer.parseInt(fieldBerat.getText());
                    int jmlJas = Integer.parseInt(jumlahJas.getText());
                    int jmlDalaman = Integer.parseInt(jumlahDalaman.getText());
                    int jmlSprei = Integer.parseInt(jumlahSprei.getText());
                    int jmlBedCover = Integer.parseInt(jumlahBedCover.getText());
                    double jmlDiskon = Double.parseDouble(fieldDiskon.getText());

                    if (comboBoxJenis.getSelectedIndex() == 0) {
                        labelHargaTotal.setText("Rp " +TransactionBiasa.generateTotal(beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon/100));
                    } else if (comboBoxJenis.getSelectedIndex() == 1) {
                        labelHargaTotal.setText("Rp " +TransactionKilat.generateTotal(beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon/100));
                    } else if (comboBoxJenis.getSelectedIndex() == 2) {
                        labelHargaTotal.setText("Rp " +TransactionHanyaSetrika.generateTotal(beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, jmlDiskon/100));
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Masukkan data dengan benar!");
                }

            }
        });

        kosongkanFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldNamaCustomer.setText("");
                fieldTanggal.setText("");
                fieldBerat.setText("0");
                fieldDiskon.setText("0");
                jumlahSprei.setText("0");
                jumlahBedCover.setText("0");
                jumlahDalaman.setText("0");
                jumlahJas.setText("0");
                labelHargaTotal.setText("Rp 0");
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batal();
            }
        });
    }

    // get jenis
    public String getJenis(){
        if (comboBoxJenis.getSelectedIndex() == 0) {
            return "BIASA";
        } else if (comboBoxJenis.getSelectedIndex() == 1) {
            return "KILAT";
        } else if (comboBoxJenis.getSelectedIndex() == 2) {
            return "SETRIKA";
        }
        return "";
    }

    @Override
    public void batal() {
        int confirmed = JOptionPane.showConfirmDialog(null,
                "Apakah yakin ingin menutup tambah transaksi?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            dispose();
        }
    }
}
