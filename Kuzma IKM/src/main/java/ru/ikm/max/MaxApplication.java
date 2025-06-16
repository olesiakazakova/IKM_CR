package ru.ikm.max;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: Необходимо разработать Spring Web MVC проект с использованием
// базы данных, разработанной по дисциплине Базы данных и СУБД.
// Программа должна иметь интуитивно-понятный
// дружественный интерфейс и проверку вводимых значений.
// В программе должны присутствовать классы.
// Необходимо реализовать функции:
// -просмотр данных из базы;
// -редактирование данных в базе;
// -удаление данных из базы;
// -добавление данных в базу.
// Функции должны быть доступны для каждой таблицы.

/**
 * Основной класс приложения, содержащий точку входа.
 * Использует аннотацию {@link SpringBootApplication}
 * для автоматической настройки Spring Boot.
 * <p>Функциональность:</p>
 * <ul>
 *   <li>Автоконфигурация Spring-приложения</li>
 *   <li>Сканирование компонентов в текущем пакете и подпакетах</li>
 * </ul>
 * @see SpringBootApplication
 */
@SpringBootApplication
public class MaxApplication {

	/**
	 * Точка входа в приложение.
	 */
	public static void main(String[] args) {
		SpringApplication.run(MaxApplication.class, args);
	}

}