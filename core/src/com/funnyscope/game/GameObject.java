package com.funnyscope.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class GameObject {

    protected final int MAX_VELOCITY = 25, MAX_ANGULAR = 5;

    protected Sprite sprite;
    protected Body body;

    public GameObject(BodyDef.BodyType bodyType, World world, float width, float height) {

        //Making the bodyDef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(240 - width / 2, 145 - height / 2);


        //You stand at the beginning of creation... of a box2d object
        body = world.createBody(bodyDef);

        //Making the shape
        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width / 2, height / 2);

        //Making the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.55f;

        //Doing the fixture thingy
        body.createFixture(fixtureDef);



    }

    //Same as the previous one but with coordinates. Really doesn't matter but it looks fun
    public GameObject(BodyDef.BodyType bodyType, World world, float width, float height, float x, float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);



        body = world.createBody(bodyDef);


        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width / 2, height / 2);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.55f;


        body.createFixture(fixtureDef);



    }

    //Quite simple, right?
    public Body getBody() {
        return body;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract void update(float delta);

    public void dispose() {
        sprite.getTexture().dispose();
    }
}
