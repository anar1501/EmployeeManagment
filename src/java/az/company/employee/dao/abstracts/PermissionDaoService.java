/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.company.employee.dao.abstracts;

import az.company.employee.model.concrets.Permission;
import java.util.List;

/**
 *
 * @author NAZIM
 */
public interface PermissionDaoService {
    List<Permission> findAll();
}
