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
		//创建SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
		//创建SqlSessionFactory
		SqlSessionFactory ssf=ssfb.build(TestCase.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml"));
		//获得SqlSession对象
		session=ssf.openSession();	
	}
	
	@Test
	//测试 insert
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
	//测试 findAll
	public void test2() {
		List<Dept> dp=session.selectList("test2.findAll");
		System.out.println(dp);
		session.close();
		
	}
	
	@Test
	//测试 findById
	public void test3() {
		Dept dp=session.selectOne("test2.findById",3);
		System.out.println(dp);
		session.close();
	}
	
	@Test
	//测试 update
	public void test4() {
		Dept dp=session.selectOne("test2.findById",3);
		dp.setDeptName("SALES");
		dp.setLoc("GuangZhou");
		session.update("test2.update",dp);
		session.commit();
		session.close();
	}
	
	@Test
	//测试 delete
	public void test5() {
		session.delete("test2.delete",4);
		session.commit();
		session.close();
		
	}
}
