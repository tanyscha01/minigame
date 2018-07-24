import java.awt.*;
import java.lang.reflect.Method;

public class Block extends Rectangle{

    public int groundId;
    public int airId;
    public Method method;

    public Block(int x, int y, int width, int height, int groundId, int airId){
        setBounds(x,y,width,height);
        this.groundId = groundId;
        this.airId = airId;
    }

    public void draw(Graphics g){

        final Graphics2D g2 = (Graphics2D)g;
        g.drawImage(GameScreen.ground[groundId], x, y, width, height, null);

        if(airId != Constants.air){
            g.drawImage(GameScreen.set_air[airId], x, y, width, height, null);
        }

    }

    public void reflexionRepaint(Class r){
            final Class threadClass = r;
            try {
                method = threadClass.getDeclaredMethod("forInvoke");
                method.setAccessible(true);
            }catch(Exception e){
                System.out.println("Method is not exist");
            }
            try {
                method.invoke(threadClass.newInstance(), null);
            }catch(Exception e){
                System.out.println("Method is not exist in invoke");
            }
    }

    public void physic(int y, int x){

        if (airId == Constants.airTower1 || airId == Constants.airTower2 || airId == Constants.airTower3 || airId == Constants.airTower4) {
            for (int j = 0; j <= GameScreen.towerID; j++) {

                    if (GameScreen.tower[j].towerID == ((y + 1) * 10 + x)) {
                        GameScreen.tower[j].continueAtack();

                        if (airId == Constants.airTower1 || airId == Constants.airTower2 || airId == Constants.airTower3 || airId == Constants.airTower4) {
                            GameScreen.tower[j].startAtack();
                        }

                        if(airId == Constants.airTower1 || airId == Constants.airTower2 || airId == Constants.airTower4){
                            if(GameScreen.tower[j].secondsWait == 0) {
                                GameScreen.tower[j].giveDamage();
                            }
                        }
                        else if(airId == Constants.airTower3){
                                GameScreen.tower[j].giveDamage();
                        }
                        }
            }
        }
    }

    public void getMoney(int mobID){
        Constants.money += Constants.deathReward[mobID];
    }

    public void fight(Graphics g, int yy, int xx){

        final Graphics2D g2 = (Graphics2D)g;
        if(GameScreen.isDebug) {
            if (airId == Constants.airTower1 || airId == Constants.airTower2 || airId == Constants.airTower3) {
                for(int i = 0; i <= GameScreen.towerID; i++) {
                    if (GameScreen.tower[i].towerID == ((yy + 1) * 10 + xx)) {
                        if (GameScreen.tower[i] == null) {
                            return;
                        }
                        g.drawRect(GameScreen.tower[i].towerSquare.x, GameScreen.tower[i].towerSquare.y, GameScreen.tower[i].towerSquare.width, GameScreen.tower[i].towerSquare.height);
                    }
                }
            }
        }

        g2.setFont(new Font("Courier New", Font.BOLD, 72));
        g2.setColor(new Color(250,29,36));

        if (airId == Constants.airTower1 || airId == Constants.airTower2 || airId == Constants.airTower3) {
            for(int i = 0; i <= GameScreen.towerID; i++) {
                GameScreen.tower[i].draw(g);
            }
        }
    }
}
