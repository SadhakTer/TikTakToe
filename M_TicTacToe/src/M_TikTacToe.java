import java.util.*;

public class M_TikTacToe {
    static char cell1 = '1';
    static char cell2 = '2';
    static char cell3 = '3';
    static char cell4 = '4';
    static char cell5 = '5';
    static char cell6 = '6';
    static char cell7 = '7';
    static char cell8 = '8';
    static char cell9 = '9';

    static char symbol;
    static Scanner sc = new Scanner(System.in);

    static byte placement;

    static ArrayList<Byte> Xplacements = new ArrayList<Byte>();
    static ArrayList<Byte> Oplacements = new ArrayList<Byte>();

    public static void main(String[] args) {
        System.out.println("Enter game mode [playMultiplayer/m] or [CPU/c]:");
        String gameMode = sc.next();
        if(gameMode.equalsIgnoreCase("m") || gameMode.equalsIgnoreCase("playMultiplayer")){
            playMultiplayer();
        } else if(gameMode.equalsIgnoreCase("c") || gameMode.equalsIgnoreCase("cpu")){
            cpu();
        } else{
            System.err.println("Invalid game mode!");
        }
    }

    public static void printGameBoard(){
        System.out.println();
        System.out.println(" " + cell1 + " | " + cell2 + " | " + cell3);
        System.out.println("---+---+---");
        System.out.println(" " + cell4 + " | " + cell5 + " | " + cell6);
        System.out.println("---+---+---");
        System.out.println(" " + cell7 + " | " + cell8 + " | " + cell9);
        System.out.println();
    }

    public static void playMultiplayer(){
        toss();
        for(int i = 1;; i++) {
            printGameBoard();
            System.out.println("Player " + symbol + " turn");
            System.out.println("Enter placement:");
            placement = sc.nextByte();
            if(Xplacements.contains(placement) || Oplacements.contains(placement)){
                System.err.println("Place already occupied!");
            } else{
                if(placement > 0 && placement <= 9) {
                    if (symbol == 'X' || symbol == 'x') {
                        putPlacement(symbol,Xplacements);
                        String placementresult = checkWinner();
                        if(placementresult != null){
                            printGameBoard();
                            System.out.println(placementresult);
                            break;
                        }
                        symbol = '@';
                    } else if(symbol == '@'){
                        putPlacement(symbol,Oplacements);
                        String placementresult = checkWinner();
                        if(placementresult != null){
                            System.out.println(placementresult);
                            break;
                        }
                        symbol = 'X';
                    }
                } else{
                    System.err.println("Invalid placement!");
                }
            }
        }
    }

    public static String checkWinner(){
        final List<Byte> topRow = Arrays.asList((byte)1, (byte)2, (byte)3);
        final List<Byte> midRow = Arrays.asList((byte)4, (byte)5, (byte)6);
        final List<Byte> bottomRow = Arrays.asList((byte)7, (byte)8, (byte)9);
        final List<Byte> leftColumn = Arrays.asList((byte)1, (byte)4, (byte)7);
        final List<Byte> midColumn = Arrays.asList((byte)2, (byte)5, (byte)8);
        final List<Byte> rightColumn = Arrays.asList((byte)3, (byte)6, (byte)9);
        final List<Byte> topLeftDiagonal = Arrays.asList((byte)1, (byte)5, (byte)9);
        final List<Byte> topRightDiagonal = Arrays.asList((byte)3, (byte)5, (byte)7);

        final List<List<Byte>> winConditions = new ArrayList<List<Byte>>();
        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(bottomRow);
        winConditions.add(leftColumn);
        winConditions.add(midColumn);
        winConditions.add(rightColumn);
        winConditions.add(topLeftDiagonal);
        winConditions.add(topRightDiagonal);

        for(List<Byte> condition : winConditions){
            if(Xplacements.containsAll(condition)){
                return "Player X won";
            } else if(Oplacements.containsAll(condition)){
                return "Player @ won";
            } else if(Xplacements.size() + Oplacements.size() == 9){
                return "Tie";
            }
        }
        return null;
    }

    public static void putPlacement(char s,ArrayList<Byte> symbolplacements){
        switch(placement){
            case 1:
                cell1 = s;
                symbolplacements.add((byte)1);
                break;
            case 2:
                cell2 = s;
                symbolplacements.add((byte)2);
                break;
            case 3:
                cell3 = s;
                symbolplacements.add((byte)3);
                break;
            case 4:
                cell4 = s;
                symbolplacements.add((byte)4);
                break;
            case 5:
                cell5 = s;
                symbolplacements.add((byte)5);
                break;
            case 6:
                cell6 = s;
                symbolplacements.add((byte)6);
                break;
            case 7:
                cell7 = s;
                symbolplacements.add((byte)7);
                break;
            case 8:
                cell8 = s;
                symbolplacements.add((byte)8);
                break;
            case 9:
                cell9 = s;
                symbolplacements.add((byte)9);
                break;
        }
    }

    public static void toss(){
        Random rand = new Random();
        if(rand.nextBoolean()){
            symbol = 'X';
        } else {
            symbol = '@';
        }
    }

    public static void cpu(){
        System.out.println("Enter your symbol[X] or [@]");
        symbol = sc.next().charAt(0);

        if(symbol == 'X' || symbol == 'x' || symbol == '@'){
            for(int i = 1;;i++) {
                printGameBoard();

                System.out.println("Enter placement");
                placement = sc.nextByte();

                if (Xplacements.contains(placement) || Oplacements.contains(placement)) {
                    System.err.println("Place already occupied!");
                } else {
                    if (placement > 0 && placement <= 9) {
                        if (symbol == 'X' || symbol == 'x') {
                            putPlacement(symbol, Xplacements);
                            String placementresult = checkWinner();
                            if (placementresult != null) {
                                printGameBoard();
                                System.out.println(placementresult);
                                break;
                            }
                        } else if(symbol == '@'){
                            putPlacement(symbol, Oplacements);
                            String placementresult = checkWinner();
                            if (placementresult != null) {
                                printGameBoard();
                                System.out.println(placementresult);
                                break;
                            }
                        }
                        switchSymbol();
                        cpuPlayEasy();
                        switchSymbol();
                    } else {
                        System.err.println("Invalid Placement!");
                    }
                }
            }
        }else
            System.err.println("Invalid Symbol!");
    }

    static void switchSymbol(){
        if (symbol == 'X' || symbol == 'x')
            symbol = '@';
        else
            symbol = 'X';
    }

    static void cpuPlayEasy() {
        placement = (byte) ((Math.random() * 9) + 1);

        for(int i = 1;;i++){
            if (!(Xplacements.contains(placement) || Oplacements.contains(placement))) {
                if (placement > 0 && placement <= 9) {
                    if (symbol == 'x' || symbol == 'X') {
                        putPlacement(symbol, Xplacements);
                        String placementresult = checkWinner();
                        if (placementresult != null) {
                            printGameBoard();
                            System.out.println(placementresult);
                            break;
                        }
                    } else if(symbol == '@'){
                        putPlacement(symbol, Oplacements);
                        String placementresult = checkWinner();
                        if (placementresult != null) {
                            printGameBoard();
                            System.out.println(placementresult);
                            break;
                        }
                    }
                } else {
                    System.err.println("Invalid placement!");
                }
                break;
            } else {
                placement = (byte) ((Math.random() * 9) + 1);
            }
        }
    }
}
