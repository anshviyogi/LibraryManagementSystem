import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Issue extends JFrame {
    int quantity;
    JComboBox costumerCombo,bookCombo;
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
                bookCombo.addItem(rs.getString("BookName"));
            }
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    private void quantityDeduction() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "select * from bookadd where BookName = ?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1,bookCombo.getSelectedItem().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                quantity = rs.getInt("Quantity");
                if(quantity>0) {
                    quantity--;
                    deductSQL();
                }
            }
        }catch(Exception e1) {
            System.out.print(e1.getMessage());
        }
    }
    private void deductSQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "update bookadd set Quantity=? where BookName = ?";
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1,Integer.parseInt(String.valueOf(quantity)));
            ps.setString(2,bookCombo.getSelectedItem().toString());
            ps.executeUpdate();
            cn.close();
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }
    private JPanel contentPane;

    public Issue() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Costumer ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(36, 35, 108, 30);
        contentPane.add(lblNewLabel);

        costumerCombo = new JComboBox();
        costumerCombo.setBounds(181, 35, 152, 30);
        contentPane.add(costumerCombo);
        updateCombo();

        JLabel lblBookId = new JLabel("Book ID");
        lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblBookId.setBounds(36, 109, 108, 30);
        contentPane.add(lblBookId);

        bookCombo = new JComboBox();
        bookCombo.setBounds(181, 109, 152, 30);
        contentPane.add(bookCombo);
        updateBookCombo();

        JButton issue = new JButton("ISSUE");
        issue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    quantityDeduction();
                    if (quantity>0) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String datee = formatter.format(date);
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                        String query = "insert into transactions(BookID,CostumerID,DateOfIssue) values(?,?,?)";
                        PreparedStatement ps = cn.prepareStatement(query);
                        ps.setString(1, bookCombo.getSelectedItem().toString());
                        ps.setString(2, costumerCombo.getSelectedItem().toString());
                        ps.setString(3, datee);
                        ps.executeUpdate();
                        cn.close();
                        JOptionPane.showMessageDialog(null, "Book Issued");
                    }
                }catch(Exception e2) {
                    System.out.print(e2.getMessage());
                }
            }
        });
        issue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        issue.setBounds(181, 182, 99, 30);
        contentPane.add(issue);
    }
}