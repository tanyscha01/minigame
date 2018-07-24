import java.awt.*;

public class Constants {
    public static String title = "Tower defense";
    public static Dimension sizeFrame = new Dimension(1300,800);

    public static int blockSize = 64;
    public static int mapBlockWidth = 15;
    public static int mapBlockHeight = 10;

    public static int ground = 0;
    public static int groundRoad = 1;
    public static int air = -1;
    public static int airEnd = 0;

    public static int shopSize = 8;
    public static int shopButtonSize = 64;
    public static int cellSpace = 3;
    public static int spaceFromRoom = 20;

    public static int informationBlockSize = 64;
    public static int informationScreenWidth = 4;
    public static int informationScreenHeight = 10;

    public static int iconSize = 20;
    //изменяется в GameScreen при запуске нового уровня в define()
    public static int money = 25;
    public static int [] deathReward = {5};
    //изменяется в GameScreen при запуске нового уровня в define()
    public static int health = 10;
    public static int iconSpace = 5;

    public static int airTrashCan = 1;
    public static int airTower1 = 2;
    public static int airTower2 = 3;
    public static int airTower3 = 5;
    public static int airTower4 = 6;

    public static int mobSize = 64;//30;
    public static int mobAir = -1;
    public static int mobGreen = 0;
    public static int [] mobWaveCount = {5,10,20};
    public static int startWave = 10;
    public static int mobCount = 30;

    public static int [] mobLive = {64, 100, 200};
    //public static int mobLive = 64;
    public static int bulletSize = 5;
    public static int fpcFrame = 0, fpc = 1000000;
}
