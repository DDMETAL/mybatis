package test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import entity.Emp;
import entity.Employee;

public class TestCase {
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
	//测试 save
	public void test1() {
		//调用SqlSession对象的方法来访问数据库
		Employee emp=new Employee();
		emp.setName("广");
		emp.setAge(19);
		emp.setSalary(12000);
		
		session.insert("test.save",emp);
		//提交事务
		session.commit();
		//关闭session
		session.close();
	}
	
	@Test
	//测试 finAll
	public void test2() {
		List<Employee> employees=session.selectList("test.findAll");
		System.out.println(employees);
		session.close();
	}
	
	@Test
	//测试 findById
	public void test3() {
		Employee emp=session.selectOne("test.findById",22);
		System.out.println(emp);
		session.close();
	}
	
	@Test
	//测试  update
	public void test4() {
		Employee emp=session.selectOne("test.findById",22);
		emp.setAge(22);
		emp.setSalary(emp.getSalary()*2);
		session.update("test.update",emp);
		//添加，删除，修改必须提交事务
		session.commit();
		session.close();
	}
	
	@Test
	//测试 delete
	public void test5() {
		session.delete("test.delete",22);
		session.commit();
		session.close();
	}
	
	@Test
	//测试 findById 返回Map类型的结果
	public void test6() {
		Map data=session.selectOne("test.findById2",6);
		System.out.println(data);
		//ORACLE数据库，字段名默认都是大写
		System.out.println(data.get("NAME"));
		session.close();
	}
	
	@Test
	//测试 findById 字段名与属性名不一致时
	public void test7() {
		Emp e=session.selectOne("test.findById3",6);
		System.out.println(e);
	}
}
