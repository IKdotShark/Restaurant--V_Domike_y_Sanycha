import React, { useRef } from "react";
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Logo from "./Logo/Logo";
import ProductSection from "./Products/ProductSection"; // Импортируем новый компонент
import { CartProvider } from "./Cart/CartContext";
import Cart from "./Cart/Cart";

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
      <div className="mainContent">
        <ProductSection
          specialOffersRef={specialOffersRef}
          dishesRef={dishesRef}
          dessertsRef={dessertsRef}
          drinksRef={drinksRef}
        />
        <Cart />
      </div>
      <Footer />
    </CartProvider>
  );
}

export default App;
