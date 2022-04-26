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
 * This hedge is an implementation of fuzzy "very"
 */
public class HedgeVery extends Hedge 
{
/**
 * getName method for "very"
 */
public java.lang.String getName() 
{
	return "very";
}
/**
 * hedgeIt method for "very"
 */
public double hedgeIt(double value) 
{
	return value*value;
}
}

