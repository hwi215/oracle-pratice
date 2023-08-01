package kb.dao;

import kb.dto.EmpDTO;

public class EmpDAO {
	/**
	 * emp테이블의 사원 정보 검색하기
	 * SELECT EMPNO, ENAME, JOB, SAL, HIREDATE FROM EMP
	 * */
	public void selectAll() {
		
	}//selectAll End

	/**
	 * 사원 등록하기
	 * insert into emp(empno, ename, job, sal, hiredate) values (?,?,?,?,sysdate)
	 * */
	public int insert(EmpDTO emp) {
		
		return 0;
	}//insert End
	
}//classEnd
