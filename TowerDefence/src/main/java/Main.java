import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Main(){
        setTitle(Constants.title);
        setSize(Constants.sizeFrame);
        setResizable(false);//запретить увеличивать окно
        setLocationRelativeTo(null); //разместить окно по центру
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    public void init(){
        setLayout(new GridLayout(1,1,0,0));
        GameScreen field = new GameScreen(this);

        add(field);
        setVisible(true);
    }

    public static void main(String [] args){
        Main frame = new Main();
    }
}
