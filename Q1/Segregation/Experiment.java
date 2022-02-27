package Segregation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Experiment {


    private int gridNumber;
    private int threshold;
    private Characteristic type;
    private int [][] map;
    private Color[] colors;

    private List<Agent> unsatisfied;

    private  BufferedImage image;



    public Experiment(int GridNumber, int Threshold, Characteristic Type )
    {
        this.threshold = Threshold;
        this.gridNumber = GridNumber;
        this.type = Type;
        map = new int[gridNumber][gridNumber];
        colors = generateColors(this.type);

        image = new BufferedImage(this.gridNumber,this.gridNumber, BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void generate(){
        switch (type){
            case TwoTypes:
                generateTypeTwo();
                break;
            case ThreeTypes:
                generateTypeThree();
                break;

        }
    }

    private void generateTypeTwo(){
        int type0=0,type1=0;
        List<Integer> types = new ArrayList<>();
        for (int x = 0; x < 15000; x++) {
            types.add(1);
        }
        for (int x = 0; x < 15000; x++) {
            types.add(0);
        }
        for (int x = 0; x < 10000; x++) {
            types.add(2);
        }

        Collections.shuffle(types);

        for (int x = 0; x < gridNumber; x++) {
            for (int y = 0; y < gridNumber; y++) {
                map[x][y] = types.remove(0);
                updateImage(x, y);
            }
        }
    }

    private void generateTypeThree(){
        int type0=0,type1=0,type2=0;
        List<Integer> types = new ArrayList<>();
        for (int x = 0; x < 20000; x++) {
            types.add(0);
        }
        for (int x = 0; x < 10000; x++) {
            types.add(1);
        }
        for (int x = 0; x < 8000; x++) {
            types.add(2);
        }
        for (int x = 0; x < 2000; x++) {
            types.add(3);
        }

        Collections.shuffle(types);

        for (int x = 0; x < gridNumber; x++) {
            for (int y = 0; y < gridNumber; y++) {
                map[x][y] = types.remove(0);
                updateImage(x, y);
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    private void updateImage(int x, int y) {
        image.setRGB(x, y , colors[map[x][y]].getRGB());
    }

    private boolean isUnsatisfied(Agent agent){

        int type = map[agent.x][agent.y];
        if(type == this.type.getValue())
            return true; // empty grids will be returned unsatisfied.
        else {
            int score = 0;
            for (Agent neighbor : agent.getNeighbors()) {
                if (map[neighbor.x][neighbor.y] == type) score++;
            }
            /*
        int type = map[agent.x][agent.y];
        int score = 0;
        for (Agent neighbor : agent.getNeighbors()) {
            if (map[neighbor.x][neighbor.y] == type) score++;
        }*/
            return score < threshold;
        }
    }

    public void Step(){
        if(unsatisfied == null)
        {
            unsatisfied = findUnSatisfied();
        }
        else{
            unsatisfied = findUnSatisfied(unsatisfied);
        }
        System.out.println(unsatisfied.size());
        List<Integer> types = getTypeList();

        //There are numerous variations in the specific details of how the movement of agents within
        //a round is handled but all of them produces similar outcomes.
        Collections.shuffle(types,new Random(3));
        moveTypes(types);

    }

    private void moveTypes(List<Integer> types) {
        for(Agent agent : unsatisfied){
            map[agent.x][agent.y] = types.remove(0);
            updateImage(agent.x, agent.y);        }
    }

    private List<Integer> getTypeList() {
        List<Integer> types = new ArrayList<>();
        for(Agent agent: unsatisfied)
        {
            types.add(map[agent.x][agent.y]);
        }
        return types;
    }

    private List<Agent> findUnSatisfied(List<Agent> prevUnhapppy) {
        List<Agent> unsatisfied = new ArrayList<>();
        for(Agent previous : prevUnhapppy){
            if(isUnsatisfied(previous))
                unsatisfied.add(previous);

        }
        return unsatisfied;
    }

    private List<Agent> findUnSatisfied() {
        List<Agent> unsatisfied = new ArrayList<>();
        for(int x=0 ; x < gridNumber; x++){
            for (int y=0 ; y < gridNumber ; y++){
                Agent agent = new Agent(x, y, gridNumber);
                if(isUnsatisfied(agent))
                    unsatisfied.add(agent);
            }
        }
        return  unsatisfied;
    }

    private Color[] generateColors (Characteristic type){
        int n = type.getValue();
        Color[] color = new Color[n+1]; // +1 where empty grid is black.
        for (int i = 0; i < n; i++) {
            color[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        }
        color[n] = Color.BLACK; // empty
        return color;

    }


}
