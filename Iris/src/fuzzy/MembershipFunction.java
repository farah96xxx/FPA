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
 * Class for fuzzy membership functions.
 */
public class MembershipFunction 
{
	private String name;
	private double[] range;
/**
 * Fuzzify a value.
 * @param X Input value.
 * @return Result of fuzzification.
 */
public double  fuzzify(double X) 
{
	//Check if input value is in range, if not, return 0
	if(X<range[0] || X>range[3])	return 0;

	//Determine which of 3 /-\ slopes works
	//For middle part, return 1

	if(X>=range[1] && X<=range[2])	return 1;

	if(X>=range[0] && X<range[1])	return	(X-range[0])/(range[1]-range[0]);
	
	if(X>range[2] && X<=range[3])	return (range[3]-X)/(range[3]-range[2]);

	return 0;
}
/**
 * Return name of this membership function.
 * @return java.lang.String
 */
public String getName() 
{
	return name;
}
/**
 * Return 4 points of the trapeziod function.
 * @return double[]
 */
public double[] getRange() 
{
	return range;
}
/**
 * Constructor
 */
public MembershipFunction(String name_in, double [] range_in) 
{
	super();
	this.name = name_in;
	this.range = range_in;
}
/**
 * Return an array with discrete representation of the function.
 * @param from Left X-axis value.
 * @param to Right X-axis value.
 * @param size Number of discrete steps.
 * @return double[] Double array with size "size".
 */
public double [] plot(double from, double to, int size) 
{
	double increment = Math.abs((to-from)/size);
	
	double [] temp = new double [size];

	for(int i=0; i<size; i++)
		temp[i]=this.fuzzify(from+increment*i);
	
	return temp;
}
}

