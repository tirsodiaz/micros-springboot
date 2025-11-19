package service;

import java.util.List;

import model.Tutorial;

public interface TutorialesService {
	List<Tutorial> tutoriales(String tematica, int total);
}
