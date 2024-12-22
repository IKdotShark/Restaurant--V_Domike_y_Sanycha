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

  // Логика для отображения ингредиентов или объема
  const renderIngredientsOrVolume = (product) => {
    if (product.volume) {
      return <p className={styles.modalVolume}><strong>Объем:</strong> {product.volume}</p>;
    } else if (product.ingredients) {
      return (
        <ul className={styles.modalIngredients}>
          {product.ingredients.map((item, index) => (
            <li key={index}>{item}</li>
          ))}
        </ul>
      );
    }
    return null;
  };

  return (
    <>
      <div className={styles.card} onClick={handleCardClick}>
        <img src={product.src} alt={product.name} className={styles.image} />
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
              src={product.src}
              alt={product.name}
              className={styles.modalImage}
            />
            <div className={styles.modalContent}>
              <div>
                <h2 className={styles.modalHeader}>{product.name}</h2>
                <p className={styles.modalDescription}>
                  {product.description || "Описание отсутствует"}
                </p>
                {renderIngredientsOrVolume(product)} {/* Выводим ингредиенты или объем */}
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
