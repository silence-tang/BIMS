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
					int is=Sql_connetcton.login_s("root","tzchen_20010102");
					if(is==0)
					System.out.println("���ӳɹ�");
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
		//��contentPane�ڲ��߿�Ϊ�գ�������5�����صĺ��
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label0 = new JLabel("XDU��������ϵͳ");
		label0.setFont(new Font("����", Font.PLAIN, 30));
		label0.setBounds(132, 20, 285, 61);
		contentPane.add(label0);
		
		JLabel label1 = new JLabel("�û�����");
		label1.setFont(new Font("����", Font.PLAIN, 14));
		label1.setBounds(80, 120, 60, 20);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("����:");
		label2.setFont(new Font("����", Font.PLAIN, 14));
		label2.setBounds(80, 170, 60, 20);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("ְλ:");
		label3.setFont(new Font("����", Font.PLAIN, 14));
		label3.setBounds(80, 215, 60, 20);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("<html><body>(�û���Ϊ����)</body></html>");
		label4.setFont(new Font("����", Font.PLAIN, 15));
		label4.setBounds(362, 105, 130, 48);
		contentPane.add(label4);
		
		//�û��������
		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 15));
		textField.setBounds(145, 115, 200, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//���������
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("����", Font.PLAIN, 15));
		passwordField.setBounds(145, 165, 200, 30);
		contentPane.add(passwordField);
		
		//ְλѡ���
		positionBox = new JComboBox();
		positionBox.setBounds(145, 210, 200, 30);
		positionBox.addItem("����Ա");
		positionBox.addItem("���ӳ�");
		positionBox.addItem("·�ӳ�");
		positionBox.addItem("˾��");
		contentPane.add(positionBox);
		
		final JButton button_0 = new JButton("��¼");
		button_0.setFont(new Font("����", Font.PLAIN, 14));
		button_0.setBounds(95, 250, 120, 50);
		contentPane.add(button_0);
		
		JButton button_1 = new JButton("�˳�");
		button_1.setFont(new Font("����", Font.PLAIN, 14));
		button_1.setBounds(265, 250, 120, 50);
		contentPane.add(button_1);
		
		JLabel lblCopyrightc = new JLabel("Copyright(C)2021 ���ӳ�-���ݿ�ϵͳ[01]��");
		lblCopyrightc.setFont(new Font("����",Font.PLAIN, 14));
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
				//��ȡ�û�������˺������ְλѡ����
				Login_infos.gh = textField.getText();
				String pswd = String.valueOf(passwordField.getPassword());
				String position = (String) positionBox.getSelectedItem();
				//ֻ������ͬʱ��ȷ���������¼
				Vector result=Sql_connetcton.getLogin(Login_infos.gh,pswd,position);
				if(result==null||result.size()==0){
					JOptionPane.showMessageDialog(getParent(), "����/����/ְλ�������������룡", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//���˺������ְλ����ȷ�������ְλ���벻ͬ����ҳ��
				else {
					if(position == "˾��") {
						Login.this.dispose();
						Main_sj main_sj=new Main_sj();
						main_sj.setVisible(true);
					}
					if(position == "·�ӳ�") {
						Login.this.dispose();
						Main_ldz main_ldz=new Main_ldz();
						main_ldz.setVisible(true);
					}
					if(position == "���ӳ�") {
						Login.this.dispose();
						Main_cdz main_ldz=new Main_cdz();
						main_ldz.setVisible(true);
					}
					if(position == "����Ա") {
						Login.this.dispose();
						Main main=new Main();
						main.setVisible(true);
					}

				}
			}
		});
	}
}