package Entities;

import Utils.Position;
import World.Map;

import java.util.ArrayList;
import java.util.Random;

public class Predator extends Entity{
    Random random;
    Prey cible;
    int v;
    int newX;
    int newY;
    int searchRayon;
    public Predator(int x, int y) {
        super(x, y);
        this.v = 1;
        this.searchRayon = 5;
        this.random = new Random();
    }

    @Override
    public Position update(Map map) {
        //VERIFIER SI IL A UNE CIBLE
        //SI LA CIBLE NEST PLUS DANS LE RAYON RENDRE CIBLE = NULL
        if (this.cible != null){
            if (!isInRange(this.cible)){
                this.cible = null;
               // System.out.println("CIBLE = NULL");
            }
        }
        //SI ON A PAS DE CIBLE EN RECHERCHE LA PLUS PROCHE
        if (this.cible == null){
            this.cible = map.getNearestPrey(this,this.searchRayon);
            //System.out.println("TROUVER !!!!!");
        }
        //SI ON A UNE CIBLE ALLER VERS ELLE
        if(this.cible != null){
           // System.out.println("I'AM COMING");
            return moveToward(this.cible.getPosition());
        }
       // System.out.println("RANDOM T_T");
        return moveRandomly();
    }

    private boolean isInRange(Prey prey){
        int dx = Math.abs(prey.getX() - this.x);
        int dy = Math.abs(prey.getY() - this.y);
        return dx <= this.searchRayon && dy <= searchRayon;
    }

    private Position moveToward(Position target){
        int dx = target.getX() - this.x;
        int dy = target.getY() - this.y;

        int moveX = 0;
        int moveY = 0;
        if(Math.abs(dx) < Math.abs(dy)){
            moveX = Integer.signum(dx) * this.v;
        }else {
            moveY = Integer.signum(dy) * this.v;
        }
        return new Position(this.x + moveX,this.y + moveY);
    }

    private Position moveRandomly(){
        int dir = random.nextInt(4);

        int dx = 0;
        int dy = 0;

        if(dir == 0) dx = v;     // droite
        if(dir == 1) dx = -v;    // gauche
        if(dir == 2) dy = v;     // bas
        if(dir == 3) dy = -v;    // haut

        newX =x + dx;
        newY =y + dy;

        return new Position(newX,newY);
    }


    @Override
    public String toString() {
        return "M";
    }

    public Prey getCible() {
        return cible;
    }
    public void setCible(Prey cible) {
        this.cible = cible;
    }

}
