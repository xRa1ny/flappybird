package me.xra1ny.flappybird.entities;

import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.annotations.EntityInfo;
import me.xra1ny.gameapi.models.gameobject.Entity;
import me.xra1ny.gameapi.models.screen.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@EntityInfo(width = 90, height = 480, maxXVelocity = 0.0, maxYVelocity = 0.0)
public class Pipe extends Entity {
    public Pipe() {
        setAllowRender(true);
    }

    @Override
    public void onTick(@NotNull GameScreen gameScreen) {
        final FlappyBird flappyBird = (FlappyBird) gameScreen.getGame();

        if(flappyBird.getMainScreen().getBird().collidesWith(this, flappyBird.getCollisionTolerance())) {
            flappyBird.getMainScreen().getBird().kill(flappyBird);
        }
    }

    @Override
    public void onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        gtd.setColor(Color.GREEN);
        gtd.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}
