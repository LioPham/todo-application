package todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import todoapp.model.LoginBean;
import todoapp.utils.JdbcUtils;

public class LoginDao {
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JdbcUtils.getConnection();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from users where username = ? and password = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		}
		return status;
	}
}
