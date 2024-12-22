// src/Products/ProductSection.jsx
import React, { useState, useEffect } from "react";
import axios from "axios";
import ProductModule from "./ProductModule";
import { Titles, IDs } from "./productData";

function ProductSection({ specialOffersRef, dishesRef, dessertsRef, drinksRef }) {
  const [products, setProducts] = useState({
    specialOffers: [],
    dishes: [],
    desserts: [],
    drinks: [],
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Функция для загрузки данных с сервера
    const fetchData = async () => {
      setLoading(true);
      setError(null);

      try {
        const response = await axios.get("http://localhost:8080/api/menu/1");
        
        setProducts({
          specialOffers: response.data.specialOffers || [],
          dishes: response.data.dishes || [],
          desserts: response.data.deserts || [],
          drinks: response.data.drinks || [],
        });
        setLoading(false);
      } catch (err) {
        console.error("Ошибка при загрузке данных:", err.message);
        setError("Ошибка при загрузке данных");
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <p>Загрузка...</p>;
  if (error) return <p>{error}</p>;

  return (
    <>
      <div ref={specialOffersRef}>
        <ProductModule
          title={Titles.specialOffers}
          products={products.specialOffers}
          titleColor="red"
        />
      </div>
      <div ref={dishesRef}>
        <ProductModule
          title={Titles.dishes}
          products={products.dishes}
          titleColor="black"
        />
      </div>
      <div ref={dessertsRef}>
        <ProductModule
          title={Titles.desserts}
          products={products.desserts}
          titleColor="black"
        />
      </div>
      <div ref={drinksRef}>
        <ProductModule
          title={Titles.drinks}
          products={products.drinks}
          titleColor="black"
        />
      </div>
    </>
  );
}

export default ProductSection;
