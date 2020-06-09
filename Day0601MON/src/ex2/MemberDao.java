package ex2;

import java.util.List;

public interface MemberDao {
   Member exist(String email, String pw) throws Exception;
   List<Member> selectList() throws Exception;
   void Insert(Member member) throws Exception;
   Member UpdateSelect(String no) throws Exception;
   void Update(Member member) throws Exception;
   void Delete(String no) throws Exception;
}
