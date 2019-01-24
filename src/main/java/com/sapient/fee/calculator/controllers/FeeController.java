package com.sapient.fee.calculator.controllers;

import com.sapient.fee.calculator.models.Transaction;
import com.sapient.fee.calculator.service.CalculateFeeServiceImpl;
import com.sapient.fee.calculator.utils.ExcelReader;
import com.sapient.fee.calculator.utils.TransactionComparator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sapient.fee.calculator.serializers.TransactionSerializer.get_transactions_using_excel_sheet;

@RestController
public class FeeController {

    @RequestMapping("/processing_fees")
    public List<String> index() throws IOException, InvalidFormatException {
        CalculateFeeServiceImpl calculateFeeService = new CalculateFeeServiceImpl();
        List<Transaction> finalTxns = calculateFeeService.calculateFee(get_transactions_using_excel_sheet(ExcelReader.get_data()));
        finalTxns.sort(new TransactionComparator());
        List<String> response = new ArrayList<>();
        for (Transaction transaction: finalTxns) {
            response.add(transaction.toString());
        }
        return response;
    }

}
