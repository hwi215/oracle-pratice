package jdbc.dao;

import java.util.List;

import jdbc.dto.AccountDto;
import jdbc.dto.UserAccountCntDto;

public interface AccountDao {
	int insert(AccountDto accountDto);
	int update(AccountDto accountDto);
	int delete(int accountSeq);
	
	List<AccountDto> selectAll();
	AccountDto selectOne(int accountSeq);
	
	
	/**
	 * 
	 * AccountDao 를 이용해서 account table 에서 balance 기준 내림차순으로 정렬한 후 상위 3개만 조회
	 
	 	select aa.* from 
		(select a.*, rownum rnum from 
		(select * from account order by balance desc ) a 
		 ) aa 
		 where aa.rnum <= 3
	 * */
	List<AccountDto> selectBalanceDescTop3(); //WS_03 10번문제 
	
	
	/**
	 *  
	 *   users table 을 고객 이름으로 LIKE 검색해서  해당되는 고객의 고객 번호, 고객명, 계좌 번호, 잔고를 조회
	 *  
       select u.user_seq, u.name, a.account_number, a.balance 
	   from account a, users u 
	   where a.user_seq = u.user_seq 
	   and u.name like ? 
	 * */
	List<AccountDto> selectUsersAccountByName(String searchName);
	
	
	/**
	 * 
	 *  users table 에서 user_seq, name 을 조회하되, name 뒤에 account 의 개수도 account_cnt 로 함께 조회
	 *  
	 *    select u.user_seq, u.name, nvl(a.account_cnt, 0) account_cnt 
		  from users u left outer join 
		  ( select user_seq, count(*) account_cnt from account group by user_seq ) a 
		   on u.user_seq = a.user_seq  
	 * */
	List<UserAccountCntDto> selectUserAccountCnt();
}
