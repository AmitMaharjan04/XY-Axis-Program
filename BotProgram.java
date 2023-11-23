import java.util.Scanner;

public class BotProgram {
    private int xAxis;
    private int yAxis;
    private String prevDirection;
    public BotProgram(){
        this.xAxis = 0;
        this.yAxis = 0;
        this.prevDirection = "north";
    }

    public void moveLocation(char direction,int number){
        switch (this.prevDirection){
            case "north":
                if(direction == 'r'){
                    this.xAxis += number;
                    this.prevDirection = "east";
                }else{
                    this.xAxis -= number;
                    this.prevDirection = "west";
                }
                break;

            case "east":
                if(direction == 'r'){
                    this.yAxis -= number;
                    this.prevDirection = "south";
                }else{
                    this.yAxis += number;
                    this.prevDirection = "north";
                }
                break;

            case "south":
                if(direction == 'r'){
                    this.xAxis -= number;
                    this.prevDirection = "west";
                }else{
                    this.xAxis += number;
                    this.prevDirection = "east";
                }
                break;

            case "west":
                if(direction == 'r'){
                    this.yAxis += number;
                    this.prevDirection = "north";
                }else{
                    this.yAxis -= number;
                    this.prevDirection = "south";
                }
                break;
        }
        currentLocation();
    }
    public void currentLocation(){
        System.out.println("("+this.xAxis+","+this.yAxis+")");
    }

    public static void main(String[] args) {
        Boolean status = true;
        int numbers;
        Scanner input= new Scanner(System.in);
        BotProgram bot = new BotProgram();
        while(status){
            System.out.println("Enter word to move(hint: type stop to end): ");
            String word = input.nextLine().toLowerCase();
            if(word.contains("stop")){
                status = false;
                break;
            }
            char firstChar = word.charAt(0);
            if(firstChar != 'r' && firstChar != 'l' && firstChar != 'm'){
                System.out.println("Please enter L,R or M to decide where to move");
                continue;
            }
            if(!word.contains("m")){
                System.out.println("Please include M/m to move");
                continue;
            }
            String numberStr = word.replaceAll("[^0-9]", "");
            if(numberStr.isEmpty()){
                numbers = Integer.parseInt(numberStr);
            }else{
                System.out.println("The word does not contain any numbers.");
                continue;
            }
            bot.moveLocation(firstChar,numbers);

        }

    }

}