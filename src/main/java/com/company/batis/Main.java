package com.company.batis;
import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;


/**
 * Class {@code Main} demonstrate MyBatis standart procedure.
 *
 * @author V.Mankivskiy
 * @see com.company.batis.Main
 * @since 1.0.0
 * @date 15/09/2017 *
 */
public class Main {

    public static void main(String[] args) {

        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        //get jdbcTemplatePersonDAO
        PersonDao personDAO = new PersonDao(MyBatisConnectionFactory.getSqlSessionFactory());

        //create person bean to insert
        Person person = new Person();
        person.setName("Person 1");

        //( 1 ) insert person
        personDAO.insert(person);

        //**set name of person
        person.setName("Person 2");
        //** insert another person
        int id = personDAO.insert(person);

        //( 2 ) select persons by id
        personDAO.selectById(id);

        //( 3 ) select all
        List<Person> persons = personDAO.selectAll();

        //**set name of all persons
        for(int i = 0; i < persons.size(); i++){
            persons.get(i).setName("Person Name "+i);
            //( 4 ) update person
            personDAO.update(persons.get(i));
        }

        //**check update
        //noinspection UnusedAssignment
        persons = personDAO.selectAll(); //NOSONAR
    }
}
