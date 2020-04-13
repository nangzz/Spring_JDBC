package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String sql = "update users set name='임꺽정', gender='여', city='부산' where userid='gildong';";
		String sql = "update users set name=?, gender=?, city=? where userid=?";
		
		// 1. Driver Class Loading
		try {
			// exception 이 나니까 try-catch로 처리해줘야..
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver Loading");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2. Connection 생성
		Connection con = null;
		// select와의 차이점 (PreparedStatement)
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "scott";
		String pass = "tiger";		
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "김영희"); // name
			stmt.setString(2, "여"); // gender
			stmt.setString(3, "인천"); // city
			stmt.setString(4, "gildong"); // userid
			
			int count = stmt.executeUpdate();
			System.out.println("updated data count : " + count);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
