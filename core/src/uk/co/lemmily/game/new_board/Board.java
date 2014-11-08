package uk.co.lemmily.game.new_board;

import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.Mob;
import uk.co.lemmily.game.entity.ObjectType;
import uk.co.lemmily.game.entity.Player;

/**
 * Created by Emily on 30/10/2014.
 */
public class Board {
    private Array<Array<BoardSlot>> slots;

    public Board () {
        slots = new Array<Array<BoardSlot>>(9);

        for( int i = 0; i < 3; i++) {
            slots.add(new Array<BoardSlot>(3));
            for( int j =0; j < 3; j++) {
                slots.get(i).add(new BoardSlot(new Mob(new ObjectType()), 1));
            }


        }

        slots.get(1).get(1).put(new Player());
    }

    public Array<Array<BoardSlot>> getSlots() {
        return slots;
    }

    public BoardSlot getSlot(int x, int y) {
        return slots.get(x).get(y);
    }
}
