/*
 * Name: Ruiling Ma
 * Date: May. 03, 2018
 * Version: 1.0
 * Description: Student class for Student objects
 */
package edu.hdsb.gwss.ruiling.ics4u.unit5;


/**
 *
 * @author maruiling
 */
public class Student {

    //private variables
    private String firstName;
    private String lastName;
    private int studentNumber;
    private int birthDay;

    //default constructor
    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.studentNumber = -1;
        this.birthDay = -1;
    }

    //custemized consturctor
    public Student(String first, String last, int number, int birth) {
        if(number < 0){
           // System.out.println("Student number can not be negative. ");
            throw new RuntimeException("Student number can not be negative. ");            
        }
        this.firstName = first;
        this.lastName = last;
        this.studentNumber = number;
        this.birthDay = birth;
    }

    //getters and setters below
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    

    /**
     * Get the hash code for the object
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.studentNumber;
        return hash;
    }
    
    /**
     * Compare two students
     *
     * @param obj the student to be compared
     * @return true if they are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //if the memory adress is the same, they are the same
        if (this == obj) {
            return true;
        }
        //if the obj is null, not the same
        if (obj == null) {
            return false;
        }
        //if not from the same class, not the same
        if (getClass() != obj.getClass()) {
            return false;
        }
        //cast the obj to student
        final Student other = (Student) obj;
        //if both student have a default studentNumber
        if(this.studentNumber == -1 && other.studentNumber == -1){
            //compare secondary key, if any of them are default, they are not the same (not completed constructed)
            if(this.firstName.equalsIgnoreCase("")||this.lastName.equals("")||
                    this.birthDay == -1 || other.firstName.equals("")||other.lastName.equals("")||
                    other.birthDay == -1){
                return false;
            }else{
                //else compare secondary key
                return (this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.birthDay == other.birthDay);
            }
        //if any of the student number is default and the other is not, they are not the same
        }else if(this.studentNumber == -1 || other.studentNumber == -1){
            return false;
        //if both student have consturcted student number, compare the number   
        }else{
            return this.studentNumber == other.studentNumber;
        }
        
    }
}


