package database;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.sun.java.swing.plaf.windows.resources.windows;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Window.Type;

public class Login_error extends JDialog  {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login_error  dialog = new Login_error();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login_error() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("错误");
		setAlwaysOnTop(true);
		setBounds(100, 100, 379, 237);
		getContentPane().setLayout(null);
		{
			JButton okButton = new JButton("确定");
			okButton.setFont(new Font("宋体", Font.PLAIN, 18));
			okButton.setBounds(103, 112, 156, 36);
			getContentPane().add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login_error.this.dispose();
				}
			});
			okButton.setActionCommand("Cancel");
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel label = new JLabel("用户名或密码错误！");
		label.setForeground(Color.RED);
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		label.setBounds(69, 34, 225, 54);
		getContentPane().add(label);
		Login_error.this.setResizable(false);
		Login_error.this.setLocationRelativeTo(null);
	}
}
