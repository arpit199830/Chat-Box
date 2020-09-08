package chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import ECUtils.BaseDAO;
import chat.bean.AppUser;
import chat.bean.Chat;

public class ChatDAO extends BaseDAO{
	//id , msg , user_name , cr_date, seen_by, for_user
	public static void insert(Chat p1) {
		Connection con = null;
		try {
			con = getCon();	
			String sql = "insert into chat (msg , user_name , cr_date, seen_by, for_user) values (?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, p1.getMessage());
			st.setString(i++, p1.getUserName());
			st.setTimestamp(i++, p1.getCreatedTime());
			st.setString(i++, p1.getSeenBy());
			st.setString(i++, p1.getForUser());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
	}
	
	
	public static LinkedList<Chat> search(String name, String lid) {
		LinkedList<Chat> res = new LinkedList<Chat>();
		Connection con = null;
		try {
			con = getCon();	
			
			String sql = "select * from chat where (for_user = ? or for_user = 'all') "
					+ " and id > ? ";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			st.setString(i++, name);
			st.setString(i++, lid);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Chat p1 = new Chat();
				p1.setId(rs.getString("id"));
				p1.setUserName(rs.getString("user_name"));
				p1.setCreatedTime(rs.getTimestamp("cr_date"));
				p1.setForUser(rs.getString("for_user"));
				p1.setMessage(rs.getString("msg"));
				p1.setSeenBy(rs.getString("seen_by"));
				res.add(p1);
			}

			sql = "update chat set seen_by = CONCAT(seen_by, ?) where (for_user = ? or for_user = 'all') "
					+ " and id > ? ";
			st = con.prepareStatement(sql);
			i = 1;
			st.setString(i++, ", "+ name);
			st.setString(i++, name);
			st.setString(i++, lid);
			st.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}
	
	public static String getCurrentId() {
		String res = "0";
		Connection con = null;
		try {
			con = getCon();	
			
			String sql = "select max(id) as maxid from chat";
			PreparedStatement st = con.prepareStatement(sql);
			int i = 1;
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				res = rs.getString("maxid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return res;
	}

}
