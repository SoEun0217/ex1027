package kosta.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.model.dto.Electronics;
import kosta.util.DBUtil;

public class ElectronicsDAOImpl implements ElectronicsDAO {

	@Override
	public List<Electronics> selectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql ="select model_num,model_name,price, description,password , to_char(writeday, 'YYYY-MM-DD HH:MI:SS AM') as writeday ,readnum,fname,fsize from Electronics";
		ResultSet rs = null;
		List<Electronics>list = new ArrayList<Electronics>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String modelNum = rs.getString(1);
				String modelName = rs.getString(2);
				int price = rs.getInt(3);
				String description = rs.getString(4);
				String passoword = rs.getString(5);
				String writeday = rs.getString(6);
				int readnum = rs.getInt(7);
				String fname = rs.getString(8);
				int fsize = rs.getInt(9);
				list.add(new Electronics(modelNum,modelName,price,description,passoword,writeday,readnum,fname,fsize));
			}
		} finally {//캐치없다 예외던져서..
			DBUtil.dbClose(rs, ps, con);
		}
		return list;
	}

	@Override
	public Electronics selectByModelNum(String modelNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		Electronics elec = null;
		ResultSet rs = null;
		String sql = "select model_num,model_name,price, description,password , to_char(writeday, 'YYYY-MM-DD HH:MI:SS AM')"
				+ " as writeday ,readnum,fname,fsize from Electronics where model_num=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				modelNum = rs.getString(1);
				String modelName = rs.getString(2);
				int price = rs.getInt(3);
				String description = rs.getString(4);
				String passoword = rs.getString(5);
				String writeday = rs.getString(6);
				int readnum = rs.getInt(7);
				String fname = rs.getString(8);
				int fsize = rs.getInt(9);
				elec = new Electronics(modelNum,modelName,price,description,passoword,writeday,readnum,fname,fsize);
			}
		} finally {
			DBUtil.dbClose(rs, ps, con);
		}
		return elec;
	}

	@Override
	public int increamentByReadnum(String modelNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "update electronics set readnum = readnum+1 where model_num=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public int insert(Electronics electronics) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "insert into Electronics values(?,?,?,?,?,sysdate,0,?,?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, electronics.getModelNum());
			ps.setString(2,electronics.getModelName());
			ps.setInt(3, electronics.getPrice());
			ps.setString(4,electronics.getDescription());
			ps.setString(5,electronics.getPassword());
			ps.setString(6,electronics.getFname());
			ps.setInt(7,electronics.getFsize());
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	public int delete(String modelNum, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps= null;
		int result = 0;
		String sql = "delete from electronics where model_num=? and password=? ";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, modelNum);
			ps.setString(2,password);
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(ps, con);
		}
		return result;
	}

	@Override
	//(모델이름, 가격, 내용)
	public int update(Electronics electronics) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "update electronics set model_name=?, price =?,description =? where model_num=? and password=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, electronics.getModelName());
			ps.setInt(2,electronics.getPrice());
			ps.setString(3, electronics.getDescription());
			ps.setString(4, electronics.getModelNum());
			ps.setString(5, electronics.getPassword());
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(ps, con);
		}
		return result;
	}

}
