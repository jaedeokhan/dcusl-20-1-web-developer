package dao;
 
import java.sql.*;
import vo.DogVO;
import java.util.*;
import static db.JdbcUtil.*; 

 public class DogDAO {
    
	private Connection con;
 	private static DogDAO instance = new DogDAO();
    
    public static DogDAO getInstance() {
        return instance;
    }
    
    private DogDAO() {
    }
    
    public void setConnection(Connection con) {
    	this.con = con;
    }

	public ArrayList<DogVO> selectDogList() {
		
		ArrayList<DogVO> dogList = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM dog";
		
		
		try {
		    pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dogList = new ArrayList<DogVO>();
				DogVO dogVO = null;
				do {
					dogVO = new DogVO();
					dogVO.setId(rs.getInt("id"));
					dogVO.setContent(rs.getString("content"));
					dogVO.setCountry(rs.getString("country"));
					dogVO.setHeight(rs.getInt("height"));
					dogVO.setImage(rs.getString("image"));
					dogVO.setKind(rs.getString("kind"));
					dogVO.setPrice(rs.getInt("price"));
					dogVO.setReadCount(rs.getInt("readcount"));
					dogVO.setWeight(rs.getInt("weight"));
					dogList.add(dogVO);
				} while(rs.next());
			}
		    
		    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dogList;
	}

	public DogVO selectDogVO(String id) {
	
		DogVO dogVO = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM dog WHERE id=?";
		
		
		try {
		    pstmt = con.prepareStatement(sql);
		    pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dogVO = new DogVO();
				dogVO.setId(rs.getInt("id"));
				dogVO.setContent(rs.getString("content"));
				dogVO.setCountry(rs.getString("country"));
				dogVO.setHeight(rs.getInt("height"));
				dogVO.setImage(rs.getString("image"));
				dogVO.setKind(rs.getString("kind"));
				dogVO.setPrice(rs.getInt("price"));
				dogVO.setReadCount(rs.getInt("readcount"));
				dogVO.setWeight(rs.getInt("weight"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return dogVO;
	}

	public int updateReadCount(String id) {
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "UPDATE dog SET readcount = readcount + 1 WHERE id=?";
		
		try {
		    pstmt = con.prepareStatement(sql);
		    pstmt.setInt(1, Integer.parseInt(id));
			updateCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return updateCount;
	}
  
 }
 