/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emp;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author HUYVU
 */
public class EmpDAO {

    ArrayList<EmpDTO> list = new ArrayList<>();

    public EmpDAO(ArrayList<EmpDTO> list) {
        this.list = list;
    }

    public EmpDAO() {
    }
    
    public boolean createEmp(EmpDTO dto) {
        if (findByEmpID(dto.getEmpID()) != null) {
            return false;
        } else {
            list.add(dto);
        }
        return true;
    }

    public EmpDTO findByEmpID(String id){
        for (EmpDTO findEmp : list) {
            if (findEmp.getEmpID().equals(id)) {
                return findEmp;
            }
        }
        return null;
    }
    

    public boolean removeEmp(String id) {
        EmpDTO findEmp = findByEmpID(id);
        //listDeletedEmp.add(findEmp);
        findEmp.setIsDelete(true);
        return true;
    }

    public boolean updateEmp(EmpDTO dto) {
        EmpDTO findEmp = findByEmpID(dto.getEmpID());
        list.remove(findEmp);
        list.add(dto);
        return true;
    }

}
