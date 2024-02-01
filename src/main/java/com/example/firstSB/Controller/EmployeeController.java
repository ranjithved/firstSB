package com.example.firstSB.Controller;

import com.example.firstSB.dto.EmployeeDto;
import com.example.firstSB.model.Employee;
import com.example.firstSB.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")

    public EmployeeDto getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setCount(employeeList.size());
        employeeDto.setEmployeeList(employeeList);

        return employeeDto;
    }

    /* public List<Employee> getAllEmployees(){
    return employeeService.getAllEmployees();
    }*/

    @GetMapping("/get/{empid}")
    @Operation(description = "This method retrieves the employee details based on empid"+
            "provided. If exists it returns the 200(OK) response, else it returns" +
            "400(BAD_REQUEST) response")
    @ApiResponse(responseCode = "200", description = "SUCCESS RESPONSE")

    public ResponseEntity<Object> getEmployeeByEmpid(@PathVariable("empid") int empid) {
        System.out.println("Inside the Controller - input:" + empid);
        Employee employee = employeeService.getEmployeeByEmpid(empid);
        /*
        if(employee.getEmpid()==0) {
            return new ResponseEntity<>("Student Doesn't Exist", HttpStatus.BAD_REQUEST);
            }
         */
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/getByCity/{city}")
    public List<Employee> getEmployeesByCity(@PathVariable("city") String city){
        return employeeService.getEmployeeByCity(city);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) throws JsonProcessingException {
        if(employee.getEmpid() <=0) {
            return new ResponseEntity<>("MANDATORY FIELDS ARE MISSING", HttpStatus.BAD_REQUEST);
        }
        try {
            employeeService.upsert(employee);
            return new ResponseEntity<>("Employee Added Successfully", HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("ERROR! PLEASE CONTACT ADMIN", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    // private List<String> validateEmployee(Employee employee){}

    @PostMapping("/addList")
    public ResponseEntity<Object> addEmployees(@RequestBody List<Employee> employees){

        try {
            employeeService.upsertEmployees(employees);
            return new ResponseEntity<>("Employee Added Successfully", HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("ERROR! PLEASE CONTACT ADMIN", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file){
        try {
            employeeService.readFileContents(file.getInputStream());
            //FileUtils.forceDelete(file.getResource().getFile());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File Uploaded Successfully");
    }

    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content Disposition", "attachment; filename=\"employees.csv\"");
        employeeService.writeEmployeesToCsv(servletResponse.getWriter());
    }

    @DeleteMapping("/delete/{empid}")
    public void deleteEmployee(@PathVariable("empid") int empid){
        employeeService.deleteEmployee(empid);
    }

}
