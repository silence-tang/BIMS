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
public class Main_ldz extends JFrame {

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
					Main_ldz frame = new Main_ldz();
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
	public Main_ldz() {
		
		//通过工号获取路队长姓名
		String cc=Login_infos.gh;
		Vector detail_1=Sql_connetcton.getname(2,cc);
		String name = (String) detail_1.get(0);
		//通过工号获取路队长所在线路名
		Vector detail_2=Sql_connetcton.getname(4,cc);
		Login_infos.xlname = (String) detail_2.get(0);
		
		//System.out.println(name);
	    //System.out.println(Login_infos.cdnum);
		
		setTitle("尊敬的路队长"+Login_infos.gh+name+"，欢迎登录公交管理系统！");
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
				Sj_update_xl su=new Sj_update_xl();
				su.setVisible(true);
			}
		});
		contentPane.add(button_1);
		
		
		
		JButton button_2 = new JButton("录入汽车信息");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cl_update_xl su=new Cl_update_xl();
				su.setVisible(true);
			}
		});
		contentPane.add(button_2);
		
		
		
		JButton button_3 = new JButton("录入违章信息");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wz_update_xl si=new Wz_update_xl();
				si.setVisible(true);
			}
		});
		contentPane.add(button_3);
		
		
		
		JButton button_4 = new JButton("查询本线路司机信息");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sj_find_xl si=new Sj_find_xl();
				si.setVisible(true);
			}
		});
		contentPane.add(button_4);
		
		
		
		JButton button_5 = new JButton("<html><body><center>查询本线路\r\n司机违章信息</center></body></html>");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sjwz_find_xl si=new Sjwz_find_xl();
				si.setVisible(true);
			}
		});
		contentPane.add(button_5);
		
		
		
		JButton button_6 = new JButton("修改密码");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Passwd_update si=new Passwd_update();
				si.setVisible(true);
			}
		});
		contentPane.add(button_6);
		
		
		
		JButton button_7 = new JButton("注销登录");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_ldz.this.dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		contentPane.add(button_7);
		
		
		
		//标识信息
		JLabel lblNewLabel = new JLabel("<html><body><center>Copyright (C) 2021<br>\r\n唐子辰<br>\r\n数据库系统[01]班</center></body></html>");
		//用上面构建好的组件创建窗口
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		
		
		JButton button_9 = new JButton("退出系统");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(button_9);

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