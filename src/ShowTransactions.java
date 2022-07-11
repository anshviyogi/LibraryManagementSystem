import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ShowTransactions{
    JFrame f;
    JTextField t1;
    JTable table;
    JScrollPane scrollPane;
	private DefaultTableModel dtm;
	private JLabel lblNewLabel;
	
    void fill(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "select * from transactions";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
             dtm = new DefaultTableModel();
            dtm.addColumn("Transaction ID");
            dtm.addColumn("Book ID");
            dtm.addColumn("Costumer ID");
            dtm.addColumn("Date of Issue");
            dtm.addColumn("Date of Return");
            while (rs.next()){
                String row[] = {String.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
                dtm.addRow(row);
            }
            table.setModel(dtm);
            cn.close();
            
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    void fill(String str){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = " select * from transactions where CostumerID like '%"+str+"%' ";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Transaction ID");
            dtm.addColumn("Book ID");
            dtm.addColumn("Costumer ID");
            dtm.addColumn("Date of Issue");
            dtm.addColumn("Date of Return");
            while (rs.next()){
                String row[] = {String.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
                dtm.addRow(row);
            }
            table.setModel(dtm);
            cn.close();
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    ShowTransactions() {
        f = new JFrame("Search Student");
        t1 = new JTextField();
        t1.setBounds(80, 0, 457, 20);
        table = new JTable();
        t1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                fill(t1.getText());
            }
        });
        f.getContentPane().setLayout(null);
        f.getContentPane().add(t1);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 20, 537, 345);
        fill();
        
        
        f.getContentPane().add(scrollPane);
        
        lblNewLabel = new JLabel("Search");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 0, 95, 20);
        f.getContentPane().add(lblNewLabel);
        f.setVisible(true);
        f.setSize(563,415);
    }
}