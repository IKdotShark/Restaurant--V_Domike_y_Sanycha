package com.test_task.restaurant.services;

import com.test_task.restaurant.Dto.DesertRequest;
import com.test_task.restaurant.Dto.DishRequest;
import com.test_task.restaurant.Dto.DrinkRequest;
import com.test_task.restaurant.exception.ResourceNotFoundException;
import com.test_task.restaurant.models.Desert;
import com.test_task.restaurant.models.Dish;
import com.test_task.restaurant.models.Drink;
import com.test_task.restaurant.models.Menu;
import com.test_task.restaurant.repositories.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final DishService dishService;
    private final DesertService desertService;
    private final DrinkService drinkService;

    public MenuService(MenuRepository menuRepository,
                       DishService dishService,
                       DesertService desertService,
                       DrinkService drinkService) {
        this.menuRepository = menuRepository;
        this.dishService = dishService;
        this.desertService = desertService;
        this.drinkService = drinkService;
    }

    public Menu findMenuById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found menu with such id: " + id));
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public Object findAllItemsByCategory(String category) {
        if (category == null) {
            return findAllMenus();
        }

        return switch (category.toLowerCase()) {
            case "dish" -> findMenusWithDishes();
            case "drink" -> findMenusWithDrinks();
            case "desert" -> findMenusWithDeserts();
            default -> findAllMenus();
        };
    }

    private DishRequest findMenusWithDishes() {
        List<Dish> dishes = findAllMenus().stream()
                .filter(menu -> !menu.getDishes().isEmpty())
                .flatMap(menu -> menu.getDishes().stream())
                .collect(Collectors.toList());

        DishRequest dishRequest = new DishRequest();
        dishRequest.setDishList(dishes);
        return dishRequest;
    }


    private DrinkRequest findMenusWithDrinks() {
        List<Drink> drinks = findAllMenus().stream()
                .filter(menu -> !menu.getDrinks().isEmpty())
                .flatMap(menu -> menu.getDrinks().stream())
                .collect(Collectors.toList());

        DrinkRequest drinkRequest = new DrinkRequest();
        drinkRequest.setDrinkList(drinks);
        return drinkRequest;
    }

    private DesertRequest findMenusWithDeserts() {
        List<Desert> deserts = findAllMenus().stream()
                .filter(menu -> !menu.getDeserts().isEmpty())
                .flatMap(menu -> menu.getDeserts().stream())
                .collect(Collectors.toList());

        DesertRequest desertRequest = new DesertRequest();
        desertRequest.setDesertList(deserts);
        return desertRequest;
    }

    public void settingMenuId(Menu menu) {
        List<Dish> dishes = menu.getDishes();
        List<Drink> drinks = menu.getDrinks();
        List<Desert> deserts = menu.getDeserts();

        dishes.forEach(dish -> dish.setMenuId(menu.getId() != null ? menu.getId() : 1L));
        drinks.forEach(drink -> drink.setMenuId(menu.getId() != null ? menu.getId() : 1L));
        deserts.forEach(desert -> desert.setMenuId(menu.getId() != null ? menu.getId() : 1L));
    }


    public Menu menuIdAdder(Menu menuInfo, Menu menu) {
        if (menuInfo.getDishesIds() != null) {
            mergeCollections(menu.getDishesIds(), menuInfo.getDishesIds());
        }

        if (menuInfo.getDrinksIds() != null) {
            mergeCollections(menu.getDrinksIds(), menuInfo.getDrinksIds());
        }

        if (menuInfo.getDesertsIds() != null) {
            mergeCollections(menu.getDesertsIds(), menuInfo.getDesertsIds());
        }

        return menu;
    }
    public Menu updateMenu(Menu menu) {
        return saveOrUpdateMenu(menu);
    }

    public Menu createMenu(Menu menuRequest) {
        Menu menu = Menu.getInstance();
        menu.setDishesIds(menuRequest.getDishesIds());
        menu.setDrinksIds(menuRequest.getDrinksIds());
        menu.setDesertsIds(menuRequest.getDesertsIds());

        return saveOrUpdateMenu(menu);
    }

    private Menu saveOrUpdateMenu(Menu menu) {
        List<Long> dishesIds = menu.getDishesIds();
        List<Long> drinksIds = menu.getDrinksIds();
        List<Long> desertsIds = menu.getDesertsIds();

        menu.setDishes(dishService.findDishesByIds(dishesIds));
        menu.setDrinks(drinkService.findDrinksByIds(drinksIds));
        menu.setDeserts(desertService.findDesertsByIds(desertsIds));

        settingMenuId(menu);

        return menuRepository.save(menu);
    }

    public void deleteMenuById(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new ResourceNotFoundException("Not found menu with such id: " + id);
        }
        menuRepository.deleteById(id);
    }

    public void convertStringIngrToArray(Menu menu) {
        menu.getDishes().forEach(dish -> {
            if (dish.getIngredients() != null && !dish.getIngredients().isEmpty()) {
                List<String> ingredientsList = Arrays.asList(dish.getIngredients().split(","));
                dish.setMenuId(menu.getId());
                dish.setTransientIngredients(ingredientsList);
            }
        });

        menu.getDeserts().forEach(desert -> {
            if (desert.getIngredients() != null && !desert.getIngredients().isEmpty()) {
                List<String> ingredientsList = Arrays.asList(desert.getIngredients().split(","));
                desert.setMenuId(menu.getId());
                desert.setTransientIngredients(ingredientsList);
            }
        });
    }

    private <T> void mergeCollections(List<T> existing, List<T> updates) {
        existing.clear();
        existing.addAll(updates);
    }
}
