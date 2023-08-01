package kb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kb.common.DBManager;


public class TransactionDAO {
  /**
   * 계좌이체 기능 구현
   * 
   * */
	//입금계좌번호, 출금계좌번호, 입금액
	public void transfer(String inputAccount, String outputAccount, int money) {
		Connection con=null;
		
		try {
			con = DBManager.getConnection();
			
			con.setAutoCommit(false);//자동커밋해지!!!

			//1. 출금을 한다. - update
			int result = this.withDraw(con, outputAccount, money);
			if(result==0)throw new SQLException("출금계좌오류로 계좌이체 실패했습니다.^^");
			
			//2.  1이 정상이면 입금을 한다. - update
			result=this.deposit(con, inputAccount, money);
			if(result==0) throw new SQLException("입금계좌번호 오류로 계좌이체 실패했습니다.^^");
			
			//3. 2가 정상이면 입금계좌의 잔액을 조회한다. - select
			if( this.isCheckBalance(con, inputAccount) )
				throw new SQLException("입금계좌 총액금액 초과로 계좌이체 실패하였습니다.");
			
			//4. 조회한 금액이 1000미만이면 commit한다, 그외는 모두 rollback한다.
			con.commit();
			System.out.println("계좌이체 성공하였습니다.!!!");
			
		}catch (Exception e) {
//			e.printStackTrace();
			try {
			  con.rollback();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(e.getMessage());
		}finally {
			DBManager.releaseConnection(con, null);
		}
		
	}
	
	/**
	 * 출금하기 
	 * update bank set balance=balance-? where account = ?
	 * */
	public int withDraw(Connection con , String outputAccount , int money)throws SQLException { //출금계좌번호, 금액 
		PreparedStatement ps=null;
		String sql="update bank set balance=balance-? where account = ?";
		int result=0;
		try {
		   ps = con.prepareStatement(sql);
		   ps.setInt(1, money);
		   ps.setString(2, outputAccount);
		   
		   result = ps.executeUpdate();
		   
		}finally {
			DBManager.releaseConnection(null, ps);
		}
		
		return result;
	}
	
	/**
	 * 입금하기
	 * update bank set balance=balance+? where account =?
	 * */
     public int deposit(Connection con , String inputAccount , int money)throws SQLException { //입계좌번호, 금액 
    	 PreparedStatement ps=null;
 		String sql="update bank set balance=balance+? where account =?";
 		int result=0;
 		try {
 		   ps = con.prepareStatement(sql);
 		   ps.setInt(1, money);
 		   ps.setString(2, inputAccount);
 		   
 		   result = ps.executeUpdate();
 		   
 		}finally {
 			DBManager.releaseConnection(null, ps);
 		}
 		
 		return result;
	}
	
	/**
	 * 잔액조회하기 
	 * @return : true이면 잔액초과,  false이면 초과아니다.
	 * 
	 * select balance from bank where account=?
	 * */
     public boolean isCheckBalance(Connection con, String inputAccount)throws SQLException {//입금계좌번호
    	 PreparedStatement ps=null;
    	 ResultSet rs=null;
    	 boolean result=false;
    	 String sql="select balance from bank where account=?";
    	 try {
    		 ps =con.prepareStatement(sql);
    		 ps.setString(1, inputAccount);
    		 rs = ps.executeQuery();
    		 if(rs.next()) {
    			 int balance = rs.getInt(1);
    			 if(balance >1000)result=true;
    		 }
    		 
    	 }finally {
    		 DBManager.releaseConnection(null, ps, rs);
    	 }
    	 return result;
     }
	
	
	
	public static void main(String[] args) {
		TransactionDAO dao = new TransactionDAO();
		System.out.println("--1. 출금계좌 오류----");
		dao.transfer("A02", "A05",200);//입금, 출금, 금액
		
		//System.out.println("--2. 입금계좌 오류----");
		//dao.transfer("A04", "A01",200);//입금, 출금, 금액
		
		
		//System.out.println("--3. 입금계좌의 총액 1000원 이상인경우----");
		//dao.transfer("A02", "A01",700);//입금, 출금, 금액
		
//		System.out.println("--4. 성공----------");
//		dao.transfer("A02", "A01",100);//입금, 출금, 금액
	}
}











