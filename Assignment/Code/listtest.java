import java.util.*;

public class listtest {
    public static void main(String[] args)
    {
        DSALinkedList list = new DSALinkedList();
        list.insertLast("A");
        list.insertLast("B");
        list.insertLast("C");
        list.insertLast("C");

        Iterator iter = list.iterator();
        while(iter.hasNext())
        {
            System.out.println((String)iter.next());
        }
    }
}
