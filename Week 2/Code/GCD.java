import java.util.*;

public class GCD
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a number: ");
      int num = sc.nextInt();
      System.out.println("Enter another number: ");
      int num2 = sc.nextInt();
      System.out.println("The greatest common denominator of " + num + " and " + num2 + " is: " + calcGCD(num, num2));
   }
   
   //Wrapper
   public static int calcGCD(int n1, int n2)
   {
      if(n1 == 0 || n2 == 0)
      {
         throw new IllegalArgumentException("Numbers must not be zero");
      }
      else if(n1 < 0 || n2 < 0)
      {
         int x = Math.abs(n1);
         int y = Math.abs(n2);
         return gcd(x,y);
      }
      else
      {
         return gcd(n1, n2);
      }
   }
   
   //Method
   public static int gcd(int n1, int n2)  // from Programiz © Parewa Labs Pvt. Ltd.
   {
      if(n2 != 0)
      {
         return gcd(n2, n1 % n2);
      }
      else
      {
         return n1; 
      }
   }
}