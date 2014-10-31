package uk.co.lemmily.game.board;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Emily on 29/10/2014.
 */
public class Board {
    private Array<BoardSlot> slots;

    public Board() {
        slots = new Array<BoardSlot>(9);

        for (int i = 0; i < 9; i++) {
            slots.add(new BoardSlot(new Entity("monster_goblin_default", "monster")));
        }
    }

    public Array<BoardSlot> getSlots() {
        return slots;
    }

}
