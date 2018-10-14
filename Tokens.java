package arthematicCalculator;
public enum Tokens {

    OPEN_BRACES("("),
    SUBSTRACTION("-"),
    ADDITION("+"),
    MUTIPLICATION("*"),
    DIVISION("/"),
    POWER("^"),
    CLOSING_BRACES(")");

    private String operation;

    private Tokens(String operation) {
        this.operation = operation;
    }

    public String getOperation(){
        return this.operation;
    }

    public static Tokens getToken(String token){
        Tokens tt=null;
        for(Tokens t: Tokens.values()){
            if(t.getOperation().equalsIgnoreCase(token))
                tt=t;
        }
        return tt;
    }
}
