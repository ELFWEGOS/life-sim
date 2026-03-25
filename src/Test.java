import Simulation.Simulation;

import java.io.IOException;


public class Test {
    public static void main(String[] args) throws InterruptedException, IOException {
        //NAZUIM SUR GIT WOW
        Simulation simulation = new Simulation();
        simulation.generateMap(4,1);

        while (true){
            simulation.update();
            simulation.renderMap();
            Thread.sleep(100);
        }

    }
}
