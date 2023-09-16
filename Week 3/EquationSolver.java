import java.util.*;

public class EquationSolver
{
	public double solve(String equation)
	{
		double answer = evaluatePostfix(parseInfixToPostfix(equation));
		return answer;
	}
	
	private DSAQueue parseInfixToPostfix(String equation)
	{
		DSAQueue postfix = new DSAShufflingQueue();
		DSAStack opStack = new DSAStack();
		String[] infix = equation.split("\\s+");
		char term = 'a';
		double term2 = 0;
		int i = 0;
		double x;
				
		while (i < infix.length)
		{
			if (infix[i].length() > 1)
			{
				term2 = Double.parseDouble(infix[i]);
			}
			else
			{
				term = infix[i].charAt(0);
			}
			i++;

			if (term == ('('))
			{
				opStack.push('(');
			}
			else if (term == ')')
			{
				while (!(opStack.top().equals('(')))
				{
					postfix.enqueue(opStack.pop());
				}
				opStack.pop();
			}
			else if (term == '+' || term == '-' || term == '*' || term == '/')
			{
				while (!(opStack.isEmpty()) && (!(opStack.top().equals('('))) && precedenceOf(opStack.top().toString().charAt(0)) >= precedenceOf(term))
				{
					postfix.enqueue(opStack.pop());
				}
				opStack.push(term);
			}
			else if (term2 > 0)
			{
				postfix.enqueue(term2);
				term2 = 0;
			}
			else
			{
				//fix this method
				x = ((double)term - 48);
				postfix.enqueue(x);
			}
			term = 'a'; //reset term
		}
	
		while (!(opStack.isEmpty()))
		{
			postfix.enqueue(opStack.pop());
		}
		System.out.println(Arrays.toString(postfix.queue));
		return postfix;
	}
	
	private double evaluatePostfix(DSAQueue postfixQueue)
	{
		DSAStack solveStack = new DSAStack();
		DSAStack operandStack = new DSAStack();
		double ans = 0;
		while (postfixQueue.getCount() != 0)
		{
			if (postfixQueue.peek() instanceof Double)
			{
				operandStack.push(postfixQueue.dequeue());
			}
			else
			{
				ans = executeOperation(postfixQueue.dequeue().toString().charAt(0), (double)operandStack.pop(), (double)operandStack.pop());
				operandStack.push(ans);
			}
		}
		return ans;
	}
	
	private int precedenceOf(char theOp)
	{
		int precedence;
		
		if (theOp == '*' || theOp == '/')
		{
			precedence = 2;
		}
		else
		{
			precedence = 1;
		}
		return precedence;
	}
	
	private double executeOperation(char op, double op1, double op2)
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