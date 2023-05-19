import java.awt.BorderLayout;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MainProgram extends MyFrame implements ActionListener, MouseListener, KeyListener, WindowListener{
	
	//Customer Variables
	private JLabel lblID, lblName, lblGender, lblContact, lblSeat, lblAge;
	private JTextField txtID, txtName, txtContact, txtAge, txtSeat;
	private JComboBox cboGender;
	private Font f = new Font("Arial", Font.BOLD, 16);
	
	//Panel Variables
	private JPanel panelCustomerInfo, panelButtons, panelSearch, panelTable, panelseatButton;
	
	//Field Buttons
	private JButton btnClear, btnUpdate, btnDelete, btnClose;
	
	//Search Variables
	private JLabel lblSearch;
	private JTextField txtSearch;
	
	//Table Variables
	private JTable tbl_Customer;
	private DefaultTableModel model_customer;
	private Vector columns, rowData;
	private TableRowSorter tbl_sort;
	
	//Seat Map Variables
	private JButton s1$1, s1$2, s1$3, s1$4, s1$5, s2$1, s2$2, s2$3, s2$4, s2$5, s3$1, s3$2, s3$3, s3$4, s3$5;
	
	//Database
	private Database db=new Database("Reservation.txt");
	
	//end of Customer Variables
	
	public MainProgram(){
		
		initializedComponents();
		
		//Info Panel
		customerInfo();//method call
		add(panelCustomerInfo).setBounds(10,10,300,150);
		
		//Button Panel
		panelCustomerButtons();//method call
		add(panelButtons).setBounds(310, 10, 74, 150);
		
		//Search Panel
		add(panelCustomerSearch()).setBounds(390,10,165,23);
		
		//Table Panel
		add(panelCustomerTable()).setBounds(390, 40, 610, 120);
		
		//Seat Map
		panelseatMap();
		add(panelseatButton).setBounds(200, 190, 550, 100);
		
		//txtID number
		txtID.setText(getRowCount());
		
		//Register Buttons to ActionListener
		btnClear.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnClose.addActionListener(this);
		
		//Seat Button - ActionListener
		s1$1.addActionListener(this);
		s1$2.addActionListener(this);
		s1$3.addActionListener(this);
		s1$4.addActionListener(this);
		s1$5.addActionListener(this);
		s2$1.addActionListener(this);
		s2$2.addActionListener(this);
		s2$3.addActionListener(this);
		s2$4.addActionListener(this);
		s2$5.addActionListener(this);
		s3$1.addActionListener(this);
		s3$2.addActionListener(this);
		s3$3.addActionListener(this);
		s3$4.addActionListener(this);
		s3$5.addActionListener(this);
		
		//Mouse Listener
		tbl_Customer.addMouseListener(this);
		
		//Key Listener
		txtSearch.addKeyListener(this);
		txtName.addKeyListener(this);
		txtContact.addKeyListener(this);
		
		//Window Listener
		addWindowListener(this);
		
		//Records
		db.displayRecords(model_customer);
		resetComponents();
		
		int i =0;
		
		while(i<model_customer.getRowCount()) {
			String val = (String) model_customer.getValueAt(i, 5);
			//val=val.replace('-', '$');
			//JOptionPane.showMessageDialog(null, val);
			if (val.equals(s1$1.getText())) {
				s1$1.setEnabled(false);
			}else if (val.equals(s1$2.getText())) {
				s1$2.setEnabled(false);
			}else if (val.equals(s1$3.getText())) {
				s1$3.setEnabled(false);
			}else if (val.equals(s1$4.getText())) {
				s1$4.setEnabled(false);
			}else if (val.equals(s1$5.getText())) {
				s1$5.setEnabled(false);
			}else if (val.equals(s2$1.getText())) {
				s2$1.setEnabled(false);
			}else if (val.equals(s2$2.getText())) {
				s2$2.setEnabled(false);
			}else if (val.equals(s2$3.getText())) {
				s2$3.setEnabled(false);
			}else if (val.equals(s2$4.getText())) {
				s2$4.setEnabled(false);
			}else if (val.equals(s2$5.getText())) {
				s2$5.setEnabled(false);
			}else if (val.equals(s3$1.getText())) {
				s3$1.setEnabled(false);
			}else if (val.equals(s3$2.getText())) {
				s3$2.setEnabled(false);
			}else if (val.equals(s3$3.getText())) {
				s3$3.setEnabled(false);
			}else if (val.equals(s3$4.getText())) {
				s3$4.setEnabled(false);
			}else if (val.equals(s3$5.getText())) {
				s3$5.setEnabled(false);
			}
			i++;
		}
		
		
		//Background
		add(setBackgroundImage("IMAGES/Patapon Center (Empty, Medium).png"));
		setMyFrame("Theater Database", 1000,462,true,DISPOSE_ON_CLOSE,true);
		setLocationRelativeTo(null);
		pack();
		
	}//end of method
	
	public static void main(String[] args) {
		new MainProgram();
	}//end of main
	
	public void initializedComponents() {
		
		//Labels
		lblID = new JLabel("ID: ");

		lblName = new JLabel("Name: ");

		lblAge = new JLabel("Age: ");

		lblGender = new JLabel("Gender: ");

		lblContact = new JLabel("Contact Number: ");

		lblSeat = new JLabel("Seat: ");
		
		//Text
		txtID = new JTextField(20);
		txtID.setEditable(false);
		txtName = new JTextField(20);
		txtContact = new JTextField(20);
		txtAge = new JTextField(20);
		txtSeat = new JTextField(20);
		txtSeat.setEditable(false);
		
		
		//ComboBox
		cboGender = new JComboBox();
		loadToComboBox();
		
		//Buttons
		btnClear= new JButton ("Clear");
		btnUpdate=new JButton("Update");
		btnDelete=new JButton("Delete");
		btnClose=new JButton("Close");
		
		//Seat Buttons
		s1$1 = new JButton("S1-1");
		s1$2 = new JButton("S1-2");
		s1$3 = new JButton("S1-3");
		s1$4 = new JButton("S1-4");
		s1$5 = new JButton("S1-5");
		s2$1 = new JButton("S2-1");
		s2$2 = new JButton("S2-2");
		s2$3 = new JButton("S2-3");
		s2$4 = new JButton("S2-4");
		s2$5 = new JButton("S2-5");
		s3$1 = new JButton("S3-1");
		s3$2 = new JButton("S3-2");
		s3$3 = new JButton("S3-3");
		s3$4 = new JButton("S3-4");
		s3$5 = new JButton("S3-5");
		
		
	}//end of method
	
	public void customerInfo() {
		panelCustomerInfo = new JPanel();
		panelCustomerInfo.setBorder(BorderFactory.createTitledBorder("Theater Database"));
		panelCustomerInfo.setLayout(new GridLayout(6, 2));
		panelCustomerInfo.setFont(f);
		panelCustomerInfo.setOpaque(true);
		
		//adding components
		panelCustomerInfo.add(lblID); panelCustomerInfo.add(txtID);
		panelCustomerInfo.add(lblName); panelCustomerInfo.add(txtName);
		panelCustomerInfo.add(lblAge); panelCustomerInfo.add(txtAge);
		panelCustomerInfo.add(lblGender); panelCustomerInfo.add(cboGender);
		panelCustomerInfo.add(lblContact); panelCustomerInfo.add(txtContact);
		panelCustomerInfo.add(lblSeat); panelCustomerInfo.add(txtSeat);
	
	}//end of method
	
	
	public void loadToComboBox() {
		cboGender.addItem("Male");
		cboGender.addItem("Female");
	}//end of method
	
	public void panelCustomerButtons() {
		panelButtons=new JPanel();
		panelButtons.setLayout(new GridLayout(4, 15, 4, 2));
		panelButtons.add(btnClear);
		//panelButtons.add(new JLabel(""));
		panelButtons.add(btnUpdate);
		panelButtons.add(btnDelete);
		panelButtons.add(btnClose);
	}//end of method
	
	public JPanel panelCustomerSearch() {
		panelSearch = new JPanel();
		lblSearch= new JLabel("Search");
		txtSearch= new JTextField(10);
		panelSearch.setLayout(new FlowLayout(FlowLayout.LEFT,2,1));
		panelSearch.add(lblSearch);
		panelSearch.add(txtSearch);
		panelSearch.setOpaque(true);
		return panelSearch;
	}//end of method
	
	public JPanel panelCustomerTable() {
		panelTable=new JPanel();
		tbl_Customer= new JTable();
		model_customer=new DefaultTableModel();
		
		panelTable.setLayout(new BorderLayout());
		panelTable.add(new JScrollPane(tbl_Customer), BorderLayout.CENTER);
		
		String cols[]= {"ID", "Name","Age", "Gender", "Contact #", "Seat"};
		
		columns=new Vector<>();
		//for each loop, story array elements to vector columns
		for(String val:cols) {
			columns.add(val);
		}
		model_customer.setColumnIdentifiers(columns);
		tbl_Customer.setModel(model_customer);
		tbl_Customer.setAutoResizeMode(tbl_Customer.AUTO_RESIZE_LAST_COLUMN);
		return panelTable;
	}//end of method
	
	public String getRowCount() {
		return "10"+model_customer.getRowCount();
	}//end of method
	
	public void getData(ActionEvent e) {
		rowData= new Vector<String>();
		rowData.add(txtID.getText());
		rowData.add(txtName.getText());
		rowData.add(txtAge.getText());
		rowData.add(cboGender.getSelectedItem());
		rowData.add(txtContact.getText());
		
		//Button Thing
		if(e.getSource().equals(s1$1)) {
			rowData.add(s1$1.getText());
		}else if(e.getSource().equals(s1$2)) {
			rowData.add(s1$2.getText());
		}else if(e.getSource().equals(s1$3)) {
			rowData.add(s1$3.getText());
		}else if(e.getSource().equals(s1$4)) {
			rowData.add(s1$4.getText());
		}else if(e.getSource().equals(s1$5)) {
			rowData.add(s1$5.getText());
		}else if(e.getSource().equals(s2$1)) {
			rowData.add(s2$1.getText());
		}else if(e.getSource().equals(s2$2)) {
			rowData.add(s2$2.getText());
		}else if(e.getSource().equals(s2$3)) {
			rowData.add(s2$3.getText());
		}else if(e.getSource().equals(s2$4)) {
			rowData.add(s2$4.getText());
		}else if(e.getSource().equals(s2$5)) {
			rowData.add(s2$5.getText());
		}else if(e.getSource().equals(s3$1)) {
			rowData.add(s3$1.getText());
		}else if(e.getSource().equals(s3$2)) {
			rowData.add(s3$2.getText());
		}else if(e.getSource().equals(s3$3)) {
			rowData.add(s3$3.getText());
		}else if(e.getSource().equals(s3$4)) {
			rowData.add(s3$4.getText());
		}else if(e.getSource().equals(s3$5)) {
			rowData.add(s3$5.getText());
		}
		
	}
	
	public void panelseatMap() {
		panelseatButton=new JPanel();
		panelseatButton.setLayout(new GridLayout(3, 5, 5, 5));
		//Row 3
		panelseatButton.add(s3$1);
		panelseatButton.add(s3$2);
		panelseatButton.add(s3$3);
		panelseatButton.add(s3$4);
		panelseatButton.add(s3$5);
		//Row 2
		panelseatButton.add(s2$1);
		panelseatButton.add(s2$2);
		panelseatButton.add(s2$3);
		panelseatButton.add(s2$4);
		panelseatButton.add(s2$5);
		//Row 1
		panelseatButton.add(s1$1);
		panelseatButton.add(s1$2);
		panelseatButton.add(s1$3);
		panelseatButton.add(s1$4);
		panelseatButton.add(s1$5);
		
	}//end of method
	
	public void resetComponents() {
		txtID.setText(getRowCount());
		
		btnClear.setEnabled(true);
		btnClose.setEnabled(true);
		
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		
		//clear the swing components
		txtName.setText("");
		txtAge.setText("");
		cboGender.setSelectedIndex(0);
		txtContact.setText("");
		txtSeat.setText("");
	}//end of method
	
	public void tableClick() {
		txtID.setText(getRowCount());
		
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
	}
	
	public void process() {
		String records="";
		//Rows
		for(int r=0;r<model_customer.getRowCount();r++) {
			//Columns
			for(int c=0; c<model_customer.getColumnCount();c++) {
				records+=model_customer.getValueAt(r, c)+"#";
			}//end of inner for
			records+="\n";
		}//end of outer for
		db.storeToFile(records);
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(s1$1)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s1$1.setEnabled(false);
		}else if(e.getSource().equals(s1$2)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s1$2.setEnabled(false);
		}else if(e.getSource().equals(s1$3)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s1$3.setEnabled(false);
		}else if(e.getSource().equals(s1$4)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s1$4.setEnabled(false);
		}else if(e.getSource().equals(s1$5)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s1$5.setEnabled(false);
		}else if(e.getSource().equals(s2$1)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s2$1.setEnabled(false);
		}else if(e.getSource().equals(s2$2)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s2$2.setEnabled(false);
		}else if(e.getSource().equals(s2$3)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s2$3.setEnabled(false);
		}else if(e.getSource().equals(s2$4)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s2$4.setEnabled(false);
		}else if(e.getSource().equals(s2$5)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s2$5.setEnabled(false);
		}else if(e.getSource().equals(s3$1)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s3$1.setEnabled(false);
		}else if(e.getSource().equals(s3$2)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s3$2.setEnabled(false);
		}else if(e.getSource().equals(s3$3)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s3$3.setEnabled(false);
		}else if(e.getSource().equals(s3$4)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s3$4.setEnabled(false);
		}else if(e.getSource().equals(s3$5)) {
			getData(e);
			model_customer.addRow(rowData);
			resetComponents();
			s3$5.setEnabled(false);
		}
		
		else if(e.getSource().equals(btnClear)){
			resetComponents();
			
		}else if(e.getSource().equals(btnUpdate)){
			int i = tbl_Customer.getSelectedRow();
			getData(e);
			//starts with 1 since we don't allow changing of ID
			for(int col=1;col<4;col++) {
				tbl_Customer.setValueAt(rowData.get(col),i, col);
			}
			resetComponents();
			
		}else if(e.getSource().equals(btnDelete)){
			int i=tbl_Customer.getSelectedRow();
			
			String val = (String) model_customer.getValueAt(i, 5);
			//val=val.replace('-', '$');
			//JOptionPane.showMessageDialog(null, val);
			if (val.equals(s1$1.getText())) {
				s1$1.setEnabled(true);
			}else if (val.equals(s1$2.getText())) {
				s1$2.setEnabled(true);
			}else if (val.equals(s1$3.getText())) {
				s1$3.setEnabled(true);
			}else if (val.equals(s1$4.getText())) {
				s1$4.setEnabled(true);
			}else if (val.equals(s1$5.getText())) {
				s1$5.setEnabled(true);
			}else if (val.equals(s2$1.getText())) {
				s2$1.setEnabled(true);
			}else if (val.equals(s2$2.getText())) {
				s2$2.setEnabled(true);
			}else if (val.equals(s2$3.getText())) {
				s2$3.setEnabled(true);
			}else if (val.equals(s2$4.getText())) {
				s2$4.setEnabled(true);
			}else if (val.equals(s2$5.getText())) {
				s2$5.setEnabled(true);
			}else if (val.equals(s3$1.getText())) {
				s3$1.setEnabled(true);
			}else if (val.equals(s3$2.getText())) {
				s3$2.setEnabled(true);
			}else if (val.equals(s3$3.getText())) {
				s3$3.setEnabled(true);
			}else if (val.equals(s3$4.getText())) {
				s3$4.setEnabled(true);
			}else if (val.equals(s3$5.getText())) {
				s3$5.setEnabled(true);
			}
			model_customer.removeRow(i);
			resetComponents();
		}else if(e.getSource().equals(btnClose)){
			process();
			System.exit(0);	
		}
	}//end of method
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int i = tbl_Customer.getSelectedRow();
		
		txtID.setText(tbl_Customer.getValueAt(i, 0)+"");
		txtName.setText(tbl_Customer.getValueAt(i,  1)+"");
		txtAge.setText(tbl_Customer.getValueAt(i, 2)+"");
		cboGender.setSelectedItem(tbl_Customer.getValueAt(i,  3)+"");
		txtContact.setText(tbl_Customer.getValueAt(i, 4)+ "");
		txtSeat.setText(tbl_Customer.getValueAt(i, 5)+ "");
		
		tableClick();
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource().equals(txtContact)) {
			if(e.getKeyChar()>='a'&& e.getKeyChar()<='z'){
				e.consume();
			}
		}else if(e.getSource().equals(txtName)) {
			char ch = e.getKeyChar();
			if(!((Character.isWhitespace(ch) || e.getKeyChar()>='a' || e.getKeyChar()>='A')
					&& (e.getKeyChar()<='z'|| e.getKeyChar()<='Z'))) {
				e.consume();
			}
		}//end of if
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource().equals(txtSearch)) {
			String search = txtSearch.getText();
			tbl_sort = new TableRowSorter(model_customer);
			
			tbl_Customer.setRowSorter(tbl_sort);
			tbl_sort.setRowFilter(RowFilter.regexFilter(search,0, 1));
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		process();
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}//end of class
