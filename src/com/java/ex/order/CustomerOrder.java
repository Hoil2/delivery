package com.java.ex.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.business.Business;
import com.java.ex.dao.OrderDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.Session;

public class CustomerOrder extends JFrame{
	Font font = new Font("����", Font.BOLD, 30);
	Font font2 = new Font("����", Font.BOLD, 20);
	Font font3 = new Font("����", Font.BOLD, 15);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	
	
	public CustomerOrder(String m_id) { 
		BusinessDTO bussiness = (BusinessDTO)Session.getSession("business");
		
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,300);
		
		JButton btnBack = new JButton("�ڷΰ���");
		btnBack.setBounds(10, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Business();
			}
		});
		
		setTitle("���ֹ�");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);
		
		
		OrderDAO dao = new OrderDAO();
		ArrayList<Map<String, Object>> CustomerOrderList = dao.OrderList(m_id, bussiness.getId());
		
		for(int i = 0; i < CustomerOrderList.size(); i++) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) CustomerOrderList.get(i);				
			
			String menuname = (String) hashmap.get("menuname");
			String address = (String) hashmap.get("m_address");
			String tel = (String) hashmap.get("m_tel");
			Date odatetime = (Date) hashmap.get("o_datetime");
			String state = (String) hashmap.get("o_state");
			int count = (int) hashmap.get("menu_count");
			int price = (int) hashmap.get("menutotalprice");
			
			JLabel lbl = new JLabel("");
			lbl.setText("�ֹ��� �޴� : " + menuname + " " + "�ּ� : " + address + " " +
			"��ȭ��ȣ : " + tel + " " + "�ֹ� ��¥ : " + odatetime + " " + "��� ���� : " + 
					state + " " + "���� : " + count + " " + "���� : " + price + "��");
			lbl.setBounds(posX, posY +  (i * 50), 300, 50);
			lbl.setFont(font3);
			pane.add(lbl);
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}	
		
		add(scroll);
		add(btnBack);
		setVisible(true);
	
	}
	

}

