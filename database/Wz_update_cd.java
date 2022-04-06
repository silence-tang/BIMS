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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import javafx.scene.control.TableColumn;

@SuppressWarnings("serial")
public class Wz_update_cd extends JFrame {

	private JPanel contentPane;
	private JTextField sjnum;
	private JTextField cpnum;
	private JTextField xlname;
	private JTextField zdname;
	private JTextField wztime;
	private JTextField wzinfo;
	private JButton button;
	private String []name=new String[]{"全选","工号","车牌号","车队号","线路名","站点","时间","信息"};
	private JTable table;

	private void refresh(){
		Vector<Vector> ans=Sql_connetcton.getAllWz(2,Login_infos.cdnum);
		Vector<String> names=new Vector<String>();
		for(String s:name)
			names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
	}
	
	private void clear() {
		button.setText("录入");
		sjnum.setText("");
		cpnum.setText("");
		xlname.setText("");
		zdname.setText("");
		wztime.setText("");
		wzinfo.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("root","tzchen_20010102");
					Wz_update_cd frame = new Wz_update_cd();
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
	public Wz_update_cd() {
		setTitle("违章信息录入");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("工号:");
		label.setBounds(10, 10, 50, 20);
		contentPane.add(label);
		
		sjnum = new JTextField();
		sjnum.setBounds(45, 10, 30, 20);
		contentPane.add(sjnum);
		sjnum.setColumns(10);
		
		JLabel label_1 = new JLabel("车牌号:");
		label_1.setBounds(85, 10, 50, 20);
		contentPane.add(label_1);
		
		cpnum = new JTextField();
		cpnum.setBounds(130, 10, 40, 20);
		contentPane.add(cpnum);
		cpnum.setColumns(10);
		
		JLabel label_3 = new JLabel("线路:");
		label_3.setBounds(10, 40, 50, 20);
		contentPane.add(label_3);
		
		xlname = new JTextField();
		xlname.setBounds(45, 40, 30, 20);
		contentPane.add(xlname);
		xlname.setColumns(10);
		
		JLabel label_4 = new JLabel("站点:");
		label_4.setBounds(85, 40, 30, 20);
		contentPane.add(label_4);
		
		zdname = new JTextField();
		zdname.setBounds(130, 40, 40, 20);
		contentPane.add(zdname);
		zdname.setColumns(10);
		
		JLabel label_5 = new JLabel("时间:");
		label_5.setBounds(175, 40, 50, 20);
		contentPane.add(label_5);
		
		wztime = new JTextField();
		wztime.setBounds(220, 40, 70, 20);
		contentPane.add(wztime);
		wztime.setColumns(10);
		
		JLabel label_6 = new JLabel("信息:");
		label_6.setBounds(300, 40, 50, 20);
		contentPane.add(label_6);
		
		wzinfo = new JTextField();
		wzinfo.setBounds(340, 40, 65, 20);
		contentPane.add(wzinfo);
		wzinfo.setColumns(10);	
		
		button = new JButton("录入");
		button.setBounds(310, 6, 93, 20);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 69, 434, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//结果表格
		table = new JTable();
		JScrollPane span=new JScrollPane(table);
		span.setBounds(10, 35, 400, 150);
		panel.add(span);
		refresh();
		
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
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);		
							if((boolean)s.get(0)==true){
								if(Sql_connetcton.delWz((String) s.get(1), (String) s.get(2), (String) s.get(5), (String) s.get(6), (String) s.get(7))==true)
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
		btnNewButton.setBounds(310, 6, 97, 23);
		panel.add(btnNewButton);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sjnum.getText().equals(""))
					JOptionPane.showMessageDialog(getParent(), "请输入工号！");
				else {
					//获取输入信息
					String dt = wztime.getText();
					String sjgh = sjnum.getText();
					String cph = cpnum.getText();
					String xlm = xlname.getText();
					String zdm = zdname.getText();
					//判断日期格式
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			        try{  
			            Date date = formatter.parse(dt);
			        }catch(Exception e1){
			        	JOptionPane.showMessageDialog(getParent(), "日期格式错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
			        	return;
			        } 
					Vector<String> data=new Vector<String>();
					//获取输入的信息
					data.add(sjnum.getText());
					data.add(cpnum.getText());
					data.add(Login_infos.cdnum);
					data.add(xlname.getText());
					data.add(zdname.getText());
					data.add(wztime.getText());
					data.add(wzinfo.getText());
					
					//先判断输入的信息是不是符合条件的
					Vector detail=Sql_connetcton.getallconds(sjgh,cph,Login_infos.cdnum,xlm,zdm);
					if(detail==null||detail.size()==0){
						JOptionPane.showMessageDialog(getParent(), "格式错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(button.getText().equals("修改")){
							System.out.println(data.toString());
							if(Sql_connetcton.update(data, "wz")==true){
								JOptionPane.showMessageDialog(getParent(), "修改完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
								clear();
								refresh();
							}else{
								JOptionPane.showMessageDialog(getParent(), "修改失败！请检查格式", "抱歉", JOptionPane.ERROR_MESSAGE);
							}
					}
					else{
						//检查元组是否存在
						Vector detail1=Sql_connetcton.xlFindbycd(Login_infos.cdnum, xlname.getText());
						if(detail1==null||detail1.size()==0){
							JOptionPane.showMessageDialog(getParent(), "线路名错误或不属本车队管辖，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
						System.out.println(data.toString());
						if(Sql_connetcton.insert(data, "wz")==true){
							JOptionPane.showMessageDialog(getParent(), "录入完成！", "恭喜", JOptionPane.INFORMATION_MESSAGE);
							clear();
							refresh();
						}
						else{
							JOptionPane.showMessageDialog(getParent(), "录入失败！格式错误或车牌号重复", "抱歉", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
			}
		});
		this.setResizable(false);//居中
		this.setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{sjnum,cpnum, xlname, zdname, wztime, wzinfo, button, table}));
	}
}
