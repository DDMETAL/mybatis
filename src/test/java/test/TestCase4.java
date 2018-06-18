package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import dao.DeptDAO;
import entity.Dept;

public class TestCase4 {
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
		DeptDAO dao=session.getMapper(DeptDAO.class);
		Dept dp=new Dept();
		dp.setDeptName("MANAGE");
		dp.setLoc("ShangHai");
		dao.insert(dp);
		session.commit();
		session.close();
	}
	
	@Test
	public void test2() {
		DeptDAO dao=session.getMapper(DeptDAO.class);
		List<Dept> depts=dao.findAll();
		System.out.println(depts);
	}
	
	@Test
	public void test3() {
		DeptDAO dao=session.getMapper(DeptDAO.class);
		Dept dp=dao.findById(2);
		System.out.println(dp);
	}
	
	@Test
	public void test4() {
		DeptDAO dao=session.getMapper(DeptDAO.class);
		Dept dp=dao.findById(6);
		dp.setLoc("ShenZhen");
		dao.update(dp);
		session.commit();
		session.close();
	}
	
	@Test
	public void test5() {
		DeptDAO dao=session.getMapper(DeptDAO.class);
		dao.delete(2);
		
		session.commit();
		session.close();
	}
}
