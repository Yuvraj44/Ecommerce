import React from 'react';
import "./ProductCard.css";
import { useNavigate } from "react-router-dom";

const ProductCard = ({ product }) => {
    const { title, brand, image, selling_price, price, color, disscount } = product;

    const navigate = useNavigate();


    const handleNavigate = () => {
        navigate(`/product/5`)
    }



    return (
        <div onClick={handleNavigate} className=' productCard w-[15rem] border m-3 transition-all cursor-pointer rounded-xl'>



            <div className='h-[20rem] rounded-xl'>
                <img className='h-full w-full object-cover object-left-top rounded-xl' src={image} alt="" />
            </div>


            <div className='textPart bg-white p-3 '>
                <div>
                    <p className='font-bold opacity-60'>{brand}</p>
                    <p className=''>{title}</p>
                </div>

                <div className='flex space-x-2 items-center'>
                    <p className='font-semibold'>{selling_price}</p>
                    <p className='opacity-50 line-through'>{price}</p>
                    <p className='text-green-600 font-semibold'>{disscount}</p>
                </div>

            </div>
        </div>
    );
};

export default ProductCard;