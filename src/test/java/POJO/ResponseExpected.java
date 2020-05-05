package POJO;

public class ResponseExpected {
    private String expression;
    private String value;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ResponseExpected{" +
                "expression='" + expression + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
