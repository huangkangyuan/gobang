package com.hl.v0;


import java.awt.*;
import java.awt.event.MouseListener;

//实现对GoBangframe下棋界面的监听接口处理
public class FrameListener implements Gobangconfig, MouseListener {
    public Gobangframe gf;
    Gobangconfig go;

    public void setGraphics(Gobangframe gf) {
        this.gf = gf;
    }

    //AI联合算法
    public Integer unionWeight(Integer a, Integer b) {
        //必须要先判断a,b两个数值是不是null
        if ((a == null) || (b == null)) return 0;
            //一一:101/202
        else if ((a >= 22) && (a <= 25) && (b >= 22) && (b <= 25)) return 60;
            //一二、二一:1011/2022
        else if (((a >= 22) && (a <= 25) && (b >= 76) && (b <= 80)) || ((a >= 76) && (a <= 80) && (b >= 22) && (b <= 25)))
            return 800;
            //一三、三一、二二:10111/20222
        else if (((a >= 10) && (a <= 25) && (b >= 1050) && (b <= 1100)) || ((a >= 1050) && (a <= 1100) && (b >= 10) && (b <= 25)) || ((a >= 76) && (a <= 80) && (b >= 76) && (b <= 80)))
            return 3000;
            //眠三连和眠一连。一三、三一
        else if (((a >= 22) && (a <= 25) && (b >= 140) && (b <= 150)) || ((a >= 140) && (a <= 150) && (b >= 22) && (b <= 25)))
            return 3000;
            //二三、三二:110111
        else if (((a >= 76) && (a <= 80) && (b >= 1050) && (b <= 1100)) || ((a >= 1050) && (a <= 1100) && (b >= 76) && (b <= 80)))
            return 3000;
        else return 0;
    }

    public void mouseClicked(java.awt.event.MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //计算棋子要落在棋盘的哪个交叉点上
        int countx = ((x - (X - SIZE / 2)) / SIZE) * SIZE + X;
        int county = (y / SIZE) * SIZE + Y;
        Graphics g = gf.getGraphics();
        //计算棋盘上棋子在数组中相应的位置
        int Arrayj = (countx - X) / SIZE;
        int Arrayi = (county - Y) / SIZE;
        if ((Arrayi < 0) || (Arrayj < 0) || (Arrayi > 14) || (Arrayj > 14)) return;

        if (gf.turn != 0)//判断是否可以进行游戏
            if (gf.isAvail[Arrayi][Arrayj] != 0) {
                gf.PopUp("错误提示", "此处已经有棋子了，请下在其它地方");
            } else {
                //选择人人对战
                if (gf.ChooseType == 0) {
                    if (gf.turn == 1) {
                        //先获取要落的地方
                        g.drawImage(BLACKCHESS, countx - SIZE / 2, county - SIZE / 2, SIZE, SIZE, null);
                        //设置当前位置已经有棋子了,棋子为黑子
                        gf.isAvail[Arrayi][Arrayj] = 1;
                        //把当前所下的棋子位置保存在动态数组中
                        gf.ChessPositonList.add(new ChessPosition(Arrayi, Arrayj));
                        gf.turn++;

                        //判断是否已经出现五科棋子了
                        //列判断
                        //首先界定数组范围，防止越界
                        int imin = Arrayi - 4, imax = Arrayi + 4;
                        if (imin < 0) imin = 0;
                        if (imax > 14) imax = 14;
                        int count1 = 0;//判断相连的棋子数
                        for (int i = imin; i <= imax; i++) {
                            if (gf.isAvail[i][Arrayj] == 1) count1++;
                                //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                            else count1 = 0;
                            if (count1 == 5) {
                                gf.PopUp("游戏结果", "黑方赢");
                                gf.turn = 0;
                                return;
                            }
                        }
                        //行判断
                        //首先界定数组范围，防止越界
                        int jmin = Arrayj - 4, jmax = Arrayj + 4;
                        if (jmin < 0) jmin = 0;
                        if (jmax > 14) jmax = 14;
                        int count2 = 0;//判断相连的棋子数
                        for (int j = jmin; j <= jmax; j++) {
                            if (gf.isAvail[Arrayi][j] == 1) count2++;
                            else count2 = 0;
                            if (count2 == 5) {
                                gf.PopUp("游戏结果", "黑方赢");
                                gf.turn = 0;
                                return;
                            }
                            //如果出现了其他棋子，或者是没有棋子时，就重新开始计数

                        }
                        //135度判断
                        //首先界定数组范围，防止越界
                        int count3 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((Arrayi + i >= 0) && (Arrayj + i >= 0) && (Arrayi + i <= 14) && (Arrayj + i <= 14)) {
                                if (gf.isAvail[Arrayi + i][Arrayj + i] == 1) count3++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count3 = 0;
                                if (count3 == 5) {
                                    gf.PopUp("游戏结果", "黑方赢");
                                    gf.turn = 0;
                                    return;
                                }
                            }
                        }
                        int count4 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((Arrayi + i >= 0) && (Arrayj - i >= 0) && (Arrayi + i <= 14) && (Arrayj - i <= 14)) {
                                //System.out.print("count4:"+count4);
                                if (gf.isAvail[Arrayi + i][Arrayj - i] == 1) count4++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count4 = 0;
                                if (count4 == 5) {
                                    gf.PopUp("游戏结果", "黑方赢");
                                    gf.turn = 0;
                                    return;
                                }
                            }
                        }
                    } else if (gf.turn == 2) {
                        g.drawImage(WHITECHESS, countx - SIZE / 2, county - SIZE / 2, SIZE, SIZE, null);
                        //设置当前位置已经有棋子了，棋子为白子
                        gf.ChessPositonList.add(new ChessPosition(Arrayi, Arrayj));
                        gf.isAvail[Arrayi][Arrayj] = 2;
                        gf.turn--;

                        //列判断
                        //首先界定数组范围，防止越界
                        int imin = Arrayi - 4, imax = Arrayi + 4;
                        if (imin < 0) imin = 0;
                        if (imax > 14) imax = 14;
                        int count1 = 0;//判断相连的棋子数
                        for (int i = imin; i <= imax; i++) {
                            if (gf.isAvail[i][Arrayj] == 2) count1++;

                                //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                            else count1 = 0;
                            if (count1 == 5) {
                                gf.PopUp("游戏结果", "白方赢");
                                gf.turn = 0;
                                return;
                            }
                        }
                        //行判断
                        //首先界定数组范围，防止越界
                        int jmin = Arrayj - 4, jmax = Arrayj + 4;
                        if (jmin < 0) jmin = 0;
                        if (jmax > 14) jmax = 14;
                        int count2 = 0;//判断相连的棋子数
                        for (int j = jmin; j <= jmax; j++) {
                            if (gf.isAvail[Arrayi][j] == 2) count2++;
                                //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                            else count2 = 0;
                            if (count2 == 5) {
                                gf.PopUp("游戏结果", "白方赢");
                                gf.turn = 0;
                                return;
                            }

                        }
                        //135度判断
                        //首先界定数组范围，防止越界
                        int count3 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((Arrayi + i >= 0) && (Arrayj + i >= 0) && (Arrayi + i <= 14) && (Arrayj + i <= 14)) {
                                if (gf.isAvail[Arrayi + i][Arrayj + i] == 2) count3++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count3 = 0;
                                if (count3 == 5) {
                                    gf.PopUp("游戏结果", "白方赢");
                                    gf.turn = 0;
                                    return;
                                }
                            }
                        }
                        int count4 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((Arrayi + i >= 0) && (Arrayj - i >= 0) && (Arrayi + i <= 14) && (Arrayj - i <= 14)) {
                                if (gf.isAvail[Arrayi + i][Arrayj - i] == 2) count4++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count4 = 0;
                                if (count4 == 5) {
                                    gf.PopUp("游戏结果", "白方赢");
                                    gf.turn = 0;
                                    return;
                                }
                            }
                        }
                    }
                }
                //如果选择的是人机对战
                else {
                    if (gf.turn == 1) {

                        //人先落子
                        //先获取要落的地方
                        g.drawImage(BLACKCHESS, countx - SIZE / 2, county - SIZE / 2, SIZE, SIZE, null);
                        //设置当前位置已经有棋子了,棋子为黑子
                        gf.isAvail[Arrayi][Arrayj] = 1;
                        //把当前所下的棋子位置保存在动态数组中
                        gf.ChessPositonList.add(new ChessPosition(Arrayi, Arrayj));
                        gf.turn++;

                        //判断是否已经出现五科棋子了
                        //列判断
                        //首先界定数组范围，防止越界
                        int Blackimin = Arrayi - 4, Blackimax = Arrayi + 4;
                        if (Blackimin < 0) Blackimin = 0;
                        if (Blackimax > 14) Blackimax = 14;
                        int count1 = 0;//判断相连的棋子数
                        for (int i = Blackimin; i <= Blackimax; i++) {
                            if (gf.isAvail[i][Arrayj] == 1) count1++;
                                //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                            else count1 = 0;
                            if (count1 == 5) {
                                gf.PopUp("游戏结果", "黑方赢");
                                return;
                            }
                        }
                        //行判断
                        //首先界定数组范围，防止越界
                        int Blackjmin = Arrayj - 4, Blackjmax = Arrayj + 4;
                        if (Blackjmin < 0) Blackjmin = 0;
                        if (Blackjmax > 14) Blackjmax = 14;
                        int count2 = 0;//判断相连的棋子数
                        for (int j = Blackjmin; j <= Blackjmax; j++) {
                            if (gf.isAvail[Arrayi][j] == 1) count2++;
                            else count2 = 0;
                            if (count2 == 5) {
                                gf.PopUp("游戏结果", "黑方赢");
                                return;
                            }
                            //如果出现了其他棋子，或者是没有棋子时，就重新开始计数

                        }
                        //135度判断
                        //首先界定数组范围，防止越界
                        int count3 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((Arrayi + i >= 0) && (Arrayj + i >= 0) && (Arrayi + i <= 14) && (Arrayj + i <= 14)) {
                                if (gf.isAvail[Arrayi + i][Arrayj + i] == 1) count3++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count3 = 0;
                                if (count3 == 5) {
                                    gf.PopUp("游戏结果", "黑方赢");
                                    return;
                                }
                            }
                        }
                        int count4 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((Arrayi + i >= 0) && (Arrayj - i >= 0) && (Arrayi + i <= 14) && (Arrayj - i <= 14)) {
                                //System.out.print("count4:"+count4);
                                if (gf.isAvail[Arrayi + i][Arrayj - i] == 1) count4++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count4 = 0;
                                if (count4 == 5) {
                                    gf.PopUp("游戏结果", "黑方赢");
                                    return;
                                }
                            }
                        }

                        //机器落子
                        //先计算出各个位置的权值
                        for (int i = 0; i < gf.isAvail.length; i++) {
                            for (int j = 0; j < gf.isAvail[i].length; j++) {
                                //首先判断当前位置是否为空
                                if (gf.isAvail[i][j] == 0) {
                                    //往左延伸
                                    String ConnectType = "0";
                                    int jmin = Math.max(0, j - 4);
                                    for (int positionj = j - 1; positionj >= jmin; positionj--) {
                                        //依次加上前面的棋子
                                        ConnectType = ConnectType + gf.isAvail[i][positionj];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置中
                                    Integer valueleft = gf.map.get(ConnectType);
                                    if (valueleft != null) gf.weightArray[i][j] += valueleft;

                                    //往右延伸
                                    ConnectType = "0";
                                    int jmax = Math.min(14, j + 4);
                                    for (int positionj = j + 1; positionj <= jmax; positionj++) {
                                        //依次加上前面的棋子
                                        ConnectType = ConnectType + gf.isAvail[i][positionj];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置中
                                    Integer valueright = gf.map.get(ConnectType);
                                    if (valueright != null) gf.weightArray[i][j] += valueright;

                                    //联合判断，判断行
                                    gf.weightArray[i][j] += unionWeight(valueleft, valueright);

                                    //往上延伸
                                    ConnectType = "0";
                                    int imin = Math.max(0, i - 4);
                                    for (int positioni = i - 1; positioni >= imin; positioni--) {
                                        //依次加上前面的棋子
                                        ConnectType = ConnectType + gf.isAvail[positioni][j];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置中
                                    Integer valueup = gf.map.get(ConnectType);
                                    if (valueup != null) gf.weightArray[i][j] += valueup;

                                    //往下延伸
                                    ConnectType = "0";
                                    int imax = Math.min(14, i + 4);
                                    for (int positioni = i + 1; positioni <= imax; positioni++) {
                                        //依次加上前面的棋子
                                        ConnectType = ConnectType + gf.isAvail[positioni][j];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置中
                                    Integer valuedown = gf.map.get(ConnectType);
                                    if (valuedown != null) gf.weightArray[i][j] += valuedown;

                                    //联合判断，判断列
                                    gf.weightArray[i][j] += unionWeight(valueup, valuedown);

                                    //往左上方延伸,i,j,都减去相同的数
                                    ConnectType = "0";
                                    for (int position = -1; position >= -4; position--) {
                                        if ((i + position >= 0) && (i + position <= 14) && (j + position >= 0) && (j + position <= 14))
                                            ConnectType = ConnectType + gf.isAvail[i + position][j + position];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置
                                    Integer valueLeftUp = gf.map.get(ConnectType);
                                    if (valueLeftUp != null) gf.weightArray[i][j] += valueLeftUp;

                                    //往右下方延伸,i,j,都加上相同的数
                                    ConnectType = "0";
                                    for (int position = 1; position <= 4; position++) {
                                        if ((i + position >= 0) && (i + position <= 14) && (j + position >= 0) && (j + position <= 14))
                                            ConnectType = ConnectType + gf.isAvail[i + position][j + position];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置
                                    Integer valueRightDown = gf.map.get(ConnectType);
                                    if (valueRightDown != null) gf.weightArray[i][j] += valueRightDown;

                                    //联合判断，判断行
                                    gf.weightArray[i][j] += unionWeight(valueLeftUp, valueRightDown);

                                    //往左下方延伸,i加,j减
                                    ConnectType = "0";
                                    for (int position = 1; position <= 4; position++) {
                                        if ((i + position >= 0) && (i + position <= 14) && (j - position >= 0) && (j - position <= 14))
                                            ConnectType = ConnectType + gf.isAvail[i + position][j - position];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置
                                    Integer valueLeftDown = gf.map.get(ConnectType);
                                    if (valueLeftDown != null) gf.weightArray[i][j] += valueLeftDown;

                                    //往右上方延伸,i减,j加
                                    ConnectType = "0";
                                    for (int position = 1; position <= 4; position++) {
                                        if ((i - position >= 0) && (i - position <= 14) && (j + position >= 0) && (j + position <= 14))
                                            ConnectType = ConnectType + gf.isAvail[i - position][j + position];
                                    }
                                    //从数组中取出相应的权值，加到权值数组的当前位置
                                    Integer valueRightUp = gf.map.get(ConnectType);
                                    if (valueRightUp != null) gf.weightArray[i][j] += valueRightUp;

                                    //联合判断，判断行
                                    gf.weightArray[i][j] += unionWeight(valueLeftDown, valueRightUp);
                                }
                            }
                        }

                        //取出最大的权值
                        int AIi = 0, AIj = 0;
                        int weightmax = 0;
                        for (int i = 0; i < go.ROW; i++) {
                            for (int j = 0; j < go.COLUMN; j++) {
                                if (weightmax < gf.weightArray[i][j]) {
                                    weightmax = gf.weightArray[i][j];
                                    AIi = i;
                                    AIj = j;
                                }
                            }
                        }

                        //确定位置，落子
                        //i对应y，j对应x
                        countx = X + AIj * SIZE;
                        county = Y + AIi * SIZE;
                        g.drawImage(WHITECHESS, countx - SIZE / 2, county - SIZE / 2, SIZE, SIZE, null);
                        //设置当前位置已经有棋子了，棋子为白子
                        gf.ChessPositonList.add(new ChessPosition(AIi, AIj));
                        gf.isAvail[AIi][AIj] = 2;
                        gf.turn--;

                        //落子以后重置权值数组weightArray
                        for (int i = 0; i < go.COLUMN; i++)
                            for (int j = 0; j < go.ROW; j++)
                                gf.weightArray[i][j] = 0;

                        //列判断
                        //首先界定数组范围，防止越界
                        int imin = AIi - 4, imax = AIi + 4;
                        if (imin < 0) imin = 0;
                        if (imax > 14) imax = 14;
                        count1 = 0;//判断相连的棋子数
                        for (int i = imin; i <= imax; i++) {
                            if (gf.isAvail[i][AIj] == 2) count1++;

                                //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                            else count1 = 0;
                            if (count1 == 5) {
                                gf.PopUp("游戏结果", "白方赢");
                                gf.turn = 0;
                                return;
                            }
                        }
                        //行判断
                        //首先界定数组范围，防止越界
                        int jmin = AIj - 4, jmax = AIj + 4;
                        if (jmin < 0) jmin = 0;
                        if (jmax > 14) jmax = 14;
                        count2 = 0;//判断相连的棋子数
                        for (int j = jmin; j <= jmax; j++) {
                            if (gf.isAvail[AIi][j] == 2) count2++;
                                //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                            else count2 = 0;
                            if (count2 == 5) {
                                gf.PopUp("游戏结果", "白方赢");
                                gf.turn = 0;
                                return;
                            }

                        }
                        //135度判断
                        //首先界定数组范围，防止越界
                        count3 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((AIi + i >= 0) && (AIj + i >= 0) && (AIi + i <= 14) && (AIj + i <= 14)) {
                                if (gf.isAvail[AIi + i][AIj + i] == 2) count3++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count3 = 0;
                                if (count3 == 5) {
                                    gf.PopUp("游戏结果", "白方赢");
                                    gf.turn = 0;
                                    return;
                                }
                            }
                        }
                        count4 = 0;//判断相连的棋子数
                        for (int i = -4; i <= 4; i++) {
                            if ((AIi + i >= 0) && (AIj - i >= 0) && (AIi + i <= 14) && (AIj - i <= 14)) {
                                if (gf.isAvail[AIi + i][AIj - i] == 2) count4++;
                                    //如果出现了其他棋子，或者是没有棋子时，就重新开始计数
                                else count4 = 0;
                                if (count4 == 5) {
                                    gf.PopUp("游戏结果", "白方赢");
                                    gf.turn = 0;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
    }


    // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    // Method descriptor #8 (Ljava/awt/event/MouseEvent;)V
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}