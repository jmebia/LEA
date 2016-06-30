/**
 * Created by jmebia on 29/06/2016.
 */

import javax.swing.JFrame;
import java.awt.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Launcher {

    public static void main(String[] args){
        JFrame frame = new JFrame("LEA | Light-weight Email App [Gmail]");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(600,450);
        frame.add(new Mail());
        frame.setVisible(true);
    }

}

