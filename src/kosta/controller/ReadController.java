package kosta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class ReadController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		//모델번호를받기
		String modelNum = request.getParameter("modelNum");
		String flag = request.getParameter("flag");
		boolean state = flag==null?true:false;
		//서비스호출하기
		Electronics electronics= 
				ElectronicsService.selectByModelNum(modelNum, state);//조회수가 증가한다.
		request.setAttribute("elec", electronics);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/read.jsp");
		return mv;
	}

}
