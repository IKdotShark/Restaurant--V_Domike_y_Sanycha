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
    email: "",
    address: "",
  });

  const [errorMessage, setErrorMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    if (errorMessage) setErrorMessage("");
  };

  const handleDeliveryTypeChange = (e) => {
    setDeliveryType(e.target.value);
    if (e.target.value === "carryout") {
      setFormData((prev) => ({ ...prev, address: "" }));
    }
  };

  const isFormValid = () => {
    const fullName = `${formData.firstName.trim()} ${formData.lastName.trim()}`;
    return (
      fullName.trim() && // Проверка полного имени
      formData.phone.trim() &&
      formData.email.trim() &&
      (deliveryType === "carryout" || (deliveryType === "delivery" && formData.address.trim()))
    );
  };

  const sendOrderRequest = async () => {
    const expandItems = (category) =>
      cartItems
        .filter((item) => item.category === category)
        .flatMap((item) => Array(item.quantity).fill(item.id));
  
    const fullName = `${formData.firstName.trim()} ${formData.lastName.trim()}`;
    const formattedPhone = formData.phone.replace(/\+/g, ""); // Убираем плюс из номера телефона
  
    const requestData = {
      client: {
        name: fullName,
        contact: formattedPhone, // Используем обработанный номер телефона
      },
      status: "ACCEPTED",
      dishesIds: expandItems("Dish"),
      drinksIds: expandItems("Drink"),
      desertsIds: expandItems("Desert"),
    };
  
    try {
      // Проверка доступности товаров
      for (const item of cartItems) {
        const inventoryResponse = await fetch(
          `http://localhost:8080/api/inventory/search?productName=${encodeURIComponent(item.name)}`
        );
        if (!inventoryResponse.ok) {
          throw new Error("Ошибка при проверке наличия товара.");
        }
  
        const inventoryData = await inventoryResponse.json();
        const productData = inventoryData.find(
          (product) => product.productName === item.name
        );
  
        if (!productData || productData.quantity < item.quantity) {
          setErrorMessage(
            `Товара "${item.name}" недостаточно на складе. Доступно: ${productData?.quantity || 0}.`
          );
          return;
        }
  
        // Обновление количества товара
        const updateResponse = await fetch(
          `http://localhost:8080/api/inventory/update/${productData.id}`,
          {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              productName: item.name,
              quantity: productData.quantity - item.quantity,
              supplier: productData.supplier,
            }),
          }
        );
  
        if (!updateResponse.ok) {
          throw new Error(
            `Ошибка при обновлении количества товара "${item.name}".`
          );
        }
      }
  
      // Отправка заказа
      const orderResponse = await fetch("http://localhost:8080/api/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      });
  
      if (!orderResponse.ok) {
        throw new Error("Ошибка при оформлении заказа.");
      }
  
      alert("Заказ успешно оформлен!");
      clearCart();
      setOrderModalOpen(false);
      setErrorMessage("");
    } catch (error) {
      setErrorMessage(error.message);
    }
  };
  
  
  const handlePayment = () => {
    if (!isFormValid()) {
      setErrorMessage("Пожалуйста, заполните все обязательные поля.");
    } else {
      sendOrderRequest();
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
            <h2 className={styles.cartTitle}>Корзина</h2>
            <ul className={styles.cartList}>
              {cartItems.length > 0 ? (
                cartItems.map((item) => (
                  <li key={item.id} className={styles.cartItem}>
                    <div className={styles.productImage}>
                      <img src={item.src} alt={item.name} />
                    </div>
                    <div className={styles.itemPrice}>
                      <div className={styles.cartDetails}>
                        <span className={styles.cartItemTitle}>
                          {item.name} x {item.quantity}
                        </span>
                        <div className={styles.quantityControls}>
                          <button
                            className={styles.controlButton}
                            onClick={() => decreaseQuantity(item.id, item.category)}
                          >
                            -
                          </button>
                          <button
                            className={styles.controlButton}
                            onClick={() => increaseQuantity(item.id, item.category)}
                          >
                            +
                          </button>
                          <button
                            className={styles.controlButton}
                            onClick={() => removeFromCart(item.id, item.category)}
                          >
                            ✖
                          </button>
                        </div>
                      </div>
                      <span>{item.price * item.quantity} ₽</span>
                    </div>
                  </li>
                ))
              ) : (
                <p>Корзина пуста</p>
              )}
            </ul>
            <div className={styles.cartTotal}>
              <strong >Итого ({calculateItemCount()} шт.):</strong> {calculateTotal()} ₽
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
            <div className={styles.orderTitle}>
              <h1>Оформление заказа</h1>
            </div>
            <div className={styles.orderItems}>
              <h2>Ваш заказ:</h2>
              <ul className={styles.orderItemList}>
                {cartItems.map((item) => (
                  <li key={item.id} className={styles.orderItem}>
                    <span>
                      {item.name} x {item.quantity}
                    </span>
                    <span> - {item.price * item.quantity} ₽</span>
                  </li>
                ))}
              </ul>
              <div className={styles.orderTotal}>
                <strong>Итого:</strong> {calculateTotal()} ₽
              </div>
            </div>
            <form className={styles.orderForm}>
              <div className={styles.orderFormText}>
                <label>
                  Имя*:
                  <input
                    type="text"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                  />
                </label>
                <label>
                  Фамилия*:
                  <input
                    type="text"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                  />
                </label>
                <label>
                  Телефон*:
                  <input
                    type="tel"
                    placeholder="+7 999 999 99 99"
                    name="phone"
                    value={formData.phone}
                    onChange={handleChange}
                  />
                </label>
                <label>
                  Email*:
                  <input
                    type="text"
                    placeholder="email@mail.com"
                    name="email"
                    value={formData.email}
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
                    Адрес*:
                    <input
                      type="text"
                      name="address"
                      value={formData.address}
                      onChange={handleChange}
                    />
                  </label>
                )}
                {errorMessage && <p className={styles.errorMessage}>{errorMessage}</p>}
              </div>
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
