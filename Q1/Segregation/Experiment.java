package Segregation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class Experiment {


    private int gridNumber;
    private int threshold;
    private Characteristic type;
    private int [][] map;
    private Color[] colors;

    private List<Agent> unsatisfied;

    Random rand = new Random();
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
        int randomNumber;
        for (int x = 0; x < gridNumber; x++) {
            for (int y = 0; y < gridNumber; y++) {
                randomNumber = rand.nextInt(type.getValue() + 1 );
                switch (randomNumber) {
                    case 0:
                        if (type0 < 15000) {
                            type0++;
                            break;
                        } else
                            randomNumber++;
                    case 1:
                        if (type1 < 15000) {
                            type1++;
                            break;
                        } else
                            randomNumber++;
                    default:
                        break;
                }


                map[x][y] = randomNumber;
                updateImage(x, y);
            }
        }
    }

    private void generateTypeThree(){
        int type0=0,type1=0,type2=0;
        int randomNumber;
        for (int x = 0; x < gridNumber; x++) {
            for (int y = 0; y < gridNumber; y++) {
                randomNumber = rand.nextInt(type.getValue() + 1 );
                switch (randomNumber) {
                    case 0:
                        if (type0 < 15000) {
                            type0++;
                            break;
                        } else
                            randomNumber++;
                    case 1:
                        if (type1 < 10000) {
                            type1++;
                            break;
                        } else
                            randomNumber++;
                    case 2:
                        if (type2 < 5000) {
                            type2++;
                            break;
                        } else
                            randomNumber++;
                    default:
                        break;
                }


                map[x][y] = randomNumber;
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
            return false; // empty grids will be returned satisfied.
        else{
            int score = 0;
            for (Agent neighbor : agent.getNeighbors()) {
                if (map[neighbor.x][neighbor.y] == type) score++;
            }
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
    }

    private List<Agent> findUnSatisfied(List<Agent> prevUnhapppy) {
    }

    private List<Agent> findUnSatisfied() {

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
