package jdbc.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.common.DBManager;
import jdbc.dao.UserDAO;
import jdbc.dao.UserDaoImpl;
import jdbc.dto.UserDto;

public class Test {
	public static UserDAO dao = new UserDaoImpl();

	public static void main(String[] args) throws SQLException {

		// #1. users table 에 insert 1건 처리를 위해 insert() 를 완성한다.
		System.out.println("#1. insert()");
		insert();

		// #2. users table 에 update 1건 처리를 위해 update() 를 완성한다.
		System.out.println("#2 =====================");
		//update();

		// #3. users table 을 전체 조회, 출력하는 selectAll() 를 완성한다.
		System.out.println("#3 =====================");
		//selectAll();

			
		// #4. users table 을 user_seq 로 1건  조회, 출력하는 selectOne() 를 완성한다.
		System.out.println("#4 =====================");
		//selectOne();
			
		// #5. users table 을 user_seq 로 1건  삭제하는 delete() 를 완성한다.
		System.out.println("#5 =====================");
		//delete();
		
	}
	
	static UserDto insert() {
		
		UserDto userDto = new UserDto();
		userDto.setUserSeq(666);
		userDto.setName("육길동");
		userDto.setEmail("six@gildong@com");
		userDto.setPhone("010-6666-6666");
		userDto.setSleep(false); // DB에 넣을때 true이면 Y , false이면 N의 값으로 저장한다.

		//dao 호출하고 리턴한 값을 받아서 상황에 맞게 출력해본다.
		int result = dao.insert(userDto);
		System.out.println("test insert");
		System.out.println("result = " + result);
		return userDto;
		
		
	}
	
	static UserDto update() {
		
		UserDto userDto = new UserDto();
		userDto.setUserSeq(666);
		userDto.setName("육길동");
		userDto.setEmail("yook@gildong@com");
		userDto.setPhone("010-7777-7777");
		userDto.setSleep(true);

		 //dao 호출하고 리턴한 값을 받아서 상황에 맞게 출력해본다.
		return userDto;
		
	}
	
	static void selectAll() {
		
		 //dao 호출하고 리턴한 값을 받아서 상황에 맞게 출력해본다.
	}
	
	static void selectOne() {
		
		int userSeq = 666;
		 //dao 호출하고 리턴한 값을 받아서 상황에 맞게 출력해본다.
		
		
	}
	
	static void delete() {
		
		int userSeq = 666;
		 //dao 호출하고 리턴한 값을 받아서 상황에 맞게 출력해본다.
		
		
	}
}
