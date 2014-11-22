package com.tacktic.inbudget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Resources {
    private Map<String, Texture> itemTextures;
    private Sound dropSound;
    private Music rainMusic;
    private Texture blankImage;

    public Resources() {
        itemTextures = new HashMap<String, Texture>();
        for (FileHandle file : Gdx.files.internal("items").list()) {
            itemTextures.put(file.nameWithoutExtension(), new Texture(file));
        }
        blankImage = new Texture(Gdx.files.internal("none.png"));
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
    }

    public Texture itemImage(String itemId) {
        if (itemTextures.containsKey(itemId)) {
            return itemTextures.get(itemId);
        } else {
            return blankImage;
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
        for (Texture texture : itemTextures.values()) {
            texture.dispose();
        }
        itemTextures.clear();
        dropSound.dispose();
        rainMusic.dispose();
    }
}
