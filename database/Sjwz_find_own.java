package database;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Sjwz_find_own extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("root","tzchen_20010102");
					Sjwz_find_own frame = new Sjwz_find_own();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] name=new String[]{"序号","工号","车牌号","车队","线路","站点","时间","违章信息"};
	private void refresh(){
		Vector<Vector> ans=Sql_connetcton.sjwzFindown(Login_infos.gh);
		Vector<String> names=new Vector<String>();
		for(String s:name)
			names.add(s);
		TableModel tableModle=new DefaultTableModel(ans, names){
			public boolean isCellEditable(int row, int column){
                return false;}//表格不允许被编辑
		};
		table.setModel(tableModle);

	}
	/**
	 * Create the frame.
	 */
	public Sjwz_find_own() {
		setTitle("司机个人违章信息查询");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		final JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBounds(165, 9, 93, 23);
		contentPane.add(button);
		
		table=new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 37, 414, 214);
		contentPane.add(scrollPane);
		

	}
}
