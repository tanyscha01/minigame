import java.awt.*;

public class MobPink extends Mob {

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(inGame){
            if(countAnimRight > 0) {
                if ((countAnimRight < animStartRight) || (countAnimRight >= animFirstPositionRight && countAnimRight < animContinueRight)) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[48], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[60], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[12], x, y, width, height, null);
                    }
                } else if (countAnimRight >= animStartRight && countAnimRight < animFirstPositionRight) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[49], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[61], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[13], x, y, width, height, null);
                    }
                } else if (countAnimRight >= animContinueRight) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[50], x, y, width, height, null);
                    }else if(burning){
                        g.drawImage(GameScreen.set_mob[62], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[14], x, y, width, height, null);
                    }
                }
            }

            if(countAnimDown > 0) {
                if ((countAnimDown < animStartDown) || (countAnimDown >= animFirstPositionDown && countAnimDown < animContinueDown)) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[51], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[63], x, y, width, height, null);
                    }
                    else {
                        g.drawImage(GameScreen.set_mob[15], x, y, width, height, null);
                    }
                } else if (countAnimDown >= animStartDown && countAnimDown < animFirstPositionDown) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[52], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[64], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[16], x, y, width, height, null);
                    }
                } else if (countAnimDown >= animContinueDown) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[53], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[65], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[17], x, y, width, height, null);
                    }
                }
            }

            if(countAnimUp > 0) {
                if ((countAnimUp < animStartUp) || (countAnimUp >= animFirstPositionUp && countAnimUp < animContinueUp)) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[54], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[66], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[18], x, y, width, height, null);
                    }
                } else if (countAnimUp >= animStartUp && countAnimUp < animFirstPositionUp) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[55], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[67], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[19], x, y, width, height, null);
                    }
                } else if (countAnimUp >= animContinueUp) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[56], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[68], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[20], x, y, width, height, null);
                    }
                }
            }

            if(countAnimLeft > 0) {
                if ((countAnimLeft < animStartLeft) || (countAnimLeft >= animFirstPositionLeft && countAnimLeft < animContinueLeft)) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[57], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[69], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[21], x, y, width, height, null);
                    }
                } else if (countAnimLeft >= animStartLeft && countAnimLeft < animFirstPositionLeft) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[58], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[70], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[22], x, y, width, height, null);
                    }
                } else if (countAnimLeft >= animContinueLeft) {
                    if(freezing){
                        g.drawImage(GameScreen.set_mob[59], x, y, width, height, null);
                    }else if (burning){
                        g.drawImage(GameScreen.set_mob[71], x, y, width, height, null);
                    }else {
                        g.drawImage(GameScreen.set_mob[23], x, y, width, height, null);
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
