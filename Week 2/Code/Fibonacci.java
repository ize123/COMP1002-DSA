import java.util.*;

public class Fibonacci
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a number: ");
      int num = sc.nextInt();
      System.out.println("The " + num + "th number in the fibonacci sequence is: " + calcFibonacci(num));
   }
   
   //Wrapper
   public static int calcFibonacci(int n)
   {
      if(n < 0)
      {
         throw new IllegalArgumentException("Number must not be negative");
      }
      else
      {
         return fibRecursive(n);
      }
   }
   
   public static int fibIterative(int n)
   {
      int fibVal = 0;
      int currVal = 1;
      int lastVal = 0;
      
      if(n == 0)
      {
         fibVal = 0;
      }
      else if(n == 1)
      {
         fibVal = 1;
      }
      else
      {
         for(int ii = 1; ii < n; ii++)
         {
            fibVal = currVal + lastVal;
            lastVal = currVal;
            currVal = fibVal;
         }
      }
      return fibVal;
   }
   
   //Method
   private static int fibRecursive(int n)
   {
      int fibVal = 0; 
      
      if(n == 0)
      {
         fibVal = 0;
      }
      else if(n == 1)
      {
         fibVal = 1;
      }
      else
      {
         fibVal = fibRecursive(n-1) + fibRecursive(n-2);
      }
      return fibVal;
   }
}