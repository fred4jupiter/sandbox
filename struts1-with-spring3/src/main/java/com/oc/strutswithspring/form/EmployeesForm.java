package com.oc.strutswithspring.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.oc.strutswithspring.domain.Contact;
import com.oc.strutswithspring.domain.Employee;

public class EmployeesForm extends ActionForm {

    private static final long serialVersionUID = -7676581345118986380L;

    private Log logger = LogFactory.getLog(this.getClass());

    private List<Employee> employees;
    
    public EmployeesForm() {
        logger.debug("creating new EmployeesForm: " + this);
    }
    

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {        
        return employees;
    }

    @SuppressWarnings("unchecked")
    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

        employees = ListUtils.lazyList(new ArrayList<Employee>(), new Factory() {
            public Object create() {
                return buildEmployee();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private Employee buildEmployee() {
        Employee emp = new Employee();
        List<Contact> contacts = ListUtils.lazyList(new ArrayList<Contact>(), new Factory() {
            public Object create() {
                return new Contact();
            }
        });
        emp.setContacts(contacts);
        return emp;
    }
}
