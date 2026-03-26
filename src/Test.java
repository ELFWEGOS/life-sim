import Rendering.Renderer;
import Simulation.Simulation;
import World.Map;

import java.io.IOException;


public class Test {
    public static void main(String[] args) throws InterruptedException, IOException {
        Map map = new Map(10,10);
        Simulation simulation = new Simulation(map);
        Renderer renderer = new Renderer(map);
        simulation.generateMap(4,1);

        while (true){
            simulation.update();
            renderer.renderMap(simulation);
            Thread.sleep(20);
        }

    }
}
