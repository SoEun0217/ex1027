package kosta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class ReadController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		//�𵨹�ȣ���ޱ�
		String modelNum = request.getParameter("modelNum");
		String flag = request.getParameter("flag");
		boolean state = flag==null?true:false;
		//����ȣ���ϱ�
		Electronics electronics= 
				ElectronicsService.selectByModelNum(modelNum, state);//��ȸ���� �����Ѵ�.
		request.setAttribute("elec", electronics);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/read.jsp");
		return mv;
	}

}
