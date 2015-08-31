package idv.trashchu.finance.dao.purejdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import idv.trashchu.finance.dao.GenericDAO;
import idv.trashchu.finance.entity.PrimaryKey;
import idv.trashchu.finance.entity.TableEntity;
import idv.trashchu.finance.util.PureJDBCUtil;

public abstract class GenericPureJDBCDAO<T1 extends TableEntity, T2 extends PrimaryKey> implements GenericDAO<T1, T2> {

	@Override
	public void insert(T1 t1) throws SQLException {
		Connection connection = PureJDBCUtil.getConnection();
		Statement stmt = connection.createStatement();
		
		String sql =	"INSERT INTO " + this.getTableSQL() + " " +
						"(" + this.getInsertFieldSQL(t1) + ") " +
						"VALUES (" + this.getInsertValueSQL(t1) + ")";
		
		stmt.executeUpdate(sql);
	}
	
	@Override
	public void update(T1 t1) throws SQLException {
		Connection connection = PureJDBCUtil.getConnection();
		Statement stmt = connection.createStatement();
		
		String sql =	"UPDATE " + this.getTableSQL() + " " +
						"SET " + this.getUpdateSetSQL(t1) + " " +
						"WHERE " + this.getWhereSQL(t1);
		
		stmt.executeUpdate(sql);
	}
	
	@Override
	public void insertOrUpdate(T1 t1) throws SQLException {
		Connection connection = PureJDBCUtil.getConnection();
		Statement stmt = connection.createStatement();
		
		String sql =	"INSERT INTO " + this.getTableSQL() + " " +
						"(" + this.getInsertFieldSQL(t1) + ") " +
						"VALUES (" + this.getInsertValueSQL(t1) + ") " + 
						"ON DUPLICATE KEY UPDATE " + this.getUpdateSetSQL(t1);
		
		stmt.executeUpdate(sql);
	}

	@Override
	public T1 get(T2 t2) throws SQLException {
		
		T1 t1 = null;
		
		Connection connection = PureJDBCUtil.getConnection();
		Statement stmt = connection.createStatement();
		
		String sql =	"SELECT * " + 
						"FROM " + this.getTableSQL() + " " +
						"WHERE " + this.getWhereSQL(t2);
		
		ResultSet rs = stmt.executeQuery(sql);
			
		if (rs.next()) {
			t1 = this.getTableEntity(rs);
		}

		return t1;
	}
	
	abstract String getTableSQL();
	
	abstract String getInsertFieldSQL(T1 t1);
	
	abstract String getInsertValueSQL(T1 t1);
	
	abstract String getUpdateSetSQL(T1 t1);
	
	abstract String getWhereSQL(T1 t1);
	
	abstract String getWhereSQL(T2 t2);
	
	
	
	
	
	
	//abstract String getInsertSQL(T1 t1);
	
	//abstract String getUpdateSQL(T1 t1);
	
	//abstract String getInsertDuplicateUpdateSQL(T1 t1);
	
	//abstract String getSelectSQL(T2 t2);
	
	
	
	abstract T1 getTableEntity(ResultSet rs) throws SQLException;
	
}
