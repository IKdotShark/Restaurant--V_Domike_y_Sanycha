// src/Header/Header.jsx
import React, { useState } from 'react';
import styles from "./header.module.css";
import { Images, Texts } from './headerData';
import { Titles } from './headerData';
import LoyaltyModal from "../LoyaltyModal/LoyaltyModal";

function Header({ scrollToSection, refs }) {
  const [isMenuOpen, setMenuOpen] = useState(false);
  const [isLoyaltyModalOpen, setLoyaltyModalOpen] = useState(false);

  const toggleMenu = () => {
    setMenuOpen((prev) => !prev);
  };

  const openLoyaltyModal = () => {
    setLoyaltyModalOpen(true);
    toggleMenu(); // Закрываем меню после клика
  };

  const closeLoyaltyModal = () => {
    setLoyaltyModalOpen(false);
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
        <img
          onClick={toggleMenu}
          className={styles.header_burger}
          src={isMenuOpen ? Images.cross : Images.burger}
          alt={isMenuOpen ? "Close Menu" : "Open Menu"}
        />
      </header>

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
              <li onClick={() => { toggleMenu(); scrollToSection(refs.reservationRef); }}>
                {Titles.reservations}
              </li>
              <li onClick={openLoyaltyModal} className={styles.loyaltyProgramm}>
                {Titles.loyaltyProgramm}
              </li>
            </ul>
          </div>
        </div>
      )}

      <LoyaltyModal isOpen={isLoyaltyModalOpen} onClose={closeLoyaltyModal} />
    </>
  );
}

export default Header;