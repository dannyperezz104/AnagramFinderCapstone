/**@author Daniel Oswaldo Perez (uni: dop2107)
 * @version 1.0.0
 * December 16th, 2023
 * Description: Anagram finder is a program that finds
 * anagrams for words using hashmap, bst, and avl data structures.
 * It reads from a text file and organizes anagrams based on the words and desired data structure.
 * After the anagram map is created it allows for the efficient searching of anagrams.
 */

import java.io.*;
import java.util.Iterator;

public class AnagramFinder {
    private static BufferedReader reader;
    private static MyMap<String, MyList<String>> map;

    /**
     * The main method of the AnagramFinder program.
     * It takes user's arguments and validates them before creating an anagram map.
     * It then sorts the users word, which will function as the key when retrieving anagrams from the map.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args){

        //checks to make sure user input includes a word, text file, and appropriate data structure
        checkArgValidity(args);

        //creates the map where the anagrams will be stored
        createAnagramMap(args);
        //sorting user's word and finding anagrams for it
        String sortedUserWord = insertionSort(args[0].toLowerCase());
        MyLinkedList<String> anagramList = (MyLinkedList<String>)map.get(sortedUserWord);

        if(anagramList != null) {
            //Source for using iterators: https://www.tutorialspoint.com/java/java_using_iterator.html
            Iterator<String> iterator = anagramList.iterator();
            while (iterator.hasNext()) {
                String anagram = iterator.next();
                if (!(anagram.equals(args[0]))) {
                    System.out.println(anagram);
                }
            }
        } else{
            System.out.println("No anagrams found.");
        }
    }

    /**
     * Checks the validity of the command-line arguments.
     *
     * This method ensures that the correct number of arguments is provided and validates the file
     * extension and data structure type. If the arguments are invalid, the method prints an error
     * message to the standard error stream and terminates the program.
     *
     * @param args An array containing the command-line arguments.
     *             It should include the word, dictionary file, and data structure type.
     */
    static void checkArgValidity(String[] args){
        if(args.length != 3){
            System.err.println("Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>");
            System.exit(1);
        }

        //Source for exception handling: https://www.youtube.com/watch?v=1XAfapkBQjk&t=635s
        try{
            //checking to see if the 2nd argument is a valid .txt file

            //Source for reading from file: https://www.baeldung.com/reading-file-in-java
            reader = new BufferedReader(new FileReader(args[1]));
        } catch (IOException e) {
            System.err.println("Error: Cannot open file '" + args[1] + "' for input.");
            System.exit(1);
        }

        if( !((args[2].equals("bst")) || (args[2].equals("avl")) || (args[2].equals("hash")))){
            System.err.println("Error: Invalid data structure '" + args[2] + "' received.");
            System.exit(1);
        }
    }

    /**
     * Creates a map containing anagrams based on the specified data structure.
     *
     * The method initializes the map based on the user's input for the data structure (hash, bst, or avl).
     * It then populates the map with words from the provided dictionary file, organizing them by sorted form.
     * Once the data structure type is determined, it calls assembleMap() to build the map.
     *
     *
     * @param args An array containing user inputs, including the word, dictionary file, and data structure type.
     */
    static void createAnagramMap(String[] args){
        //assigns the appropriate type of map: hash/bst/avl depending on user's input
        if(args[2].equals("hash")){
            map = new MyHashMap<>();
            assembleMap();
        }
        else if(args[2].equals("bst")){
            map = new BSTMap<>();
            assembleMap();
        }

        else if(args[2].equals("avl")){
            map = new AVLTreeMap<>();
            assembleMap();
        }
    }

    /**
     * Assembles anagram map by reading words from the provided file and organizing them
     * based on their sorted forms.
     *
     * The method reads words from the file, sorts each word, and adds them to the corresponding
     * linked list in the anagram map. If a sorted form already exists in the map, the word is
     * added to the existing linked list.
     *
     * @throws IOException if error occurs while reading from the file.
     */
    static void assembleMap(){
        String word;
        String sortedWord;
        MyLinkedList<String> LL;
        try {
            while ((word = reader.readLine()) != null) {
                sortedWord = insertionSort(word);
                LL = (MyLinkedList<String>) map.get(sortedWord);
                if(LL!= null){
                    //adding word to the pre-existing Linked List
                    LL.add(word);
                } else{
                    //Instantiating the new linked list with the word from text file
                    MyLinkedList<String> newLL = new MyLinkedList<>();
                    newLL.add(word);
                    map.put(sortedWord, newLL);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sorts the characters of a word using ASCII values.
     * @param word input word to be sorted.
     * @return sorted word.
     */
    static String insertionSort(String word){
        String wordLow = word.toLowerCase();
        char[] wordLowCharArr = wordLow.toCharArray();

        //Uses modified version of Dr. Borowski's insertion sort algorithm
        for (int i = 1, i_bound = wordLowCharArr.length; i < i_bound; ++i){
            int k, current = wordLowCharArr[i];
            for(k = i-1; k >= 0 && ((int)wordLowCharArr[k] > current); --k){
                wordLowCharArr[k + 1] = wordLowCharArr[k];
            }
            wordLowCharArr[k + 1] = (char) current;
        }
        String sortedWord = new String(wordLowCharArr);
        return sortedWord;
    }
}
