package com.hl;

import javax.swing.*;
import java.awt.*;

public class Chessboard extends JPanel {


    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Image image = new ImageIcon("images/qipan1.png").getImage();
        graphics.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), this);
    }
}
