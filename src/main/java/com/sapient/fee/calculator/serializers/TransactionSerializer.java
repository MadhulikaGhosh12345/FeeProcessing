package com.sapient.fee.calculator.serializers;

import com.sapient.fee.calculator.models.Transaction;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adityakumarghosh on 23/01/19.
 */
public class TransactionSerializer {

    public static List<Transaction> get_transactions_using_excel_sheet(Sheet sheet) {
        List<Transaction> transactions = new ArrayList<>();

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        // 2. Or you can use a for-each loop to iterate over the rows and columns



        for (Row row: sheet) {
            if (row.getRowNum() == sheet.getFirstRowNum()) {
                continue;
            }
            Transaction transaction = new Transaction();
            transaction.setExternalTxnId(dataFormatter.formatCellValue(row.getCell(0)));
            transaction.setClientId(dataFormatter.formatCellValue(row.getCell(1)));
            transaction.setSecurityId(dataFormatter.formatCellValue(row.getCell(2)));
            transaction.setTxnType(Transaction.TxnType.valueOf(dataFormatter.formatCellValue(row.getCell(3))));
            transaction.setTxnDate(get_local_date_from_string(dataFormatter.formatCellValue(row.getCell(4))));
            transaction.setMarketValue(Double.parseDouble(dataFormatter.formatCellValue(row.getCell(5))));
            transaction.setPriority(Transaction.Priority.valueOf(dataFormatter.formatCellValue(row.getCell(6))));
            transactions.add(transaction);
        }

        return transactions;

    }

    private static LocalDate get_local_date_from_string(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
        return LocalDate.parse(date, formatter);
    }

}
