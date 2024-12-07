import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Logo from "./Logo/Logo";
import ProductModule from './Products/ProductModule';
import { Titles, specialOffers, dishes, desserts, drinks } from './Products/productData';

function App() {
  return (<>
  <Header/>
  <Logo/>
  <ProductModule
    title={Titles.specialOffers}
    products={specialOffers}
    titleColor="red"
  />
    <ProductModule
    title={Titles.dishes}
    products={dishes}
    titleColor="black"
  />
  <ProductModule
    title={Titles.desserts}
    products={desserts}
    titleColor="black"
  />
  <ProductModule
    title={Titles.drinks}
    products={drinks}
    titleColor="black"
  />
  <Footer/>
  </>)
}

export default App
