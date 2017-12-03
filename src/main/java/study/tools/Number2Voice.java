package study.tools;

public class Number2Voice {

    private static final String[] NUMBER_VOICE = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};

    private static final String NAGTIVE_VOICE = "负";

    private static final String DOT_VOICE = "点";

    private static final String[] UNIT_VOICE = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿"};

    public static String number2Voice(double number) {
        String numberLiteral = String.valueOf(number);

        StringBuffer sb = new StringBuffer(numberLiteral.length()*2);
        if(numberLiteral.startsWith("-")) {
            sb.append(NAGTIVE_VOICE);
            numberLiteral = numberLiteral.substring(1);
        }
        if(number == 0) {
            sb.append(NUMBER_VOICE[0]);
        }
        String numberString, scaleString = null;
        if(numberLiteral.contains(".")) {
            String[] split = numberLiteral.split("[.]");
            numberString = split[0];
            scaleString = split[1];
        } else {
            numberString = numberLiteral;
        }
        if(null != numberString && 0 != numberString.length()) {
            for(int i=0,len=numberString.length(); i<len; i++) {
                char ch = numberString.charAt(i);
                Integer currentNumber = Integer.valueOf(ch+"");
                sb.append(NUMBER_VOICE[currentNumber]).append(UNIT_VOICE[numberString.length()-1-i]);
            }
        }
        if(null != scaleString && 0 != scaleString.length()) {
            sb.append(DOT_VOICE);
            for(int i=0,len=scaleString.length(); i<len; i++) {
                int currentNumber = Integer.valueOf(scaleString.charAt(i)+"");
                sb.append(NUMBER_VOICE[currentNumber]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(number2Voice(-10.1123));
    }

}
