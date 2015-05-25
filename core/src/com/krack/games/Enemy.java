package com.krack.games;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends AirShip{

    public Enemy(Texture texture, float posX, float posY){
        sprite = new Sprite(texture);
        cenX = sprite.getWidth()/2;
        cenY = sprite.getHeight()/2;
        sprite.setCenter(cenX,cenY);
        this.posX = posX;
        this.posY = posY;
        angle = 0;
        speed = 2f;
        sprite.setPosition(posX,posY);
    }

    @Override
    public void draw(Batch batch){
        update();
        sprite.draw(batch);
    }

    @Override
    public void update(){
        float relX= (float) (speed*(Math.cos(Math.toRadians(angle))));
        float relY= (float) (speed*(Math.sin(Math.toRadians(angle))));
        sprite.translate(relX,relY);
        posY = sprite.getY()+cenY;
        posX = sprite.getX()+cenX;
        if(posY>(50*16*1.5f)){
            posY=cenY;
            sprite.setPosition(posX-cenX,posY-cenY);
        }
        if(posX>(50*16*1.5f)){
            posX=cenX;
            sprite.setPosition(posX-cenX,posY-cenY);
        }
        if(posY<0){
            posY=(50*16*1.5f)-cenY;
            sprite.setPosition(posX-cenX,posY);
        }
        if(posX<0){
            posX=(50*16*1.5f)-cenX;
            sprite.setPosition(posX,posY-cenY);
        }
    }

}
