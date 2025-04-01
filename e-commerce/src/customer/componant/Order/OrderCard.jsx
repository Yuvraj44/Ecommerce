import { Box, Grid, Typography } from "@mui/material";
import FiberManualRecordIcon from "@mui/icons-material/FiberManualRecord";
import AdjustIcon from "@mui/icons-material/Adjust";
import React from "react";
import { useNavigate } from "react-router-dom";
import StarIcon from "@mui/icons-material/Star";
import { kurta_data } from "../../../ecommerce-products-data-master/Kurta/kurta";


const OrderCard = () => {

    const navigate = useNavigate();


    const handleNavigate = () => {
        navigate(`/account/order/5`)
    }


    const item = {
        "image": "https://rukminim1.flixcart.com/image/612/612/xif0q/ethnic-set/b/4/m/m-na-kurta-set-010-rama-woxen-original-imagzkgg8mnyxmcg.jpeg?q=70",
        "brand": "WOXEN",
        "title": "Women Kurta and Pant Set Art Silk",
        "color": "",
        "selling_price": "₹699",
        "price": "₹2,899",
        "disscount": "75% off",
        "size": ""
    }

    return (
        <Box 
        onClick={handleNavigate}
        className="p-5 shadow-lg hover:shadow-2xl border ">
            <Grid spacing={2} container sx={{ justifyContent: "space-between" }}>
                <Grid item xs={6}>
                    <div
                        className="flex cursor-pointer"
                    >
                        <img
                            className="w-[5rem] h-[5rem] object-cover object-top"
                            src={item.image}
                            alt=""
                        />
                        <div className="ml-5">
                            <p className="mb-2">{item.title}</p>
                            <p className="opacity-50 text-xs font-semibold space-x-5">
                                <span>Size: {item.size} L</span>
                            </p>
                        </div>
                    </div>
                </Grid>

                <Grid item xs={2}>
                    <p>{item.price}</p>
                </Grid>
                <Grid item xs={4}>
                    <p>
                        <AdjustIcon
                            sx={{ width: "15px", height: "15px" }}
                            className="text-green-600 p-0 mr-2 text-sm"
                        />
                        <span>Expected Delivery On Mar 03</span>

                    </p>

                    <p className="text-xs">Your Item Has Been Delivered</p>

                    <div

                        className="flex items-center text-blue-600 cursor-pointer"
                    >
                        <StarIcon sx={{ fontSize: "2rem" }} className="px-2 text-5xl" />
                        <span>Rate & Review Product</span>
                    </div>

                </Grid>
            </Grid>
        </Box>
    );
};

export default OrderCard;