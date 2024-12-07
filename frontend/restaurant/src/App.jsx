import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Logo from "./Logo/Logo";
import ProductModule from './Products/ProductModule';

import { Titles, ProductNames, ProductPrices } from './Products/productData';

const specialOffers = [
  { id: 1, name: 'Несквик с пивом', price: 349, image: './src/assets/products/nesquick.png' },
  { id: 2, name: 'Торт GlaDOS', price: 333, image: './src/assets/products/cake.png' },
  { id: 3, name: 'Эстус', price: 999, image: './src/assets/products/estus.png' },
  { id: 4, name: 'Бледина', price: 199, image: './src/assets/products/bledina.png' },
  { id: 5, name: 'Ещё Несквик', price: 349, image: './src/assets/products/nesquick.png' },
  { id: 6, name: 'Ещё Несквик', price: 349, image: './src/assets/products/nesquick.png' },
  { id: 6, name: 'Ещё Несквик', price: 349, image: './src/assets/products/nesquick.png' },
];

const dishes = [
  { id: 7, name: 'Мороженое', price: 200, image: './src/assets/products/icecream.png' },
  { id: 8, name: 'Пирожное', price: 150, image: './src/assets/products/cake.png' },
];

const desserts = [
  { id: 7, name: 'Мороженое', price: 200, image: './src/assets/products/icecream.png' },
  { id: 8, name: 'Пирожное', price: 150, image: './src/assets/products/cake.png' },
];

const drinks = [
  { id: 7, name: 'Мороженое', price: 200, image: './src/assets/products/icecream.png' },
  { id: 8, name: 'Пирожное', price: 150, image: './src/assets/products/cake.png' },
];

function App() {
  return (<>
  <Header/>
  <Logo/>
  <ProductModule title={Titles.special} products={specialOffers} titleColor="red"/>
  <ProductModule title={Titles.dishes} products={dishes} titleColor="black"/>
  <ProductModule title={Titles.desserts} products={desserts} titleColor="black"/>
  <ProductModule title={Titles.drinks} products={drinks} titleColor="black"/>
  <Footer/>
  </>)
}

export default App
