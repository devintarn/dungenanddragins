import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class CharacterManager {

    /** Method Name: binNumOfRecs
     * Author Devin Tran
     * Date 10/29/2023
     * Modified 10/29/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    private static int binNumOfRecs(String fileName){
        try{
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw"); //Used to access binary file
            long fileSize = raf.length();
            raf.close();
            return (int) fileSize/ Character.getRecLen(); // dividing the byte size of record by the set length of a character record
        }catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    /** Method Name: textNumOfRecs
     * Author Devin Tran
     * Date 10/29/2023
     * Modified 10/29/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    private static int textNumOfRecs(String fileName){
        int recLines = 11;// This is the number of lines that a record contains in a text file
        int fileLines = 0;//Counter to count how many lines are in the text file
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while(reader.ready()){
                reader.readLine();
                fileLines++;
            }reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }return fileLines / recLines;
    }

    private static CharacterArray welcome(Scanner scan){
        int choice = 0;//This represents that users choice of file to read
        CharacterArray helper;
        System.out.println("Welcome to the Character Manager");
        while(choice != 1 && choice !=2){
            System.out.println("Select desired file type\n1.\tText\n2.\tBinary");
            choice = Integer.parseInt(scan.nextLine());
            if(choice != 1 && choice != 2) {
                System.out.println("\nINVALID INPUT");
            }
        } System.out.println("Enter the name of the file");
        String fileName = scan.nextLine();
        if (choice == 1) {
            helper = new CharacterArray(textNumOfRecs(fileName));
            helper.readTextFile(fileName);
        }
        else{
            helper = new CharacterArray(binNumOfRecs(fileName));

        }
    }

    public static void main(String[] args) {

    }
}