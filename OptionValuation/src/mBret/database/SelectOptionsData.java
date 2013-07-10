package mBret.database;

/**
 * Class used to pass required data. Will be used to hold all
 * the pertinent data associated with one specific option transaction.
 * 
 * @author mblackford mBret Bret Blackford
 *
 */
public class SelectOptionsData {

		public String trade;
		public String tradebook;
		public String product;
		public String settlementDate;
		public String counterparty;
		public String execution;
		public String positionType;
		public String priceStatus;
		public String quantity;
		public String priceQuantity;
		public String value;
		public String optionValue;
		public String marketPrice;
		public String optionValue_Black;
		public String optionValue_BlackScholes;
		public String delta;
		public String gamma;
		public String theta;
		public String vega;
		public String rho;
		public String delta_s;
		public String gamma_s;
		public String theta_s;
		public String vega_s;
		public String rho_s;
		public String volatility;
		public String strikePrice;
		public String expDate;
		public String begTime;
		public String endTime;
		public String price;
		public String location;
		public String optionType; //
		public String optionStyle;//
		public String accountingMethod; //from Position
		public String strike_s; // parsed from Description
		public String market_s; // parsed from Description
		public String intRate_s;// parsed from Description
		public String expTime_s;// parsed from Description
		public String expTime;
		public String description;
		
		public void clear() {
			trade = "";
			tradebook = "";
			product = "";
			settlementDate = "";
			counterparty = "";
			execution = "";
			positionType = "";
			priceStatus = "";
			quantity = "";
			priceQuantity = "";
			value = "";
			optionValue = "";
			marketPrice = "";
			delta = "";
			delta_s= "";
			gamma = "";
			gamma_s = "";
			theta = "";
			theta_s = "";
			vega = "";
			vega_s = "";
			rho = "";
			rho_s = "";
			volatility = "";
			strikePrice = "";
			expDate = "";
			begTime = "";
			endTime = "";
			price = "";
			location = "";
			optionType = "";
			optionStyle = "";
			accountingMethod = "";
			strike_s = "";
			market_s = "";
			intRate_s = "";
			expTime_s = "";
			expTime = "";
			description = "";
			optionValue_Black = "";
			optionValue_BlackScholes = "";
		}

		public String printHeaders() {

			String out = "trade|tradebook|product|settlementDate|counterparty|";
			out += "execution|positionType|priceStatus|quantity|priceQUantity|value|";
			out += "optionValue|marketPrice|Black|BlackScholes|";
			out += "delta|delta_s|gamma|gamma_s|theta|theta_s|vega|vega_s|rho|rho_s|";
			out += "volatility|strikePrice|expDate|begTime|endTime|";
			out += "price|location|optionType|optionStyle|acctgMeth|strike_s|market_s|intRate_s|";
			out += "expTime_s|expTime|description";

			return out;
		}

	public String toString() {

		String delim = "|";

		String out = trade + delim + tradebook + delim + product + delim
				+ settlementDate + delim;
		out += counterparty + delim + execution + delim + positionType + delim
				+ priceStatus + delim;
		out += quantity + delim + priceQuantity + delim + value + delim;
		out += optionValue + delim + marketPrice + delim + optionValue_Black
				+ delim + optionValue_BlackScholes + delim;
		out += delta + delim + delta_s + delim + gamma + delim + gamma_s
				+ delim;
		out += theta + delim + theta_s + delim + vega + delim + vega_s + delim;
		out += rho + delim + rho_s + delim;
		out += volatility + delim + strikePrice + delim + expDate + delim
				+ begTime + delim;
		out += endTime + delim + price + delim + location + delim + optionType
				+ delim;
		out += optionStyle + delim + accountingMethod + delim + strike_s
				+ delim + market_s + delim;
		out += intRate_s + delim + expTime_s + delim + expTime + delim
				+ description;

		return out;
	}
	
}
