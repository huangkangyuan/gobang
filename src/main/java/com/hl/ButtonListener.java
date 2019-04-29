package com.hl;//设置按钮监听方法ButttonLitener类

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//实现对JPanel的监听接口处理
public class ButtonListener implements Gobangconfig,ActionListener{
    public Gobangframe gf;

    public ButtonListener(Gobangframe gf) {
        this.gf=gf;//获取左半部分的画板
    }

    //当界面发生操作时进行处理
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击按钮的内容，判断是不是"开始新游戏"这个按钮
        if(e.getActionCommand().equals("开始新游戏")) {
            //如果是开始新游戏的按钮，再为左半部分设置监听方法
            FrameListener fl=new FrameListener();
            fl.setGraphics(gf);//获取画笔对象
            gf.addMouseListener(fl);
        }
        //判断当前点击的按钮是不是悔棋
        else if(e.getActionCommand().equals("悔棋")) {
            if(gf.ChessPositonList.size()>1) {
                //把棋子数组相应的位置置为0；
                ChessPosition l=new ChessPosition();
                //获取最后一个棋子的对象信息
                l=gf.ChessPositonList.remove(gf.ChessPositonList.size()-1);
                //把相应的数组位置置为0
                gf.isAvail[l.Listi][l.Listj]=0;
                //把玩家还原为上一步的玩家
                if(gf.turn==1) gf.turn++;
                else gf.turn--;

                //直接调用gf的重绘方法，重绘方法的画笔应该是在棋盘页面还没生成的时候就要获取
                //调用repaint会自动调用paint方法，而且不用给参数
                gf.repaint();
                //gf.paint(gf.getGraphics());

            }
            else {
                System.out.println("不能悔棋!");
            }
        }
        else if(e.getActionCommand().equals("认输")) {
            if(gf.turn==1) System.out.println("白方赢");
            else System.out.println("黑方赢");
        }
    }

}