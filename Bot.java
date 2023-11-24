import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bot {
    private int xAxis;
    private int yAxis;
    private String currentDirection;
    public Bot(){
        this.xAxis = 0;
        this.yAxis = 0;
    }

    public void moveLocation(char direction,int number){
        switch (direction){
            case 'l':
                this.xAxis -= number;
                this.currentDirection = "west";
                break;

            case 'r':
                this.xAxis += number;
                this.currentDirection = "east";
                break;

            case 'u':
                this.yAxis += number;
                this.currentDirection = "north";
                break;

            case 'd':
                this.yAxis -= number;
                this.currentDirection = "south";
                break;
        }

        currentLocation();
    }
    public void currentLocation(){
        System.out.println(this.currentDirection+" ("+this.xAxis+","+this.yAxis+")");
    }

    public Map<String, Object> checkValidation(String word){
        Map<String, Object> dataMap = new HashMap<>();//empty key value pair Map i.e like Associative Array in php
        char firstChar = word.charAt(0);
        if (firstChar != 'r' && firstChar != 'l' && firstChar != 'u' && firstChar != 'd' && firstChar != 'm') {
            dataMap.put("response", "Please enter L,R,U,D or M to decide where to move");
            dataMap.put("status", 0);
            return dataMap;
        }
        if (!word.contains("m")) {
            dataMap.put("response", "Please include M/m to move");
            dataMap.put("status", 0);
            return dataMap;
        }
        String numberStr = word.replaceAll("[^0-9]", "");
        if (numberStr.isEmpty()) {
            dataMap.put("response", "The word does not contain any numbers.");
            dataMap.put("status", 0);
            return dataMap;
        }
        dataMap.put("status", 1);
        return dataMap;
    }
    public static void main(String[] args) {
        Boolean status = true;
        int numbers;
        Scanner input= new Scanner(System.in);
        Bot bot = new Bot();
        while(status){
            System.out.println("Enter word to move(hint: type stop to end): ");
            String word = input.nextLine().toLowerCase();
            try {
                String[] words = word.split("\\s+");
                for (String str : words) {
                    if(str.contains("stop")){
                        status = false;
                        break;
                    }
                    Map<String, Object> response = bot.checkValidation(str);
                    if ((int) response.get("status") == 0) {
                        System.out.println(response.get("response"));
                        continue;
                    }
                    String numberStr = str.replaceAll("[^0-9]", "");
                    numbers = Integer.parseInt(numberStr);
                    char firstChar = str.charAt(0);
                    bot.moveLocation(firstChar, numbers);
                }
            }catch(Exception e){
                System.out.println("Exception occured: " + e.getMessage());
            }
        }

    }

}