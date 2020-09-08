package chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import ECUtils.BaseDAO;
import chat.bean.AppUser;

public class AppUserDAO extends BaseDAO{
	//id, name, pass, role, status
	public static AppUser validateUser(String uname, String pass) {
		AppUser res= null;
		Connection con = null;
		try {
			con = getCon();	
			String sql = "select * from app_users where name = ? and pass = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, uname);
			st.setString(i++, pass);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				AppUser p1 = new AppUser();
				p1.setId(rs.getString("id"));
				p1.setUserName(rs.getString("name"));
				p1.setPassword(rs.getString("pass"));
				p1.setRole(rs.getString("role"));
				p1.setStatus(rs.getString("status"));
				res = p1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
	
	public static void insert(AppUser p1) {
		Connection con = null;
		try {
			con = getCon();	
			String sql = "insert into app_users (name, pass, status, role) values (?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getUserName());
			st.setString(i++, p1.getPassword());
			st.setString(i++, p1.getStatus());
			st.setString(i++, p1.getRole());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
	}

	
	public static AppUser searchByName(String si) {
		AppUser res= null;
		Connection con = null;
		try {
			con = getCon();	
			String sql = "select * from app_users where name = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, si);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				AppUser p1 = new AppUser();
				p1.setId(rs.getString("id"));
				p1.setUserName(rs.getString("name"));
				p1.setPassword(rs.getString("pass"));
				p1.setRole(rs.getString("role"));
				p1.setStatus(rs.getString("status"));
				res = p1;				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}


	public static void delete(AppUser p1) {
		Connection con = null;
		try {
			con = getCon();	
			String sql = "delete from app_users where name = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getUserName());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
	}


	public static void update(AppUser p1) {
		Connection con = null;
		try {
			con = getCon();	
			String sql = "update app_users set status =? where name = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getStatus());
			st.setString(i++, p1.getUserName());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
	}

	public static LinkedList<AppUser> search(String si) {
		LinkedList<AppUser> res = new LinkedList<AppUser>();
		Connection con = null;
		try {
			con = getCon();	
			String sql = "select * from app_users where name like ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, "%"+ si + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				AppUser p1 = new AppUser();
				p1.setId(rs.getString("id"));
				p1.setUserName(rs.getString("name"));
				p1.setPassword(rs.getString("pass"));
				p1.setRole(rs.getString("role"));
				p1.setStatus(rs.getString("status"));
				res.add(p1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}


}
