package Entities;

import Utils.Position;
import World.Map;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Entity {
    int x;
    int y;

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract Position update(Map map);

    public int getX() {return x;}
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(@NotNull Position position){
        this.setX(position.getX());
        this.setY(position.getY());
    }
    public Position getPosition(){
        return new Position(x,y);
    }
}
