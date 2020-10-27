package kosta.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.model.dto.Electronics;

public class ElectronicsDAOImpl implements ElectronicsDAO {

	@Override
	public List<Electronics> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Electronics selectByModelNum(String modelNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int increamentByReadnum(String modelNum) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Electronics electronics) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String modelNum, String password) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Electronics electronics) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
