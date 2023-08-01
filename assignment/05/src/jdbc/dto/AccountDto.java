package jdbc.dto;

public class AccountDto {
	private int accountSeq;
	private String accountNumber;
	private int balance;
	private int userSeq;
	private String name;;
	
	public int getAccountSeq() {
		return accountSeq;
	}
	public void setAccountSeq(int accountSeq) {
		this.accountSeq = accountSeq;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "AccountDto [accountSeq=" + accountSeq + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ ", userSeq=" + userSeq + ", name=" + name + "]";
	}

}
