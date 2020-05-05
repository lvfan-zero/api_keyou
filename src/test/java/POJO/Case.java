package POJO;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.NotNull;


public class Case {

    @Excel(name = "用例编号")
    @NotNull
    private String caseId;
    @Excel(name = "接口编号")
    private String apiId;
    @Excel(name = "测试项")
    private String test;
    @Excel(name = "测试子项")
    private String test_1;
    @Excel(name = "测试二级子项")
    private String test_2;
    @Excel(name = "测试类型")
    private String testType;
    @Excel(name = "前置条件")
    private String precond;
    @Excel(name = "测试步骤")
    private String step;
    @Excel(name = "参数列表")
    private String param;
    @Excel(name = "预期响应结果")
    private String response_expected;
    @Excel(name = "实际响应结果")
    private String actual;
    @Excel(name = "检验SQL")
    private String sql;
    @Excel(name = "结论")
    private String result;
    @Excel(name = "备注")
    private String note;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest_1() {
        return test_1;
    }

    public void setTest_1(String test_1) {
        this.test_1 = test_1;
    }

    public String getTest_2() {
        return test_2;
    }

    public void setTest_2(String test_2) {
        this.test_2 = test_2;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getPrecond() {
        return precond;
    }

    public void setPrecond(String precond) {
        this.precond = precond;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResponse_expected() {
        return response_expected;
    }

    public void setResponse_expected(String response_expected) {
        this.response_expected = response_expected;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId='" + caseId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", test='" + test + '\'' +
                ", test_1='" + test_1 + '\'' +
                ", test_2='" + test_2 + '\'' +
                ", testType='" + testType + '\'' +
                ", precond='" + precond + '\'' +
                ", step='" + step + '\'' +
                ", param='" + param + '\'' +
                ", response_expected='" + response_expected + '\'' +
                ", actual='" + actual + '\'' +
                ", sql='" + sql + '\'' +
                ", result='" + result + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
