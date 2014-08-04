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
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeditor.FileManager;

public class MainGameWindow extends Applet implements Runnable,KeyListener {
    private boolean game=true;
    FileManager m = new FileManager("res/world.png");
    Being hero = new Being("Avatar",10,10,500,"res/char.png");
    Being enemy[] = {new Being("Guard",16,15,32,"res/enemy.png"),new Being("Guard",18,15,62,"res/enemy.png"),new Being("Guard",20,15,42,"res/enemy.png"),new Being("Paladin",20,10,220,"res/enemy.png")};
    Frame frame;
    int framesPerSecond=0;
    int animationFrames=0;
    int mapW=10,mapH=10,mapOffsetW=1,mapOffsetH=1;
    private Image image;
    private Graphics second;
    private boolean menu=false;

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
        setSize(400,800);
        setBackground(Color.black);
        frame = (Frame) this.getParent().getParent();
        frame.setTitle("Swotal");
        mapOffsetW=(frame.getWidth()-mapW*18)/8;mapOffsetH=frame.getHeight()/12;
        setFocusable(true);
        addKeyListener(this);
        this.getGraphics().setFont(m.font);
        System.out.println(m.font);
    }

    @Override
    public void run() {
        float prevTime= System.currentTimeMillis(); 
        while(menu){
            drawMenuScreen(m.font);
        }
        while(game){
            repaint();
            
            framesPerSecond++;
            float currentTime = System.currentTimeMillis();
            framesPerSecond += currentTime - prevTime;
            
            if(framesPerSecond>24000){
                try {
                    Thread.sleep(17);
                    framesPerSecond=0;           
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainGameWindow.class.getName()).log(Level.SEVERE, null, ex);
                } 
                for (Being e: enemy ) {
                    e.AI(hero);
                }       
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
            if(game){
		Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.red);
                //g2.setStroke(new BasicStroke(4));
                //g2.setFont(m.font);
                g2.drawString("Hero x = "+hero.x+" Hero y = "+hero.y, 10, 10);
                //g2.drawString("Enemy x = "+enemy.x+" Enemy y = "+enemy.y, 10, 20);
                g2.drawString("Fps ="+framesPerSecond/1000, 10, 30);
                displayMap(g);
            }
	}
        
        public void displayMap(Graphics g){                     
            int num=0,windowH=frame.getHeight()/m.imageW,windowW=frame.getWidth()/m.imageW;
            System.out.println("Rysuje mape :"+mapW+mapH);
            int x=0,y=0;
            for (int layerT = 0; layerT <= 1; layerT++) {

               for (int i = hero.y-mapH/2; i < m.map[0].length-1&&y<=mapH&&i>=0; i++,y++) {
                   
                   for (int j = hero.x-mapW/2; j < m.map[0].length-1&&x<=mapW&&j>0; j++,x++) {
                            if((layerT==1&&m.map[layerT][j][i].ID>0) ||layerT==0){
                                g.drawImage(m.map[layerT][j][i].sprite, m.imageW * x+mapOffsetW, m.imageH * y+mapOffsetH, frame);
                            }
                           //g.drawImage(hero.img, mapW/2*m.imageW +mapOffsetW, mapH/2*m.imageH+mapOffsetH, frame);
                           if(j==hero.x&&i==hero.y){
                               hero.draw(g, x*m.imageW +mapOffsetW, y*m.imageH+mapOffsetH, frame);
                           }
                           for (Being e: enemy ) {
                                if(i==e.y&&j==e.x){
                                e.draw(g,(x*m.imageW) +mapOffsetW, (y*m.imageH)+mapOffsetH, frame);
                                }
                           }    
                   }
                   x=0;
               }
               y=0;
            }  
        }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
         switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.y--;
            break;

            case KeyEvent.VK_DOWN:
                hero.y++;
            break;

            case KeyEvent.VK_LEFT:
                hero.x--;
            break;

            case KeyEvent.VK_RIGHT:
                hero.x++;
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    void drawMenuScreen(Font f){
                Graphics2D g2 = (Graphics2D) this.getGraphics();
                g2.setFont(f);
                g2.setColor(Color.red);
                //g2.setFont(m.font);
                String[] menu = {"New Game","Load Game","Exit"};
                int offset=this.getWidth()-f.getSize()-40;              
                for(String s:menu){
                    g2.drawString(s, this.getWidth()/2-s.length()*f.getSize()/3, offset);
                    offset+=f.getSize()+10;
                }
                
                
    }
}
