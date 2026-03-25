package World;

import Entities.Entity;

public class Cell {
    Entity entity;

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public Entity getEntity() {
        return entity;
    }

    public Boolean isEmpty(){
        return entity == null;
    }

    @Override
    public String toString() {
        if(!this.isEmpty()){
            return this.entity.toString();
        }else{
            return ".";
        }
    }
}
