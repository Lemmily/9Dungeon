package uk.co.lemmily.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Emily on 21/10/2014.
 */
public class PlayGame extends InputAdapter implements Screen {
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;

    InputMultiplexer multiplexer = new InputMultiplexer();

    Texture textureTileset;
    OrthographicCamera cam;
    SpriteBatch batch = new SpriteBatch();;

    Sprite[][] sprites = new Sprite[10][10];
    Matrix4 matrix = new Matrix4();
    private TextureRegion[] tileSet;
    private float tileWidth = 1.0f;
//    private float tileHeight = .5f;
    private int[][] map;



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,.5f,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(cam.combined);
        batch.setTransformMatrix(matrix);
        batch.begin();
        for (int x = 0; x < sprites.length; x++) {
            for (int z = 0; z < sprites[x].length; z++) {
                sprites[x][z].draw(batch);
            }
        }
        batch.end();

//        checkTileTouched();

        //update
        stage.act(delta);
        //draw
        stage.draw();
//        Table.drawDebug(stage);

    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        table.invalidateHierarchy();

        //the cam will show 10 tiles
        float camWidth = tileWidth * 10.0f;

        //for the height, we just maintain the aspect ratio
        float camHeight = camWidth * ((float)height / (float)width);

        cam = new OrthographicCamera(camWidth, camHeight);
        cam.position.set(5, 5, 10);
        cam.direction.set(-1, -1, -1);
        cam.near = 1;
        cam.far = 100;
        matrix.setToRotation(new Vector3(1, 0, 0), 90);
        cam.update();
//        cam.direction.set(-1,-1,-1);
//        cam.near = 1;
//        cam.far = 100;
    }

    @Override
    public void show() {

        GL20 gl = Gdx.graphics.getGL20();
        gl.glEnable(GL20.GL_BLEND);
        gl.glEnable(GL20.GL_TEXTURE_2D);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


        textureTileset = new Texture("tileset.png");
        textureTileset.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        tileSet = new TextureRegion[4];
        for(int x=0;x<4;x++){
            tileSet[x] = new TextureRegion(textureTileset, x*64, 0, 64, 32);
        }


        //creat a 10x10 isometric map
        map = new int[][]{
                {0, 0, 0 ,0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1 ,1, 1, 1, 1, 1, 1, 0},
                {0, 1, 2 ,2, 0, 0, 0, 0, 1, 0},
                {0, 1, 2 ,2, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1 ,1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0 ,0, 0, 0, 0, 0, 0, 0}
        };



        for(int z = 0; z < 10; z++) {
            for(int x = 0; x < 10; x++) {
                sprites[x][z] = new Sprite(tileSet[map[x][z]]);
                sprites[x][z].setPosition(x,z);
                sprites[x][z].setSize(1, 1);
            }
        }
//        id = new Matrix4();
//        id.idt();
//
//        isoTransform = new Matrix4();
//        isoTransform.idt();
//        isoTransform.translate(0.0f, 0.25f, 0.0f);
//        isoTransform.scale((float)(Math.sqrt(2.0) / 2.0), (float)(Math.sqrt(2.0) / 4.0), 1.0f);
////        isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);
//
//        invIsoTransform = new Matrix4(isoTransform);
//        invIsoTransform.inv();


        matrix.setToRotation(new Vector3(1,0,0), 90);

        stage = new Stage();


        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);


        Gdx.input.setInputProcessor(multiplexer);

        atlas = new TextureAtlas(Gdx.files.internal("ui/skin/button.pack"));
        skin = new Skin(Gdx.files.internal("ui/skin/menuSkin.json"), atlas);

        table = new Table(skin);
        table.setFillParent(true);


        TextButton buttonExit = new TextButton("Exit", skin);
        buttonExit.pad(15);
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        table.bottom().right().add(buttonExit);

        //add the table to the stage
        stage.addActor(table);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }


    final Plane xzPlane = new Plane(new Vector3(0, 1, 0), 0);
    final Vector3 intersection = new Vector3();
    Sprite lastSelectedTile = null;

    private void checkTileTouched() {
        if(Gdx.input.justTouched()) {
            Ray pickRay = cam.getPickRay(Gdx.input.getX(), Gdx.input.getY());
            Intersector.intersectRayPlane(pickRay, xzPlane, intersection);
            int x = (int)intersection.x;
            int z = (int)intersection.z;
            if(x >= 0 && x < 10 && z >= 0 && z < 10) {
                if(lastSelectedTile != null) lastSelectedTile.setColor(1, 1, 1, 1);
                Sprite sprite = sprites[x][z];
                sprite.setColor(1, 0, 0, 1);
                lastSelectedTile = sprite;
            }
        }
    }


    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();
    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        Ray pickRay = cam.getPickRay(x, y);
        Intersector.intersectRayPlane(pickRay, xzPlane, curr);

        if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
            pickRay = cam.getPickRay(last.x, last.y);
            Intersector.intersectRayPlane(pickRay, xzPlane, delta);
            delta.sub(curr);
            cam.position.add(delta.x, delta.y, delta.z);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        last.set(-1,-1,-1);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
