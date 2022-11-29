
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.types.DataTypes;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;

/**
 * Input :-- [{"name": "neha","age": "30"},{"name": "vivek", "age": "33"},{"name": "rekha", "age": "55"},{"name": "aryan", "age": "16"},{"name": "kulbhushan", "age": "62"},{"name": "kanta", "age": "30"}]
 * <p>
 * <p>
 * output :--
 * <p>
 * +---+----------+
 * |age|      name|
 * +---+----------+
 * | 30|      neha|
 * | 33|     vivek|
 * | 55|     rekha|
 * | 16|     aryan|
 * | 62|kulbhushan|
 * | 30|     kanta|
 * +---+----------+
 * <p>
 * root
 * |-- age: string (nullable = true)
 * |-- name: string (nullable = true)
 * <p>
 * <p>
 * +-----+
 * | name|
 * +-----+
 * | neha|
 * |kanta|
 * +-----+
 */


public class sparkSqlExample {


    private static Object StringEncoder;

    public static void main(String[] args) {
    /*SparkSession spark = SparkSession
            .builder()
            .appName("Java Spark SQL basic example")
            .config("spark.master", "local")
            .getOrCreate();
*/
        SparkSession spark = SparkSession.builder().appName("Java Spark SQL basic example")
                .master("local[*]").config("spark.driver.memory", "5g")
                .config("spark.driver.host", "127.0.0.1") // #Machine ip  (localhost worked for me)
                .config("spark.driver.bindAddress", "127.0.0.1") // #Machine ip (localhost worked for me )
                .getOrCreate();

        spark.sparkContext().setLogLevel("ERROR");

        //  Dataset<Row> df = spark.read().json("examples/src/main/resources/people.json");
        Dataset<Row> df = spark.read().json("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/people.json");

        // Displays the content of the DataFrame to stdout
        df.show();





        // get the count of df
        System.out.println("total row count in df is ****  " + df.count());  //total row count in df is ****  6

     /*   +---+----------+
                |age|      name|
                +---+----------+
                | 30|      neha|
                | 33|     vivek|
                | 55|     rekha|
                | 16|     aryan|
                | 62|kulbhushan|
                +---+----------+
        */


        df.printSchema();

        System.out.println("showing group by by age  ****  ");  //total row count in df is ****  6

        df.groupBy("age").count().show();
        System.out.println("showing MAXIMUm age  ****  ");  //total row count in df is ****  6

        df.agg(max("age")).show();

        System.out.println("showing MAXIMUm age GROUP BY DEPT ****  ");  //total row count in df is ****  6

        df.groupBy("dept").agg(max("age")).show();


        df.groupBy("dept").agg(avg("age")).show();

        System.out.println("showing MAXIMUm age MIN WEIGHT  ****  ");
        df.agg(max("age"),min("weight")).show();

        System.out.println("showing MAXIMUm age MIN WEIGHT  group by depT ****  ");

      Dataset<Row> selected =df.groupBy("dept").agg(max("age").alias("max_age"),min("weight").alias("min_weight")).select("dept","max_age","min_weight");
          System.out.println("showingselected fields  ****  ");
          selected.show();








    /*    val df2 = df1.select(
                $"Gender",
                (when($"treatment" === "Yes", 1).otherwise(0)).alias("All-Yes"),
                (when($"treatment" === "No", 1).otherwise(0)).alias("All-Nos")
        )

        */


        // Register the DataFrame as a SQL temporary view
        df.createOrReplaceTempView("people");


        /*--------------------------------------------------------------------------------------------------------------------------------------------*/
        // You can extract a column of a dataframe by its name
        Dataset<Row> sqlDF = spark.sql("SELECT current_timestamp(),name FROM people where age=30");

       // The columns of a row in the result can be accessed by field index
        Encoder<String> stringEncoder = Encoders.STRING();
        Dataset<String> teenagerNamesByIndexDF = sqlDF.map(new MapFunction<Row, String>() {
            @Override
            public String call(Row row) throws Exception {
                return "name: " + row.getString(1);
            }
        }, stringEncoder);


        System.out.println(" teenagerNamesByIndexDF");

        teenagerNamesByIndexDF.show();



        /*--------------------------------------------------------------------------------------------------------------------------------------------*/

        /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
        Dataset<Row> sqlDF1 = spark.sql("SELECT max(age), min(age) FROM people");


        // Prints value of dataframe
        sqlDF1.show();

        /*      +--------+--------+
                |max(age)|min(age)|
                +--------+--------+
                |      62|      16|
                +--------+--------+
        */


        // This doesnt print value of df
        System.out.println("sqlDF1 " + sqlDF1);   // prints sqlDF1 [max(age): string, min(age): string]


        /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/


  /*  UDF POC    */

      //   Writting 1st UDF
       //  Terminology/fundamental
        // 1.  dataframe == table;
        // 2. register dataframe as tempview   --> df.createTempView("employee")
        // 3. To select columns apply dataframe.sql(select col from employee))
        // 4. udf called as nameofUdf(column)  in select statement .
// 5.  The Results of sql queries are dataframes and support all the normal RDD operations .


        //  Dataset<Row> df = spark.read().json("examples/src/main/resources/people.json");
        Dataset<Row> df10 = spark.read().json("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/people.json");


        // Register the DataFrame as a SQL temporary view
        df10.createOrReplaceTempView("person");


/*
        spark.udf().register("GettingSurname", new UDF1<String, String>() {
            @Override
            public String call(String fullName) throws Exception {
                String[] strArr = fullName.split(" ");

               return strArr[1];
            }
        }, DataTypes.StringType);
*/

      //  Dataset<Row> sqlDF10 = spark.sql("SELECT GettingSurname(name) FROM person ");  // call  udf(column) from tempview  ; createtempview from source dataframe & then pass column of dataframe to udf .
       // sqlDF10.show();

        /*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/

        //-------------------------------------------------------------------------------------------------------//
      // UDF Example 2 concatinating 2 columns

      // notice UDF2 for new udf
        spark.udf().register("ConcatedColumns", new UDF2<String, String,String>() {
            @Override
            public String call(String column1, String column2) throws Exception {
                String concatedStr = column1+" * * "+column2;

                return concatedStr;
            }
        }, DataTypes.StringType);




        Dataset<Row> sqlDF11 = spark.sql("SELECT ConcatedColumns(name,age) FROM person ");  // call  udf(column) from tempview  ; createtempview from source dataframe & then pass column of dataframe to udf .
        sqlDF11.show();

        //-------------------------------------------------------------------------------------------------------//

       //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&//

     //   A Dataset is a strongly typed collection of domain-specific objects that can be transformed in parallel using functional or relational operations. Each Dataset also has an untyped view called a DataFrame, which is a Dataset of Row.
      //in short

       // Operations available on Datasets are divided into transformations and actions. Transformations are the ones that produce new Datasets, and actions
        // are the ones that trigger computation and return results. Example transformations include map, filter, select, and aggregate (groupBy).
        // Example actions count, show, or writing data out to file systems.
        // We Can apply map/flatmap like transformation/action to dataframe  & even after converting it to RDD or from one dataframe to anothore datafgrame .
long reconrunid=38;
        List<Long> list=Arrays.asList(reconrunid);
        Dataset<Long> ds= spark.createDataset(list,Encoders.LONG());
       // sequence("abc")
       // ds.withColumnRenamed("value");
      // Dataset<Row> ds=spark.createDataFrame();
        System.out.println("empty dataframe");
        //ds.show();
       Dataset<Row> ds1=ds.withColumn("current_time", lit(1)).withColumnRenamed("value","reconrunid");
       System.out.println("showing ds1 ");

  //ds1.show();


          // except set operation
        Dataset<Row> ds5=ds.withColumn("current_time", lit(1)).withColumn("abcd", lit(1));
        Dataset<Row> ds6=ds.withColumn("current_time", lit(2)).withColumn("abcd", lit(2));


       Dataset<Row> delta =ds5.except(ds6);
       //System.out.print("showing delta count ** "+delta.count());



       List<String> abc= new ArrayList<>();
        abc.add("b");
        abc.add("a");
        //System.out.print("list is "+abc);

        List<String> dsRow=abc.stream().map(x->x).sorted().collect(Collectors.toList());
       // System.out.print("dsRow is "+dsRow);
 //df.schema();

        Dataset<Row> dsNew=sqlDF11.withColumn("neha", lit(1001));
 Dataset<Row> ds10=null;
        ds10 =dsNew;
        System.out.print("showing ds10  next** ");
        ds10.show();

        Map<String,String> entryMap= new HashMap<String,String>();
        entryMap.put("1","load_dt");
        entryMap.put("2","filename");

        System.out.println("printing entryset **");
      Optional<Map.Entry<String, String>> opt=entryMap.entrySet().stream().findFirst();//.collect(Collectors.toSet());

        System.out.println("opt is "+opt.get().getValue());
        Collection<String> optList=null;
        if(entryMap.size()>0){  System.out.println("opt is "+opt.get().getValue());

            optList=entryMap.values();

        }
        else{
            optList=null;
        }
        System.out.println("optList is "+optList);



/* SPLITTING SURNAME */

        df.show();
   Dataset<Row> we=df.select(col("name"));
   JavaRDD<String> surnameSplitted=  we.toJavaRDD().flatMap(x->{
      // ArrayList<String> name= new ArrayList<>();
      // Optional<String[]> abcd=Optional.ofNullable(x.toString().split(" "));
      //String abc= abcd[0];
       /*if(abcd.isPresent()) {
          name.add(abcd[0].get());
       }*/

       ArrayList<String> name = new ArrayList<>();
       List<String> opt1=Arrays.stream(x.toString().split(" ")).collect(Collectors.toList());


       if (opt1.size()<1) {
           name.add(opt1.get(1));
       }
//if(surname.isPresent()){
           // name.add(surname.get());
//}
          // }
return  name.iterator();
   });

        surnameSplitted.foreach(e-> System.out.println(e));

/* RUNNNING BUT commenting
     /* SPLITTING NAME BASED ON AGE FILTER *
        df.show();
        Dataset<Row> we2=df.filter(col("age").gt(45)).select(col("name"));
        JavaRDD<String> surnameSplitted2=  we2.toJavaRDD().flatMap(x->{
            List<String> name=new ArrayList<String>();
            String[] abcd=x.toString().split(" ");
            String surname=abcd[1];
            name.add(surname);
            return name.iterator();
        });





        surnameSplitted2.foreach(e-> System.out.println(e));
*/

       List<Integer> wer= Arrays.asList(1,2,3,4,5);
       Optional<Integer> qw= wer.stream().reduce((i, j)->Math.max(i,j)); // fiNDING MAX() ON STREAM
        System.out.println("qw is "+qw);

        Optional<Integer> sum=wer.stream().reduce((i, j)->i+j);
       long count2 =wer.stream().count();
      //  Optional<Long> average= sum/count2;

        OptionalDouble avg=wer.stream().mapToInt(x-> x.intValue()).average();

        System.out.println("avg "+avg);



        // Encoders for most common types are provided in class Encoders
        Encoder<Integer> integerEncoder = Encoders.INT();
        Dataset<Integer> primitiveDS = spark.createDataset(Arrays.asList(1, 2, 3), integerEncoder);
        Dataset<Integer> transformedDS = primitiveDS.map(
               value -> value + 1,
                integerEncoder);
        transformedDS.collect(); // Returns [2, 3, 4]

       // Creating dataset
      Dataset<Integer> qyu= spark.createDataset(Arrays.asList(1,2,3) ,Encoders.INT());
        Dataset<Integer> returnedtype = qyu.map(e -> {
            if (e == 3) {
              return 3;
            }
            else return 0;
        }, Encoders.INT());
       // qyu.map(e-> e.filter());
        System.out.println("returnedtype  is "+returnedtype);

        returnedtype.persist();

        /* commenting just because sparkContext is not getting created because of jar dependency else code is working */

        // ways to CreATE an rdd 1. parAlLIZe 2. extrenal dataset 3. from another rdd
      // first way from parallize method.
       // SparkConf sparkconf =new SparkConf();  // step 1
        //  JavaSparkContext sparkContext= new JavaSparkContext(sparkconf);  //step 2
      //  JavaRDD<Integer> javaRdd1 =  sparkContext.parallelize(Arrays.asList(1,2,3,4,5));
       // List<Integer> ListRdd = javaRdd1.coalesce(2).filter(x-> x==2).collect();
       // System.out.println("ListRdd  "+ListRdd);



                // Apply a schema to an RDD of JavaBeans to get a DataFrame
              //  Dataset<Row> peopleDF = spark.createDataFrame(peopleRDD, Person.class);  // params (rdd, schema)

        /* dataframe to dataset conversion  */
        //  Dataset<String> dataset=peopleDF.map((mapFunction <Row,String>) row-> "name: "+row.getString(0),stringEncoder);  // dataframe to dataset conversion


        //  Dataset<Row> linesDF12 = spark.read().text("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/textfile5.txt");
        Dataset<Row> linesDF12 = spark.read().json("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/Runs.json");

       //linesDF12.groupBy("name").count().show();
      //  linesDF12.show();
       // linesDF12.filter( linesDF12.col("run").equalTo(4)).filter(linesDF12.col("run").equalTo(6)).select(linesDF12.col("name"), linesDF12.col("run")).show();

     /*   select name , run from batsman where run=4 and run=6 ;

rohit,0,1
      rohit 0,1 */

        linesDF12.createOrReplaceTempView("runs");
        Dataset<Row> linesDF13=spark.sql("select count(*),name.run from runs where run IN(4,6) groupby(name,run)");

        linesDF13.show();

        spark.stop();
    }

}




