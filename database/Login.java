package database;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JComboBox positionBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int is=Sql_connetcton.login_s("root","********");
					if(is==0)
					System.out.println("连接成功");
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,500, 400);
		contentPane = new JPanel();
		//让contentPane内部边框为空，并且有5个像素的厚度
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label0 = new JLabel("XDU公交管理系统");
		label0.setFont(new Font("楷体", Font.PLAIN, 30));
		label0.setBounds(132, 20, 285, 61);
		contentPane.add(label0);
		
		JLabel label1 = new JLabel("用户名：");
		label1.setFont(new Font("宋体", Font.PLAIN, 14));
		label1.setBounds(80, 120, 60, 20);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("密码:");
		label2.setFont(new Font("宋体", Font.PLAIN, 14));
		label2.setBounds(80, 170, 60, 20);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("职位:");
		label3.setFont(new Font("宋体", Font.PLAIN, 14));
		label3.setBounds(80, 215, 60, 20);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("<html><body>(用户名为工号)</body></html>");
		label4.setFont(new Font("楷体", Font.PLAIN, 15));
		label4.setBounds(362, 105, 130, 48);
		contentPane.add(label4);
		
		//用户名输入框
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setBounds(145, 115, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//密码输入框
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 15));
		passwordField.setBounds(145, 165, 200, 30);
		contentPane.add(passwordField);
		
		//职位选择框
		positionBox = new JComboBox();
		positionBox.setBounds(145, 210, 200, 30);
		positionBox.addItem("管理员");
		positionBox.addItem("车队长");
		positionBox.addItem("路队长");
		positionBox.addItem("司机");
		contentPane.add(positionBox);
		
		final JButton button_0 = new JButton("登录");
		button_0.setFont(new Font("宋体", Font.PLAIN, 14));
		button_0.setBounds(95, 250, 120, 50);
		contentPane.add(button_0);
		
		JButton button_1 = new JButton("退出");
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		button_1.setBounds(265, 250, 120, 50);
		contentPane.add(button_1);
		
		JLabel lblCopyrightc = new JLabel("Copyright(C)2021 xxx-数据库系统[01]班");
		lblCopyrightc.setFont(new Font("楷体",Font.PLAIN, 14));
		lblCopyrightc.setBounds(98, 330, 350, 15);
		contentPane.add(lblCopyrightc);
		
		Login.this.setResizable(false);
		Login.this.setLocationRelativeTo(null);	
		passwordField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				button_0.doClick();
				
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		button_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取用户输入的账号密码和职位选择结果
				Login_infos.gh = textField.getText();
				String pswd = String.valueOf(passwordField.getPassword());
				String position = (String) positionBox.getSelectedItem();
				//只有三者同时正确，才允许登录
				Vector result=Sql_connetcton.getLogin(Login_infos.gh,pswd,position);
				if(result==null||result.size()==0){
					JOptionPane.showMessageDialog(getParent(), "工号/密码/职位错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//若账号密码和职位均正确，则根据职位进入不同的主页面
				else {
					if(position == "司机") {
						Login.this.dispose();
						Main_sj main_sj=new Main_sj();
						main_sj.setVisible(true);
					}
					if(position == "路队长") {
						Login.this.dispose();
						Main_ldz main_ldz=new Main_ldz();
						main_ldz.setVisible(true);
					}
					if(position == "车队长") {
						Login.this.dispose();
						Main_cdz main_ldz=new Main_cdz();
						main_ldz.setVisible(true);
					}
					if(position == "管理员") {
						Login.this.dispose();
						Main main=new Main();
						main.setVisible(true);
					}

				}
			}
		});
	}
}
