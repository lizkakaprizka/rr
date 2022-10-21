package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class obstacles {
    class WallPair{
        Vector2 position;
        float speed;
        int offset;
        Rectangle emptyspace;
        public WallPair(Vector2 pos){
            position = pos;
            speed =2;
            offset = new Random().nextInt(250);
            emptyspace = new Rectangle(position.x,position.y -offset+500, 120, betweenDistance);
        }
        public void update(){
            position.x-=speed;
            if(position.x<-120){
                position.x=800;
                offset = new Random().nextInt(250);
            }
            emptyspace.x= position.x;
        }

    }
    static WallPair[] obs;
    Texture txt;
    int betweenDistance;

    public obstacles(){
        txt = new Texture("wall.jpg");
        obs = new WallPair[4];
        betweenDistance = 250;
        int startPosX = 400;
        for (int i=0; i < obs.length; i++){
            obs[i] = new WallPair(new Vector2(startPosX, 0));
            startPosX+=220;
        }
    }
    public  void render(SpriteBatch batch){
        for (WallPair ob : obs) {
            batch.draw(txt, ob.position.x, ob.position.y
                    - ob.offset);
            batch.draw(txt, ob.position.x, ob.position.y
                    + betweenDistance + txt.getHeight() - ob.offset);
        }
    }
    public static void update(){
        for (int i = 0; i < obs.length; i++) {
            obs[i].update();
        }
    }
    public  void recreate(){
        int startPosX=400;
        for (int i = 0; i < obs.length; i++) {
            obs[i]=new WallPair(new Vector2(startPosX,0));
            startPosX+=220;
        }
    }
}
