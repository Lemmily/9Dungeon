package uk.co.lemmily.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.lemmily.game.screens.Splash;

public class RecLike extends Game {
    public static final String TITLE = "REC-LIKE", VERSION = "0.0.0.0.1";

    @Override
    public void create() {
        setScreen(new Splash());
    }

    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
