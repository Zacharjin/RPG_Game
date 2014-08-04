/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Image;

public class Animation {
    String text;
    Image img;
    int frames=0;
    public Animation(String text){
        this.text=text;
    }
    public Animation(Image img){
        this.img = img;
    }
    public int check(){
        if(text==null){
            return 1;
        }else return 0;
    }
}
