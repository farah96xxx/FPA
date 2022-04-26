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
/**
 * This exception is thrown if an error occurs during evaluation of a rule
 * (most likely a MF name is wrong)
 */
public class EvaluationException extends Exception {
/**
 * Constructor
 * @param s java.lang.String
 */
public EvaluationException(String s) {
	super(s);
}
/**
 * Constructor
 */
public EvaluationException() 
{
	super();
}
}
