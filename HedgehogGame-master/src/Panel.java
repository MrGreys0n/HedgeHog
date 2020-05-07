import DataClasses.Images;
import DataClasses.Variables;
import Objects.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {

    private Player player;
    private Exit exit;
    private Tnt tnt;
    private ArrayList<Wall> walls;
    private ArrayList<Coin> coins;
    private ArrayList<Fire> fires;
    private ArrayList<Monster> monsters;
    private boolean isEnd;
    private boolean hasTNT;
    private int coin_count;

    public Panel(){
        setPreferredSize(new Dimension(Variables.FIELD_WIDTH, Variables.FIELD_HEIGHT));
        startGame();
    }

    private void startGame(){
        isEnd = false;
        hasTNT = false;
        player = new Player();
        exit = new Exit();
        tnt = new Tnt();
        coin_count = 0;
        addWalls();
        addCoins();
        addFires();
        addMonsters();
        addTNT();
    }

    public void stepArrow(int dx, int dy){
        if (isEnd) return;
        int newX = player.getX() + dx * Variables.CELL_SIZE;
        int newY = player.getY() + dy * Variables.CELL_SIZE;
        if (collisionWith(walls, newX, newY)) return;
        player.setCoordinates(newX, newY);
        if (player.collisionWith(exit))
            endWinLevel();
        if (collisionWith(fires, player) || collisionWith(monsters, player))
            endLoseGame();
        if (collisionWith(coins, player)){
            coin_count++;
            for(int i = 0; i < coins.size(); i++){
                if (player.collisionWith(coins.get(i))){
                    coins.remove(i);
                    break;
                }
            }
        }
        if (player.collisionWith(tnt)){
            hasTNT = true;
            System.out.println("You picked up TNT!");
        }

        repaint();
    }

    public void boom(int mx, int my){
        //if (!hasTNT) return;
        System.out.println(mx+ " " + my);
        if (collisionBoom(walls, mx, my)){
            System.out.println("BOOM!");
            for(int i = 0; i < walls.size(); i++){
                if (collisionBoom(walls.get(i), mx, my)){
                    System.out.println(i);
                    walls.remove(i);
                    System.out.println("Deleted");
                    hasTNT = false;
                    break;
                }
            }
        }
    }

    private void endWinLevel(){
        isEnd = true;
        System.out.println("You Win! Coins: " + coin_count);
    }

    private void endLoseGame(){
        isEnd = true;
        System.out.println("You Lose! Coins: " + coin_count);
    }

    //collisions
    //-------------------------------------------------------------------------------------------
    private boolean collisionWith(ArrayList list, GameObject object){
        return collisionWith(list, object.getX(), object.getY());
    }

    private boolean collisionWith(ArrayList list, int x, int y){
        for (GameObject object: (ArrayList<GameObject>)list)
            if (object.collisionWith(x, y))
                return true;
        return false;
    }

    private boolean collisionBoom(GameObject object, int x, int y){
        if (x >= object.getX() && x <= object.getX() + 40)
            if (y >= object.getY() && x <= object.getY() + 40)
                return true;
        return false;
    }

    private boolean collisionBoom(ArrayList list, int x, int y){
        for (GameObject object: (ArrayList<GameObject>)list)
            if (collisionBoom(object, x, y)) return true;
        return false;
    }

    private boolean collisionWithAllObjects(GameObject object){
        return collisionWithAllObjects(object.getX(), object.getY());
    }

    private boolean collisionWithAllObjects(int x, int y){
        if (player.collisionWith(x, y) || exit.collisionWith(x, y)) return true;
        if (walls != null && collisionWith(walls, x, y)) return true;
        if (coins != null && collisionWith(coins, x, y)) return true;
        if (fires != null && collisionWith(fires, x, y)) return true;
        if (monsters != null && collisionWith(monsters, x, y)) return true;
        return false;
    }
    //---------------------------------------------------------------------------------------------------

    public void addWalls(){
        walls = new ArrayList<Wall>();
        while (walls.size() < Variables.WALLS_COUNT){
            Wall wall = new Wall();
            if (collisionWithAllObjects(wall))
                continue;
            walls.add(wall);
        }
    }

    public void addCoins(){
        coins = new ArrayList<Coin>();
        while (coins.size() < Variables.COINS_COUNT){
            Coin coin = new Coin();
            if (collisionWithAllObjects(coin))
                continue;
            coins.add(coin);
        }
    }

    public void addFires(){
        fires = new ArrayList<Fire>();
        while (fires.size() < Variables.FIRE_COUNT){
            Fire fire = new Fire();
            if (collisionWithAllObjects(fire))
                continue;
            fires.add(fire);
        }
    }

    public void addMonsters(){
        monsters = new ArrayList<Monster>();
        while (monsters.size() < Variables.MONSTER_COUNT){
            Monster monster = new Monster();
            if (collisionWithAllObjects(monster))
                continue;
            monsters.add(monster);
        }
    }

    public void addTNT(){
        while (true){
            if (!collisionWithAllObjects(tnt)) break;
            tnt = new Tnt();
            }
    }

    //PAINT
    //---------------------------------------------------------------------------------------------------
    public void drawGrass(Graphics gr){
        for (int i = 0; i < Variables.CELLS_H; i++){
            for (int j = 0; j < Variables.CELLS_W; j++){
                gr.drawImage(Images.GRASS, j * Variables.CELL_SIZE, i * Variables.CELL_SIZE,
                                 Variables.CELL_SIZE, Variables.CELL_SIZE,null);
            }
        }
    }

    public void drawObjects(Graphics gr){
        for (Wall wall: walls)
            wall.draw(gr);
        for (Coin coin: coins)
            coin.draw(gr);
        for (Fire fire: fires)
            fire.draw(gr);
        for (Monster monster: monsters)
            monster.draw(gr);
        exit.draw(gr);
        player.draw(gr);
        if (!hasTNT) tnt.draw(gr);
    }

    public void paintComponent(Graphics gr){
        super.paintComponent(gr);
        drawGrass(gr);
        drawObjects(gr);
    }

}
