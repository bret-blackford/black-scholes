package mBret.options;

public class OptionGreeks {
	
	 public double delta;
	 public double theta;
	 public double rho;
	 public double gamma;
	 public double vega;
	 
	 public String toString() {
		 
		 String out = "delta-[" + delta + "] theta-[" + theta;
		 out += "] rho-[" + rho + "] gamma-[" + gamma;
		 out += "] vega-[" + vega;
		 
		 return out;
	 }

}
