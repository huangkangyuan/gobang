package com.hl.v0;

import com.hl.v1.Gobangframe;

public class ApplicationStart {

    //主函数入口
    public static void main(String[] args) {
        GobangFrame gobangFrame = new GobangFrame();//初始化一个五子棋界面的对象
        gobangFrame.initUI();//调用方法进行界面的初始化
    }
}



