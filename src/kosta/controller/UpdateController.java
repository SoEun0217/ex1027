package kosta.controller;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class UpdateController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//���۵� ������ �ޱ�
		
		String modelNum=request.getParameter("modelNum"); 
		String modelName=request.getParameter("model_name"); 
		String price=request.getParameter("price"); 
		String description=request.getParameter("description");
		String password = request.getParameter("password");
		Electronics electronics = 
				new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		//update�����Ѵ�.
		ElectronicsService.update(electronics);
		ModelAndView mv = new ModelAndView();
		//���������� read.jsp�̵��Ѵ�. - �𵨹�ȣ�ش��ϴ� ���� �����ͼ� �̵�
		/*
		 * Electronics elec = ElectronicsService.selectByModelNum(modelNum, false);
		 * request.setAttribute("elec", elec); mv.setViewName("elecView/read.jsp");
		 */
		
		mv.setViewName("elec?command=read&modelNum="+URLEncoder.encode(modelNum,"UTF-8")+"&flag=1");
		mv.setRedirect(true);
		
		return mv;
	}

}
