package ru.ikm.max.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ikm.max.entity.*;
import ru.ikm.max.repository.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ExhibitRepository exhibitRepository;
    private final HallRepository hallRepository;
    private final MuseumRepository museumRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Exhibit> exhibits = exhibitRepository.findAll();
        List<Hall> halls = hallRepository.findAll();
        List<Museum> museums = museumRepository.findAll();
        model.addAttribute("exhibits", exhibits);
        model.addAttribute("halls", halls);
        model.addAttribute("museums", museums);
        return "index";
    }

    @GetMapping("/AllExhibits")
    public String allExhibits(Model model) {
        List<Exhibit> exhibits = exhibitRepository.findAll();
        model.addAttribute("exhibits", exhibits);
        return "AllExhibits";
    }

    @GetMapping("/AllHalls")
    public String allHalls(Model model) {
        List<Hall> halls = hallRepository.findAll();
        model.addAttribute("halls", halls);
        return "AllHalls";
    }

    @GetMapping("/AllMuseums")
    public String allMuseums(Model model) {
        List<Museum> museums = museumRepository.findAll();
        model.addAttribute("museums", museums);
        return "AllMuseums";
    }

    // Add
    @GetMapping("/AddExhibit")
    public String showAddExhibitForm(Model model) {
        model.addAttribute("exhibit", new Exhibit());
        return "AddExhibit";
    }

    @PostMapping("/AddExhibit")
    public String addExhibit(@Valid @ModelAttribute Exhibit exhibit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddExhibit";
        }
        exhibitRepository.save(exhibit);
        return "redirect:/AllExhibits";
    }

    @GetMapping("/AddHall")
    public String showAddHallForm(Model model) {
        model.addAttribute("hall", new Hall());
        return "AddHall";
    }

    @PostMapping("/AddHall")
    public String addHall(@Valid @ModelAttribute Hall hall, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddHall";
        }
        hallRepository.save(hall);
        return "redirect:/AllHalls";
    }

    @GetMapping("/AddMuseum")
    public String showAddMuseumForm(Model model) {
        model.addAttribute("museum", new Museum());
        return "AddMuseum";
    }

    @PostMapping("/AddMuseum")
    public String addMuseum(@Valid @ModelAttribute Museum museum, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddMuseum";
        }
        museumRepository.save(museum);
        return "redirect:/AllMuseums";
    }

    // Edit Exhibit
    @GetMapping("/editExhibit/{id}")
    public String showEditExhibitForm(@PathVariable Long id, Model model) {
        Exhibit exhibit = exhibitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exhibit not found"));
        model.addAttribute("exhibit", exhibit);
        return "editExhibit";
    }

    @PostMapping("/editExhibit/{id}")
    public String editExhibit(@PathVariable Long id, @Valid @ModelAttribute Exhibit updatedExhibit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editExhibit";
        }
        Exhibit existingExhibit = exhibitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Exhibit not found"));

        // Обновляем поля существующего экспоната
        existingExhibit.setExhibitsName(updatedExhibit.getExhibitsName());
        existingExhibit.setDescription(updatedExhibit.getDescription());

        exhibitRepository.save(existingExhibit);
        return "redirect:/AllExhibits";
    }

    // Edit Hall
    @GetMapping("/editHall/{id}")
    public String showEditHallForm(@PathVariable Long id, Model model) {
        Hall hall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hall not found"));
        model.addAttribute("hall", hall);
        return "editHall";
    }

    @PostMapping("/editHall/{id}")
    public String editHall(@PathVariable Long id, @Valid @ModelAttribute Hall updatedHall, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editHall";
        }
        Hall existingHall = hallRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hall not found"));

        // Обновляем поля существующего зала
        existingHall.setHallsName(updatedHall.getHallsName());

        hallRepository.save(existingHall);
        return "redirect:/AllHalls";
    }

    // Edit Museum
    @GetMapping("/editMuseum/{id}")
    public String showEditMuseumForm(@PathVariable Long id, Model model) {
        Museum museum = museumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Museum not found"));
        model.addAttribute("museum", museum);
        return "editMuseum";
    }

    @PostMapping("/editMuseum/{id}")
    public String editMuseum(@PathVariable Long id, @Valid @ModelAttribute Museum updatedMuseum, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editMuseum";
        }
        Museum existingMuseum = museumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Museum not found"));

        // Обновляем поля существующего музея
        existingMuseum.setMuseumsName(updatedMuseum.getMuseumsName());
        existingMuseum.setFoundationYear(updatedMuseum.getFoundationYear());
        existingMuseum.setCountry(updatedMuseum.getCountry());
        existingMuseum.setCity(updatedMuseum.getCity());

        museumRepository.save(existingMuseum);
        return "redirect:/AllMuseums";
    }

    // Delete
    @PostMapping("/deleteExhibit/{id}")
    public String deleteExhibit(@PathVariable Long id) {
        exhibitRepository.deleteById(id);
        return "redirect:/AllExhibits";
    }

    @PostMapping("/deleteHall/{id}")
    public String deleteHall(@PathVariable Long id) {
        hallRepository.deleteById(id);
        return "redirect:/AllHalls";
    }

    @PostMapping("/deleteMuseum/{id}")
    public String deleteMuseum(@PathVariable Long id) {
        museumRepository.deleteById(id);
        return "redirect:/AllMuseums";
    }
}