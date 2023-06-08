import javax.swing.*;

public class DaftarHarga extends JFrame{
    private JPanel mainPanel;
    private JLabel Title;
    private JLabel labelBiasa;
    private JPanel panelLaundryBiasa;
    public DaftarHarga(String title){
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
        setSize(500,600);
    }
}
