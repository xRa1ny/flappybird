package me.xra1ny.flappybird.entities;

import lombok.Getter;
import lombok.Setter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.gameapi.annotations.EntityInfo;
import me.xra1ny.gameapi.models.gameobject.Entity;
import me.xra1ny.gameapi.models.screen.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

@EntityInfo(width = 80, height = 480, maxXVelocity = 0.0, maxYVelocity = 0.0)
public class PipeLane extends Entity {
    @Getter(onMethod = @__(@NotNull))
    private final Pipe top = new Pipe(), bottom = new Pipe();
    @Getter
    @Setter
    private boolean passed;

    private final int gap;
    private final int gapY;

    public PipeLane(int gap, int gameHeight) {
        this.gap = gap;
        gapY = new Random().nextInt(gameHeight-gap);
        setAllowRender(true);
    }

    @Override
    public void onTick(@NotNull GameScreen gameScreen) {
        final FlappyBird flappyBird = (FlappyBird) gameScreen.getGame();

        if(flappyBird.getMainScreen().getBird().isAlive()) {
            setX(getX()-3);
            setY(0);
            top.tick(gameScreen);
            bottom.tick(gameScreen);
        }


        if(!passed) {
            if(flappyBird.getMainScreen().getBird().collidesWith(this, flappyBird.getCollisionTolerance())) {
                flappyBird.getMainScreen().getBird().score(flappyBird, this);
            }
        }
    }

    @Override
    public void onRender(@NotNull GameScreen gameScreen, @NotNull Graphics2D gtd) {
        top.onRender(gameScreen, gtd);
        bottom.onRender(gameScreen, gtd);
    }

    @Override
    public void setX(double x) {
        super.setX(x);
        top.setX(x-(getWidth()/3));
        bottom.setX(x-(getWidth()/3));
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        top.setY(gapY-top.getHeight());
        bottom.setY(gapY+gap);
    }
}
