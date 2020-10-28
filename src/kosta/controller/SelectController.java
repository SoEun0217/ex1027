package kosta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.dto.Electronics;
import kosta.model.service.ElectronicsService;

public class SelectController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		System.out.println("selectController");
		//예외를 던졌으므로 정상적인 상황만 고려하는중
		
		//서비스호출 -> dao 호출해서 그결과 받아서 이동
	
		List<Electronics>list = ElectronicsService.selectAll();
		request.setAttribute("list", list);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/list.jsp");
		
		return mv;
	}

}
