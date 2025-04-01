import { Route, Routes } from 'react-router-dom';
import CustomerRouter from './Router/CustomerRouter.jsx';
import HomePage from './customer/Pages/HomePage.jsx';
import ProductPage from './customer/Pages/ProductPage.jsx';
function App() {
  return (

    <div className="App">

      <Routes>
        <Route path="/*" element={<CustomerRouter/>} />
      </Routes>

      {/* <HomePage /> */}


      {/* <ProductPage /> */}


      {/* <ProductDetailPage /> */}


      {/* <CartPage /> */}



      {/* <CheckoutPage /> */}

      {/* <OrdersPage/> */}

      {/* <OrderDetailsPage/> */}
    </div>
  );
}

export default App;
