package me.xra1ny.flappybird.entities;

import me.xra1ny.gameapi.annotations.EntityInfo;
import me.xra1ny.gameapi.models.gameobject.Entity;
import me.xra1ny.gameapi.models.screen.GameScreen;
import me.xra1ny.gameapi.models.sprite.Sprite;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@EntityInfo(width = 0, height = 0, maxXVelocity = 0.0, maxYVelocity = 0.0)
public class Background extends Entity {
    public Background() {
        setAllowRender(true);
    }

    private final Sprite sprite = new Sprite("background.png");

    @Override
    public void onTick(@NotNull GameScreen gameScreen) {

    }

    @Override
    public void onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.drawImage(getSprite().getBufferedImage(), 0, 0, gameScreen.getGame().getWidth(), gameScreen.getGame().getHeight(), null);
    }

    @Override
    public @NotNull Sprite getSprite() {
        return sprite;
    }
}
