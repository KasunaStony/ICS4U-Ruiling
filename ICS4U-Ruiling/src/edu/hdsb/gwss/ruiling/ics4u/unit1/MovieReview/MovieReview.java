/*
 * Name: Ruiling Ma
 * Date: Feb 15, 2018
 * Version: 2.0
 * Description: Search through a file containing movie 
 *              reviews from the Rotten Tomatoes website and determine 
 *              which words are positive and which are negative.
 */
package edu.hdsb.gwss.ruiling.ics4u.unit1.MovieReview;

/**
 * UNIT 1 ASSIGNMENT - MOVIE RERIEW
 *
 * @author Ruiling Ma
 * @version 2018-02
 */
import java.io.*;
import java.util.*;

public class MovieReview {

    /**
     * This method will count the number of reviews that contain the key word.
     *
     * @param word the key word the review must contain.
     * @param reviews the file that contains the movie reviews.
     * @return the number of reviews that contain the key work at least once.
     */
    public static int wordCount(String word, File reviews) throws Exception {
        //BufferedReader to read the reviews
        BufferedReader reader = new BufferedReader(new FileReader(reviews));
        //A StringTokenizer to sepreat words
        StringTokenizer st;
        //String of each review
        String newLine;
        //Counter for number of words
        int count = 0;
        //If the review list has more line, keep reading
        while (((newLine = reader.readLine()) != null)) {
            //Tokenize a review
            st = new StringTokenizer(newLine);
            //If the review has more words, keep reading
            while (st.hasMoreTokens()) {
                //If find a same word in one review, counter plus 1
                if (st.nextToken().equalsIgnoreCase(word)) {
                    count++;
                    //In case the number of the same word in one review is more than one 
                    break;
                }
            }
        }
        //return the number of words
        return count;
    }

    /**
     * This method will accumulate the the movie review scores that contain the
     * key word.
     *
     * @param word the key word the review must contain.
     * @param reviews the file that contains the movie reviews.
     * @return the sum of the scores for reviews that contain the key work at
     * least once.
     */
    public static int wordTotalScore(String word, File reviews) throws Exception {
        //BufferedReader to read the reviews
        BufferedReader reader = new BufferedReader(new FileReader(reviews));
        //A StringTokenizer to sepreat words
        StringTokenizer st;
        //String of each review
        String newLine;
        //the total score of the words
        int totalScore = 0;
        //the score for each review
        int reviewScore = 0;
        //If the review list has more line, keep reading
        while ((newLine = reader.readLine()) != null) {
            //Tokenize a review
            st = new StringTokenizer(newLine);
            //read the score of the review
            reviewScore = Integer.parseInt(st.nextToken());
            //If the review has more words, keep reading
            while (st.hasMoreTokens()) {
                //If the word marches, add the score of the review to the total score
                if (word.equalsIgnoreCase(st.nextToken())) {
                    totalScore += reviewScore;
                    //In case the number of the same word in one review is more than one 
                    break;
                }
            }
        }
        //return the total score of the word
        return totalScore;
    }

    /**
     * Average score of reviews containing that word, given the specified file.
     *
     * @param word the key word the review must contain.
     * @param reviews reviews the file that contains the movie reviews.
     * @return the average score for the key word. Word Total Score / Word Count
     */
    public static double wordAverage(String word, File reviews) throws Exception {
        //if there is no such word, return -1
        if (wordCount(word, reviews) == 0) {
            return -1;
        } else {
            //return the average score of the word
            return (double) wordTotalScore(word, reviews) / wordCount(word, reviews);
        }
    }

    /**
     * This method returns the average movie review score of the words in the
     * file, given th specified movie review file.
     *
     * @param wordList the list of words to be determined.
     * @param reviews reviews the file that contains the movie reviews.
     * @return the average score of the sentence.
     */
    public static double sentenceAverage(File wordList, File reviews) throws Exception {
        //BufferedReader to read the word list
        BufferedReader reader = new BufferedReader(new FileReader(wordList));
        //String for each words
        String words;
        //the number of words
        int wordsNumber = 0;
        //the total score of the sentence
        double totalScore = 0;
        //if there is more words, keep reading
        while ((words = reader.readLine()) != null) {
            //if the number of words in the review file is not 0
            if (wordCount(words, reviews) != 0) {
                //add the score of the word in the score of the sentence
                totalScore += wordAverage(words, reviews);
                //number of words + 1
                wordsNumber++;
            }
        }
        //return the score of the sentence
        return totalScore / wordsNumber;
    }

    /**
     * This method returns the most positive word in the sentence.
     *
     * @param wordList the list of words to be determined.
     * @param reviews reviews the file that contains the movie reviews.
     * @return the most positive word.
     */
    public static String mostPositiveWord(File wordList, File reviews) throws FileNotFoundException, IOException, Exception {
        //BufferedReader to read the word list
        BufferedReader reader = new BufferedReader(new FileReader(wordList));
        //String for each word
        String words;
        //The most positive word
        String mostPositive = "";
        //the highest score
        double mostPositiveScore = -1;
        //if there is more word, keep reading
        while ((words = reader.readLine()) != null) {
            //if the number of words in the list is not 0 and the score of the word is bigger than the current score, switch
            if (wordCount(words, reviews) != 0 && wordAverage(words, reviews) > mostPositiveScore) {
                mostPositive = words;                
                mostPositiveScore = wordAverage(words, reviews);
            }
        }
        //return the most positive word
        return mostPositive;
    }

    /**
     * This method returns the most negative word in the sentence.
     *
     * @param wordList the list of words to be determined.
     * @param reviews reviews the file that contains the movie reviews.
     * @return the most negative word.
     */
    public static String mostNegativeWord(File wordList, File reviews) throws FileNotFoundException, IOException, Exception {
        //BufferedReader to read the word list
        BufferedReader reader = new BufferedReader(new FileReader(wordList));
        //String for each word
        String words;
        //The most positive word
        String mostNegative = "";
        //if there is more word, keep reading
        double mostNegativeScore = 10;
        //if there is more word, keep reading
        while ((words = reader.readLine()) != null) {
            //if the number of words in the list is not 0 and the score of the word is smaller than the current score, switch
            if (wordCount(words, reviews) != 0 && wordAverage(words, reviews) < mostNegativeScore) {
                mostNegative = words;
                mostNegativeScore = wordAverage(words, reviews);
            }
        }
        //return the most negative word
        return mostNegative;
    }

    /**
     * Sort the words in a sentence in to positive.txt and negative.txt.
     *
     * @param wordList the list of words to be determined.
     * @param reviews reviews the file that contains the movie reviews.
     */
    public static void wordSort(File wordList, File reviews) throws FileNotFoundException, IOException, Exception {
        //BufferedReader to read the word list
        BufferedReader reader = new BufferedReader(new FileReader(wordList));
        //writer for the positive file and negative file
        BufferedWriter writerPositive = new BufferedWriter(new FileWriter(new File("./TestData/Positive.txt")));
        BufferedWriter writerNegative = new BufferedWriter(new FileWriter(new File("./TestData/Negative.txt")));
        //String for each words
        String words;
        //if the there is more word, keep reading
        while ((words = reader.readLine()) != null) {
            //if there is no such word in the list, skip
            if (wordCount(words, reviews) == 0); 
            else if (wordAverage(words, reviews) > 2.1) {
                //if the score is bigger than 2.1, put in the positive list
                writerPositive.write(words + "\n");
            } else if (wordAverage(words, reviews) < 1.9) {
                //if the score is smaller than 1.9, put in the negative list
                writerNegative.write(words + "\n");
            }
        }
        //close
        reader.close();
        writerPositive.close();
        writerNegative.close();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {

        //new scanner to read user input
        Scanner input = new Scanner(System.in);
        //movie review data 
        File review = new File("./TestData/MovieReviews.txt");
        //user choice
        int choice;
        //the name of the word or file inputed by user
        String wordOrFileName;
        //the file user want to be determined
        File userFile;
        //positive and negative word
        String positive, negative;
        //infinite loop 
        for (;;) {
            //ask the user what they want to do
            System.out.println("What would you like to do?\n"
                    + "1: Get the score of a word.\n"
                    + "2: Get the average score of words in a file.\n"
                    + "3: Find the highest/lowest scoring words in a file.\n"
                    + "4: Sort words from a file into positive.txt and negative.txt.\n"
                    + "5: Exit.\n");
            System.out.print("Enter number 1-5: ");
            //get the choice of the user
            choice = input.nextInt();
            System.out.println();
            //exit the program if the user choose to
            if (choice == 5) {
                System.out.println("Thanks for using");
                System.exit(0);
            } else if (choice == 1) {
                //ask the user to enter the word they want to be determined
                System.out.print("Enter a word: ");
                //get the word
                wordOrFileName = input.next();
                //print the result of the number of appearases and the score by invoking the two methods
                System.out.println("\n" + wordOrFileName + " appears " + wordCount(wordOrFileName, review) + " times.\n"
                        + "The average score for the reviews containing " + wordOrFileName + " is " + wordAverage(wordOrFileName, review) + ".");
                System.out.println("\n");
            } else if (choice == 2) {
                //ask the user to enter the file name
                System.out.print("Enter the name of the file with words you want to find the average score for: ");
                //get the name of the file
                wordOrFileName = input.next();
                //new a file
                userFile = new File("./TestData/" + wordOrFileName);
                System.out.println();
                //print the first part of the message by invoking sentenceAverage
                System.out.println("The average score of words in " + wordOrFileName + " is " + sentenceAverage(userFile, review) + "\n");
                //determine the overall emotion of the sentence
                if (sentenceAverage(userFile, review) > 2.01) {
                    System.out.println("The overall sentiment is positive");
                } else if (sentenceAverage(userFile, review) < 1.99) {
                    System.out.println("The overall sentiment is negative");
                } else {
                    System.out.println("The overall sentiment is neutral");
                }

            } else if (choice == 3) {
                //ask the user to enter the name of the file
                System.out.print("Enter the name of the file with words you want to score: ");
                //get the name of the file
                wordOrFileName = input.next();
                //new a file
                userFile = new File("./TestData/" + wordOrFileName);
                System.out.println();
                //get the most positive and most negative word
                positive = mostPositiveWord(userFile, review);
                negative = mostNegativeWord(userFile, review);
                //print the message
                System.out.println("The most positive word, with a score of " + wordAverage(positive, review) + " is " + positive + ".\n"
                        + "The most negative word, with a score of " + wordAverage(negative, review) + " is " + negative + ".\n");

            } else if (choice == 4) {
                //ask the user to enter the file name
                System.out.print("Enter the file name with words you want to sort: ");
                //get the name of the file
                wordOrFileName = input.next();
                //new a file
                userFile = new File("./TestData/" + wordOrFileName);
                System.out.println();
                //sort the words into two lists
                wordSort(userFile, review);
            } else {
                //default
                System.out.println("Please enter a number between 1-5");
            }
        }
    }
}
