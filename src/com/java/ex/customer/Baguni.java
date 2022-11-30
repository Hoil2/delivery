package com.java.ex.customer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.java.ex.dao.MenuDAO;
import com.java.ex.dto.BaguniDTO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;

public class Baguni extends JFrame {
	
	Font font = new Font("����", Font.BOLD, 30);
	Font font2 = new Font("����", Font.BOLD, 20);
	Font font3 = new Font("����", Font.BOLD, 15);
	int posX = 20, posY = 20;
	
	JPanel pane;
	JScrollPane scroll;
	MemberDTO member = (MemberDTO)Session.getSession("member");
	
	ArrayList<JLabel> lblBaguniList = new ArrayList<JLabel>();
	ArrayList<JButton> btnDeleteList = new ArrayList<JButton>();
	ArrayList<JButton> btnPlusList = new ArrayList<JButton>();
	ArrayList<JButton> btnMinusList = new ArrayList<JButton>();
	
	public Baguni() {
		pane = new JPanel();
		pane.setLayout(null);
		pane.setBackground(Color.white);
		pane.setPreferredSize(new Dimension(20, 20));
		
		scroll = new JScrollPane(pane);
		scroll.setBounds(80,80,850,300);
		
		setTitle("��ٱ���");
		setSize(1024, 576);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);
		
		JButton btnBack = new JButton("�ڷΰ���");
		btnBack.setBounds(830, 10, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Customer();
			}
		});
			
		
		JLabel lbl = new JLabel("�� ��ٱ��� ���");
		lbl.setBounds(posX+55, posY, 300, 50);
		lbl.setFont(font);
		
		MenuDAO Dao = new MenuDAO();
		ArrayList<Map<String, Object>> BaguniList = new ArrayList<Map<String, Object>>();
		BaguniList = Dao.selectBaguni(member.getId());
			
		MenuDAO dao = new MenuDAO();
		ArrayList<BaguniDTO> dtos = new ArrayList<BaguniDTO>();	
		
		for(int i=0; i<BaguniList.size(); i++) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) BaguniList.get(i);
			JLabel lblBaguni = new JLabel("���Ը� : " + hashmap.get("businessname")+ " " + "�޴� : " + hashmap.get("menuname") + " " + "���� : " + hashmap.get("menuprice") + "��"+ "  "+  "���� : " + hashmap.get("menu_count"));
			lblBaguni.setBounds(posX, posY + (i*50), 800, 50);
			lblBaguni.setFont(font3);
			
			int sbno = (int) hashmap.get("sb_no");
			
			JButton btnDelete = new JButton("�ֹ� ���");
			btnDelete.setBounds(posX + 700, posY + (i * 60), 100, 30);
			
			JButton btnPlus = new JButton("+");
			btnPlus.setBounds(posX + 600, posY + (i * 60), 45, 30);
			
			JButton btnMinus = new JButton("-");
			btnMinus.setBounds(posX + 650, posY + (i * 60), 45, 30);
			
			
			btnPlus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int Plus = JOptionPane.showConfirmDialog(null, "������ �ø��ðڽ��ϱ�?",
							"���� �߰�", JOptionPane.YES_NO_OPTION);
					
					int menu_count = (int)hashmap.get("menu_count") + 1;
					hashmap.put("menu_count", menu_count);
					
					if(Plus == JOptionPane.YES_OPTION) {
						dao.BaguniUpdate(member.getId(), menu_count, sbno);
						lblBaguni.setText("���Ը� : " + hashmap.get("businessname")+ " " + "�޴� : " + hashmap.get("menuname") + " " + "���� : " + hashmap.get("menuprice") + "��"+ "  "+  "���� : " + menu_count);					
					}
				}
			});
			
			btnMinus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int Minus = JOptionPane.showConfirmDialog(null, "������ �����ڽ��ϱ�?",
							"���� ���� ", JOptionPane.YES_NO_OPTION);
					int menu_count = (int)hashmap.get("menu_count") - 1;
					hashmap.put("menu_count", menu_count);
					if(menu_count > 1) {
						if(Minus == JOptionPane.YES_OPTION) {
							dao.BaguniUpdate(member.getId(), menu_count, sbno);
							lblBaguni.setText("���Ը� : " + hashmap.get("businessname")+ " " + "�޴� : " + hashmap.get("menuname") + " " + "���� : " + hashmap.get("menuprice") + "��"+ "  "+  "���� : " + menu_count);					
							
						}
					}
				}
			});
			
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int clear = JOptionPane.showConfirmDialog(null, "�ֹ��� ����Ͻðڽ��ϱ�?",
							"�ֹ� ���", JOptionPane.YES_NO_OPTION);
					if(clear == JOptionPane.YES_OPTION) {
						dao.BaguniSelectDelete(member.getId(), sbno);
						lblBaguni.setVisible(false);
						btnDelete.setVisible(false);
						btnPlus.setVisible(false);
						btnMinus.setVisible(false);
						
						int index = lblBaguniList.indexOf(lblBaguni);
						
						lblBaguniList.remove(lblBaguni);
						btnDeleteList.remove(btnDelete);
						btnPlusList.remove(btnPlus);
						btnMinusList.remove(btnMinus);
						
						
						for(int i = index; i < lblBaguniList.size(); i++) {
							Point testNamePoint = lblBaguniList.get(i).getLocation();
							testNamePoint.y -= 55;
							Point deletePoint = btnDeleteList.get(i).getLocation();
							deletePoint.y -= 60;
							Point PlusPoint = btnPlusList.get(i).getLocation();
							PlusPoint.y -= 60;
							Point MinusPoint = btnMinusList.get(i).getLocation();
							MinusPoint.y -= 60;
							
							
							lblBaguniList.get(i).setLocation(testNamePoint);
							btnDeleteList.get(i).setLocation(deletePoint);
							btnPlusList.get(i).setLocation(PlusPoint);
							btnMinusList.get(i).setLocation(MinusPoint);
						}
						
						Dimension di = pane.getPreferredSize();
						di.height -= 60;
						pane.setPreferredSize(di);
					}
				}
			});
			lblBaguniList.add(lblBaguni);
			btnDeleteList.add(btnDelete);
			btnPlusList.add(btnPlus);
			btnMinusList.add(btnMinus);
			
			pane.add(btnPlus);
			pane.add(btnMinus);
			pane.add(lblBaguni);
			pane.add(btnDelete);
			Dimension di = pane.getPreferredSize();
			di.height += 60;
			pane.setPreferredSize(di);
		}
		
		add(lbl);
		setVisible(true);
		
		MenuDAO payDao = new MenuDAO();
		ArrayList<Map<String, Object>> payBaguniList = new ArrayList<Map<String, Object>>();
		
		payBaguniList = payDao.payBaguni(member.getId());
		/*
		for(int i=0; i<payBaguniList.size(); i++) {
			HashMap<String, Object> hashmap = (HashMap<String, Object>) payBaguniList.get(i);
			String menuname = (String) hashmap.get("menuname");
			int menuprice = (int) hashmap.get("menuprice");
			int menucount = (int) hashmap.get("menu_count");
		}
		*/
		JButton btnBaguni = new JButton("�ֹ��ϱ�");
		btnBaguni.setBounds(325, 400, 150, 100);
		btnBaguni.setFont(font2);
		btnBaguni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				int pay = JOptionPane.showConfirmDialog(null, "�ֹ��Ͻðڽ��ϱ�?",
						"�ֹ� Ȯ��", JOptionPane.YES_NO_OPTION);
				if(pay == JOptionPane.YES_OPTION) {
					setVisible(true);
					
				}
			}
		});
		
		add(btnBaguni);
		setVisible(true);
		
			
		
		JButton btnBaguniClear = new JButton("�ٱ��� ����");
		btnBaguniClear.setBounds(530, 400, 170, 100);
		btnBaguniClear.setFont(font2);
		btnBaguniClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				int clear = JOptionPane.showConfirmDialog(null, "�ٱ��ϸ� ���ڽ��ϱ�?",
						"�ٱ��� ����", JOptionPane.YES_NO_OPTION);
				if(clear == JOptionPane.YES_OPTION) {
					dao.BaguniDelete(member.getId());
					pane.removeAll();
					repaint();
				}
			}
		});
		
		add(btnBaguniClear);
		add(btnBack);
		add(scroll);
		setVisible(true);
	}
}
