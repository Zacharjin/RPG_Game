/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author Piotr
 */
public class FightManager {
    private ArrayList <Being> list;
    private Being[] enemies;
    private Being center;
    private int fieldOfView;
    private Graphics g;
    
    public FightManager(Graphics g,int fieldOfView,Being center,Being[] enemies){
        //System.out.println("Tworze arrayList");
        list=new ArrayList<Being>();
        this.fieldOfView=fieldOfView;
        this.enemies=enemies;
        this.center=center;
        this.g=g;
    }
    
    public void update(){
        if(!list.isEmpty()){
            list.clear();
        }
        try{
            
            list.add(center);
            for(Being b:enemies){
                if(abs(b.x-center.x)<fieldOfView&&abs(b.y-center.y)<fieldOfView){
                    list.add(b);
                }
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private int roll(Being b1,Being b2){
        Random rand = new Random();
        return rand.nextInt(b1.atk)-b2.def;
    }
    
    public void fight(Being b1,Being b2){
        int damage =roll(b1,b2);
        if(b1.alive&&b2.alive){
            if(damage>0){
                g.setColor(Color.red);
                g.drawOval(b2.x*32, b2.y*32, 32, 32);
                b1.attack(damage);
                //b1.target.hurt=true;
                //System.out.println(b1.name + " attacks "+b2.name + " and deals "+damage+" damage");
            }
        }else {b1.target=null; b2.target=null;}
    }
    public void checkFight(){
        Iterator i= list.iterator();
        while(i.hasNext()){
            Being b1 = (Being) i.next();
            if(b1.target != null){
                if(abs(b1.target.x-b1.x)<=1&&abs(b1.target.y-b1.y)<=1){
                    if(b1.target.alive){
                        fight(b1,b1.target);
                    }
                }
            }
        }   
    }
}
