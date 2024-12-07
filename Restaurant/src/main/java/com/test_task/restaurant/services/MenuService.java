package com.test_task.restaurant.services;

import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Menu;
import com.test_task.restaurant.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteMenuById(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isEmpty()) throw new ResourceNotFoundException("Not found menu with such id: " + id);
        menuRepository.deleteById(id);
    }
}
