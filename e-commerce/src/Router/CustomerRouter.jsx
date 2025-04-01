import React from "react";
import { Route, Routes, useLocation } from "react-router-dom";
import Navigation from "../customer/componant/Header/navigation";
import CheckoutPage from "../customer/Pages/CheckoutPage";
import CartPage from "../customer/Pages/CartPage";
import HomePage from "../customer/Pages/HomePage";
import OrderDetailsPage from "../customer/Pages/OrderDetailsPage";
import OrdersPage from "../customer/Pages/OrdersPage";
import ProductDetailPage from "../customer/Pages/ProductDetailPage";
import ProductPage from "../customer/Pages/ProductPage";
import Footer from "../customer/componant/Footer/Footer";

const CustomerRoutes = () => {
    const location = useLocation();

    const showNavigation = location.pathname !== "*";

    return (
        <div>

            <Navigation/>

            <Routes>
                

                <Route path="/" element={<HomePage/>}></Route>
                <Route path="/login" element={<HomePage/>}></Route>
                <Route path="/register" element={<HomePage/>}></Route>
                <Route path="/home" element={<HomePage/>}></Route>
                <Route path="/:lavelOne/:lavelTwo/:lavelThree" element={<ProductPage/>}></Route>
                <Route path="/product/:productId" element={<ProductDetailPage />}></Route>
                <Route path="/cart" element={<CartPage/>}></Route>
                <Route path="/account/order" element={<OrdersPage/>}></Route>
                <Route path="/account/order/:orderId" element={<OrderDetailsPage/>}></Route>
                <Route path="checkout" element={<CheckoutPage/>}></Route>
            </Routes>

            <Footer />
        

        </div >
    );
};

export default CustomerRoutes;