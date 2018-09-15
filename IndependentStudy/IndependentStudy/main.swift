/*
 * Name: Ruiling Ma
 * Date: June 3, 2018
 * Version: 1.0
 * Description: Nodes for LinkList and tester
 */
import Foundation
public class Node{
    //private varible data and next node
    private var data: String
    private var next: Node?

    //constructor with the data
    init(data: String) {
        self.data = data
    }
    /**
     * Get the value of the data
     *
     * - returns the data
     */
    public func getData() -> String?{
        return self.data
    }
    /**
     * Get the next node
     *
     * - returns the next node
     */
    public func getNext() -> Node?{
        return self.next
    }
    /**
     * Set next node
     *
     * - param newNode the node to set
     */
    public func setNext(n:Node?){
        self.next = n
    }

}

public class LinkList{
    //private variable head and tail
    private var head: Node? = nil
    private var tail: Node? = nil
    //default constructor
    init() {
    }
    /**
     * Check if the LinkList is empty
     */
    public var isEmpty: Bool {
        return head == nil
    }
    /**
     * Get the data of head
     */
    public var headNode: String? {
        return head?.getData()
    }
    /**
     * Get the data of tail
     */
    public var tailNode: String? {
        return tail?.getData()
    }
    /**
     * Calculate the number of Nodes in the LinkList
     */
    public var size: Int {
        if(self.isEmpty){
            print("LinkList is empty. ")
            return 0
        }else{
            //counter start with head
            var count: Int = 0
            var position: Node? = head
            //while next is not the tail, counter plus 1
            while(position !== tail){
                position = position?.getNext()
                count = count + 1
            }
            //return counter + tail
            return count + 1
        }
        
    }
    /**
     * Make the LinkList empty
     *
     */
    public func makeEmpty(){
        //both head and tail point to null
        self.head = nil
        self.tail = nil
    }
    /**
     * Add a Node to the front
     *
     * - param str the data of the node to be added
     */
    public func addAtFront(str: String){
        //create a new node by the given data
        let newNode = Node(data: str)
        //if the LinkList is empty, point both head and tail to the new node
        if(self.isEmpty){
            self.head = newNode
            self.tail = newNode
        }else{
            //set the nextNode of the new node to the old head
            newNode.setNext(n: head)
            //point the head to the new node
            head = newNode
        }
    }
    /**
     * Add a Node to the end
     *
     * - param str the data of the node to be added
     */
    public func addAtEnd(str: String){
        //create a new node by the given data
        let newNode = Node(data: str)
        //if the LinkList is empty, point both head and tail to the new node
        if(self.isEmpty){
            self.head = newNode
            self.tail = newNode
        }else{
            //set the old tail to point to the new node
            tail?.setNext(n: newNode)
            //set the tail point to the new node
            tail = newNode
        }
    }
    /**
     * Removes the first occurrence of the given string.
     *
     * - param str the data of the node to be removed
     */
    public func remove(str: String){
        //if the required node is in the linklist
        var isSuchNode: Bool = false
        //if the linklist is empty, no node found or deleted
        if(self.isEmpty){
            print("LinkList is empty. ")
        }
        //if the data given is the head, remove head
        else if(head?.getData() == str){
            //there is such node
            isSuchNode = true
            //remove head
            self.removeHead()
        }
        //start to search from head.getNext
        else {
            //the node before target
            var before = head
            //the node to delete
            var target: Node?
            //while next node of front is not null, before is not tail
            while(before !== tail){
                //set target to the next node of before
                target = before?.getNext()
                //if target is not tail and has the data given
                //foung the node
                if(target !== tail && target?.getData() == str){
                    isSuchNode = true
                    //set the node before the target to the next node of target, skip target
                    before?.setNext(n: target?.getNext())
                    //break the loop
                    break
                }
                //if the target is the tail
                else if(target?.getData() == str){
                    //found the node
                    isSuchNode = true
                    //remove tail
                    self.removeTail()
                    //break the loop
                    break
                }
                //if not found the node, set before to the next node
                before = target
            }
        }
        //if did not find the node, print a message
        if(!isSuchNode){
            print("No such element in the LinkList. ")
        }
    }
    /**
     * Remove head from the LinkList
     *
     * - returns the data of head
     */
    public func removeHead() -> String? {
        //str to store the data of old head
        var str:String?
        //if the LinkList is empty
        if(self.isEmpty){
            //no data of head
            str = ""
            //print a message
            print("LinkList is empty. ")
        }
        //if only one node
        else if(self.size == 1){
            //str is the data of the old head
            str = head?.getData()
            //set both head and tail to null
            head = nil
            tail = nil
        }else{
            //str is the data of the old head
            str = head?.getData()
            //set head to the next node
            head = head?.getNext()
        }
        //return the data of the old head
        return str
    }
    /**
     * Remove tail from the LinkList
     *
     * - returns the data of tail
     */
    public func removeTail() -> String? {
        //String to store the data of the old tail
        var str: String?
        //if the Linklist is empty
        if(self.isEmpty){
            //no data of the tail
            str = ""
            print("LinkList is empty. ")
        }
         //if only 1 node in the list
        else if (self.size == 1){
            //store the data of the old tail
            str = tail?.getData()
            //set both head and tail to be null
            head = nil
            tail = nil
        }else{
            //store the data of the old tail
            str = tail?.getData()
            //find the node before tail, start with head
            var beforeTail = head
            //while beforeTail is not tail
            while(beforeTail?.getNext() !== nil){
                //if the beforeTail is before tail
                if(beforeTail?.getNext() === tail){
                     //set tail to the node before the old one
                    tail = beforeTail
                    //break loop
                    break
                }
                //if before tail is not before tail, set it to the next node
                beforeTail = beforeTail?.getNext()
            }
        }
        //return the data of the old tail
        return str
    }
    /**
     * Return the String contains every data of every node in the LinkList
     *
     * - returns the String of each data, each data per line
     */
    public func toString() -> String {
        //the String of the data
        var str:String = ""
        //get the array of data
        for item in self.toArray(){
            //add each item
            str = [str,item]
                .flatMap{$0}
                .joined(separator: "\n")
        }
        //return the string
        return str
    }
    /**
     * Store all data in a String array
     *
     * - returns a String array contains all data
     */
    public func toArray() -> [String?] {
        //start with head
        var n = head
        //new array
        var array: [String?] = []
        //while it is not tail
        while(n !== tail){
            //add the data
            array.append(n?.getData())
            //to next node
            n = n?.getNext()
        }
        //add tail
        array.append(tail?.getData())
        //return the array
        return array
    }

    
}

//tester
print("---------TEST CASE 1------------\n"
    + "-------TEST CASE 1A------\n"
    + "---Empty LinkList----")
//create LinkList
var l = LinkList()
//nothing in the list
//check every method
//print(l.headNode)
assert (l.headNode == nil)
assert (l.tailNode == nil)
assert (l.size == 0)
assert (l.isEmpty)
l.remove(str: "B")
assert (l.removeHead() == "")
assert (l.removeTail() == "")
print("-------TEST 1A PASSED-------")
print("------TEST CASE 1B--------\n"
    + "-----LinkList Size 1-----")
//LinkList with size 1
l.addAtFront(str: "A")
//test every method
assert (l.headNode == "A")
assert (l.tailNode == "A")
assert (l.size == 1)
assert (!l.isEmpty)
l.remove(str:"B")
assert (l.removeHead() == "A")
l.addAtEnd(str:"A")
assert (l.removeTail() == "A")
assert (l.isEmpty)
print("------TEST 1B PASSED------")
print("------TEST CASE 1C--------\n"
    + "---LinkList with 4 - 200 size---")
//random size
var s:Int = Int(arc4random_uniform(100) + 2)
//add each node to the LinkList
var i: Int = 0
while(i < s){
    l.addAtFront(str: String(i))
    l.addAtEnd(str: String(i))
    //test methods
    assert (l.size == (i + 1) * 2)
    assert (!l.isEmpty)
    assert (l.headNode == (String(i)))
    assert (l.tailNode == (String(i)))
    i += 1
}
//size should be double the s
assert (l.size == s * 2)
//test removeHead or removeTail
i = s
while(i > 0){
    assert (l.size == (i) * 2)
    assert (l.removeHead() == (String(i-1)))
    assert (l.removeTail() == (String(i-1)))
    i -= 1
}
//should be empty at the end
assert(l.isEmpty)
//re-assign nodes to the LinkList
i = 1
while(i <= s){
    l.addAtEnd(str: String(i))
    i += 1
}
//test the remove method by removing nodes in the middle
i = 2
while(i < s){
    l.remove(str: String(i))
    i += 1
}
//should be left with a head and a tail
assert(l.toString() == ("\n1" + "\n" + String(s)))
//remove head
l.remove(str: "1")
//remove tail
l.remove(str: String(s))
//should be empty
assert(l.isEmpty)
//re-assign the nodes
i = 1
while(i <= s){
    l.addAtEnd(str: String(i))
    i += 1
}
//test makeEmpty method
l.makeEmpty()
//should be empty
assert(l.isEmpty)
print("------TEST 1C PASSED------")
print("---------TEST 1D---------" +
        "----------Test toArray-------")
//fill LinkList
i = 0
while(i < s){
    l.addAtEnd(str: String(i))
    i += 1
}
//new array
var newArray: [String?] = l.toArray()
i = 0
//check if every item in the array match
for item in newArray {
    assert(item == String(i))
    i+=1
}
print("------------TEST 1D PASSED--------")
print("---------TEST CASE 1 PASSED------------")

var t: DERByte
t = 2
print(t)
