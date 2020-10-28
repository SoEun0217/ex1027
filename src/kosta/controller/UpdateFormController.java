package kosta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class UpdateFormController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		//수정을 하기 위해 폼을 띄우기....-모델번호에 해당하는 정보 가져오기,조회수는 오르지 않는다.
		String modelNum = request.getParameter("modelNum");
		Electronics electronics = ElectronicsService.selectByModelNum(modelNum, false);
		request.setAttribute("elec", electronics);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/update.jsp");
		return mv;
		
	}

}
