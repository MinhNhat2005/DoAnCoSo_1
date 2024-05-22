package model;

import java.util.ArrayList;

public interface IManager {
	ArrayList<Student> getAll();

	boolean add(Student s);

	boolean edit(String masv, Student s);

	boolean delete(String masv);

	ArrayList<Student> findByMasv(String masv);
}