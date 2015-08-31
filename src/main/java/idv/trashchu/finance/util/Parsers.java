package idv.trashchu.finance.util;

import idv.trashchu.finance.Config;
import idv.trashchu.finance.dao.FinanceDAOFactory;
import idv.trashchu.finance.dao.IncomeStatementDAO;
import idv.trashchu.finance.dao.StockRecordDAO;
import idv.trashchu.finance.entity.IncomeStatement;
import idv.trashchu.finance.entity.IncomeStatementPrimaryKey;
import idv.trashchu.finance.entity.StockRecord;
import idv.trashchu.finance.entity.TableEntity;
import idv.trashchu.util.IntervalTimer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parsers {

	private static Logger logger = LoggerFactory.getLogger(Parsers.class);
	
	public static void ParseStockRecordHTML() throws IOException, SQLException {
		
		IntervalTimer it = new IntervalTimer();
		
		StockRecordDAO srdao = (StockRecordDAO) FinanceDAOFactory.generateDAO(TableEntity.STOCKRECORD, DatabaseUtil.PUREJDBC);
		
		
		String [] srHTMLs = new File(Config.stockrecordPath).list(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".htm");
	        }
	    });

		Arrays.sort(srHTMLs);

		for (int i = 0 ; i < srHTMLs.length ; i++) {
			
			logger.info(srHTMLs[i]);
			
			it.setStartTime();
			
			
			File sourceFile = new File(Config.stockrecordPath, srHTMLs[i]);
			File destinationFile = new File(Config.stockrecordPath + Config.bakDir, srHTMLs[i]);
			
			String currentDateTimeString = srHTMLs[i].substring(0, srHTMLs[i].lastIndexOf('.'));
			
			Document doc = Jsoup.parse(sourceFile, "MS950");
			
			Elements trs = doc.select("table[width=1000]").select("tbody").select("tr");
				
			//int count01 = 0, count02 = 0;
				
				
			for (Element tr : trs) {

				Elements tds = tr.select("td");
				
				if ((tds.size() == 16) && (tds.get(0).text().trim().length() == 4)) {

					
					StockRecord sr = new StockRecord();
						
					sr.setToday(currentDateTimeString);
					sr.setSymbol(tds.get(0).text().trim());
					sr.setName(tds.get(1).text().trim());
					sr.setVolume(Long.valueOf(tds.get(2).text().trim().replaceAll(",","")));
					sr.setTransaction(Long.valueOf(tds.get(3).text().trim().replaceAll(",","")));
					sr.setValue(Long.valueOf(tds.get(4).text().trim().replaceAll(",","")));
						
					if (tds.get(5).text().trim().equals("--"))
					{
						sr.setOpen(Double.valueOf((double) -1));
					}
					else
					{
						sr.setOpen(Double.valueOf(tds.get(5).text().trim().replaceAll(",","")));
					}
						
					if (tds.get(6).text().trim().equals("--"))
					{
						sr.setHigh(Double.valueOf((double) -1));
					}
					else
					{
						sr.setHigh(Double.valueOf(tds.get(6).text().trim().replaceAll(",","")));
					}
						
					if (tds.get(7).text().trim().equals("--"))
					{
						sr.setLow(Double.valueOf((double) -1));
					}
					else
					{
						sr.setLow(Double.valueOf(tds.get(7).text().trim().replaceAll(",","")));
					}
						
					if (tds.get(8).text().trim().equals("--"))
					{
						sr.setClose(Double.valueOf((double) -1));
					}
					else
		            {
						sr.setClose(Double.valueOf(tds.get(8).text().trim().replaceAll(",","")));
		            }

					sr.setDirection(tds.get(9).text().trim());
					sr.setChange(Double.valueOf(tds.get(10).text().trim().replaceAll(",","")));

					if (tds.get(11).text().trim().equals("--"))
		            {
						sr.setLastBidPrice(Double.valueOf((double) -1));
		            }
		            else
		            {
		                sr.setLastBidPrice(Double.valueOf(tds.get(11).text().trim().replaceAll(",","")));
		            }
					
					if (tds.get(12).text().trim().equals(""))
		            {
		                sr.setLastBidVolume(Long.valueOf((long) 0));
		            }
		            else
		            {
		                sr.setLastBidVolume(Long.valueOf(tds.get(12).text().trim()));
		            }
					
					if (tds.get(13).text().trim().equals("--"))
		            {
						sr.setLastAskPrice(Double.valueOf((double) -1));
		            }
		            else
		            {
		                sr.setLastAskPrice(Double.valueOf(tds.get(13).text().trim().replaceAll(",","")));
		            }

					if (tds.get(14).text().trim().equals(""))
		            {
		                sr.setLastAskVolume(Long.valueOf((long) 0));
		            }
		            else
		            {
		                sr.setLastAskVolume(Long.valueOf(tds.get(14).text().trim()));
		            }

					sr.setPer(Double.valueOf(tds.get(15).text().trim().replaceAll(",","")));
											

					
					
					
					
					srdao.insertOrUpdate(sr);
					

					//count02++;
					

				}
				
				//count01++;
			}
			
			
			
			if (destinationFile.exists()) {
				destinationFile.delete();
			}
			
			sourceFile.renameTo(destinationFile);
			
			
			it.setEndTime();
			
			logger.info("Execution Time: " + it.getInterval());
			
			
			//System.out.println(count01 + " : " + count02);


		}
	}
}
