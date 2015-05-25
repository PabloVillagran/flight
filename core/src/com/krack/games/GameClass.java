package com.krack.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameClass extends ApplicationAdapter implements GestureDetector.GestureListener {
	SpriteBatch batch;
    ShapeRenderer sr;
    OrthographicCamera oCamera;
    Viewport fViewport;
    Sprite tileSprite;
    AirShip pn;
    Enemy enemy;
    //Tile tile0, tile1, tile2, tile3;

    @Override
	public void create () {
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        oCamera = new OrthographicCamera (Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        fViewport = new FillViewport(700,700,oCamera);
        fViewport.apply();

        Texture e = new Texture("Tiles/basic.png");
        enemy = new Enemy(e,0,50*16*1.5f/2);

        Texture wwi = new Texture(Gdx.files.internal("AirShips/dummy.png"));
        pn = new AirShip(oCamera, wwi);

        Gdx.input.setInputProcessor(new GestureDetector(this));
	}

    @Override
    public void dispose(){
        batch.dispose();
        sr.dispose();
        tileSprite.getTexture().dispose();
    }

	@Override
	public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sr.setProjectionMatrix(oCamera.combined);

        sr.begin(ShapeType.Filled);
        sr.setColor(0, 0.3f, 0.5f, 0.7f);
        sr.rect(0, 0, 50 * 16 * 1.5f, 50 * 16 * 1.5f);
        sr.setColor(1, 0, 0, 0.7f);
        for(int i = 0; i <= 50*16*1.5f; i+=64) {
            for (int j = 0; j <= 50 * 16 * 1.5f; j += 64) {
                sr.circle(i, j, 5);
            }
        }
        sr.end();


        batch.begin();
        enemy.draw(batch);
        pn.draw(batch);
        batch.end();
        sr.begin(ShapeType.Filled);
        sr.setColor(1,1,1,1);
        sr.circle(pn.cannon1[0],pn.cannon1[1],3);
        sr.circle(pn.cannon2[0],pn.cannon2[1],3);
        sr.setColor(0,1,0,1);
        sr.circle(pn.posX,pn.posY,3);
        sr.circle(pn.sprite.getX(),pn.sprite.getY(),3);
        sr.end();
        sr.begin(ShapeType.Line);
        sr.setColor(1/255,1/215,0,1);
        sr.circle(pn.getPosX(),pn.posY,200);
        sr.setColor(1/225,0,1/255,1);
        sr.circle(enemy.getPosX(),enemy.getPosY(),200);
        sr.end();
	}

    @Override
    public void resize(int width, int height){
        fViewport.update(width, height);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        //CONTROLES BASICOS DE AVION.
       if (x < Gdx.graphics.getWidth() / 2)
            pn.turnLeft();
       if (x > Gdx.graphics.getWidth() / 2)
            pn.turnRight();
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        while(Gdx.input.isTouched()) {
            if (x < Gdx.graphics.getWidth() / 2)
                pn.turnLeft();
            if (x > Gdx.graphics.getWidth() / 2)
                pn.turnRight();
        }
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //oCamera.translate(-deltaX, deltaY);
        //oCamera.update();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
