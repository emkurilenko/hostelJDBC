package com.kurilenko.service;

import com.kurilenko.dao.DepartmentDAO;
import com.kurilenko.dao.FacultyDAO;
import com.kurilenko.dao.GroupStudentsDAO;
import com.kurilenko.dao.SpecialityDAO;
import com.kurilenko.dao.impl.DepartmentDAOImpl;
import com.kurilenko.dao.impl.FacultyDAOImpl;
import com.kurilenko.dao.impl.GroupStudentsDAOImpl;
import com.kurilenko.dao.impl.SpecialityDAOImpl;
import com.kurilenko.entity.Department;
import com.kurilenko.entity.GroupStudents;
import com.kurilenko.entity.Specialty;
import com.kurilenko.utils.MapperRS;

import java.util.List;
import java.util.stream.Collectors;

public class UniversityService {
    private FacultyDAO facultyDAO;
    private DepartmentDAO departmentDAO;
    private SpecialityDAO specialityDAO;
    private GroupStudentsDAO groupStudentsDAO;
    private MapperRS mapperRS;

    public UniversityService() {
        mapperRS = MapperRS.getInstance();
        specialityDAO = new SpecialityDAOImpl();
        facultyDAO = new FacultyDAOImpl();
        departmentDAO = new DepartmentDAOImpl();
        groupStudentsDAO = new GroupStudentsDAOImpl();
    }

    public GroupStudents getGroupStudentByName(String name){
        return groupStudentsDAO.getOneByName(name);
    }
    public Long getIdFacultyByName(String name) {
        return facultyDAO.getFacultyByName(name).getId();
    }

    public Department getDepartmentByName(String name) {
        return departmentDAO.getDepartmentByName(name);
    }

    public List<String> getAllFacultyName() {
        return facultyDAO.getAll().stream().map(faculty -> faculty.getNameFaculty()).collect(Collectors.toList());
    }

    public List<String> getAllDepartmentName(Long idFaculty) {
        return departmentDAO.getAll().stream().filter(department -> department.getIdFaculty() == idFaculty).map(department -> department.getNameDepartment()).collect(Collectors.toList());
    }

    public List<String> getAllSpecialityName(Long idDepartment) {
        return specialityDAO.getAll().stream().filter(specialty -> specialty.getIdDepartment() == idDepartment).map(specialty -> specialty.getNameSpecialty()).collect(Collectors.toList());
    }

    public List<String> getAllGroupStudent(Long idGroupSpeciality) {
        return groupStudentsDAO.getAll().stream().filter(groupStudents -> groupStudents.getFkSpecialty() == idGroupSpeciality).map(
                groupStudents -> groupStudents.getNameGroup()).collect(Collectors.toList());
    }

    public Specialty getSpecialityByName(String value) {
        return specialityDAO.getOneByName(value);
    }

    public Long saveGroup(GroupStudents groupStudents){
        return groupStudentsDAO.save(groupStudents);
    }

    public Long saveSpecialty(Specialty specialty) {
        return specialityDAO.save(specialty);
    }
}
