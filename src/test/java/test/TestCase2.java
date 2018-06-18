package test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import entity.Dept;

public class TestCase2 {
	private SqlSession session;
	@Before
	public void init() {
		//����SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
		//����SqlSessionFactory
		SqlSessionFactory ssf=ssfb.build(TestCase.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml"));
		//���SqlSession����
		session=ssf.openSession();	
	}
	
	@Test
	//���� insert
	public void test1() {
		Dept dp=new Dept();
		dp.setDeptName("Sales");
		dp.setLoc("BeiJing");
		session.insert("test2.insert",dp);
		session.commit();
		session.close();
		System.out.println(dp);
	}
	
	@Test
	//���� findAll
	public void test2() {
		List<Dept> dp=session.selectList("test2.findAll");
		System.out.println(dp);
		session.close();
		
	}
	
	@Test
	//���� findById
	public void test3() {
		Dept dp=session.selectOne("test2.findById",3);
		System.out.println(dp);
		session.close();
	}
	
	@Test
	//���� update
	public void test4() {
		Dept dp=session.selectOne("test2.findById",3);
		dp.setDeptName("SALES");
		dp.setLoc("GuangZhou");
		session.update("test2.update",dp);
		session.commit();
		session.close();
	}
	
	@Test
	//���� delete
	public void test5() {
		session.delete("test2.delete",4);
		session.commit();
		session.close();
		
	}
}
