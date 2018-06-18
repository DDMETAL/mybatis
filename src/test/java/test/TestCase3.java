package test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import dao.EmployeeDAO;
import entity.Emp;
import entity.Employee;

public class TestCase3 {
	private SqlSession session;
	@Before
	public void init() {
		//创建SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
		//创建SqlSessionFactory
		SqlSessionFactory ssf=ssfb.build(TestCase.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml"));
		//获得SqlSession对象
		session=ssf.openSession();	
	}
	@Test
	public void test1() {
		//获得映射器的实现
		//MyBatis在底层会依据接口要求(Mapper映射器)生成符合接口要求的对象
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		Employee emp=new Employee();
		emp.setName("Kitty");
		emp.setAge(21);
		emp.setSalary(20000);
		dao.save(emp);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void test2() {
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		List<Employee> employees=dao.findAll();
		System.out.println(employees);
		session.close();
	}
	
	@Test
	public void test3() {
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		Employee emp=dao.findById(6);
		System.out.println(emp);
	}
	
	@Test
	public void test4() {
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		Employee emp=dao.findById(6);
		emp.setSalary(emp.getSalary()*3);
		dao.update(emp);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void test5() {
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		dao.delete(6);
		
		session.commit();
		session.close();
	}
	
	@Test
	public void test6() {
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		Map data=dao.findById2(42);
		System.out.println(data);
		System.out.println(data.get("NAME"));
	}
	
	@Test
	public void test7() {
		EmployeeDAO dao=session.getMapper(EmployeeDAO.class);
		Emp e=dao.findById3(42);
		System.out.println(e);
	}
}
