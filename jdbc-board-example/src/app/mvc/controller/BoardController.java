package app.mvc.controller;

import app.mvc.dto.BoardDTO;
import app.mvc.dto.ReplyDTO;
import app.mvc.exception.DMLException;
import app.mvc.exception.SearchWrongException;
import app.mvc.service.BoardService;
import app.mvc.service.BoardServiceImpl;
import app.mvc.view.FailView;
import app.mvc.view.SuccessView;

import javax.sql.rowset.serial.SerialException;
import java.util.List;


public class BoardController {

	private static BoardService boardService = BoardServiceImpl.getInstance();
   
   
   /**
    * 전체검색
    * */
   public static void boardSelectByAll() {
	   // 서비스 호출
	   try {
		   List<BoardDTO> list = boardService.boardSelectAll();
		   SuccessView.selectPrint(list);
	   }catch (SearchWrongException e){
		   FailView.errorMessage(e.getMessage());
	   }
	  
   }
    /**
     * 글번호에 해당하는 게시물 검색
     * */
	public static void boardSelectByNo(int boardNo) {
		try{
			BoardDTO boardDTO = boardService.boardSelectByNo(boardNo);
			SuccessView.selectByNoPrint(boardDTO);
		}catch (SearchWrongException e){
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	
	/**
	 * subject에 인수로 전달된 word를 포함한 게시물 검색
	 * */
	public static void boardSelectBySubject(String word) {

		try{
			List<BoardDTO> list = boardService.boardSelectBySubject(word);
			SuccessView.selectPrint(list);
		}catch (SearchWrongException e){
			FailView.errorMessage(e.getMessage());
		}
		
		
	}
	
	/**
	 * 게시물 등록하기 
	 * */
	public static void boardInsert(BoardDTO board) {
		try{
			boardService.boardInsert(board);
			System.out.println("게시물 등록 성공 !");
		}catch (SearchWrongException e){
			FailView.errorMessage(e.getMessage());
		}

		
		
	}
	
	/**
	 * 게시물 수정하기
	 * */
	public static void boardUpdate(BoardDTO board) {
		try{
			boardService.boardUpdate(board);
			System.out.println("게시물 수정 성공!");
		}catch (SearchWrongException e){
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 게시물 삭제하기 
	 * */
	public static void boardDelete(int no) {
		try {
			boardService.boardDelete(no);
			SuccessView.messagePrint(no + "게시물 삭제 완료");
		}catch (DMLException e){
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * 댓글등록하기
	 * */
	public static void replyInsert(ReplyDTO replyDTO) {
		try{
			boardService.replyInsert(replyDTO);
			System.out.println("댓글 등록 성공!");
		}catch (SearchWrongException e){
			FailView.errorMessage(e.getMessage());
		}
		
	}



	/**
	 * 부모글에 해당하는 댓글정보 가져오기
	 * */
	public static void replySelectByParentNo(int boardNo) {
		try{
			BoardDTO boardDTO = boardService.replySelectByParentNo(boardNo);
			System.out.println("댓글 정보 불러오기 성공!");
			SuccessView.selectReplyPrint(boardDTO);
		}catch (SearchWrongException e){
			FailView.errorMessage(e.getMessage());
		}


	}
	
}



















