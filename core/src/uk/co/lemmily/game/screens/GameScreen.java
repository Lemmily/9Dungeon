package uk.co.lemmily.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import uk.co.lemmily.game.entity.Player;
import uk.co.lemmily.game.entity.PlayerActor;
import uk.co.lemmily.game.inventory.Crafting;
import uk.co.lemmily.game.inventory.CraftingActor;
import uk.co.lemmily.game.inventory.Inventory;
import uk.co.lemmily.game.inventory.InventoryActor;
import uk.co.lemmily.game.new_board.Board;
import uk.co.lemmily.game.new_board.BoardActor;
import uk.co.lemmily.game.ui.DragAndDropLocked;

/**
 * Created by Emily on 23/10/2014.
 */
public class GameScreen implements Screen {
    public static Stage stage;
    private InventoryActor inventoryActor;
    private CraftingActor craftingActor;
    private BoardActor boardActor;
    private PlayerActor playerActor;

    @Override
    public void show() {
        //create and make it receive input
        stage = new Stage();
        stage.setViewport(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        DragAndDrop dragAndDrop = new DragAndDrop();
//        DragAndDropLocked dragAndDropLocked = new DragAndDropLocked();

        Inventory inventory = new Inventory();
        Crafting crafting = new Crafting(9, 3);
        Board board = new Board();

        Player player = new Player();
        playerActor = new PlayerActor(player);

        inventoryActor = new InventoryActor(inventory, dragAndDrop, skin);
        craftingActor = new CraftingActor(crafting, inventory, dragAndDrop, skin);
        boardActor = new BoardActor(board, player, dragAndDrop, skin);

        stage.addActor(inventoryActor);
        stage.addActor(craftingActor);
        stage.addActor(boardActor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        //show the inventory when any key is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            inventoryActor.setVisible(true);
            craftingActor.setVisible(true);
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        boardActor.resize(width,height);
    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
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
    }
}
