import java.util.Calendar;
import java.util.Date;

public class problem1d {
	enum Job{
		REGULARCLIENT,
		PREMIUMCLIENT,
		FINANCIALADVISOR,
		FINANCIALPLANNER,
		INVESTMENTADVISOR,
		COMPLIANCEOFFICER,
		TELLER,
		TECHNICALSUPPORT
	}
	enum Access{
		NONE,
		READ,
		WRITE,
		OWN
	}
	
	private Access ClientAccountBalance;
	private Access ClientInvestmentPortfolio;
	private Access FAContactDetails;
	private Access FPContactDetails;
	private Access IAContactDetails;
	private Access PrivateConsumerInstruments;
	private Access MoneyMarketInstruments;
	private Access DerivativesTrading;
	private Access InterestInstruments;
	private boolean givenPermission;
	
		
	public problem1d() {
		// Initialize all accesses to none 
		this.ClientAccountBalance = Access.NONE;
		this.ClientInvestmentPortfolio = Access.NONE;
		this.FAContactDetails = Access.NONE;
		this.FPContactDetails = Access.NONE;
		this.IAContactDetails = Access.NONE;
		this.PrivateConsumerInstruments = Access.NONE;
		this.MoneyMarketInstruments = Access.NONE;
		this.DerivativesTrading = Access.NONE;
		this.InterestInstruments = Access.NONE;
		this.givenPermission = false;
	}
	
	private void AccessLevels(Job job) {
		switch(job) {
		case REGULARCLIENT:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.READ;
			this.FAContactDetails = Access.READ;
			break;
		
		case PREMIUMCLIENT:
			this.ClientAccountBalance = Access.WRITE;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.FAContactDetails = Access.READ;
			this.FPContactDetails = Access.READ;
			this.IAContactDetails = Access.READ;
			break;
			
		case FINANCIALADVISOR:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.FAContactDetails = Access.WRITE;
			this.PrivateConsumerInstruments = Access.READ;
			break;
			
		case FINANCIALPLANNER:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.FPContactDetails = Access.WRITE;
			this.PrivateConsumerInstruments = Access.READ;
			this.MoneyMarketInstruments = Access.READ;
			break;
			
		case INVESTMENTADVISOR:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.IAContactDetails = Access.WRITE;
			this.PrivateConsumerInstruments = Access.READ;
			this.MoneyMarketInstruments = Access.READ;
			this.InterestInstruments = Access.READ;
			break;
			
		case COMPLIANCEOFFICER:
			this.ClientInvestmentPortfolio = Access.OWN;
			break;
			
		case TELLER:
			if(Calendar.DAY_OF_WEEK == (Calendar.MONDAY | 
										Calendar.TUESDAY | 
										Calendar.WEDNESDAY | 
										Calendar.THURSDAY | 
										Calendar.FRIDAY)
									& ((Calendar.HOUR_OF_DAY >= 9) 
									&  (Calendar.HOUR_OF_DAY <=  17))) {
				this.ClientAccountBalance = Access.READ;
				this.ClientInvestmentPortfolio = Access.READ;
			}
			break;
			
		case TECHNICALSUPPORT:
			if(this.givenPermission) {
				this.ClientAccountBalance = Access.READ;
				this.ClientInvestmentPortfolio = Access.READ;
			}
						
			break;
			
		default:
			break;
			
		}
	}
	
	public Access[] getAccess(Job job) {
		this.AccessLevels(job);
		Access[] accessArray = new Access[9];
		accessArray[0] = this.ClientAccountBalance;
		accessArray[1] = this.ClientInvestmentPortfolio;
		accessArray[2] = this.FAContactDetails;
		accessArray[3] = this.FPContactDetails;
		accessArray[4] = this.IAContactDetails;
		accessArray[5] = this.PrivateConsumerInstruments;
		accessArray[6] = this.MoneyMarketInstruments;
		accessArray[7] = this.DerivativesTrading;
		accessArray[8] = this.InterestInstruments;
		return accessArray;
		
	}
	
	public String accessString(Job job) {
		Access[] accessArray = this.getAccess(job);

		String s = "";
		s += "Client Account Balance: " + accessArray[0] + "\n";
		s += "Client Investment Portfolio: " + accessArray[1] + "\n";
		s += "Financial Advisor Contact Details: " + accessArray[2] + "\n";
		s += "Financial Planner Contact Details: " + accessArray[3] + "\n";
		s += "Invesment Advisor Contact Details: " + accessArray[4] + "\n";
		s += "Private Consumer Instruments: " + accessArray[5] + "\n";
		s += "Money Making Instruments: " + accessArray[6] + "\n";
		s += "Derivatives Trading: " + accessArray[7] + "\n";
		s += "Interest Instruments: " + accessArray[8] + "\n";
		
		
		return s;
		
		
	}
	
	public static void main(String[] args) {
		problem1d d = new problem1d();		
		System.out.println("The Access for a Regular Client is:\n" + d.accessString(Job.REGULARCLIENT));
		problem1d d1 = new problem1d();
		System.out.println("The Access for a Premium Client is:\n" + d1.accessString(Job.PREMIUMCLIENT));
		problem1d d2 = new problem1d();
		System.out.println("The Access for a Financial Advisor is:\n" + d2.accessString(Job.FINANCIALADVISOR));
		problem1d d3 = new problem1d();
		System.out.println("The Access for a Financial Planner is:\n" + d3.accessString(Job.FINANCIALPLANNER));
		problem1d d4 = new problem1d();
		System.out.println("The Access for an Investment Advisor is:\n" + d4.accessString(Job.INVESTMENTADVISOR));
		problem1d d5 = new problem1d();
		System.out.println("The Access for a Compliance Officer is:\n" + d5.accessString(Job.COMPLIANCEOFFICER));
		problem1d d6 = new problem1d();
		System.out.println("The Access for a Teller if it is Monday-Friday 9:00AM to 5:00PM on the computer clock:\n" + d6.accessString(Job.TELLER));
		problem1d d7 = new problem1d();
		d7.givenPermission = true;
		System.out.println("The Access for Technical Support if given permssion by a client:\n" + d7.accessString(Job.TECHNICALSUPPORT));
	}
}
