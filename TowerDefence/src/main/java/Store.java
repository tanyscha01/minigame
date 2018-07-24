import java.awt.*;

public class Store {

    public Rectangle[] buttonShop = new Rectangle[Constants.shopSize];
    public Rectangle buttonHealth;
    public Rectangle buttonCoins;
    public Rectangle buttonMobsCountKilled;
    public static int[] buttonShopID = {Constants.airTower1, Constants.airTower2, Constants.airTower3, Constants.airTower4, Constants.air, Constants.air, Constants.air, Constants.air};
    public static int itemIn = 4;
    public static int heldID = -1;
    public boolean holdsItem = false;
    public static int realID = -1;
    public static int [] buttonPrice = {10,15,20,25,0,0,0,0};

    public Store(){
        define();
    }

    public void click(int mouseButton){
        if(mouseButton == 1){
            for(int i = 0; i < buttonShop.length; i++){
                if(buttonShop[i].contains(GameScreen.mouseEvent)){
                    if(buttonShopID[i] != Constants.air){
                        if(buttonShopID[i] == Constants.airTrashCan){
                            holdsItem = false;
                        }else {
                            heldID = buttonShopID[i];
                            realID = i;
                            holdsItem = true;
                        }
                    }
                }
            }

            if(holdsItem){
                if(Constants.money >= buttonPrice[realID]){
                    for(int y = 0; y < GameScreen.room.block.length; y++){
                        for(int x = 0; x < GameScreen.room.block[0].length; x++) {
                            if (GameScreen.room.block[y][x].contains(GameScreen.mouseEvent)){
                                if(GameScreen.room.block[y][x].groundId != Constants.groundRoad && GameScreen.room.block[y][x].airId == Constants.air){
                                    GameScreen.room.block[y][x].airId = heldID;
                                    Constants.money -= buttonPrice[realID];
                                    if(heldID == buttonShopID[0]){
                                        GameScreen.tower[GameScreen.towerID+1] = new TowerBlue((y+1)*10+x, GameScreen.room.block[y][x].x, GameScreen.room.block[y][x].y, GameScreen.room.block[y][x].width, GameScreen.room.block[y][x].height);
                                        GameScreen.towerID = GameScreen.towerID + 1;
                                    }
                                    else if(heldID == buttonShopID[1]){
                                        GameScreen.tower[GameScreen.towerID+1] = new TowerYellow((y+1)*10+x, GameScreen.room.block[y][x].x, GameScreen.room.block[y][x].y, GameScreen.room.block[y][x].width, GameScreen.room.block[y][x].height);
                                        GameScreen.towerID = GameScreen.towerID + 1;
                                    }
                                    else if(heldID == buttonShopID[2]){
                                        GameScreen.tower[GameScreen.towerID+1] = new TowerPurple((y+1)*10+x, GameScreen.room.block[y][x].x, GameScreen.room.block[y][x].y, GameScreen.room.block[y][x].width, GameScreen.room.block[y][x].height);
                                        GameScreen.towerID = GameScreen.towerID + 1;
                                    }else if(heldID == buttonShopID[3]){
                                        GameScreen.tower[GameScreen.towerID+1] = new TowerBrown((y+1)*10+x, GameScreen.room.block[y][x].x, GameScreen.room.block[y][x].y, GameScreen.room.block[y][x].width, GameScreen.room.block[y][x].height);
                                        GameScreen.towerID = GameScreen.towerID + 1;
                                    }
                                }
                            }
                        }
                    }
                }else{
                    GameScreen.information.infoShow = true;
                    GameScreen.information.info = "You need more money";
                    GameScreen.room.timerInfo.start();
                }
            }
        }
    }

    public void define(){
        for(int i = 0; i< buttonShop.length; i++){
            buttonShop[i] = new Rectangle((GameScreen.fieldWidth/2) - ((Constants.shopSize*(Constants.shopButtonSize+ Constants.cellSpace))/2) + ((Constants.shopButtonSize+ Constants.cellSpace)*i),(GameScreen.room.block[Constants.mapBlockHeight-1][0].y)+Constants.blockSize+ Constants.spaceFromRoom, Constants.shopButtonSize, Constants.shopButtonSize);
        }

        buttonHealth = new Rectangle(GameScreen.room.block[0][0].x-1, buttonShop[0].y, Constants.iconSize, Constants.iconSize);
        buttonCoins = new Rectangle((GameScreen.room.block[0][0].x-1)+(GameScreen.room.block[0][0].x-1) , buttonShop[0].y, Constants.iconSize, Constants.iconSize);
        buttonMobsCountKilled = new Rectangle(GameScreen.room.block[0][Constants.mapBlockWidth-1].x-1, buttonShop[0].y, Constants.blockSize/4, Constants.blockSize/4);
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < buttonShop.length; i++){
            if(buttonShop[i].contains(GameScreen.mouseEvent)){
                //g.clearRect(buttonShop[i].x, buttonShop[i].y, buttonShop[i].width, buttonShop[i].height);
                g.setColor(new Color(200,150,150,150));
                g.fillRect(buttonShop[i].x, buttonShop[i].y, buttonShop[i].width, buttonShop[i].height);
                //g.drawImage(GameScreen.set_store[1], buttonShop[i].x, buttonShop[i].y, buttonShop[i].width, buttonShop[i].height, null);
            }else{
            //g.fillRect(buttonShop[i].x, buttonShop[i].y, buttonShop[i].width, buttonShop[i].height);
            g2.drawImage(GameScreen.set_store[0], buttonShop[i].x, buttonShop[i].y, buttonShop[i].width, buttonShop[i].height, null);}
            g2.setFont(new Font("Courier New", Font.BOLD, 14));
            g2.setColor(new Color(255,255,255));
            if(buttonPrice[i] > 0){
                g2.drawString("$" + buttonPrice[i], buttonShop[i].x, buttonShop[i].y  + buttonShop[i].height - itemIn);
            }
            if(buttonShopID[i] != Constants.air) {
                g2.drawImage(GameScreen.set_air[buttonShopID[i]], buttonShop[i].x + itemIn, buttonShop[i].y + itemIn, buttonShop[i].width - (itemIn * 2), buttonShop[i].height - (itemIn * 2), null);
            }
        }

        g2.drawImage(GameScreen.set_attribute[0], buttonHealth.x,buttonHealth.y,buttonHealth.width,buttonHealth.height, null);
        g2.drawImage(GameScreen.set_attribute[1], buttonCoins.x,buttonCoins.y,buttonCoins.width,buttonCoins.height, null);
        g2.setFont(new Font("Courier New", Font.BOLD, 14));
        g2.setColor(new Color(255,255,255));
        g2.drawString(""+ Constants.health, buttonHealth.x+buttonHealth.width+ Constants.iconSpace, buttonHealth.y+buttonHealth.height);
        g2.drawString(""+ Constants.money, buttonCoins.x+buttonCoins.width+ Constants.iconSpace, buttonCoins.y+buttonHealth.height);
        g2.drawString("Wave: " + (GameScreen.wave+1) + "/" + (GameScreen.maxWave + 1) + " " + "Mobs killed: " + GameScreen.killed + "/"+ GameScreen.mobWave[GameScreen.wave], buttonMobsCountKilled.x+buttonMobsCountKilled.width+ Constants.iconSpace, buttonMobsCountKilled.y+buttonMobsCountKilled.height);

        if(holdsItem){
            g2.drawImage(GameScreen.set_air[heldID], GameScreen.mouseEvent.x - ((buttonShop[0].width - (itemIn*2)/2)) + itemIn, GameScreen.mouseEvent.y + itemIn, buttonShop[0].width - (itemIn*2), buttonShop[0].height - (itemIn*2), null );
        }
    }
}
