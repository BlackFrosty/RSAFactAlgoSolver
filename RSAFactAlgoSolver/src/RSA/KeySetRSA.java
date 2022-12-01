package RSA;

import java.math.*;

public class KeySetRSA {
	
	public BigInteger modulus, prime1, prime2, publicExponent, privateExponent;
	
	public KeySetRSA(BigInteger modulus, BigInteger publicExponent) {
		this.modulus = modulus;
		this.prime1 = this.calculatePrime1(modulus);
		this.prime2 = this.calculatePrime2(modulus, this.prime1);
		this.publicExponent = publicExponent;
		this.privateExponent = this.calculatePrivateExponent(publicExponent, prime1, prime2);
	}
	
	public void displayRSA() {
		System.out.println("modulus = "+this.modulus);
		System.out.println("prime 1 = "+this.prime1);
		System.out.println("prime 2 = "+this.prime2);
		System.out.println("public exponent = "+this.publicExponent);
		System.out.println("private exponent = "+this.privateExponent);
		System.out.println("exponent 1 = "+this.calculateExponent1(privateExponent, prime1));
		System.out.println("exponent 2 = "+this.calculateExponent2(privateExponent, prime2));
		System.out.println("coefficient = "+this.calculateCoefficient(prime1, prime2));
	}
	


}
