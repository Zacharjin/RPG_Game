/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import mapeditor.FileManager;

/**
 *
 * @author Piotr
 */
public class Being{
    Being target;
    public int x=0,y=0,hp=0,maxHp=0;
    boolean alive=true;
    String name;
    public Image img;
    int atk=5,def=1,damage=0;
    boolean hurt = false;
    public Being(){}
    public Being(String name,int x,int y,int hp){
        this.x=x;
        this.y=y;
        this.hp=hp;
        this.maxHp=hp;
        this.name=name;
    }
    public Being(String name,int x,int y,int hp,Image img){
        this(name,x,y,hp);
        this.img =img;     
    }
    public Being(String name,int x,int y,int hp,String img){
        this(name,x,y,hp);
        try{
            this.img = ImageIO.read(new File(img));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void AI(Being target){
        
        if(target.alive&&MainGameWindow.frames %5==0){
            this.target=target;
            if(target!=this){
            int dir=9;
            if(alive){
                int movementX= target.x-this.x;
                int movementY=target.y-this.y;
                if(6>abs(movementX)&&6>abs(movementY)){
                    if(abs(movementX)>=abs(movementY)){
                        if(movementX>1){
                            dir=1;
                        }else if(movementX<1) {dir=-1;}
                    }else
                    if(abs(movementX)<abs(movementY)){
                       if(movementY>1){
                            dir=2;
                        }else if(movementY<1) {dir=-2;}
                    }
                    if(100*hp/maxHp <=20){
                        dir*=-1;
                    }
                    move(dir);
                }

            }else target = null;}
        }else target = null;
    }
    public void draw(Graphics g,int x,int y,ImageObserver observer){
        if(alive){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.black);
            //ramka zdrowia
            g2d.drawRect(x+2, y-5, 32, 2);
            //Pasek zdrowia
            if(hp>maxHp/2){
                g2d.setColor(Color.green);
            }else if(hp>maxHp/3){
                g2d.setColor(Color.yellow);
            }else g2d.setColor(Color.red);
            //rysowanie imienia
            g2d.drawString(name, x+2, y-8);
            
            g2d.drawRect(x+3, y-4, 31*hp/maxHp, 1);
            //rysowanie postaci
        }
        g.drawImage(img, x, y, observer);
    }
    
    public void attack(int damage){
        if(target.hp>=0){
            target.hurt=true;
            target.hp-=damage;
            target.damage=damage;
        }else target.die();
    }
    public void move(int dir){
        int tx=x,ty=y;
        switch(dir){
            case -1: tx=x-1; break;
            case -3: tx=x-1; ty=y-1; break;
            case -2: ty=y-1; break;
            case -4: tx=x+1; ty=y-1;break;
            case 1: tx=x+1; break;
            case 3: tx=x+1;ty=y+1; break;
            case 2: ty=y+1; break;
            case 4: tx=x-1;ty=y+1; break;
            default: break;
        }
       if(MainGameWindow.m.map[0][tx][ty].ID==101){
                    x=tx;
                    y=ty;
       }
    }
    @Override
    public String toString(){
        return name + " has "+ hp + " hp";
    }

    private void die() {
        alive=false;
        target=null;
        hurt=false;
        try{
                img= ImageIO.read(new File("res/body.png"));
            }catch(IOException e){
                e.printStackTrace();
            }
    }
    
}
