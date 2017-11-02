import java.io.*;
import java.util.Scanner;

public class Lex
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc1 = null; //scanner to count number of lines
        Scanner sc2 = null; //scanner to read in number of lines
        PrintWriter out = null; //what will print out to file

        if(args.length < 2)
        {
            System.err.println("Usage: java Lex [inputFile] [outputFile]");
            System.exit(1);
        }

        //count # of lines
        int count = 0; 
        sc1 = new Scanner(new File(args[0]));   
        while(sc1.hasNextLine())
        {
            count++;
            sc1.nextLine();
        }
        sc1.close();

        
        String[] lineArray = new String[count]; //new array to hold lines

        //insert the lines into array
        int index = 0;
        String line;
        sc2 = new Scanner(new File(args[0]));
        while(sc2.hasNextLine())
        {
            line = sc2.nextLine();
            lineArray[index] = line;
            index++;
        }
        sc2.close();

        out = new PrintWriter(new FileWriter(args[1]));//will be writing to args[1]
        List newList = new List(); //creates new list using 
                                  //list constructor from List.java
        sortList(newList, lineArray);

        //move cursor to front of list and print 
        //out the elements in the array relative to
        //the order of indices in the linked list.
        newList.moveFront();
        while(newList.index() >= 0) //once it hits null index is -1
        {
            out.println(lineArray[newList.get()]);
            newList.moveNext();
        }
    }

    //Separate function for the sorting. It takes in
    //as parameters the List, the array of strings
    //function is heavily comented, tried explaining it 
    //as detailed as possible
    static void sortList(List L, String[] arr)
    {
        //insert first index of array into list
        L.prepend(0);

        //loops through the enire array
        //sorting everything in lexicographical order
        for(int i = 1; i < arr.length; i++)
        {
            //this will set cursor to head; eachtime a new pass through the
            //loop is performed, the cursor is set to the front. This makes 
            //the index 0 and allows it to enter the while loop from the begining 
            //to compare all the indices in the list to the rest in the array
            L.moveFront();

            //this is what inserts stuff into the list
            while(L.index() >= 0)
            {    
                //this if statement compares both values lexicographically
                //if the value being passed as a parameter continues to 
                //be to the right of the current element being compared then
                //moveNext function is called until the compareTo fails
                //and is returned  as greater than 0. At this point it breaks 
                //out of the while and proceeds to do the proper insertion.
                if(arr[L.get()].compareTo(arr[i]) < 0) 
                {
                    L.moveNext();
                }
                else
                {
                    break;
                }
            }
            //When element belongs within the list. moveNext() was called until
            // the comparison fails to satisfy the if statement thus 
            //hopping into this loop. insertBefore() also calls prepend()
            //within itslef if cursor is head. 
            if(L.index() != -1)
            {
                L.insertBefore(i);
            }
            //moveNext() was called to the point where cursor was set
            //to null thus setting the index -1 and skipping the if above
            else
            {
                L.append(i);
            }
        }
    }
}