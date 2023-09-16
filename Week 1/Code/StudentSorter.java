import java.util.*;
import java.io.*;

public class StudentSorter
{
   public static void main(String[] args)
   {
      String fileName = "Output.csv";
      
      int[] Arr;
      int[] Arr2;
      int[] Arr3;
      Arr = readFile("RandomNames7000(2).csv");
      Arr2 = readFile("RandomNames7000(2).csv");
      Arr3 = readFile("RandomNames7000(2).csv");
      bubbleSort(Arr);
      selectionSort(Arr2);
      insertionSort(Arr3);
      
      for(int i = 0; i < Arr.length; i++)
      {
         //System.out.println(Arr[i]);
         //System.out.println(Arr2[i]);
         //System.out.println(Arr3[i]);
         writeOneRow(fileName, Arr[i]);
      }
   }
   
   private static int[] readFile(String inFileName)
   {
      FileInputStream fileStrm = null;
      InputStreamReader rdr;
      BufferedReader bufRdr;
      int lineNum;
      String line;
      int[] studentIDArr = new int[7000];
      try
      {
         fileStrm = new FileInputStream(inFileName);
         rdr = new InputStreamReader(fileStrm);
         bufRdr = new BufferedReader(rdr);
         
         lineNum = 0;
         line = bufRdr.readLine();
         while(line != null)
         {
            studentIDArr[lineNum] = processLine(line);
            lineNum++;
            //processLine(line);
            line = bufRdr.readLine();
         }
         fileStrm.close();       
      }
      catch (IOException e)
      {
         if(fileStrm != null)
         {
            try {fileStrm.close(); } catch (IOException ex2) { }
         }
         System.out.println("Error in file processing: " + e.getMessage());
      }
      return studentIDArr;
   }
   
   private static int processLine (String csvRow)
   {
      int studentID;
      
      String[] tokens = csvRow.split(",");
      
      try 
      {
         studentID = Integer.parseInt(tokens[0]);
         return studentID;    
      }
      catch (Exception e)
      {
         throw new IllegalStateException("CSV row had invalid format");
      }
   }
   
   private static void writeOneRow(String filename, int id)
   {
      FileOutputStream fileStrm = null;
      PrintWriter pw;
      
      try
      {
         fileStrm = new FileOutputStream(filename, true);
         pw = new PrintWriter(fileStrm);
         
         pw.println(id);
         pw.close();
      }
      catch (IOException e)
      {
         if(fileStrm != null)
         {
            try {fileStrm.close(); } catch (IOException ex2) { }
         }
         System.out.println("Error in writing to file: " + e.getMessage());
      }
   }
   
   // bubble sort
    public static void bubbleSort(int[] A)
    {
      int pass = 0;
      boolean sorted;
      do
      {
         sorted = true;
         for(int ii = 0; ii <= (A.length-1 - pass) -1; ii++)
         {
            if(A[ii] > A[ii+1])
            {
               int temp = A[ii];
               A[ii] = A[ii+1];
               A[ii+1] = temp;
               sorted = false;
            }
         }
      pass = pass + 1;
      }
      while(sorted == false);
    }//bubbleSort()

    // selection sort
    public static void selectionSort(int[] A)
    {
      for(int nn = 0; nn <= A.length-1; nn++)
      {
         int minIdx = nn;
         for(int jj = nn+1; jj <= A.length-1; jj++)
         {
            if(A[jj] < A[minIdx])
            {
               minIdx = jj;
            }
         }
         int temp = A[minIdx];
         A[minIdx] = A[nn];
         A[nn] = temp;
      }
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
      for(int nn = 1; nn <= A.length-1; nn++)
      {
         int ii = nn;
         while((ii > 0) && (A[ii-1] > A[ii]))
         {
            int temp = A[ii];
            A[ii] = A[ii-1];
            A[ii-1] = temp;
            
            ii = ii - 1;
         }
      }
    }// insertionSort()

}