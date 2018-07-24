import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tower {
    protected int towerSquareSize ;
    protected Rectangle towerSquare;
    protected int damage;
    protected int level;
    protected int loseFrame;
    protected int towerID;
    protected int loseTime;
    protected String ability;
    protected int maxLevel;
    protected boolean [] isShoting;
    protected int x = 0;
    protected int y = 0;
    protected int width = 0;
    protected int height = 0;
    protected int [] shotMob;

    public Timer timerLinePath;
    public Timer timerCoordinates;
    public int secondsWait = 1;

    int x1;
    int x2;
    int y1;
    int y2;
    protected boolean x1minusBulletDirection = false;
    protected boolean x1plusBulletDirection = false;
    protected boolean y1minusBulletDirection = false;
    protected boolean y1plusBulletDirection = false;
    int times = 10;

    public Tower(int id, int x, int y, int width, int height){
        towerID = id;
        towerSquareSize = 150;
        damage = 1;
        level = 1;
        maxLevel = 3;
        loseFrame = 100;
        loseTime = 100;
        isShoting = new boolean[2];
        ability = null;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        towerSquare();
        shotMob = new int[2];
        for(int i = 0; i < shotMob.length; i++){
            shotMob[i] = -1;
        }

        timerLinePath = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (secondsWait == 0) {
                    timerLinePath.stop();
                    secondsWait = 1;
                    //System.out.println("Нету полоски");
                } else {
                    secondsWait -= 1;
                    //System.out.println("Полоска");
                }
            }
        });

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
                    times = 10;
                    x1minusBulletDirection = false;
                    x1plusBulletDirection = false;
                    y1minusBulletDirection = false;
                    y1plusBulletDirection = false;
                }
            }
        });
    }

    public Rectangle towerSquare(){
        towerSquare = new Rectangle(x - (towerSquareSize/2),y - (towerSquareSize/2),width + towerSquareSize,height + (towerSquareSize));
        return towerSquare;
    }

    public void levelUP(){}

    public void draw(Graphics g){
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

    public void continueAtack(){
        if (shotMob[0] != -1 && (towerSquare.intersects(GameScreen.mobs[shotMob[0]]))) {
            isShoting[0] = true;
            timerCheck();
        } else {
            isShoting[0] = false;
        }
    }

    public void timerCheck(){
        if(!timerLinePath.isRunning()){
            timerLinePath.start();
            if(timerCoordinates.isRunning()){
                timerCoordinates.stop();
                x1minusBulletDirection = false;
                x1plusBulletDirection = false;
                y1minusBulletDirection = false;
                y1plusBulletDirection = false;
                times = 10;
            }
            x1 = x + (Constants.mapBlockWidth / 2) + 12;
            x2 = GameScreen.mobs[shotMob[0]].x + (GameScreen.mobs[shotMob[0]].width / 2);
            y1 = y + (Constants.mapBlockHeight / 2) + 12;
            y2 = GameScreen.mobs[shotMob[0]].y + GameScreen.mobs[shotMob[0]].height / 2;
            timerCoordinates.start();
        }
    }

    public void giveDamage(){
        if (isShoting[0]) {
            if (loseFrame >= loseTime) {
                GameScreen.mobs[shotMob[0]].looseMobHealth(damage);
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
