package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Bird bird;
	obstacles obs;
	boolean gameOver;
	Texture restartTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Background();
		bird =  new Bird();
		restartTexture = new Texture("gameover1.png");
	}

	@Override
	public void render () {
		update();

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bg.render(batch);
		bird.render(batch);
		obs.render(batch);
		if(!gameOver){
			bird.render(batch);
		}
		else {
			batch.draw(restartTexture, 200, 200);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
	public void update(){
		bg.update();
		bird.update();
		obstacles.update();
		for (int i=0; i<obstacles.obs.length; i++){
			if (bird.position.x > obstacles.obs[i].position.x&&
					bird.position.x<obstacles.obs[i].position.x+50) {
				if (!obstacles.obs[i].emptyspace. contains(bird.position)){
					gameOver=true;
				}
			}
		}
		if (bird.position.y<0||bird.position.y>1080){
			gameOver=true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)&&gameOver){
			recreate();
		}
	}

	private void recreate() {
		bird.recreate();
		obs.recreate();
		gameOver=false;
	}
}
