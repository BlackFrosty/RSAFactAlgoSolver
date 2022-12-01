/**
 * 
 */
package RSASolvers;

import java.math.BigInteger;

import RSA.KeySetRSA;

/**
 * @author charles.lagreou
 *
 */
public class TrialDivisionSolver extends FactorAlgoSolver {

	/**
	 * 
	 */
	public TrialDivisionSolver() {
		super("TrialDivision");
	}

	public KeySetRSA solve(KeySetRSA keySet) {
		// TODO
		return null;
	}
	
	public BigInteger calculatePrime1(BigInteger modulus) {
		BigInteger displayRange = BigInteger.valueOf(1000000);
		BigInteger prime1 = new BigInteger("0");
		
		while (true) {
        	if (prime1.mod(displayRange).compareTo(BigInteger.valueOf(0)) == 0)
			System.out.println("Testing prime from "+prime1.divide(displayRange).multiply(displayRange)+
								" to "+prime1.divide(displayRange).add(BigInteger.valueOf(1)).multiply(displayRange).subtract(BigInteger.valueOf(1))+
								" ...");
        	
			if (prime1.isProbablePrime(10))
        		if (modulus.mod(prime1).compareTo(BigInteger.valueOf(0)) == 0) {
        			System.out.println("Prime found!");
        			return prime1;
        		}
        	prime1 = prime1.add(BigInteger.valueOf(1));
		}
	}

	public BigInteger calculatePrime2(BigInteger modulus, BigInteger prime1) {
		return modulus.divide(prime1);
	}
	
	public BigInteger calculatePrivateExponent(BigInteger publicExponent, BigInteger prime1, BigInteger prime2) {
		return publicExponent.modInverse((prime1.subtract(BigInteger.valueOf(1))).multiply(prime2.subtract(BigInteger.valueOf(1))));
	}
		
	public BigInteger calculateExponent1(BigInteger privateExponent, BigInteger prime1) {
		return privateExponent.mod(prime1.subtract(BigInteger.valueOf(1)));
	}
	
	public BigInteger calculateExponent2(BigInteger privateExponent, BigInteger prime2) {
		return privateExponent.mod(prime2.subtract(BigInteger.valueOf(1)));
	}

	public BigInteger calculateCoefficient(BigInteger prime1, BigInteger prime2) {
		return prime2.modInverse(prime1);
	}
}
