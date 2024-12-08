import React, { useRef } from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Logo from "./Logo/Logo";
import ProductModule from "./Products/ProductModule";
import { Titles, specialOffers, dishes, desserts, drinks } from "./Products/productData";
import Cart from "./Cart/Cart";
import { CartProvider } from "./Cart/CartContext";

function App() {
  const specialOffersRef = useRef(null);
  const dishesRef = useRef(null);
  const dessertsRef = useRef(null);
  const drinksRef = useRef(null);

  const HEADER_HEIGHT = 128;

  const scrollToSection = (ref) => {
    const sectionPosition = ref.current.offsetTop;
    window.scrollTo({
      top: sectionPosition - HEADER_HEIGHT,
      behavior: "smooth",
    });
  };

  return (
    <CartProvider>
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
      <Cart />
      <Footer />
    </CartProvider>
  );
}

export default App;
