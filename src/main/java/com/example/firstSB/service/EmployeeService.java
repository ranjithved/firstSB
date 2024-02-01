package com.example.firstSB.service;

import com.example.firstSB.kafka.KafkaProducerService;
import com.example.firstSB.model.Employee;
import com.example.firstSB.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.aspectj.apache.bcel.classfile.SourceFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    KafkaProducerService kafkaProducerService;

    public List<Employee> getAllEmployees(){
     return empRepo.findAll();
     // Select * from Employee;
    }

    public Employee getEmployeeByEmpid(int empid){
        System.out.println("INSIDE THE SERVICE - imput: " +empid);
        return empRepo.findById(empid).get();
        // Optional<Employee> opEmployee = empRepo.findById(empid);
        // select * from Employee where empid =?
        /* if(opEmployee.isPresent())
            return opEmployee.get();
        else
            return new Employee(); */

    }

    public List<Employee> getEmployeeByCity(String city){
        return empRepo.findByCity(city);
    }

    public void upsert(Employee employee) throws JsonProcessingException {
        // empRepo.save(employee);
        kafkaProducerService.sendSimpleMessage(employee);

        // insert into employee values(); or update existing
    }

    public void upsertEmployees( List<Employee> employees) {
        empRepo.saveAll(employees);
        // insert into employee values(); or update existing
    }

    public void readFileContents(InputStream inputStream) throws Exception{
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT);

        List<CSVRecord> records= parser.getRecords();
        Random randomEmployeeIds = new Random();
        List<Employee> employeeList= new ArrayList<>();
        // Enhanced for Loop
        for(CSVRecord record: records) {
            Employee employee = new Employee();
            //employee.setEmpid(Integer.parseInt(record.get(0)));
            employee.setEmpid(randomEmployeeIds.nextInt(400));
            employee.setName(record.get(0));
            employee.setCity(record.get(1));
            employee.setCreatedby("FileUpload");
            employee.setCreateddate(Date.valueOf(LocalDate.now()));

            // custom logic to implet as per requirements

            employeeList.add(employee);
        }

        empRepo.saveAll(employeeList);
    }

    public void writeEmployeesToCsv(Writer writer){
        //List<Employee> employees = getAllEmployees();
        List<Employee> employees = empRepo.findAll();
        try(CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("EMPID", "NAME", "CITY", "CREATED BY", "CREATED DATE");
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getEmpid(),
                        employee.getName(),
                        employee.getCity(),
                        employee.getCreatedby(),
                        employee.getCreateddate());
            }
        }
        catch(IOException e) {
            System.out.println("Exception Raised");
        }

    }

    public void deleteEmployee(int empid){
        empRepo.deleteById(empid);
    }
}
