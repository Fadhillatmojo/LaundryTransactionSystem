import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame{
    private JPanel registerMainPanel;
    private JLabel Title;
    private JPanel fieldPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JButton btnRegisterKaryawan;
    private JPasswordField passwordInput2;
    private JButton buttonBatal;

    // constructor
    public Register(String title){
        super(title);
        setContentPane(registerMainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        pack();
        setSize(400,450);
        setLocationRelativeTo(null);
        btnRegisterKaryawan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] passwordUserChar = passwordInput.getPassword();
                char[] passwordUserChar2 = passwordInput.getPassword();
                String usernameUser = new String(usernameInput.getText());
                String passwordUser = new String(passwordUserChar);
                String passwordUser2 = new String(passwordUserChar2);
                if (passwordUser.equals(passwordUser2)) {
                    Conn.registerKaryawan(usernameUser, passwordUser);
                    redirectToLoginPage();
                    JOptionPane.showMessageDialog(null, "Berhasil Register!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "password tidak sama!");
                }
            }
        });

        setVisible(true);
        buttonBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redirectToLoginPage();
            }
        });
    }

    // method
    public void redirectToLoginPage(){
        // direct ke login page
        Login loginPage = new Login("Login");
        dispose();
    }
}
