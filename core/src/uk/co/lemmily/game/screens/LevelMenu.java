package uk.co.lemmily.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Emily on 23/10/2014.
 */
public class LevelMenu implements Screen{

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        stage = new Stage();
        atlas = new TextureAtlas("ui/skin/button.pack");
        skin = new Skin(Gdx.files.internal("ui/skin/menuSkin.json"), atlas);
        table = new Table(skin);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
