import React, { createContext, useState, useContext } from "react";

const CartContext = createContext();

export const useCart = () => useContext(CartContext);

export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);

  const addToCart = (item) => {
    setCartItems((prevItems) => {
      // Ищем товар по id и category
      const index = prevItems.findIndex(
        (cartItem) => cartItem.id === item.id && cartItem.category === item.category
      );
  
      if (index !== -1) {
        const updatedItems = [...prevItems];
        updatedItems[index] = {
          ...updatedItems[index],
          quantity: updatedItems[index].quantity + 1,
        };
        return updatedItems;
      }
  
      return [...prevItems, { ...item, quantity: 1 }];
    });
  };
  
  const removeFromCart = (id, category) => {
    setCartItems((prevItems) =>
      prevItems.filter((item) => item.id !== id || item.category !== category)
    );
  };
  
  const increaseQuantity = (id, category) => {
    setCartItems((prevItems) =>
      prevItems.map((item) =>
        item.id === id && item.category === category
          ? { ...item, quantity: item.quantity + 1 }
          : item
      )
    );
  };
  
  const decreaseQuantity = (id, category) => {
    setCartItems((prevItems) =>
      prevItems.map((item) =>
        item.id === id && item.category === category && item.quantity > 1
          ? { ...item, quantity: item.quantity - 1 }
          : item
      )
    );
  };
  

  const clearCart = () => {
    setCartItems([]);
  };

  return (
    <CartContext.Provider
      value={{
        cartItems,
        addToCart,
        removeFromCart,
        increaseQuantity,
        decreaseQuantity,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};
