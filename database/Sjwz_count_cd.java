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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Sjwz_count_cd extends JFrame {

	private JPanel contentPane;
	private JTextField startDate;
	private JTextField endDate;
	private JTable table;
	private JLabel name1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_connetcton.login_s("root","tzchen_20010102");
					Sjwz_count_cd frame = new Sjwz_count_cd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] name=new String[]{"���","Υ��ͳ����Ϣ"};
	private void refresh(){
		//��ȡ�ı���������Ϣ
		String sd=startDate.getText();
		String ed=endDate.getText();
		//�ж����ڸ�ʽ
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{  
            Date date1 = formatter.parse(sd);
            Date date2 = formatter.parse(ed);
        }catch(Exception e){
        	JOptionPane.showMessageDialog(getParent(), "���ڸ�ʽ�������������룡", "����", JOptionPane.ERROR_MESSAGE);
        	return;
        } 
        name1.setText((String) Login_infos.cdnum);
		Vector<Vector> ans1=Sql_connetcton.wzFind(4,Login_infos.cdnum,sd,ed);
		Vector<String> names=new Vector<String>();
		for(String s:name)
			names.add(s);
		
		TableModel tableModle1=new DefaultTableModel(ans1, names){
			public boolean isCellEditable(int row, int column){
                return false;}//��������༭
		};
		table.setModel(tableModle1);
	}
	/**
	 * Create the frame.
	 */
	public Sjwz_count_cd() {
		setTitle("����Υ����Ϣͳ��");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JLabel label_1 = new JLabel("����");
		label_1.setBounds(25, 10, 50, 15);
		contentPane.add(label_1);
		name1 = new JLabel("");
		name1.setBounds(50, 10, 80, 15);
		contentPane.add(name1);
		
		JLabel label_2 = new JLabel("��ʼ����:");
		label_2.setBounds(25, 30, 80, 15);
		contentPane.add(label_2);
		startDate = new JTextField();
		startDate.setBounds(85, 30, 70, 15);
		contentPane.add(startDate);
		startDate.setColumns(20);
		
		JLabel label_3 = new JLabel("��ֹ����:");
		label_3.setBounds(170, 30, 80, 15);
		contentPane.add(label_3);
		endDate = new JTextField();
		endDate.setBounds(230, 30, 70, 15);
		contentPane.add(endDate);
		endDate.setColumns(20);
		
		final JButton button = new JButton("��ѯ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		button.setBounds(320, 10, 93, 23);
		contentPane.add(button);
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 50, 414, 214);
		contentPane.add(scrollPane);
	}
}
