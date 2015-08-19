package com.hy.Function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hy.API.Bank;
import com.hy.DB.DB;
import com.hy.vo.Message;

public class BankImpl implements Bank {
	private Connection conn;
	public BankImpl(){
		conn = new DB().getConn();
	}
	@Override
	public Message saveMoney(String CardID, String password, double money) {
		// TODO Auto-generated method stub
		Message message = new Message();
		CallableStatement cs = null;
		try {
			double temp = queryMoney(CardID,password);
			String sql ="update card set money=? where ID= ? and password = ?";
			cs = conn.prepareCall(sql);
			cs.setDouble(1, temp+money);
			cs.setString(2, CardID);
			cs.setString(3, password);
			message.setState(cs.execute());
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(message.isState()){
			message.setMessage("存钱成功");
		}else {
			message.setMessage("存钱失败");
		}
		return message;
	}

	@Override
	public Message geyMoney(String CardID, String password, double money) {
		// TODO Auto-generated method stub
		Message message = new Message();
		CallableStatement cs = null;
		try {
			double temp = queryMoney(CardID,password);
			if(money > temp){
				message.setMessage("当前用户余额不足");
				message.setState(false);
				return message;
			}else{
				String sql ="update card set money=? where ID= ? and password = ?";
				cs = conn.prepareCall(sql);
				cs.setDouble(1, temp-money);
				cs.setString(2, CardID);
				cs.setString(3, password);
				message.setState(cs.execute());
				message.setMessage("取钱成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return message;
	}
	@Override
	public double queryMoney(String CardID, String password) {
		// TODO Auto-generated method stub
		double money = 0.0d;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select money from card where ID= ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, CardID);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while(rs.next()){
				money = rs.getDouble("money");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return money;
	}
	public static void main(String[] args) {
		System.out.println(new BankImpl().geyMoney("6212233500014424515", "123456",10000d).getMessage());
	}
}
