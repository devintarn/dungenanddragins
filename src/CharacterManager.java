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
            helper.readUserBinaryFile(fileName);
        }return helper;
    }

    private static void menu(Scanner scan)throws IOException{
        CharacterArray helper = welcome(scan);
        while(true){
            System.out.println("Select desired option:\n1.\tRead Text File\n2.\tRead Bin File\n3.\tWrite Bin File\n4.\tPrint Characters\n5.\tAdd Character\n6.\tDelete Character\n7.\tChange Race\n8.\tChange Class\n9.\tUpdate Level\n10.\tUpdate Character Stat\n11.\tSearch Character\n12.\tExit");
            int option = Integer.parseInt(scan.nextLine());
            switch(option){
                case 1:
                    System.out.println("Enter Name of Text File:");
                    helper.readTextFile(scan.nextLine());
                    System.out.println("File has been read. Binary File \"characters.bin\" has been created");
                    break;
                case 2:
                    System.out.println("Enter Name of Binary File");
                    helper.readUserBinaryFile(scan.nextLine());
                    break;
                case 3:
                    helper.writeBin();
                    break;
                case 4:
                    helper.printCharacters();
                    break;
                case 5:
                    helper.addCharacter(scan);
                    break;
                case 6:
                    helper.deleteCharacter(scan);
                    break;
                case 7:
                    helper.changeRace(scan);
                    break;
                case 8:
                    helper.changeClass(scan);
                    break;
                case 9:
                    helper.levelUp(scan);
                    break;
                case 10:
                    helper.updateStat(scan);
                    break;
                case 11:
                    helper.printCharacter(scan);
                    break;
                case 12:
                    System.exit(0);
                default:
                    System.out.println("INVALID INPUT");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        menu(scan);
    }
}