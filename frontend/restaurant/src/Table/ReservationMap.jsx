import React, { useState } from "react";
import styles from "./ReservationMap.module.css";
import { Titles } from "../Header/headerData";

const ReservationMap = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    phone: "",
    email: "",
    tableType: "common", // changed from 'regular' to 'common'
    dateTime: "", // поле для времени
  });
  const [errorMessage, setErrorMessage] = useState("");

  const handleReserveClick = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setErrorMessage("");
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
    if (errorMessage) setErrorMessage("");
  };

  const displayTablePrice = () => {
    if (formData.tableType === "vip") {
      return 1000;
    } else if (formData.tableType === "table_with_sanych") {
      return 100000;
    }
    return 500;
  };

  const sendReservationRequest = async () => {
    const fullName = `${formData.firstName.trim()} ${formData.lastName.trim()}`;
  
    // Преобразование времени из ISO 8601 в "DD.MM.YYYY HH:mm"
    const dateTime = new Date(formData.dateTime);
    const formattedDateTime = dateTime
      .toLocaleString("ru-RU", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      })
      .replace(",", ""); // Убираем запятую между датой и временем
  
    const requestData = {
      name: fullName,
      phoneNUmber: formData.phone.trim(),
      email: formData.email.trim() || null, // email может быть необязательным
      tableType: formData.tableType.trim(),
      dateTime: formattedDateTime,
    };
  
    try {
      const response = await fetch("http://178.236.244.137/api/reservations", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
      });
  
      if (!response.ok) {
        throw new Error("Ошибка при бронировании стола.");
      }
  
      alert("Бронирование успешно оформлено!");
      setIsModalOpen(false);
      setErrorMessage("");
    } catch (error) {
      setErrorMessage("Не удалось забронировать стол. Попробуйте ещё раз.");
    }
  };
  

  const handleReserveTable = () => {
    if (!formData.firstName || !formData.lastName || !formData.phone || !formData.dateTime || !formData.email) {
      setErrorMessage("Пожалуйста, заполните все обязательные поля.");
      return;
    }

    sendReservationRequest();
  };

  return (
    <div className={styles.container}>
      <h1 className={styles.title} >
        {Titles.reservations}
      </h1>
      <div className={styles.map}>
        <img
          src="./src/assets/table.jpg"
          alt="Restaurant Map"
          className={styles.mapImage}
        />
        <button onClick={handleReserveClick} className={styles.reserveButton}>
          Забронировать стол
        </button>
      </div>

      {isModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h1>Бронирование стола</h1>
            <form className={styles.form}>
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
                  type="email"
                  placeholder="email@mail.com"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                />
              </label>
              <label>
                Тип стола:
                <select
                  name="tableType"
                  value={formData.tableType}
                  onChange={handleChange}
                >
                  <option value="common">Обычный стол</option> {/* changed to "common" */}
                  <option value="vip">VIP стол</option>
                  <option value="table_with_sanych">Стол с Санычем</option> {/* added new option */}
                </select>
              </label>
              <label>
                Дата и время*:
                <input
                  type="datetime-local"
                  name="dateTime"
                  value={formData.dateTime}
                  onChange={handleChange}
                />
              </label>
              <div className={styles.tablePrice}>
                <strong>Цена:</strong> {displayTablePrice()} ₽
              </div>
              {errorMessage && <p className={styles.errorMessage}>{errorMessage}</p>}
              <button
                type="button"
                className={styles.modalReserveButton}
                onClick={handleReserveTable}
              >
                Забронировать
              </button>
              <button
                type="button"
                className={styles.closeButton}
                onClick={handleCloseModal}
              >
                Закрыть
              </button>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default ReservationMap;
