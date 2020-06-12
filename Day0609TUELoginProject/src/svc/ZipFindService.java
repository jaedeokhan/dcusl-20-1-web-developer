package svc;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.LoginDAO;
import vo.ZipCodeVO;


public class ZipFindService {

	public ArrayList<ZipCodeVO> getZipcodeList(String ro) {
		
		Connection con = getConnection();
		LoginDAO loginDAO = LoginDAO.getInstance();
		loginDAO.setConnection(con);
		
		ArrayList<ZipCodeVO> zipCodeList = loginDAO.selectZipCodeList(ro);
		
		// 항상 Service 단에서 트랜잭션 때문에 con을 닫아줘야한다.
		close(con);
		
		return zipCodeList;
	}
}
