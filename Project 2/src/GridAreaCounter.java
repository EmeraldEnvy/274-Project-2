/**
 * Alexander Pham
 * October 11, 2019
 * Checks a grid for area's of o and displays each area with the amount of o's
 */

import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class GridAreaCounter {
    public static void main( String args[] ) {
        while ( true ) {
            System.out.print( "Enter grid # : " );
            //User Input
            int userNumber = CheckInput.getInt();
            while ( userNumber < 1 || userNumber > 4 ) {
                System.out.println( "Grid number not found " );
                System.out.print( "Enter grid # : " );
                userNumber = CheckInput.getInt();
            }
            //Create file name
            String file = "grid" + userNumber + ".txt";
            //Initialize grid
            Character[][] gridArray = new Character[10][10];
            //Populate grid
            gridArray = readFile(gridArray, file);
            displayArray(gridArray);
            System.out.println();
            //Analyzes the grid
            int areaNumber = 0;
            for ( int i = 0; i < gridArray.length ; i++ ){
                int count;
                count = traverseGrid(gridArray);
                areaNumber += 1;
                //Stops looking for area when the no area can be found
                if ( count == 0 ){
                    break;
                }
                System.out.println( "Area " + areaNumber + " = " + count );
            }
            //Aesthetic purposes
            System.out.println( "---------------------------------" );
        }
    }

    /**
     * Read from a file and creates an array
     * @param gridArray
     * @param file
     * @return the new populated array
     */
    public static Character[][] readFile( Character[][] gridArray , String file ) {
        try {
            Scanner read = new Scanner( new File(file) );
            do {
                for ( int i = 0 ; i < 10 ; i++ ) {
                    //Makes the first and last row into a row of #'s
                    if ( i <= 0 || i >= 9 ) {
                        for ( int k = 0 ; k < 10 ; k++ ) {
                            gridArray[i][k] = '#';
                        }
                    } else {
                        //Populate the beginning and end of every row with #'s
                        gridArray[i][9] = '#';
                        gridArray[i][0] = '#';
                        String line = read.next();
                        //Populate every space in array with characters from file
                        for ( int j = 1 ; j < 9 ; j++ ) {
                            char character = line.charAt( j - 1 );
                            gridArray[i][j] = character;
                        }
                    }
                }
            } while (read.hasNext());
        } catch ( FileNotFoundException fnf ) {
            System.out.println( "File was not found" );
        }
        return gridArray;
    }

    /**
     * Display the grid
     * @param gridArray
     */
    public static void displayArray( Character[][] gridArray ) {
        for ( int i = 1 ; i < 9 ; i++ ) {
            System.out.print( " " );
            for ( int j = 1 ; j < 9 ; j++ ) {
                System.out.print( gridArray[i][j] );
            }
            System.out.println();
        }
    }

    /**
     * Moves around the grid and calls recursive count when an 'o' is found
     * @param gridArray
     * @return the area
     */
    public static int traverseGrid( Character[][] gridArray ) {
        int score;
        int count = 0;
        for ( int i = 0 ; i < 9 ; i++ ) {
            for ( int j = 0 ; j < 9 ; j++ ) {
                if (gridArray[i][j].equals( 'o' ) ) {
                    score = recursiveCount( gridArray , i , j , count );
                    return score;
                }
            }
        }
        return 0;
    }

    /**
     * Counts the area while turning counted o's into x's
     * @param gridArray
     * @param x index
     * @param y index
     * @param count
     * @return the area
     */
    public static int recursiveCount( Character[][] gridArray, int x, int y , int count ) {
        //left right up down
        if ( gridArray[x][y].equals( 'o' ) ) {
            count += 1;
            //Marks the o
            gridArray[x][y] = 'x';
            //left
            if ( gridArray[x-1][y].equals( 'o' ) ) {
                count = recursiveCount( gridArray, x-1 , y , count );
            }
            //right
            if ( gridArray[x+1][y].equals( 'o' ) ) {
                count = recursiveCount( gridArray, x+1 , y , count );
            }
            //up
            if ( gridArray[x][y-1].equals( 'o' ) ) {
                count = recursiveCount( gridArray, x , y-1 , count );
            }
            //down
            if ( gridArray[x][y+1].equals( 'o' ) ) {
                count = recursiveCount( gridArray, x , y+1 , count );
            }
            else{
                count = 0 + count;
            }
        }
        return count;
    }




}