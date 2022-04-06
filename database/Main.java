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
import java.awt.GridLayout;

import javax.swing.JLabel;
@SuppressWarnings("serial")
public class Main extends JFrame {

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
					Main frame = new Main();
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
	public Main() {
		setTitle("�𾴵Ĺ���Ա����ӭ��¼XD��������ϵͳ��");
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
				Sj_update su=new Sj_update();
				su.setVisible(true);
			}
		});
		contentPane.add(button_1);
		
		
		
		JButton button_2 = new JButton("¼��������Ϣ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cl_update su=new Cl_update();
				su.setVisible(true);
			}
		});
		contentPane.add(button_2);
		
		
		
		JButton button_3 = new JButton("¼��Υ����Ϣ");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wz_update si=new Wz_update();
				si.setVisible(true);
			}
		});
		contentPane.add(button_3);
		
		
		
		JButton button_4 = new JButton("��ѯ˾����Ϣ");
		contentPane.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sj_find sf=new Sj_find();
				sf.setVisible(true);
			}
		});
		
		
		
		JButton button_5 = new JButton("��ѯ˾��Υ����Ϣ");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sjwz_find tf=new Sjwz_find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_5);
		
		
		
		JButton button_6 = new JButton("��ѯ��·Υ����Ϣ");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Xlwz_find tf=new Xlwz_find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_6);
		
		
		
		JButton button_7 = new JButton("��ѯ����Υ����Ϣ");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cdwz_find tf=new Cdwz_find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_7);
		
		
		
		JButton button_8 = new JButton("����Υ��ͳ��");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cdwz_count tf=new Cdwz_count();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_8);
		
		
		
		JButton button_9 = new JButton("�޸�����");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Passwd_update tf=new Passwd_update();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_9);
		
		
		
		JButton button_10 = new JButton("ע����¼");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.this.dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		contentPane.add(button_10);
		
		
		
		//��ʶ��Ϣ
		JLabel lblNewLabel = new JLabel("<html><body><center>Copyright (C) 2021<br>\r\n���ӳ�<br>\r\n���ݿ�ϵͳ[01]��</center></body></html>");
		//�����湹���õ������������
		JPanel panel = new JPanel();
		contentPane.add(panel);
		JButton button_11 = new JButton("�˳�ϵͳ");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(button_11);
		
		
		
		//������
		lblNewLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				g.setVisible(true);
				
			}
		});
		panel.add(lblNewLabel);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}