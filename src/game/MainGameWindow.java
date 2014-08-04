/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.abs;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeditor.FileManager;

public class MainGameWindow extends Applet implements Runnable, KeyListener, MouseListener {

    private boolean game = true;
    static FileManager m = new FileManager("res/world.png");
    static FightManager f;
    public static Being hero = new Being("Avatar", 10, 10, 500, "res/char.png");
    Being enemy[] = {new Being("Guard", 16, 15, 32, "res/enemy.png"), new Being("Guard", 18, 15, 62, "res/enemy.png"), new Being("Guard", 20, 15, 42, "res/enemy.png"), new Being("Paladin", 20, 10, 120, "res/paladin.png")};
    Frame frame;
    int framesPerSecond = 0;
    static int frames = 0;
    public static int mapW = 10, mapH = 10, mapOffsetW = 1, mapOffsetH = 1;
    private Image image;
    private Graphics second;
    private boolean menu = false;

    @Override
    public void stop() {
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        Thread t = new Thread(this);
        t.start();
        setSize(400, 800);
        setBackground(Color.black);
        frame = (Frame) this.getParent().getParent();
        frame.setTitle("Game");
        mapOffsetW = (frame.getWidth() - mapW * 18) / 8;
        mapOffsetH = frame.getHeight() / 5;
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        this.getGraphics().setFont(m.font);
    }

    @Override
    public void run() {
        f = new FightManager(this.getGraphics(), 9, hero, enemy);
        float prevTime = System.currentTimeMillis();
        while (menu) {
            drawMenuScreen(m.font);
        }
        while (game) {
            repaint();
            frames++;
            frames %= 100;
            f.update();
            f.checkFight();
            try {
                Thread.sleep(200);
                framesPerSecond = 0;
            } catch (InterruptedException ex) {
                Logger.getLogger(MainGameWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Being e : enemy) {
                e.AI(hero);
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        if (game) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
                //g2.setStroke(new BasicStroke(4));
            //g2.setFont(m.font);
            g2.drawString("Hero x = " + hero.x + " Hero y = " + hero.y, 10, 10);
            //g2.drawString("Enemy x = "+enemy.x+" Enemy y = "+enemy.y, 10, 20);
            g2.drawString("Fps =" + framesPerSecond / 1000, 10, 30);
            displayMap(g);
            if (!hero.alive) {
                g2.setColor(new Color(20, 10, 10, 150));
                g2.fillRect(mapOffsetW, mapOffsetH, (mapW + 1) * m.imageW, (mapH + 1) * m.imageH);
                g2.setColor(Color.red);
                g2.drawString("You are dead.", mapW * m.imageW / 2 + mapOffsetW, mapH * m.imageH + m.imageH + mapOffsetH);
            }
        }
    }

    public static boolean isVisible(int x, int y) {
        if (abs(hero.y - y) < hero.y - mapH / 2 && abs(hero.y - x) < hero.x - mapW) {
            return true;
        }
        return false;
    }

    public void displayMap(Graphics g) {
        int num = 0, windowH = frame.getHeight() / m.imageW, windowW = frame.getWidth() / m.imageW;
        int x = 0, y = 0;
        for (int layerT = 0; layerT <= 1; layerT++) {

            for (int i = hero.y - mapH / 2; i < m.map[0].length - 1 && y <= mapH && i >= 0; i++, y++) {

                for (int j = hero.x - mapW / 2; j < m.map[0].length - 1 && x <= mapW && j > 0; j++, x++) {
                    if ((layerT == 1 && m.map[layerT][j][i].ID > 0) || layerT == 0) {
                        g.drawImage(m.map[layerT][j][i].sprite, m.imageW * x + mapOffsetW, m.imageH * y + mapOffsetH, frame);
                    }
                    //g.drawImage(hero.img, mapW/2*m.imageW +mapOffsetW, mapH/2*m.imageH+mapOffsetH, frame);
                    if (j == hero.x && i == hero.y) {
                        hero.draw(g, x * m.imageW + mapOffsetW, y * m.imageH + mapOffsetH, frame);
                        if (hero.hurt) {
                            g.setColor(Color.red);
                            g.drawString(new String(hero.damage + ""), x * m.imageW + m.imageW + mapOffsetW, y * m.imageH + 2 * frames % 4 + mapOffsetH);
                            if (frames % 4 == 0) {
                                hero.hurt = false;
                            }
                        }
                    }
                    for (Being e : enemy) {
                        if (i == e.y && j == e.x) {
                            e.draw(g, (x * m.imageW) + mapOffsetW, (y * m.imageH) + mapOffsetH, frame);
                            if (e.equals(hero.target)) {
                                g.setColor(Color.red);
                                g.drawRect(x * m.imageW + mapOffsetW, y * m.imageH + mapOffsetH, m.imageW, m.imageH);
                            }
                            if (e.hurt) {
                                g.setColor(Color.red);
                                g.drawString(new String(e.damage + ""), x * m.imageW + m.imageW + mapOffsetW, y * m.imageH + 2 * frames % 4 + mapOffsetH);
                                if (frames % 4 == 0) {
                                    e.hurt = false;
                                }
                            }
                        }
                    }
                }
                x = 0;
            }
            y = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (hero.alive) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    hero.move(-2);
                    break;

                case KeyEvent.VK_DOWN:
                    hero.move(2);
                    break;

                case KeyEvent.VK_LEFT:
                    hero.move(-1);
                    break;

                case KeyEvent.VK_RIGHT:
                    hero.move(1);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void drawMenuScreen(Font f) {
        Graphics2D g2 = (Graphics2D) this.getGraphics();
        g2.setFont(f);
        g2.setColor(Color.red);
        //g2.setFont(m.font);
        String[] menu = {"New Game", "Load Game", "Exit"};
        int offset = this.getWidth() - f.getSize() - 40;
        for (String s : menu) {
            g2.drawString(s, this.getWidth() / 2 - s.length() * f.getSize() / 3, offset);
            offset += f.getSize() + 10;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click");
        int clickX = e.getX() / m.imageW - mapOffsetW / m.imageW + hero.x;
        int clickY = e.getY() / m.imageH - mapOffsetH / m.imageH + hero.y;
        System.out.println(clickX + " " + clickY);
        for (Being en : enemy) {
            if (en.x == clickX && en.y == clickY) {
                System.out.println("Target is " + en.name + "cords :" + en.x + " " + en.y);
                hero.target = en;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
