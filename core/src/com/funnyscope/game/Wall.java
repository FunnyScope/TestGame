package com.funnyscope.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends GameObject {


    //Bro, it's a wall, I'm not going to put any comments here
    public Wall(World world, float width, float height, float x, float y, boolean isItX) {
        super(BodyDef.BodyType.StaticBody, world, width, height, x, y);

        sprite = new Sprite(new Texture(Gdx.files.internal("Wall.png")), (int) width, (int) height);
        if(isItX)
            sprite.setPosition(x - 240, y - 5);
        else
            sprite.setPosition(x - 5, y - 135);

        body.setUserData(sprite);

    }

    @Override
    public void update(float delta) {

    }
}
