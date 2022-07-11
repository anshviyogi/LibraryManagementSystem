import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class BookAdd extends JFrame {

    private JPanel contentPane;
    private JTextField bookName;
    private JTextField bookPrice;
    private JTextField bookQuantity;


    public BookAdd() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 494, 416);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Book Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(36, 41, 120, 34);
        contentPane.add(lblNewLabel);

        bookName = new JTextField();
        bookName.setBounds(209, 41, 204, 34);
        contentPane.add(bookName);
        bookName.setColumns(10);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCategory.setBounds(36, 244, 120, 34);
        contentPane.add(lblCategory);

        JComboBox comboBox = new JComboBox();
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Novel", "Literature", "History", "Magazine"}));
        comboBox.setBounds(210, 244, 120, 32);
        contentPane.add(comboBox);
        String select = (String) comboBox.getSelectedItem();

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPrice.setBounds(36, 109, 120, 34);
        contentPane.add(lblPrice);

        bookPrice = new JTextField();
        bookPrice.setColumns(10);
        bookPrice.setBounds(209, 109, 204, 34);
        contentPane.add(bookPrice);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQuantity.setBounds(36, 177, 120, 34);
        contentPane.add(lblQuantity);

        bookQuantity = new JTextField();
        bookQuantity.setColumns(10);
        bookQuantity.setBounds(209, 177, 204, 34);
        contentPane.add(bookQuantity);

        JButton bookSubmit = new JButton("Submit");
        bookSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                    String query = "insert into bookadd(BookName,Price,Quantity,Category) values(?,?,?,?)";
                    PreparedStatement ps = cn.prepareStatement(query);
                    ps.setString(1,bookName.getText());
                    ps.setInt(2,Integer.parseInt(bookPrice.getText()));
                    ps.setInt(3,Integer.parseInt(bookQuantity.getText()));
                    ps.setString(4,(String) comboBox.getSelectedItem());
                    int a = ps.executeUpdate();
                    cn.close();
                    JOptionPane.showMessageDialog(null,"Data Saved");
                    bookName.setText("");
                    bookPrice.setText("");
                    bookQuantity.setText("");
                }catch (Exception ee){
                    JOptionPane.showMessageDialog(null,ee.getMessage());
                }
            }
        });
        bookSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        bookSubmit.setBounds(209, 310, 92, 32);
        contentPane.add(bookSubmit);
    }
}
