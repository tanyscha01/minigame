import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Room {

    public Block[][] block;
    public Rectangle stringStartWave;
    public Timer timerInfo;
    private boolean infoShow = false;

    public Room(){
        define();
    }

    public void define()
    {
        block = new Block[Constants.mapBlockHeight][Constants.mapBlockWidth];

        for(int y = 0; y < block.length; y++){
            for(int x = 0; x < block[0].length; x++){
                block[y][x] = new Block((GameScreen.fieldWidth/2)-((Constants.mapBlockWidth* Constants.blockSize)/2)- Constants.blockSize*2 +(x* Constants.blockSize),
                        y* Constants.blockSize, Constants.blockSize, Constants.blockSize, Constants.ground, Constants.air);
            }
        }

        timerInfo = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (GameScreen.information.infoShow) {
                    GameScreen.information.infoShow = false;
                    infoShow = true;
                }else{
                    infoShow = false;
                    timerInfo.stop();
                }
            }
        });
    }
    public void physic(){
        for(int y = 0; y < block.length; y++){
            for(int x = 0; x < block[0].length; x++){
                block[y][x].physic(y,x);
            }
        }
    }

    public void draw(Graphics g){
        for(int x = 0; x < block.length; x++){
            for(int y = 0; y < block[0].length; y++){
                block[x][y].draw(g);
            }
        }

        for(int x = 0; x < block.length; x++){
            for(int y = 0; y < block[0].length; y++){
                block[x][y].fight(g, x, y);
            }
        }

        if(GameScreen.waveStartTime){
            stringStartWave = new Rectangle(GameScreen.room.block[0][0].x - 1, GameScreen.room.block[0][0].y, Constants.blockSize / 4, Constants.blockSize / 4);
            g.setFont(new Font("Courier New", Font.BOLD, 16));
            g.setColor(new Color(255,255,255));
            g.drawString("Wait for new wave - "+ (GameScreen.secondsWait) + " seconds", stringStartWave.x+stringStartWave.width+ Constants.iconSpace, stringStartWave.y+stringStartWave.height);
        }else if(infoShow){
            stringStartWave = new Rectangle(GameScreen.room.block[0][0].x - 1, GameScreen.room.block[0][0].y, Constants.blockSize / 4, Constants.blockSize / 4);
            g.setFont(new Font("Courier New", Font.BOLD, 16));
            g.setColor(new Color(255,255,255));
            g.drawString(GameScreen.information.info, stringStartWave.x+stringStartWave.width+ Constants.iconSpace, stringStartWave.y+stringStartWave.height);
        }
    }

    public void click(int mouseButton){
            if(mouseButton == 1){
                for(int y = 0; y < block.length; y++){
                    for(int x = 0; x < block[0].length; x++) {
                        if (block[y][x].contains(GameScreen.mouseEvent)) {
                            if(GameScreen.room.block[y][x].groundId != Constants.groundRoad && GameScreen.room.block[y][x].airId != Constants.air){
                                int id = (y+1)*10+x;
                                GameScreen.information.show(GameScreen.room.block[y][x].airId, id);
                            }else{
                                GameScreen.information.clear();
                            }
                        }
                    }
                }

            }else if (mouseButton == 3){
                GameScreen.store.holdsItem = false;
            }
    }
}
