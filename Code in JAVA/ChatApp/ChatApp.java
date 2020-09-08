package chat;

import java.util.LinkedList;
import java.util.Scanner;

import ECUtils.ECValidattion.PassValidator;
import chat.bean.AppUser;
import chat.bean.Chat;
import chat.dao.AppUserDAO;
import chat.dao.ChatDAO;
import chat.utils.MyUtils;
import chat.utils.ShowMsg;

public class ChatApp {
static AppUser u1 = null;
public static void main(String[] args) {
	try {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("===== Chat App ======");
			System.out.println("For login enter L");
			System.out.println("For exit enter e");
			String ch = sc.nextLine();
			if("L".equalsIgnoreCase(ch)) {
				System.out.println("User Name?");
				String uname = sc.nextLine();
				System.out.println("Password?");
				String pass = sc.nextLine();
				u1 = AppUserDAO.validateUser(uname, pass);
				if(u1==null) {
					System.out.println("Invalid Credentials!");
				} else {
					
					ShowMsg t1 = new ShowMsg();
					t1.u1 = u1;
					String lid = ChatDAO.getCurrentId();
					t1.lid = lid;
					t1.start();
					
					if("admin".equalsIgnoreCase(u1.getRole())) {
						admin();
					} else {
						user();
					}
				}
			} else if ("e".equalsIgnoreCase(ch)) {
				return;
			} else {
				System.out.println("Invalid Option!");
			}
			System.out.println("");
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public static void admin() {
		try {
			Scanner sc = new Scanner(System.in);
			while(true) {
				System.out.println("===== Welcome Admin ======");
				System.out.println("To mute enter m");
				System.out.println("To unmute enter u");
				System.out.println("To send message enter s");
				System.out.println("To send message to user su");
				System.out.println("To add user enter a");
				System.out.println("To remove user enter r");
				System.out.println("To search user enter se");
				System.out.println("To exit enter e");
				String ch = sc.nextLine();
				if("m".equalsIgnoreCase(ch)) {
					AppUser p1 = new AppUser();
					System.out.println("Name?");
					p1.setUserName(sc.nextLine());
					AppUser old = AppUserDAO.searchByName(p1.getUserName());
					if(old==null) {
						System.out.println("Does not exist!!");
						continue;
					}
					old.setStatus("mute");
					AppUserDAO.update(old);
					sendAutoMsg("all", old.getUserName() + " muted by admin");
					System.out.println("Done!!");										
				} else if ("u".equalsIgnoreCase(ch)) {
					AppUser p1 = new AppUser();
					System.out.println("Name?");
					p1.setUserName(sc.nextLine());
					AppUser old = AppUserDAO.searchByName(p1.getUserName());
					sendAutoMsg("all", old.getUserName() + " unmuted by admin");
					if(old==null) {
						System.out.println("Does not exist!!");
						continue;
					}
					old.setStatus("unmute");
					AppUserDAO.update(old);
					System.out.println("Done!!");										
				} else if ("s".equalsIgnoreCase(ch)) {
					sendMsg("all");					
				} else if ("su".equalsIgnoreCase(ch)) {
					System.out.println("Enter name of user");
					String name = sc.nextLine();
					sendMsg(name);					
				} else if ("a".equalsIgnoreCase(ch)) {
					AppUser p1 = new AppUser();
					System.out.println("Name?");
					p1.setUserName(sc.nextLine());
					AppUser old = AppUserDAO.searchByName(p1.getUserName());
					if(old!=null) {
						System.out.println("Already Exist!!");
						continue;
					}
					System.out.println("password?");
					p1.setPassword(sc.nextLine());
					p1.setStatus("unmute");
					p1.setRole("user");
					PassValidator v1 = new PassValidator();
					String err = v1.validate(p1.getPassword());
					if(err!=null) {
						System.out.println(err);
						continue;
					}
					AppUserDAO.insert(p1);					
					System.out.println("Done!!");					
				} else if ("r".equalsIgnoreCase(ch)) {
					AppUser p1 = new AppUser();
					System.out.println("Name?");
					p1.setUserName(sc.nextLine());
					AppUser old = AppUserDAO.searchByName(p1.getUserName());
					if(old==null) {
						System.out.println("Does not exist!!");
						continue;
					}
					AppUserDAO.delete(p1);
					System.out.println("Done!!");					
				} else if ("se".equalsIgnoreCase(ch)) {
					System.out.println("Search For?");
					String si = sc.nextLine();
					LinkedList<AppUser> res = AppUserDAO.search(si);
					if(res.size()==0) {
						System.out.println("No Record Found!");
						continue;
					}
					for(AppUser p1 : res) {
						System.out.println(p1.getUserName() +"\t:\t"+ p1.getStatus() );
					}

				} else if ("e".equalsIgnoreCase(ch)) {
					return;
				} else {
					System.out.println("Invalid Option!");
				}
				System.out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void user() {
		try {
			Scanner sc = new Scanner(System.in);
			while(true) {
				System.out.println("===== Welcome User ======");
				System.out.println("To send message enter s");
				System.out.println("To send message to admin only sa");
				System.out.println("To exit enter e");
				String ch = sc.nextLine();
				if("s".equalsIgnoreCase(ch)) {
					sendMsg("all");		
				} else if("sa".equalsIgnoreCase(ch)) {
					sendMsg("admin");										
				} else if ("e".equalsIgnoreCase(ch)) {
					return;
				} else {
					System.out.println("Invalid Option!");
				}
				System.out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static void sendMsg(String forUser) {
		try {		
			if(!"admin".equalsIgnoreCase(forUser)) {
				AppUser newU1 = AppUserDAO.searchByName(u1.getUserName());
				if("mute".equalsIgnoreCase(newU1.getStatus())) {
					System.out.println("Sorry u r muted by admin! U can request admin to unmute!");				
					return;
				}									
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter msg to send!");
			String msg = sc.nextLine();
			Chat c1 = new Chat();
			c1.setCreatedTime(MyUtils.getTodayWithTime());
			c1.setMessage(msg);
			c1.setSeenBy("");
			c1.setUserName(u1.getUserName());
			c1.setForUser(forUser);
			ChatDAO.insert(c1);
			System.out.println("Sent!!");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public static void sendAutoMsg(String forUser, String msg) {
		try {		
			Chat c1 = new Chat();
			c1.setCreatedTime(MyUtils.getTodayWithTime());
			c1.setMessage(msg);
			c1.setSeenBy("");
			c1.setUserName(u1.getUserName());
			c1.setForUser(forUser);
			ChatDAO.insert(c1);
			System.out.println("Sent!!");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
