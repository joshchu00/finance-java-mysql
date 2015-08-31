package idv.trashchu.finance.dao;

import idv.trashchu.finance.dao.purejdbc.IncomeStatementPureJDBCDAO;
import idv.trashchu.finance.dao.purejdbc.StockRecordPureJDBCDAO;
import idv.trashchu.finance.entity.TableEntity;
import idv.trashchu.finance.util.DatabaseUtil;

public class FinanceDAOFactory {

	public static GenericDAO generateDAO(String entity, String database) {
		
		GenericDAO g = null;
		
		switch (database) {
				
			case DatabaseUtil.PUREJDBC:
				switch (entity) {
					case TableEntity.STOCKRECORD:
						g = new StockRecordPureJDBCDAO();
						break;
						
					case TableEntity.INCOMESTATEMENT:
						g = new IncomeStatementPureJDBCDAO();
						break;
					
					default:
						break;
				}
				break;
			
			default:
				break;
		}
		
		return g;
	}
}
