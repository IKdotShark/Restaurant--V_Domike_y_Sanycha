package com.test_task.restaurant.controllers;

import com.test_task.restaurant.Dto.DesertRequest;
import com.test_task.restaurant.Dto.DishRequest;
import com.test_task.restaurant.Dto.DrinkRequest;
import com.test_task.restaurant.models.Menu;
import com.test_task.restaurant.services.MenuService;
import com.test_task.restaurant.services.DishService;
import com.test_task.restaurant.services.DrinkService;
import com.test_task.restaurant.services.DesertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;
    private final DishService dishService;
    private final DrinkService drinkService;
    private final DesertService desertService;

    public MenuController(MenuService menuService, DishService dishService, DrinkService drinkService, DesertService desertService) {
        this.menuService = menuService;
        this.dishService = dishService;
        this.drinkService = drinkService;
        this.desertService = desertService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findMenuById(@PathVariable Long id) {
        Menu menu = menuService.findMenuById(id);
        menuService.convertStringIngrToArray(menu);
        return ResponseEntity.ok(menu);
    }

    @GetMapping
    public ResponseEntity<?> getAllMenuByCategory(@RequestParam(required = false) String category) {
        Object result = menuService.findAllItemsByCategory(category);

        if (result instanceof List<?> menus) {
            if (menus.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(menus);
        }

        if (result instanceof DishRequest dishRequest) {
            return ResponseEntity.ok(dishRequest);
        }

        if (result instanceof DrinkRequest drinkRequest) {
            return ResponseEntity.ok(drinkRequest);
        }

        if (result instanceof DesertRequest desertRequest) {
            return ResponseEntity.ok(desertRequest);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected result type");
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menuRequest) {
        Menu createdMenu = menuService.createMenu(menuRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu menuInfo, @PathVariable Long id) {
        Menu menu = menuService.findMenuById(id);
        Menu menuIdAdder = menuService.menuIdAdder(menuInfo, menu);
        Menu updatedMenu = menuService.updateMenu(menuIdAdder);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenuById(id);
        return ResponseEntity.noContent().build();
    }
}
