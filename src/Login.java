import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Login extends JFrame{
    private JLabel Title;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JPanel fieldPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JPanel mainPanel;
    private JComboBox comboBoxTipe;
    private JButton btnRegisterKaryawan;

    public Login(String title) {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set seize window
        setSize(400,450);

        //set posisi window di layar
        setLocationRelativeTo(null);

        // mengatur fixed window
        setResizable(false);

        // button-button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordUserChar = passwordInput.getPassword();
                String passwordUser = new String(passwordUserChar);
                String usernameUser = new String(usernameInput.getText());
                try {
                    if (Conn.isUserCanLogin(usernameUser, passwordUser) && comboBoxTipe.getSelectedIndex() == 0) {
                        redirectToHomePageEmployee();
//                        System.out.println("Sukses login sebagai pegawai");
                    } else if (Conn.isOwnerCanLogin(usernameUser, passwordUser) && comboBoxTipe.getSelectedIndex() == 1) {
                        redirectToHomePageOwner();
//                        System.out.println("Sukses login sebagai owner");
                    } else {
                        JOptionPane.showMessageDialog(null, "Password/username salah!");
                        usernameInput.setText("");
                        passwordInput.setText("");
                    }
                } catch (Exception ex) {
                    System.out.println("eror");
                }
            }

        });

        btnRegisterKaryawan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToRegisterPage();
            }
        });

        // set visible untuk membuat window terlihat
        setVisible(true);
    }

    public void redirectToHomePageOwner(){
        // direct ke home page owner
        new HomepageOwner("Laundry Transaction System");
        dispose();
    }
    public void redirectToHomePageEmployee(){
        // direct ke home page employee
        new HomepageEmployee("Laundry Transaction System");
        dispose();
    }

    // method register karyawan
    public void redirectToRegisterPage(){
        // direct ke register page
        Register registerKaryawan = new Register("Register Karyawan");
        dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new Login("Login");
    }

}
