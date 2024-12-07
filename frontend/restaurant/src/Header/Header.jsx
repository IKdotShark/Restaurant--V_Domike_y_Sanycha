import React, { useState } from 'react';
import styles from "./header.module.css";
import { Images, Texts } from './headerData';

function Header() {
  const [isMenuOpen, setMenuOpen] = useState(false);

  // Функция переключения меню
  const toggleMenu = () => {
    setMenuOpen((prev) => !prev);
  };

  // Переход по пути и закрытие меню
  const handleNavigation = (path) => {
    window.location.href = path;
    setMenuOpen(false);
  };

  return (
    <>
      {/* Основной хедер */}
      <header className={styles.header}>
        <img
          className={styles.header_logo}
          src={Images.logo}
          alt="Logo"
        />
        <p className={styles.header_text}>{Texts.address}</p>
        <p className={styles.header_text}>{Texts.phone}</p>
        <p className={styles.header_text}>{Texts.delivery}</p>

        {/* Кнопка-бургер */}
        <img
          onClick={toggleMenu}
          className={styles.header_burger}
          src={Images.burger}
          alt="Burger"
        />
      </header>

      {/* Оверлэй и меню */}
      {isMenuOpen && (
        <div className={styles.overlay} onClick={toggleMenu}>
          <div
            className={styles.menu}
            onClick={(e) => e.stopPropagation()} // Предотвращаем закрытие меню при клике по нему
          >
            <ul className={styles.menuList}>
              <li onClick={() => handleNavigation('')}>Специальные предложения</li>
              <li onClick={() => handleNavigation('')}>Блюда</li>
              <li onClick={() => handleNavigation('')}>Десерты</li>
              <li onClick={() => handleNavigation('')}>Напитки</li>
            </ul>
          </div>
        </div>
      )}
    </>
  );
}

export default Header;
