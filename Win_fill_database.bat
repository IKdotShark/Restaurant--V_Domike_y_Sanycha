@echo off
:: Script for filling the database with test data

:: Dishes
curl -X POST http://localhost:8080/api/dishes -H "Content-Type: application/json" -d "{ \"name\": \"Цезарь с курицей\", \"price\": 350.0, \"ingredients\": [\"Куриное филе\", \"Салат айсберг\", \"Сыр пармезан\", \"Соус цезарь\", \"Гренки\"], \"description\": \"Классический салат Цезарь с сочным куриным филе и хрустящими гренками.\", \"category\": \"Dish\", \"src\": \"./src/assets/products/cesar_chicken.png\" }"

curl -X POST http://localhost:8080/api/dishes -H "Content-Type: application/json" -d "{ \"name\": \"Паста карбонара\", \"price\": 400.0, \"ingredients\": [\"Спагетти\", \"Бекон\", \"Сливки\", \"Пармезан\", \"Яичный желток\"], \"description\": \"Итальянская паста в сливочном соусе с ароматным беконом.\", \"category\": \"Dish\", \"src\": \"./src/assets/products/pasta_carbonara.png\" }"

curl -X POST http://localhost:8080/api/dishes -H "Content-Type: application/json" -d "{ \"name\": \"Рыбный суп\", \"price\": 300.0, \"ingredients\": [\"Форель\", \"Картофель\", \"Морковь\", \"Лук\", \"Сливки\"], \"description\": \"Нежный рыбный суп с форелью и сливками.\", \"category\": \"Dish\", \"src\": \"./src/assets/products/fish_soup.png\" }"

:: Drinks
curl -X POST http://localhost:8080/api/drinks -H "Content-Type: application/json" -d "{ \"name\": \"Лимонад классический\", \"price\": 120.0, \"volume\": \"500ml\", \"description\": \"Освежающий домашний лимонад с ярким лимонным вкусом.\", \"category\": \"Drink\", \"src\": \"./src/assets/products/lemonade.png\" }"

curl -X POST http://localhost:8080/api/drinks -H "Content-Type: application/json" -d "{ \"name\": \"Капучино\", \"price\": 150.0, \"volume\": \"200ml\", \"description\": \"Классический капучино с мягким сливочным вкусом.\", \"category\": \"Drink\", \"src\": \"./src/assets/products/cappuccino.png\" }"

curl -X POST http://localhost:8080/api/drinks -H "Content-Type: application/json" -d "{ \"name\": \"Фруктовый смузи\", \"price\": 200.0, \"volume\": \"400ml\", \"description\": \"Натуральный фруктовый смузи без добавления сахара.\", \"category\": \"Drink\", \"src\": \"./src/assets/products/fruit_smoothie.png\" }"

:: Desserts
curl -X POST http://localhost:8080/api/deserts -H "Content-Type: application/json" -d "{ \"name\": \"Тирамису\", \"price\": 250.0, \"ingredients\": [\"Савоярди\", \"Маскарпоне\", \"Кофе\", \"Какао\"], \"description\": \"Итальянский десерт с нежным кремом и ароматным кофе.\", \"category\": \"Desert\", \"src\": \"./src/assets/products/tiramisu.png\" }"

curl -X POST http://localhost:8080/api/deserts -H "Content-Type: application/json" -d "{ \"name\": \"Чизкейк классический\", \"price\": 270.0, \"ingredients\": [\"Сливочный сыр\", \"Песочное тесто\", \"Сахар\", \"Ваниль\"], \"description\": \"Классический чизкейк с нежной текстурой.\", \"category\": \"Desert\", \"src\": \"./src/assets/products/cheesecake.png\" }"

curl -X POST http://localhost:8080/api/deserts -H "Content-Type: application/json" -d "{ \"name\": \"Мороженое пломбир\", \"price\": 100.0, \"ingredients\": [\"Молоко\", \"Сливки\", \"Сахар\"], \"description\": \"Классическое сливочное мороженое с насыщенным вкусом.\", \"category\": \"Desert\", \"src\": \"./src/assets/products/ice_cream.png\" }"

:: Menu
curl -X POST http://localhost:8080/api/menu -H "Content-Type: application/json" -d "{ \"dishesIds\": [1,2,3], \"drinksIds\": [1,2,3], \"desertsIds\": [1,2,3] }"

:: Supplier
curl -X POST http://localhost:8080/api/suppliers -H "Content-Type: application/json" -d "{ \"name\": \"ООО Поставка Продуктов\", \"contactInfo\": \"+7 (900) 123-45-67\" }"

:: Inventory
curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Цезарь с курицей\", \"quantity\": 50, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Паста карбонара\", \"quantity\": 40, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Рыбный суп\", \"quantity\": 30, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Лимонад классический\", \"quantity\": 100, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Капучино\", \"quantity\": 80, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Фруктовый смузи\", \"quantity\": 60, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Тирамису\", \"quantity\": 40, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Чизкейк классический\", \"quantity\": 50, \"supplier\": { \"id\": 1 } }"

curl -X POST http://localhost:8080/api/inventory -H "Content-Type: application/json" -d "{ \"productName\": \"Мороженое пломбир\", \"quantity\": 100, \"supplier\": { \"id\": 1 } }"

pause
