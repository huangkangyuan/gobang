package com.hl;

import javax.swing.*;

public class Gobang extends JFrame {

    public Gobang(){
        this.setSize(1300, 1300);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("五子棋");

        //最大化  可选
//        this.setResizable(false);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu("选项");
        JMenuItem jMenuItem1_1 = new JMenuItem("重新开始");
        JMenuItem jMenuItem1_2 = new JMenuItem("排行榜");
        JMenuItem jMenuItem1_3 = new JMenuItem("退出游戏");
        jMenu1.add(jMenuItem1_1);
        jMenu1.add(jMenuItem1_2);
        jMenu1.add(jMenuItem1_3);

        JMenu jMenu2 = new JMenu("设置");
        JMenuItem jMenuItem2_1 = new JMenuItem("更换棋盘");
        JMenuItem jMenuItem2_2 = new JMenuItem("更换棋盘");
        jMenu2.add(jMenuItem2_1);
        jMenu2.add(jMenuItem2_2);

        JMenu jMenu3 = new JMenu("帮助");
        JMenuItem jMenuItem3 = new JMenuItem("关于我们");
        jMenu3.add(jMenuItem3);

        //菜单栏
        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);

        this.setJMenuBar(jMenuBar);

        //添加背景图片
        this.add(new Chessboard());
    }

    public static void main(String[] args) {
        Gobang wzi = new Gobang();
        Chessboard chessboard = new Chessboard();
    }
}
