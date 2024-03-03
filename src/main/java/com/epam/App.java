package com.epam;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.ui.Main;

public class App {
public static void main(String args[])
{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(QuizConfiguration.class);
	Main main=context.getBean(Main.class);
	Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	main.main();
	context.close();
}
}
