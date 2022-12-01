/**
 * 
 */
package RSA;

/**
 * @author charles.lagreou
 *
 */
public class ComputeSpecs {
	private	final String	computerName;
	private	String			cPUName;
	private	int				rAMSize; //expressed in MegaBytes
	
	//Constructor
	public	ComputeSpecs(String computerName, String cPUName, int rAMSize) {
		this.computerName = computerName;
		this.cPUName = cPUName;
		this.rAMSize = rAMSize;
	}
	
	//Getter
	public	String	getComputerName() {
		return this.computerName;
	}
	
	public	String	getCPUName() {
		return this.cPUName;
	}
	
	public	int		getRAMSize() {
		return this.rAMSize;
	}
	
	//Setter
	public	void	setCPUName(String cPUName) {
		this.cPUName = cPUName;
	}
	
	public	void	setRAMSize(int rAMSize) {
		this.rAMSize = rAMSize;
	}
}
