package Simulation;

import Entities.Entity;
import Entities.Predator;
import Entities.Prey;
import Utils.Position;
import World.Cell;
import World.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    private final Random random;
    private final Map map;
    private final ArrayList<Entity> entities;
    private ArrayList<Entity> entitiesToRemove;
    private int frame = 0;

    public Simulation(Map map) {
        this.entitiesToRemove = new ArrayList<>();
        this.random = new Random();
        this.map = map;
        this.entities = new ArrayList<>();
    }

    public void update() {
        frame ++;
        for (Entity e : this.entities) {
            Position wantedPos = e.update(this.map);
            this.moveEntity(e, this.map, wantedPos);
        }
        //UI -> COUNTING FRAMES & PREYS
        for (Entity e : this.entitiesToRemove){
            entities.remove(e);
        }
        entitiesToRemove = new ArrayList<>();
    }


    public void generateMap(int numberPrey,int numberPredator) {
        //MIAW MARWANE
        int actualNbPrey = 0;
        int actualNbPredator = 0;
        for (int y = 0; y < this.map.getHeight(); y++) {
            for (int x = 0; x < this.map.getWidth(); x++) {

                float randf = this.random.nextFloat();

                Entity entity = null;

                if (randf < 0.4f){
                    if(numberPrey > actualNbPrey){
                        entity = new Prey(x,y);
                        actualNbPrey++;
                    }

                }else if (randf < 0.7f){
                    if(numberPredator > actualNbPredator){
                    entity = new Predator(x,y);
                        actualNbPredator++;
                    }
                }
                if (entity != null){
                    this.entities.add(entity);
                    this.map.getCell(new Position(x,y)).setEntity(entity);
                }
            }
        }
    }


    public void moveEntity(Entity entity,Map map,Position position) {
        if (position != null) {

            //VERIFY IF THE WANTED POSITION IS IN BOUNDS
            if (map.isInBounds(position)) {
                Cell wantedCell = map.getCell(position);

                if (wantedCell.isEmpty()) { // VERIFY IF THE WANTED CELL IS EMPTY
                    map.getCell(entity.getPosition()).setEntity(null);
                    wantedCell.setEntity(entity);
                    entity.setPosition(position);
                   // System.out.println("move vers vide");

                } else {//WANTED CELL NOT EMPTY
                     if (entity instanceof Predator && wantedCell.getEntity() instanceof Prey) { // M -> P
                        map.getCell(entity.getPosition()).setEntity(null);//VIDER LA PREMIERE CELL
                        entitiesToRemove.add(wantedCell.getEntity());//enlever la proie de la matrice MDR
                        wantedCell.setEntity(entity);//DEPLACER LE PREDATOR A SA PLACE
                        entity.setPosition(position);//CHANGER LA POSITION STOCKER
                       // System.out.println("move & manger");
                    }
                }
            }
        }
    }

    public int getFrame(){
        return this.frame;
    }

}
