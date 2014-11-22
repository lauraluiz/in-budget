package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    private Texture tvImage;
    private Sound dropSound;
    private Music rainMusic;

    public Resources() {
        tvImage = new Texture(Gdx.files.internal("bucket.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
    }

    public Texture itemImage(String itemId) {
        if (itemId.equals("tv")) {
            return tvImage;
        } else {
            throw new RuntimeException("Image not found: " + itemId);
        }
    }

    public Sound dropSound() {
        return dropSound;
    }

    public void playBackgroundMusic() {
        rainMusic.setLooping(true);
        rainMusic.play();
    }

    public void dispose() {
        tvImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
