import styles from "./header.module.css"

import { Images, Texts } from './headerData';

function Header() {
  const handleClick = () => {
    window.location.href = '/';
  };

  return (
    <header className={styles.header}>
      <img
        onClick={handleClick}
        className={styles.header_logo}
        src={Images.logo}
        alt="Logo"
      />
      <p className={styles.header_text}>{Texts.address}</p>
      <p className={styles.header_text}>{Texts.phone}</p>
      <p className={styles.header_text}>{Texts.delivery}</p>
      <img
        onClick={handleClick}
        className={styles.header_burger}
        src={Images.burger}
        alt="Burger"
      />
    </header>
  );
}

export default Header;