package app.mvc.dao;

import app.mvc.common.DBManager;
import app.mvc.dto.BoardDTO;
import app.mvc.dto.ReplyDTO;
import app.mvc.exception.DMLException;
import app.mvc.exception.SearchWrongException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements BoardDAO{

    private static BoardDAO instance = new BoardDAOImpl();

    public static BoardDAO getInstance() {
        return instance;
    }

    @Override
    public List<BoardDTO> boardSelectAll() throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<BoardDTO> list = new ArrayList<>();

        String sql = "select * from board order by board_no desc";
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);

            // ?가 있다면 set 설정 필요

            // db쿼리를 전송 - executexxx()
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new BoardDTO(rs.getInt("board_no"), rs.getString("subject"), rs.getString("writer"), rs.getString("content"), rs.getString("board_date")));

            }

        }catch (SQLException e){
            e.printStackTrace(); // 무슨 오류가 났는지 확인용 (개발할때만 넣기)
            throw new SearchWrongException("전체 게시물에 오류가 발생했습니다! 잠시후 다시 이용해주세요");

        }finally {
            DBManager.releaseConnection(con, ps, rs); // 이 때 만 연결 끊음

        }
        return list;
    }

    @Override
    public List<BoardDTO> boardSelectBySubject(String keyWord) throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from board where subject like ?";
        List<BoardDTO> list = new ArrayList<>();

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, keyWord);

            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("subject").equals(keyWord)){ // 포함되는걸로 수정
                    list.add(new BoardDTO(rs.getInt("board_no"), rs.getString("subject"), rs.getString("writer"), rs.getString("content"), rs.getString("board_date")));
                }
            }

        }catch (SearchWrongException e){
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(con, ps, rs);

        }

        return list;
    }

    @Override
    public BoardDTO boardSelectByNo(int boardNo) throws SearchWrongException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "select * from board where board_no = ? ";

        BoardDTO boardDTO = null;
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boardNo);
            result = ps.executeUpdate();

            // ?가 있다면 set 설정 필요

            // db쿼리를 전송 - executexxx()
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("board_no") == boardNo){
                    boardDTO = new BoardDTO(rs.getInt("board_no"), rs.getString("subject"), rs.getString("writer"), rs.getString("content"), rs.getString("board_date"));

                }
            }

        }catch (SQLException e){
            e.printStackTrace(); // 무슨 오류가 났는지 확인용 (개발할때만 넣기)
            throw new SearchWrongException("전체 게시물에 오류가 발생했습니다! 잠시후 다시 이용해주세요");

        }finally {
            DBManager.releaseConnection(con, ps, rs); // 이 때 만 연결 끊음

        }
        return boardDTO;
    }

    @Override
    public int boardInsert(BoardDTO boardDTO) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "insert into board (board_no, subject, writer, content, board_date) values (board_seq.nextval, ?, ?, ?, sysdate)";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDTO.getSubject());
            ps.setString(2, boardDTO.getWriter());
            ps.setString(3, boardDTO.getContent());
            result = ps.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace(); // 무슨 오류가 났는지 확인용 (개발할때만 넣기)
            throw new SearchWrongException("전체 게시물에 오류가 발생했습니다! 잠시후 다시 이용해주세요");

        }finally {
            DBManager.releaseConnection(con, ps, rs); // 이 때 만 연결 끊음

        }
        return result;
    }

    @Override
    public int boardUpdate(BoardDTO boardDTO) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "update board set content = ? where board_no = ?";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDTO.getContent());
            ps.setInt(2, boardDTO.getBoardNo());
            result = ps.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace(); // 무슨 오류가 났는지 확인용 (개발할때만 넣기)
            throw new SearchWrongException("전체 게시물에 오류가 발생했습니다! 잠시후 다시 이용해주세요");

        }finally {
            DBManager.releaseConnection(con, ps, rs); // 이 때 만 연결 끊음

        }
        return result;
    }

    @Override
    public int boardDelete(int boardNo) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        int result = 0;
        String sql = "delete from board where board_no = ?";
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boardNo);

            result = ps.executeUpdate();
        }catch (SQLException e){
            throw new DMLException("게시물 삭제에 오류 발생, 다시 이용해주세요.");

        }finally {
            DBManager.releaseConnection(con, ps);
        }

        return result;
    }

    @Override
    public int replyInsert(ReplyDTO replyDTO) throws DMLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "insert into reply values(reply_no_seq.nextval , ?, ? , sysdate)";

        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, replyDTO.getReplyContent());
            ps.setInt(2, replyDTO.getBoardNo());
            result = ps.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace(); // 무슨 오류가 났는지 확인용 (개발할때만 넣기)
            throw new SearchWrongException("전체 게시물에 오류가 발생했습니다! 잠시후 다시 이용해주세요");

        }finally {
            DBManager.releaseConnection(con, ps, rs); // 이 때 만 연결 끊음

        }
        return result;
    }

    @Override
    public BoardDTO replySelectByParentNo(int boardNo) throws SearchWrongException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;

        String sql = "select * from board join reply using(board_no)  where board_no=?";

        BoardDTO boardDTO = null;
        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boardNo);
            result = ps.executeUpdate();

            // ?가 있다면 set 설정 필요

            // db쿼리를 전송 - executexxx()
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getInt("board_no") == boardNo){
                    boardDTO = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                    // 댓글정보 가져오기
                    List<ReplyDTO> repliesList = this.replySelect(con, boardNo);
                    boardDTO.setRepliesList(repliesList); // 부모글 안에 댓글 넣기

                }
            }

        }catch (SQLException e){
            e.printStackTrace(); // 무슨 오류가 났는지 확인용 (개발할때만 넣기)
            throw new SearchWrongException("댓글 정보 검색시 오류가 발생했습니다! 잠시후 다시 이용해주세요");

        }finally {
            DBManager.releaseConnection(con, ps, rs); // 이 때 만 연결 끊음

        }
        return boardDTO;
    }

    /***
     * 부모글에 해당하는 댓글 정보 가져오기
     */
    private List<ReplyDTO> replySelect(Connection con, int boardNo){

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ReplyDTO> list = new ArrayList<>();

        String sql = "select * from reply where board_no=?";

        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, boardNo);

            rs = ps.executeQuery();

            while(rs.next()){
                ReplyDTO reply = new ReplyDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                list.add(reply);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.releaseConnection(null, ps, rs);

        }
        return list;

    }

}
