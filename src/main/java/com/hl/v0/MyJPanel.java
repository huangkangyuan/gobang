package com.hl.v0;

import javax.swing.*;
import java.awt.*;


public class MyJPanel extends JPanel implements GobangConfig {


    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(MESSAGEPICTURE, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}