import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class BookSearch{
    JFrame f;
    JTextField t1;
    JTable table;
    JScrollPane scrollPane;
    Button b1,b2;
	private DefaultTableModel dtm;
	
    void fill(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
            String query = "select * from bookadd";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
             dtm = new DefaultTableModel();
             dtm.addColumn("Book Id");
             dtm.addColumn("Book Name");
             dtm.addColumn("Price");
             dtm.addColumn("Quantity");
             dtm.addColumn("Category");
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
            String query = " select * from bookadd where BookName like '%"+str+"%' ";
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            dtm = new DefaultTableModel();
            dtm.addColumn("Book Id");
            dtm.addColumn("Book Name");
            dtm.addColumn("Price");
            dtm.addColumn("Quantity");
            dtm.addColumn("Category");
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

    BookSearch(){
        f = new JFrame("Book Details");
        t1 = new JTextField();
        t1.setBounds(97, 0, 470, 20);
        table = new JTable();
        t1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                fill(t1.getText());
            }
        });
        f.getContentPane().setLayout(null);
        f.getContentPane().add(t1);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 20, 579, 319);
        fill();
        Panel p = new Panel();
        p.setBounds(0, 339, 384, 22);
        p.setLayout(new GridLayout(1,2));
        f.getContentPane().add(scrollPane);
        f.getContentPane().add(p);
        
                b1 = new Button("Delete");
                p.add(b1);
                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	try {
                            if (table.getSelectedRow()!=-1){
                            	int rowIndex = table.getSelectedRow();
                            	int cid=Integer.parseInt(table.getValueAt(rowIndex,0).toString());
                                dtm.removeRow(rowIndex);
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&password=anshviyogi12");
                                String query = "delete from bookadd where BookID=?";
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
        b2 = new Button("Update");
        p.add(b2);
        JLabel lblNewLabel = new JLabel("Search");
        lblNewLabel.setBounds(21, 1, 82, 19);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        f.getContentPane().add(lblNewLabel);
        f.setVisible(true);
        f.setSize(593,400);
    }
}