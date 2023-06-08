package Transaction;

import javax.swing.*;

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
        setSize(400,650);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
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
