package net.agten.heatersimulator.domain.algorithm;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class FuzzyLogic implements ControllerAlgorithm {

public final static int INITIAL_TARGET_ADC = 300;
	
	double aggressiveness = 5.5; // there may be values between 0 and 9
    int targetAdc;
    FunctionBlock fb;
	

	public FuzzyLogic(double aggressiveness) {
		this.aggressiveness = aggressiveness;
		this.targetAdc = INITIAL_TARGET_ADC;
		
		String filename = "FuzzyLogicControl.fcl";
        FIS fis = FIS.load(filename, true);
        
        if (fis == null) {
            System.err.println("Can't load file: '" + filename + "'");
            System.exit(0);
        }
        
        fb = fis.getFunctionBlock(null);
        fb.setVariable("aggressiveness", this.aggressiveness);
	}


	public short nextValue(short curAdc) {
		int error = this.targetAdc - curAdc;
		
		fb.setVariable("error", -error/5);	 
		fb.evaluate(); 
	    fb.getVariable("command").defuzzify();  
	    
		double result = fb.getVariable("command").getValue();
		System.out.println(-error/5);

		return (short)Math.max(result, 0);
	}

	
	public void setTargetAdc(short targetAdc) {
		this.targetAdc = targetAdc;		
	}

}
