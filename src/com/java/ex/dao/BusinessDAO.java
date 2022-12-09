package com.java.ex.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.java.ex.db.DBConnection;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;

public class BusinessDAO extends DBConnection {
		
	public BusinessDTO selectBusiness(String id) {
		query = "select * from business where b_id='"+id+"'";
		BusinessDTO dto = null;
		try {
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos; 
	}
	
	public void bDelete(String b_id) {
		query = "delete from business where b_id='" + b_id + "'";
		
		try {
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void signUpBusiness(BusinessDTO dto) {
		query = "insert into business value(?,?,?,?,?,?)";
		
		try {
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateBusiness(BusinessDTO dto) {
		query = "update business set b_pw = ?, b_name = ?, b_tel = ?, b_address = ? where b_id = ?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getTel());
			pstmt.setString(4, dto.getAddress());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeUpdate();
			 
			System.out.println("ȸ�� ���� ���� ����");
		} catch(SQLException ex) {
			System.out.println("���� ��ddddd��");
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public  ArrayList<Map<String,Object>> orderList(String m_id) {
		query = "select m_id, menuname, (menuprice*menu_count) as menutotalprice, menu_count, m_address, o_datetime, o_state from order o, baguni b, member m where b.menu_no = o.menu_no AND b.m_id = m.m_id";
		ArrayList<Map<String,Object>> payBaguniList = new ArrayList<Map<String,Object>>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {		
				//String m_id = rs.getString("m_id");			
				String menuname = rs.getString("menuname");
				int menuprice = rs.getInt("menutotalprice");
				int menu_count = rs.getInt("menu_count");
								
				Map map = new HashMap<String, Object>();
				//map.put("m_id",m_id);
				map.put("menuname", menuname);
				map.put("menutotalprice", menuprice);
				map.put("menu_count", menu_count);
				payBaguniList.add(map);
				System.out.println("����");
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return payBaguniList; 
	}
}