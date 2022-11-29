import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.*;

import java.util.*;

import static java.lang.Math.max;
import static org.apache.spark.sql.functions.avg;

public class sqlOperations {

    private static Object Encoder;


    public static <optional, mapFunction> void main(String[] args) {
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

        /* multiline lambda expression working  */

        Dataset<Row> lines = spark.read().text("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/textfile.txt");

        /* expression 1   (a) -> {; ; ; ; };  */
        lines.foreach(s -> {
                    System.out.println("my line *** ");
                    System.out.println(s);
                }
        );

        /* expression 2 bUT IT DoeS NOt RETRURN ANYTHiNG AS FOREACH RETURNS VOID  */
        List<Long> counting = new ArrayList<Long>();
        lines.foreach(e -> {
            if (e.toString().contains("not") == false) {
                String[] line = e.toString().split(" ");//flatMap(s->Arrays.asList(s.split(" ")),Encoders.STRING());
                long abcs = Arrays.stream(line).count();
                counting.add(abcs);
            } else {
                System.out.println(" bogus line");

            }
            Long Max = counting.stream().max(Long::compare).get();
            System.out.println("max value is " + Max);
        });






        /* Getting values out of dataframes .Type 2 example . */
        Dataset<Row> allLines = spark.read().text("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/textfile.txt");
        System.out.println(" allLines.show() ");
        allLines.show();
        Dataset<Row> allLinesWithoutNot = allLines.filter(e -> !e.toString().contains("not"));
        System.out.println(" allLinesWithoutNot " + allLinesWithoutNot);
        allLinesWithoutNot.show();
        allLines.createOrReplaceTempView("readintextfileview");
        Dataset<Row> sqldf50 = spark.sql("select count(*) from readintextfileview ");
        sqldf50.show();

        /* CONVERTED DATAFRAME INTO rdd TO APPLY FLATMAP SO THAT FLATMAP COULD RETURN VALUES . iN RDD nO PREDEFINED FORMAT OF RETeUN TYpE Os U CAN rETURN ANyTHING */
        JavaRDD<Long> kkk = allLinesWithoutNot.toJavaRDD().flatMap((e -> {
            if (e.toString().contains("not") == false) {
                String[] line = e.toString().split(" ");//flatMap(s->Arrays.asList(s.split(" ")),Encoders.STRING());
                long abcs = Arrays.stream(line).count();
                counting.add(abcs);
            } else {
                System.out.println(" bogus line");

            }//return (Iterator<String>) counting;
            return counting.iterator();
        }));

        Long BC = kkk.reduce((a, b) -> max(a, b));
        System.out.println("bC IS " + BC);
        System.out.println("All liNs  CHEck " + allLinesWithoutNot);


        /* APPLYING MAP INTO DATAFRAME ITSELF . iN RDD nO PREDEFINED FORMAT OF RETeUN TYpE Os U CAN
        rETURN ANyTHING BuT MAP FUNCTION OF DATaFRAMEs COME WITH A PREDEFINED RETRUN TYPe So NOT EXPECTING
        A LIST TO RETURN HENCE CONVERTED DATAFRAME INTO RDD AND THEN APPLIeD RDD OPERAtion to do free handing */
       /*List<long> nm= allLinesWithoutNot.map( (MapFunction<Row, List<Long>) e -> { if (e.toString().contains("not") == false) {
                    String[] line = e.toString().split(" ");//flatMap(s->Arrays.asList(s.split(" ")),Encoders.STRING());
                    long abcs = Arrays.stream(line).count();
                    counting.add(abcs);
                } else {
                    System.out.println(" bogus line");

                }return counting;
                },
                Encoders.LIST<LONG>);  */


        System.out.println("counting  is here  ");
        // System.out.println(" somebody's me "+ abgf.stream().sorted().findFirst());

        // perform same exercise but with columnar data on dataframe only + read _spark sql +spark core n spark streaming
        //  pl = sdf.filter('col3 <= 30')\         .groupBy("col1","col4").agg(F.sum('col2').alias('sumC2')) pr = sdf.filter('col3 > 30')\         .groupBy("col1","col4").agg(F.sum('col2').alias('sumC2'))


   /*     // To create Dataset<Row> using SparkSession
        Dataset<Row> people = spark.read().parquet("...");
        Dataset<Row> department = spark.read().parquet("...");

        people.filter("age".gt(30))
                .join(department, people.col("deptId").equalTo(department("id")))
                .groupBy(department.col("name"), "gender")
                .agg(avg(people.col("salary")), max(people.col("age")));
*/

        Dataset<Row> employee3 = spark.read().json("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/employee3.json");
        employee3.show();

        Dataset<Row> employee4 = spark.read().json("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/employee4.json");
        employee4.show();

     /* Dataset<Row> joinedDataset=  employee3.filter(employee3.col("city").equalTo("noida"))
                .join(employee4, employee4.col("depart").equalTo(employee3.col("department")))
              .groupBy(employee3.col("name"),employee3.col("city"))
              .agg(avg(employee3.col("salary")));
*/

        // CHOOSE WHEN OTHERWISE CASE .
     /*   joinedDataset.select(joinedDataset.col("depart").when(joinedDataset.col("iris_class").equalTo("Iris-setosa"), 0)
                .when(joinedDataset.col("iris_class").equalTo("Iris-versicolor"),1)
                .when(joinedDataset.col("iris_class").equalTo("Iris-versicolorNEW"),2)
                .otherwise(3));
*/

       /* Dataset<Row> df10=employee3.select(employee3.col("department").when(employee3.col("department").equalTo("nonIT"),"nonitgroup")
                .when(employee3.col("department").equalTo("nonIT"),"itgroup").otherwise("additionALgROUP"));

        System.out.println("when dataFrame pRinting ");
        df10.show(); */


        // joinedDataset.show();


        Dataset<Row> wordsDf = spark.read().text("/home/osboxes/IntellijProjects/sparkSqlExample/src/resources/textFile4.txt");
        wordsDf.show();

        JavaRDD<Integer> abcds = wordsDf.toJavaRDD().map(e -> {
            if (!e.toString().contains("NOT")) {
                Integer length = e.toString().split(" ").length;
                return length;
            }
            return 0;

        });

        abcds.take(2);


        Integer result = abcds.reduce((i, j) -> max(i, j));
        System.out.println(result);

        List<Integer> ll1 = new ArrayList<Integer>();
        JavaRDD<Integer> abc = wordsDf.toJavaRDD().flatMap(e -> {
            int len = e.toString().split(" ").length;
            if (len > 0) {
                ll1.add(len);
                return ll1.iterator();
            } else {
                return ll1.iterator();
            }
        });
        abc.reduce((i, j) -> Math.max(i, j));



/*Dataset<String> awe=spark.createDataset(Arrays.asList("1","2","4","5"),Encoders.STRING());
        Dataset<String> nwe=awe.toJavaRDD().flatMap(

                    int lenf=e.toString().split(" ").length;
                    if (lenf > 0) {
                        ll1.add(lenf);
                        return ll1;
                    } else {
                        return ll1.iterator();
                    }
                ,Encoders.STRING());*/
        spark.stop();
    }


}
