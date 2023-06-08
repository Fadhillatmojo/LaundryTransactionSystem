package Transaction;

import javax.swing.*;

public class TransactionKilat extends Transaction{
    private JTextArea areaReceipt;
    private JButton buttonCetakStruk;

    public TransactionKilat(String title, String nama, String tanggal, String jenis, int berat, int jmlJas, int dalaman, int jmlSprei, int jmlBedCover, int diskon, String status, int idTransaction){
        super(title);
        bayar(nama,tanggal,jenis,berat, jmlJas, dalaman, jmlSprei, jmlBedCover, diskon, status, idTransaction);
    }

    @Override
    public void bayar(String nama, String tanggal, String jenis, int berat, int jmlJas, int jmlDalaman, int jmlSprei, int jmlBedCover, int diskon, String status, int idTransaction) {
        double total = generateTotal(berat, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, diskon/100);
        super.getAreaReceipt().setText("                LAUNDRY SHOP"+ "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "           Jalan Kebuntuan, No.12" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "             Kabupaten Kerinduan" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "            Provinsi Kesengsaraan" + "\n" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Customer       : " + nama + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Tanggal        : " + tanggal + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Jenis          : " + jenis + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Berat          : " + berat + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  TAMBAHAN:" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Jas            : " + jmlJas + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Dalaman        : " + jmlDalaman + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Sprei          : " + jmlSprei + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Bed Cover      : " + jmlBedCover + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Diskon         : " + diskon + " %" +"\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Total          : " + total + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n");
        if (status.equals("DP(50%)")) {
            super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Status : " + status + "\n" + "  Sisa : " + (total - (total/2.0)) + "\n");
        } else{
            super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  Status         : " + status + "\n");
        }
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "================================================" + "\n" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "  MOHON PERIKSA PESANAN ANDA DAN HASIL CUCIAN" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "     HASIL CUCIAN YANG SUDAH DIBAWA PULANG"+ "\n" + "           BUKAN TANGGUNG JAWAB KAMI" + "\n");
        super.getAreaReceipt().setText(super.getAreaReceipt().getText() + "       TERIMA KASIH TELAH MEMERCAYAKAN" + "\n" +"             CUCIAN ANDA PADA KAMI" + "\n");

    }

    // static generate total
    public static double generateTotal(int beratLaundry, int jmlJas, int jmlDalaman, int jmlSprei, int jmlBedCover, double jmlDiskon){
        double total = (double) (beratLaundry*10000 + jmlJas*50000 + jmlDalaman*2000 + jmlSprei*10000 + jmlBedCover*35000);
        total = total - total*jmlDiskon;
        return total;
    }
}
