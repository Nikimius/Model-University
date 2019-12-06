package Builder;

interface PersonBuilder {
    PersonBuilder setName(String name);

    PersonBuilder setAge(int age);

    PersonBuilder setSalary(double salary);

    Person build();
}

public class Main {
    public static void main(String[] args) {
        Person person = new PersonBuilderImpl().setName("Sergei").setAge(23).build();
        person.print();
    }
}

class Person {
    String name;
    int age;
    double salary;

    public void print() {
        System.out.println(name + " " + age + " " + salary);
    }
}

class PersonBuilderImpl implements PersonBuilder {
    Person person = new Person();

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
