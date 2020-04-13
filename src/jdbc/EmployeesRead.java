package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeesRead {

	public static void main(String[] args) {
		//1. Driver class loading
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver loading OK!!");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "hr";
		String pass = "hr";
		String sql = "select * from employees";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//2. DB 연결 : Connection 생성
			//Connection con = new T4CConnection();(X) vendor 종속적
			// getConnection() -> Factory Method
			con = DriverManager.getConnection(url, user, pass);
			System.out.println(con.getClass().getName()); // T4CConnection
			//3. SQL 전달하기 위한 준비: Statement 생성
			stmt = con.createStatement();
			System.out.println(stmt.getClass().getName()); // OracleStatementWrapper
			//4. SQL 실행
			rs = stmt.executeQuery(sql);
			System.out.println(rs.getClass().getName()); // OracleResultImpl
			//5. query 결과 처리
			while(rs.next()) {
				String id = rs.getString("employee_id");
				String name = rs.getString("first_name");
				Date hdate = rs.getDate("hire_date");
				float salary = rs.getFloat("salary");
				System.out.println(id+" " + name+" "+hdate+" "+
						salary);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
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
