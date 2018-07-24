import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.Random;
import javax.swing.Timer;

public class GameScreen extends JPanel implements Runnable{
    public Thread thread = new Thread(this);
    final static Logger logger = Logger.getLogger(GameScreen.class);

    private Timer timerWaveWait;
    public static Room room;
    public static Load load;
    public static Store store;
    public static Information information;

    public static int [] mobWave = {5,10,20};
    public static Mob[] mobs = new Mob[100];
    public static Tower[] tower = new Tower[100];
    public static int towerID = -1;

    int [] mobKind = {0, 1};
    Random k = new Random();

    public static int fieldWidth;
    public static int fieldHeight;

    public static int killed = 0;
    public static int level = 1;
    public static int wave = 0;
    public static int maxWave = 2;
    public static int maxLevel = 3;
    public static int winFrame = 0;
    public static int levelWait = 3;
    public static boolean waveStartTime = false;
    public static int secondsWait = Constants.startWave;

    public static Image[] ground = new Image[100];
    public static Image[] set_air = new Image[100];
    public static Image[] set_store = new Image[100];
    public static Image[] set_information = new Image[100];
    public static Image[] set_attribute = new Image[100];
    public static Image[] set_mob = new Image[100];
    public static Image[] set_bullets = new Image[100];
    public static Image[] set_pointer = new Image[100];

    public static boolean isFirst = true;
    public static boolean isDebug = false;
    public static boolean isWin = false;
    private boolean isWaiting = false;

    public static Point mouseEvent = new Point(0,0);

    public GameScreen(){}

    public GameScreen(Main frame){
        frame.addMouseListener(new MouseHandler());
        frame.addMouseMotionListener(new MouseHandler());
        thread.start();
        //setBackground(Color.ORANGE);
    }

    static public void hasWon(){
        if (killed == Constants.mobWaveCount[wave]) {
            isWin = true;
        }
    }


    public void define(){

        room = new Room();
        load = new Load();
        store = new Store();
        information = new Information();

        Constants.money = 25;
        Constants.health = 5;

        ground[0] = new ImageIcon(getClass().getResource("/res/ground1.png")).getImage();
        ground[1] = new ImageIcon(getClass().getResource("/res/ground2.png")).getImage();

        for(int i = 0; i < set_air.length; i++){
            set_air[i] = new ImageIcon(getClass().getResource("/res/set_air.png")).getImage();
            set_air[i] = createImage(new FilteredImageSource(set_air[i].getSource(), new CropImageFilter(0,32*i,32,32)));
        }

        set_store[0] = new ImageIcon(getClass().getResource("/res/set_store.png")).getImage();
        //set_store[1] = new ImageIcon(getClass().getResource("/res/set_store1.png")).getImage();
        set_attribute[0] = new ImageIcon(getClass().getResource("/res/health.png")).getImage();
        set_attribute[1] = new ImageIcon(getClass().getResource("/res/coin.png")).getImage();
        set_bullets[0] = new ImageIcon(getClass().getResource("/res/bullet.png")).getImage();

        //right-down-up-left
        for(int i = 0, j = 0; i < 12 && j < 12; i++, j++){
            set_mob[i] = new ImageIcon(getClass().getResource("/res/mob1/mob1_move.png")).getImage();
            set_mob[i] = createImage(new FilteredImageSource(set_mob[i].getSource(), new CropImageFilter(0,32*j,32,32)));
        }

        for(int i = 12, j = 0; i < 24 && j < 12; i++, j++){
            set_mob[i] = new ImageIcon(getClass().getResource("/res/mob2/mob2_move.png")).getImage();
            set_mob[i] = createImage(new FilteredImageSource(set_mob[i].getSource(), new CropImageFilter(0,32*j,32,32)));
        }

        for(int i = 24, j = 0; i < 36 && j < 12; i++, j++){
            set_mob[i] = new ImageIcon(getClass().getResource("/res/mob1/mob1_freezing.png")).getImage();
            set_mob[i] = createImage(new FilteredImageSource(set_mob[i].getSource(), new CropImageFilter(0,32*j,32,32)));
        }

        for(int i = 36, j = 0; i < 48 && j < 12; i++, j++){
            set_mob[i] = new ImageIcon(getClass().getResource("/res/mob1/mob1_burning.png")).getImage();
            set_mob[i] = createImage(new FilteredImageSource(set_mob[i].getSource(), new CropImageFilter(0,32*j,32,32)));
        }

        for(int i = 48, j = 0; i < 60 && j < 12; i++, j++){
            set_mob[i] = new ImageIcon(getClass().getResource("/res/mob2/mob2_freezing.png")).getImage();
            set_mob[i] = createImage(new FilteredImageSource(set_mob[i].getSource(), new CropImageFilter(0,32*j,32,32)));
        }

        for(int i = 60, j = 0; i < set_mob.length && j < 12; i++, j++){
            set_mob[i] = new ImageIcon(getClass().getResource("/res/mob2/mob2_burning.png")).getImage();
            set_mob[i] = createImage(new FilteredImageSource(set_mob[i].getSource(), new CropImageFilter(0,32*j,32,32)));
        }

        set_information[0] = new ImageIcon(getClass().getResource("/res/informationScreen.png")).getImage();


        for(int i = 0; i < 8; i++){
            set_pointer[i] = new ImageIcon(getClass().getResource("/res/green_pointer.png")).getImage();
            set_pointer[i] = createImage(new FilteredImageSource(set_pointer[i].getSource(), new CropImageFilter(0,16*i,15,16)));
        }

        load.newLevel(level);

        newWave();
    }

    public void newWave(){
        for(int i = 0; i <mobWave[wave]; i++){
            int kindMob = k.nextInt(mobKind.length);
            if(kindMob == 0) {
                mobs[i] = new MobBlue();
            }else if(kindMob == 1){
                mobs[i] = new MobPink();
            }
        }
    }
/*
    public void forInvoke(){
        repaint();
    }*/

    public void run(){
        while(true){
            if(!isFirst && Constants.health > 0 && !isWin){
                room.physic();
                mobSpawner();
                for(int i = 0; i <mobWave[wave]; i++){
                    if(mobs[i].inGame){
                        mobs[i].physic();
                    }
                }
            } else if(isWin){
                if(winFrame >= winFrame){
                        winFrame = 0;
                        killed = 0;
                        Constants.money += 20;
                        if(wave == maxWave)
                        {
                            try {
                                Thread.sleep(2000);
                            }catch(Exception ex){}
                            if(level == maxLevel){
                                System.exit(0);
                            }
                            level += 1;
                            wave = 0;
                            killed = 0;
                            towerID = -1;
                            tower = new Tower[100];
                            isWin = false;
                            define();
                        }else{
                            isWin = false;
                            waveStartTime = true;
                            timerWaveWait =new Timer(1000,new ActionListener(){
                                                        public void actionPerformed(ActionEvent ev) {
                                                            repaint();
                                                            if(secondsWait == 0) {
                                                                timerWaveWait.stop();
                                                                repaint();
                                                                waveStartTime = false;
                                                                secondsWait = Constants.startWave;
                                                                wave += 1;
                                                                killed = 0;
                                                                newWave();
                                                            }else {
                                                                secondsWait -= 1;
                                                            }
                                                        }});
                            timerWaveWait.start();
                        }

                }else{
                    winFrame += 1;
                }
            }
            repaint();
            try {
                Thread.sleep(1);
            }catch(Exception ex){}
        }
    }

    public int spawnTime = 2000;
    public int spawnFrame = 0;
    public void mobSpawner(){
        if(spawnFrame >= spawnTime){
            for(int i = 0; i<mobWave[wave]; i++){
                if(!mobs[i].inGame && !mobs[i].wasKilled){
                    mobs[i].spawnMob(Constants.mobGreen);
                    break;
                }
            }
            spawnFrame = 0;
        }else{
            spawnFrame += 1;
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(isFirst) {
            fieldHeight = getHeight();
            fieldWidth = getWidth();
            define();
            isFirst = false;
        }
        g2.setColor(new Color(100,100,100));
        g2.fillRect(0,0, getWidth(), getHeight());
        g2.setColor(new Color(0,0,0));
        g2.drawLine(room.block[0][0].x-1, 0,room.block[0][0].x-1, room.block[Constants.mapBlockHeight-1][0].y-1 + Constants.blockSize + 1);
        g2.drawLine(room.block[0][Constants.mapBlockWidth-1].x + Constants.blockSize, 0,room.block[0][Constants.mapBlockWidth-1].x + Constants.blockSize, room.block[Constants.mapBlockHeight-1][0].y + Constants.blockSize);
        g2.drawLine(room.block[0][0].x-1,room.block[Constants.mapBlockHeight-1][0].y + Constants.blockSize, room.block[0][Constants.mapBlockWidth-1].x + Constants.blockSize, room.block[Constants.mapBlockHeight-1][0].y + Constants.blockSize);

        room.draw(g);//drawing the room

        g2.setStroke(new BasicStroke(1.0f));
        g2.setColor(new Color(0,0,0));
        g2.drawLine(information.blockInformation[0][0].x-1, 0,information.blockInformation[0][0].x-1, information.blockInformation[Constants.informationScreenHeight-1][0].y-1 + Constants.informationBlockSize + 1);
        g2.drawLine(information.blockInformation[0][Constants.informationScreenWidth-1].x + Constants.informationBlockSize, 0,information.blockInformation[0][Constants.informationScreenWidth-1].x + Constants.informationBlockSize, information.blockInformation[Constants.informationScreenHeight-1][0].y + Constants.informationBlockSize);
        g2.drawLine(information.blockInformation[0][0].x-1,information.blockInformation[Constants.informationScreenHeight-1][0].y + Constants.informationBlockSize, information.blockInformation[0][Constants.informationScreenWidth-1].x + Constants.informationBlockSize, information.blockInformation[Constants.informationScreenHeight-1][0].y + Constants.informationBlockSize);

        information.draw(g);

        for(int i = 0; i<mobWave[wave]; i++){
            if(mobs[i].inGame){
                mobs[i].draw(g);
            }
        }

        store.draw(g);

        if(Constants.health < 1){
            g2.setColor(new Color(240, 20,20));
            g2.fillRect(0,0,GameScreen.fieldWidth,GameScreen.fieldHeight);
            g2.setColor(new Color(255,255,255));
            g2.setFont(new Font("Courier New", Font.BOLD, 16));
            g2.drawString("GAME OVER", GameScreen.fieldWidth/2 - 200, GameScreen.fieldHeight/2);
        }

        if(isWin && wave == maxWave){
            g.setColor(new Color(20,134,0));
            g2.fillRect(0,0,getWidth(), getHeight());
            g2.setColor(new Color(255,255,255));
            g2.setFont(new Font("Courier New", Font.BOLD, 16));
            if(level == maxLevel){
                g2.drawString("YOU WON !!!", GameScreen.fieldWidth/2 - 200, GameScreen.fieldHeight/2);
            }else {
                g2.drawString("YOU WON !!! Wait for the next level...", GameScreen.fieldWidth/2 - 200, GameScreen.fieldHeight/2);
            }
        }
    }
}
