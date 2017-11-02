//Jesus Hernandez
//jherna83
//PA3
//List.java
//same List but with some minor changes to work with the Matrix ADT 

public class List
{
    private class Node
    {
        //fields
        Object data;
        Node next;
        Node prev;

        //constructor
        Node(Object d)
        {
            this.data = d;
            next = null;
            prev = null;
        }

        public String toString()
        {
            return String.valueOf(data);
        }

        public boolean equals(Object x)
        {
            boolean equal = false;

            Node temp;

            if(x instanceof Node)
            {
                temp = (Node)x;
                equal = (data.equals(temp.data));
            }
            return equal;
        }
    }

    private Node head;
    private Node tail;
    private Node cursor;
    private int index;
    private int length;

    //List constructor
    List()
    {
        head = null;
        tail = null;
        cursor = null;
        index = -1;
        length = 0;
   // }
    }

    //returns the length of the Node
    int length()
    {

        return length;
    }

    //returns the current index of cursor
    int index()
    {
        return index;
    }

    //returns the front of the array
    //pre: length() > 0
    Object front()
    {
        if(length() > 0)
        {
            return head.data;
        }
        else
        {
            throw new RuntimeException("Error at front(): " + 
                "the length must be greater than 0");
        }
    }

    //returns the back of the array
    //pre: length() > 0
    Object back()
    {

        if(length() > 0)
        {
            return tail.data;
        }
        else
        {
            throw new RuntimeException("Error at back(): " +
                "the length muxt be greater than 0");
        }
    }

    //returns the current value of cursor
    //pre: length() > 0 and index() >= 0
    Object get()
    {
        if((length() > 0) && (index() >= 0))
        {
            return cursor.data;
        }        
        else
        {
            throw new RuntimeException("Error at get(): the " + 
                "length must be greater than 0 and index must be " +
                "greater than or equal to 0");
        }
    }

    //returns true if this list and L have same int sequence
    //states of cursors are not used to determine equality
    public boolean equals(Object x)
    {
        boolean equal = false;

        List lis;
        Node temp, lisTemp;

        if(x instanceof List)
        {
            lis = (List)x;
            temp = head;
            lisTemp = lis.head;
            equal = (this.length == lis.length);
            while(equal && temp != null)
            {
                equal = temp.equals(lisTemp);
                temp = temp.next;
                lisTemp = lisTemp.next;
            }
        }
        return equal;
    }

    //resets list back to empty state
    void clear()
    {
        head = null;
        tail = null;
        cursor = null;
        length = 0;
        index = -1;
    }

    //move the cursor to the front
    void moveFront()
    {
        if (this.length() != 0)
        {
            cursor = head;
            index = 0;
        }
    }

    //move the cursor to the back
    void moveBack()
    {
        if(this.length() != 0)
        {
            cursor = tail;
            index = this.length - 1;
        }
    }

    //move cursor towards front of list
    //if cursor is at front, it becomes undefined
    //otherwise nothing is done
    void movePrev()
    {
        if(cursor != null && cursor.prev != null)
        {
            cursor = cursor.prev;
            index--;
        }
        else if(cursor == head)
        {
            cursor = null;
            index = -1;
        }
    }

    //move cursor towards front of list
    //if cursor is at front, it becomes undefined
    //otherwise nothing is done
    void moveNext()
    {
        if(cursor != null && cursor.next != null)
        {
            cursor = cursor.next;
            index++;
        }
        else if(cursor == tail)
        {
            cursor = null;
            index = -1;
        }
    }

    //insert new element at front
    void prepend(Object data)
    {
        Node temp = new Node(data);

        if(this.length() == 0)
        {
            head = temp;
            tail = temp;
            length++;
        }
        else
        {
            head.prev = temp;
            temp.next = head;
            head = temp;
            length++;

            if(cursor != null)
            {
                index++;
            }
        }
    }

    //insert new element at back
    void append(Object data)
    {
        Node temp = new Node(data);

        if(this.length == 0)
        {
            tail = temp;
            head = temp;
            length++;
        }
        else
        {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
            length++;
        }
    }

    //inserts new element before cursor
    //pre: length() > 0 and index() >=0
    void insertBefore(Object data)
    {
        Node temp = new Node(data);

        if(this.length() > 0 && this.index() >= 0)
        {
            //if the cursor is at the head
            //call prepend instead
            if(cursor == head)
            {
                prepend(data);
            }
            else
            {
                temp.prev = cursor.prev;
                cursor.prev.next = temp;
                temp.next = cursor;
                cursor.prev = temp;

                index++;    
                length++;
            }
        }
        else
        {
            throw new RuntimeException("Error at insertBefore(): the " +
                "length must be greater than 0 and index must be " +
                "greater than or equal to 0");
        }
    }

    //inserts new element after cursor
    //pre: length() > 0 and index() >= 0
    void insertAfter(Object data)
    {
        Node temp = new Node(data);

        if(this.length() > 0 && this.index() >= 0)
        {
            if(cursor == tail)
            {
                append(data);
            }

            else
            {
                temp.next = cursor.next;
                cursor.next.prev = temp;
                temp.prev = cursor;
                cursor.next = temp;

                length++;
            }
            //temp.prev = cursor;
            //cursor.next = temp;
        }
        else
        {
            throw new RuntimeException("Error at insertAfter(): the " +
                "length must be greater than 0 and index must be " +
                "greater than or equal to 0");
        }
    }

    //deletes the front element
    //pre: length() > 0
    void deleteFront()
    {
        if(this.length == 0)
        {
            throw new RuntimeException("Error at deleteFront(): " +
                "the length must be greater than 0");
        }
        if(this.length > 1)
        {

            head = head.next;
            head.prev = null;

            index--;
            length--;
        }   
        else
        {
            head = null;
            tail = null;
            length--;
        }
    }

    //deletes the back emelent
    //pre: length() > 0
    void deleteBack()
    {
        if(length() == 0)
        {
            throw new RuntimeException("Error at deleteBack(): " +
                "the length must be greater than 0");
        }

        if(length() > 1)
        {
            if(cursor == tail)
            {
                index = -1;
                cursor = null;
            }
            tail = tail.prev;
            tail.next = null;
            length--;
        }
        else
        {
            head = null;
            tail = null;

            length--;
        }
    }

    //deletes the element cursor is pointing to
    //makes cursor undefined
    //pre: length() > 0 and index() >= 0
    void delete()
    {
        if(length() > 0 && index() >= 0)
        {
            if(cursor == head)
            {
                deleteFront();
            }
            else if(cursor == tail)
            {
                deleteBack();
            }
            else
            {
                cursor.next.prev = cursor.prev;
                cursor.prev.next = cursor.next;
                length--;

                index = -1;
                cursor = null;
            }
        }
        else
        {
            throw new RuntimeException("Error at delete(): the " +
                "length must be greater than 0 and index must be " +
                "greater than or equal to 0");
        }
    }

    public String toString()
    {
        StringBuffer buff = new StringBuffer();
        Node temp = head;
        while(temp != null)
        {
            buff.append(" ");
            buff.append(temp.toString());
            temp = temp.next;
        }
        return new String(buff);
    }
}