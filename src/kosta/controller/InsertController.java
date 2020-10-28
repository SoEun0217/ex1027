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
		//���۵Ǵ� ������ �ޱ� upload������Ʈ���� uploadServlet ����
		String saveDir=request.getServletContext().getRealPath("/save");//realPath�� ���������� ����Ǵ� ��
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
		//���� ÷���ΰ��...
		if(m.getFilesystemName("file")!=null) {
			electronice.setFname(m.getFilesystemName("file"));
			electronice.setFsize((int)m.getFile("file").length());
		}
		//insert�Ϸ�����
		ElectronicsService.insert(electronice);
		
		ModelAndView mv = new ModelAndView("elec",true);
		//�����ϸ� list.jsp �̵�
		return mv;
		
	}

}
