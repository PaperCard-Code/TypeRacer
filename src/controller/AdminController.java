package controller;

import dao.adminDAO;
import model.Admin;

import java.util.ArrayList;

public class AdminController {

    private adminDAO adminDAO;

    public AdminController() {

        adminDAO = new adminDAO();
    }

    public Admin login(
            String email,
            String password) {

        return adminDAO.login(email,password);
    }

    public boolean addAdmin(
            String name,
            String email,
            String password) {

        Admin admin = new Admin();

        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);

        return adminDAO.addAdmin(admin);
    }

    public ArrayList<Admin> getAllAdmins() {

        return adminDAO.getAllAdmins();
    }

    public boolean updateAdmin(Admin admin) {

        return adminDAO.updateAdmin(admin);
    }

    public boolean deleteAdmin(int id) {

        return adminDAO.deleteAdmin(id);
    }
}