package service;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Tutorial;
@Service
public class TutorialesServiceImpl implements TutorialesService {
	@Autowired
	ChatClient chatClient;
	
	String prompt="""
			Quiero que me muestres una lista con los {total} primeros sitios Web, en orden de importancia, que incluyan tutoriales sobre {tematica}. 
			El resultado me lo mostrarás en una lista JSON, donde cada objeto incluye los siguientes campos: "url"(direccion del sitio), 
			"descripcion"(breve descripción del sitio Web) y "valoracion"(imagina que eres un experto en la materia y dame tu valoración del sitio entre 1, que significa mal tutorial,  y 5, que sería un muy buen tutorial)
			""";
	
	@Override
	public List<Tutorial> tutoriales(String tematica, int total) {
		BeanOutputParser<Tutorial[]> parser=new BeanOutputParser<>(Tutorial[].class);
		PromptTemplate template=new PromptTemplate(prompt);
		template.add("total", total);
		template.add("tematica", tematica);
		ChatResponse response=chatClient.call(template.create());
		return Arrays.asList(parser.parse(response.getResult().getOutput().getContent()));
	}

}
