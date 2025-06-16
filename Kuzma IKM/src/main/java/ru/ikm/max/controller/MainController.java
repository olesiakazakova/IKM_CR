package ru.ikm.max.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ikm.max.entity.*;
import ru.ikm.max.repository.*;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

//Fix me: убраны лишние комментарии

/**
 * Основной контроллер приложения, обрабатывающий запросы,
 * связанные с экспонатами, залами и музеями.
 * Предоставляет полный набор CRUD-операций для работы с этими сущностями.
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ExhibitRepository exhibitRepository;
    private final HallRepository hallRepository;
    private final MuseumRepository museumRepository;

    /**
     * Отображает главную страницу приложения с объединенным списком всех сущностей.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона главной страницы
     */
    @GetMapping("/")
    //Fix me: изменено название метода (Методы должны быть глаголами, в смешанном регистре)
    public String showIndex(Model model) {
        List<Exhibit> exhibits = exhibitRepository.findAll();
        List<Hall> halls = hallRepository.findAll();
        List<Museum> museums = museumRepository.findAll();
        model.addAttribute("exhibits", exhibits);
        model.addAttribute("halls", halls);
        model.addAttribute("museums", museums);
        return "index";
    }

    /**
     * Отображает страницу со списком всех экспонатов.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона страницы экспонатов
     */
    //Fix me: изменено название метода (Методы должны быть глаголами, в смешанном регистре)
    @GetMapping("/AllExhibits")
    public String showAllExhibits(Model model) {
        List<Exhibit> exhibits = exhibitRepository.findAll();
        model.addAttribute("exhibits", exhibits);
        return "AllExhibits";
    }

    /**
     * Отображает страницу со списком всех залов.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона страницы залов
     */
    //Fix me: изменено название метода (Методы должны быть глаголами, в смешанном регистре)
    @GetMapping("/AllHalls")
    public String showAllHalls(Model model) {
        List<Hall> halls = hallRepository.findAll();
        model.addAttribute("halls", halls);
        return "AllHalls";
    }

    /**
     * Отображает страницу со списком всех музеев.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона страницы музеев
     */
    //Fix me: изменено название метода (Методы должны быть глаголами, в смешанном регистре)
    @GetMapping("/AllMuseums")
    public String showAllMuseums(Model model) {
        List<Museum> museums = museumRepository.findAll();
        model.addAttribute("museums", museums);
        return "AllMuseums";
    }

    /**
     * Показывает форму для добавления нового экспоната.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона формы добавления экспоната
     */
    @GetMapping("/AddExhibit")
    public String showAddExhibitForm(Model model) {
        model.addAttribute("exhibit", new Exhibit());
        return "AddExhibit";
    }

    /**
     * Обрабатывает отправку формы добавления экспоната.
     * @param exhibit Данные экспоната из формы
     * @param bindingResult Результаты валидации
     * @return Перенаправление на страницу экспонатов или возврат формы при ошибках
     */
    @PostMapping("/AddExhibit")
    public String addExhibit(@Valid @ModelAttribute Exhibit exhibit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddExhibit";
        }
        exhibitRepository.save(exhibit);
        return "redirect:/AllExhibits";
    }

    /**
     * Показывает форму для добавления нового зала.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона формы добавления зала
     */
    @GetMapping("/AddHall")
    public String showAddHallForm(Model model) {
        model.addAttribute("hall", new Hall());
        return "AddHall";
    }

    /**
     * Обрабатывает отправку формы добавления зала.
     * @param hall Данные зала из формы
     * @param bindingResult Результаты валидации
     * @return Перенаправление на страницу залов или возврат формы при ошибках
     */
    @PostMapping("/AddHall")
    public String addHall(@Valid @ModelAttribute Hall hall, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddHall";
        }
        hallRepository.save(hall);
        return "redirect:/AllHalls";
    }

    /**
     * Показывает форму для добавления нового музея.
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона формы добавления музея
     */
    @GetMapping("/AddMuseum")
    public String showAddMuseumForm(Model model) {
        model.addAttribute("museum", new Museum());
        return "AddMuseum";
    }

    /**
     * Обрабатывает отправку формы добавления музея.
     * @param museum Объект музея с данными из формы (кроме даты основания)
     * @param bindingResult Объект для хранения результатов валидации
     * @param foundationDate Дата основания музея, получаемая отдельно из формы
     * @return Перенаправление на страницу списка музеев при успешном сохранении,
     *         или возврат на форму добавления при наличии ошибок валидации
     * @throws IllegalArgumentException При некорректных параметрах
     */
    //Fix me: правка ошибки добавления даты основания музея
    @PostMapping("/AddMuseum")
    public String addMuseum(@ModelAttribute Museum museum,
                            BindingResult bindingResult,
                            //Fix me: сделано отдельное получение даты основания музея
                            @RequestParam("foundationDate")
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate foundationDate) {
        //Fix me: добавлена проверка названия музея
        if (museum.getMuseumsName() == null || museum.getMuseumsName().trim().isEmpty()) {
            bindingResult.rejectValue("museumsName", "error.museum",
                    "Название музея обязательно");
        } else if (museum.getMuseumsName().length() > 50) {
            bindingResult.rejectValue("museumsName", "error.museum",
                    "Название не должно превышать 100 символов");
        }
        //Fix me: добавлена проверка страны
        if (museum.getCountry() == null || museum.getCountry().trim().isEmpty()) {
            bindingResult.rejectValue("country", "error.museum",
                    "Страна обязательна");
        } else if (!museum.getCountry().matches("[а-яА-Я\\s-]+")) {
            bindingResult.rejectValue("country", "error.museum",
                    "Некорректное название страны");
        }
        //Fix me: добавлена проверка города
        if (museum.getCity() == null || museum.getCity().trim().isEmpty()) {
            bindingResult.rejectValue("city", "error.museum",
                    "Город обязателен");
        } else if (!museum.getCity().matches("[а-яА-Я\\s-]+")) {
            bindingResult.rejectValue("city", "error.museum",
                    "Некорректное название города");
        }
        //Fix me: добавлена проверка даты основания
        if (museum.getFoundationYear() == null) {
            bindingResult.rejectValue("foundationYear", "error.museum",
                    "Дата основания обязательна");
        } else if (museum.getFoundationYear().isAfter(LocalDate.now())) {
            bindingResult.rejectValue("foundationYear", "error.museum",
                    "Дата основания не может быть в будущем");
        } else if (museum.getFoundationYear().getYear() < 1000) {
            bindingResult.rejectValue("foundationYear", "error.museum",
                    "Слишком ранняя дата основания");
        }

        //Fix me: добавлено для занесения даты в БД
        museum.setFoundationYear(foundationDate);

        museumRepository.save(museum);
        return "redirect:/AllMuseums";
    }

    /**
     * Показывает форму редактирования существующего экспоната.
     * @param id Идентификатор экспоната
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона формы редактирования экспоната
     * @throws IllegalArgumentException если экспонат не найден
     */
    @GetMapping("/editExhibit/{id}")
    public String showEditExhibitForm(@PathVariable Long id, Model model) {
        Exhibit exhibit = exhibitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exhibit not found"));
        model.addAttribute("exhibit", exhibit);
        return "editExhibit";
    }

    /**
     * Обрабатывает отправку формы редактирования экспоната.
     * @param id Идентификатор экспоната
     * @param updatedExhibit Обновленные данные экспоната
     * @param bindingResult Результаты валидации
     * @return Перенаправление на страницу экспонатов или возврат формы при ошибках
     * @throws IllegalArgumentException если экспонат не найден
     */
    @PostMapping("/editExhibit/{id}")
    public String editExhibit(@PathVariable Long id,
                              //TODO: перенос строки (не более 100 символов в строке)
                              @Valid @ModelAttribute Exhibit updatedExhibit,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editExhibit";
        }
        Exhibit existingExhibit = exhibitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exhibit not found"));

        existingExhibit.setExhibitsName(updatedExhibit.getExhibitsName());
        existingExhibit.setDescription(updatedExhibit.getDescription());

        exhibitRepository.save(existingExhibit);
        return "redirect:/AllExhibits";
    }

    /**
     * Показывает форму редактирования существующего зала.
     * @param id Идентификатор зала
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона формы редактирования зала
     * @throws IllegalArgumentException если зал не найден
     */
    @GetMapping("/editHall/{id}")
    public String showEditHallForm(@PathVariable Long id, Model model) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hall not found"));
        model.addAttribute("hall", hall);
        return "editHall";
    }

    /**
     * Обрабатывает отправку формы редактирования зала.
     * @param id Идентификатор зала
     * @param updatedHall Обновленные данные зала
     * @param bindingResult Результаты валидации
     * @return Перенаправление на страницу залов или возврат формы при ошибках
     * @throws IllegalArgumentException если зал не найден
     */
    @PostMapping("/editHall/{id}")
    public String editHall(@PathVariable Long id,
                           //TODO: перенос строки (не более 100 символов в строке)
                           @Valid @ModelAttribute Hall updatedHall,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editHall";
        }
        Hall existingHall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hall not found"));

        existingHall.setHallsName(updatedHall.getHallsName());

        hallRepository.save(existingHall);
        return "redirect:/AllHalls";
    }

    /**
     * Показывает форму редактирования существующего музея.
     * @param id Идентификатор музея
     * @param model Модель для передачи данных в представление
     * @return Имя шаблона формы редактирования музея
     * @throws IllegalArgumentException если музей не найден
     */
    @GetMapping("/editMuseum/{id}")
    public String showEditMuseumForm(@PathVariable Long id, Model model) {
        Museum museum = museumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Museum not found"));
        model.addAttribute("museum", museum);
        return "editMuseum";
    }

    /**
     * Обрабатывает отправку формы редактирования музея.
     * @param id Идентификатор музея
     * @param updatedMuseum Обновленные данные музея
     * @param bindingResult Результаты валидации
     * @return Перенаправление на страницу музеев или возврат формы при ошибках
     * @throws IllegalArgumentException если музей не найден
     */
    //Fix me: правка проблемы обновления даты
    @PostMapping("/editMuseum/{id}")
    public String editMuseum(@PathVariable Long id,
                             @Valid @ModelAttribute("museum") Museum updatedMuseum,
                             BindingResult bindingResult) {
        //Fix me: добавлена проверка названия музея
        if (updatedMuseum.getMuseumsName() == null || updatedMuseum.getMuseumsName().trim().isEmpty()) {
            bindingResult.rejectValue("museumsName", "error.museum",
                    "Название музея обязательно");
        } else if (updatedMuseum.getMuseumsName().length() > 50) {
            bindingResult.rejectValue("museumsName", "error.museum",
                    "Название не должно превышать 50 символов");
        }
        //Fix me: добавлена проверка страны
        if (updatedMuseum.getCountry() == null || updatedMuseum.getCountry().trim().isEmpty()) {
            bindingResult.rejectValue("country", "error.museum",
                    "Страна обязательна");
        } else if (!updatedMuseum.getCountry().matches("[а-яА-Я\\s-]+")) {
            bindingResult.rejectValue("country", "error.museum",
                    "Некорректное название страны");
        }
        //Fix me: добавлена проверка города
        if (updatedMuseum.getCity() == null || updatedMuseum.getCity().trim().isEmpty()) {
            bindingResult.rejectValue("city", "error.museum",
                    "Город обязателен");
        } else if (!updatedMuseum.getCity().matches("[а-яА-Я\\s-]+")) {
            bindingResult.rejectValue("city", "error.museum",
                    "Некорректное название города");
        }
        //Fix me: добавлена проверка даты основания
        if (updatedMuseum.getFoundationYear() == null) {
            bindingResult.rejectValue("foundationYear", "error.museum",
                    "Дата основания обязательна");
        } else if (updatedMuseum.getFoundationYear().isAfter(LocalDate.now())) {
            bindingResult.rejectValue("foundationYear", "error.museum",
                    "Дата основания не может быть в будущем");
        } else if (updatedMuseum.getFoundationYear().getYear() < 1000) {
            bindingResult.rejectValue("foundationYear", "error.museum",
                    "Слишком ранняя дата основания");
        }

        Museum existingMuseum = museumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Музей не найден"));

        existingMuseum.setMuseumsName(updatedMuseum.getMuseumsName());
        existingMuseum.setFoundationYear(updatedMuseum.getFoundationYear());
        existingMuseum.setCountry(updatedMuseum.getCountry());
        existingMuseum.setCity(updatedMuseum.getCity());

        museumRepository.save(existingMuseum);
        return "redirect:/AllMuseums";
    }

    /**
     * Удаляет экспонат по идентификатору.
     * @param id Идентификатор экспоната
     * @return Перенаправление на страницу экспонатов
     */
    @PostMapping("/deleteExhibit/{id}")
    public String deleteExhibit(@PathVariable Long id) {
        exhibitRepository.deleteById(id);
        return "redirect:/AllExhibits";
    }

    /**
     * Удаляет зал по идентификатору.
     * @param id Идентификатор зала
     * @return Перенаправление на страницу залов
     */
    @PostMapping("/deleteHall/{id}")
    public String deleteHall(@PathVariable Long id) {
        hallRepository.deleteById(id);
        return "redirect:/AllHalls";
    }

    /**
     * Удаляет музей по идентификатору.
     * @param id Идентификатор музея
     * @return Перенаправление на страницу музеев
     */
    @PostMapping("/deleteMuseum/{id}")
    public String deleteMuseum(@PathVariable Long id) {
        museumRepository.deleteById(id);
        return "redirect:/AllMuseums";
    }
}