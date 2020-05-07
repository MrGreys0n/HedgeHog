package DataClasses;

import java.awt.*;

public class Variables {
    // constants
    public static final int CELL_SIZE = 40;
    public static final double WALLS_PERCENT = 0.3;
    public static final double COINS_PERCENT = 0.1;
    public static final double FIRE_PERCENT  = 0.07;
    public static final double MONSTER_PERCENT = 0.02;
    public static final Dimension[] DIMENSIONS = {new Dimension(800,600),
                                                  new Dimension(1024,768),
                                                  new Dimension(1200,600),
                                                  new Dimension(1280,1024),
                                                  new Dimension(1680,1050),
                                                  new Dimension(1920,1080)};
    //variables
    public static int CELLS_W;
    public static int CELLS_H;
    public static int FIELD_WIDTH;
    public static int FIELD_HEIGHT;
    public static int WALLS_COUNT;
    public static int COINS_COUNT;
    public static int FIRE_COUNT;
    public static int MONSTER_COUNT;

    public static void setDimension(Dimension d){
        CELLS_W = (int)d.getWidth()  / CELL_SIZE;
        CELLS_H = (int)d.getHeight()  / CELL_SIZE;
        FIELD_WIDTH  = CELLS_W * CELL_SIZE;
        FIELD_HEIGHT = CELLS_H * CELL_SIZE;
        WALLS_COUNT = (int)(CELLS_H * CELLS_W * WALLS_PERCENT);
        COINS_COUNT = (int)(CELLS_H * CELLS_W * COINS_PERCENT);
        FIRE_COUNT  = (int)(CELLS_H * CELLS_W * FIRE_PERCENT);
        MONSTER_COUNT = (int)(CELLS_H * CELLS_W * MONSTER_PERCENT);
    }
}
