import java.util.*;

public class TowersOfHanoi
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter number of disks: ");
      int nDisks = sc.nextInt();
      int level = 1;
      towersWrap(nDisks, 1, 3, level);
   }
   
   //Wrapper
   public static void towersWrap(int nDisks, int src, int dest, int level)
   {
      if(nDisks <= 0)
      {
         throw new IllegalArgumentException("Number of disks must be greater than zero");
      }
      else if(src != 1)
      {
         throw new IllegalArgumentException("source tower must be 1");
      }
      else if(dest != 3)
      {
         throw new IllegalArgumentException("destination tower must be 3");
      }
      else
      {
         towers(nDisks, 1, 3, level);
      }
   }
   
   //Method
   public static void towers(int nDisks, int src, int dest, int level)
   {
      String result;
      if(nDisks == 1)
      {
         System.out.println("Recursion Level=" + level);
         System.out.println(moveDisk(nDisks, src, dest));
      }
      else
      {
         int temp = 6 - src - dest;
         towers(nDisks - 1, src, temp,level +1);
         System.out.println("Recursion Level=" + level);
         System.out.println(moveDisk(nDisks, src, dest));
         towers(nDisks - 1, temp, dest, level +1);
      }
   }
   
   public static String moveDisk(int nDisk,int src, int dest)
   {
      return "Moving disk " + nDisk + " from peg: " + src + " to peg: " + dest;
   }
}