import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateTransaksiPage extends JFrame implements ButtonBatal{
    private JTable tableUpdateTransaksi;
    private JScrollPane scrollPaneLihatTransaksi;
    private JPanel mainPanel;
    private JTextField fieldCari;
    private JLabel titleOwner;
    private JButton buttoncari;
    private JTextField fieldNama;
    private JTextField fieldBerat;
    private JTextField fieldJas;
    private JTextField fieldDalaman;
    private JTextField fieldSprei;
    private JTextField fieldBedCover;
    private JComboBox comboBoxJenis;
    private JButton buttonUpdate;
    private JButton buttonBatal;
    private JComboBox comboBoxStatus;
    private JTextField fieldId;
    private JButton buttonDelete;

    private TableModel loadTable;

    public UpdateTransaksiPage(String title){
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
        setSize(1000,600);

        // load table mysql
        Conn.loadTable(tableUpdateTransaksi);

        // set text default
        fieldCari.setText("Cari Nama");


        buttoncari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTable(fieldCari.getText());
            }
        });

        // listener ketika klik pada table
        tableUpdateTransaksi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableUpdateTransaksi.getSelectedRow();
                if (row >= 0) {
                    updateFieldsFromTable(row);
                }
            }
        });


        // button update
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idTransaksi = Integer.parseInt(fieldId.getText());
                    String nama = fieldNama.getText();
                    int beratLaundry = Integer.parseInt(fieldBerat.getText());
                    int jmlJas = Integer.parseInt(fieldJas.getText());
                    int jmlDalaman = Integer.parseInt(fieldDalaman.getText());
                    int jmlSprei = Integer.parseInt(fieldSprei.getText());
                    int jmlBedCover = Integer.parseInt(fieldBedCover.getText());
                    String status = (String) comboBoxStatus.getSelectedItem();
                    String jenis = (String) comboBoxJenis.getSelectedItem();

                    Conn.updateData(tableUpdateTransaksi, nama, jenis, beratLaundry, jmlJas, jmlDalaman, jmlSprei, jmlBedCover, status, idTransaksi);

                    // kosongkan field setelah mengupdate data
                    fieldId.setText("");
                    fieldNama.setText("");
                    fieldBerat.setText("");
                    fieldJas.setText("");
                    fieldDalaman.setText("");
                    fieldBedCover.setText("");
                    fieldSprei.setText("");
                    comboBoxJenis.setSelectedItem("BIASA");
                    comboBoxStatus.setSelectedItem("UNPAID");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Tolong pilih data yang benar!");
                }

            }
        });
        buttonBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batal();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idTransaksi = Integer.parseInt(fieldId.getText());
                    Conn.deleteData(tableUpdateTransaksi, idTransaksi);

                    // kosongkan field setelah hapus data
                    fieldId.setText("");
                    fieldNama.setText("");
                    fieldBerat.setText("");
                    fieldJas.setText("");
                    fieldDalaman.setText("");
                    fieldBedCover.setText("");
                    fieldSprei.setText("");
                    comboBoxJenis.setSelectedItem("BIASA");
                    comboBoxStatus.setSelectedItem("UNPAID");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Tolong pilih data yang benar!");
                }

            }
        });
    }

    private void searchTable(String keyword) {
        if (!keyword.isEmpty()) {
            Conn.searchData(tableUpdateTransaksi, keyword);
        } else {
            Conn.loadTable(tableUpdateTransaksi);
        }
    }

    // ini method untuk mengupdate fields, jadi ketika suatu data pada table itu di klik, maka fields nya akan ikut berubah, misal field nama, akan menjadi nama sesuai
    // dengan data yang user klik
    private void updateFieldsFromTable(int row){
        DefaultTableModel model = (DefaultTableModel) tableUpdateTransaksi.getModel();
        int idTransaksi = Integer.parseInt(model.getValueAt(row, 0).toString());

        // ini fungsi where
        try {
            PreparedStatement statement = Conn.getCon().prepareStatement("SELECT idtransaction,customername,jenis,berat, jumlahjas,jumlahdalaman,jumlahsprei,jumlahbedcover,status FROM laundrytransaction WHERE idtransaction = ?");
            statement.setInt(1, idTransaksi);
            ResultSet rs = statement.executeQuery();

            if (rs.next() == true) {
                String id = rs.getString(1);
                String nama = rs.getString(2);
                String beratLaundry = rs.getString(4);
                String jmlJas = rs.getString(5);
                String jmlDalaman = rs.getString(6);
                String jmlSprei = rs.getString(7);
                String jmlBedCover = rs.getString(8);
                String status = rs.getString(9);
                String jenis = rs.getString(3);

                // set text
                fieldId.setText(id);
                fieldNama.setText(nama);
                fieldBerat.setText(beratLaundry);
                fieldJas.setText(jmlJas);
                fieldDalaman.setText(jmlDalaman);
                fieldSprei.setText(jmlSprei);
                fieldBedCover.setText(jmlBedCover);
                comboBoxStatus.setSelectedItem(status);
                comboBoxJenis.setSelectedItem(jenis);

            } else{

                // set text
                fieldNama.setText("");
                fieldBerat.setText("");
                fieldJas.setText("");
                fieldDalaman.setText("");
                fieldSprei.setText("");
                fieldBedCover.setText("");
                comboBoxStatus.setSelectedItem("");
                comboBoxJenis.setSelectedItem("");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void batal() {
        dispose();
    }
}
