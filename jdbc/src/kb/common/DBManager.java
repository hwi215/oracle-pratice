package kb.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC를 위한 로드, 연결, 닫기
 */
public class DBManager {

	/**
	 * 로드
	 */
	static {
		try {
			Class.forName(DBProperties.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // 오류메시지 출력
		}
	}
	
	/**
	 * 연결
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DBProperties.URL, DBProperties.USER_ID, DBProperties.USER_PASS);
	}

	/**
	 * 닫기(DML 전용 - insert ,update . delete)
	 */
	public static void releaseConnection(Connection con, Statement st) {
		try {
			if (st != null) st.close();
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 닫기(SELECT 전용)
	 */
	public static void releaseConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		releaseConnection(con, st);
	}
}
