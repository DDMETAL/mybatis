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
		//����SqlSessionFactoryBuilder
		SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
		//����SqlSessionFactory
		SqlSessionFactory ssf=ssfb.build(TestCase.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml"));
		//���SqlSession����
		session=ssf.openSession();	
	}
	@Test
	//���� save
	public void test1() {
		//����SqlSession����ķ������������ݿ�
		Employee emp=new Employee();
		emp.setName("��");
		emp.setAge(19);
		emp.setSalary(12000);
		
		session.insert("test.save",emp);
		//�ύ����
		session.commit();
		//�ر�session
		session.close();
	}
	
	@Test
	//���� finAll
	public void test2() {
		List<Employee> employees=session.selectList("test.findAll");
		System.out.println(employees);
		session.close();
	}
	
	@Test
	//���� findById
	public void test3() {
		Employee emp=session.selectOne("test.findById",22);
		System.out.println(emp);
		session.close();
	}
	
	@Test
	//����  update
	public void test4() {
		Employee emp=session.selectOne("test.findById",22);
		emp.setAge(22);
		emp.setSalary(emp.getSalary()*2);
		session.update("test.update",emp);
		//��ӣ�ɾ�����޸ı����ύ����
		session.commit();
		session.close();
	}
	
	@Test
	//���� delete
	public void test5() {
		session.delete("test.delete",22);
		session.commit();
		session.close();
	}
	
	@Test
	//���� findById ����Map���͵Ľ��
	public void test6() {
		Map data=session.selectOne("test.findById2",6);
		System.out.println(data);
		//ORACLE���ݿ⣬�ֶ���Ĭ�϶��Ǵ�д
		System.out.println(data.get("NAME"));
		session.close();
	}
	
	@Test
	//���� findById �ֶ�������������һ��ʱ
	public void test7() {
		Emp e=session.selectOne("test.findById3",6);
		System.out.println(e);
	}
}
