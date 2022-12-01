/**
 * 
 */
package RSASolvers;

/**
 * @author charles.lagreou
 *
 */
public abstract class FactorAlgoSolver implements SolverModel{
	private final 	String	algoName;
	private			long	executionTime;
	
	//Constructor
	FactorAlgoSolver(String algoName) {
		this.algoName = algoName;
		this.executionTime = 0;
	}
	
	//Getter
	public 	long 	getExecutionTime() {
		return this.executionTime;
	}
	
	public 	String 	getAlgoName() {
		return this.algoName;
	}
	
}
