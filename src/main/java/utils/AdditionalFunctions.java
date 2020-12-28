package utils;

public class AdditionalFunctions {
    /**
     * Общий метод явного ожидания
     *
     * @param milliseconds - время ожидания в милисекундах
     */
    public static void sleepByInterval(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Преобразование цены из String в int
     *
     * @param str - значение для преобразования
     */
    public static Integer atoiInteger(String str) {
        str = str.replaceAll("\\D", "");
        return Integer.parseInt(str);
    }

    public static Double atoiDouble(String str) {
        str = str.replace(",", ".");
        str = str.replace(" ", "");
        return Double.parseDouble(str);
    }

}
