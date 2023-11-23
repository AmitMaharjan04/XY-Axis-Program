import java.util.Scanner;

public class ProgramBot {
    private int xAxis;
    private int yAxis;
    private char prevDirection=' ';
    public ProgramBot(){
        this.xAxis = 0;
        this.yAxis = 0;
    }
    public void origin(int number,char direction) {
        if(direction == 'r'){
            this.xAxis += number;
            this.yAxis = 0;
        }else{
            this.xAxis -= number;
            this.yAxis = 0;
        }
        this.prevDirection = direction;
        currentLocation();
    }
    public void moveSideWay(int number){//only move right and left not up and down
        if(this.prevDirection == 'r'){
            this.xAxis += number;
        }else{
            this.xAxis -= number;
        }
        this.currentLocation();
    }
    public void changePosition(int number,char direction){
        if(this.xAxis >= 0 && this.yAxis >= 0){
            if(direction == 'r'){
                this.yAxis -= number;
            }else{
                this.xAxis -= number;
            }
        }else if(this.xAxis >=0 && this.yAxis < 0){
            if(direction == 'r'){
                this.xAxis -= number;
            }else{
                this.yAxis += number;
            }
        }else if(this.xAxis < 0 && this.yAxis < 0){
            if(direction == 'r'){
                this.yAxis += number;
            }else{
                this.xAxis += number;
            }
        }else{
            if(direction == 'r'){
                this.xAxis += number;
            }else{
                this.yAxis -= number;
            }
        }
        this.prevDirection = direction;
        currentLocation();
    }
    public void currentLocation(){
        System.out.println("("+this.xAxis+","+this.yAxis+")");
    }

    public static void main(String[] args) {
        Boolean status = true;
        Scanner input= new Scanner(System.in);
        ProgramBot bot = new ProgramBot();
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
            int numbers = Integer.parseInt(word.replaceAll("[^0-9]", ""));

            if(firstChar == 'm' && bot.xAxis== 0 && bot.yAxis == 0){
                System.out.println("Please select the side to move 1st");
                break;
            }else if (firstChar == 'm' && bot.xAxis!=0 || bot.yAxis !=0){
                bot.moveSideWay(numbers);
            }else{
                if(bot.xAxis == 0 && bot.yAxis == 0){
                    bot.origin(numbers,firstChar);
                }else{
                    bot.changePosition(numbers,firstChar);
                }
            }
        }

    }

}