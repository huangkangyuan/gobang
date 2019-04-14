package com.hl;

import javax.swing.*;

public class Gobang extends JFrame {

    public Gobang(){
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("五子棋");

        //最大化  可选
        this.setResizable(false);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("选项");

        //菜单栏
        jMenuBar.add(jMenu);

        this.setJMenuBar(jMenuBar);

    }

    public static void main(String[] args) {
        Gobang wzi = new Gobang();
    }
}
