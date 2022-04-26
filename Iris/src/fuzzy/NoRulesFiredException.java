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
 * This exception is thrown if no rules fired and defuzzification attempted.
 */
public class NoRulesFiredException extends Exception {
/**
 * Constructor
 * @param s java.lang.String
 */
public NoRulesFiredException(String s) {
	super(s);
}
/**
 * Constructor
 */
public NoRulesFiredException() 
{
	super();
}
}

