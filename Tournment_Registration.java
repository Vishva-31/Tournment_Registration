package com.jdbc;
import java.sql.*;
import java.util.Scanner;
public class TournmentReg {
public static void main(String args[]) {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c =DriverManager.getConnection("jdbc:mysql://localhost:3306/tournment","root","Vishva@2003");
		Statement st = c.createStatement();
		ResultSet r ;
		PreparedStatement pt;
		String qry="";
		int id,choice;
		String name,memb,num,color;
		
		Scanner ch = new Scanner(System.in);
		Scanner inf = new Scanner(System.in);
		
		while(true) {
			System.out.println("------Tournment Registration------");
			System.out.println("1.Enter Details");
			System.out.println("2.Delete Details");
			System.out.println("3.Select Details");
			System.out.println("4.Update Details");
			System.out.println("5.Exit");
			System.out.println("Enter the choice:");
			choice =ch.nextInt();
			System.out.println("----------------------------------");
			switch(choice) {
			case 1:
				System.out.println("1.Enter new data");
				System.out.println("Enter the Team-Name: ");
				name=inf.nextLine();
				System.out.println("Enter the Team-Members Count: ");
				memb=inf.nextLine();
				System.out.println("Enter the Contact Number of your Team: ");
				num=inf.nextLine();
				System.out.println("Enter the Team-Color: ");
				color=inf.nextLine();
				qry="INSERT INTO `tournment-details`(`TEAM-NAME`,`TEAM-MEMBERS`,`CONTACT-DETAILS`,`TEAM-COLOR`) VALUES(?,?,?,?)";
				pt= c.prepareStatement(qry);
				pt.setString(1,name);
				pt.setString(2,memb);
				pt.setString(3,num);
				pt.setString(4, color);
				pt.executeUpdate();
				System.out.println("Details are Entered Successfully");
				break;
				
			case 2:
				System.out.println("2.Delete a data");
				System.out.println("Enter the Team-ID: ");
				id=ch.nextInt();
				qry="DELETE FROM `tournment-details` WHERE `TEAM-ID`=?";
				pt = c.prepareStatement(qry);
				pt.setInt(1, id);
				pt.executeUpdate();
				System.out.println("Details are Deleted Successfully");
				break;
				
			case 3:
				System.out.println("3.Select a data");
				qry="SELECT `TEAM-ID`,`TEAM-NAME`,`TEAM-MEMBERS`,`CONTACT-DETAILS`,`TEAM-COLOR` FROM `tournment-details`";
				r=st.executeQuery(qry);
				while(r.next()) {
					id=r.getInt("TEAM-ID");
					name=r.getString("TEAM-NAME");
					memb=r.getString("TEAM-MEMBERS");
					num=r.getString("CONTACT-DETAILS");
					color=r.getString("TEAM-COLOR");
					
					System.out.println(" TEAM-ID        : "+id);
					System.out.println(" TEAM-NAME      : "+name);
					System.out.println(" TEAM-MEMBERS   : "+memb);
					System.out.println(" CONTACT-DETAILS: "+num);
					System.out.println(" TEAM-COLOR   : "+color);
		}
				break;
			case 4:
				System.out.println("4.Update data");
				System.out.println("Enter TEAM-ID: ");
				id=ch.nextInt();
				System.out.println("Enter TEAM-NAME: ");
				name=inf.nextLine();
				System.out.println("Enter TEAM-MEMBERS: ");
				memb=inf.nextLine();
				System.out.println("Enter CONTACT-DETAILS: ");
				num=inf.nextLine();
				System.out.println("Enter TEAM-COLOR: ");
				color=inf.nextLine();
				qry="UPDATE `tournment-details` SET `TEAM-NAME`=?,`TEAM-MEMBERS`=?,`CONTACT-DETAILS`=?,`TEAM-COLOR`=?";
				pt=c.prepareStatement(qry);
				pt.setString(1,name);
				pt.setString(2,memb);
				pt.setString(3,num);
				pt.setString(4,color);
				pt.executeUpdate();
				System.out.println("Details are Updated Successfully");
				
			case 5:
				System.out.println("Thanks for your registration in our Tournment");
				System.exit(0);
				break;
			
			default:
				System.out.println("Select the correct option");
				break;
				
			}
			
			
		}
	}
	catch(Exception e){
		System.out.println(e.toString());
	}
}
}
