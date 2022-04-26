/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iris;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author aina
 */

/**
 *  THE PARAMETER POSITION..OR EVEN NOT USE solution value.. tuning msf happen with high accuracy at the end.
 * @author aina . 
 * 
 */

public class FPA  {
        private static final Random random = new Random(System.currentTimeMillis());

        private int gmax=0;
        private double fitness;
        private double rule;
         private double rulessent;
        private double pvalue;
        private double dvalue;
        private double accuracy;
        double min;
        double max=0;
        int popsize;
            
           double doubleruletry;
	// ND: Normal distribution
	private static final double meanND = 0.0;
	private static final double stdDevND = 1.0;

	// Parameters
        private int maxpopulation=80;//parameter max gen
        double fmin;
        
        public double getrule() { return this.rule;};
        public void setrule (double rule) { this.rule = rule;} 
        
        public double getrulessent() { return this.rulessent;};
        public void setrulessent (double rulessent) { this.rulessent = rulessent;} 
         
        public double getstartmf() { return this.pvalue;};
         public void setstartmf (double startmffpa){this.pvalue = startmffpa;} 
         
        public double getendmf() { return this.dvalue;};
        public void setendmf (double endmffpa){this.dvalue = endmffpa;} 
   
        public double getaccuracy() { return this.accuracy;};
        public void setaccuracy (double highestacc ){this.accuracy = highestacc ;} 
        public void setmin (double min) {this.min = min;} 
         public double getfmin() { return fmin;};   
         public double getmax() { return max;}; 
         public double getmin() { return min;};
             
          public void setpopsize (int popsize ){this.popsize = popsize;}
          
      public double run(int generation, int popsize, double switchProhability, Function function, double rule, double dvalue, double pvalue,double accuracy) {
		int indexBest, rand1, rand2;
		double fnew, epsilon;
		double[] fitness = new double[popsize];
		double[] candidate = new double[maxpopulation];
		double[] best = new double[maxpopulation];
		double[] levy = new double[maxpopulation];
		double[][] population = new double[popsize][maxpopulation];
                
                
		//System.out.println("rulevalue" + rule);
                //System.out.println("endmf" + dvalue);
                //System.out.println("startmf" + pvalue);
                
		// Initialize the population..iterations
		for (int i = 0; i < popsize; i++) {
			for (int j = 0; j < maxpopulation; j++)
				population[i][j] = (pvalue) +((dvalue) - (pvalue)) * random.nextDouble();

			fitness[i] = evaluate(population[i], function);
		}

		// Find the current best
		indexBest = 0;
		for (int i = 1; i < popsize; i++)
			if (fitness[i] < fitness[indexBest])
				indexBest = i;

		fmin = fitness[indexBest];
		for (int i = 0; i < maxpopulation; i++)
			best[i] = population[indexBest][i];

		// Iterations
		for (int iter = 0; iter < generation; iter++) {
			for (int i = 0; i < popsize; i++) {
				if (random.nextDouble() > switchProhability)
                                {
					/* Global Pollination */
      
					levy = levy();

					for (int j = 0; j < maxpopulation; j++) {
						candidate[j] = population[i][j] + levy[j] * (best[j] - population[i][j]);
					}
				} else {
        
					/* Local Pollination */
     
					epsilon = random.nextDouble();
                                           //System.out.println("Epsilon: " + epsilon);
					do {
						rand1 = random.nextInt(popsize);
					} while (rand1 == i);
					do {
						rand2 = random.nextInt(popsize);
					} while (rand2 == rand1);

					for (int j = 0; j < maxpopulation; j++)
						candidate[j] += epsilon * (population[rand1][j] - population[rand2][j]);
				}

				// Check bounds
				checkBounds(candidate);

				// Evaluate new solution
				fnew = evaluate(candidate, function);
                                System.out.println("List of result(not optimum): " + fitness[i]);

				// If the new solution is better: Replace
				if (fnew < fitness[i]) {
					fitness[i] = fnew;
					for (int j = 0; j < maxpopulation; j++)
						population[i][j] = candidate[j];
				}
                               

				// Update best solution
				if (fnew < fmin) {
					fmin = fnew;
					for (int j = 0; j < maxpopulation; j++){
                                        
						best[j] = candidate[j];
                                               System.out.println("Best candidate: " + best[j]);
                                                 }
                                               
		                    }
                                          
                                
		// Print 
		//System.out.println("Rule After Fuzzy-FPA: " + rule);
                System.out.println("Optimum result of Fuzzy-FPA[in this generation]: " + fmin);
		System.out.println("--------------------------");
                
                        }
                }              
                
		for (int i = 0; i < maxpopulation; i++)
                { 
                 Arrays.sort(best); 
	         System.out.println("Candidate solution " + Integer.toString(i + 1) + ": " + best[i]); 
               
                }
               
               
                  min= best[8];
                 int index = 0;
                 int indexOne = 0; 
                 max= best[79];
                 
                
                for (int f = 0; f < 10; f++){
                    
                      rulessent= best[9];
                      
                    if(max < best[f])
                {
                     Arrays.sort(best); 
                      max = best[f] ;  
                      max=max;
                      indexOne = f;
                      rulessent= best[f];
                 }
               
                     if(min< best[f] && min != max)//the min is the second largest value not minimum..
                 {
                    Arrays.sort(best); 
                   min = min;
                  index = f;
                  
                  }
                  
                }
                
              
                System.out.println("Best Value(in double):" +max);
               
               
               return 0;
              
              }
      
          
             private double evaluate(double[] sol, Function func) {
	
		switch (func) {
		case SPHERE:
			for (int i = 0; i < maxpopulation; i++) {
				fitness += Math.pow(sol[i], 2);
                                
			}
			return fitness;
                      case ROSENBROCK:
			for (int i = 0; i < maxpopulation - 1; i++) {
				fitness += (100 * Math.pow((sol[i + 1] - Math.pow(sol[i], 2)), 2) + (Math.pow((1 - sol[i]), 2)));
			}
			return fitness;
		case RASTRIGIN:
			double A = rule;
			fitness += A * maxpopulation;
			for (int i = 0; i < maxpopulation; i++) {
				fitness += (Math.pow(sol[i], 2) - A * Math.cos(2 * Math.PI * sol[i]));
			}
			return fitness;
		
		default:
			return fitness;
		}
	}

	/** checks lower and upper bounds */

	private void checkBounds(double[] solution) {
		for (int i = 0; i < maxpopulation; ++i) {
			if (solution[i] < (pvalue)){
				solution[i] = (pvalue);
                        }

			if (solution[i] > (dvalue)){
				solution[i] = (dvalue);
                        }
		}
	}

	/** creates Levy flight samples */

	private double[] levy() {
		double[] step = new double[maxpopulation];

		double sigma = Math.pow(Gamma.gamma(1 +(accuracy)) * Math.sin(Math.PI * (accuracy) / 2)
				/ (Gamma.gamma((1 + (accuracy)) / 2) * accuracy * Math.pow(2, ((accuracy)- 1) / 2)), (1 / (accuracy)));
                   
		for (int i = 0; i < maxpopulation; i++) {

			double u = Distribution.normal(random, meanND, stdDevND) * sigma;
			double v = Distribution.normal(random, meanND, stdDevND);

			step[i] = 0.01 * u / (Math.pow(Math.abs(v), (1 - (accuracy)
                                )));
                       
		}
              
		return step;
	}


	/* Getter and setter */

	public void setDimension(int maxpopulation) {
		this.maxpopulation = maxpopulation;
	}

	public void setUpperBound(double upperBound) {
		this.dvalue = upperBound;
	}

	public void setLowerBound(double lowerBound) {
		this.pvalue= lowerBound;
	}

	public void setLambda(double lambda) {
		this.rule = lambda;
	}   
}
