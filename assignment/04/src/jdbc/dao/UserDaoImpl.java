package jdbc.dao;

import jdbc.common.DBManager;
import jdbc.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO{

    /**
     * 등록
     * */
    public int insert(UserDto userDto){
        int result = 0;

        Connection con = null;
        PreparedStatement ps = null;
        String sql = "insert into user_dto(usreSeq, name, email, phone) values (?,?,?,?)";

        try{
            con = DBManager.getConnection(); // 연결
            ps = con.prepareStatement(sql);
            ps.setInt(1, userDto.getUserSeq());
            ps.setString(2, userDto.getName());
            ps.setString(3, userDto.getEmail());
            ps.setString(4, userDto.getPhone());


            // DB에 쿼리 전송
            result = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBManager.releaseConnection(con, ps);

        }

        System.out.println("insert 완료");

        return result;
    }

    /**
     * 수정
     * */
    public int update(UserDto userDto){
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update set into user_dto(usreSeq, name, email, phone, isSleep) values (?,?,?,?,?)";


        try{
            con = DBManager.getConnection();
            ps = con.prepareStatement(sql);
            // ?가 있다면 ?의 개수만큼 setType(?index, 값) 설정 -> set 4번
            ps.setInt(1, userDto.getUserSeq());
            ps.setString(2, userDto.getName());
            ps.setString(3, userDto.getPhone());
            ps.setBoolean(4, userDto.isSleep());
            if(userDto.isSleep()){
                ps.setString(5, "Y");
            }else{
                ps.setString(5, "N");
            }

            // DB에 쿼리 전송
            result = ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();


        }finally {
            DBManager.releaseConnection(con, ps);

        }

        return result;

    }

    /**
     * 삭제
     * */
    public int delete(int userSeq){
        return 0;
    }

    /**
     * 전체검색
     * */
    public List<UserDto> selectAll() throws SQLException {
        // 로드 연결 실행 닫기
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT userSeq, name, email, phone, isSleep FROM user_dto";

        List<UserDto> list = new ArrayList<>();
        try{
            conn = DBManager.getConnection(); // 연결
            ps = conn.prepareStatement(sql);
            System.out.println("연결 성공");
            rs = ps.executeQuery();

        }catch(SQLException e){

            // DB에 쿼리를 전송한다. - excuteUpdate() or excuteQuery()
            if(conn != null){
                System.out.println("성공");
            }else{
                System.out.println("실패");
            }

            rs = ps.executeQuery();

            while(rs.next()) { // 내려갈게 없으면 false, 이수면 t
                // 현재 행의 열의 정보(컬럼 정보)를 가져온다. (조회)
                int userSeq = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                boolean isSleep = rs.getBoolean(5);

                list.add(new UserDto(userSeq, name, email, phone, isSleep));

            }


        }finally {
            // 닫기
            DBManager.releaseConnection(conn, ps, rs);

        }
        return list;

    }

    /**
     * userSeq에 해당하는 회원검색
     * */
    public UserDto selectOne(int userSeq){
        return null;
    }
}
