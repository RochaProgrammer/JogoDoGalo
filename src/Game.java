import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static Position[][] table = new Position[3][3];
    ArrayList<Integer> numbers = new ArrayList<>();

    public Game(){
        int counter= 1;
        for(int i = 0; i < table.length; i++){
            for (int f = 0; f < table[i].length; f++){
                table[i][f] = new Position(String.valueOf(counter));
                counter++;
            }
        }

        for(int i = 1; i <=9; i++){
            numbers.add(i);
        }
    }

    //Method's
    public void startGame(){
        Scanner sc = new Scanner(System.in);
        Player[] players = players();
        String playAgain = "1";
        int choice = 0;
        boolean itsAnOption = true;
        int playerThatMove = 0;

        while(playAgain.equals("1")) {
            resetPos();
            printGame();
            while (true) {
                while (itsAnOption) {
                    System.out.println("Where do you want to play?");
                    choice = sc.nextInt();
                    for (int i = 0; i < numbers.size(); i++) {
                        if (numbers.get(i) == choice) {
                            numbers.remove(i);
                            itsAnOption = false;
                            break;
                        }
                    }
                    if (itsAnOption) {
                        System.out.println();
                        System.out.println("Chose a valid position!!!\n");
                    }
                }
                setOnPosition(players[playerThatMove], choice);
                printGame();

                //Check Victory then if the player win, quit

                if (checkVictory(players[playerThatMove])) {
                    playerScore(players, players[playerThatMove]);
                    break;
                }

                //Change the player that will move at next round
                if (playerThatMove == 1) {
                    playerThatMove = 0;
                    itsAnOption = true;
                    continue;
                }
                playerThatMove = 1;
                itsAnOption = true;
            }

            //Ask the player if he needs to play again
            do {
                System.out.println("Wanna play Again???");
                System.out.println("1 - YES");
                System.out.println("0 - NO");
                playAgain = sc.next();
            } while (!playAgain.equals("1") && !playAgain.equals("0"));
        }
    }

    //Reset all position's
    private void resetPos(){
        for(int i = 0; i <9; i++){
            numbers.set(i,(i + 1));
        }
    }

    //Increase the number of victories/losses of a player
    private void playerScore(Player[] players, Player winner){
        if(players[0] != winner){
            players[0].setLosses((players[0].getLosses() + 1));
        }
        players[1].setLosses((players[1].getLosses() + 1));
        winner.setWins(winner.getWins() + 1);
    }

    //Check if the game have 3 in a row
    private boolean checkVictory(Player player){
        boolean win = false;

        //Check Only rows
        for (Position[] value : table) {
            if (win) {
                break;
            }

            for (Position position : value) {
                if (!position.getPlayer().equals(player.getPlayerCharacter())) {
                    win = false;
                    break;
                }
                win = true;
            }
        }

        //Check Only Columns
        for (int i = 0; i < table.length; i++){
            if (win) {
                break;
            }

            for (Position[] positions : table) {
                if (!positions[i].getPlayer().equals(player.getPlayerCharacter())) {
                    win = false;
                    break;
                }
                win = true;
            }
        }

        //Check diagonal
        {
            if ((table[0][0].getPlayer().equals(player.getPlayerCharacter()) &&
                    (table[1][1].getPlayer().equals(player.getPlayerCharacter()) &&
                            (table[2][2].getPlayer().equals(player.getPlayerCharacter()))))) {
                win = true;
            }

            if ((table[2][0].getPlayer().equals(player.getPlayerCharacter()) &&
                    (table[1][1].getPlayer().equals(player.getPlayerCharacter()) &&
                            (table[0][2].getPlayer().equals(player.getPlayerCharacter()))))) {
                win = true;
            }
        }

        return win;
    }

    //Player's Organization
    private Player[] players(){
        Scanner sc = new Scanner(System.in);
        String chart;
        System.out.println("What's your character player one?");
        chart = sc.next();
        Player playerOne = new Player(chart);
        System.out.println("What's your character player two?");
        chart = sc.next();
        Player playerTwo = new Player(chart);
        return new Player[]{playerOne,playerTwo};
    }

    //Print game
    private void printGame(){
        System.out.println(" "+table[0][0].getPlayer()+" | "+table[0][1].getPlayer()+" | "+table[0][2].getPlayer()+"\n" +
                           "-----------\n" +
                           " "+table[1][0].getPlayer()+" | "+table[1][1].getPlayer()+" | "+table[1][2].getPlayer()+"\n" +
                           "-----------\n" +
                           " "+table[2][0].getPlayer()+" | "+table[2][1].getPlayer()+" | "+table[2][2].getPlayer()+"\n");
    }

    //Set Player with on input position
    private void setOnPosition(Player player, int position){
        switch (position){
            case 1:
                table[0][0].setPlayer(player.getPlayerCharacter());
                break;
            case 2:
                table[0][1].setPlayer(player.getPlayerCharacter());
                break;
            case 3:
                table[0][2].setPlayer(player.getPlayerCharacter());
                break;
            case 4:
                table[1][0].setPlayer(player.getPlayerCharacter());
                break;
            case 5:
                table[1][1].setPlayer(player.getPlayerCharacter());
                break;
            case 6:
                table[1][2].setPlayer(player.getPlayerCharacter());
                break;
            case 7:
                table[2][0].setPlayer(player.getPlayerCharacter());
                break;
            case 8:
                table[2][1].setPlayer(player.getPlayerCharacter());
                break;
            case 9:
                table[2][2].setPlayer(player.getPlayerCharacter());
            default:
        }
    }

    //Getter's
    public Position[][] getTable() {
        return table;
    }
}
