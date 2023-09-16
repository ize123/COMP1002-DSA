import java.util.*;

public class Conversion
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a number: ");
      int num = sc.nextInt();
      System.out.println("Enter a base to convert to: ");
      int base = sc.nextInt();
      System.out.println("Converting: " + num + " to base " + base + " is: " + convert(num, base));
   }
   
   //Wrapper
   public static String baseConversion(int num, int base)
   {
      if(base == 0)
      {
         throw new IllegalArgumentException("Base cannot be zero");
      }
      else if(base < 0) 
      {
         throw new IllegalArgumentException("Base cannot be negative");
      }
      else
      {
         return convert(num, base);
      }
   }
   
   //Method
   public static String convert(int num, int base) //Using recursion to convert number to other bases ©Rick Mercer
   {
      if(num == 0)
      {
         return "";
      }
      else return convert((num / base), base) + (num % base);
   }
}