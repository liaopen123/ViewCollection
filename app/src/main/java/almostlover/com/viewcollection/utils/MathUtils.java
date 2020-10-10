package almostlover.com.viewcollection.utils;

import androidx.annotation.Nullable;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 日期工具类A
 *
 * @author fada
 * DecimalFormat("0.00")已0占位
 * DecimalFormat("#.##")不显示
 */
public class MathUtils {

    private static final String TAG = "MathUtils";
    static String[] units = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿",
            "十亿", "百亿", "千亿", "万亿"};
    static char[] numArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 将String类型的数字转换成没有.00,如果有小数则显示小数
     *
     * @param data
     * @return
     */
    public static String formatData(String data) {
//        if (ObjectNullUtils.strintIsNull(data))
//            return "";
        double number1 = Double.parseDouble(data);
        DecimalFormat df = new DecimalFormat("0.00");
        data = df.format(number1);
        double number = Double.parseDouble(data);
//        Logger.e("MathUtils", "double" + number + "int" + (int) number);
        if ((int) number == number) {
            //说明是整数
            return (int) number + "";
        } else {
            //保留小数点后两位
            if (data.endsWith("0")) {
                return data.substring(0, data.length() - 1);
            } else {
                return data;
            }
        }
    }

    /**
     * 将String类型的数字转换成没有.00,如果有小数则显示小数
     *
     * @param data
     * @return
     */
    public static String formatDataWithDot(String data) {
        try {
            double number1 = Double.parseDouble(data);
            DecimalFormat df = new DecimalFormat("0.00");
            data = df.format(number1);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }


    }

    /**
     * 将字符串三位一个逗号显示  保留两位小数
     *
     * @param data
     * @return
     */
    public static String formatData2(String data) {
        if (TextUtils.isEmpty(data))
            return "";

        double number1 = Double.parseDouble(data);
        DecimalFormat df = new DecimalFormat("###,###,##0.00");
        return df.format(number1);
    }

    /**
     * 将字符串三位一个逗号显示  123,451并且保留一位或两位小数
     *
     * @param data
     * @return
     */
    public static String formatData5(String data) {
        if (TextUtils.isEmpty(data))
            return "";
        double number1 = Double.parseDouble(data);
        DecimalFormat df = new DecimalFormat("0.00");
        data = df.format(number1);
        double number = Double.parseDouble(data);
//        Logger.e("MathUtils", "double" + number + "int" + (int) number);
        if ((int) number == number) {
            //说明是整数
            DecimalFormat intdata = new DecimalFormat("###,##0");
            String intnumber = intdata.format(Double.parseDouble("" + (int) number));
            return intnumber;
        } else {
            //保留小数点后两位
            if (data.endsWith("0")) {
                DecimalFormat doubdata1 = new DecimalFormat("###,##0.#");
                String doublenumber1 = doubdata1.format(Double.parseDouble(data.substring(0, data.length() - 1)));
                return doublenumber1;
            } else {
                DecimalFormat doubdata2 = new DecimalFormat("###,##0.##");
                String doublenumber2 = doubdata2.format(Double.parseDouble(data));
                return doublenumber2;
            }
        }
    }

    /**
     * 将字符串三位一个逗号显示  123,451
     *
     * @param str
     * @return
     */
    public static String getString1(String str) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(Double.parseDouble(str));
    }

//    public static String formatDataString(String str) {
////        if (StringJudgment.isEmpty(str)) {
////            return "";
////        } else {
////            DecimalFormat df = new DecimalFormat("###,###,##0.00");
////            return df.format(Double.parseDouble(str));
////        }
//
//
//    }


    /**
     * 将字符串三位一个逗号显示  123,451
     *
     * @param str
     * @return
     */
    public static String getString(String str) {
        DecimalFormat df = new DecimalFormat("###,###.0#");
        return df.format(Double.parseDouble(str));
    }

    /**
     * 将字符串三位一个逗号显示  123,451
     *
     * @param str
     * @return
     */
    public static String formatString(String str) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(Double.parseDouble(str));
    }

    /**
     * 把数字转换成汉字
     *
     * @param num 10000
     * @return 1万
     */
    public static String formatInteger(int num) {
        String[] str = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String ss[] = new String[]{"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};
        String s = String.valueOf(num);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
        }
        String sss = String.valueOf(sb);
        int i = 0;
        for (int j = sss.length(); j > 0; j--) {
            sb = sb.insert(j, ss[i++]);
        }
        return sb.toString();
    }

    public static String formatMoney(double money) {
        if (money >= 1000 && money < 10000) {
            String moneys = String.valueOf((int) money);
            return moneys.substring(0, moneys.length() - 3) + "千";
        }
        if (money >= 10000) {
            String moneys = String.valueOf((int) money);
            return moneys.substring(0, moneys.length() - 4) + "万";
        }

        return null;
    }

    public static boolean isInt(String num) {
        try {
            DecimalFormat doubleFormat = new DecimalFormat("0.00");
            Number doubleNum = doubleFormat.parse(num);

            DecimalFormat intFormat = new DecimalFormat("0");
            Number intNum = intFormat.parse(num);

            return doubleNum.doubleValue() == intNum.doubleValue();     //有和没有小数点的数值一样则
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 将String类型的数字转换成没有.00,如果有小数则显示小数
     * 向下取值
     *
     * @param data
     * @return
     */
    public static String formatDataWithoutEnds(String data) {
        double number1 = Double.parseDouble(data);
        DecimalFormat df = new DecimalFormat("0.000");
        data = df.format(number1);
        double number = Double.parseDouble(data);
//        Logger.e("MathUtils", "double" + number + "int" + (int) number);
        if ((int) number == number) {
            //说明是整数
            return (int) number + "";
        } else {
            //保留小数点后两位
            if (data.endsWith("00")) {
                return data.substring(0, data.length() - 2);
            } else if (data.endsWith("0")) {
                return data.substring(0, data.length() - 1);
            } else {
                return data.substring(0, data.length() - 1);
            }
        }
    }


    public static boolean isNumeric(String str) {
        double number = Double.parseDouble(str);
//        Logger.e("MathUtils", "double" + number + "int" + (int) number);
        if ((int) number == number) {
            //说明是整数
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化充值金额   整数后面加+"元"  小数完整显示
     *
     * @param amount 交易金额
     * @return 格式化后的
     */
    public static String formatRechargeMoney(String amount) {
        try {
            if (isNumeric(amount)) {
                return formatData2(amount) + "元";
            } else {
                return formatData2(amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "--";
        }
    }

    /**
     * 将字符串三位一个逗号显示  123,451，不保留小数
     *
     * @param data
     * @return
     */
    @Nullable
    public static String formatData3(String data) {
        if (TextUtils.isEmpty(data))
            return null;
        double number1 = Double.parseDouble(data);
        DecimalFormat df = new DecimalFormat("0.00");
        data = df.format(number1);
        double number = Double.parseDouble(data);
//        Logger.e("MathUtils", "double" + number + "int" + (int) number);
        if ((int) number == number) {
            //说明是整数
            DecimalFormat intdata = new DecimalFormat("###,##0");
            String intnumber = intdata.format(Double.parseDouble("" + (int) number));
            return intnumber;
        } else {
            //保留小数点后两位
//            if (data.endsWith("0")) {
//                DecimalFormat doubdata1 = new DecimalFormat("###,##0.#");
//                String doublenumber1 = doubdata1.format(Double.parseDouble(data.substring(0, data.length() - 1)));
//                icon_return doublenumber1;
//            } else {
//                DecimalFormat doubdata2 = new DecimalFormat("###,##0.##");
//                String doublenumber2 = doubdata2.format(Double.parseDouble(data));
//                icon_return doublenumber2;
//            }
            DecimalFormat doubdata2 = new DecimalFormat("###,###");
            String doublenumber2 = doubdata2.format(Double.parseDouble(data));
            return doublenumber2;
        }
    }

    /**
     * @param data
     * @return 展示两位小数
     */
    @Nullable
    public static String formatData4(String data) {
        if (TextUtils.isEmpty(data))
            return "";

        double number1 = Double.parseDouble(data);
        DecimalFormat df = new DecimalFormat("#########0.00");
        return df.format(number1);

//
    }


    public static double sub(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.subtract(p2).doubleValue();
    }

    public static String unitTransfer(String params) {
        try {
            int param = (int) Double.parseDouble(params);
            if (param == 0) {
                return "0";
            }

            int hundredMillion = 0;//亿
            int tenThousand = 0;//万
            int unit = 0;//个
            int temp = 0;
            int investCount = param;
            if (investCount > 100000000) {
                hundredMillion = (int) Math.floor(investCount / 100000000);
                temp = investCount - hundredMillion * 100000000;

                if (temp > 10000) {
                    tenThousand = (int) Math.floor(temp / 10000);
                } else {
                    unit = temp;
                }

            } else if (investCount >= 10000) {

                tenThousand = (int) Math.floor(investCount / 10000);
                unit = investCount - tenThousand * 10000;
            } else {
                unit = investCount;
            }

            StringBuffer investCountTransfer = new StringBuffer();
            if (hundredMillion > 0) {
                investCountTransfer.append(hundredMillion).append("亿");
            }
            if (tenThousand > 0) {
                investCountTransfer.append(tenThousand).append("万");
            }
            if (unit > 0) {
                investCountTransfer.append(unit);
            }

            return investCountTransfer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return params;
        }
    }

    /**
     * 把金额转化成万元的单位
     *
     * @param params
     * @return
     */
    public static String transferWan(String params) {
        try {
            Double param = Double.parseDouble(params);

            if (param == 0) {
                return "0";
            }

            if (param >= 10000) {
                double v = param / 10000;
//                DecimalFormat doubdata2 = new DecimalFormat("###,##0.####");
//                String doublenumber2 = doubdata2.format(v);
                StringBuffer investCountTransfer = new StringBuffer();
                if (v == (int) v) {
                    investCountTransfer.append((int) v).append("万");
                } else {
                    investCountTransfer.append(v).append("万");
                }
                return investCountTransfer.toString();
            } else {
                return formatData(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return params;
        }
    }

    /**
     * 比较2个金额的大小   如果1大于2   icon_return true   如果2大于1  icon_return false
     */
    public static boolean compare(String num1, String num2) {
        try {
            return Double.parseDouble(num1) >= Double.parseDouble(num2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param length 随机数的长度
     * @return 返回长度为length的随机数
     */
    public static String getNumber(int length) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += random.nextInt(10);
        }
        return result;
    }


    public static String get6Number() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    public static String get4Number() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    //随机生成 00  01  02
    public static String getRandomNumber() {
        Random random = new Random();
        String result = "0" + random.nextInt(2);
        return result;
    }

    //还原科学计数法导致的E
    public static String getRealNumber(Double num) {
        try {
            return new DecimalFormat("0.00").format(num);
        } catch (Exception e) {
            e.printStackTrace();
            return num + "";
        }
    }

    /**
     * 加法运算
     *
     * @param m1
     * @param m2
     * @return
     */
    public static double add(double m1, double m2) {
        BigDecimal p1 = new BigDecimal(Double.toString(m1));
        BigDecimal p2 = new BigDecimal(Double.toString(m2));
        return p1.add(p2).doubleValue();
    }

    /**
     * 减法运算
     *
     * @return
     */
    public static double sub(String m11, String m22) {
        try {
            double m1 = Double.parseDouble(m11);
            double m2 = Double.parseDouble(m22);
            BigDecimal p1 = new BigDecimal(Double.toString(m1));
            BigDecimal p2 = new BigDecimal(Double.toString(m2));
            return p1.subtract(p2).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    //是否为零的校验
    public static boolean isZero(String number) {


        int numberInt = Integer.parseInt(number);
        double numberDouble = Double.parseDouble(number);
        if (numberInt == (int) numberDouble) {
            if (numberInt == 0 || numberDouble == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 账户余额 小于 起投金额   传起投金额
     * 账户余额  大于 起投金额 传 最大的可用的余额   100起投 100递增  450余额   返回400
     *
     * @param accuntBalance
     * @param begainMoney
     * @param stepMoney
     * @return
     */
    public static String getCanInputMoney(String accuntBalance, String begainMoney, String stepMoney) {
        try {
            double accuntBalance1 = Double.parseDouble(accuntBalance);//账户余额
            double begainMoney1 = Double.parseDouble(begainMoney);//起投金额
            double stepMoney1 = Double.parseDouble(stepMoney);//递增金额
            if (accuntBalance1 < begainMoney1) {
                return begainMoney;
            } else {
                double extralMoney = accuntBalance1 - begainMoney1;
                int step = (int) (extralMoney / stepMoney1);
                return step * stepMoney1 + begainMoney1 + "";//money
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param pwd
     * @return true  校验成功  false  校验不成
     */
    public static boolean passwordVerifiers(String pwd) {
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return pwd.matches(reg);
    }

    /**
     * 取余数
     *
     * @return
     */
    public static int getParts(int totalSize, int step) {
        int i = totalSize % step;
        if (i == 0) {
            //刚好能整除
            return totalSize / step;
        } else {
            return (totalSize / step) + 1;
        }
    }

    /**
     * 把金额转化成万元的单位
     * 第一个是金额
     * 第二个是元 或者万元
     *
     * @param params
     * @return
     */
    public static String[] transferWan1(String params) {
        String[] strings = {"", "元"};
        try {
            Double param = Double.parseDouble(params);

            if (param == 0) {
                strings[0] = "0";
                return strings;
            }

            if (param >= 10000) {
                double v = param / 10000;
//                DecimalFormat doubdata2 = new DecimalFormat("###,##0.####");
//                String doublenumber2 = doubdata2.format(v);
                StringBuffer investCountTransfer = new StringBuffer();
                if (v == (int) v) {
                    investCountTransfer.append((int) v);
                } else {
                    investCountTransfer.append(v);
                }
                strings[0] = investCountTransfer.toString();
                strings[1] = "万元";
                return strings;
            } else {
                strings[0] = formatData(params);
                return strings;
            }
        } catch (Exception e) {
            e.printStackTrace();
            strings[0] = params;
            return strings;
        }
    }


    public static String deleteCharString(String sourceString, String chElemData) {
        String tmpString = "";
        tmpString += chElemData;
        StringBuffer stringBuffer = new StringBuffer(sourceString);
        int iFlag = -1;
        do {
            iFlag = stringBuffer.indexOf(tmpString);
            if (iFlag != -1) {
                stringBuffer = stringBuffer.replace(iFlag, iFlag + 1, "");
            }
        } while (iFlag != -1);
        return stringBuffer.toString();
    }

    /**
     * 生成 范围在[minValue,maxValue)的值 但不包含maxValue
     * @param minValue
     * @param maxValue
     * @return
     */
    public static int getRangeOf(int minValue,int maxValue){
        return (int) (minValue+(maxValue-minValue)*(new Random().nextDouble()));
    }
}
