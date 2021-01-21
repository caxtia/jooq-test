package com.example;

import static com.example.db.tables.Employee.EMPLOYEE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.impl.DSL;


public class MainApp {

    public static final String DB_URL = "jdbc:h2:./testdb";
    public static final String DB_USER = "sa";
    public static final String DB_PASS = "";

    /**
     * This method queries the DB to obtain the salary of all employes and calculate its sum
     * @param conn The JDBC connection to use
     * @return The sum of all employee salaries
     */
    public double salaryCost(Connection conn) throws SQLException {
        //FIXME
        return 0;
    }

    /**
     * This method alters the name of an employee
     * @param conn The JDBC connection to use
     * @param id The id of the employe whose name we will change
     * @param firstName The new firstName of the employee
     * @param lastName The new lastName of the employee
     */
    public void setName(Connection conn, int id, String firstName, String lastName) throws SQLException  {
        //FIXME        
    }

    /**
     * This method lists all
     * @param conn
     */
    public void listEmployees(Connection conn) throws SQLException {
        DSL.using(conn)
        .select(EMPLOYEE.FIRSTNAME, EMPLOYEE.LASTNAME, EMPLOYEE.BIRTHDATE)
        .from(EMPLOYEE)        
        .orderBy(EMPLOYEE.BIRTHDATE)
        .fetchStream()
        .forEach(System.out::println);
    }

    public MainApp() {
        try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            System.out.println("=== List Employees ===");
            listEmployees(conn);
            
            System.out.println("=== Salary Cost ===");
            System.out.println("The total salary cost is "+salaryCost(conn));
            
            System.out.println("=== Alter Employee 110 ===");
            setName(conn, 110, "John", "Wayne");

            System.out.println("=== Alter Employee 151 ===");
            setName(conn, 151, "Al", "Pacino");

            System.out.println("=== List Employees (after alteration) ===");
            listEmployees(conn);

        } catch (SQLException e) {
            System.err.println("Encountered an SQL error: "+e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MainApp();
    }
}