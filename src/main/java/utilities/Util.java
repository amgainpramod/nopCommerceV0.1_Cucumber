package utilities;

import com.google.common.collect.Ordering;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {

    /***
     * Sleep for specified time with information
     * @param mSec
     * @param info
     */
    public static void sleep(long mSec, String info) {
        if (info != null) {
            System.out.println("Waiting for max :: " + mSec/1000 + " seconds " + info);
        }
        try {
            Thread.sleep(mSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Sleep for specified milliseconds
     * @param mSec
     */
    public static void sleep(long mSec) {
        sleep(mSec, null);
    }

    /***
     *
     * @param methodName
     * @param browserName
     * @return
     */
    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(browserName)
                .append("_")
                .append(methodName)
                .append("_")
                .append(localDateTime)
                .append(".png");
        return name.toString();
    }

    /***
     * Get a unique report name
     * @return
     */
    public static String getReportName(){
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder()
                .append("AutomationReport_")
                .append(localDateTime)
                .append(".html");
        return name.toString();
    }

    /***
     * Get Random number within specified range
     * @param min
     * @param max
     * @return a random number
     */
    public static int getRandomNumber(int min, int max) {
        int diff = max - min;
        int randomNum = (int) (min + Math.random() * diff);
        System.out.println("Random Number :: " + randomNum +
                " within range :: " + min + " and :: " + max);
        return randomNum;
    }

    /***
     * Get Random number within 1 and a specified number
     * @param number
     * @return a random number
     */
    public static int getRandomNumber(int number) {
        return getRandomNumber(1, number);
    }

    /***
     * Get a random string of specified length
     * @param length
     * @return a random string
     */
    public static String getRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }

    /***
     * Get a random string of length 10
     * @return a random string
     */
    public static String getRandomString() {
        return getRandomString(10);
    }

    /***
     * Get simple date as string in the specified format (ddMMyy, MMddyyyy)
     * @param format
     * @return date as string type
     */
    public static String getSimpleDateFormat(String format) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        System.out.println("Date with format :: " +
                format + " :: " + formattedDate);
        return formattedDate;
    }

    /***
     * Get Current date and time in dd_MM_yyyy HH_mm_ss format
     * @return current date and time as string type
     */
    public static String getCurrentDateTime() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        System.out.println("Date and Time :: " + date);
        return date;
    }

    /***
     * Verify if the actual text contains expected text
     * @param actualText
     * @param expText
     * @return true if actual text contains expected text; false otherwise.
     */
    public static boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())) {
            System.out.println("Actual Text From Web Application UI --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> " + expText);
            System.out.println("### Verification CONTAINS!!!");
            return true;
        } else {
            System.out.println("Actual Text From Web Application UI --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> " + expText);
            System.out.println("### Verification DOES NOT CONTAIN!!!");
            return false;
        }
    }

    /***
     * Verify if the actual text is equal to the expected text
     * @param actualText
     * @param expText
     * @return true if actual text is equal to expected text; false otherwise.
     */
    public static boolean verifyTextMatches(String actualText, String expText) {
        if (actualText.equals(expText)) {
            System.out.println("Actual Text From Web Application UI --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> " + expText);
            System.out.println("### Verification MATCHED!!!");
            return true;
        } else {
            System.out.println("Actual Text From Web Application UI --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> " + expText);
            System.out.println("### Verification DOES NOT MATCH!!!");
            return false;
        }
    }

    /***
     * Verify List is not empty
     * @param actualList - actual list that needs to be verified
     * @return
     */
    public static Boolean verifyListNotEmpty(List actualList) {
        int listSize = actualList.size();
        System.out.println("Size of list :: " + listSize);
        if (listSize > 0) {
            System.out.println("List is not empty");
            return true;
        } else {
            System.out.println("List is empty");
            return false;
        }
    }

    /***
     * Verify if actual list contains items of the expected list
     * @param actualList
     * @param expList
     * @return false if does not contain; true otherwise.
     */
    public static Boolean verifyListContains(List<String> actualList, List<String> expList) {
        int expListSize = expList.size();
        for (int i = 0; i < expListSize; i++) {
            if (!actualList.contains(expList.get(i))) {
                return false;
            }
        }
        System.out.println("Actual List Contains Expected List !!!");
        return true;
    }

    /***
     * Verify actual list matches every items on expected list
     * @param actualList
     * @param expList
     * @return true if matches; false otherwise.
     */
    public static Boolean verifyListMatches(List<String> actualList, List<String> expList) {
        boolean found = false;
        int expListSize = expList.size();
        int actualListSize = actualList.size();
        if (actualListSize != expListSize) {
            return false;
        }
        for (String s : actualList) {
            found = false;
            for (String value : expList) {
                if (verifyTextMatches(s, value)) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            System.out.println("Actual List Matches Expected List !!!");
            return true;
        } else {
            System.out.println("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    /***
     * Verify item is present in the list
     * @param actualList
     * @param item
     * @return true if item is in the list; false otherwise.
     */
    public static Boolean verifyItemPresentInList(List<String> actualList, String item){
        int actualListSize = actualList.size();
        for(int i = 0; i < actualListSize; i++){
            if(actualList.contains(item)){
                System.out.println("Item PRESENT in the List !!!");
                return true;
            }
        }
        System.out.println("Item NOT PRESENT in the List !!!");
        return false;
    }

    /***
     * Verify if a list is in ascending order
     * @param list
     * @return true if in ascending order; false otherwise
     */
    public static boolean isListInAscendingOrder(List<Long> list){
        boolean sorted = Ordering.natural().isOrdered(list);
        return sorted;
    }
}
