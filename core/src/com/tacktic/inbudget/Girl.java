package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Girl {
    private final Animation girlAnimation;
    private float stateTime;

    public Girl(Resources resources) {
        Texture girlSheet = resources.girlImage();
        TextureRegion[][] tmp = TextureRegion.split(girlSheet, girlSheet.getWidth()/3, girlSheet.getHeight());
        TextureRegion[] girlFrames = new TextureRegion[54];
        int index = 0;
        for (int i = 0; i < 30; i++) {
            girlFrames[index++] = tmp[0][0]; //10
        }
        girlFrames[index++] = tmp[0][1]; //1
        for (int i = 0; i < 10; i++) {
            girlFrames[index++] = tmp[0][0]; //10
        }
        girlFrames[index++] = tmp[0][1]; //1
        girlFrames[index++] = tmp[0][2]; //1
        for (int i = 0; i < 10; i++) {
            girlFrames[index++] = tmp[0][0]; //10
        }
        girlFrames[index++] = tmp[0][1]; //1
        girlAnimation = new Animation(0.1f, girlFrames);
        stateTime = 0f;
    }

    public TextureRegion nextFrame() {
        stateTime += Gdx.graphics.getDeltaTime();
        return girlAnimation.getKeyFrame(stateTime, true);
    }
}

