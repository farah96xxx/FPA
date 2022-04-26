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
 * This hedge is an implementation of fuzzy "somewhat"
 */
public class HedgeSomewhat extends Hedge 
{
/**
 * getName method for "somewhat"
 */
public java.lang.String getName() 
{
	return "somewhat";
}
/**
 * hedgeIt method for "somewhat"
 */
public double hedgeIt(double value) 
{
	return Math.sqrt(value);
}
}

