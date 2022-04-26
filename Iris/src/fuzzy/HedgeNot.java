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
 * Fuzzy "not" is implemented as a hedge
 */
public class HedgeNot extends Hedge 
{
/**
 * getName method for "not".
 */
public java.lang.String getName() 
{
	return "not";
}
/**
 * hedgeIt method for "not"
 */
public double hedgeIt(double value) 
{
	return 1.0-value;
}
}

