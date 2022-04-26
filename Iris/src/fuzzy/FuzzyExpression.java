/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

/**
 *
 * @author aina
 */
import java.util.*;

/**
 * Contains single fuzzy expression in the form "LV is hedge, hedge, ... MF"
 */
public class FuzzyExpression 
{
	//Linguistic variable
	private LinguisticVariable LV = null;

	//Member function
	private String MF = null;

	//Vecot of hedges - the sequence of appearance is equivalent to Stack.pop() operations
	private Vector hedges = null;


	//Text representation of this expression
	private java.lang.String textExpression;

	//Public flags for structuring a rule - have no accessors
	public boolean flagAND = false;
	public boolean flagOR = false;

	//Nesting counters - have no accessors
	public int nestingUp = 0;
	public int nestingDown = 0;
/**
 * Evaluate this fuzzy expression, compute and store result.
 * @return Result of evaluation
 * @exception engine.RulesParsingException This exception is thrown
 * if an error occurs during evaluation
 */
public double evaluateExpression() throws EvaluationException 
{
	double result = 0.0;
	
	//Perform evaluation
	if(LV!=null && MF!=null)
		result = LV.is(MF);
	else 
		throw new EvaluationException(" - Missing linguistic variable or membership function: ");

	if(result == -1)
 		 throw new EvaluationException(" - Unable to perform fuzzy evaluation: ");

	//Apply hedges if needed
	for (Enumeration en = hedges.elements() ; en.hasMoreElements() ;) 
		{
	    	result = ((Hedge)en.nextElement()).hedgeIt(result);
	 	}

	return result;
}
/**
 * Execute this fuzzy expression with the given value and label.
 * @param value Double result of the last fired expression.
 * @param label String label of this rule.
 * @exception engine.RulesParsingException This exception is thrown
 * if an error occurs during evaluation.
 */
public double  executeExpression(double value, String label) throws EvaluationException 
{
	double temp = value;

	//Apply hedges if needed
	for (Enumeration en = hedges.elements() ; en.hasMoreElements() ;) 
		{
	    	temp = ((Hedge)en.nextElement()).hedgeIt(temp);
	 	}

	//Perform evaluation
	if(LV!=null && MF!=null)
		{
		if(temp>0.0)
			{
			try	{
				LV.set(label, MF, temp);
				}
			catch(Exception e)
				{throw new EvaluationException(e.getMessage());}
			}
		}
	else
		{
		throw new EvaluationException(" - Missing linguistic variable or membership function: ");
		}

	return temp;

}
/**
 * Return text representation of this expression.
 * @return java.lang.String
 */
public java.lang.String getTextExpression() 
{
	return textExpression;
}
/**
 * Constructor. Create an fuzzy expression "something is hedge, hedge, ... something".
 * For example, if expression is "temperature is not very high" then
 * lVariable = temperature,
 * mVariable = normal,
 * hVector = not, very,
 * textRepresentation = temperature is not very high.
 * This class is a service class for FuzzyRule and should not be used otherwise.
 * @param lv engine.LinguisticVariable Lingustic variable
 * @param mf java.lang.String Membership function
 * @param hVector java.util.Vector Vector containing all the hedges
 * @param textRepresentation java.lang.String Text representation of this expression
 */
public FuzzyExpression(LinguisticVariable lVariable, String mVariable, Vector hVector, String textRepresentation) 
{
	LV = lVariable;
	MF = mVariable;
	hedges = hVector;
	textExpression = textRepresentation;
}
}

