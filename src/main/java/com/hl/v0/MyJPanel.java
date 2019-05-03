package com.hl;

import javax.swing.*;
import java.awt.*;


public class MyJPanel extends JPanel implements Gobangconfig {


    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(MESSAGEPICTURE, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}