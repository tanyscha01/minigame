import java.awt.*;
import java.awt.geom.Ellipse2D;

public class TowerBlue extends Tower {

    public TowerBlue(int id, int x, int y, int width, int height){
        super(id, x, y, width, height);
        towerSquareSize = 150;
        damage = 1;
        level = 1;
        secondsWait = 2;
        isShoting[0] = false;
        ability = "freezing";
    }

    public void levelUP(){
        if(level != 3){
            level++;
        }else{
            return;
        }
        towerSquareSize += 20;
        damage += 1;
        towerSquare();
    }

    public void draw(Graphics g){

        final Graphics2D g2 = (Graphics2D)g;

        if(shotMob[0] != -1) {
                if (isShoting[0]) {
                    if(secondsWait == 0){
                        g2.setFont(new Font("Courier New", Font.BOLD, 72));
                        //g2.setColor(new Color(255, 169, 40));
                        g2.setStroke(new BasicStroke(5.0f));

                        //g2.drawLine(x + (Constants.mapBlockWidth / 2), y + (Constants.mapBlockHeight / 2), GameScreen.mobs[shotMob[0]].x + (GameScreen.mobs[shotMob[0]].width / 2), GameScreen.mobs[shotMob[0]].y + GameScreen.mobs[shotMob[0]].height / 2);
                        Ellipse2D ellipse = new Ellipse2D.Double(x1, y1, 4,4);
                        g2.setColor(new Color(17, 167, 250));
                        g2.draw(ellipse);
                        g2.setStroke(new BasicStroke(1.0f));
                    }
                }
        }
    }

    public void giveDamage(){
        if (isShoting[0]) {
            if (loseFrame >= loseTime) {
                GameScreen.mobs[shotMob[0]].looseMobHealth(damage);
                GameScreen.mobs[shotMob[0]].freezing = true;
                loseFrame = 0;
            } else {
                loseFrame++;
            }

            if (GameScreen.mobs[shotMob[0]].isDead()) {
                //getMoney(GameScreen.mobs[shotMob].mobID);
                isShoting[0] = false;
                shotMob[0] = -1;
                GameScreen.hasWon();
            }
        }
    }
}
