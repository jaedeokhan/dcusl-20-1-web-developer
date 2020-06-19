package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.DogRegistService;
import vo.ActionForward;
import vo.DogVO;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// cos.jar을 이용해서 정의
		String realFolder = ""; // 파일이 업로드 될 서버상의 물리적인 경로 
		String saveFolder = "image"; // 파일이 업로드 될 상대경로
		String encType = "UTF-8"; // 파일이름의 한글 처리 => 한글이 안깨지게!
		int maxSize = 5 * 1024 * 1024; // 5MB
		
		// 애플리케이션 하나 당 할당되는 컨텍스트(환경설정)
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = 
				new MultipartRequest(request, realFolder, maxSize, encType,
						             new DefaultFileRenamePolicy());
		// RenamePolicy => 1.zip , 2.zip, 3.zip 이런 형식으로 이름을 배정한다.
		
		// Client에서 데이터를 받아온다.
		String kind = multi.getParameter("kind");
		int price = Integer.parseInt(multi.getParameter("price"));
		// image는 객체로 받아야한다.
		String image = multi.getFilesystemName("image");
		String country = multi.getParameter("country");
		int height = Integer.parseInt(multi.getParameter("height"));
		int weight = Integer.parseInt(multi.getParameter("weight"));
		String content = multi.getParameter("content");
		
		// Client의 데이터를 객체에 모두 담는다.
		DogVO dogVO = new DogVO();
		dogVO.setKind(kind);
		dogVO.setPrice(price);
		dogVO.setImage(image);
		dogVO.setCountry(country);
		dogVO.setHeight(height);
		dogVO.setWeight(weight);
		dogVO.setContent(content);
		
		DogRegistService dogRegistService = new DogRegistService();
		
		boolean registSuccess = dogRegistService.registDog(dogVO);
		ActionForward forward = null;
		
		if (registSuccess) {
		   forward = new ActionForward();
		   forward.setRedirect(true);
		   forward.setUrl("dogList.dog");
		}
		else {
		   response.setContentType("text/html; charset=UTF-8");
		   PrintWriter out = response.getWriter();
		   out.println("<script>");
		   out.println("alert('등록실패')");
		   out.println("history.back()");
		   out.println("</script>");
		}
		
		return forward;
	}

}
