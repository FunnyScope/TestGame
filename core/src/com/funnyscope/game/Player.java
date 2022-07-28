package com.funnyscope.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends GameObject {

    //Just a constructor
    public Player(World world, float width, float height) {
        super(BodyDef.BodyType.DynamicBody, world, width, height);

        //Doing the sprite thing - because that hasn't been done in GameObject
        sprite = new Sprite(new Texture(Gdx.files.internal("testspaceship.png")), (int) width, (int) height);
        sprite.setPosition(body.getPosition().x - width, body.getPosition().y - height);


        body.setUserData(sprite);



    }

    //I get tired from looking at this one
    @Override
    public void update(float delta) {

        //First we set the sprite to be as it should be
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.setRotation(body.getAngle() / (float) Math.PI * 180);

        //Important calculations for the maths after this
        float sin = MathUtils.sin(body.getAngle());
        float cos = MathUtils.cos(body.getAngle());

        //Doing the linear impulse for going forwards
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            int totalForce = 1000;

            float velX = cos * totalForce;
            float velY = sin * totalForce;

            body.applyLinearImpulse(new Vector2(velX, velY), new Vector2(body.getPosition().x, body.getPosition().y), true);


        }

        //Doing the linear impulse for going backwards
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {

            int totalForce = -1000;

            float velX = cos * totalForce;
            float velY = sin * totalForce;

            body.applyLinearImpulse(new Vector2(velX, velY), new Vector2(body.getPosition().x, body.getPosition().y), true);


        }

        //Turn one way
        if(Gdx.input.isKeyPressed(Input.Keys.A) && body.getAngularVelocity() < MAX_ANGULAR) {
            body.applyAngularImpulse(500, true);

        }

        //Turn the other way
        if(Gdx.input.isKeyPressed(Input.Keys.D) && body.getAngularVelocity() > -MAX_ANGULAR) {
            body.applyAngularImpulse(-500, true);

        }

        //If you don't have your key pressed, you stop turning. Does it make sense with regards to physics? No!
        if(!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D))
            body.setAngularVelocity(0);

    }



}
