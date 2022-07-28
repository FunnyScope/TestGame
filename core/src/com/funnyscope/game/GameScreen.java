package com.funnyscope.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameScreen implements Screen {

    //Same as MainMenu
    private TestGame game;
    private OrthographicCamera camera;

    //Box2d world thingies
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private float accumulator = 0;

    //The object array
    Array<GameObject> gameObjects = new Array<GameObject>();

    public GameScreen(TestGame game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WORLD_WIDTH, game.WORLD_HEIGHT);


    }

    //Makes a step in physics depending on the time between frames
    private void physicsStep(float deltaTime) {

        float stepTime = MathUtils.clamp(deltaTime, 1/240f, 1/60f);
        accumulator += stepTime;
        while(accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

    }

    //Creates all necessities without taking up memory beforehand
    @Override
    public void show() {
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        gameObjects.add(new Player(world, 32, 16));

        //Adding walls, could probably be done with some smart method but lazy code's fine here
        gameObjects.add(new Wall(world, game.WORLD_WIDTH, 10, 240, 5, true));
        gameObjects.add(new Wall(world, game.WORLD_WIDTH, 10, 240, game.WORLD_HEIGHT - 5, true));
        gameObjects.add(new Wall(world, 10, game.WORLD_HEIGHT, 5, 135, false));
        gameObjects.add(new Wall(world, 10, game.WORLD_HEIGHT, game.WORLD_WIDTH - 5, 135,false));
    }

    //Updates all game objects
    private void update(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(delta);
        }
    }

    //Draws all game objects
    private void draw(Batch batch) {
        for (GameObject gameObject : gameObjects) {
            gameObject.getSprite().draw(batch);
        }
    }

    //Main method
    @Override
    public void render(float delta) {

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        //For development reasons
        if(Gdx.input.isKeyPressed(Input.Keys.Q))
            debugRenderer.render(world, camera.combined);

        game.batch.begin();

        draw(game.batch);

        game.batch.end();

        update(delta);
        physicsStep(delta);

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

    //When the screen is hidden, all things in memory get disposed of
    @Override
    public void hide() {
        world.dispose();
        debugRenderer.dispose();

        for(int i = 0; i < gameObjects.size; i++) {
            gameObjects.get(i).dispose();
            gameObjects.removeIndex(i);
        }
    }

    @Override
    public void dispose() {

    }
}
