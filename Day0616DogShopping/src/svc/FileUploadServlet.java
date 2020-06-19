package svc;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class FileUploadServlet
 */
@MultipartConfig
@WebServlet("/upload.do")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		// Part Interface : 업로드를 처리해주는 API
		Part part = request.getPart("attachment");
		ServletContext context = getServletContext();
		String realPath = context.getRealPath("/image");
		
		// 업로드를 제출된 파일이름
		String fileName = part.getSubmittedFileName();
		
		part.write(realPath + "/"  +fileName);
		System.out.println(realPath + '/' + fileName);
		
		response.sendRedirect("partUploadForm.jsp");
		
	}

}
