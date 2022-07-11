import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Return extends JFrame{
    JComboBox costumerCombo,returnCombo;
    String selected,selected2;
    private JPanel returnButton;
    private void updateCombo(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "select * from costumeradd";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                costumerCombo.addItem(rs.getString("Name"));
            }
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    private void updateBookCombo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "select * from bookadd";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                returnCombo.addItem(rs.getString("BookName"));
            }
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    private void returnCombo(){

    }

    public Return() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 373, 264);
        returnButton = new JPanel();
        returnButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(returnButton);
        returnButton.setLayout(null);



        JLabel lblNewLabel = new JLabel("Costumer");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(32, 34, 87, 28);
        returnButton.add(lblNewLabel);

        costumerCombo = new JComboBox();
        costumerCombo.setBounds(148, 34, 148, 28);
        updateCombo();
        returnButton.add(costumerCombo);

        JLabel lblReturn = new JLabel("Return");
        lblReturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblReturn.setBounds(32, 105, 87, 28);
        returnButton.add(lblReturn);

        returnCombo = new JComboBox();
        returnCombo.setBounds(148, 105, 148, 28);
        returnButton.add(returnCombo);
        updateBookCombo();

        JButton btnNewButton = new JButton("Return");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String datee = formatter.format(date);
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                    String query = "update transactions set DateOfReturn=? where BookID = ? AND CostumerID = ?";
                    PreparedStatement ps = cn.prepareStatement(query);
                    ps.setString(1,datee);
                    ps.setString(2,returnCombo.getSelectedItem().toString());
                    ps.setString(3,costumerCombo.getSelectedItem().toString());
                    ps.executeUpdate();
                    cn.close();
                    JOptionPane.showMessageDialog(null, "Book Returned");
                }catch (Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.setBounds(148, 163, 105, 28);
        returnButton.add(btnNewButton);
    }
}
