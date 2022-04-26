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
 * Abstract class for Hedges.
 */
public abstract class Hedge {
/**
 * Abstract method which returns hedge's name.
 * @return java.lang.String
 */
public abstract String getName();
/**
 * Abstract method for applying hedge.
 * @param value Double value to be hedged.
 * @return Result of hedging
 */
public abstract double hedgeIt(double value);
}

