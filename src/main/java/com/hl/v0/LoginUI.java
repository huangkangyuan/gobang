package com.hl.v0;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

//登录界面
public class LoginUI extends JFrame implements Gobangconfig{
	public ArrayList<User> userList=new ArrayList<>();
	
	public void initUI(){
		this.setTitle("五子棋登录界面");
		this.setSize(UIWIDTH,UIHIGHTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLayout(null);//设置顶级容器JFrame为框架布局

		JButton buttonLogin=new JButton("进入游戏界面",LOGINBUTTON);
		//buttonLogin.setPreferredSize(dim2);
		buttonLogin.setBounds(550,680,dim2.width,dim2.height);
		this.add(buttonLogin);
		
		ButtonListener buttonListener=new ButtonListener(this);
		buttonLogin.addActionListener(buttonListener);
		
		this.setVisible(true);
	}
	
	//重写重绘方法
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(LOGINPICTURE, 0, 0,this.getWidth(), this.getHeight(), this);
	}
	//主函数入口
	public static void main(String[] args) {
		LoginUI ui=new LoginUI();
		ui.initUI();
	}
}
