package idv.trashchu.finance.dao.purejdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import idv.trashchu.finance.dao.IncomeStatementDAO;
import idv.trashchu.finance.entity.IncomeStatement;
import idv.trashchu.finance.entity.IncomeStatementPrimaryKey;

public class IncomeStatementPureJDBCDAO extends GenericPureJDBCDAO<IncomeStatement, IncomeStatementPrimaryKey> implements IncomeStatementDAO {

	@Override
	String getTableSQL() {

		return "`incomestatements`";
	}

	@Override
	String getInsertFieldSQL(IncomeStatement t1) {

		return	"`season`, " +
				"`symbol`, " +
				"`name`, " +
				"`eps`";
	}

	@Override
	String getInsertValueSQL(IncomeStatement t1) {
		
		return	"'" + t1.getSeason() + "', " +
				"'" + t1.getSymbol() + "', " +
				"'" + t1.getName() + "', " +
				t1.getEps().toString();
	}

	@Override
	String getUpdateSetSQL(IncomeStatement t1) {

		return 	"`name` = '" + t1.getName() + "', " +
				"`eps` = " + t1.getEps().toString();
	}

	@Override
	String getWhereSQL(IncomeStatement t1) {

		return "`symbol` = '" + t1.getSymbol() + "' AND `season` = '" + t1.getSeason() + "'";
	}

	@Override
	String getWhereSQL(IncomeStatementPrimaryKey t2) {

		return "`symbol` = '" + t2.getSymbol() + "' AND `season` = '" + t2.getSeason() + "'";
	}
	
	/*@Override
	String getInsertSQL(IncomeStatement t1) {
		
		return	"INSERT INTO `incomestatements` " +
				"(" +
					"`season`, " +
					"`symbol`, " +
					"`name`, " +
					"`eps` " +
				") " +
				"VALUES (" +
					"'" + t1.getSeason() + "', " +
					"'" + t1.getSymbol() + "', " +
					"'" + t1.getName() + "', " +
					t1.getEps().toString() +
				")";
	}
	
	@Override
	String getUpdateSQL(IncomeStatement t1) {

		return	"UPDATE `incomestatements` " +
				"SET " +
					"`name` = '" + t1.getName() + "', " +
					"`eps` = " + t1.getEps().toString() + " " +
				"WHERE `symbol` = '" + t1.getSymbol() + "' AND `season` = '" + t1.getSeason() + "'";
	}
	
	@Override
	String getInsertDuplicateUpdateSQL(IncomeStatement t1) {
		
		return	this.getInsertSQL(t1) + " " +
				"ON DUPLICATE KEY UPDATE " + 
					"`name` = '" + t1.getName() + "', " +
					"`eps` = " + t1.getEps().toString()
				;
	}
	
	@Override
	String getSelectSQL(IncomeStatementPrimaryKey t2) {

		return	"SELECT * " + 
				"FROM `incomestatements` " +
				"WHERE `symbol` = '" + t2.getSymbol() + "' AND `season` = '" + t2.getSeason() + "'";
	}*/

	@Override
	IncomeStatement getTableEntity(ResultSet rs) throws SQLException {

		return new	IncomeStatement(
						rs.getString("season"), 
						rs.getString("symbol"), 
						rs.getString("name"), 
						rs.getDouble("eps")
					);
	}

	

	

	

}
