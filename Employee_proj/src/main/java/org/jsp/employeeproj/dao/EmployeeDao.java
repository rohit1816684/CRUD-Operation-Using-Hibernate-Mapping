package org.jsp.employeeproj.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsp.employeeproj.dto.Employees;

public class EmployeeDao {
	Session s=new Configuration().configure().buildSessionFactory().openSession();
	
	public Employees saveEmployee(Employees emp) {
		s.save(emp);
		Transaction t=s.beginTransaction();
		t.commit();
		return emp;
	}
	
	public Employees updateEmployee(Employees emp) {
		s.update(emp);
		Transaction t = s.beginTransaction();
		t.commit();
		return emp;
	}
	public Employees findById(int id) {
		return s.get(Employees.class, id);
	}
	
	public Employees verifyEmployee(long phone, String password) {
		String hql="select e from Employees e where e.phone=?1 and e.password=?2";
		Query<Employees> q=s.createQuery(hql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	public Employees verifyEmployee(int id, String password) {
		String hql="select e from Employees e where e.id=?1 and e.password=?2";
		Query<Employees> q=s.createQuery(hql);
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	public boolean deleteEmployee(int id) {
		Employees e= findById(id);
		if(e !=null) {
			Transaction t=s.beginTransaction();
			s.delete(e);
			t.commit();
			return true;
		}
		return false;
	}
	
	public List<Employees> findEmployeeByDesg(String desg){
		String qry="select e from Employees e where e.desg=?1";
		Query<Employees> q=s.createQuery(qry);
		q.setParameter(1, desg);
		return q.getResultList();
	}

}
