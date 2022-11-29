package headFirstJava;
// Example of ploymorphism

import java.util.ArrayList;
import java.util.List;

class myexp extends Animal {    // default class hence outside of this package its not going to be called .

     Animal[] animal=new Animal[5];
      int i=0;

   public void add(Animal a){
       System.out.println("i am being called ");


       if(animal.length>i) {
       System.out.println("animal i "+animal[i]);
        i++;

       }

   }
}




  public class PolymorphismExample {
public static void main(String[] args){
List<Animal> myAnimalList= new ArrayList<Animal>();
    System.out.println("i am caller  ");
    Dog d =new Dog();
    Cat c=new Cat();
//Dog d =new Dog("jonny",1 ,106);
//Cat c=new Cat("lisa",2,86);
    myAnimalList.add(c);
    myAnimalList.add(d);
}

}
