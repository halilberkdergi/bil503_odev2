package main;

import Segregation.Characteristic;
import Segregation.Experiment;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {

        Experiment experiment = new Experiment(200,4, Characteristic.TwoTypes);
        experiment.generate();

        for (int i = 0; i < 50; i++) {
            if(i%2 == 0){
                File f = new File("images/" + String.format("%03d", i) + ".png");
                f.mkdirs();
                ImageIO.write(experiment.getImage(), "PNG", f);

            }
            experiment.Step();

        }
    }
}
