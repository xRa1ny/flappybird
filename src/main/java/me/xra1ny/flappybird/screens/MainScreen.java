package me.xra1ny.flappybird.screens;

import lombok.Getter;
import me.xra1ny.flappybird.FlappyBird;
import me.xra1ny.flappybird.entities.Background;
import me.xra1ny.flappybird.entities.Bird;
import me.xra1ny.flappybird.tasks.PipeTask;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.models.screen.GameScreen;
import me.xra1ny.gameapi.utils.Fonts;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainScreen extends GameScreen {
    private final Background background = new Background();
    @Getter(onMethod = @__(@NotNull))

    private Bird bird;

    private PipeTask pipeTask;

    private boolean started = false;

    public MainScreen(@NotNull Game game) {
        super(game);
    }

    @Override
    public void onRender(@NotNull Graphics2D gtd) {
        final FlappyBird flappyBird = (FlappyBird) getGame();
        gtd.setFont(Fonts.PROXIMA_NOVA);
        gtd.setColor(Color.BLACK);
        if(!this.started) {
            final String highscore = "HIGHSCORE: " + flappyBird.getHighscore();
            gtd.drawString(highscore, getGame().getWidth()/2, getGame().getHeight()/2);
            gtd.drawString("Press any Button to start...", getGame().getWidth()/2, getGame().getHeight()/2+50);
        }else {
            if(this.bird.isAlive()) {
                gtd.drawString("Score: " + bird.getCurrentScore(), 20, 20);
            }else {
                final String highscore = "NEW HIGHSCORE!";
                final String score = "Your Score: " + bird.getCurrentScore();
                final String again = "Press any Button to play again!";
                final int highscoreWidth = gtd.getFontMetrics().stringWidth(highscore);
                final int scoreWidth = gtd.getFontMetrics().stringWidth(score);
                final int againWidth = gtd.getFontMetrics().stringWidth(again);
                if(getBird().getCurrentScore() > flappyBird.getHighscore()) {
                    gtd.drawString(highscore, getGame().getWidth()/2-(highscoreWidth/2), getGame().getHeight()/2);
                }
                gtd.drawString(score, getGame().getWidth()/2-(scoreWidth/2), getGame().getHeight()/2+75);
                gtd.drawString(again, getGame().getWidth()/2-(againWidth/2), getGame().getHeight()/2+95);
            }
        }
    }

    @Override
    public void onKeyPress(@NotNull KeyEvent keyEvent) {

    }

    @Override
    public void onKeyRelease(@NotNull KeyEvent keyEvent) {

    }

    @Override
    public void onEnable() {
        final FlappyBird flappyBird = (FlappyBird) getGame();
        this.started = false;
        if(this.pipeTask != null) {
            this.pipeTask.kill();
        }

        if(this.bird != null) {
            if(this.bird.getCurrentScore() > flappyBird.getHighscore()) {
                flappyBird.setHighscore(bird.getCurrentScore());
            }
        }

        this.bird = new Bird();

        bird.setAlive(true);
        bird.setAllowTick(false);

        bird.setCurrentScore(0);

        getGameObjects().clear();

        getGameObjects().add(this.background);
        getGameObjects().add(this.bird);

        this.pipeTask = new PipeTask((FlappyBird) getGame());
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onMousePress(@NotNull MouseEvent e) {
        final int keycode = e.getButton();

        if(keycode == MouseEvent.BUTTON1) {
            if(this.started) {
                if(this.bird.isAlive()) {
                    this.bird.jump(getGame());
                }else {
                    onEnable();
                }
            }else {
                this.started = true;
                this.bird.setAllowTick(true);
                this.bird.jump(getGame());
                this.pipeTask.enable();
            }
        }
    }

    @Override
    public void onMouseRelease(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseEnterComponent(@NotNull MouseEvent mouseEvent) {

    }

    @Override
    public void onMouseExitComponent(@NotNull MouseEvent mouseEvent) {

    }
}
