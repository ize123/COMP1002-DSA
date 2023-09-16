import java.util.*;

public class CallStack
{
   public static void main(String[] args)
   {
      DSAStack stack = new DSAStack();
      Scanner sc = new Scanner(System.in);
      
      //Factorial Iterative Test
      System.out.println("Factorial Iterative Test");
      System.out.println("Enter a number: ");
      int num = sc.nextInt();
      System.out.println(num + "! = " + calcNFactorialIterative(num, stack));
      System.out.println("");
      
      //Factorial Recursive Test
      System.out.println("Factorial Recursive Test");
      System.out.println("Enter a number: ");
      int num2 = sc.nextInt();
      System.out.println(num2 + "! = " + calcNFactorialRecursive(num2, stack));
      System.out.println("");
            
      //Fibonacci Test
      System.out.println("Fibonacci Test");
      System.out.println("Enter a number: ");
      int num3 = sc.nextInt();
      System.out.println("The " + num3 + "th number in the fibonacci sequence is: " + fibRecursive(num3, stack));
      System.out.println("");
      
      //GCD Test
      System.out.println("GCD Test");
      System.out.println("Enter a number: ");
      int gcdNum = sc.nextInt();
      System.out.println("Enter another number: ");
      int gcdNum2 = sc.nextInt();
      System.out.println("The greatest common denominator of " + gcdNum + " and " + gcdNum2 + " is: " + gcd(gcdNum, gcdNum2, stack));
      System.out.println("");
      
      //Conversion Test
      System.out.println("Conversion Test");
      System.out.println("Enter a number: ");
      int conNum = sc.nextInt();
      System.out.println("Enter a base to convert to: ");
      int conBase = sc.nextInt();
      System.out.println("Converting: " + conNum + " to base " + conBase + " is: " + convert(conNum, conBase, stack));
      System.out.println("");
   }
   
   //Previous methods altered to push and pop off the stack
   public static long calcNFactorialIterative(int n, DSAStack s)
   {
      long nFactorial = 1;
      s.push("FacIter : n=" + n + " |");
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
      for (int ii = n; ii >= 2; ii--)
      {
         nFactorial *= ii;
      } 
      s.pop();
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
      
      }
      return nFactorial;
   }
   
   private static long calcNFactorialRecursive(int n, DSAStack s)
   {
      s.push("FacRecur : n=" + n + " |");
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
      
      }
      long x;
      if(n == 0)
      {
         x = 1;
      }
      else
      {
         x = n * calcNFactorialRecursive(n-1, s);
      }
      s.pop();
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
      
      }
      return x;
   }
   
   private static int fibRecursive(int n, DSAStack s)
   {
      s.push("FibRecur : n=" + n + " |");
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
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
         fibVal = fibRecursive(n-1, s) + fibRecursive(n-2, s);
      }
      s.pop();
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
      return fibVal;      
   }

   public static int gcd(int n1, int n2, DSAStack s)  // from Programiz © Parewa Labs Pvt. Ltd.
   {
      int x;
      s.push("GCD : n=" + n1 + ", n2=" + n2 + " |");
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
      if(n2 != 0)
      {
         x = gcd(n2, n1 % n2, s);
      }
      else
      {
         x = n1; 
      }
      s.pop();
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
      return x;
   }
   
   public static String convert(int num, int base, DSAStack s) //Using recursion to convert number to other bases ©Rick Mercer
   {
      String str = "";
      s.push("GCD : num=" + num + ", base=" + base + " |");
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
      if(num == 0)
      {
         str = "";
      }
      else str = convert((num / base), base, s) + (num % base);
      s.pop();
      try
      {
         s.display();
      }
      catch(IllegalStateException e)
      {
         
      }
      return str;
   }
}