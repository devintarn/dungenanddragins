import java.io.BufferedReader;
import java.io.RandomAccessFile;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CharacterArray {
    private Character[] characters;
    private String binFile = "characters.bin";

    public CharacterArray(int size) {
        characters = new Character[size];

    }

    /** Method Name: printCharacter
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will delete a selected Character and replace it with bytes
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void printCharacter(Scanner scan) {
        int characterIndex = findCharacter(scan);
        characters[characterIndex].print();
    }

    /** Method Name: updateBinaryStat
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will delete a selected Character and replace it with bytes
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    private void updateBinaryStat(int statByte, int characterIndex, int updatedStat)throws IOException{
        RandomAccessFile randomAccess = new RandomAccessFile(binFile, "rw");
        randomAccess.seek(characterIndex * Character.getRecLen() + statByte);
        randomAccess.write(updatedStat);
        randomAccess.close();
    }

    /** Method Name: statPoints
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will delete a selected Character and replace it with bytes
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    private int statPoints(Scanner scan) {
        int statPoints;
        System.out.println("Enter the new number of stat points:");
        return statPoints = Integer.parseInt(scan.nextLine());
    }

    /** Method Name: updateStat
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will update the selected individual stat
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void updateStat(Scanner scan)throws IOException{
        int characterIndex = findCharacter(scan);//The index of the character to be updated
        int stat;//This is a number that represents the stat to be updated
        int statByte;//This is the byte location of the stat to be updated
        int statPoints;//This is the new stat point that will be updated
        if(characterIndex != -1) {
            System.out.println("\nSelect the Stat you would like to update by entering its corresponding number:\n1) Strength\n2) Constitution\n3) Intelligence\n4) Wisdom\n5) Dexterity\n6) Charisma");
            stat = Integer.parseInt(scan.nextLine());
            switch(stat) {
                case 1:
                    statByte = 122;
                    statPoints = statPoints(scan);
                    characters[characterIndex].setStrength(statPoints);
                    updateBinaryStat(statByte, characterIndex, statPoints);
                    break;
                case 2:
                    statByte = 126;
                    statPoints = statPoints(scan);
                    characters[characterIndex].setConstitution(statPoints);
                    updateBinaryStat(statByte, characterIndex, statPoints);
                    break;
                case 3:
                    statByte = 130;
                    statPoints = statPoints(scan);
                    characters[characterIndex].setIntelligence(statPoints);
                    updateBinaryStat(statByte, characterIndex, statPoints);
                    break;
                case 4:
                    statByte = 134;
                    statPoints = statPoints(scan);
                    characters[characterIndex].setWisdom(statPoints);
                    updateBinaryStat(statByte, characterIndex, statPoints);
                    break;
                case 5:
                    statByte = 138;
                    statPoints = statPoints(scan);
                    characters[characterIndex].setDexterity(statPoints);
                    updateBinaryStat(statByte, characterIndex, statPoints);
                    break;
                case 6:
                    statByte = 142;
                    statPoints = statPoints(scan);
                    characters[characterIndex].setCharisma(statPoints);
                    updateBinaryStat(statByte, characterIndex, statPoints);
                    break;
                default:
                    System.out.println("\n**INVALID ENTRY**");
                    break;
            }
        }
    }

    /** Method Name: levelUp
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will delete a selected Character and replace it with bytes
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void levelUp(Scanner scan){
        int characterIndex = findCharacter(scan);
        if(characterIndex != -1) {
            System.out.println("Enter the Number of Levels:");
            characters[characterIndex].levelUp(Integer.parseInt(scan.nextLine()));
        }
    }

    /** Method Name: changeClass
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description Prompts the user for a new class and will adjust the hit points
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    public void changeClass(Scanner scan){
        int characterIndex = findCharacter(scan);
        if(characterIndex != -1) {
            System.out.println("Enter the New Class:\n(Warrior, Cleric, Bard/Ranger/Rogue, Mage");
            characters[characterIndex].changeClass(scan.nextLine());
        }
    }

    /** Method Name: changeRace
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will prompt the user for a new race and will adjust the stats
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void changeRace(Scanner scan){
        int characterIndex = findCharacter(scan);
        if(characterIndex != -1) {
            System.out.println("Enter the New Race:\n(Human, Halfling, Elf, Dwarf, Orc, Gnome)");
            characters[characterIndex].changeRace(scan.nextLine());
        }
    }

    /** Method Name: findCharacter
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will find the index number of a selected character in the array
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    private int findCharacter(Scanner scan) {
        System.out.println("Enter the Character's Name:");
        String name = scan.nextLine();//The name of the character
        for(int i = 0; i < characters.length; i++) {
            if(characters[i].getCharacterName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        System.out.println("The Character " + name + " was not found.");
        return -1;
    }

    /** Method Name: deleteCharacter
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will delete a selected Character and replace it with bytes
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void deleteCharacter(Scanner scan)throws IOException{
        int characterIndex = findCharacter(scan); //The index of the character in the array
        byte[] emptyData = new byte[Character.getRecLen()]; //Creating an empty record that will replace the deleted character
        int emptyByte = characterIndex * Character.getRecLen();//The byte location of the file to be deleted
        if(characterIndex != -1) {
            RandomAccessFile randomAccess = new RandomAccessFile(binFile, "rw");
            randomAccess.seek(emptyByte);
            randomAccess.write(emptyData);
            randomAccess.close();
        }
    }

    /** Method Name: writeACharacter
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description This will write a character to the binary file
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void writeACharacter(int charIndex)throws IOException{
        RandomAccessFile randomAccess = new RandomAccessFile(binFile, "rw");
        randomAccess.seek(charIndex * Character.getRecLen());
        characters[charIndex].writeRecord(randomAccess, charIndex);
    }

    /** Method Name: addCharacter
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description Adds new character to end of binary file while also creating new Character array
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void addCharacter(Scanner scan)throws IOException{
        int characterIndex = characters.length;
        Character[] addedCharacters = new Character[characters.length +1];
        System.arraycopy(characters, 0, addedCharacters, 0, characters.length);
        addedCharacters[characterIndex] = new Character();
        System.out.println("Enter the New Character's Name:");
        addedCharacters[characterIndex].setCharacterName(scan.nextLine());
        System.out.println("Enter the New Character's Race: ");
        addedCharacters[characterIndex].setRace(scan.nextLine());
        System.out.println("Enter the New Character's Class: ");
        addedCharacters[characterIndex].setCharacterClass(scan.nextLine());
        addedCharacters[characterIndex].setLevel(1);
        addedCharacters[characterIndex].randomStats();
        characters = addedCharacters;
        writeACharacter(characterIndex);
        characters[characterIndex].print();

    }

    /** Method Name: printCharacters
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description Prints all Characters
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void printCharacters() {
        for(int i = 0; i < characters.length; i++) {
            characters[i].print();
        }
    }



    /** Method Name: writeBin
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description Writing the Character array to a binary file
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void writeBin(){
        try {
            RandomAccessFile raf = new RandomAccessFile(binFile, "rw");
            raf.setLength(Character.getRecLen()*characters.length);
            for(int i = 0; i < characters.length; i++) {
                characters[i].writeRecord(raf,i);
            }
            raf.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void readUserBinaryFile(String fileName){
        binFile = fileName;
        readBinaryFile();
    }

    public void readBinaryFile(){
        try{
            RandomAccessFile raf = new RandomAccessFile(binFile,"rw");
            for(int i = 1; i < characters.length; i++){
                characters[i] = new Character();
                characters[i].readRecord(raf,i);
            }raf.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** Method Name: readTextFile
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description Reads text file to then write to a character array and binary file
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void readTextFile(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int i = 0;//The array index number
            while(reader.ready()) {
                characters[i] = new Character();
                characters[i].setCharacterName(reader.readLine());
                characters[i].setRace(reader.readLine());
                characters[i].setCharacterClass(reader.readLine());
                characters[i].setLevel(Integer.parseInt(reader.readLine()));
                characters[i].setHitPoints(Integer.parseInt(reader.readLine()));
                characters[i].setStrength(Integer.parseInt(reader.readLine()));
                characters[i].setConstitution(Integer.parseInt(reader.readLine()));
                characters[i].setIntelligence(Integer.parseInt(reader.readLine()));
                characters[i].setWisdom(Integer.parseInt(reader.readLine()));
                characters[i].setDexterity(Integer.parseInt(reader.readLine()));
                characters[i].setCharisma(Integer.parseInt(reader.readLine()));
                i++;
            }
            reader.close();
            writeBin();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
