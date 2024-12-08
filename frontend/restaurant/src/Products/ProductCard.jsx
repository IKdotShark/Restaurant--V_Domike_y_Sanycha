import React, { useState } from 'react';
import styles from './ProductCard.module.css';

function ProductCard({ product }) {
  const [isModalOpen, setModalOpen] = useState(false);

  const handleCardClick = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  const handleBasketClick = (e) => {
    e.stopPropagation(); // Остановить распространение клика вверх по DOM
  };

  const ingredientsArray = product.ingredients.map((ingredient) => <li key={ingredient}>{ingredient}</li>);

  return (
    <>
      {/* Карточка продукта */}
      <div className={styles.card} onClick={handleCardClick}>
        <img src={product.image} alt={product.name} className={styles.image} />
        <h3 className={styles.name}>{product.name}</h3>
        <p className={styles.price}>{product.price} ₽</p>
        {/* Кнопка "В корзину" с отдельным обработчиком кликов */}
        <button className={styles.button} onClick={handleBasketClick}>
          В корзину
        </button>
      </div>

      {/* Модальное окно */}
      {isModalOpen && (
        <div className={styles.modalOverlay} onClick={handleCloseModal}>
          <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
            <h2 className={styles.modalTitle}>{product.name}</h2>
            <img src={product.image} alt={product.name} className={styles.image} />
            <p className={styles.modalDescription}>
              {product.description || 'Описание отсутствует'}
            </p>
            <ul className={styles.modalIngredients}>{ingredientsArray}</ul>
            <p className={styles.price}>{product.price} ₽</p>
            <button className={styles.button} onClick={handleBasketClick}>
              В корзину
           </button>
           <br/>
            <button className={styles.closeButton} onClick={handleCloseModal}>
              Закрыть
            </button>
          </div>
        </div>
      )}
    </>
  );
}

export default ProductCard;
