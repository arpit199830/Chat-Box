package chat.utils;

import java.util.LinkedList;

import chat.bean.AppUser;
import chat.bean.Chat;
import chat.dao.ChatDAO;

public class ShowMsg extends Thread{
	public String lid = "0";
	public AppUser u1 = null;
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(2000);
				LinkedList<Chat> res = ChatDAO.search(u1.getUserName(), lid);
				if(res.size()==0) {
					continue;
				}
				for(Chat p1 : res) {
					System.out.println(p1.getMessage() +"\t: by: "+ p1.getUserName());
					lid = p1.getId();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
