package com.test_task.restaurant.controllers;

import com.test_task.restaurant.models.Menu;
import com.test_task.restaurant.services.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findMenuById(@PathVariable Long id) {
        Menu menu = menuService.findMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenuByCategory(@RequestParam(required = false) String category) {
        List<Menu> menus = menuService.findAllItemsByCategory(category);

        if (menus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(menus);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu createdMenu = menuService.createMenu(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menuInfo, @PathVariable Long id) {
        Menu menu = menuService.findMenuById(id);
        menu.setDeserts(menuInfo.getDeserts());
        menu.setDishes(menuInfo.getDishes());
        menu.setDeserts(menuInfo.getDeserts());
        menuService.createMenu(menu);
        return ResponseEntity.ok(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenuById(id);
        return ResponseEntity.noContent().build();
    }
}
