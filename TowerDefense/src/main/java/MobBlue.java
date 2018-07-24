import java.awt.*;

public class MobBlue extends Mob{
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(inGame){
            if(countAnimRight > 0) {
                if ((countAnimRight < animStartRight) || (countAnimRight >= animFirstPositionRight && countAnimRight < animContinueRight)) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[24], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[36], x, y, width, height, null);
                    }else{
                        g.drawImage(GameScreen.set_mob[0], x, y, width, height, null);
                    }
                } else if (countAnimRight >= animStartRight && countAnimRight < animFirstPositionRight) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[25], x, y, width, height, null);
                    }else if(burning) {
                        g.drawImage(GameScreen.set_mob[37], x, y, width, height, null);
                    }
                    else{
                        g.drawImage(GameScreen.set_mob[1], x, y, width, height, null);
                    }
                } else if (countAnimRight >= animContinueRight) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[26], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[38], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[2], x, y, width, height, null);
                    }
                }
            }

            if(countAnimDown > 0) {
                if ((countAnimDown < animStartDown) || (countAnimDown >= animFirstPositionDown && countAnimDown < animContinueDown)) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[27], x, y, width, height, null);
                    }else if(burning) {
                        g.drawImage(GameScreen.set_mob[39], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[3], x, y, width, height, null);
                    }
                } else if (countAnimDown >= animStartDown && countAnimDown < animFirstPositionDown) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[28], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[40], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[4], x, y, width, height, null);
                    }
                } else if (countAnimDown >= animContinueDown) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[29], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[41], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[5], x, y, width, height, null);
                    }
                }
            }

            if(countAnimUp > 0) {
                if ((countAnimUp < animStartUp) || (countAnimUp >= animFirstPositionUp && countAnimUp < animContinueUp)) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[30], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[42], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[6], x, y, width, height, null);
                    }
                } else if (countAnimUp >= animStartUp && countAnimUp < animFirstPositionUp) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[31], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[43], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[7], x, y, width, height, null);
                    }
                } else if (countAnimUp >= animContinueUp) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[32], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[44], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[8], x, y, width, height, null);
                    }
                }
            }

            if(countAnimLeft > 0) {
                if ((countAnimLeft < animStartLeft) || (countAnimLeft >= animFirstPositionLeft && countAnimLeft < animContinueLeft)) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[33], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[45], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[9], x, y, width, height, null);
                    }
                } else if (countAnimLeft >= animStartLeft && countAnimLeft < animFirstPositionLeft) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[34], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[46], x, y, width, height, null);
                    }
                    else{
                        g.drawImage(GameScreen.set_mob[10], x, y, width, height, null);
                    }
                } else if (countAnimLeft >= animContinueLeft) {
                    if(freezing) {
                        g.drawImage(GameScreen.set_mob[35], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[47], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[11], x, y, width, height, null);
                    }
                }
            }

            g2.setStroke(new BasicStroke(1.0f));
            g2.setColor(new Color(134,25,0));
            g2.fillRect(x,y - (healthSpace + healthHeight), width, healthHeight);
            g2.setColor(new Color(62,134,62));
            g2.fillRect(x,y - (healthSpace + healthHeight), healthLine, healthHeight);
            g2.setColor(new Color(0,0,0));
            g2.drawRect(x,y - (healthSpace + healthHeight), healthLine - 1, healthHeight - 1);
            g2.setColor(new Color(0,0,0));
            g2.setFont(new Font("Courier New", Font.BOLD, 10));
            g2.drawString("" + health, x, y - spaceNumb);
        }
    }
}
