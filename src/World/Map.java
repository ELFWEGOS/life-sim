package World;

import Entities.Entity;
import Entities.Predator;
import Entities.Prey;
import Utils.Position;

import java.util.ArrayList;

public class Map {
    int height;
    int width;
    Cell[][] grid;

    public Map(int width,int height){
        this.width = width;
        this.height = height;
        this.grid = new Cell[this.height][this.width];

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.grid[y][x] = new Cell();
            }
        }
    }

    public Prey getNearestPrey(Predator preadtor,int searchRayon){
        ArrayList<Prey> preys = getPreys();
        double dx = 0;
        double dy = 0;
        double d = 0;

        Prey nearsetPrey = null;
        double lastD = 0;

        for (Prey prey : preys){
            dx = prey.getX() - preadtor.getX();
            dy = prey.getY() - preadtor.getY();
            d = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));

            if(nearsetPrey == null){
                nearsetPrey = prey;
                lastD = d;
            }else {
                if(lastD > d){
                    nearsetPrey = prey;
                    lastD = d;
                }
            }
        }
        return nearsetPrey;
    }

    public Boolean isInBounds(Position position){
        int wantedX = position.getX();
        int wantedY = position.getY();
        return wantedX >= 0 && wantedX < width && wantedY >= 0 && wantedY < height;
    }

    //GETTERS

    public Cell getCell(Position position){
        return this.grid[position.getY()][position.getX()];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Prey> getPreys(){
        ArrayList<Prey> preys = new ArrayList<>();

        //HAVING ALL ENTITIES
        ArrayList<Entity> entities = this.getEntities();

        //FILTRING ENTITES TO HAVE ONLY PREYS
        for (Entity entity : entities){
            if(entity instanceof Prey){
                preys.add((Prey) entity);
            }
        }
        return preys;
    }

    public ArrayList<Entity> getEntities(){
        ArrayList<Entity> entities = new ArrayList<>();

        //HAVING ALL ENTITIES
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Position actualPos = new Position(x,y);
                Cell actualCell = this.getCell(actualPos);
                Entity actualEntity = actualCell.getEntity();
                entities.add(actualEntity);
            }
        }

        return entities;
    }
}
