package Builder;

public class Main {
    public static void main(String[] args) {
        Person person = new PersonBuilderImpl().setName("Sergei").setAge(23).build();
        person.toString();
    }
}
class Person {

    String name;
    int age;
    double salary;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

}

interface PersonBuilder {
    PersonBuilder setName(String name);
    PersonBuilder setAge(int age);
    PersonBuilder setSalary(double salary);

    Person build();
}

class PersonBuilderImpl implements PersonBuilder {

    private Person person = new Person();

    @Override
    public PersonBuilder setName(String name) {
        person.name = name;
        return this;
    }

    @Override
    public PersonBuilder setAge(int age) {
        person.age = age;
        return this;
    }

    @Override
    public PersonBuilder setSalary(double salary) {
        person.salary = salary;
        return this;
    }

    public Person build() {
        return person;
    }
}
