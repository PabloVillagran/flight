package com.krack.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class AirShip {
    public Sprite sprite;
    public OrthographicCamera camera;
    public float speed = 2f;
    protected float posX, posY, angle, cenX, cenY;
    protected float cannon1[], cannon2[];

    public AirShip(OrthographicCamera camera){
        sprite = new Sprite(new Texture(Gdx.files.internal("AirShips/dummy.png")));
        cenX = sprite.getWidth()/2;
        cenY = sprite.getHeight()/2;
        sprite.setCenter(cenX, cenY);
        posX = 0;
        posY = 0;
        angle = 90;
        sprite.setPosition(posX,posY);

        cannon1= new float[2];
        cannon2= new float[2];
        cannon1[0] = 0;
        cannon1[1] = 0;
        cannon2[0] = 0;
        cannon2[1] = 0;

        this.camera = camera;
        camera.position.set(sprite.getWidth()/2,sprite.getHeight()/2,0);
        camera.update();
    }

    public AirShip(OrthographicCamera camera, float posX, float posY, float angle){
        sprite = new Sprite(new Texture(Gdx.files.internal("AirShips/dummy.png")));
        cenX = sprite.getWidth()/2;
        cenY = sprite.getHeight()/2;
        sprite.setCenter(cenX, cenY);
        this.posX = posX;
        this.posY = posY;
        sprite.setPosition(posX,posY);

        this.camera = camera;
        camera.position.set(cenX,cenY,0);
        camera.update();
        this.angle = angle;

    }

    public AirShip(OrthographicCamera camera, Texture texture){
        sprite = new Sprite(texture);
        cenX = sprite.getWidth()/2;
        cenY = sprite.getHeight()/2;
        sprite.setCenter(cenX, cenY);
        sprite.setOriginCenter();

        this.camera = camera;
        camera.position.set(sprite.getWidth() / 2, sprite.getHeight() / 2, 0);
        camera.update();

        posX = cenX;
        posY = cenY;
        angle = 90;
        cannon1 = new float[2];
        cannon2 = new float[2];
        angleSet();
        /*cannon1[0]=cenX+31;
        cannon1[1]=cenY+26;
        cannon2 = new float[2];
        cannon2[0]=cenX-31;
        cannon2[1]=cenY+26;*/

        sprite.setPosition(0,0);
    }

    public AirShip(){}

    public void update(){
        move();
        camera.position.set(posX,posY,0);
        camera.update();
    }

    public void angleSet(){
        cannon1[0] = (float)(posX + 40.45d*Math.cos(Math.toRadians(angle-45)));
        cannon1[1] = (float)(posY + 40.45d*Math.sin(Math.toRadians(angle-45)));
        cannon2[0] = (float)(posX + 40.45d*Math.cos(Math.toRadians(angle+45)));
        cannon2[1] = (float)(posY + 40.45d*Math.sin(Math.toRadians(angle+45)));
    }

    public void move(){
        float relX= (float) (speed*(Math.cos(Math.toRadians(angle))));
        float relY= (float) (speed*(Math.sin(Math.toRadians(angle))));
        sprite.translate(relX,relY);
        cannon1[0] += relX;
        cannon1[1] += relY;
        cannon2[0] += relX;
        cannon2[1] += relY;
        posY = sprite.getY()+cenY;
        posX = sprite.getX()+cenX;
        if(posY>(50*16*1.5f)){
            posY=0;
            sprite.setPosition(posX - cenX, posY-cenY);
            angleSet();
            Gdx.app.log("RESETY", "RESETY RESETY RESETY RESETY RESETY RESETY");
        }
        if(posX>(50*16*1.5f)){
            posX=0;
            sprite.setPosition(posX - cenX, posY - cenY);
            angleSet();
            Gdx.app.log("RESETX","RESETX RESETX RESETX RESETX RESETX RESETX");
        }
        if(posY<0){
            posY=(50*16*1.5f)-cenY;
            sprite.setPosition(posX-cenX,posY);
            angleSet();
            Gdx.app.log("RESETY","RESETY RESETY RESETY RESETY RESETY RESETY");
        }
        if(posX<0){
            posX=(50*16*1.5f)-cenX;
            sprite.setPosition(posX,posY-cenY);
            angleSet();
            Gdx.app.log("RESETY","RESETY RESETY RESETY RESETY RESETY RESETY");
        }
    }

    public void draw(Batch batch){
        update();
        batch.setProjectionMatrix(camera.combined);
        sprite.draw(batch);
    }

    public void turnRight(){
        sprite.rotate(-5);
        cannon1[0] = (float)(posX + 40.45d*Math.cos(Math.toRadians(angle-45-5)));
        cannon1[1] = (float)(posY + 40.45d*Math.sin(Math.toRadians(angle-45-5)));
        cannon2[0] = (float)(posX + 40.45d*Math.cos(Math.toRadians(angle+45-5)));
        cannon2[1] = (float)(posY + 40.45d*Math.sin(Math.toRadians(angle+45-5)));
        angle-=5;
    }

    public void turnLeft(){
        sprite.rotate(5);
        cannon1[0] = (float)(posX + 40.45d*Math.cos(Math.toRadians(angle-45+5)));
        cannon1[1] = (float)(posY + 40.45d*Math.sin(Math.toRadians(angle-45+5)));
        cannon2[0] = (float)(posX + 40.45d*Math.cos(Math.toRadians(angle+45+5)));
        cannon2[1] = (float)(posY + 40.45d*Math.sin(Math.toRadians(angle+45+5)));
        angle+=5;
    }

    public void dispose(){
        sprite.getTexture().dispose();
    }

    public void setPosX(float posX){
        this.posX = posX;
    }

    public void setPosY(float posY) { this.posY = posY; }

    public void setAngle(float angle){
        this.angle = angle;
        sprite.setRotation(angle);
    }

    public float getPosX(){
        return posX;
    }

    public float getPosY(){
        return posY;
    }

}
