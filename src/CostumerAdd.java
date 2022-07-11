import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CostumerAdd extends JFrame {

    private JPanel contentPane;
    private JTextField CostumerName;
    private JTextField CostumerMobile;
    private JTextField CostumerAddress;


    public CostumerAdd() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Costumer Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(30, 28, 136, 36);
        contentPane.add(lblNewLabel);

        CostumerName = new JTextField();
        CostumerName.setBounds(203, 28, 181, 29);
        contentPane.add(CostumerName);
        CostumerName.setColumns(10);

        JLabel lblMobileNo = new JLabel("Mobile No.");
        lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMobileNo.setBounds(30, 78, 136, 36);
        contentPane.add(lblMobileNo);

        CostumerMobile = new JTextField();
        CostumerMobile.setColumns(10);
        CostumerMobile.setBounds(203, 78, 181, 29);
        contentPane.add(CostumerMobile);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblAddress.setBounds(30, 134, 136, 36);
        contentPane.add(lblAddress);

        CostumerAddress = new JTextField();
        CostumerAddress.setColumns(10);
        CostumerAddress.setBounds(203, 134, 181, 36);
        contentPane.add(CostumerAddress);

        JButton CostumerAdd = new JButton("Add");
        CostumerAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        CostumerAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                    String query = "insert into CostumerAdd(Name,MobileNo,Address) values(?,?,?)";
                    PreparedStatement ps = cn.prepareStatement(query);
                    ps.setString(1,CostumerName.getText());
                    ps.setString(2,CostumerMobile.getText());
                    ps.setString(3,CostumerAddress.getText());
                    ps.executeUpdate();
                    cn.close();
                    JOptionPane.showMessageDialog(null,"Costumer Added Sucessfully");
                    CostumerName.setText("");
                    CostumerAddress.setText("");
                    CostumerMobile.setText("");
                }catch (Exception ee){
                    System.out.println(ee.getMessage());
                }
            }
        });
        CostumerAdd.setBounds(203, 195, 104, 29);
        contentPane.add(CostumerAdd);
    }
}