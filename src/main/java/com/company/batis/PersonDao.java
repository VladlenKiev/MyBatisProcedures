package com.company.batis;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;



class PersonDao {

    private SqlSessionFactory sqlSessionFactory = null;

    public PersonDao(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private static Logger log = Logger.getLogger(MyBatisConnectionFactory.class.getName());


    /**
     * Returns the list of all Person instances from the database.
     * @return the list of all Person instances from the database.
     */
    @SuppressWarnings("unchecked")
    public  List<Person> selectAll(){
        List<Person> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Person.selectAll");
        } finally {
            session.close();
        }
        System.out.println("selectAll() --> "+list); //NOSONAR
        log.log(Level.INFO, "selectAll() --> ", list);

        return list;

    }

    @SuppressWarnings("UnusedReturnValue")
    public Person selectById(int id){
        Person person = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            person = session.selectOne("Person.selectById", id);

        } finally {
            session.close();
        }
        System.out.println("selectById("+id+") --> "+person); //NOSONAR
        log.log(Level.INFO, "selectById("+id+") --> ", person);

        return person;
    }
    /**
     * Insert an instance of Person into the database.
     * @param person the instance to be persisted.
     */
    public int insert(Person person){
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Person.insert", person);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("insert("+person+") --> "+person.getId()); //NOSONAR
        log.log(Level.INFO, "insert --> ", person.getId());
        return id;
    }
    /**
     * Update an instance of Person into the database.
     * @param person the instance to be persisted.
     */
    public void update(Person person){
        @SuppressWarnings("UnusedAssignment") int id = -1; //NOSONAR
        SqlSession session = sqlSessionFactory.openSession();

        try {
            //noinspection UnusedAssignment
            id = session.update("Person.update", person); //NOSONAR

        } finally {
            session.commit();
            session.close();
        }
        System.out.println("update("+person+") --> updated"); //NOSONAR
        log.log(Level.FINE, "update: ", person.toString());
    }

    /**
     * Delete an instance of Person from the database.
     * @param id value of the instance to be deleted.
     */
    @SuppressWarnings("UnusedDeclaration")
    public void delete(int id){

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Person.delete", id);
        } finally {
            session.commit();
            session.close();
        }
        System.out.println("delete("+id+")"); //NOSONAR
        log.log(Level.INFO, "delete: {}", id);
    }
}