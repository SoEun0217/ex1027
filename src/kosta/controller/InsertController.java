package kosta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class InsertController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송되는 데이터 받기 upload프로젝트에서 uploadServlet 참조
		String saveDir=request.getServletContext().getRealPath("/save");//realPath는 실제파일이 저장되는 곳
		int maxSize=1024*1024*100;//100M
		String encoding="UTF-8";
		
		MultipartRequest m= new MultipartRequest(request, saveDir,maxSize,encoding,new DefaultFileRenamePolicy());
		
		String modelNum=m.getParameter("model_num"); 
		String modelName=m.getParameter("model_name"); 
		String price=m.getParameter("price"); 
		String description=m.getParameter("description");
		String password = m.getParameter("password");
		
		Electronics electronice = 
				new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		//파일 첨부인경우...
		if(m.getFilesystemName("file")!=null) {
			electronice.setFname(m.getFilesystemName("file"));
			electronice.setFsize((int)m.getFile("file").length());
		}
		//insert하러가기
		ElectronicsService.insert(electronice);
		
		ModelAndView mv = new ModelAndView("elec",true);
		//성공하면 list.jsp 이동
		return mv;
		
	}

}
