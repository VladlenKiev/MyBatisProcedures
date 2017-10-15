package com.company.batis;

/**
 * Class {@code Person} is Model.
 *
 * @author V.Mankivskiy
 * @see com.company.batis.Person
 * @since 1.0.0
 * @date 15/09/2017
 */
class Person  {

    private int id;
    private String name;
    public int getId() {
        return id;
    }
    @SuppressWarnings("UnusedDeclaration")
    public void setId(int id) {
        this.id = id;
    }
    @SuppressWarnings("UnusedDeclaration")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "id: "+id+" Name: "+name;
    }

}