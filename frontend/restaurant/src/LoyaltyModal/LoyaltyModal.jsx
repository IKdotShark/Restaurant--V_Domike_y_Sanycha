import React, { useState } from 'react';
import styles from './LoyaltyModal.module.css'; // Подключение стилей

const LoyaltyModal = ({ isOpen, onClose }) => {
  const [phoneNumber, setPhoneNumber] = useState('');

  const handleInputChange = (e) => {
    setPhoneNumber(e.target.value);
  };

  const handleSubmit = async () => {
    if (phoneNumber.trim()) {
      try {
        const response = await fetch('http://localhost:8080/api/loyalty_program', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ phoneNumber }),
        });

        if (response.ok) {
          alert('Карта успешно создана!');
          setPhoneNumber('');
          onClose();
        } else {
          const errorData = await response.json();
          alert(`Ошибка: ${errorData.message || 'Не удалось создать карту.'}`);
        }
      } catch (error) {
        alert('Не удалось создать карту. Номер телефона не зарегестирован в базе данных или к нему уже привязана карта.');
        console.error(error);
      }
    } else {
      alert('Пожалуйста, введите номер телефона.');
    }
  };

  if (!isOpen) return null;

  return (
    <div className={styles.modal} onClick={onClose}>
      <div className={styles.modalContent} onClick={(e) => e.stopPropagation()}>
        <h2 className={styles.title}>Программа лояльности</h2>
        <p className={styles.descritption}>Введите номер телефона, чтобы создать вашу карту лояльности.</p>
        <div className={styles.form}>
          <input
            type="text"
            placeholder="Ваш номер телефона"
            value={phoneNumber}
            onChange={handleInputChange}
            className={styles.input}
          />
          <button className={styles.modalCreateButton} onClick={handleSubmit}>
            Создать карту
          </button>
          <button className={styles.closeButton} onClick={onClose}>
          Закрыть
        </button>
        </div>
      </div>
    </div>
  );
};

export default LoyaltyModal;
