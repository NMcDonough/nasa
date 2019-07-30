package com.challenge.nasa;

import com.challenge.nasa.RoverCommunicator;
import tests.RoverTest;
/**
 *
 * @author mcnoa
 */
public class Nasa {
    public static void main(String[] args) {
        //Initalizes the interface for the rovers
        if(args.length < 1) {
                RoverCommunicator comm = new RoverCommunicator();
        } else {
            if(args[0].equals("runTests")){
                RoverTest test = new RoverTest();
            }
        }
    }
}
