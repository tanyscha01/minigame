import javafx.scene.shape.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class TowerBrown extends Tower{

    Circle bulletSquare;
    int bulletSquareSize;

    public TowerBrown(int id, int x, int y, int width, int height){
        super(id, x, y, width, height);
        towerSquareSize = 150;
        bulletSquareSize = 20;
        damage = 2;
        level = 1;
        secondsWait = 2;
        isShoting[0] = false;

        timerCoordinates = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(times > 0)
                {
                    if (x1 > x2) {
                        if (x1plusBulletDirection == false) {
                            x1 = x1 - ((x1 - x2) / times);
                            x1minusBulletDirection = true;
                        }
                    }
                    if (y1 > y2) {
                        if (y1plusBulletDirection == false) {
                            y1 = y1 - ((y1 - y2) / times);
                            y1minusBulletDirection = true;
                        }
                    }
                    if (x1 < x2) {
                        if (x1minusBulletDirection == false) {
                            x1 = x1 + ((x2 - x1) / times);
                            x1plusBulletDirection = true;
                        }
                    }
                    if (y1 < y2) {
                        if (y1minusBulletDirection == false) {
                            y1 = y1 + ((y2 - y1) / times);
                            y1plusBulletDirection = true;
                        }
                    }
                    times = times - 2;
                }else{
                    timerCoordinates.stop();
                    bulletSquare(x1, y1);
                    times = 10;
                    x1minusBulletDirection = false;
                    x1plusBulletDirection = false;
                    y1minusBulletDirection = false;
                    y1plusBulletDirection = false;
                }
            }
        });
    }

    public void levelUP(){
        if(level != 3){
            level++;
        }else{
            return;
        }
        towerSquareSize += 20;
        damage += 2;
        towerSquare();
    }

    public void draw(Graphics g){

        final Graphics2D g2 = (Graphics2D)g;

        if(shotMob[0] != -1) {
            if (isShoting[0]) {
                if(secondsWait == 0){
                    g2.setFont(new Font("Courier New", Font.BOLD, 72));
                    g2.setStroke(new BasicStroke(5.0f));
                    //g2.drawLine(x + (Constants.mapBlockWidth / 2), y + (Constants.mapBlockHeight / 2), GameScreen.mobs[shotMob[0]].x + (GameScreen.mobs[shotMob[0]].width / 2), GameScreen.mobs[shotMob[0]].y + GameScreen.mobs[shotMob[0]].height / 2);
                    Ellipse2D ellipse = new Ellipse2D.Double(x1, y1, 4,4);
                    g2.setColor(new Color(250, 149, 0));
                    g2.draw(ellipse);
                    g2.setStroke(new BasicStroke(1.0f));
                }
            }
        }
    }

    public Circle bulletSquare(int x, int y){
        bulletSquare = new Circle(x - (bulletSquareSize/2),y - (bulletSquareSize/2),2 + bulletSquareSize);
        return bulletSquare;
    }
}
