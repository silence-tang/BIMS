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
public class Sj_update extends JFrame {

	private JPanel contentPane;
	private JTextField sjnum;
	private JTextField sjname;
	private JTextField xlname;
	private JTextField textField_2;
	private JButton button,button_2;
	private JComboBox sjsex;
	private String []name=new String[]{"全选","工号","姓名","性别","线路名"};
	private JTable table;

	private void refresh(){
		String NULL = null;
		Vector<Vector> ans=Sql_connetcton.getAllSj(1,NULL);
		Vector<String> names=new Vector<String>();
		for(String s:name)
			names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));

	}
	
	private void clear() {
		button.setText("录入");
		sjname.setText("");
		xlname.setText("");
		sjsex.setSelectedIndex(0);
		sjname.setEditable(true);
		sjnum.setText("");
		button_2.setVisible(false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("root","tzchen_20010102");
					Sj_update frame = new Sj_update();
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
	public Sj_update() {
		setTitle("司机信息录入");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("工号:");
		label.setBounds(21, 10, 54, 15);
		contentPane.add(label);
		
		sjnum = new JTextField();
		sjnum.setBounds(56, 7, 66, 21);
		contentPane.add(sjnum);
		sjnum.setColumns(10);
		
		JLabel label_1 = new JLabel("姓名:");
		label_1.setBounds(139, 10, 59, 15);
		contentPane.add(label_1);
		
		sjname = new JTextField();
		sjname.setBounds(180, 7, 66, 21);
		contentPane.add(sjname);
		sjname.setColumns(10);
		
		JLabel label_2 = new JLabel("线路名:");
		label_2.setBounds(10, 38, 54, 15);
		contentPane.add(label_2);
		
		xlname = new JTextField();
		xlname.setBounds(56, 35, 66, 21);
		contentPane.add(xlname);
		xlname.setColumns(10);
		
		JLabel label_3 = new JLabel("性别:");
		label_3.setBounds(144, 38, 54, 15);
		contentPane.add(label_3);
		
		button = new JButton("录入");
		button.setBounds(272, 6, 93, 23);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 69, 434, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("批量修改:");
		label_5.setBounds(10, 10, 72, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("姓名:");
		label_6.setBounds(115, 10, 54, 15);
		panel.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(148, 7, 66, 21);
		panel.add(textField_2);
		table = new JTable();
		JScrollPane span=new JScrollPane(table);
		span.setBounds(10, 35, 414, 147);
		panel.add(span);
		refresh();

		
		//添加修改功能
		JButton button_1 = new JButton("修改选中");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定修改？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					if(textField_2.getText() != null){
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector data=tableModel.getDataVector();
						int rows=tableModel.getRowCount();
						int suc=0,bad=0;
						String sjString=textField_2.getText();
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);
							if((boolean)s.get(0)==true){
								s.remove(0);
								s.set(1,sjString);
                                // System.out.println(s.toString());
								if(Sql_connetcton.update(s,"sj")==true)
									suc++;
								else bad++;
							}
						}
						JOptionPane.showMessageDialog(getParent(), "更新完成,成功"+suc+"个,失败"+bad+"个！");
						refresh();
					}
					else{
						textField_2.setText("");
					}
				}
			}
		});
		button_1.setBounds(224, 6, 93, 23);
		panel.add(button_1);
		
		
		//删除选中的字段
		JButton btnNewButton = new JButton("删除选中");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "确定删除？", "警告", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					{
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector data=tableModel.getDataVector();
						int rows=tableModel.getRowCount();
						int suc=0,bad=0;
						String sjString=textField_2.getText();
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);
							
							if((boolean)s.get(0)==true){
//								System.out.println(s.get(1));
								if(Sql_connetcton.delSj((String) s.get(1))==true)
									suc++;
								else bad++;
							}
						}
						JOptionPane.showMessageDialog(getParent(), "删除完成,成功"+suc+"个,失败"+bad+"个！");
						refresh();
					}
				}
			}
		});
		
		btnNewButton.setBounds(327, 6, 97, 23);
		panel.add(btnNewButton);
		button_2 = new JButton("放弃");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		button_2.setBounds(371, 6, 73, 23);
		contentPane.add(button_2);
		button_2.setVisible(false);
		
		sjsex = new JComboBox();
		sjsex.setBounds(180, 35, 54, 21);
		sjsex.addItem("男");
		sjsex.addItem("女");
		contentPane.add(sjsex);
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sjnum.getText().equals(""))JOptionPane.showMessageDialog(getParent(), "请输入工号！");
				else {
					Vector<String> data=new Vector<String>();
					
					//获取输入的信息
					data.add(sjnum.getText());
					data.add(sjname.getText());
					data.add((String) sjsex.getSelectedItem());
					data.add(xlname.getText());
						
					if(button.getText().equals("修改")){
							System.out.println(data.toString());
							if(Sql_connetcton.update(data, "sj")==true){
								JOptionPane.showMessageDialog(getParent(), "修改完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								clear();
								refresh();
							}else{
								JOptionPane.showMessageDialog(getParent(), "修改失败！请检查格式。", "抱歉", JOptionPane.ERROR_MESSAGE);
							}
					}
					else{
						String [][]ans=Sql_connetcton.find_sj(1,sjnum.getText());
						if(ans!=null&&ans.length!=0){
							int t=JOptionPane.showConfirmDialog(getParent(),"已存在工号"+sjnum.getText()+"的司机,是否进入修改模式?","错误",JOptionPane.YES_NO_OPTION );
							if(t==0){
								sjnum.setText(ans[0][1]);
								xlname.setText(ans[0][2]);
								sjsex.setSelectedItem(ans[0][3]);
								button.setText("修改");
								sjname.setEditable(false);
								button_2.setVisible(true);
							}
						}
						else{
								System.out.println(data.toString());
								if(Sql_connetcton.insert(data, "sj")==true){
									JOptionPane.showMessageDialog(getParent(), "录入完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
									clear();
									refresh();
								}else{
									JOptionPane.showMessageDialog(getParent(), "录入失败！格式错误或有重复工号", "抱歉", JOptionPane.ERROR_MESSAGE);
								}
						}
					}
				}
			}
		});
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{sjnum, sjname, xlname, sjsex, button, button_2, textField_2, button_1, btnNewButton,table}));
	}
}
