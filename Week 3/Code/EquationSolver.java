import java.util.*;

public class EquationSolver
{
      public static void main(String[] args)
   {
      String equation = "( 10.3 * ( 14 + 3.2 ) ) / ( 5 + 2 - 4 * 3 )";
      System.out.println(equation + " = " + solve(equation));
   }
   
   public static double solve(String equation)
   {
      double answer = evaluatePostfix(parseInfixToPostfix(equation));
      return answer;
   }
   
   private static DSAQueue parseInfixToPostfix(String equation)
   {
      DSAQueue postfix = new DSAQueue();
      DSAStack opStack = new DSAStack();
      String[] infix = equation.split(" ");
      
      char c = 'a';
      double term = 0;
      String result = "";
      
      for(int i = 0; i < infix.length; i++)
      {
         if (infix[i].length() > 1)
			{
				term = Double.parseDouble(infix[i]);
			}
			else
			{
				c = infix[i].charAt(0);
			}
         
         if(c == '(')
         {
            opStack.push('(');
         }
         else if( c == ')')
         {
            while(!opStack.top().equals('('))
            {
               postfix.enqueue(opStack.pop());
            }
            opStack.pop();
         }
         else if(c == '+' || c == '-' || c == '*' || c == '/')
         {
            while(!opStack.isEmpty() && !(opStack.top().equals('(')) && (precedenceOf(opStack.top().toString().charAt(0)) >= precedenceOf(c)))
            {
               postfix.enqueue(opStack.pop());
            }
            opStack.push(c);
         }
         else if (term > 0)
			{
				postfix.enqueue(term);
				term = 0;
			}
         else
         {
            postfix.enqueue(c);
         }
         c = 'a';
      }
      while(!opStack.isEmpty())
      {
         postfix.enqueue(opStack.pop());
      }     
      return postfix;
   }
   
   private static double evaluatePostfix(DSAQueue postfixQueue)
   {
      DSAStack operands = new DSAStack();
      double answer = 0;
      while(!postfixQueue.isEmpty())
      {
         if(postfixQueue.peek() instanceof Double) //if its not an operator add it on to the stack of operators
         {
            operands.push(postfixQueue.dequeue());           
         }
         else
         {
            try
            {
               answer = executeOperation(postfixQueue.dequeue().toString().charAt(0), (double)operands.pop(), (double)operands.pop());
               operands.push(answer);  
            }
            catch(IllegalStateException e)
            {
            
            }     
         }
      }
      return answer;
   }
   
   private static int precedenceOf(char theOp)
   {
      int precedence;
      if(theOp == '+' || theOp == '-')
      {
         precedence = 1;
      }
      else
      {
         precedence = 2;
      }
      return precedence;
   }
   
   private static double executeOperation(char op, double op1, double op2)
   {
      double answer = 0;
		switch (op)
		{
			case '+':
				answer = op1 + op2;
				break;
			case '-':
				answer = op2 - op1;
				break;
			case '*':
				answer = op1 * op2;
				break;
			case '/':
				answer = op2 * op1;
				break;
		}
		return answer;
   }
}