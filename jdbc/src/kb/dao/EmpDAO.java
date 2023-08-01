package kb.dao;

import kb.common.DBManager;
import kb.dto.EmpDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	/**
	 * emp테이블의 사원 정보 검색하기
	 * SELECT EMPNO, ENAME, JOB, SAL, HIREDATE FROM EMP
	 *
	 * @return*/
	public List<EmpDTO> selectAll() throws SQLException {
		// 로드 연결 실행 닫기
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT EMPNO, ENAME, JOB, SAL, HIREDATE FROM EMP";

		List<EmpDTO> list = new ArrayList<>();
		try{
			conn = DBManager.getConnection(); // 연결
			ps = conn.prepareStatement(sql);
			System.out.println("연결 성공");

		}catch(SQLException e){
			// ?가 있다면, ?의 갯수만큼 순소대로 set을 설정한다.

			// DB에 쿼리를 전송한다. - excuteUpdate() or excuteQuery()
			if(conn != null){
				System.out.println("성공");
			}else{
				System.out.println("실패");
			}
			rs = ps.executeQuery();

			while(rs.next()) { // 내려갈게 없으면 false, 이수면 t
				// 현재 행의 열의 정보(컬럼 정보)를 가져온다. (조회)
				int empno = rs.getInt(1);
				String ename = rs.getString(2);
				String job = rs.getString(3);
				int sal = rs.getInt(4);
				String hiredate = rs.getString(5);

				System.out.println("empno: " + empno + "| ename:"  + ename);
				list.add(new EmpDTO(empno, ename, job, sal, hiredate));

			}

		}finally {
			// 닫기
			DBManager.releaseConnection(conn, ps, rs);

		}
		return list;

	}//selectAll End

	/**
	 * 사원 등록하기
	 * insert into emp(empno, ename, job, sal, hiredate) values (?,?,?,?,sysdate)
	 * */
	public int insert(EmpDTO emp) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "insert into emp(empno, ename, job, sal, hiredate) values (?,?,?,?,sysdate)";
		int result = 0;

		try{
			con = DBManager.getConnection();
			ps = con.prepareStatement(sql);
			// ?가 있다면 ?의 개수만큼 setType(?index, 값) 설정 -> set 4번
			ps.setInt(1, emp.getEmpno());
			ps.setString(2, emp.getEname());
			ps.setString(3, emp.getJob());
			ps.setInt(4, emp.getSal());

			// DB에 쿼리 전송
			result = ps.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();


		}finally {
			DBManager.releaseConnection(con, ps);

		}

		return result;
	}//insert End
	
}//classEnd
