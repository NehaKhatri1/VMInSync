public class test {
    public static void main(String[] args){
        String returnedValue=XorStrings();
        //System.out.println("retrunedValuer is "+returnedValue);
        String returnedVal=PalindromeStrings();
        System.out.println("returnedVal is "+returnedVal);

    }

   public static String XorStrings(){
       String firstString= "1";
       String SecondString= "0111";
        Integer returned =(Integer.parseInt(firstString)) ^ ( Integer.parseInt(SecondString));
       System.out.println("returned is "+returned);

       String returnedString =String.valueOf(returned);
       return returnedString;

   }
    public static String PalindromeStrings(){
        String any= "mAdam";
       // boolean flag = false;
        int n=any.length()-1;
        for(int i=0; i<=n/2;i++){
           char first=any.charAt(i);
            String firstString=String.valueOf(first);
            char second=any.charAt(n-i);
            String secondString=String.valueOf(second);

            if(firstString.equalsIgnoreCase(secondString)){
               // do nothing
           }
           else return "not palindrome";
        }
        return "palindrome";
    }


}
