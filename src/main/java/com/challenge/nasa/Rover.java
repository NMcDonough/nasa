package com.challenge.nasa;

/**
 *
 * @author mcnoa
 */
public class Rover {
    private int plateau[] = new int[2];
    private char orientation;
    private final String DIRECTIONS = "NESW";
    private int location[] = new int[2];
    
    public Rover(){}
    
    //Splits the String input into an array using a space as the delimiter,
    //then converts and assings these values to the location and
    //orientation variables.
    private void parseLocation(String s) {
        String[] parsedString = s.split(" ");
        location[0] = Integer.parseInt(parsedString[0]);
        location[1] = Integer.parseInt(parsedString[1]);
        orientation = parsedString[2].charAt(0);
    }
    
    //Splits the String input into an array using a space as the delimiter,
    //then converts and assings these values to the plateau variable.
    private void parsePlateau(String s) {
        String[] parsedString = s.split(" ");
        plateau[0] = Integer.parseInt(parsedString[0]);
        plateau[1] = Integer.parseInt(parsedString[1]);
    }
    
    //Checks bearing then executes corresponding method.
    private void move(String s) {
        String[] commands = s.split("");
        for(int x=0; x<commands.length; x++){
            switch(commands[x]) {
                case "L":
                    turnLeft(commands[x]);
                    break;
                case "R":
                    turnRight(commands[x]);
                    break;
                case "M":
                    forward();
                    break;
                default:
                    System.out.println("Problem!");
            }
        }
    }
    
    //Method receives commands from NASA and sends them to parsing methods for
    //later execution. Returns updated location or humorous message.
    public String getCommands(String plateau, String location, String movement) {
        parsePlateau(plateau);
        parseLocation(location);
        move(movement);
        
        return report();
    }
    
    private void turnLeft(String c) {
        if(DIRECTIONS.indexOf(orientation) == 0){
            orientation = DIRECTIONS.charAt(DIRECTIONS.length() - 1);
        } else {
            orientation = DIRECTIONS.charAt(DIRECTIONS.indexOf(orientation) - 1);
        }
    }
    
    private void turnRight(String c) {
        if(DIRECTIONS.indexOf(orientation) == DIRECTIONS.length()-1){
            orientation = DIRECTIONS.charAt(0);
        } else {
            orientation = DIRECTIONS.charAt(DIRECTIONS.indexOf(orientation) + 1);
        }
    }
    
    private void forward(){
        switch(orientation){
            case 'N':
                location[1] += 1;
                break;
            case 'E':
                location[0] += 1;
                break;
            case 'S':
                location[1] -= 1;
                break;
            case 'W':
                location[0] -= 1;
                break;
        }
        
        //SOP statement for bug checking.
        //System.out.println("Rover has moved " + orientation + ". New position is " + location[0] + "," + location[1]);
    }
    
    //Returns updated rover location or humorous message.
    private String report(){
        if(location[0] > plateau[0] || location[0] < 0 || location[1] > location[1] || location[1] < 0)
            return "Rover has stopped responding. Last transmission suggests Rover has fallen off the plateau.\nPlease take better care of the rovers, these things are expensive.";
        else
            return location[0] + " " + location[1] + " " + orientation;
    }
}
