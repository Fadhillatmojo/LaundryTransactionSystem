import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;

public class LihatTransaksiPage extends JFrame{
    private JTable tableLihatTransaksi;
    private JScrollPane scrollPaneLihatTransaksi;
    private JPanel mainPanel;
    private JTextField fieldCari;
    private JLabel titleOwner;
    private JButton buttoncari;
    private JButton buttonUrutkanBerdasarkanTanggal;
    private JButton buttonUrutkanBerdasarkanID;


    public LihatTransaksiPage(String title){
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();

        // resizeable
        setResizable(false);

        //set posisi pada layar
        setLocationRelativeTo(null);

        // set visible agar window terlihat
        setVisible(true);

        // set size untuk window
        setSize(800,500);

        // load table mysql
        Conn.loadTable(tableLihatTransaksi);

        // set text default
        fieldCari.setText("Cari Nama/id/berat/jumlah laundry");

        buttoncari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTable(fieldCari.getText());
            }
        });
        buttonUrutkanBerdasarkanTanggal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn.sortTableByDate(tableLihatTransaksi);
            }
        });
        buttonUrutkanBerdasarkanID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn.loadTable(tableLihatTransaksi);
            }
        });
    }

    private void searchTable(String keyword) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableLihatTransaksi.getModel());
        tableLihatTransaksi.setRowSorter(sorter);
        if (!keyword.isEmpty()) {
            RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + keyword);
            sorter.setRowFilter(rowFilter);
        } else {
            sorter.setRowFilter(null);
            Conn.loadTable(tableLihatTransaksi);
        }
    }


}
