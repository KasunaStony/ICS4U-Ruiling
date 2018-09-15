/*
 * Name: Ruiling Ma
 * Date: May. 03, 2018
 * Version: 1.0
 * Description: HashTable class for HashTable objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;

/**
 *
 * @author maruiling
 */
public class HashTable implements HashTableInterface {

    //array of students with a initial capacity of 47
    private Student data[] = new Student[53];

    //default constructor
    public HashTable() {
    }

    /**
     * Get the number of the Student object in the HashTable.
     *
     * @return number of the Student object in the HashTable.
     */
    @Override
    public int size() {
        //counter initial to 0
        int size = 0;
        //iterate the array and find non-null objects
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                size++;
            }
        }
        //return the size
        return size;
    }

    /**
     * Get the capacity of the HashTable
     *
     * @return the capacity of the HashTable
     */
    @Override
    public int capacity() {
        //the capapcity is the length of the array
        return data.length;
    }

    @Override
    public double loadFactor() {
        return ((double) this.size()) / this.capacity();
    }

    /**
     * Empty the HashTable.
     */
    @Override
    public void makeEmpty() {
        //re-create a array to empty the old one
        data = new Student[data.length];
    }

    /**
     * Check the HashTalbe is empty or not
     *
     * @return true if the HashTalbe is empty, false if it is not
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Rehash the array when the load factor is ABOVE 75%, and have the load
     * factor drop down to 25%.
     */
    @Override
    public void rehash() {
        //find new capacity
        int newCapacity = nextPrime(data.length * 3);
        //copy the old data
        Student[] oldTable = data;
        //create the new data array
        data = new Student[newCapacity];
        //go though the old table to copy each object to the new talbe
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                this.put(oldTable[i].hashCode(), oldTable[i]);
            }
        }
    }

    /**
     * Get the student with the given key
     *
     * @param key the key of the student
     * @return the student with the same key
     */
    @Override
    public Student get(int key) {
        //the student wanted
        Student s = null;
        //starting position of the aray
        int position = this.hash(key);
        //keep finding if the next slot is not null
        while (data[position] != null) {
            //if find the student with the given key
            if (data[position].hashCode() == key) {
                //assins the current object to s
                s = data[position];
                //break loop
                break;
            }
            //next position
            position++;
            //if reach the last object, change position to 0
            if (position == this.capacity()) {
                position = 0;
            }
        }
        //return the found student
        return s;
    }

    /**
     * Put the student value in the HashTable
     *
     * @param key the key of the student
     * @param value the given student to be put
     */
    @Override
    public void put(int key, Student value) {
        //if the hash table does not contain the student
        if (!this.contains(value)) {
            //starting position
            int position = this.hash(key);
            //the counter for collision
            int collisions = 0;
            //while the data point is not null
            while (data[position] != null) {
                //one more collision
                collisions++;
                //next student
                position++;
                //if reach the last student, change to 0
                if (position == this.capacity()) {
                    position = 0;
                }
            }
            //assign the null space to value
            data[position] = value;
            //print the message for collision and load factor
            System.out.println("The number of collisions is " + collisions + " and the load factor is " + this.loadFactor());
            //if the load factor is bigger than 0.75, rehash the table
            if (this.loadFactor() >= 0.75) {
                this.rehash();
            }
        } else //if the hashtable contains the same student, print a message
        {
            System.out.println("Duplicate item.");
        }
    }

    /**
     * Check if the hash table contains the student given
     *
     * @param value the student to be find
     * @return true if the table contains the student, false otherwise
     */
    @Override
    public boolean contains(Student value) {
        //the boolean for contains or not
        boolean contains = false;
        //starting position
        int position = this.hash(value.hashCode());
        //while the slot is not null, keeping comparing
        while (data[position] != null) {
            //if the two objects are the same, the table contains the object, break loop
            if (data[position].equals(value)) {
                contains = true;
                break;
            }
            //next slot
            position++;
            //if reach the last object, change it to 0
            if (position == this.capacity()) {
                position = 0;
            }
        }
        //return the result
        return contains;
    }

    /**
     * Check if the hash table contains the key
     *
     * @param key the key to find
     * @return true if the table contains key, false otherwise
     */
    @Override
    public boolean containsKey(int key) {
        //if the get method return a non-null object, then the table contains the key
        return (this.get(key) != null);
    }

    /**
     * The hash method returns the starting position to get or put a certain
     * object
     *
     * @param key the certain number to be hashed
     * @return the hashed number
     */
    @Override
    public int hash(int key) {
        return key % this.capacity();
    }

    /**
     * Return the next prime
     *
     * @param n the number to start looking
     * @return the next prime after n
     *
     * COPY RIGHT BY mcmediumdrink from
     * https://gist.github.com/mcmediumdrink/18024dc38bbe02afd29dd48f7849dc53
     */
    public static int nextPrime(int n) {
        boolean isPrime = false;

        int start = 2;

        while (!isPrime) {
            n += 1;
            int m = (int) Math.ceil(Math.sqrt(n));

            isPrime = true;
            for (int i = start; i <= m; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return n;
    }

    /**
     * Transfer the hash table to a readable output
     *
     * @return the string of the hash table
     */
    @Override
    public String toString() {
        //start with a empty string
        String s = "";
        //go through the array 
        for (int i = 0; i < data.length; i++) {
            //if the slot is not null
            if (data[i] != null) {
                //if not null
                s += "Slot: " + i + " after hash: " + this.hash(data[i].hashCode()) + " hashCode: " + data[i].hashCode() + "\n";
            } else {
                //if null
                s += i + " null " + "\n";
            }
        }
        //return the string
        return s;
    }
}
