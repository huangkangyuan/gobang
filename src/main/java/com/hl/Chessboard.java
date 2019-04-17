package com.hl;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JPanel implements Gobangconfig{

    private Gobangframe goBangframe = new Gobangframe();

    //重写重绘方法,这里重写的是第一个大的JPanel的方法
    public void paint(Graphics g) {
        super.paint(g);

        //重绘出棋盘
        g.setColor(Color.black);
        for (int i = 0; i < row; i++) {
            g.drawLine(x, y + size * i, x + size * (column - 1), y + size * i);
        }
        for (int j = 0; j < column; j++) {
            g.drawLine(x + size * j, y, x + size * j, y + size * (row - 1));
        }

        //重绘出棋子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (goBangframe.isAvail[i][j] == 1) {
                    int countx = size * i + 20;
                    int county = size * j + 20;
                    g.setColor(Color.black);
                    g.fillOval(countx - size / 2, county - size / 2, size, size);
                } else if (goBangframe.isAvail[i][j] == 2) {
                    int countx = size * i + 20;
                    int county = size * j + 20;
                    g.setColor(Color.white);
                    g.fillOval(countx - size / 2, county - size / 2, size, size);
                }
            }
        }
    }
}
