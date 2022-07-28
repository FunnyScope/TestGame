package com.funnyscope.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenu implements Screen {

    //Camera and game. No viewport because I'm lazy
    private TestGame game;
    private OrthographicCamera camera;

    public MainMenu(TestGame game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WORLD_WIDTH, game.WORLD_HEIGHT);



    }

    @Override
    public void show() {

    }

    //Renders the thing, really simple
    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();


        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            game.setScreen(game.gameScreen);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
