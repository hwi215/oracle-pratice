package jdbc.app;

import java.util.List;

import jdbc.dto.AccountDto;
import jdbc.dto.UserAccountCntDto;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
		
			// #1. account table 에 insert 1건 처리를 위해 insert() 를 완성한다.
			insert();
			// #2. account table 에 update 1건 처리를 위해 update() 를 완성한다.
			update();
			// #3. account table 을 전체 조회, 출력하는 selectAll() 를 완성한다.
			selectAll();
			// #4. account table 을 account_seq 로 1건  조회, 출력하는 selectOne() 를 완성한다.
			selectOne();
			// #5. account table 을 account_seq 로 1건  삭제하는 delete() 를 완성한다.
			delete();
						
			// #6. account table 에서 balance 기준 내림차순으로 정렬한 후 상위 3개만 조회, 출력하는 selectBalaceDescTop3() 를 완성한다.
			selectBalanceDescTop3();
			
			// #7. users table 을 고객 이름으로 검색해서  해당되는 고객의 고객 번호, 고객명, 계좌 번호, 잔고를 출력하는 selectUsersAccountByName() 를 완성한다.
			selectUsersAccountByName();
			
			// #8. users table 에서 user_seq, name 조회하되, name 뒤에 account 의 개수도 account_cnt 로 함께 보여 준다. 계좌가 없는 user 는 account_cnt 를 0 으로 보여준다.
			//     위  내용을 조회, 출력하는 selectUserAccountCnt() 를 완성한다.
			selectUserAccountCnt();
			
		
	}
	
	// 아래의 모든 메소드는 AccountDao 를 이용해서 DB Access 를 수행한다.
	
	// #1
	static void insert() {
		
		AccountDto accountDto = new AccountDto();
		accountDto.setAccountSeq(80);
		accountDto.setAccountNumber("00800808008008");
		accountDto.setBalance(8000);
		accountDto.setUserSeq(333);
		
		// 이 곳에 AccountDao 를 이용해서 계좌 데이터을 1건 등록하는 코드를 작성한다.
		// 계좌 정보는  AccountDto 객체를 이용한다.
	}
	
	// #2
	static void update() {
		
		AccountDto accountDto = new AccountDto();
		accountDto.setUserSeq(80);
		accountDto.setAccountNumber("00800808008008");
		accountDto.setBalance(5000);
		accountDto.setUserSeq(333);
		
		// 이 곳에 AccountDao 를 이용해서 계좌 데이터을 1건 수정하는 코드를 작성한다.
		// 수정되는 고객 정보는 UserDto 객체를 이용한다. 
	}
	
	// #3
	static void selectAll() {
		
		List<AccountDto> accountList = null;
		
		// 이 곳에 AccountDao 를 이용해서 account table 을 전체 조회하고 각각을 AccountDto 객체로 생성한 후 accountList 담는다.
		// accountList 를 순회하면서 각 AccountDto 객체의 내용을 출력하는 코드를 작성한다.
	}
	
	// #4
	static void selectOne() {
		
		int accountSeq = 80;
		AccountDto accountDto = null;
		
		// 이 곳에 AccountDao 를 이용해서 account table 을 1건 조회하여 AccountDto 객체를 만들고 객체의 내용을 출력하는 코드를 작성한다.
		// 위 accountSeq 를 이용해서 1건  조회한다.
	}
	
	// #5
	static void delete() {
		
		int accountSeq = 80;
		
		// 이 곳에 AccountDao 를 이용해서 account table 을 1건 삭제하는 코드를 작성한다.
		// 위 accountSeq 를 이용해서 1 건을 삭제한다.
	}
	
	// #6
	static void selectBalanceDescTop3() {

		List<AccountDto> accountList = null;
		
		// 이 곳에 AccountDao 를 이용해서 account table 에서 balance 기준 내림차순으로 정렬한 후 상위 3개만 조회하고, 각각을 AccountDto 객체로 생성한 후 accountList 담는다.
		// accountList 를 순회하면서 각 AccountDto 객체의 내용을 출력하는 코드를 작성한다.	
	}
	
	// #7
	static void selectUsersAccountByName() {
		
		String searchName = "길동"; // 고객 이름 변수
		List<AccountDto> accountList = null;
		
		// 이 곳에 AccountDao 를 이용해서 users table 을 고객 이름으로 LIKE 검색해서  해당되는 고객의 고객 번호, 고객명, 계좌 번호, 잔고를 조회하고, 각각을 AccountDto 객체로 생성한 후 accountList 담는다.
		// accountList 를 순회하면서 각 AccountDto 객체의 내용을 출력하는 코드를 작성한다.
		// 검색하는 고객 이름은 임의로 작성한다.	
	}
	
	// #8
	static void selectUserAccountCnt() {

		List<UserAccountCntDto> userAccountCntList = null;
		
		// 이 곳에 AccountDao 를 이용해서 users table 에서 user_seq, name 을 조회하되, name 뒤에 account 의 개수도 account_cnt 로 함께 조회하고, 각각을 UserAccountCntDto 객체로 생성한 후 userAccountCntList 담는다.
		// userAccountCntList 를 순회하면서 각 UserAccountCntDto 객체의 내용을 출력하는 코드를 작성한다.	
	}

}
