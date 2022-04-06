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
public class Cdwz_count extends JFrame {

	private JPanel contentPane;
	private JTextField cdNum;
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
					Cdwz_count frame = new Cdwz_count();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String[] name=new String[]{"序号","违章统计信息"};
	private void refresh(){
		//获取文本框输入的车队信息
		String cc=cdNum.getText();
		String sd=startDate.getText();
		String ed=endDate.getText();
		Vector detail=Sql_connetcton.getcd(cc);
		//若在车队表中找不到相应的车队，则显示提示信息
		if(detail==null||detail.size()==0){
			JOptionPane.showMessageDialog(getParent(), "车队号错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		//判断日期格式是否正确
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{  
            Date date1 = formatter.parse(sd);
            Date date2 = formatter.parse(ed);
        }catch(Exception e){
        	JOptionPane.showMessageDialog(getParent(), "日期格式错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
        	return;
        }
		name1.setText((String) cdNum.getText());
		//调用wzFind函数查询违章统计信息
		Vector<Vector> ans1=Sql_connetcton.wzFind(4,cc,sd,ed);
		Vector<String> names=new Vector<String>();
		//获取所有的属性名
		for(String s:name)
			names.add(s);
		//显示结果表格
		TableModel tableModle1=new DefaultTableModel(ans1, names){
			public boolean isCellEditable(int row, int column){
                return false;}//表格不允许被编辑
		};
		table.setModel(tableModle1);
	}
	/**
	 * Create the frame.
	 */
	public Cdwz_count() {
		setTitle("车队违章信息统计");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JLabel label = new JLabel("车队号:");
		label.setBounds(25, 10, 50, 15);
		contentPane.add(label);
		cdNum = new JTextField();
		cdNum.setBounds(75, 10, 50, 15);
		contentPane.add(cdNum);
		cdNum.setColumns(10);
		
		JLabel label_2 = new JLabel("起始日期:");
		label_2.setBounds(25, 30, 80, 15);
		contentPane.add(label_2);
		startDate = new JTextField();
		startDate.setBounds(85, 30, 70, 15);
		contentPane.add(startDate);
		startDate.setColumns(20);
		
		JLabel label_3 = new JLabel("截止日期:");
		label_3.setBounds(170, 30, 80, 15);
		contentPane.add(label_3);
		endDate = new JTextField();
		endDate.setBounds(230, 30, 70, 15);
		contentPane.add(endDate);
		endDate.setColumns(20);
		
		final JButton button = new JButton("查询");
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
		
		JLabel label_1 = new JLabel("车队");
		label_1.setBounds(170, 10, 50, 15);
		contentPane.add(label_1);
		
		name1 = new JLabel("");
		name1.setBounds(195, 10, 80, 15);
		contentPane.add(name1);
		cdNum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				button.doClick();
				 
			}
		});
	}
}
