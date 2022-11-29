package resources;


import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

public class java8BasicsExamples {

    /*

    Stream Operations
Intermediate Operations


    filter()
    map()
    flatMap()
    distinct()
    sorted()   // stateful operation because sorting depends last element as well.
    peek()  // used for logging & debugging .prints all elements for debugging .
    limit()  // limits elements . // short circuit intermediate operation .
    skip()  // skips first n elements  // short circuit intermediate operation .


     Lazy Evaluation
Computation on the source data is only performed when the terminal operation is initiated,
 and source elements are consumed only as needed.


All intermediate operations are lazy, so they’re not executed until a
result of a processing is actually needed.


Terminal Operations

    forEach()
    forEachOrdered()
    toArray()
    reduce()
    collect()  // collect(collectors.toMap()) collect(collectors.toList()) & collect(Collectors.toSet());
    min()   // both min & max is stateful operations because min depends last element as well.
    max()  // The max() method is a terminal operation. So the Stream cannot be used after this method has been executed.
    count()
    anyMatch() // short circuit terminal operation.
    allMatch()
    noneMatch()
    findFirst()  // short circuit terminal operation
    findAny()
     */

    public static void main(String[] args) throws Throwable {
        // write your code here
        System.out.println("hello neha . So good to see you here");



        // example 1 stream of integers
        Stream<Integer> stream = Stream.of(1, 2, 4, 5, 7, 9);
        stream.forEach(a -> System.out.println(a));




        // example 2 stream of array
        System.out.println("creating a stream from a array trial type 1");
        Integer[] abc = {2, 5, 8, 6, 3};
        Stream<Integer> arrayOfStreams = Stream.of(abc);
        arrayOfStreams.forEach(b -> System.out.println(b));


        System.out.println("creating a stream from a array Type 2");
        Stream<Integer> stream2 = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream2.forEach(p -> System.out.println(p));



        System.out.println("creating a stream from List type 1 ");
        List<Integer> listOfInt= new ArrayList<Integer>();
        listOfInt.add(4);
        listOfInt.add(9);
        listOfInt.add(7);
        listOfInt.stream().forEach(a->System.out.println(a));


        System.out.println("creating a stream from List type 2");
        List<Integer> listOfInt2= new ArrayList<Integer>();
        for (int i=0;i<=10;i++){
            listOfInt2.add(i);
        }
        listOfInt2.stream().forEach(a->System.out.println(a));



        System.out.println("creating a stream from List type 3");
        List<Integer> listOfInt3= Arrays.asList(1,5,3,9);
        listOfInt3.stream().forEach(a->System.out.println(a));

        System.out.println("creating a stream from generate & random method");
        List<Integer> randomNumbers = Stream
                .generate(() -> (new Random()).nextInt(100)).limit(3).collect(Collectors.toList());


        Long count=  Stream.generate(() -> (new Random()).nextInt(100)).limit(10).count();
        System.out.println("counts are "+count);

        // Stream.generate(() -> (new Random()).nextInt(100)).limit(3).forEach(e-> System.out.println(e));

        // splitting character array
        Stream<String> stream5 = Stream.of("A$B$C".split("\\$"));
        stream5.forEach(p -> System.out.println(p));

        String[] abcd={"we","are","one"};
        Stream<String> strin= Stream.of(abcd);

        String abcde="we";
        Stream<String> abcs=Stream.of("ww*ee*qq".split("//*"));

        Supplier<LongStream> streamOfLong= ()->LongStream.of(1,2,5,6,8);
        streamOfLong.get().findAny().equals(5);
        OptionalDouble avg= streamOfLong.get().average();


        List<Integer> listofInt5= Arrays.asList(1,8,1,7,6,8,6);


        List<Integer> sortedDistinctList=listofInt5.stream().filter(e->e!=null).sorted().distinct().collect(Collectors.toList());
        sortedDistinctList.stream().count();
        // sortedDistinctList.map(a->a*5).reduce((a,b)->System.out.println(c));
        System.out.println(" applying reduce on integer "+sortedDistinctList.stream().reduce((s1,s2) -> s1 + s2));

        // find the max/min of sorted list.
        // int maxValue=listofInt5.stream().max((Comparator<? super Integer>) sortedDistinctList).get();
        //  System.out.println("maxValue "+maxValue);


        // find the sorted list count
        Long countDistinctSorted= sortedDistinctList.stream().count();
        System.out.println("countDistinctSorted "+countDistinctSorted);



        // operation on strings
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh arya");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        //memberNames.stream().map(a->System.out.println(a.length())).forEach(b->System.out.println("length of element"++" is "+b));

        // find the length of the string with string .
        memberNames.forEach(a->{
            System.out.print(a);
            System.out.print("  "+a.length());
            System.out.println();
        });



        String returnedString=memberNames.parallelStream().filter(s-> s.startsWith("Y")|| s.startsWith("y")).findAny().get();
        System.out.println("returnedString ** "+returnedString);


        String returned1=memberNames.stream().collect(Collectors.joining(","));
        System.out.println("returned1 "+returned1);


        /* Stream.anyMatch() & stream.findFirst() they are called short-circuit operations
        as soon as a String is found starting with the letter 'A', the stream will end and the
        result will be returned.
        */
        Boolean returnmatchedValue= memberNames.stream().anyMatch(s->s.endsWith("a"));
        System.out.println("returnmatchedValue "+returnmatchedValue);

        if(returnmatchedValue==false){
            System.out.println("I am in if ");
        }
        else{
            System.out.println("I am in else");
        }


        String[] stringSplited;
        stringSplited = returned1.split(",");


        List<Integer> is = Stream.of(1,4,6,8,4)
                .collect(Collectors.toList());

        System.out.println("is is "+is);


        /*anytime we want to do a particular job using multiple threads in parallel cores,
        all we have to call parallelStream() method instead of stream() method.
         */

        /*Java program to use peek() API to debug the Stream operations and logging Stream
        elements as they are processed.
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

     System.out.println("Lets see how peek works "+   list.stream()
                .peek( System.out::println ).collect(Collectors.toList()));


       /* Stream skip() method is stateful intermediate operation. Stateful operations, such as
       distinct and sorted, may incorporate state from previously seen elements when processing new elements.
                Returns a stream consisting of the remaining elements of the stream after discarding the first n elements of the stream.
                If the stream contains fewer than n elements then an empty stream will be returned.
        Generally skip() is a cheap operation, it can be quite expensive on ordered parallel pipelines, especially for large values of n.
        */

        Stream<Integer> evenNumInfiniteStream = Stream.iterate(0, n -> n + 2);


        List<Integer> newList = evenNumInfiniteStream
                .skip(5)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(newList);



        // max operation..

        List<Integer> list1 = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);

        Optional<Integer> maxNumber = list1.stream()
                .max((i, j) -> i.compareTo(j));  // .reduce((s1,s2)->s1+s2);   same as reduction opr

        System.out.println(maxNumber.get());

        //min operation..
        List<Integer> list2=Arrays.asList(1,3,5,6,8,2);
        Integer returnMin=list2.stream().min((s1,s2)->s1.compareTo(s2)).get();
        System.out.println("returnMin "+returnMin);


        //stream of numbers using custom comparator.
        List<Integer> list3 = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);

        Comparator<Integer> maxComparator = new Comparator<Integer>() {

            @Override
            public int compare(Integer n1, Integer n2) {
                return n1.compareTo(n2);
            }
        };

        Optional<Integer> maxNumber2 = list3.stream()
                .max(maxComparator);

        System.out.println(maxNumber.get());



        //sum operation.. type 1

        int sumValue=IntStream.of(1,2,4,6).sum();
        System.out.println("sumValue "+sumValue);

        //sum operation.. type 2
        IntStream intstream= IntStream.of(1,4,7,7);
        System.out.println(intstream.summaryStatistics().getSum());

        //sum operation.. type 3
        //
        OptionalDouble intstrm = IntStream.of(1, 4, 7, 7).average();
        System.out.println("intstrm "+intstrm);


        //average operation..
        DoubleStream averageValue= DoubleStream.of(2.33,4.5,5.67);
        System.out.println("averageValue "+averageValue);



        //ONE WAY TO CALCULATE STATSTICS IS USING OPERTAIONS On primitive data types
        DoubleStream doublestream = DoubleStream.of(1,6,8,4);
        System.out.println("sum of double stream is "+doublestream.sum());
      /*
      stream has already been operated upon or closed  so operating on different stream now.

      System.out.println("average of double stream is "+doublestream.average());
        System.out.println("count of double stream is "+doublestream.count());
        System.out.println("min of double stream is "+doublestream.min());
        System.out.println("max of double stream is "+doublestream.max());
*/

        DoubleStream doublestream1 = DoubleStream.of(1,6,8,4);
        System.out.println("average of double stream is "+doublestream1.average());





        //ANOTHER WAY TO CALCULATE STATSTICS IS USING summaryStatics() on primitive data type

        //sum operation.. type 2
        IntStream intstream1= IntStream.of(1,4,7,7);
        System.out.println(intstream1.summaryStatistics().getSum());
      /*  System.out.println(intstream1.summaryStatistics().getAverage());  //stream has already been operated upon or closed sp pls initiate stream again
        System.out.println(intstream1.summaryStatistics().getCount());
        System.out.println(intstream1.summaryStatistics().getMax());
        System.out.println(intstream1.summaryStatistics().getMin());
*/



        //oNe WAY TO CALCULATE STATSTICS IS USING operations on stream directly/non-primitive type.
        Stream<Integer> myIntegerStream = Stream.of(2,6,8,9);
      /*
        stream has already been operated upon or closed sp pls initiate stream again
        System.out.println("myIntegerStream is "+myIntegerStream.min((i,j) -> i.compareTo(j)).get());
        System.out.println("myIntegerStream is "+myIntegerStream.max((i,j) -> i.compareTo(j)).get()); */
        System.out.println("myIntegerStream is "+myIntegerStream.reduce((i,j) -> i+j).get()); // for sum() */

        // average

        int[] ab= new int[3];
        ab[0]= 2;
        ab[1]=5;
        ab[2]=6;

        //or
        int[] abe={'a','b','n'};


        //likewise
        char[] ch=new char[4];
        ch[0]='a';
        ch[1]='b';

        //or
        char[] ch1={'r','t','y'};

/*
partitioningBy

We can partition a stream into two – based on whether the elements satisfy certain criteria or not.

Let’s split our List of numerical data, into even and ods:

Here, the stream is partitioned into a Map, with even and odds stored as true and false keys.
booleanListmap is {false=[1, 5, 7], true=[2, 4]}
 */
        List<Integer> listIn=Arrays.asList(1,2,4,5,7);
        Map<Boolean,List<Integer>>  booleanListMap=listIn.stream().collect(Collectors.partitioningBy(i-> i%2==0));
        System.out.println("booleanListmap is "+booleanListMap);

        // groupingBy() offers advanced partitioning – where we can partition the stream into more than just two groups.
        String[] str={"apple","orange","grapes","apple"};
        Stream<String> stringStream=Stream.of(str);
      //  Map<Boolean,List<String>> myReturnedMap=stringStream.collect(Collectors.groupingBy(e->e.startsWith(stringStream.findFirst().get())));
        //System.out.println("myReturnedMap iss "+myReturnedMap);
      // Optional<Map<Boolean,List<String>>> myReturnedMap=stringStream.collect(Collectors.groupingBy(e->e.startsWith(stringStream.findFirst().get())));
       // System.out.println("myReturnedMap iss "+myReturnedMap);



      /*
        mapping() & reducing() are used with groupingBy()
        reducing() is most useful when used in a multi-level reduction,
       downstream of groupingBy() or partitioningBy(). To perform a simple reduction on a stream,
       use reduce() instead.
       */


        /* INFINITE STREAM
       . We might not know beforehand how many elements we’ll need. Unlike using list or map,
                where all the elements are already populated,
        we can use infinite streams, also called as unbounded streams. generate() & iterate() methods to generte infinite streams ..
           */

        /*With infinite streams, we need to provide a condition to eventually terminate the processing.
        One common way of doing this is using limit(). In above example, we limit the stream to 5 random
        numbers and print them as they get generated.
        Please note that the Supplier passed to generate() could be stateful
        and such stream may not produce the same result when used in parallel.
         */


      /* running example
       Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);


   // generating infinite parallel stream example . kkep in mind first stream will be generated then parallel() will be called.
        Stream.generate(Math::random).parallel()
                .limit(5)
                .forEach(System.out::println);

   */

/*
        iterate()

        iterate() takes two parameters: an initial value, called seed element and a function
        which generates next element using the previous value. iterate(), by design, is stateful
        and hence may not be useful in parallel streams:



        Vector<Integer> vector  =Stream.iterate(2,i->i*2).parallel().limit(5).collect(Collectors.toCollection(Vector::new));
        System.out.println("vector "+vector);

*/

       /* Stream<Integer> result = Stream.ofNullable(number);

        The new method returns empty Optionals in it receives null, avoiding runtime errors in scenarios that would normally cause one, like in the following example:

        Integer number = null;
        Stream<Integer> result = Stream.ofNullable(number);
        result.map(x -> x * x).forEach(System.out::println);
*/

        // Predicate<Employee> olderThan50 ;

        List<Integer>  list6= Arrays.asList(2,4,5,8);

        list6.stream().map(e->e*e).count();


        Integer[] arrayOne={1,3,4};
        Stream<Integer> stream7= Stream.of(arrayOne);
        System.out.println(stream7.map(e->e*e).count());

        // calculating Average .....
        List<Integer> list10= Arrays.asList(1,3,4,5);

        // wrong way of doing
        //      long countElements=list10.stream().count();
        //    long sum = list10.stream().reduce((a,b)-> a+b).get();


        //  public long average(long sum,long countElements){
        //    return sum/countElements;
        // }
        // Optional<Integer> average2=sum/countElements;
        //  System.out.println("average2 is "+average2);


        //Long average = list10.stream().reduce(new Function<String, Integer>()  new Function<List<Integer>, Long>() {
       /* Integer average = list10.stream().reduce(new Function<List<Integer> list,Integer>(){

            @Override
            public Integer apply(List<Integer> integers) {
                return 0;
            }
            public Integer findAverage(List<Integer> list){
                long count1 =list.stream().count();
                long sum =list.stream().reduce((i,j)->i.compareTo(j)).get();
                return sum/count1;
            }
        }

        );
*/
      //  System.out.println("average is "+average);

        // another ways to initalize array
        ArrayList<Integer> list102= new ArrayList<>();
        list102.add(1);
        list102.add(12);
        long countElements = list102.stream().count();
        System.out.println("countElements "+countElements);

        int[] list103 =new int[]{1,2,3,4,7,8,5};
        // list103.finalize();



        List<Integer> list104= Arrays.asList(1,2,3,6);


        Optional<Integer> maxNo=  list104.stream().reduce((a,b)->Math.max(a,b));
        System.out.println("maxNo "+maxNo);



       Optional<Integer> maxValue= list104.stream().max((i,j)->i.compareTo(j));
        System.out.println("maxValue "+maxValue);

        //list104.stream().map(i,j->reduce((i,j)->i+j);
       SparkSession spark = SparkSession.builder().appName("Java Spark SQL basic example")
                .master("local[*]").config("spark.driver.memory", "5g")
                .config("spark.driver.host", "127.0.0.1") // #Machine ip  (localhost worked for me)
                .config("spark.driver.bindAddress", "127.0.0.1") // #Machine ip (localhost worked for me )
                .getOrCreate();
         Dataset<Row> lines = spark.read().text("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/textfile.txt");

        System.out.println("lines.count "+lines.count());

         ArrayList<Integer> counts =new ArrayList<>();
       //JavaRDD<Object> countRDD=
               lines.toJavaRDD().map(e-> {
         //  if(e.toString().iterator().hasNext()){
            long noOfWordsCount =
                    Arrays.stream(e.toString().split(" ")).count();
            //System.out.println("noOfWordsCount " + noOfWordsCount);
            counts.add((int) noOfWordsCount);
            System.out.println("returning  " + counts);
          // }
           // return  counts ;

                   return counts;
               });

     //   countRDD.first();
      //  countRDD.foreach(e-> System.out.println(e));

        //System.out.println(" take (n) "+countRDD.take(3));

    /*    SparkConf sparkConf= new SparkConf().setAppName("neha poc ").setMaster("local[2]");
        JavaSparkContext sc =new JavaSparkContext(sparkConf);

        List<Integer> counts1 =Arrays.asList(1,2,4,6,9);

        JavaRDD<Integer> abdc= sc.parallelize(counts1);
        System.out.println(" take (n) abdc "+abdc.take(3));
       // sc.sparkContext().driver.allowMultipleContexts(true);
        /*countRDD.map((i,j)-> {
            Integer maxNo=Math.max(i,j);
        });*/

       /* System.out.println("counts "+counts.size());
        Optional<Long> maxNo1=counts.stream().max((i,j)->i.compareTo(j));
        System.out.println("maxNo1 is "+maxNo1);
*/


         /*Finding even and odd nos */
       Supplier<Stream<Integer>> strm = ()->Stream.of(1,2,3,5,7,9,10,12); /*Supplier --> Simply put, the solution consists of creating a new Stream each time we need one. We can, of course, do that manually, but that's where the Supplier functional interface becomes really handy:  */
       /* The Supplier is based on a lambda expression that takes no input and returns a new Stream.   https://www.baeldung.com/java-stream-operated-upon-or-closed-exception  */
        List<Integer> evenNos=strm.get().filter(e->(e%2==0)).collect(Collectors.toList());
        List<Integer> oddNos=strm.get().filter(e->(e%2!=0)).collect(Collectors.toList());
            System.out.println("evenNos "+evenNos);
            System.out.println("oddNos "+oddNos);

        /* understand the behaviour of reduce n stream   */
        Supplier<Stream<Integer>> strm1=()->Stream.of(100,10,20,30);
        Optional<Integer> avgReturned=strm1.get().reduce((e, j)-> {
          long sum  = e + j;
          long totalNo= strm1.get().count();
            System.out.println("totalNo is  "+totalNo);
            System.out.println("sum is  "+sum);   // Important to know how sum works  1. first iterations i+j --> 100+10 =120     2. second iteration i+j -->120+20   3. third iterations i+j --> 140+30 .

            long avg5= sum/totalNo;
            System.out.println("avg is  "+avg5);
           return Math.toIntExact(avg5);
        });

        System.out.println("returned avg is  "+avgReturned);


        Supplier<Stream<Integer>> strm2=()->Stream.of(100,10,20,30);
        Optional<Integer> sum2 =strm2.get().reduce((e, j)->e+j);
        Long  totalNo2 = strm2.get().count();

        //long avg2=sum2/totalNo2;


        Optional<Integer> sum3 =strm2.get().reduce((i,j)->i+j);





   System.out.println("showing lines all over again ");
   lines.show();

   int lengthOfEachLines=lines.toString().split("").length;
        System.out.println("lengthOfEachLines "+lengthOfEachLines);




        }


}