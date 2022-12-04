package com.java.ex.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.business.Business;
import com.java.ex.dao.OrderDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MenuDTO;
import com.java.ex.dto.OrderDTO;
import com.java.ex.dto.Session;

public class CustomerOrder extends JFrame{
	Font font = new Font("����", Font.BOLD, 30);
	Font font2 = new Font("����", Font.BOLD, 20);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	
	
	public CustomerOrder() { //Customer���� dtos2.getId()���� �޾ƿ�
		BusinessDTO business = (BusinessDTO)Session.getSession("business");
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
		ArrayList<OrderDTO> dtos = new ArrayList<OrderDTO>();
		
		dtos = dao.selectAllOrder(business.getId());
		
		for(int i = 0; i < dtos.size(); i++) {
			OrderDTO order = dtos.get(i);				
			
			String mid = order.getM_id();
			JLabel lbl = new JLabel(order.getM_id());
			lbl.setBounds(posX, posY + (i * 50), 300, 50);
			lbl.setFont(font);
			//String menuPrice = Integer.toString(menu.getMenuprice());
			//JLabel price = new JLabel(Integer.toString(menu.getMenuprice()) + "��");
			//price.setBounds(posX+200, posY + (i * 50), 300, 50);
			//price.setFont(font);
			JButton btnTake = new JButton("���");
			btnTake.setBounds(posX+500, posY + (i*50), 100, 50);
			btnTake.setFont(font2);
			
			btnTake.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			
			pane.add(btnTake);
			pane.add(lbl);
			//pane.add(price);
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}	
			
		add(scroll);
		add(btnBack);
		setVisible(true);
	}

}

