package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;

import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;

//import javafx.scene.control.TableColumn;

@SuppressWarnings("serial")
public class Passwd_update extends JFrame {

	private JPanel contentPane;
	private JTextField newpswd;
	private JButton button;
	private String []name=new String[]{"全选","工号","密码","职位"};
	private JTable table;

	private void refresh(){
		Vector<Vector> ans=Sql_connetcton.getpswd();
		Vector<String> names=new Vector<String>();
		for(String s:name)
			names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
	}
	private void clear() {
		button.setText("修改");
		newpswd.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("root","tzchen_20010102");
					Passwd_update frame = new Passwd_update();
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
	public Passwd_update() {
		
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("新密码:");
		label.setBounds(21, 10, 54, 15);
		contentPane.add(label);
		
		newpswd = new JTextField();
		newpswd.setBounds(65, 7, 66, 21);
		contentPane.add(newpswd);
		newpswd.setColumns(10);
		
		button = new JButton("修改");
		button.setBounds(272, 6, 93, 23);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 69, 434, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		JScrollPane span=new JScrollPane(table);
		span.setBounds(10, 35, 414, 147);
		panel.add(span);
		refresh();
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newpswd.getText().equals(""))JOptionPane.showMessageDialog(getParent(), "请输入新密码！");
				else {
					Vector<String> data=new Vector<String>();
					//获取输入的信息
					data.add(newpswd.getText());		
					if(button.getText().equals("修改")){
							System.out.println(data.toString());
							if(Sql_connetcton.update(data, "pswd")==true){
								JOptionPane.showMessageDialog(getParent(), "修改完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								clear();
								refresh();
							}else{
								JOptionPane.showMessageDialog(getParent(), "修改失败！请检查格式。", "抱歉", JOptionPane.ERROR_MESSAGE);
							}
					}
				}
			}
		});
		
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{button, table}));
	}
}
