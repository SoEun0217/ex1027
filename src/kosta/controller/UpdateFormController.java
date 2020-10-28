package kosta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class UpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		//������ �ϱ� ���� ���� ����....-�𵨹�ȣ�� �ش��ϴ� ���� ��������,��ȸ���� ������ �ʴ´�.
		String modelNum = request.getParameter("modelNum");
		Electronics electronics = ElectronicsService.selectByModelNum(modelNum, false);
		request.setAttribute("elec", electronics);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/update.jsp");
		return mv;
		
	}

}
