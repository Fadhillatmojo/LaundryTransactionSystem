package Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public abstract class Transaction extends JFrame {

    private JTextArea areaReceipt;
    private JButton buttonCetakStruk;
    private JPanel mainPanelTransaction;
    private JButton buttonPrint;

    // constructor
    public Transaction(String title){
        super(title);
        setContentPane(mainPanelTransaction);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        buttonPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    areaReceipt.print();
                } catch (PrinterException ex) {
                    System.out.println("Gagal Print");
                }
            }
        });
    }

    // method untuk transaksi
    public abstract void bayar(String nama, String tanggal, String jenis, int berat, int jmlJas, int dalaman, int jmlSprei, int jmlBedCover, int diskon, String status, int idTransaction);

    // getter and setter untuk GUI
    public JTextArea getAreaReceipt() {
        return areaReceipt;
    }

    public void setAreaReceipt(JTextArea areaReceipt) {
        this.areaReceipt = areaReceipt;
    }

    public JButton getButtonCetakStruk() {
        return buttonCetakStruk;
    }

    public void setButtonCetakStruk(JButton buttonCetakStruk) {
        this.buttonCetakStruk = buttonCetakStruk;
    }

    public JPanel getMainPanelTransaction() {
        return mainPanelTransaction;
    }

    public void setMainPanelTransaction(JPanel mainPanelTransaction) {
        this.mainPanelTransaction = mainPanelTransaction;
    }
}
