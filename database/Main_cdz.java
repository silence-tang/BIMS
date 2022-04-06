package database;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.GridLayout;

import javax.swing.JLabel;
@SuppressWarnings("serial")
public class Main_cdz extends JFrame {

	private JPanel contentPane;
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
					Main_cdz frame = new Main_cdz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 ����ܿ��
	 */
	public Main_cdz() {
		
		//ͨ�����Ż�ȡ���ӳ�����
		String cc=Login_infos.gh;
		Vector detail_1=Sql_connetcton.getname(3,cc);
		String name = (String) detail_1.get(0);
		//ͨ�����Ż�ȡ���ӳ����ڳ��Ӻ�
		Vector detail_2=Sql_connetcton.getname(5,cc);
		Login_infos.cdnum = (String) detail_2.get(0);
		
		setTitle("�𾴵ĳ��ӳ�"+Login_infos.gh+name+"����ӭ��¼XD��������ϵͳ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		final GPL g=new GPL();
		
		
		JButton button_1 = new JButton("¼��˾����Ϣ");
		button_1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				Sj_update_cd su=new Sj_update_cd();
				su.setVisible(true);
			}
		});
		contentPane.add(button_1);
		
		
		
		JButton button_2 = new JButton("¼��������Ϣ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cl_update_cd su=new Cl_update_cd();
				su.setVisible(true);
			}
		});
		contentPane.add(button_2);
		
		
		
		JButton button_3 = new JButton("¼��Υ����Ϣ");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wz_update_cd si=new Wz_update_cd();
				si.setVisible(true);
			}
		});
		contentPane.add(button_3);
		
		
		
		JButton button_4 = new JButton("��ѯ������˾����Ϣ");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sj_find_cd si=new Sj_find_cd();
				si.setVisible(true);
			}
		});
		contentPane.add(button_4);
		
		
		
		JButton button_5 = new JButton("<html><body><center>��ѯ������\r\n˾��Υ����Ϣ</center></body></html>");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sjwz_find_cd si=new Sjwz_find_cd();
				si.setVisible(true);
			}
		});
		contentPane.add(button_5);
		
		
		
		JButton button_6 = new JButton("<html><body><center>��ѯ������\r\nΥ��ͳ����Ϣ</center></body></html>");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sjwz_count_cd si=new Sjwz_count_cd();
				si.setVisible(true);
			}
		});
		contentPane.add(button_6);
		
		
		
		JButton button_7 = new JButton("�޸�����");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Passwd_update si=new Passwd_update();
				si.setVisible(true);
			}
		});
		contentPane.add(button_7);
		
		
		
		JButton button_8 = new JButton("ע����¼");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_cdz.this.dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		contentPane.add(button_8);
		
		
		
		JButton button_9 = new JButton("�˳�ϵͳ");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(button_9);

		
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}