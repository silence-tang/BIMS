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
					System.out.println("连接成功");
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 搭建功能框架
	 */
	public Main() {
		setTitle("尊敬的管理员，欢迎登录XD公交管理系统！");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 0, 0));
		final GPL g=new GPL();
		
		
		JButton button_1 = new JButton("录入司机信息");
		button_1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {
				Sj_update su=new Sj_update();
				su.setVisible(true);
			}
		});
		contentPane.add(button_1);
		
		
		
		JButton button_2 = new JButton("录入汽车信息");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cl_update su=new Cl_update();
				su.setVisible(true);
			}
		});
		contentPane.add(button_2);
		
		
		
		JButton button_3 = new JButton("录入违章信息");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wz_update si=new Wz_update();
				si.setVisible(true);
			}
		});
		contentPane.add(button_3);
		
		
		
		JButton button_4 = new JButton("查询司机信息");
		contentPane.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sj_find sf=new Sj_find();
				sf.setVisible(true);
			}
		});
		
		
		
		JButton button_5 = new JButton("查询司机违章信息");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sjwz_find tf=new Sjwz_find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_5);
		
		
		
		JButton button_6 = new JButton("查询线路违章信息");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Xlwz_find tf=new Xlwz_find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_6);
		
		
		
		JButton button_7 = new JButton("查询车队违章信息");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cdwz_find tf=new Cdwz_find();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_7);
		
		
		
		JButton button_8 = new JButton("车队违章统计");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cdwz_count tf=new Cdwz_count();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_8);
		
		
		
		JButton button_9 = new JButton("修改密码");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Passwd_update tf=new Passwd_update();
				tf.setVisible(true);
			}
		});
		contentPane.add(button_9);
		
		
		
		JButton button_10 = new JButton("注销登录");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.this.dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		contentPane.add(button_10);
		
		
		
		//标识信息
		JLabel lblNewLabel = new JLabel("<html><body><center>Copyright (C) 2021<br>\r\n唐子辰<br>\r\n数据库系统[01]班</center></body></html>");
		//用上面构建好的组件创建窗口
		JPanel panel = new JPanel();
		contentPane.add(panel);
		JButton button_11 = new JButton("退出系统");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(button_11);
		
		
		
		//鼠标监听
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