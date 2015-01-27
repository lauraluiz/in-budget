package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    private final Texture backgroundImage;
    private final Texture topMenuImage;
    private final Texture bottomMenuImage;
    private final Texture girlImage;
    private final Texture priceTagImage;
    private final Sound dropSound;
    private final Music pianoMusic;
    private final Texture blankImage;
    private Texture resultBackgroundImage;

    public Resources() {
        blankImage = new Texture(Gdx.files.internal("none.png"));
        backgroundImage = new Texture(Gdx.files.internal("background-layer1.png"));
        topMenuImage = new Texture(Gdx.files.internal("background-layer3.png"));
        bottomMenuImage = new Texture(Gdx.files.internal("background-layer2.png"));
        resultBackgroundImage = new Texture(Gdx.files.internal("background-layer4.png"));
        girlImage = new Texture(Gdx.files.internal("girl.png"));
        priceTagImage = new Texture(Gdx.files.internal("tag.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        pianoMusic = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
        pianoMusic.setLooping(true);
    }

    public Texture girlImage() {
        return girlImage;
    }

    public Texture backgroundImage() {
        return backgroundImage;
    }

    public Texture resultBackgroundImage() {
        return resultBackgroundImage;
    }

    public Texture topMenuImage() {
        return topMenuImage;
    }

    public Texture bottomMenuImage() {
        return bottomMenuImage;
    }

    public Texture priceTagImage() {
        return priceTagImage;
    }

    public Sound dropSound() {
        return dropSound;
    }

    public Music gameMusic() {
        return pianoMusic;
    }

    public void dispose() {
        backgroundImage.dispose();
        resultBackgroundImage.dispose();
        topMenuImage.dispose();
        bottomMenuImage.dispose();
        dropSound.dispose();
        pianoMusic.dispose();
    }
}
