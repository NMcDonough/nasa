This challenge completed 7/30 2019 by Noah McDonough.

To run this application:
1.) git clone http://github.com/NMcDonough/nasa.git
2.) cd nasa
3.) mvn clean install
4.) java -jar target/nasa-1.0-SNAPSHOT.jar

To run tests use
java -jar target/nasa-1.0-SNAPSHOT.jar runTests

This application accepts commands in the following format:
x y
x y (N, E, W or S)
(any combination of L, R or M, no spaces)

Line 1 is the length/height of a plateau that the rover explores.
Line 2 is the x/y location of the rover followed by the direction it's
    currently facing.
Line 3 is L for turn Left, R for turn Right, and M for Move forward. This line
    is how the rover knows where to go.
Consecutive repeating lines in the format of line 2 and 3 determine how many
    rovers there are and how many outputs you get.
This application outputs in the same format as line 2.

Example(from my prompt, there's two rovers here):
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM

Output:
1 3 N
5 1 E