package app.mvc.service;

import app.mvc.dao.BoardDAO;
import app.mvc.dao.BoardDAOImpl;
import app.mvc.dto.BoardDTO;
import app.mvc.dto.ReplyDTO;
import app.mvc.exception.DMLException;
import app.mvc.exception.SearchWrongException;

import java.util.List;

public class BoardServiceImpl implements BoardService{
    private static BoardService instance = new BoardServiceImpl();

    private  BoardServiceImpl(){} // 생성자
    private BoardDAO boardDAO = BoardDAOImpl.getInstance();

    public static BoardService getInstance() {
        return instance;
    }

    @Override
    public List<BoardDTO> boardSelectAll() throws SearchWrongException {
        // dao call
        List<BoardDTO> list = boardDAO.boardSelectAll();
        if(list.isEmpty()){
            throw new SearchWrongException("게시판에 게시물의 정보가 없습니다.");
        }
        return list;
    }

    @Override
    public List<BoardDTO> boardSelectBySubject(String keyWord) throws SearchWrongException {
        List<BoardDTO> list = boardDAO.boardSelectBySubject(keyWord);
        if(list.isEmpty()){
            throw new SearchWrongException("해당 게시물의 정보가 없습니다.");
        }
        return list;
    }

    @Override
    public BoardDTO boardSelectByNo(int boardNo) throws SearchWrongException {
        BoardDTO boardDTO = boardDAO.boardSelectByNo(boardNo);
        /*
        if(boardDTO.equals(null)){
            throw new SearchWrongException("해당 게시물의 정보가 없습니다.");
        }

         */
        return boardDTO;
    }

    @Override
    public void boardInsert(BoardDTO boardDTO) throws DMLException {
        int result = boardDAO.boardInsert(boardDTO);
        System.out.println("result:  "+ result);
        if(result == 0){
            throw new DMLException("해당 게시물이 등록되지 않았습니다.");
        }

    }

    @Override
    public void boardUpdate(BoardDTO boardDTO) throws DMLException {
        int result = boardDAO.boardUpdate(boardDTO);
        System.out.println("result:  "+ result);
        if(result == 0){
            throw new DMLException("해당 게시물이 업데이트 되지 않았습니다.");
        }

    }

    @Override
    public void boardDelete(int boardNo) throws DMLException {
        int result = boardDAO.boardDelete(boardNo);
        System.out.println("result:  "+ result);
        if(result == 0){
            throw new DMLException("해당 게시물이 삭제 되지 않았습니다.");
        }


    }

    @Override
    public void replyInsert(ReplyDTO replyDTO) throws DMLException {
        int result = boardDAO.replyInsert(replyDTO);
        System.out.println("result:  "+ result);
        if(result == 0){
            throw new DMLException("해당 댓글이 등록 되지 않았습니다.");
        }

    }

    @Override
    public BoardDTO replySelectByParentNo(int boardNo) throws SearchWrongException {
        BoardDTO boardDTO = boardDAO.replySelectByParentNo(boardNo);
        if(boardDTO == null){
            throw new DMLException("해당 댓글이 조회 되지 않았습니다.");
        }

        return boardDTO;
    }
}
