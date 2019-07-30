package com.challenge.nasa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author mcnoa
 */
public class RoverCommunicator extends Frame{
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 800;
    private TextArea console;
    private TextArea commands;
    private final int plateau[] = new int[2];
    private Rover[] rovers;
    private final Button submit;
    
    public RoverCommunicator(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.addWindowListener(new WindowAdapter(){  
            @Override
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }
        });
        
        console = new TextArea(20, 100);
        console.setEditable(false);
        this.add(console);
        
        commands = new TextArea(5, 100);
        commands.setEditable(false);
        this.add(commands);
        
        submit = new Button("Submit Instructions");
        submit.addActionListener((ActionEvent arg0) -> {
            String[] parsedCommands = parseInstructions(commands.getText());
            if(validateCommands(parsedCommands)){
                executeCommands(parsedCommands);
            } else {
                console.append("Invalid Command(s)\n");
            }
        });
        submit.setEnabled(false);
        this.add(submit);
        
        this.setVisible(true);
        
        initConnection();
    }
    
    //This method just provides some immersion based on the prompt.
    //Displays a message with a delay that mimics waiting on a response to
    //a request, then unlocks the gui for user input. Gotta have some fun :)
    private void initConnection() {
        String line = "Initializing off-world connection...\n";
        console.append(line);
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(4);
        } catch(Exception e) {
            console.append("Exception caught:\n" + e.toString());
        }
        console.append("Control link established. All rovers accounted for and awaiting instruction.\n");
        //Unlocks interface to user
        commands.setEditable(true);
        submit.setEnabled(true);
    }
    
    //Splits raw string data into an array for use in other methods
    private String[] parseInstructions(String commands) {
        String lines[] = commands.split("\\r?\\n");
        return lines;
    }
    
    //This method holds method calls that execute the commands if they
    //pass validation.
    private void executeCommands(String[] commands) {
        createRovers(commands);
        sendCommands(commands[0], Arrays.copyOfRange(commands, 1, commands.length));
    }
    
    //Initializes the rovers array and fills it with blank rovers.
    private void createRovers(String[] commands) {
        //The length of commands minus one divided by two tells us how many rovers there are
        rovers = new Rover[(commands.length - 1) / 2];
        for(int x = 0; x < rovers.length; x++) {
            rovers[x] = new Rover();
        }
    }
    
    //Sends the parsed commands and the raw plateau string to the rovers.
    private void sendCommands(String plateau, String[] commands) {
        //Iterates through the list to send commands individually.
        for(int x=0; x<rovers.length; x+=1) {
            console.append(
                    rovers[x].getCommands(plateau, commands[x*2], commands[x*2 +1])
                    + "\n"
            );
        }
    }
    
    //Basic command validation. This is not comprehensive, but you can't have
    //a user interface without at least a some validation.
    public boolean validateCommands(String[] commands){
        //A properly entered command sequence will always have an odd number length
        if(commands.length % 2 != 1)
            return false;
        else {
            //Checks for any letters in the first command
            Pattern pat = Pattern.compile("[^0-9|\\s]");
            Matcher mat = pat.matcher(commands[0]);
            if(mat.find() || commands[0].length() < 3){
                return false;
            }
        }
        for(int x=1; x<commands.length; x++) {
            if(x%2 == 1) {
                //Every odd line should end with a letter that corresponds to a direction
                char temp = commands[x].charAt(commands[x].length() - 1);
                switch(temp) {
                    case 'N':
                        break;
                    case 'E':
                        break;
                    case 'S':
                        break;
                    case 'W':
                        break;
                    default:
                        return false;
                }
            } else {
                //Regex check fails for anything other than L, M or R
                Pattern pat = Pattern.compile("[^LMR]");
                Matcher mat = pat.matcher(commands[x]);
                if(mat.find()){
                    return false;
                }
            }
        }
        return true;
    }
}
