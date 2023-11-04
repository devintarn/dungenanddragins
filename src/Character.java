/**
 * Author: Devin Tran
 * Date: 2023/10/02
 * Description:
 **/
import java.util.Random;
import java.util.Arrays;
import java.io.RandomAccessFile;
import java.io.IOException;

public class Character {

    private String characterName; //This is the name of the Character
    private String race; //This is the race of the Character
    private String characterClass; //This is the class of the Character
    private int level; //This is the level of the Character
    private int hitPoints; //This is the hit points of the Character
    private int strength; //This is the strength of the Character
    private int constitution; //This is the constitution of the Character
    private int intelligence; //This is the intelligence of the Character
    private int wisdom; //This is the wisdom of the Character
    private int dexterity; //This is the dexterity of the Character
    private int charisma; //This is the charisma of the Character
    private final Random random; //This is the random that will be used to generate random values
    private static final int recLen = 146;//This the maximum bytes that a record has

    public Character() {
        this.random = new Random();
    }

    public Character(String characterName, String race, String characterClass, int level, int hitPoints, int strength,
                     int constitution, int intelligence, int wisdom, int dexterity, int charisma) {
        this.random = new Random();
        this.characterName = characterName;
        this.race = race;
        this.characterClass = characterClass;
        this.level = level;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.dexterity = dexterity;
        this.charisma = charisma;
    }

    public void print() {
        System.out.println("Name: " + characterName + "\tRace: " + race + "\tClass: " + characterClass + "\tLevel: " + level +
                "\tHit Points: " + hitPoints + "\tStrength: " + strength + "\tConstitution: " + constitution +
                "\tIntelligence: " + intelligence + "\tWisdom: " + wisdom + "\tDexterity: " + dexterity + "\tCharisma: " + charisma);
    }

    /** Method Name: readRecord
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void readRecord(RandomAccessFile raf, int recordNums)throws IOException{
        raf.seek(recordNums * recLen);
        characterName = readString(raf);
        race = readString(raf);
        characterClass = readString(raf);
        level = raf.readInt();
        hitPoints = raf.readInt();
        strength = raf.readInt();
        constitution = raf.readInt();
        intelligence = raf.readInt();
        wisdom = raf.readInt();
        dexterity = raf.readInt();
        charisma = raf.readInt();
    }

    /** Method Name: readString
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    private String readString(RandomAccessFile raf)throws IOException{
        StringBuilder temp = new StringBuilder();
        //The longest string is 19
        int stringLen = 19;
        for(int i = 0; i < stringLen; i++) {
            temp.append(raf.readChar());
        }
        return temp.toString().trim();
    }

    /** Method Name: writeRecord
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    private void writeString(RandomAccessFile raf, String string) throws IOException{
        int stringLength = string.length();//This is the number of characters in a string
        int padLength = 0;//This is the number of spaces will be need to maintain the string length
        if(stringLength > 19) {
            stringLength = 19;
        }
        else {
            padLength = 19 - stringLength;
        }
        for(int i = 0; i < string.length(); i++) {
            raf.writeChar(string.charAt(i));
        }
        if(padLength > 0) {
            for(int i = 0; i < padLength; i++) {
                raf.writeChar(' ');
            }
        }
    }

    /** Method Name: writeRecord
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void writeRecord(RandomAccessFile raf, int recordNums) throws IOException{
        raf.seek(recordNums * recLen);
        writeString(raf, characterName);
        writeString(raf, race);
        writeString(raf, characterClass);
        raf.writeInt(level);
        raf.writeInt(hitPoints);
        raf.writeInt(strength);
        raf.writeInt(constitution);
        raf.writeInt(intelligence);
        raf.writeInt(wisdom);
        raf.writeInt(dexterity);
        raf.writeInt(charisma);
    }
    /** Method Name: human
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    private void human() {
        switch(race) {
            case "Human":
                break;
            case "Halfling":
                dexterity -=2;
                constitution -=1;
                race = "Human";
                break;
            case "Elf":
                dexterity -=3;
                constitution +=2;
                race = "Human";
                break;
            case "Dwarf":
                constitution -=3;
                race = "Human";
                break;
            case "Orc":
                strength -=3;
                constitution -=1;
                race = "Human";
                break;
            case "Gnome":
                intelligence -=2;
                wisdom -=1;
                strength +=3;
                race = "Human";
                break;
            default:
                System.out.println("ERORR");
                break;
        }
    }

    /** Method Name: changeRace
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/

    public void changeRace(String raceSelected) {
        human();
        switch(raceSelected) {
            case "Human":
                break;
            case "Halfling":
                dexterity +=2;
                constitution +=1;
                race = "Halfling"; // race select
                break;
            case "Elf":
                dexterity +=3;
                constitution -=2;
                race = "Elf";
                break;
            case "Dwarf":
                constitution +=3;
                race = "Dwarf";
                break;
            case "Orc":
                strength +=3;
                constitution +=1;
                race = "Orc";
                break;
            case "Gnome":
                intelligence +=2;
                wisdom +=1;
                strength -=3;
                race = "Gnome";
                break;
            default:
                System.out.println("ERROR");
                break;
        }

    }

    /** Method Name: changeClass
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    public void changeClass(String newClass) {
        characterClass = newClass;
        hitPoints = newHitPoints(level);
    }

    /** Method Name: newHitPoints
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    private int newHitPoints(int numOfLevels) {
        int newHitPoints = 0;
        for(int i = 0; i < numOfLevels; i++) {
            switch(characterClass) {
                case "Warrior":
                    newHitPoints += diceRoll(10);
                    break;
                case "Cleric":
                    newHitPoints += diceRoll(8);
                    break;
                case "Bard","Ranger", "Rogue":
                    newHitPoints += diceRoll(6);
                    break;
                case "Mage":
                    newHitPoints += diceRoll(4);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
        return newHitPoints;
    }

    /** Method Name: levelUp
     * Author Devin Tran
     * Date 10/10/2023
     * Modified 10/10/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies:
     * Throws/Exceptions: N/A
     **/
    public void levelUp(int numOfLevels) {
        hitPoints += newHitPoints(numOfLevels);
        level += numOfLevels;
    }

    /** Method Name: diceRoll
     * Author Devin Tran
     * Date 10/02/2023
     * Modified 10/02/2023
     * Description This method will roll a random number based on the number of sides the die has
     * Parameters N/A
     * Returns an integer that is the rolled number
     * Dependencies: Random
     * Throws/Exceptions: N/A
     **/
    private int diceRoll(int diceSides) {
        return random.nextInt(diceSides) + 1;
    }

    /** Method Name: randomStat
     * Author Devin Tran
     * Date 10/03/2023
     * Modified 10/03/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies: Random
     * Throws/Exceptions: N/A
     **/

    private int randomStat() {
        int diceSides = 6;
        int numOfRolls = 4;
        int[] diceRolls = new int[numOfRolls];
        int[] smallArray = new int[diceRolls.length-1];
        int arrayCounter = 0;
        int total = 0;

        for(int i = 0; i < diceRolls.length; i++) {
            diceRolls[i] = diceRoll(diceSides);
        }

        Arrays.sort(diceRolls);
        for(int i = 0; i < smallArray.length; i++) {
            smallArray[arrayCounter] = diceRolls[i];
            total += smallArray[arrayCounter];
            arrayCounter++;
        }
        return total;
    }

    /** Method Name: randomStats
     * Author Devin Tran
     * Date 10/04/2023
     * Modified 10/04/2023
     * Description
     * Parameters N/A
     * Returns
     * Dependencies: Random
     * Throws/Exceptions: N/A
     **/

    public void randomStats() {
        hitPoints = randomStat();
        strength = randomStat();
        constitution = randomStat();
        intelligence = randomStat();
        wisdom = randomStat();
        dexterity = randomStat();
        charisma = randomStat();
    }

    public String getCharacterName() {
        return characterName;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getCharacterClass() {
        return characterClass;
    }
    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getHitPoints() {
        return hitPoints;
    }
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getConstitution() {
        return constitution;
    }
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public int getWisdom() {
        return wisdom;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getCharisma() {
        return charisma;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public static int getRecLen() {
        return recLen;
    }
}
