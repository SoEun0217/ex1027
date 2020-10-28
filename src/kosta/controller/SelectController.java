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
		//���ܸ� �������Ƿ� �������� ��Ȳ�� ����ϴ���
		
		//����ȣ�� -> dao ȣ���ؼ� �װ�� �޾Ƽ� �̵�
	
		List<Electronics>list = ElectronicsService.selectAll();
		request.setAttribute("list", list);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("elecView/list.jsp");
		
		return mv;
	}

}
