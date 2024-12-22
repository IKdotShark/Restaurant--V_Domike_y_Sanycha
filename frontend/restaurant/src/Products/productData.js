export const Titles = {
  specialOffers: 'Специальные предложения',
  dishes: 'Блюда',
  desserts: 'Десерты',
  drinks: 'Напитки',
};

export const IDs = {
    dishesIds: [1],  // Пример ID блюд
    drinksIds: [1, 2],  // Пример ID напитков
    desertsIds: [1], // Пример ID десертов
}

export const specialOffers = [
  {
    id: 1,
    name: 'Несквик с пивом',
    price: 349,
    image: './src/assets/products/nesquick.png',
    description: 'Несквик с пивом — неожиданный и смелый коктейль, сочетающий сладость какао и горчинку хмеля. Отличный выбор для любителей экспериментов!',
    ingredients: ['Несквик', 'Светлое пиво', 'Сахар', 'Молоко']
  },
  {
    id: 2,
    name: 'Торт GlaDOS',
    price: 333,
    image: './src/assets/products/cake.png',
    description: 'Торт GlaDOS — загадочный десерт, вдохновленный культовой игрой Portal. Нежный шоколадный вкус с нотками ванили.',
    ingredients: ['Шоколадный бисквит', 'Ванильный крем', 'Глазурь', 'Ягоды']
  },
  {
    id: 3,
    name: 'Эстус',
    price: 999,
    image: './src/assets/products/estus.png',
    description: 'Эстус — согревающий напиток из мира Dark Souls. Прекрасно подходит для восстановления сил после долгого дня.',
    ingredients: ['Травяной настой', 'Апельсиновая цедра', 'Мёд', 'Имбирь']
  },
  {
    id: 4,
    name: 'Бледина',
    price: 199,
    image: './src/assets/products/bledina.png',
    description: 'Бледина — вкусное фруктовое пюре, созданное специально для самых маленьких. Полезный и питательный перекус.',
    ingredients: ['Яблочное пюре', 'Банановое пюре', 'Лимонный сок']
  },
  {
    id: 5,
    name: 'Ещё Несквик',
    price: 349,
    image: './src/assets/products/nesquick.png',
    description: 'Несквик с пивом — неожиданный и смелый коктейль, сочетающий сладость какао и горчинку хмеля. Отличный выбор для любителей экспериментов!',
    ingredients: ['Несквик', 'Светлое пиво', 'Сахар', 'Молоко']
  }
];

export const dishes = [
  {
    id: 8,
    name: 'Мороженое',
    price: 200,
    image: './src/assets/products/icecream.png',
    description: 'Мороженое — освежающее лакомство, идеально подходящее для жарких дней. Кремовая текстура и нежный вкус.',
    ingredients: ['Молоко', 'Сливки', 'Сахар', 'Ваниль']
  },
  {
    id: 9,
    name: 'Пирожное',
    price: 150,
    image: './src/assets/products/cake.png',
    description: 'Торт GlaDOS — загадочный десерт, вдохновленный культовой игрой Portal. Нежный шоколадный вкус с нотками ванили.',
    ingredients: ['Бисквит', 'Крем', 'Шоколадная крошка']
  }
];

export const desserts = [
  {
    id: 10,
    name: 'Мороженое',
    price: 200,
    image: './src/assets/products/icecream.png',
    description: 'Мороженое — освежающее лакомство, идеально подходящее для жарких дней. Кремовая текстура и нежный вкус.',
    ingredients: ['Молоко', 'Сливки', 'Сахар', 'Ваниль']
  },
  {
    id: 11,
    name: 'Пирожное',
    price: 150,
    image: './src/assets/products/cake.png',
    description: 'Торт GlaDOS — загадочный десерт, вдохновленный культовой игрой Portal. Нежный шоколадный вкус с нотками ванили.',
    ingredients: ['Бисквит', 'Крем', 'Шоколадная крошка']
  }
];

// Изменения в массиве drinks
export const drinks = [
  {
    id: 12,
    name: 'Клубничный коктейль',
    price: 200,
    image: './src/assets/products/icecream.png',
    description: 'Освежающий коктейль с насыщенным вкусом клубники.',
    volume: '300ml' // Заменяем ингредиенты на объем
  },
  {
    id: 13,
    name: 'Шоколадный коктейль',
    price: 150,
    image: './src/assets/products/cake.png',
    description: 'Густой и насыщенный шоколадный коктейль.',
    volume: '250ml' // Заменяем ингредиенты на объем
  },
  {
    id: 14,
    name: 'Водка с квасом',
    price: 150,
    image: './src/assets/products/vodkakvas.jpg',
    description: 'Спросите у батина',
    volume: '500ml' // Заменяем ингредиенты на объем
  }
];
