package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Tutorial;
import service.TutorialesService;

@RestController
public class TutorialesController {
	@Autowired
	TutorialesService tutorialesService;
	@GetMapping(value="tutoriales",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tutorial>> tutoriales(@RequestParam("tematica") String tematica, 
													@RequestParam("total") int total){
		return new ResponseEntity<>(tutorialesService.tutoriales(tematica, total),HttpStatus.OK);
	}
}
