package com.krack.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
    private int type;
    private boolean solid;
    private float x, y;
    private final float SIZE = 30;

    public Sprite sprite;
    public Tile north, east, west, south;

    public Tile() {
        sprite = null;
        type = 0;
        solid = false;
        x = 0;
        y = 0;
        north = null;
        east = null;
        west = null;
        south = null;
    }

    public Tile(Sprite sprite, int type, boolean solid, float x, float y) {
        this.sprite = sprite;
        this.type = type;
        this.solid = solid;
        this.x = x;
        this.y = y;
        this.sprite.setPosition(x, y);

        north = null;
        east = null;
        west = null;
        south = null;
    }

    public Tile(Sprite sprite, int type, boolean solid, Tile north, Tile east, Tile south, Tile west) {
        this.sprite = sprite;
        this.type = type;
        this.solid = solid;
        this.north = north;
        this.east = east;
        this.west = west;
        this.south = south;

        if(south != null)
            this.y = south.y + SIZE;
        if(west != null)
            this.x = west.x + SIZE;

        this.sprite.setPosition(this.x,this.y);
        Gdx.app.log("SET POSITION",this.sprite.getX()+" X "+this.sprite.getY()+" Y.");
    }

    public void setSolid(boolean solid){
        this.solid = solid;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public boolean isSolid(){
        return solid;
    }

    public int getType(){
        return type;
    }

    public void disposeTexture(){
        sprite.getTexture().dispose();
    }
}
