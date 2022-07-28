package com.funnyscope.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestGame extends Game {

	//Made this just to test box2d, never used it before. Might want to figure out box2d bullets after this,
	//so I'll update the repo if that's the case

	//The spritebatch bundles all the things you want drawn and sends them to OpenGL in one go, which OpenGL likes more
	//than being told to draw something 100 times
	public SpriteBatch batch;
	public GameScreen gameScreen;
	public MainMenu mainMenu;

	//Wow, it's a bunch of constants. Exciting, aren't they?
	public final int WORLD_WIDTH = 480, WORLD_HEIGHT = 270;

	//Very standard stuff
	@Override
	public void create () {

		batch = new SpriteBatch();

		gameScreen = new GameScreen(this);
		mainMenu = new MainMenu(this);

		this.setScreen(mainMenu);
	}

	//You've got to do super.render() if you're using screens and the Game class
	@Override
	public void render () {
		super.render();
	}

	//Remember to dispose of disposables!
	@Override
	public void dispose () {

		batch.dispose();
		gameScreen.dispose();

	}
}
