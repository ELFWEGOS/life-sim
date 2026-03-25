package Entities;

import Utils.Position;
import World.Map;

import java.util.ArrayList;
import java.util.Random;

public class Prey extends Entity{
    int newX;
    int newY;
    int v;
    Random random;
    public Prey(int x, int y) {
        super(x, y);
        this.v = 1;
        this.random = new Random();
    }

    @Override
    public Position update(Map map) {
        int dir = this.random.nextInt(4);

        int dx = 0;
        int dy = 0;

        if(dir == 0) dx = this.v;     // droite
        if(dir == 1) dx = -this.v;    // gauche
        if(dir == 2) dy = this.v;     // bas
        if(dir == 3) dy = -this.v;    // haut

        this.newX =this.x + dx;
        this.newY =this.y + dy;

        return new Position(this.newX,this.newY);
    }


    @Override
    public String toString() {
        return "P";
    }
}
