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
public class Cl_update_cd extends JFrame {

	private JPanel contentPane;
	private JTextField cpnum;
	private JTextField zwnum;
	private JTextField xlname;
	private JTextField textField_2;
	private JButton button,button_2;
	private String []name=new String[]{"ȫѡ","���ƺ�","��λ��","��·��"};
	private JTable table;

	private void refresh(){
		Vector<Vector> ans=Sql_connetcton.getAllCl(2,Login_infos.cdnum);
		Vector<String> names=new Vector<String>();
		for(String s:name)
			names.add(s);
		CheckTableModle tableModle=new CheckTableModle(ans, names);
		table.setModel(tableModle);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
	}
	private void clear() {
		button.setText("¼��");
		zwnum.setText("");
		xlname.setText("");
		zwnum.setEditable(true);
		cpnum.setText("");
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
					Cl_update_cd frame = new Cl_update_cd();
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
	public Cl_update_cd() {
		setTitle("������Ϣ¼��");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("���ƺ�:");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		
		cpnum = new JTextField();
		cpnum.setBounds(56, 7, 66, 21);
		contentPane.add(cpnum);
		cpnum.setColumns(10);
		
		JLabel label_1 = new JLabel("��λ��:");
		label_1.setBounds(139, 10, 59, 15);
		contentPane.add(label_1);
		
		zwnum = new JTextField();
		zwnum.setBounds(180, 7, 66, 21);
		contentPane.add(zwnum);
		zwnum.setColumns(10);
		
		JLabel label_2 = new JLabel("��·��:");
		label_2.setBounds(10, 38, 54, 15);
		contentPane.add(label_2);
		
		xlname = new JTextField();
		xlname.setBounds(56, 35, 66, 21);
		contentPane.add(xlname);
		xlname.setColumns(10);
		
		button = new JButton("¼��");
		button.setBounds(272, 6, 93, 23);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 69, 434, 192);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_5 = new JLabel("�����޸�:");
		label_5.setBounds(10, 10, 72, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("��λ��:");
		label_6.setBounds(105, 10, 54, 15);
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

		
		//����޸Ĺ���
		JButton button_1 = new JButton("�޸�ѡ��");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "ȷ���޸ģ�", "����", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					if(textField_2.getText() != null){
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector data=tableModel.getDataVector();
						int rows=tableModel.getRowCount();
						int suc=0,bad=0;
						String clString=textField_2.getText();
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);
							if((boolean)s.get(0)==true){
								s.remove(0);
								s.set(1,clString);
                                // System.out.println(s.toString());
								if(Sql_connetcton.update(s,"cl")==true)
									suc++;
								else bad++;
							}
						}
						JOptionPane.showMessageDialog(getParent(), "�������,�ɹ�"+suc+"��,ʧ��"+bad+"����");
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
		
		
		//ɾ��ѡ�е��ֶ�
		JButton btnNewButton = new JButton("ɾ��ѡ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans=JOptionPane.showConfirmDialog(getParent(), "ȷ��ɾ����", "����", JOptionPane.YES_NO_OPTION);
				if(ans==0){
					{
						DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
						Vector data=tableModel.getDataVector();
						int rows=tableModel.getRowCount();
						int suc=0,bad=0;
						String clString=textField_2.getText();
						for(int i=0;i<rows;i++){
							Vector s=(Vector) data.elementAt(i);
							
							if((boolean)s.get(0)==true){
//								System.out.println(s.get(1));
								if(Sql_connetcton.delCl((String) s.get(1))==true)
									suc++;
								else bad++;
							}
						}
						JOptionPane.showMessageDialog(getParent(), "ɾ�����,�ɹ�"+suc+"��,ʧ��"+bad+"����");
						refresh();
					}
				}
			}
		});
		
		btnNewButton.setBounds(327, 6, 97, 23);
		panel.add(btnNewButton);
		button_2 = new JButton("����");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		
		button_2.setBounds(371, 6, 73, 23);
		contentPane.add(button_2);
		button_2.setVisible(false);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cpnum.getText().equals(""))JOptionPane.showMessageDialog(getParent(), "�����복�ƺţ�");
				else {
					Vector<String> data=new Vector<String>();
					
					//��ȡ�������Ϣ
					data.add(cpnum.getText());
					data.add(zwnum.getText());
					data.add(xlname.getText());
					if(button.getText().equals("�޸�")){
							System.out.println(data.toString());
							if(Sql_connetcton.update(data, "cl")==true){
								JOptionPane.showMessageDialog(getParent(), "�޸���ɣ�", "��ϲ", JOptionPane.INFORMATION_MESSAGE);
								clear();
								refresh();
							}else{
								JOptionPane.showMessageDialog(getParent(), "�޸�ʧ�ܣ������ʽ", "��Ǹ", JOptionPane.ERROR_MESSAGE);
							}
					}
					else{
						String [][]ans=Sql_connetcton.find_cl(1,cpnum.getText());
						if(ans!=null&&ans.length!=0){
							int t=JOptionPane.showConfirmDialog(getParent(),"�Ѵ��ڳ��ƺ�Ϊ"+cpnum.getText()+"�ĳ���,�Ƿ�����޸�ģʽ?","����",JOptionPane.YES_NO_OPTION );
							if(t==0){
								cpnum.setText(ans[0][1]);
								xlname.setText(ans[0][2]);
								button.setText("�޸�");
								zwnum.setEditable(false);
								button_2.setVisible(true);
							}
						}
						else{
								//���Ԫ���Ƿ����
								Vector detail=Sql_connetcton.xlFindbycd(Login_infos.cdnum, xlname.getText());
								if(detail==null||detail.size()==0){
									JOptionPane.showMessageDialog(getParent(), "��·��������������ӹ�Ͻ�����������룡", "����", JOptionPane.ERROR_MESSAGE);
									return;
								}
								System.out.println(data.toString());
								if(Sql_connetcton.insert(data, "cl")==true){
									JOptionPane.showMessageDialog(getParent(), "¼����ɣ�", "��ϲ", JOptionPane.INFORMATION_MESSAGE);
									clear();
									refresh();
								}
								else{
									JOptionPane.showMessageDialog(getParent(), "¼��ʧ�ܣ���ʽ������ƺ��ظ�", "��Ǹ", JOptionPane.ERROR_MESSAGE);
								}
		
						}
					}
					
				}
			}
		});
		this.setResizable(false);//����
		this.setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{cpnum, zwnum, xlname, button, button_2, textField_2, button_1, btnNewButton,table}));
	}
}
