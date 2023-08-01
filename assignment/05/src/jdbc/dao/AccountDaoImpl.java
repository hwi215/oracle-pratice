package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.common.DBManager;
import jdbc.dto.AccountDto;
import jdbc.dto.UserAccountCntDto;

public class AccountDaoImpl implements AccountDao{

	private static AccountDaoImpl instance = new AccountDaoImpl();
	
	private AccountDaoImpl() {}
	
	public static AccountDaoImpl getInstance() {
		return instance;
	}

	
	@Override
	public int insert(AccountDto accountDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AccountDto accountDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int accountSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AccountDto> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountDto selectOne(int accountSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDto> selectBalanceDescTop3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountDto> selectUsersAccountByName(String searchName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccountCntDto> selectUserAccountCnt() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
