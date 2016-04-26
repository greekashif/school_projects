package com.tcs.ilp.dao;
import com.tcs.ilp.service.AccountService;
import com.tcs.ilp.util.*;
import com.tcs.ilp.bean.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {

public static int addAccount(AccountBean acc) {
	int rows = 0;
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(QueryConstants.ADD)) {
		ps.setInt(1, acc.getId());
		ps.setString(2, acc.getName());
		ps.setString(3, acc.getType());
		rows = ps.executeUpdate();
		System.out.println(rows + " row(s) added");
		conn.commit();
	} catch (SQLException e) {
		e.printStackTrace();
	} 		
	return rows;
	
}

public static AccountBean viewAccount(int id) {
	
	AccountBean acc = null;
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(QueryConstants.VIEW);
		) {
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			acc = new AccountBean();
			acc.setId(rs.getInt(1));
			acc.setName(rs.getString(2));
			acc.setType(rs.getString(3));
		}
		conn.commit();
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return acc;
}

public static int updateAccount(AccountBean acc) {
	int rows = 0;
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(QueryConstants.UPDATE)) {
		ps.setString(1, acc.getName());
		ps.setString(2, acc.getType());
		ps.setInt(3, acc.getId());
		rows = ps.executeUpdate();
		System.out.println("Row(s) updated: " + rows);
		conn.commit();
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return rows;
}

public static int deleteAccount(int id) {
	int rows = 0;
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(QueryConstants.DELETE)) {
		ps.setInt(1, id);
		rows = ps.executeUpdate();
		System.out.println("Row(s) deleted: " + rows);
		conn.commit();
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return rows;
}

public static ArrayList<AccountBean> viewAll() {
	ArrayList<AccountBean> accountList = new ArrayList<AccountBean>();
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(QueryConstants.VIEW_ALL)) {
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			AccountBean acc = new AccountBean();
			acc.setId(rs.getInt(1));
			acc.setName(rs.getString(2));
			acc.setType(rs.getString(3));
			accountList.add(acc);
		}
		conn.commit();
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return accountList;
}

public static float deposit(int id, float amount) {
	float balance = -1.0f;
	float interest = AccountService.calculateInterest(amount);
	try(
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement(
				QueryConstants.DEPOSIT);
		PreparedStatement psAcct = conn.prepareStatement(
				QueryConstants.VIEW);
		ResultSet rs = psAcct.executeQuery()) {
		rs.next();
		balance = rs.getFloat(4);
		ps.setFloat(1, balance + interest);
		ps.setFloat(2, interest);
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return balance + interest;
}

}
