// src/Header/Header.jsx
import React, { useState } from 'react';
import styles from "./header.module.css";
import { Images, Texts } from './headerData';
import { Titles } from '../Products/productData';

function Header({ scrollToSection, refs }) {
  const [isMenuOpen, setMenuOpen] = useState(false);

  const toggleMenu = () => {
    setMenuOpen((prev) => !prev);
  };

  const logoClick = () => {
    window.location.href = '/';
  };

  return (
    <>
      <header className={styles.header}>
        <img
          onClick={logoClick}
          className={styles.header_logo}
          src={Images.logo}
          alt="Logo"
        />
        <div className={styles.header_texts}>
          <p className={styles.header_text}>{Texts.address}</p>
          <p className={styles.header_text}>{Texts.phone}</p>
          <p className={styles.header_text}>{Texts.delivery}</p>
        </div>
        {/* Бургер-кнопка, изменяющаяся на крестик */}
        <img
          onClick={toggleMenu}
          className={styles.header_burger}
          src={isMenuOpen ? Images.cross : Images.burger}
          alt={isMenuOpen ? "Close Menu" : "Open Menu"}
        />
      </header>

      {/* Оверлей меню */}
      {isMenuOpen && (
        <div className={styles.overlay} onClick={toggleMenu}>
          <div
            className={styles.menu}
            onClick={(e) => e.stopPropagation()}
          >
            <ul className={styles.menuList}>
              <li onClick={() => { toggleMenu(); scrollToSection(refs.dishesRef); }}>
                {Titles.dishes}
              </li>
              <li onClick={() => { toggleMenu(); scrollToSection(refs.dessertsRef); }}>
                {Titles.desserts}
              </li>
              <li onClick={() => { toggleMenu(); scrollToSection(refs.drinksRef); }}>
                {Titles.drinks}
              </li>
            </ul>
          </div>
        </div>
      )}
    </>
  );
}

export default Header;
