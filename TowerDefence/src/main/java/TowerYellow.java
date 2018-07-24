import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class TowerYellow extends Tower {

    public TowerYellow(int id, int x, int y, int width, int height){
        super(id, x, y, width, height);
        towerSquareSize = 160;
        ability = "burning";
        isShoting[0] = false;

        timerLinePath = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (secondsWait == 0) {
                    timerLinePath.stop();
                    secondsWait = 1;
                } else {
                    secondsWait -= 1;
                }
            }
        });
    }

    public void levelUP(){
        if(level != maxLevel){
            level++;
        }else{
            return;
        }
        towerSquareSize += 22;
        damage += 1;
        towerSquare();
    }

    public void draw(Graphics g){
        final Graphics2D g2 = (Graphics2D)g;

        if(shotMob[0] != -1) {
            if (isShoting[0]) {
                    g2.setFont(new Font("Courier New", Font.BOLD, 72));
                    g2.setStroke(new BasicStroke(5.0f));
                    //g2.drawLine(x + (Constants.mapBlockWidth / 2), y + (Constants.mapBlockHeight / 2), GameScreen.mobs[shotMob[0]].x + (GameScreen.mobs[shotMob[0]].width / 2), GameScreen.mobs[shotMob[0]].y + GameScreen.mobs[shotMob[0]].height / 2);
                    Ellipse2D ellipse = new Ellipse2D.Double(x1, y1, 4,4);
                    g2.setColor(new Color(250, 29, 36));
                    g2.draw(ellipse);
                    g2.setStroke(new BasicStroke(1.0f));
            }
        }
    }

    public void giveDamage(){
        if (isShoting[0]) {
            if (loseFrame >= loseTime) {
                GameScreen.mobs[shotMob[0]].looseMobHealth(damage);
                GameScreen.mobs[shotMob[0]].burning = true;
                loseFrame = 0;
            } else {
                loseFrame++;
            }

            if (GameScreen.mobs[shotMob[0]].isDead()) {
                isShoting[0] = false;
                shotMob[0] = -1;
                GameScreen.hasWon();
            }
        }
    }

    public void startAtack(){
        if (!isShoting[0]) {
            for (int i = 0; i < GameScreen.mobWave[GameScreen.wave]; i++) {
                if (GameScreen.mobs[i].inGame) {
                    if (towerSquare.intersects(GameScreen.mobs[i])) {
                        isShoting[0] = true;
                        shotMob[0] = i;
                        timerCheck();
                    }
                }
            }
        }
    }
}
