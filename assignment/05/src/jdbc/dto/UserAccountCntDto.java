package jdbc.dto;

public class UserAccountCntDto {
	private int userSeq;
	private String name;
	private int accountCnt;
	
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
	public int getAccountCnt() {
		return accountCnt;
	}
	public void setAccountCnt(int accountCnt) {
		this.accountCnt = accountCnt;
	}
	
	@Override
	public String toString() {
		return "UserAccountCntDto [userSeq=" + userSeq + ", name=" + name + ", accountCnt=" + accountCnt + "]";
	}

}
