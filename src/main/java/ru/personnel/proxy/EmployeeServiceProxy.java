package ru.personnel.proxy;

import org.springframework.web.client.RestTemplate;
import ru.personnel.dto.employee.EmployeeDataOutDto;

import java.util.Collections;
import java.util.List;


public class EmployeeServiceProxy {
    public static List<EmployeeDataOutDto> getEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        String orgsUrl = "http://localhost:8080/orgs/test1";
        List<EmployeeDataOutDto> responseEntity = Collections.singletonList(new RestTemplate().getForObject(
                orgsUrl,
                EmployeeDataOutDto.class
        ));
        return responseEntity;
    }
}
