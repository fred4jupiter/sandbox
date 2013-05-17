package com.oc.strutswithspring.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.strutswithspring.domain.Employee;
import com.oc.strutswithspring.form.EmployeesForm;
import com.oc.strutswithspring.service.EmployeeService;

@Component
public class EmployeeAction extends DispatchAction {
    
    private Log logger = LogFactory.getLog(this.getClass());
    
    @Autowired
    private EmployeeService employeeService;
    
    public EmployeeAction() {
        logger.debug("creating new EmployeeAction: " + this);
    }

    public ActionForward getEmployees(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {        
        logger.debug("getEmployees");
        populateEmployees(request);
        return mapping.findForward(ForwardName.SUCCESS);
    }

    public ActionForward setUpForUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.debug("setUpForUpdate");
        EmployeesForm empForm = (EmployeesForm) form;
        empForm.setEmployees(employeeService.getAllEmployees());
        return mapping.findForward(ForwardName.SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.debug("update");
        EmployeesForm empForm = (EmployeesForm) form;
        employeeService.updateEmployees(empForm.getEmployees());
        populateEmployees(request);
        return mapping.findForward(ForwardName.SUCCESS);
    }

    private void populateEmployees(HttpServletRequest request) {
        List<Employee> employees = employeeService.getAllEmployees();
        request.setAttribute(ForwardName.EMPLOYEES, employees);
    }

}
