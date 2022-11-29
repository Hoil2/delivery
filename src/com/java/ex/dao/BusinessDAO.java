package com.java.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;

public class BusinessDAO {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://localhost:3306/delivery";
	static String uid = "root";
	static String pwd = "1234";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private String query = null;
	
	public BusinessDAO() {
		try {
			Class.forName(driver);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public BusinessDTO selectBusiness(String id) {
		query = "select * from business where b_id='"+id+"'";
		BusinessDTO dto = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()==true) {
			
				String pw = rs.getString("b_pw");
				String name = rs.getString("b_name");
				String tel = rs.getString("b_tel");
				String address = rs.getString("b_address");
				Date reg_date = rs.getDate("b_reg_date");
			
				dto = new BusinessDTO(id, pw, name,  tel, address, reg_date);
			} 
			
		}catch(SQLException ex) {
			System.out.println("���� ����");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public ArrayList<BusinessDTO> selectAllBusiness() {
		query = "select * from business";
		ArrayList<BusinessDTO> dtos = new ArrayList<BusinessDTO>();
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				String id = rs.getString("b_id");
				String pw = rs.getString("b_pw");
				String name = rs.getString("b_name");
				String tel = rs.getString("b_tel");
				String address = rs.getString("b_address");
				Date reg_date = rs.getDate("b_reg_date");
				
				BusinessDTO dto = new BusinessDTO(id, pw, name, tel, address, reg_date);
				dtos.add(dto);
			}
		} catch(SQLException ex) {
			System.out.println("���� ����");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos; 
	}
	
	public void mDelete(MemberDTO dto) {
		query = "delete from business where b_id='" + dto.getId() + "'";
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			int result = stmt.executeUpdate(query);
			if(result == 1) 
				System.out.println("���� ����");
			else System.out.println("���� ����");
		} catch(SQLException ex) {
			System.out.println("���� ����");
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void signUpBusiness(BusinessDTO dto) {
		query = "insert into business value(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getAddress());
			pstmt.setDate(6, dto.getReg_date());
			pstmt.executeUpdate();
			 
			System.out.println("ȸ������ ����");
			
		} catch(SQLException ex) {
			System.out.println("���� ����");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateBusiness(BusinessDTO dto) {
		query = "update business set b_pw = ?, b_name = ?, b_tel = ?, b_address = ?, where b_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeUpdate();
			 
			System.out.println("ȸ�� ���� ���� ����");
		} catch(SQLException ex) {
			System.out.println("���� ����");
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}