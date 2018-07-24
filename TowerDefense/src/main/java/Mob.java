import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class Mob extends Rectangle {

    protected int xC, yC;
    protected int health;
    protected int healthLine;
    protected int healthSpace = 3;
    protected int healthHeight = 6;
    protected int mobID = Constants.mobAir;
    protected boolean inGame = false;
    protected int mobWalk = 0;
    protected int upward = 2, downward = 1, right = 0, left = -1;
    protected int direction = right;
    protected boolean hasUpward = false;
    protected boolean hasDownward = false;
    protected boolean hasLeft = false;
    protected boolean hasRight = false;
    protected boolean wasKilled = false;
    protected boolean freezing = false;
    protected boolean burning = false;
    public Timer timer;
    public Timer timer2;
    public int secondsWait = 5;

    public Mob(){
        timer = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                timer.stop();
                freezing = false;
            }
        });

        timer2 = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if(secondsWait == 0) {
                    timer2.stop();
                    burning = false;
                    secondsWait = 5;
                }else{
                    secondsWait--;
                    looseMobHealth(1);
                }
            }
        });
    }

    public void spawnMob(int mobID){
        for(int y = 0; y< GameScreen.room.block.length; y++){
            if(GameScreen.room.block[y][0].groundId == Constants.groundRoad){
                setBounds(GameScreen.room.block[y][0].x, GameScreen.room.block[y][0].y, Constants.mobSize, Constants.mobSize);
                xC = 0;
                yC = y;
            }
        }
        this.mobID = mobID;
        health = Constants.mobLive[GameScreen.wave];
        healthLine = Constants.blockSize;
        inGame = true;
    }

    public int animStartRight = 10;
    public int animFirstPositionRight = 20;
    public int animContinueRight = 30;
    public int animEndRight = 40;
    public int countAnimRight = 0;

    public int animStartDown = 10;
    public int animFirstPositionDown = 20;
    public int animContinueDown = 30;
    public int animEndDown = 40;
    public int countAnimDown = 0;

    public int animStartUp = 10;
    public int animFirstPositionUp = 20;
    public int animContinueUp = 30;
    public int animEndUp = 40;
    public int countAnimUp = 0;

    public int animStartLeft = 10;
    public int animFirstPositionLeft = 20;
    public int animContinueLeft = 30;
    public int animEndLeft = 40;
    public int countAnimLeft = 0;

    public int spaceNumb = 10;

    abstract void draw(Graphics g);

    public void deleteMob(){
        inGame = false;
        wasKilled = true;
        direction = right;
        mobWalk = 0;
        GameScreen.killed += 1;
        GameScreen.hasWon();
        //Constants.mobCountKilled += 1;
        //чтобы получить 5 монет от одной башни
        GameScreen.room.block[0][0].getMoney(mobID);
    }

    public void looseHealth(){
        Constants.health--;
        if(Constants.health < healthLine){
            healthLine--;
        }
    }

    public void looseMobHealth(int countLive){
        health -= countLive;
        if(health < healthLine){
            healthLine -= countLive;
        }

        checkDeath();
    }

    public void checkDeath(){
        if(health <= 0 && (wasKilled == false)){
            deleteMob();
        }
    }

    public boolean isDead(){
        if(inGame){
            return false;
        }else{
            return true;
        }
    }

    public int walkFrame = 0;
    public int walkSpeed = 20;

    public void physic() {
        if(burning){
            if(!timer2.isRunning()) {
                timer2.start();
            }
            freezing = false;
            timer.stop();
        }
        if (freezing != true){
            if (walkFrame >= walkSpeed) {
                if (direction == right) {
                    x += 1;
                    countAnimRight++;
                    if (countAnimRight == animEndRight) {
                        countAnimRight = 1;
                    }
                } else if (direction == left) {
                    x -= 1;
                    countAnimLeft++;
                    if (countAnimLeft == animEndLeft) {
                        countAnimLeft = 1;
                    }
                } else if (direction == downward) {
                    y += 1;
                    countAnimDown++;
                    if (countAnimDown == animEndDown) {
                        countAnimDown = 1;
                    }

                } else if (direction == upward) {
                    y -= 1;
                    countAnimUp++;
                    if (countAnimUp == animEndUp) {
                        countAnimUp = 1;
                    }
                }
                mobWalk += 1;
                if (mobWalk == Constants.blockSize) {
                    if (direction == right) {
                        xC += 1;
                        hasRight = true;
                    } else if (direction == left) {
                        xC -= 1;
                        hasLeft = true;
                    } else if (direction == downward) {
                        yC += 1;
                        hasDownward = true;
                    } else if (direction == upward) {
                        yC -= 1;
                        hasUpward = true;
                    }

                    if (!hasLeft) {
                        try {
                            if (GameScreen.room.block[yC][xC + 1].groundId == Constants.groundRoad) {
                                direction = right;
                                countAnimDown = 0;
                                countAnimUp = 0;
                                countAnimLeft = 0;
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (!hasRight) {
                        try {
                            if (GameScreen.room.block[yC][xC - 1].groundId == Constants.groundRoad) {
                                direction = left;
                                countAnimRight = 0;
                                countAnimDown = 0;
                                countAnimUp = 0;
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (!hasUpward) {
                        try {
                            if (GameScreen.room.block[yC + 1][xC].groundId == Constants.groundRoad) {
                                direction = downward;
                                countAnimRight = 0;
                                countAnimUp = 0;
                                countAnimLeft = 0;
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (!hasDownward) {
                        try {
                            if (GameScreen.room.block[yC - 1][xC].groundId == Constants.groundRoad) {
                                direction = upward;
                                countAnimDown = 0;
                                countAnimRight = 0;
                                countAnimLeft = 0;
                            }
                        } catch (Exception e) {
                        }
                    }

                    if (GameScreen.room.block[yC][xC].airId == Constants.airEnd) {
                        deleteMob();
                        looseHealth();
                    }
                    hasUpward = false;
                    hasDownward = false;
                    hasRight = false;
                    hasLeft = false;
                    mobWalk = 0;
                }
                walkFrame = 0;
            } else {
                walkFrame += 1;
            }
        }else if (freezing){
            timer.start();
            timer2.stop();
            secondsWait = 5;
            burning = false;
        }
    }
}
