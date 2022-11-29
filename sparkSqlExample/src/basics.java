import java.util.*;
import java.util.stream.Collectors;

public class basics {
public static void main(String[] args) {

    Map<Integer, String> map = new HashMap<>();
    map.put(11,"neha");
    map.put(21,"anamika");


  // printing key value in loop
    for( Map.Entry<Integer, String> map2entryset : map.entrySet()){
        Integer  map2keys=map2entryset.getKey();
        String  map2values=map2entryset.getValue();
        System.out.println("map2keys is "+map2keys);
        System.out.println("map2values is  "+map2values);
    }



    Set<Integer> setOfKeys=map.keySet();
    Collection<String> collectionOfValues= map.values();

    System.out.println("setOfKeys in hashmap **"+setOfKeys);
    System.out.println("collectionOfValues in hashmap **"+collectionOfValues);

    //not getting key value. using for each is wrong approach
    for(int i =1;i<=map.size();i++){
        System.out.println("individual key "+i+" individual value  "+map.get(i));

    }

// String methods join & concat()
    String joinedStringOfValuesByDelimiter = String.join(",", collectionOfValues).concat(",created_timestamp").concat(",created_by");
    System.out.println("joinedStringOfValuesByDelimiter  is **"+joinedStringOfValuesByDelimiter);

    //printing array
    String[] number1={"1","2","3"};
    for(int i=0; i< number1.length;i++) {
        System.out.println(" printing array of number1 " + number1[i]);
    }
    String joinedString1="";
    for (int j=0;j<number1.length;j++) {
         joinedString1 = joinedString1+"-"+number1[j];
          }
    System.out.println(" joinedString1 is " + joinedString1);


    //printing list
   List<String> number2= new ArrayList<>();
   number2.add("1");
   number2.add("2");
   number2.add("3");
   System.out.println(" printing list of number2 "+number2);

    String joinedString2=String.join("-",  number2);
    System.out.println(" joinedString2 is "+joinedString2);


    Set<String> number3= new HashSet<>();
    number3.add("1");
    number3.add("2");
    number3.add("3");
    System.out.println(" printing set of number3 "+number3);

    String joinedString3=String.join("-",  number3);
    System.out.println(" joinedString3 is "+joinedString3);


    Map<String,String> number4= new HashMap();
    number4.put("1","one");
    number4.put("2","two");
    number4.put("3","three");
    System.out.println(" printing map of number4 "+number4);


    String joinedString4=String.join("-",  number4.values());
    System.out.println(" joinedString4 is "+joinedString4);

    if(number4.containsKey("3")){
        System.out.println("get the value of that key "+number4.get("3"));

        Set<Map.Entry<String, String>> afbc= number4.entrySet();
        System.out.println("number4.entrySet() is "+number4.entrySet());

       // number4.entrySet() is [1=one, 2=two, 3=three]



        Iterator<String> itr = number4.keySet().iterator();
        while(itr.hasNext()){
            String key = itr.next();
            System.out.println("key inside iterator is "+key);
        }

        //number4.entrySet() is [1=one, 2=two, 3=three]
        Iterator<Map.Entry<String, String>> itr1 = number4.entrySet().iterator();
        while(itr1.hasNext()){
            Map.Entry<String, String> obj  = itr1.next();
            System.out.println("obj inside iterator is "+obj);
        }

    }


    // String reversal

    String normal="normal";
    String reversed="";
    System.out.println("reversed NON "+normal.length());
    for(int i=0;i<=normal.length()-1;i++){
        reversed=normal.charAt(i)+reversed;
        System.out.println("reversed "+reversed);
    }
    System.out.println("reversed IS "+reversed);





    /*  STRING BASIC FUNCTIONS    */

    // 1) length for array VS length() for string  VS size() for list ,set,map
    int[] inte={1,2,3,5,6};
  //or
  char[] ch={'a','b','c','d'};
  String str= new String(ch);
    int len=ch.length;   // length fun for array
    System.out.println("len "+len);



    String abcs= "we cant stop";
  int length=abcs.length();    // length() fun for string
    System.out.println("length IS "+length);

   List<String>  abwq= new ArrayList<String>();
    abwq.add("4");
    System.out.println("size Of LIST IS "+abwq.size());




    int[] anc={1,3,4,5};

    /* lIST initiazlisation  TYPE 1*/
   List<String> mylist= new ArrayList<>();
    mylist.add("1");
    mylist.add("2");

     System.out.println(" lIST initiazlisation  TYPE 1 "+mylist);


//javatpoint.com


    /* lIST initiazlisation  TYPE 2 AND methods */

  List<String> list2=new ArrayList<String>(Arrays.asList("1","2","3","4","5","6"));

    System.out.println(" lIST initiazlisation  TYPE 2 "+list2);

    boolean res=list2.equals(mylist);
    System.out.println("lIST equals to another list or collection res  "+ res);


    System.out.println(" lIST HASHCODE "+ list2.hashCode());

    System.out.println(" lIST addALL aNOTHER LIST OR COLECTION "+list2.addAll(mylist));
    System.out.println(" lIST last indexof element in list "+list2.lastIndexOf("2"));

    System.out.println(" lIST2 "+ list2);

    list2.indexOf("3");

    System.out.println(" lIST2 INDEX OF  "+ list2.indexOf("3"));


    System.out.println(" lIST2 containsAll OF  "+  list2.containsAll(setOfKeys));

    System.out.println(" printing lISTS 2  "+list2);

   boolean gh=list2.removeIf(s -> s.contains("1"));
    System.out.println(" gh "+gh);

    List <String> subList=list2.subList(2,5);
    list2.set(2,"hi");
    System.out.println(" subList "+subList);

    int as =list2.toString().split(",").length;  // SPLIT METHOD 1
    System.out.println(" as  "+subList);

    long hu =list2.stream().spliterator().estimateSize();  // split METHOD 2 ON STREAM ITERATORS
    System.out.println(" hu splitartor "+hu);



    List<String> mysorted=list2.stream().sorted().collect(Collectors.toList());  // SORTING OF LIST AFTER CONVERSION TO STREAM IN JAVA 8


    Collections.sort(list2);
    System.out.println(" sort using collections "+list2);

            list2.sort(Comparator.naturalOrder());   // WITHout CONVERTING TO STREAM  JAVA 7
    System.out.println(" sort(Comparator.naturalOrder() "+list2);

    list2.sort(Comparator.reverseOrder()); // WITHout CONVERTING TO STREAM  java 7
    System.out.println(" sort(Comparator.reverseOrder() "+list2);

    list2.sort(String.CASE_INSENSITIVE_ORDER);


   String[] listToArrayConversion = (String[]) list2.toArray();  //CONVERTING LIST INTO ARRAY
    System.out.println(" listToArrayConversion "+listToArrayConversion);

    Integer x=5;
    Integer y=10;
    if(x.equals(y))   {}  // boolean equals(Object y)    ONLY COMAPRE OBJECTS (Integer )NOT int .


    /*
    * STring fUNcTION*/

    String beliver=" BElIEVER_believer ";
     char ch1=beliver.charAt(5);
    System.out.println(" believer charAt"+ch1);


    String beliversubstring=beliver.substring(5);
    System.out.println(" believer substring "+beliversubstring);

    String beliversubstring1=beliver.substring(5,9);
    System.out.println(" believer substring "+beliversubstring1);


    String beliversubstring2=String.join("_#_",beliversubstring,"WELCOME","NEHA");
    System.out.println("  beliversubstring joined "+beliversubstring2);

    boolean beliversubstring3 =beliversubstring2.equals(beliver);
    System.out.println("  beliversubstring3 equals  "+beliversubstring3);


    String beliversubstring4=beliversubstring2.replace("er","qq");
    System.out.println("  beliversubstring4 REPLACE  "+beliversubstring4);


    String[] beliversubstring5=beliversubstring4.split("_",2);
    System.out.println("  beliversubstring5   "+beliversubstring5[1]);

    String intern=new String("TWITTER").intern();

    System.out.println("  intern   "+intern);


    System.out.println("  indexof char   "+beliversubstring2.indexOf('e',8)); // int indexOf(string str1, int fromindex);

    System.out.println("  indexof   substring "+beliversubstring2.indexOf("eC",10));  //int indexof(string str, int fromindex);

    System.out.println("  indexof   TRIM **"+ beliver.trim()+"**");  // REMOVES BEGINNING AND ENDING SPACE



   /* The java string valueOf() method converts different types of values into string.
   By the help of string valueOf() method, you can convert int to string, long to string, boolean to string, character to string, float to string,
   double to string, object to string and char array to string.

       public static String valueOf(boolean b)
    public static String valueOf(char c)
    public static String valueOf(char[] c)
    public static String valueOf(int i)
    public static String valueOf(long l)
    public static String valueOf(float f)
    public static String valueOf(double d)
    public static String valueOf(Object o)
    */


    int intValue= 125;
    float floatValue=12.5f;
    double doubleValue=12.4;
    String intToString=String.valueOf(intValue);
    String floatToString=String.valueOf(floatValue);
    String doubleToString=String.valueOf(doubleValue);

    System.out.println(" intToString "+intToString);
    System.out.println(" floatToString "+floatToString);
    System.out.println(" doubleToString "+doubleToString);

    String abx=String.valueOf(intValue);

    Integer we=Integer.parseInt("123");  // int parseInt(string s) converts string into int .
    System.out.println(" we "+we);


    Double double1=Double.parseDouble("123");
    System.out.println(" double1 "+double1);


    String str2="WELCOME TO HOME 2";
    String[] arr= str2.split(" ");



    String formatted=String.format("VALUE is %x",12345);
    System.out.println(" formatted "+formatted);


















    //  public boolean isPalindrome() {
        /*    int x=121;
            boolean returnedValue=false;
            String valueInt=String.valueOf(x);
            for(int i=String.valueOf(x).length();i>=0;i--){
                System.out.println("i & j is  "+i+" ** ");

                for(int j=1;j<=(String.valueOf(x).length())/2;j++){
                //    System.out.println("i & j is  "+i+" ** "+j);


                    if((valueInt.charAt(i))==(valueInt.charAt(j))){
                        returnedValue=true;
                    }
                    else{
                        returnedValue=false;
                    }
                }
            }
            //return returnedValue;
      //  }*/




}






















}
