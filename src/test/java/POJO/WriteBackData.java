package POJO;

public class WriteBackData {
    private int rowNum;
    private int cellNum_response;
    private String response;
    private int cellNum_result;
    private String result;

    public WriteBackData() {
    }

    public WriteBackData(int rowNum, int cellNum_response, String response, int cellNum_result, String result) {
        this.rowNum = rowNum;
        this.cellNum_response = cellNum_response;
        this.response = response;
        this.cellNum_result = cellNum_result;
        this.result = result;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCellNum_response() {
        return cellNum_response;
    }

    public void setCellNum_response(int cellNum_response) {
        this.cellNum_response = cellNum_response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCellNum_result() {
        return cellNum_result;
    }

    public void setCellNum_result(int cellNum_result) {
        this.cellNum_result = cellNum_result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
