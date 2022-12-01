import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class guiApp extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiApp frame = new guiApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guiApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton querya = new JButton("Country");
		querya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection ca=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","");
				    Statement sa=ca.createStatement();
				    String qa="select Code,Name,Continent,Region,Population,Capital from country order by population DESC;";
				    ResultSet ra= sa.executeQuery(qa);
				    ResultSetMetaData md=ra.getMetaData();
				    DefaultTableModel mo=(DefaultTableModel) table.getModel();
				    
				    int c=md.getColumnCount();
				    String[] as=new String[c];
				    for(int i=0;i<c;i++) {
				    	as[i]=md.getColumnName(i+1);
				    	mo.setColumnIdentifiers(as);
				        String Code,name,Continent,Region,Population;
				        while(ra.next()) {
				        	Code=ra.getString(1);
				        	name=ra.getString(2);
				        	Continent=ra.getString(3);
				        	Region=ra.getString(4);
				        	Population=ra.getString(5);
				        	String[] rows= {Code,name,Continent,Region,Population};
				        		mo.addRow(rows);
				        }
				        sa.close();
				        
				    }
				    	
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		querya.setBounds(10, 11, 89, 23);
		contentPane.add(querya);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 15, 384, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton clear = new JButton("Delete");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		clear.setBounds(10, 293, 89, 23);
		contentPane.add(clear);
		
		JButton queryb = new JButton("city");
		queryb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cb=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","");
				    Statement sb=cb.createStatement();
				    String qb="select name,population,district from city order by population DESC;";
				    ResultSet rb= sb.executeQuery(qb);
				    ResultSetMetaData md=rb.getMetaData();
				    DefaultTableModel mo=(DefaultTableModel) table.getModel();
				    
				    int cba=md.getColumnCount();
				    String[] co=new String[cba];
				    for(int i=0;i<cba;i++) {
				    	co[i]=md.getColumnName(i+1);
				    	mo.setColumnIdentifiers(co);
				        String name,Population,district;
				        while(rb.next()) {
				        	name=rb.getString(1);
				        	Population=rb.getString(2);
				        	district=rb.getString(3);
				        	String[] row= {name,Population,district};
				        		mo.addRow(row);
				        }
				        sb.close();
				        
				    }
				    	
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		queryb.setBounds(10, 45, 89, 23);
		contentPane.add(queryb);
		
		JButton queryc = new JButton("Population");
		queryc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection cc=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","");
				    Statement sc=cc.createStatement();
				    String qc="SELECT country.continent,SUM(distinct country.population)"+"SUM(city.population) as total in cities"+"CONCAT(FORMAT((SUM(city.population)/SUM(distinct country.population))*100,2),'%')as'livingincity%',"+"(SUM(distinct country.population)-SUM(city.population) as 'notincities'"+"CONCAT(FORMAT(((SUM(distinct country.population)-SUM(city.population))/SUM(distinct country.population))*100,2),'%') as 'notincities%' "+"FROM country,city where country.code=city.countrycode";
				    ResultSet rc= sc.executeQuery(qc);
				    ResultSetMetaData md=rc.getMetaData();
				    DefaultTableModel mo=(DefaultTableModel) table.getModel();
				    
				    int co=md.getColumnCount();
				    String[] col=new String[co];
				    for(int i=0;i<co;i++) {
				    	col[i]=md.getColumnName(i+1);
				    	mo.setColumnIdentifiers(col);
				        String continent,Population,totalincities,totalincitiesper,notincities,notcitiesper;
				        while(rc.next()) {
				        	continent=rc.getString(1);
				        	Population=rc.getString(2);  
				        	totalincities=rc.getString(3);
				        	totalincitiesper=rc.getString(4);
				        	notincities=rc.getString(5);
				        	notcitiesper=rc.getString(6);
				        	String[] row= {continent,Population,totalincities,totalincitiesper,notincities,notcitiesper};
				        		mo.addRow(row);
				        }
				        sc.close();
				        cc.close();
				    }
				    	
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		queryc.setBounds(10, 79, 89, 23);
		contentPane.add(queryc);
		
		JButton queryd = new JButton("capital city");
		queryd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
			        Connection cc=DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","");
				    Statement sc=cc.createStatement();
				    String qc="select capital,country,population from country order by population DESC;";
				    ResultSet rc= sc.executeQuery(qc);
				    ResultSetMetaData md=rc.getMetaData();
				    DefaultTableModel mo=(DefaultTableModel) table.getModel();
				    
				    int co=md.getColumnCount();
				    String[] col=new String[co];
				    for(int i=0;i<co;i++) {
				    	col[i]=md.getColumnName(i+1);
				    	mo.setColumnIdentifiers(col);
				        String capital,country,Population;
				        while(rc.next()) {
				        	capital=rc.getString(1);
				        	country=rc.getString(2);
				        	Population=rc.getString(3);        	
				        	String[] row= {capital,country,Population};
				        		mo.addRow(row);
				        }
				        sc.close();
				        cc.close();
				    }
				    	
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		queryd.setBounds(10, 118, 89, 23);
		contentPane.add(queryd);
	}
}
