package zyb.design.ssm.utils.excel;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description Excel 解析 Exception
 * @Date 2018-06-06
 * @Time 15:56
 */
//抛出Excel异常
public class ExcelException extends RuntimeException {
    public ExcelException(String message) {
        super(message);
    }
}
