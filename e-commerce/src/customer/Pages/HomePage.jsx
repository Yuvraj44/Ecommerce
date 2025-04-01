import React from 'react';
import MainCarousal from '../componant/HomeCarousal/HomeCarousal';

import HomeSecCourosal from '../componant/HomeSecCourosal/HomeSecCourosal';
import { sareePage1 } from "../../ecommerce-products-data-master/Saree/page1";
import { dressPage1 } from "../../ecommerce-products-data-master/dress/page1";
import { gounsPage1 } from "../../ecommerce-products-data-master/Gouns/gouns";
import { kurta_data } from "../../ecommerce-products-data-master/Kurta/kurta";
import { mensShoesPage1 } from "../../ecommerce-products-data-master/shoes";
import { mens_kurta } from "../../ecommerce-products-data-master/Men/men_kurta";


const HomePage = () => {
    return (
        <div>

            <MainCarousal />
            <div className='space-y-10 py-20'>
                <HomeSecCourosal data={mens_kurta} section={"Men's Kurta"} />
                <HomeSecCourosal data={mensShoesPage1} section={"Men's Shoes"} />
                <HomeSecCourosal data={sareePage1} section={"Saree"} />
                <HomeSecCourosal data={dressPage1} section={"Dress"} />
                <HomeSecCourosal data={gounsPage1} section={"Women's Gouns"} />
                <HomeSecCourosal data={kurta_data} section={"Women's Kurtas"} />
            </div>
            

        </div>

    )
}

export default HomePage;