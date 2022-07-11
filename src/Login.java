import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JPanel contentPane;
    private JTextField id;
    private JPasswordField password;
    static String sqlUser,sqlPassword;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 295);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("User Id");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(35, 44, 90, 31);
        contentPane.add(lblNewLabel);

        id = new JTextField();
        id.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        id.setBounds(149, 44, 182, 31);
        contentPane.add(id);
        id.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(35, 121, 90, 31);
        contentPane.add(lblPassword);

        password = new JPasswordField();
        password.setFont(new Font("Tahoma", Font.PLAIN, 16));
        password.setBounds(149, 121, 182, 31);
        contentPane.add(password);

        JButton login = new JButton("Login");
        login.setFont(new Font("Tahoma", Font.PLAIN, 16));
        login.setBounds(149, 182, 98, 31);
        contentPane.add(login);

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//connect database for id password------------------------------------------------------------
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                    String query = "select * from password";
                    PreparedStatement ps = cn.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                        sqlUser = rs.getString("User");
                        sqlPassword = rs.getString("Password");
                    }
                }catch (Exception e1){
                    System.out.println(e1.getMessage());
                }
                if (id.getText().equals(sqlUser)&&password.getText().equals(sqlPassword)){
                    AdminPage ap = new AdminPage();
                    ap.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect ID Or Password");
                }
            }
            
        });
        
    }
}
