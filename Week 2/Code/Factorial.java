import java.util.*;

public class Factorial
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a number: ");
      int num = sc.nextInt();
      System.out.println(num + "! = " + calcNFactorial(num));
   }
   
   //Wrapper
   public static long calcNFactorial(int n)
   {
      if(n < 0)
      {
         throw new IllegalArgumentException("Number must not be negative");
      }
      else
      {
         return calcNFactorialRecursive(n);
      }
   }
   
   public static long calcNFactorialIterative(int n)
   {
      long nFactorial = 1;
      for (int ii = n; ii >= 2; ii--)
      {
         nFactorial *= ii;
      } 
      return nFactorial;
   }
   
   //Method
   private static long calcNFactorialRecursive(int n)
   {
      if(n == 0)
      {
         return 1;
      }
      else
      {
         return n * calcNFactorialRecursive(n-1);
      }
   }
}