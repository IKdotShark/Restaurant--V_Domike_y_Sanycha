import React, { useRef } from 'react';
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Logo from "./Logo/Logo";
import ProductModule from './Products/ProductModule';
import { Titles, specialOffers, dishes, desserts, drinks } from './Products/productData';

function App() {
  // Создаем рефы для каждой секции
  const specialOffersRef = useRef(null);
  const dishesRef = useRef(null);
  const dessertsRef = useRef(null);
  const drinksRef = useRef(null);

  // Высота хедера (можно также вынести в переменную)
  const HEADER_HEIGHT = 128;

  // Функция для прокрутки с учётом хедера
  const scrollToSection = (ref) => {
    const sectionPosition = ref.current.offsetTop; // Позиция секции
    window.scrollTo({
      top: sectionPosition - HEADER_HEIGHT, // Учитываем высоту хедера
      behavior: "smooth",
    });
  };

  return (
    <>
      {/* Передаем scrollToSection для навигации */}
      <Header
        scrollToSection={scrollToSection}
        refs={{
          specialOffersRef,
          dishesRef,
          dessertsRef,
          drinksRef,
        }}
      />
      <Logo />
      <div ref={specialOffersRef}>
        <ProductModule
          title={Titles.specialOffers}
          products={specialOffers}
          titleColor="red"
        />
      </div>
      <div ref={dishesRef}>
        <ProductModule
          title={Titles.dishes}
          products={dishes}
          titleColor="black"
        />
      </div>
      <div ref={dessertsRef}>
        <ProductModule
          title={Titles.desserts}
          products={desserts}
          titleColor="black"
        />
      </div>
      <div ref={drinksRef}>
        <ProductModule
          title={Titles.drinks}
          products={drinks}
          titleColor="black"
        />
      </div>
      <Footer />
    </>
  );
}

export default App;
