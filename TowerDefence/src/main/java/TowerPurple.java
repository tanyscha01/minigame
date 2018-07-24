import java.awt.*;

public class TowerPurple extends Tower {

    public TowerPurple(int id, int x, int y, int width, int height){
        super(id, x, y, width, height);
        towerSquareSize = 150;
        damage = 1;
        level = 1;
        secondsWait = 2;
        isShoting[0] = false;
        ability = "laser";
    }

    public void draw(Graphics g){

        final Graphics2D g2 = (Graphics2D)g;

        if(shotMob[0] != -1) {
            if (isShoting[0]) {
                    g2.setFont(new Font("Courier New", Font.BOLD, 72));
                    g2.setColor(new Color(138, 21, 250));
                    g2.setStroke(new BasicStroke(5.0f));

                    g2.drawLine(x + (Constants.mapBlockWidth / 2), y + (Constants.mapBlockHeight / 2), GameScreen.mobs[shotMob[0]].x + (GameScreen.mobs[shotMob[0]].width / 2), GameScreen.mobs[shotMob[0]].y + GameScreen.mobs[shotMob[0]].height / 2);
                    g2.setStroke(new BasicStroke(1.0f));
            }
        }
    }

    public void continueAtack(){
        if (shotMob[0] != -1 && (towerSquare.intersects(GameScreen.mobs[shotMob[0]]))) {
            isShoting[0] = true;
        } else {
            isShoting[0] = false;
        }
    }

    public void startAtack(){
        if (!isShoting[0]) {
            for (int i = 0; i < GameScreen.mobWave[GameScreen.wave]; i++) {
                if (GameScreen.mobs[i].inGame) {
                    if (towerSquare.intersects(GameScreen.mobs[i])) {
                        isShoting[0] = true;
                        shotMob[0] = i;
                    }
                }
            }
        }
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
}
