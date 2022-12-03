package com.java.ex.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.ex.customer.Customer;
import com.java.ex.dao.BusinessDAO;
import com.java.ex.dao.MemberDAO;
import com.java.ex.dto.BusinessDTO;
import com.java.ex.dto.MemberDTO;
import com.java.ex.dto.Session;
import com.java.ex.login.Login;

public class BusinessModify extends JFrame {
	int posX = 50, posY = 50;
	
	JTextField txtName;
	JTextField txtID;
	JPasswordField txtPW;
	JTextField txtNickn;
	JTextField txtAddress;
	JTextField txtTel;
	
	
	
	public BusinessModify() {
		BusinessDTO business = (BusinessDTO)Session.getSession("business");
		setTitle("���� ����");
		setSize(400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); // ���� ��ġ
		setLocationRelativeTo(null); // ȭ�� �߾� ��ġ
		setResizable(false);
		
		JLabel lblID = new JLabel("���̵�");
		lblID.setBounds(posX, posY, 80, 40);
		
		txtID = new JTextField();
		txtID.setBounds(posX+70, posY, 150, 30);
		txtID.setText(business.getId());
		txtID.setEditable(false);
		
		JLabel lblPW = new JLabel("��й�ȣ");
		lblPW.setBounds(posX, posY+35, 100, 40);
		
		txtPW = new JPasswordField();
		txtPW.setBounds(posX+70, posY+35, 200, 30);
		
		JLabel lblName = new JLabel("��ü��");
		lblName.setBounds(posX, posY + 70, 100, 40);
		
		txtName = new JTextField();
		txtName.setBounds(posX+70, posY + 70, 100, 30);
		txtName.setText(business.getName());		
		
		JLabel lblAddress = new JLabel("�ּ�");
		lblAddress.setBounds(posX, posY + 155, 100, 40);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(posX+70, posY + 160, 200, 30);
		txtAddress.setText(business.getAddress());
		
		JLabel lblTel = new JLabel("��ȭ��ȣ");
		lblTel.setBounds(posX, posY + 195, 100, 40);
		
		txtTel = new JTextField();
		txtTel.setBounds(posX+70, posY + 200, 200, 30);
		txtTel.setText(business.getTel());
		
		JButton btnModify = new JButton("ȸ�� ����");
		btnModify.setBounds(80, 380, 100, 50);
		btnModify.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isBlanks()) {
				JOptionPane.showMessageDialog(null, "�� ĭ�� �ֽ��ϴ�", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			BusinessDAO dao = new BusinessDAO();
			
			BusinessDTO dto = dao.selectBusiness(txtID.getText());
			dto.setPw(txtPW.getText());
			dto.setName(txtName.getText());
			dto.setAddress(txtAddress.getText());
			dto.setTel(txtTel.getText());
			
			dao.updateBusiness(dto);
			setVisible(false);
			new Login();
			
			JOptionPane.showMessageDialog(null, "���� ������ �Ϸ��߽��ϴ�.", "Success", JOptionPane.PLAIN_MESSAGE);
			}
		});	
		
		JButton btnBack = new JButton("�ڷΰ���");
		btnBack.setBounds(210, 380, 100, 50);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Business();
			}
		});
		
		add(lblName);
		add(txtName);
		add(lblID);
		add(txtID);
		add(lblPW);
		add(txtPW);
		add(lblAddress);
		add(txtAddress);
		add(lblTel);
		add(txtTel);
		add(btnModify);
		add(btnBack);
		setVisible(true);
	}
		boolean isBlanks() {
			if(txtID.getText().equals("") || txtPW.getText().equals("") || txtName.getText().equals("") || txtAddress.getText().equals("") || txtTel.getText().equals("")) {
				return true;
			}
			return false;
	}
}