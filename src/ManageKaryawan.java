import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageKaryawan extends JFrame implements ButtonBatal{
    private JTable tableManageKaryawan;
    private JScrollPane scrollPanelManageKaryawan;
    private JPanel mainPanel;
    private JTextField fieldCari;
    private JLabel titleOwner;
    private JButton buttoncari;
    private JButton buttonDeleteKaryawan;
    private JTextField fieldId;
    private JButton buttonBatal;


    public ManageKaryawan(String title){
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
        Conn.loadTableEmployee(tableManageKaryawan);

        // set text default
        fieldCari.setText("Cari Nama/id Karyawan");

        buttoncari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTable(fieldCari.getText());
            }
        });

        buttonDeleteKaryawan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int idEmployee = Integer.parseInt(fieldId.getText());
                    Conn.deleteEmployee(tableManageKaryawan, idEmployee);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Tolong pilih data karyawan yang benar!");
                }
            }
        });

        // listener ketika klik pada table
        tableManageKaryawan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableManageKaryawan.getSelectedRow();
                if (row >= 0) {
                    setFieldIdEmployee(row);
                }
            }
        });
        buttonBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batal();
            }
        });
    }

    private void searchTable(String keyword) {
        if (!keyword.isEmpty()) {
            Conn.searchEmployee(tableManageKaryawan, keyword);
        } else {
            Conn.loadTableEmployee(tableManageKaryawan);
        }
    }

    private void setFieldIdEmployee(int row){
        DefaultTableModel model = (DefaultTableModel) tableManageKaryawan.getModel();
        int idEmployee = Integer.parseInt(model.getValueAt(row, 0).toString());
        fieldId.setText(String.valueOf(idEmployee));
    }

    @Override
    public void batal() {
        dispose();
    }

}
