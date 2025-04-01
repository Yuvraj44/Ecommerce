import React from 'react'
import { IconButton, Button } from "@mui/material";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";


const CartItem = () => {

    const [item, setItem] = React.useState({
        "image": "https://rukminim1.flixcart.com/image/612/612/xif0q/ethnic-set/b/4/m/m-na-kurta-set-010-rama-woxen-original-imagzkgg8mnyxmcg.jpeg?q=70",
        "brand": "WOXEN",
        "title": "Women Kurta and Pant Set Art Silk",
        "color": "",
        "selling_price": "₹699",
        "price": "₹2,899",
        "disscount": "75% off",
        "size": ""
    });

    return (


        <div className="p-5 shadow-lg border rounded-md">

            <div className="flex items-center">



                <div className="w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[9rem] ">
                    <img
                        className="w-full h-full object-cover object-top"
                        src={item.image}
                        alt=""
                    />
                </div>


                <div className="ml-5 space-y-1">

                    <p className="font-semibold">{item.title}</p>

                    <p className="opacity-70">Size: {item.size},White</p>

                    <p className="opacity-70 mt-2">Seller: {item.brand}</p>


                    <div className="flex space-x-2 items-center pt-3">
                        <p className="opacity-50 line-through">{item.price}</p>
                        <p className="font-semibold text-lg">{item.selling_price}</p>
                        <p className="text-green-600 font-semibold">{item.disscount}</p>
                    </div>
                </div>


            </div>





            <div className="flex items-center space-x-2 ">
                <IconButton >
                    <RemoveCircleOutlineIcon />
                </IconButton>

                <span className="py-1 px-7 border rounded-sm">{item?.quantity}</span>
                <IconButton >
                    <AddCircleOutlineIcon />
                </IconButton>
            </div>
            <div className="flex text-sm lg:text-base mt-5 lg:mt-0">

                <Button >
                    Remove
                </Button>

            </div>


        </div>









    )
}

export default CartItem