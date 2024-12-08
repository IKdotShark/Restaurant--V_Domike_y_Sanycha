import React, { useState } from "react";
import styles from "./ProductCard.module.css";
import { useCart } from "../Cart/CartContext";

function ProductCard({ product }) {
  const [isModalOpen, setModalOpen] = useState(false);
  const { addToCart } = useCart();

  const handleCardClick = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  const handleBasketClick = (e) => {
    e.stopPropagation(); // Остановить распространение клика
    addToCart(product); // Добавляем товар в корзину
  };

  const ingredientsArray = product.ingredients.map((ingredient) => (
    <li key={ingredient}>{ingredient}</li>
  ));

  return (
    <>
      <div className={styles.card} onClick={handleCardClick}>
        <img src={product.image} alt={product.name} className={styles.image} />
        <h3 className={styles.name}>{product.name}</h3>
        <p className={styles.price}>{product.price} ₽</p>
        <button className={styles.button} onClick={handleBasketClick}>
          В корзину
        </button>
      </div>

      {isModalOpen && (
        <div className={styles.modalOverlay} onClick={handleCloseModal}>
          <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
            <img
              src={product.image}
              alt={product.name}
              className={styles.modalImage}
            />
            <div className={styles.modalContent}>
              <div>
                <h2 className={styles.modalHeader}>{product.name}</h2>
                <p className={styles.modalDescription}>
                  {product.description || "Описание отсутствует"}
                </p>
                <ul className={styles.modalIngredients}>
                  {product.ingredients?.map((item, index) => (
                    <li key={index}>{item}</li>
                  ))}
                </ul>
              </div>
              <p className={styles.price}>{product.price} ₽</p>
              <div className={styles.modalFooter}>
                <button className={styles.button} onClick={handleBasketClick}>
                  В корзину
                </button>
                <button className={styles.button} onClick={handleCloseModal}>
                  Закрыть
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default ProductCard;
