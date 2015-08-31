package idv.trashchu.finance.dao.purejdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import idv.trashchu.finance.dao.StockRecordDAO;
import idv.trashchu.finance.entity.StockRecord;
import idv.trashchu.finance.entity.StockRecordPrimaryKey;

public class StockRecordPureJDBCDAO extends GenericPureJDBCDAO<StockRecord, StockRecordPrimaryKey> implements StockRecordDAO {

	@Override
	String getTableSQL() {

		return "`stockrecords`";
	}

	@Override
	String getInsertFieldSQL(StockRecord t1) {

		return	"`today`, " +
				"`symbol`, " +
				"`name`, " +
				"`volume`, " +
				"`transaction`, " +
				"`value`, " +
				"`open`, " +
				"`high`, " +
				"`low`, " +
				"`close`, " +
				"`direction`, " +
				"`change`, " +
				"`lastbidprice`, " +
				"`lastbidvolume`, " +
				"`lastaskprice`, " +
				"`lastaskvolume`, " +
				"`per`";
	}

	@Override
	String getInsertValueSQL(StockRecord t1) {

		return	"'" + t1.getToday() + "', " + 
				"'" + t1.getSymbol() + "', " +
				"'" + t1.getName() + "', " +
				t1.getVolume().toString() + ", " +
				t1.getTransaction().toString() + ", " +
				t1.getValue().toString() + ", " +
				t1.getOpen().toString() + ", " +
				t1.getHigh().toString() + ", " +
				t1.getLow().toString() + ", " +
				t1.getClose().toString() + ", " +
				"'" + t1.getDirection() + "', " +
				t1.getChange().toString() + ", " +
				t1.getLastBidPrice().toString() + ", " +
				t1.getLastBidVolume().toString() + ", " +
				t1.getLastAskPrice().toString() + ", " +
				t1.getLastAskVolume().toString() + ", " +
				t1.getPer().toString();
	}

	@Override
	String getUpdateSetSQL(StockRecord t1) {

		return	"`name` = '" + t1.getName() + "', " + 
				"`volume` = " + t1.getVolume().toString() + ", " + 
				"`transaction` = " + t1.getTransaction().toString() + ", " + 
				"`value` = " + t1.getValue().toString() + ", " + 
				"`open` = " + t1.getOpen().toString() + ", " + 
				"`high` = " + t1.getHigh().toString() + ", " + 
				"`low` = " + t1.getLow().toString() + ", " + 
				"`close` = " + t1.getClose().toString() + ", " +
				"`direction` = '" + t1.getDirection() + "', " + 
				"`change` = " + t1.getChange().toString() + ", " + 
				"`lastbidprice` = " + t1.getLastBidPrice().toString() + ", " + 
				"`lastbidvolume` = " + t1.getLastBidVolume().toString() + ", " + 
				"`lastaskprice` = " + t1.getLastAskPrice().toString() + ", " + 
				"`lastaskvolume` = " + t1.getLastAskVolume().toString() + ", " +
				"`per` = " + t1.getPer().toString();
	}

	@Override
	String getWhereSQL(StockRecord t1) {

		return "`symbol` = '" + t1.getSymbol() + "' AND `today` = '" + t1.getToday() + "'";
	}

	@Override
	String getWhereSQL(StockRecordPrimaryKey t2) {

		return "`symbol` = '" + t2.getSymbol() + "' AND `today` = '" + t2.getToday() + "'";
	}
	
	/*@Override
	String getInsertSQL(StockRecord t1) {
		return	"INSERT INTO `stockrecords` " +
				"(" +
					"`today`, " +
					"`symbol`, " +
					"`name`, " +
					"`volume`, " +
					"`transaction`, " +
					"`value`, " +
					"`open`, " +
					"`high`, " +
					"`low`, " +
					"`close`, " +
					"`direction`, " +
					"`change`, " +
					"`lastbidprice`, " +
					"`lastbidvolume`, " +
					"`lastaskprice`, " +
					"`lastaskvolume`, " +
					"`per`" +
				") " +
				"VALUES (" +
					"'" + t1.getToday() + "', " + 
					"'" + t1.getSymbol() + "', " +
					"'" + t1.getName() + "', " +
					t1.getVolume().toString() + ", " +
					t1.getTransaction().toString() + ", " +
					t1.getValue().toString() + ", " +
					t1.getOpen().toString() + ", " +
					t1.getHigh().toString() + ", " +
					t1.getLow().toString() + ", " +
					t1.getClose().toString() + ", " +
					"'" + t1.getDirection() + "', " +
					t1.getChange().toString() + ", " +
					t1.getLastBidPrice().toString() + ", " +
					t1.getLastBidVolume().toString() + ", " +
					t1.getLastAskPrice().toString() + ", " +
					t1.getLastAskVolume().toString() + ", " +
					t1.getPer().toString() +
				")";
	}
	
	@Override
	String getUpdateSQL(StockRecord t1) {

        return	"UPDATE `stockrecords` " + 
				"SET " + 
					"`name` = '" + t1.getName() + "', " + 
					"`volume` = " + t1.getVolume().toString() + ", " + 
					"`transaction` = " + t1.getTransaction().toString() + ", " + 
					"`value` = " + t1.getValue().toString() + ", " + 
					"`open` = " + t1.getOpen().toString() + ", " + 
					"`high` = " + t1.getHigh().toString() + ", " + 
					"`low` = " + t1.getLow().toString() + ", " + 
					"`close` = " + t1.getClose().toString() + ", " +
					"`direction` = '" + t1.getDirection() + "', " + 
					"`change` = " + t1.getChange().toString() + ", " + 
					"`lastbidprice` = " + t1.getLastBidPrice().toString() + ", " + 
					"`lastbidvolume` = " + t1.getLastBidVolume().toString() + ", " + 
					"`lastaskprice` = " + t1.getLastAskPrice().toString() + ", " + 
					"`lastaskvolume` = " + t1.getLastAskVolume().toString() + ", " +
					"`per` = " + t1.getPer().toString() + " " +
				"WHERE `symbol` = '" + t1.getSymbol() + "' AND `today` = '" + t1.getToday() + "'";
	}

	@Override
	String getInsertDuplicateUpdateSQL(StockRecord t1) {
		return	this.getInsertSQL(t1) + " " +
				"ON DUPLICATE KEY UPDATE " + 
					"`name` = '" + t1.getName() + "', " + 
					"`volume` = " + t1.getVolume().toString() + ", " + 
					"`transaction` = " + t1.getTransaction().toString() + ", " + 
					"`value` = " + t1.getValue().toString() + ", " + 
					"`open` = " + t1.getOpen().toString() + ", " + 
					"`high` = " + t1.getHigh().toString() + ", " + 
					"`low` = " + t1.getLow().toString() + ", " + 
					"`close` = " + t1.getClose().toString() + ", " +
					"`direction` = '" + t1.getDirection() + "', " + 
					"`change` = " + t1.getChange().toString() + ", " + 
					"`lastbidprice` = " + t1.getLastBidPrice().toString() + ", " + 
					"`lastbidvolume` = " + t1.getLastBidVolume().toString() + ", " + 
					"`lastaskprice` = " + t1.getLastAskPrice().toString() + ", " + 
					"`lastaskvolume` = " + t1.getLastAskVolume().toString() + ", " +
					"`per` = " + t1.getPer().toString()
				;
	}
	
	@Override
	String getSelectSQL(StockRecordPrimaryKey t2) {

		return	"SELECT * " + 
				"FROM `stockrecords` " +
				"WHERE `symbol` = '" + t2.getSymbol() + "' AND `today` = '" + t2.getToday() + "'";
	}*/

	@Override
	StockRecord getTableEntity(ResultSet rs) throws SQLException {
		
		return new	StockRecord(
						rs.getString("today"), 
						rs.getString("symbol"), 
						rs.getString("name"), 
						rs.getLong("volume"), 
						rs.getLong("transaction"), 
						rs.getLong("value"), 
						rs.getDouble("open"), 
						rs.getDouble("high"), 
						rs.getDouble("low"), 
						rs.getDouble("close"), 
						rs.getString("direction"),
						rs.getDouble("change"), 
						rs.getDouble("lastBidPrice"), 
						rs.getLong("lastBidVolume"), 
						rs.getDouble("lastAskPrice"), 
						rs.getLong("lastAskVolume"), 
						rs.getDouble("per")
					);
	}

	

	

	

}
