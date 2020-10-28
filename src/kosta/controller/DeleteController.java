package kosta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.service.ElectronicsService;

public class DeleteController implements Controller {

	@Override
	public ModelAndView handleRequset(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String modelNum = request.getParameter("modelNum");
		String password = request.getParameter("password");
		//세번째 인수 path 
		String path =request.getServletContext().getRealPath("/save");
		ElectronicsService.delete(modelNum, password,path);
		ModelAndView mv = new ModelAndView("elec",true);
		return mv;
	}

}
