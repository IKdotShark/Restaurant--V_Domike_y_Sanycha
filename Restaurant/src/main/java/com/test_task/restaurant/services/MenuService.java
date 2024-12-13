package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Menu;
import com.test_task.restaurant.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found menu with such id: " + id));
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public List<Menu> findAllItemsByCategory(String category) {
        if (category == null) {
            return findAllMenus();
        }

        return switch (category.toLowerCase()) {
            case "dishes" -> findMenusWithDishes();
            case "drinks" -> findMenusWithDrinks();
            case "deserts" -> findMenusWithDeserts();
            default -> findAllMenus();
        };
    }

    private List<Menu> findMenusWithDishes() {
        return findAllMenus().stream()
                .filter(menu -> !menu.getDishes().isEmpty())
                .collect(Collectors.toList());
    }

    private List<Menu> findMenusWithDrinks() {
        return findAllMenus().stream()
                .filter(menu -> !menu.getDrinks().isEmpty())
                .collect(Collectors.toList());
    }

    private List<Menu> findMenusWithDeserts() {
        return findAllMenus().stream()
                .filter(menu -> !menu.getDeserts().isEmpty())
                .collect(Collectors.toList());
    }

    public void deleteMenuById(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not found menu with such id: " + id);
        }
        menuRepository.deleteById(id);
    }
}
