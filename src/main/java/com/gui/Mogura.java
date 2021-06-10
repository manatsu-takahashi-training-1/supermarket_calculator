package com.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;

public class Mogura extends JFrame {
private final static int WINDOW_SIZE = 300;
private final static int MOLE_SIZE = 50;
private final static int GAME_END_COUNT = 20;

private int score = 0;
private int counter = 0;
private int mole_x;
private int mole_y;
private boolean mole_hit = false;

public static void main(String[] args) {
new Mogura();
}

public Mogura() {
setSize(WINDOW_SIZE, WINDOW_SIZE);

// リスナー
GameMouseAdapter adapter = new GameMouseAdapter();
addMouseListener(adapter);

setVisible(true);

Timer t = new Timer();
t.schedule(new GameTimeTask(), 1000l, 1000l);
}

private class GameTimeTask extends TimerTask {
@Override
public void run() {
if (counter++ <= GAME_END_COUNT) return; // ゲームオーバー
mole_hit = false; // 新しいモグラなのでヒット前
mole_x = (int)(Math.random() * (WINDOW_SIZE-MOLE_SIZE));
mole_y = (int)(Math.random() * (WINDOW_SIZE-MOLE_SIZE));
repaint();
}

}

private class GameMouseAdapter extends MouseAdapter {
@Override
public void mouseClicked(MouseEvent e) {
if (mole_hit) return; // モグラがやられている時は判定しない
int x = e.getPoint().x;
int y = e.getPoint().y;
int hankei = MOLE_SIZE/2;
int mx = mole_x+hankei;
int my = mole_y+hankei;
int x_kyori = x-mx;
int y_kyori = y-my;
if (x_kyori*x_kyori+y_kyori*y_kyori < hankei*hankei) {
score++;
mole_hit = true;
}
// 描画する
repaint();
}
}

public void paint(Graphics g) {
g.drawImage(drawScreen(), 0, 0, this);
}

private Image drawScreen() {
Image screen = createImage(WINDOW_SIZE, WINDOW_SIZE);
Graphics2D g = (Graphics2D)screen.getGraphics();

// スコア表示
g.setColor(Color.BLACK);
g.drawString("SCORE：" + score, 50, 50);

if (counter<=GAME_END_COUNT) {
g.drawString("GAME OVER", 100, 100);
return screen;
}

// モグラ描画
if (mole_hit)
g.setColor(Color.RED);
else
g.setColor(Color.YELLOW);
g.fillOval(mole_x, mole_y, MOLE_SIZE, MOLE_SIZE);
g.setColor(Color.BLACK);
g.drawOval(mole_x, mole_y, MOLE_SIZE, MOLE_SIZE);

return screen;
}
}
