package idv.trashchu.finance.test;

import java.sql.SQLException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.trashchu.finance.util.DatabaseUtil;
import idv.trashchu.finance.util.Parsers;
import idv.trashchu.finance.dao.FinanceDAOFactory;
import idv.trashchu.finance.dao.IncomeStatementDAO;
import idv.trashchu.finance.entity.IncomeStatement;
import idv.trashchu.finance.entity.IncomeStatementPrimaryKey;
import idv.trashchu.finance.entity.TableEntity;

public class FinanceTest {

	private static Logger logger = LoggerFactory.getLogger(FinanceTest.class);



	
	//@Test
	public void testDB02() {
		
		try {
			
			IncomeStatement is = new IncomeStatement("201302", "2301", "測試", 0.5);
		
			IncomeStatementDAO isdao = (IncomeStatementDAO) FinanceDAOFactory.generateDAO(TableEntity.INCOMESTATEMENT, DatabaseUtil.PUREJDBC);
			
			isdao.insertOrUpdate(is);
			
			is = isdao.get(new IncomeStatementPrimaryKey("201302", "2301"));
			
			logger.info("IncomeStatement: " + is.getSeason() + ", " + is.getSymbol() + ", " + is.getName() + ", " + is.getEps());
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}
	
	@Test
	public void testDB03() {
		
		try {
			
			Parsers.ParseStockRecordHTML();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
