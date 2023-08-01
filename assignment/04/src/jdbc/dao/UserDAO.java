package jdbc.dao;

import java.util.List;

import jdbc.dto.UserDto;

public interface UserDAO {
	/**
	 * 등록
	 * */
    int insert(UserDto userDto) ;
    
    /**
	 * 수정
	 * */
	int  update(UserDto userDto);
	
	/**
	 * 삭제
	 * */
	int  delete(int userSeq) ;
	
	/**
	 * 전체검색
	 * */
	List<UserDto> selectAll() ;
	
	/**
	 * userSeq에 해당하는 회원검색
	 * */
	UserDto  selectOne(int userSeq) ;
	
	
}
