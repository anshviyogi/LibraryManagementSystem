import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Settings extends JFrame {

    private JPanel contentPane;
    private JTextField oldPassword;
    private JPasswordField newPassword;
    private JPasswordField confirmPassword;


    public Settings() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Old Password");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(35, 37, 122, 26);
        contentPane.add(lblNewLabel);

        oldPassword = new JTextField();
        oldPassword.setBounds(188, 37, 178, 26);
        contentPane.add(oldPassword);
        oldPassword.setColumns(10);

        JLabel lblNewPassword = new JLabel("New Password");
        lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewPassword.setBounds(35, 94, 122, 26);
        contentPane.add(lblNewPassword);

        newPassword = new JPasswordField();
        newPassword.setBounds(188, 94, 178, 26);
        contentPane.add(newPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblConfirmPassword.setBounds(35, 146, 143, 26);
        contentPane.add(lblConfirmPassword);

        confirmPassword = new JPasswordField();
        confirmPassword.setBounds(188, 146, 178, 26);
        contentPane.add(confirmPassword);

        JButton updateButton = new JButton("UPDATE");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (oldPassword.getText().equals(Login.sqlPassword) && newPassword.getText().equals(confirmPassword.getText())){
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                        String query = "update password set Password=? where User='ansh'";
                        PreparedStatement ps = cn.prepareStatement(query);
                        ps.setString(1,confirmPassword.getText());
                        ps.executeUpdate();
                        cn.close();
                        JOptionPane.showMessageDialog(null,"Password Updated");
                    }catch (Exception ee){
                        System.out.println(ee.getMessage());
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Details");
                }
            }
        });
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        updateButton.setBounds(188, 199, 108, 33);
        contentPane.add(updateButton);
    }

}
