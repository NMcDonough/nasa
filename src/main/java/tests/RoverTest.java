package tests;

import com.challenge.nasa.Rover;
import com.challenge.nasa.RoverCommunicator;
/**
 *
 * @author mcnoa
 */
public class RoverTest {
    String expectedResults[] = new String[3];
    String actualResults[] = new String[3];
    
    public RoverTest(){
        expectedResults[0] = "1 3 N";
        expectedResults[1] = "5 1 E";
        expectedResults[2] = "Rover has stopped responding. Last transmission suggests Rover has fallen off the plateau.\n" +
            "Please take better care of the rovers, these things are expensive.";
        
        getResults();
        runTests();
    }
    
    private void runTests() {
        System.out.println("=========TESTING=========\n");
        for(int x=0; x<actualResults.length; x++) {
            outputResults(actualResults[x].equals(expectedResults[x]), x);
        }
        System.out.println("=========================");
    }
    
    private void getResults() {
        Rover rover = new Rover();
        actualResults[0] = rover.getCommands("5 5", "1 2 N", "LMLMLMLMM");
        actualResults[1] = rover.getCommands("5 5", "3 3 E", "MMRMMRMRRM");
        actualResults[2] = rover.getCommands("5 5", "3 3 E", "MMRMMRMRRMMMMMMMMMMMMMMMMM");
    }
    
    private void outputResults(boolean b, int i) {
        if(b){
            System.out.println("Test " + (i+1) + " was successful. Expected and received '" + actualResults[i] + "'.\n");
        } else {
            System.out.println("Test " + (i+1) + " failed. Expected '" + expectedResults[i] + "'.");
            System.out.println("Received '" + actualResults[i] + "'.\n");
        }
    }
}
