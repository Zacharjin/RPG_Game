/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeditor;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Piotr
 */
public class FileManager {
    public Font font;
    public static class Block{
       public BufferedImage sprite;
       public short ID;
       public Block(){}
       public Block(short ID,BufferedImage sprite){
           this.ID = ID;
           this.sprite=sprite;
       }
       @Override
       public String toString(){
           return ID +"";
       }
    }
    public Block block[]= new Block[256];
    public Block map[][][]= new Block[2][50][50];
    public Block menu[][] = new Block[4][64];
    BufferedImage  mapSet[] = new BufferedImage[256];
    
    public short imageW=32,imageH=32;
    public FileManager(){ 
    }
    
    public FileManager(String file) {
        loadGraph(file);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,new File("res/font/goth.ttf")).deriveFont(40f);
        } catch (FontFormatException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void loadGraph(String file){
        try{
            File input = new File(file);
            BufferedImage sprite = ImageIO.read(input);
            short num =0;
            for(int i=0;i<sprite.getHeight();i+=imageH+2){
                for (int j = 0; j <sprite.getWidth(); j+=imageW+2) {

                    block[num] = new Block(num, sprite.getSubimage(j, i, imageW,imageH));
                    num++;    
                }
            }
            System.out.println(num);
            openMap("res/tristiam.txt");
        }
        catch(IOException ie)
            {
                System.out.println(ie.getMessage());
                System.out.println("COS NAWALILO");
            }  
    }
    
    void openMap(String file){
        try{
        File f=new File(file);
        Scanner s = new Scanner(f);
        int line=0;
        for (int layer = -1; s.hasNextLine(); layer++ ) {

            //System.out.println("Layer:"+layer+" "+s.nextLine());
            while(s.hasNextLine()){
               int num=0;
               String n= s.nextLine(); 
                System.out.println(n);
               if(n.contains("Layer")){
                   
                   System.out.println("Ide do kolejnego wiersza Layer wynosi "+layer);
                   break;
               }
               Scanner l=new Scanner(n);
                
                    while(l.hasNext()) {
                         int number = Integer.parseInt(l.next());
                         System.out.print(block[number]);
                         map[layer][num][line] = block[number];
                         num++;
                    }
                    System.out.println(" " + line);
                    line++;
                }
                line =0;
            }
            inintMenu(0);
        s.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void save(String file){
           try{
                File f=new File(file);
                BufferedWriter w=new BufferedWriter(new FileWriter(f));
                StringBuilder b=new StringBuilder();
                for (int layer = 0; layer < 2; layer++) {
                    w.write("Layer:"+layer);
                    w.newLine();
                    for (int i = 0; i < map[layer][i].length-1; i++) {
                        b=new StringBuilder();
                        for (int j = 0; j < map[layer][i].length; j++) {
                            b.append(map[layer][j][i]+" ");
                        }
                        w.write(b.toString());
                        w.newLine();
                   }
                }
                w.close();
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    void inintMenu(int layer){
        int num=0,maxNum=0;
        
        switch(layer){
            case 0: num=0;maxNum=125; break;
            case 1: num=126;maxNum=block.length; break;
        }
        System.out.println("LAYER IS "+layer);
        System.out.println("NUM IS "+num);
        for (int i = 0; i < menu[0].length; i++) {
            for (int j = 0; j < menu.length; j++,num++) {
                    if(num<maxNum){
                        menu[j][i] = block[num];
                    }else menu[j][i] = block[0];
            }
        }
    }
}