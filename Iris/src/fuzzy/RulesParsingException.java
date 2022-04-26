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
 * This exception is thrown if an error occurs during parsing a fuzzy rule
 * Exception.getMessage() will return cause of the error
 */
public class RulesParsingException extends Exception 
{
/**
 * Constructor
 * @param s java.lang.String
 */
public RulesParsingException(String s) 
{
	super(s);
}
/**
 * Constructor
 */
public RulesParsingException() 
{
	super();
}
}
