/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iris;

import java.util.Random;

/**
 *
 * @author aina
 */
public class Distribution {
    private static double spare;
       private static boolean isSpareReady = false;

	/** Polar-Method */
	public static double normal(Random random, double mean, double stdDev) {
		if (isSpareReady) {
			isSpareReady = false;
			return spare * stdDev + mean;
		} else {
			double u, v, s;
			do {
				u = random.nextDouble() * 2 - 1;
				v = random.nextDouble() * 2 - 1;
				s = u * u + v * v;
			} while (s >= 1 || s == 0);
			double mul = StrictMath.sqrt(-2.0 * StrictMath.log(s) / s);
			spare = v * mul;
			isSpareReady = true;
			return mean + stdDev * u * mul;
		}
	}
}
