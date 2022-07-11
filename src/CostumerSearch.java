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

class CostumerSearch{
    JFrame f;
    JTextField t1;
    JTable table;
    JScrollPane scrollPane;
    Button b1,b2;
	private DefaultTableModel dtm;
	private JLabel lblNewLabel;
	
    void fill(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "select * from costumeradd";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
             dtm = new DefaultTableModel();
            dtm.addColumn("Sr No.");
            dtm.addColumn("Name");
            dtm.addColumn("Phone No");
            dtm.addColumn("Address");
            while (rs.next()){
                String row[] = {String.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4)};
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
            String query = " select * from costumeradd where Name like '%"+str+"%' ";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Sr No.");
            dtm.addColumn("Name");
            dtm.addColumn("Mobile Number");
            dtm.addColumn("Address");
            while (rs.next()){
                String row[] = {String.valueOf(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4)};
                dtm.addRow(row);
            }
            table.setModel(dtm);
            cn.close();
        }catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }

    CostumerSearch() {
        f = new JFrame("Search Student");
        t1 = new JTextField();
        t1.setBounds(80, 0, 304, 20);
        table = new JTable();
        t1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                fill(t1.getText());
            }
        });
        f.getContentPane().setLayout(null);
        f.getContentPane().add(t1);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 20, 384, 319);
        fill();

        b1 = new Button("Delete");
        b2 = new Button("Update");
        Panel p = new Panel();
        p.setBounds(0, 339, 384, 22);
        p.add(b1);
        p.add(b2);
        p.setLayout(new GridLayout(1,2));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
                    if (table.getSelectedRow()!=-1){
                    	int rowIndex = table.getSelectedRow();
                    	int cid=Integer.parseInt(table.getValueAt(rowIndex,0).toString());
                        dtm.removeRow(rowIndex);
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                        String query = "delete from costumeradd where SrNo=?";
                        PreparedStatement ps = cn.prepareStatement(query);
                        ps.setInt(1,cid);
                        ps.executeUpdate();
                        cn.close();
                        JOptionPane.showMessageDialog(null,"Selected Row Deleted");
                    }
                }catch (Exception ee){
                    System.out.println(ee.getMessage());
                }
            }
        });
        f.getContentPane().add(scrollPane);
        f.getContentPane().add(p);
        
        lblNewLabel = new JLabel("Search");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 0, 95, 20);
        f.getContentPane().add(lblNewLabel);
        f.setVisible(true);
        f.setSize(400,400);
    }
}