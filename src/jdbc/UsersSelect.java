package jdbc;

/* 조회(select) 하는 클래스 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.user.vo.UserVO;

public class UsersSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. Driver Class Lodading
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
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "scott";
		String pass = "tiger";
		String sql = "select * from users";
		
		try {
			// exception 이 나니까 try-catch로 처리해줘야..
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection : " + con); // T4CConnection
			
			// 3. Statement 객체 생성
			stmt = con.createStatement();
			System.out.println("Statement : " + stmt); // OracleStatementWrapper
			
			// 4. SQL문 실행 - executeQuery()
			rs = stmt.executeQuery(sql);
			System.out.println("ResultSet : " + rs); // OracleResultSetImpl
			System.out.println("--------------------------------");

			// 5. query 결과값 처리 - ""은 컬럼명
			UserVO userVO = null; // userVO 객체에 데이터 저장하기
			List<UserVO> userList = new ArrayList<>(); // userVO 객체를 리스트에 저장하기
			
			while(rs.next()) { // boolean
				String userid = rs.getString("userid");
				String name = rs.getString("name");
				char gender = rs.getString("gender").charAt(0); // String으로 우선 가져온 뒤 char로 받기
				String city = rs.getString("city");
				
				userVO = new UserVO(userid, name, gender, city); // VO 객체에 저장 후 VO가 여러개 생성
				userList.add(userVO); // VO 객체들을 List에 저장
			}
			
			for (UserVO user2 : userList) {
				System.out.println(user2);
			}
			
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
