import React, { useState } from "react";
import styles from "./Cart.module.css";
import { useCart } from "./CartContext";

function Cart() {
  const [isCartOpen, setCartOpen] = useState(false);
  const [isOrderModalOpen, setOrderModalOpen] = useState(false);
  const {
    cartItems,
    clearCart,
    increaseQuantity,
    decreaseQuantity,
    removeFromCart,
  } = useCart();

  const toggleCart = () => {
    setCartOpen((prev) => !prev);
  };

  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
  };

  const calculateItemCount = () => {
    return cartItems.reduce((count, item) => count + item.quantity, 0);
  };

  const orderCart = () => {
    setOrderModalOpen(true);
  };

  const closeOrderModal = () => {
    setOrderModalOpen(false);
    setErrorMessage("");
  };

  const [deliveryType, setDeliveryType] = useState("carryout");
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    phone: "",
    address: "",
  });

  const [errorMessage, setErrorMessage] = useState(""); // Состояние для сообщений об ошибке

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    if (errorMessage) setErrorMessage(""); // Очищаем сообщение об ошибке при изменении данных
  };

  const handleDeliveryTypeChange = (e) => {
    setDeliveryType(e.target.value);
    if (e.target.value === "carryout") {
      setFormData((prev) => ({ ...prev, address: "" }));
    }
  };

  const isFormValid = () => {
    return (
      formData.firstName.trim() &&
      formData.lastName.trim() &&
      formData.phone.trim() &&
      (deliveryType === "carryout" || (deliveryType === "delivery" && formData.address.trim()))
    );
  };

  const handlePayment = () => {
    if (!isFormValid()) {
      setErrorMessage("Пожалуйста, заполните все обязательные поля."); // Выводим сообщение об ошибке
    } else {
      alert("Заказ успешно оформлен!");
      setOrderModalOpen(false);
      clearCart();
      setErrorMessage(""); // Очищаем сообщение после успешного заказа
    }
  };

  return (
    <>
      {/* Иконка для открытия корзины с отображением количества товаров */}
      <div className={styles.cartIcon} onClick={toggleCart}>
        🛒 <span className={styles.itemCount}>{calculateItemCount()}</span>
      </div>

      {isCartOpen && (
        <div className={styles.cartOverlay}>
          <div className={styles.cartContent}>
            <h2>Корзина</h2>
            <ul className={styles.cartList}>
              {cartItems.length > 0 ? (
                cartItems.map((item) => (
                  <li key={item.id} className={styles.cartItem}>
                    <div className={styles.productImage}>
                      <img src={item.image} alt={item.name} />
                    </div>
                    <div className={styles.cartDetails}>
                      <span>
                        {item.name} x {item.quantity}
                      </span>
                      <div className={styles.quantityControls}>
                        <button
                          className={styles.controlButton}
                          onClick={() => decreaseQuantity(item.id)}
                        >
                          -
                        </button>
                        <button
                          className={styles.controlButton}
                          onClick={() => increaseQuantity(item.id)}
                        >
                          +
                        </button>
                        <button
                          className={styles.controlButton}
                          onClick={() => removeFromCart(item.id)}
                        >
                          ✖
                        </button>
                      </div>
                    </div>
                    <span>{item.price * item.quantity} ₽</span>
                  </li>
                ))
              ) : (
                <p>Корзина пуста</p>
              )}
            </ul>
            <div className={styles.cartTotal}>
              <strong>Итого ({calculateItemCount()} шт.):</strong> {calculateTotal()} ₽
            </div>

            {/* Кнопка "Заказать" отображается только при наличии товаров в корзине */}
            {cartItems.length > 0 && (
              <button className={styles.orderButton} onClick={orderCart}>
                Заказать
              </button>
            )}

            <button className={styles.clearButton} onClick={clearCart}>
              Очистить корзину
            </button>
            <button className={styles.closeButton} onClick={toggleCart}>
              Закрыть корзину
            </button>
          </div>
        </div>
      )}

      {/* Модальное окно заказа */}
      {isOrderModalOpen && (
        <div className={styles.orderModalOverlay}>
          <div className={styles.orderModal}>
            <h2>Оформление заказа</h2>
            <form className={styles.orderForm}>
              <label>
                Имя:
                <input
                  type="text"
                  name="firstName"
                  value={formData.firstName}
                  onChange={handleChange}
                />
              </label>
              <label>
                Фамилия:
                <input
                  type="text"
                  name="lastName"
                  value={formData.lastName}
                  onChange={handleChange}
                />
              </label>
              <label>
                Телефон:
                <input
                  type="tel"
                  placeholder="+7 999 999 99 99"
                  name="phone"
                  value={formData.phone}
                  onChange={handleChange}
                />
              </label>
              <label>
                Тип доставки:
                <select
                  name="deliveryType"
                  value={deliveryType}
                  onChange={handleDeliveryTypeChange}
                >
                  <option value="carryout">С собой</option>
                  <option value="delivery">На дом</option>
                </select>
              </label>
              {deliveryType === "delivery" && (
                <label>
                  Адрес:
                  <input
                    type="text"
                    name="address"
                    value={formData.address}
                    onChange={handleChange}
                  />
                </label>
              )}

              {errorMessage && <p className={styles.errorMessage}>{errorMessage}</p>}

              <button
                className={styles.paymentButton}
                type="button"
                onClick={handlePayment}
              >
                Оплатить
              </button>
              <button
                className={styles.closeModalButton}
                type="button"
                onClick={closeOrderModal}
              >
                Закрыть
              </button>
            </form>
          </div>
        </div>
      )}
    </>
  );
}

export default Cart;
