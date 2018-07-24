import java.io.*;
import java.util.*;

public class Save {
    public void loadSave(File loadPath){
        try{
        Scanner loadScanner = new Scanner(loadPath);

        while(loadScanner.hasNext()){

            for(int y = 0; y < GameScreen.room.block.length; y++){
                for(int x = 0; x < GameScreen.room.block[0].length; x++){
                    GameScreen.room.block[y][x].groundId = loadScanner.nextInt();
                }
            }

            for(int y = 0; y < GameScreen.room.block.length; y++){
                for(int x = 0; x < GameScreen.room.block[0].length; x++){
                    GameScreen.room.block[y][x].airId = loadScanner.nextInt();
                }
            }
        }
        loadScanner.close();
        }catch (Exception e){}
    }
}
