package Entities;

import Utils.Position;
import World.Map;

import java.util.ArrayList;

public class Vide extends Entity{
    public Vide(int x, int y) {
        super(x, y);
    }

    @Override
    public Position update(Map map) {

        return null;
    }


    @Override
    public String toString() {
        return ".";
    }
}
