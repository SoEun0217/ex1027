package kosta.model.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import kosta.model.dao.ElectronicsDAO;
import kosta.model.dao.ElectronicsDAOImpl;
import kosta.model.dto.Electronics;

public class ElectronicsService {

	private static ElectronicsDAO elecDAO  = new ElectronicsDAOImpl();
	
	/**
	 * ElectronicsDAOImpl의 모든레코드 검색하는 메소드 호출
	 * */
	public static List<Electronics> selectAll() throws SQLException{
		List<Electronics>list = elecDAO.selectAll();
		/*
		 * if(list.size()==0) { throw new SQLException("검색된 레코드가 없습니다..."); }
		 */
		return list;
	}
	
 
	  /**
	   * ElectronicsDAOImpl의 레코드 삽입하는 메소드 호출
	   * */
	public static int insert(Electronics elec) throws SQLException{
		int result = elecDAO.insert(elec);
		if(result==0) throw new SQLException("등록되지 않았습니다.");
		
		return result;
	}
	  
	  /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 검색하는 메소드 호출
	   * @param : boolean flag - 조회수 증가 여부를 판별하는 매개변수임(true이면 조회수증가 / false이면 조회수 증가 안함)
	   * */
	public static Electronics selectByModelNum(String modelNum, boolean flag) throws SQLException{
		if(flag) {
			int result = elecDAO.increamentByReadnum(modelNum);
			if(result==0) throw new SQLException("조회수 증가에 오류가 발생했다.");
		}
		
		Electronics electronics = elecDAO.selectByModelNum(modelNum);
		if(electronics==null)throw new SQLException(modelNum+"해당하는 정보가 없습니다.");
		return electronics;
	}
	
		
	 /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 삭제 메소드 호출
	   * */
	  public static int delete(String modelNum, String password,String path)throws SQLException{
		  Electronics dbElec = elecDAO.selectByModelNum(modelNum);
		  if(!dbElec.getPassword().equals(password)) {
			  throw new SQLException("비밀번호가 일치하지 않습니다.");
		  }
		  int result= elecDAO.delete(modelNum, password);
		  if(result==0)throw new SQLException("삭제되지않았습니다.");
		  //삭제 완료후에 파일에 파일 삭제하고싶다
		  if(dbElec.getFname()!=null) {
			  new File(path,dbElec.getFname()).delete();
		  }
		  return result ;
	  }
	  

	  
	  /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 수정  메소드 호출
	   * */
	  public static int update(Electronics electronics) throws SQLException{
		  Electronics dbElec = 
				  elecDAO.selectByModelNum(electronics.getModelNum());
		  
		  //수정하기 전에 비밀번호 먼저 체ㅡ한다.
		  if(!dbElec.getPassword().equals(electronics.getPassword())) {
			  throw new SQLException("비밀번호가 일치하지 않습니다.");
		  }
		  
		  int result = elecDAO.update(electronics);
		  if(result ==0)throw new SQLException("업데이트되지 않았습니다.");
		  return result;
	  }
	
	  
	  
}



