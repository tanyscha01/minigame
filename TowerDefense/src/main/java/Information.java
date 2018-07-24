import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information {

    public Block[][] blockInformation;
    public int towerType = 0;
    public int towerID = 0;
    public int towerNumb = 0;
    public int towerTBlue = 2;
    public int towerTYellow = 3;
    public int towerTPurple = 5;
    public int towerTBrown = 6;
    public int [] towerClicked = {2,3,5,6};
    public int cellSpace = 20;
    public int textSpace = 30;
    public Rectangle buttonImprovement;
    public static int [] buttonLevelPrice = {10,15,20,25};
    public int pointer = 0;
    public Timer timer;
    public String info;
    public boolean infoShow = false;

    public Information(){
        define();
    }

    public void define(){

        timer = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (pointer == 7) {
                    timer.stop();
                    pointer = 0;
                    timer.start();
                } else {
                    pointer += 1;
                }
            }
        });

        blockInformation = new Block[Constants.informationScreenHeight][Constants.informationScreenWidth];

        for(int y = 0; y < blockInformation.length; y++){
            for(int x = 0; x < blockInformation[0].length; x++){
                blockInformation[y][x] = new Block(Constants.mapBlockWidth*(Constants.blockSize)+ ((GameScreen.fieldWidth/2)-((Constants.mapBlockWidth* Constants.blockSize)/2))/3 +(x* Constants.informationBlockSize),
                        y* Constants.informationBlockSize,Constants.informationBlockSize, Constants.informationBlockSize, Constants.ground, Constants.air);
            }
        }

        buttonImprovement = new Rectangle(blockInformation[0][0].x-1, blockInformation[0][0].y, 15, 15);
    }

    public void click(int mouseButton){
        if(mouseButton == 1) {
            for(int y = 0; y < blockInformation.length; y++) {
                for (int x = 0; x < blockInformation[0].length; x++) {
                    if (blockInformation[y][x].contains(GameScreen.mouseEvent)){
                        if (blockInformation[y][x] == blockInformation[1][2]) {
                            if(GameScreen.tower[towerNumb].level != GameScreen.tower[towerNumb].maxLevel){
                                if(Constants.money >= buttonLevelPrice[0]){
                                    Constants.money -= buttonLevelPrice[0];
                                    GameScreen.tower[towerNumb].levelUP();
                                }else{
                                    infoShow = true;
                                    info = "You need more money";
                                    GameScreen.room.timerInfo.start();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        for(int x = 0; x < blockInformation.length; x++){
            for(int y = 0; y < blockInformation[0].length; y++){
                g2.setStroke(new BasicStroke(1.0f));
                g2.drawImage(GameScreen.set_information[0], blockInformation[x][y].x, blockInformation[x][y].y, blockInformation[x][y].width, blockInformation[x][y].height, null);
            }
        }

            if (towerType != 0) {
                for(int i = 0; i <= GameScreen.towerID; i++) {
                    if(GameScreen.tower[i].towerID == towerID) {
                        g2.setColor(new Color(92, 70, 14));
                        g2.setFont(new Font("Courier New", Font.BOLD, 14));
                        if(GameScreen.tower[i].level != GameScreen.tower[i].maxLevel) {
                            g2.drawString(buttonLevelPrice[0] + " coins", blockInformation[0][3].x - 20, blockInformation[1][0].y + 30);
                            if (!timer.isRunning()) {
                                timer.start();
                            }
                            g2.drawImage(GameScreen.set_pointer[pointer], blockInformation[0][2].x-5, blockInformation[1][0].y, blockInformation[0][0].width-20, blockInformation[0][0].height-20, null);
                        }

                        towerNumb = i;
                        g2.setStroke(new BasicStroke(1.0f));
                        //g2.drawImage(GameScreen.set_pointer[0], buttonImprovement.x, buttonImprovement.y, buttonImprovement.width, buttonImprovement.height, null);
                        if(towerType == towerTBlue){
                        g2.drawImage(GameScreen.set_air[2], blockInformation[0][0].x + 2, blockInformation[0][0].y + 2, blockInformation[0][0].width * 2, blockInformation[0][0].height * 2, null);}
                        else if(towerType == towerTYellow){
                            g2.drawImage(GameScreen.set_air[3], blockInformation[0][0].x + 2, blockInformation[0][0].y + 2, blockInformation[0][0].width * 2, blockInformation[0][0].height * 2, null);
                        }else if(towerType == towerTPurple){
                            g2.drawImage(GameScreen.set_air[5], blockInformation[0][0].x + 2, blockInformation[0][0].y + 2, blockInformation[0][0].width * 2, blockInformation[0][0].height * 2, null);
                        }else if(towerType == towerTBrown){
                            g2.drawImage(GameScreen.set_air[6], blockInformation[0][0].x + 2, blockInformation[0][0].y + 2, blockInformation[0][0].width * 2, blockInformation[0][0].height * 2, null);
                        }
                        g2.drawString("The damage of tower - " + GameScreen.tower[i].damage, blockInformation[0][0].x + 5, blockInformation[0][0].y + blockInformation[0][0].height * 2 + cellSpace);
                        //g2.drawString("The speed of damage - " + GameScreen.tower[i].speedShoting, blockInformation[0][0].x + 5, blockInformation[0][0].y + blockInformation[0][0].height * 2 + cellSpace + textSpace);
                        g2.drawString("The tower square size - " + GameScreen.tower[i].towerSquareSize, blockInformation[0][0].x + 5, blockInformation[0][0].y + blockInformation[0][0].height * 2 + cellSpace + textSpace * 1);
                        g2.drawString("The level of tower - " + GameScreen.tower[i].level, blockInformation[0][0].x + 5, blockInformation[0][0].y + blockInformation[0][0].height * 2 + cellSpace + textSpace * 2);
                        if(GameScreen.tower[i].ability != null) {
                            g2.drawString("Additional ability - " + GameScreen.tower[i].ability, blockInformation[0][0].x + 5, blockInformation[0][0].y + blockInformation[0][0].height * 2 + cellSpace + textSpace * 3);
                        }
                    }
                }
            }
    }

    public void show(int airID, int id){

        towerID = id;
        if(airID == Constants.airTower1){
            towerType = Constants.airTower1;
        }
        if(airID == Constants.airTower2){
            towerType = Constants.airTower2;
        }
        if(airID == Constants.airTower3){
            towerType = Constants.airTower3;
        }
        if(airID == Constants.airTower4){
            towerType = Constants.airTower4;
        }
    }

    public void clear(){
        towerType = 0;
    }
}
