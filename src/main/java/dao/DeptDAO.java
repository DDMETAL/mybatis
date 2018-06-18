package dao;

import java.util.List;

import entity.Dept;

public interface DeptDAO {
	public void insert(Dept dp);
	public List<Dept> findAll();
	public Dept findById(int id);
	public void update(Dept dp);
	public void delete(int id);
}
