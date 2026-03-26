package Rendering;

import Entities.Prey;
import Simulation.Simulation;
import Utils.Position;
import World.Map;

import java.io.IOException;
import java.util.ArrayList;

public class Renderer {
    Map map;

    public Renderer(Map map){
        this.map = map;
    }

    public void renderMap(Simulation simulation) throws IOException, InterruptedException {
        //ANSI ACTIVATION
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        // clear écran
        System.out.print("\033[H\033[2J");
        System.out.flush();
        //UI DU DESSUS
        this.showUI(simulation);
        //AFFICHAGE
        for (int y = 0; y < this.map.getHeight(); y++) {
            for (int x = 0; x < this.map.getWidth(); x++) {
                System.out.print(this.map.getCell(new Position(x,y)));
            }
            System.out.println();
        }

        System.out.println();
    }

    public void showUI(Simulation simulation){
        ArrayList<Prey> preys = map.getPreys();
        System.out.println("PREYS : "+preys.toArray().length);
        System.out.println("FRAMES : "+simulation.getFrame());
    }
}
