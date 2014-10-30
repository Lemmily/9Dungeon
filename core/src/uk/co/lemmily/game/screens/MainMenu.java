package uk.co.lemmily.game.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import uk.co.lemmily.game.RecLike;
import uk.co.lemmily.game.tween.ActorAccessor;

/**
 * Created by Emily on 20/10/2014.
 */
public class MainMenu implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit;
    private BitmapFont white;
    private Label heading;

    private TweenManager tweenManager;


    @Override
    public void render(float delta) {
        //clear screen
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update animations before anything else.
        tweenManager.update(delta);

        //update
        stage.act(delta);
        //draw
        stage.draw();
//        Table.drawDebug(stage);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
//        table.setSize(width, height);
        table.invalidateHierarchy();
    }

    @Override
    public void show() {
        stage = new Stage();

        atlas = new TextureAtlas(Gdx.files.internal("ui/skin/button.pack"));
        skin = new Skin(Gdx.files.internal("ui/skin/menuSkin.json"), atlas);

        table = new Table(skin);
        table.setFillParent(true);


        //make it so it will listen for actions.
        Gdx.input.setInputProcessor(stage);

        heading = new Label(RecLike.TITLE, skin);
//        heading.setColor( .5f, .5f, .5f, 0f);
        heading.setFontScale(2);

        //create a button
        buttonPlay = new TextButton("New Game", skin);
        buttonPlay.pad(15);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayGame());
            }
        });

        buttonExit = new TextButton("Exit", skin);
        buttonExit.pad(15);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        table.add("HIYYYAA");
//        table.getCell(heading).spaceBottom(100);
        table.row();
        table.add(buttonPlay);
        table.row();
        table.add(buttonExit);
        table.debug();
        stage.addActor(table);


        //creating the animations
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccessor());

        Timeline.createSequence().beginSequence()
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0,0,1))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0,1,0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1,0,0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1,1,0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1,1,1))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0,1,1))
                .end().repeat(Tween.INFINITY, 0).start(tweenManager);

        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
                .push(Tween.from(heading, ActorAccessor.ALPHA, .5f).target(0))
                .push(Tween.to(buttonPlay, ActorAccessor.ALPHA, .5f).target(1))
                .push(Tween.to(buttonExit, ActorAccessor.ALPHA, .5f).target(1))
                .end().start(tweenManager);

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
//        white.dispose();
        skin.dispose();
    }
}
