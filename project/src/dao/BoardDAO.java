 package dao;
 
 import java.sql.*;
 import javax.naming.*;
import javax.sql.DataSource;

import vo.BoardVO;

import java.util.*;
 import static db.JdbcUtil.*; 

 public class BoardDAO {
    
	private Connection con;
 	private static BoardDAO instance = new BoardDAO();
    
    public static BoardDAO getInstance() {
        return instance;
    }
    
    private BoardDAO() {
    }
    
    public void setConnection(Connection con) {
    	this.con = con;
    }
    
    private Connection getConnection() throws Exception {
      Connection con = null;
      
     //Java Naming and Directory Interface
     // 자바 플랫폼에서 자원에 명칭을 부여하는 기능을 제공하는 인터페이스
      Context initCtx = new InitialContext();
      // 톰켓 자체의 컨텍스틑 얻어옴
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      // Resource 정의 컨텍스를 얻어옴 
      DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
      con = ds.getConnection();
      con.setAutoCommit(false);
  
      return con;
    }
 
    public int insertArticle(BoardVO article) 
    throws Exception {
    	int insertCount  = 0;
        PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 답변글 처리에 필요한 값들을 변수에 저장
		int num=article.getNum();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		// 관련글 번호를 저장할 변수 
		// 원글을 작성했을 때 관련글 번호를 새로 생성할 것임
		int number=0;
        String sql="";

        try {

            // 새로운 관련글 번호를 구하는 SQL 구문
            pstmt = con.prepareStatement
            		("select max(num) from board");
			rs = pstmt.executeQuery();
			
			// 이미 다른글이 작성되어있다면!
			// 지금 등록하는 글이 첫 번째 글이 아니면
			if (rs.next())
		      number=rs.getInt(1)+1;
			// 지금 작성한 글이 첫번째이면! => 글의 목록을 1로 준다.
		    else
		      number=1; 
		   
			// 원글 쓰는것이 아니라, 답변 글을 쓸때!
		    if (num!=0)   
		    {  
		      sql="update board set re_step=re_step+1 " +
		      		"where ref= ? and re_step> ?";
              pstmt = con.prepareStatement(sql);
              pstmt.setInt(1, ref);
			  pstmt.setInt(2, re_step);
			  pstmt.executeUpdate();
			  
			  // 지금 등록하는 글의 값 구하기 - 자신 
			  re_step=re_step+1;
			  re_level=re_level+1;
		     }
		    // 현재 작성하는게 원글이면! 왜냐하면, 0이니까! 
		    else{
		  	  ref=number;
			  re_step=0;
			  re_level=0;
		     }	 
            // ������ �ۼ�
            sql = "insert into board" +
           "(num,writer,email,subject,passwd,reg_date,";
            sql+="ref,re_step,re_level,content,ip) " +
            "values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getEmail());
            pstmt.setString(3, article.getSubject());
            pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getReg_date());
            pstmt.setInt(6, ref);
            pstmt.setInt(7, re_step);
            pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
            insertCount = pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			close(rs);
			close(pstmt);
        }
        
        return insertCount;
    }
    
	public int selectArticleCount()
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs =null;

        int articleCount = 0;

        try {
            con = getConnection();
            
            pstmt = con.prepareStatement
            		("select count(*) from board");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               articleCount= rs.getInt(1);
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	close(rs);
        	close(pstmt);
        }
		return articleCount;
    }

	public List selectArticleList(int start, int end)
    throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        List articleList=null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            
            pstmt = con.prepareStatement(
			"select list2.* from(select rownum r, list1.*  " +
			"from(select *  from board order by ref desc, re_step asc)list1) " +
			"list2 where r between ? and ?");
            pstmt.setInt(1, start);
			pstmt.setInt(2, start+end-1);
            rs = pstmt.executeQuery();
    /*    try {
         conn  = getConnection( );
            stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
            String query ="select * from board order by ref desc, re_step asc";
            rs = stmt.executeQuery( query );

      int skip  = start - 1;

      if( skip > 0 )
        rs.absolute( skip );
        */
                if (rs.next()) {
                int i=0;
                articleList = new ArrayList(end);
                do{
                  BoardVO article= new BoardVO();
      article.setNum(rs.getInt("num"));
      article.setWriter(rs.getString("writer"));
                  article.setEmail(rs.getString("email"));
                  article.setSubject(rs.getString("subject"));
                  article.setPasswd(rs.getString("passwd"));
         article.setReg_date(rs.getTimestamp("reg_date"));
      article.setReadcount(rs.getInt("readcount"));
                  article.setRef(rs.getInt("ref"));
                  article.setRe_step(rs.getInt("re_step"));
      article.setRe_level(rs.getInt("re_level"));
                  article.setContent(rs.getString("content"));
         article.setIp(rs.getString("ip")); 
      
                  articleList.add(article);
                  i++;
       }while(rs.next()&& i<end);
   }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
           close(rs);
           close(pstmt);
        }
  return articleList;
    }

 
    public BoardVO selectArticle(int num)
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardVO article=null;
        try {
            pstmt = con.prepareStatement(
            	"update board set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

            pstmt = con.prepareStatement(
            	"select * from board where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new BoardVO();
                article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
                article.setEmail(rs.getString("email"));
                article.setSubject(rs.getString("subject"));
                article.setPasswd(rs.getString("passwd"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
                article.setRef(rs.getInt("ref"));
                article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
                article.setContent(rs.getString("content"));
			    article.setIp(rs.getString("ip"));     
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	close(rs);
        	close(pstmt);
        }
		return article;
    }

    public BoardVO selectUpdateArticle(int num)
    throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardVO article=null;
        try {
            con = getConnection();

            pstmt = con.prepareStatement(
            	"select * from board where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new BoardVO();
                article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
                article.setEmail(rs.getString("email"));
                article.setSubject(rs.getString("subject"));
                article.setPasswd(rs.getString("passwd"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
                article.setRef(rs.getInt("ref"));
                article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
                article.setContent(rs.getString("content"));
			    article.setIp(rs.getString("ip"));     
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
           close(rs);
           close(pstmt);
        }
		return article;
    }

    public int updateArticle(BoardVO article)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;

        String dbpasswd="";
        String sql="";
		int x=-1;
        try {
            conn = getConnection();
            
			pstmt = conn.prepareStatement(
            	"select passwd from board where num = ?");
            pstmt.setInt(1, article.getNum());
            rs = pstmt.executeQuery();
            
			if(rs.next()){
			  dbpasswd= rs.getString("passwd"); 
			  if(dbpasswd.equals(article.getPasswd())){
                sql="update board set writer=?,email=?,subject=?,passwd=?";
			    sql+=",content=? where num=?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, article.getWriter());
                pstmt.setString(2, article.getEmail());
                pstmt.setString(3, article.getSubject());
                pstmt.setString(4, article.getPasswd());
                pstmt.setString(5, article.getContent());
			    pstmt.setInt(6, article.getNum());
                pstmt.executeUpdate();
				x= 1;
			  }else{
				x= 0;
			  }
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
    
    public int deleteArticle(int num, String passwd)
    throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        try {
			conn = getConnection();

            pstmt = conn.prepareStatement(
            	"select passwd from board where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
			if(rs.next()){
				dbpasswd= rs.getString("passwd"); 
				if(dbpasswd.equals(passwd)){
					pstmt = conn.prepareStatement(
            	      "delete from board where num=?");
                    pstmt.setInt(1, num);
                    pstmt.executeUpdate();
					x= 1; //�ۻ��� ����
				}else
					x= 0; //��й�ȣ Ʋ��
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		return x;
    }
 }