public class Load {
    public void newLevel(int level){

    int ground [][] = new int[10][15];
    int air[][] = new int[10][15];

    switch(level){
        case 1: {
            ground = Map.level1ground;
            air = Map.level1air;
            break;
        }
        case 2: {
            ground = Map.level2ground;
            air = Map.level2air;
            break;
        }
        case 3: {
            ground = Map.level3ground;
            air = Map.level3air;
            break;
        }
        default: break;
    }


    for(int y = 0; y < GameScreen.room.block.length; y++){
                for(int x = 0; x < GameScreen.room.block[0].length; x++){
                    GameScreen.room.block[y][x].groundId = ground[y][x];
                }
            }

    for(int y = 0; y < GameScreen.room.block.length; y++){
                for(int x = 0; x < GameScreen.room.block[0].length; x++){
                    GameScreen.room.block[y][x].airId = air[y][x];
                }
            }
    }
}
