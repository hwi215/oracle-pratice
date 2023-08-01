package kb.view;

import kb.dao.EmpDAO;
import kb.dto.EmpDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		//1. 전체 사원 검색
		System.out.print("*** 1. 전체사원 검색 ****\n");
		EmpDAO dao = new EmpDAO();
		dao.selectAll();
		
		//2. 사원 등록
		System.out.print("*** 2. 신규 사원 등록 ****\n");
		int result = dao.insert(new EmpDTO(9000,"이지은", "학생", 100));
//		EmpDTO dto = new EmpDTO(9000,"이지은", "학생", 100);
//		dao.insert(dto);
		if(result >0) System.out.println("등록되었습니다.");
		else System.out.println("등록되지않았습니다.");
		
	}

/*
	public class DbManager{
		// 생성
		static {
			ClassformName("드라이버 명");

			// 연결
			public static Connection getConnection () throws SQLException {
				Connections con = DriverManager.getConnection(url, id, password);
				return con;
			}
		};// 메소드 닫기


 */

}

