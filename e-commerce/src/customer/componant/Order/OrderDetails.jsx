import { Box, Button, Grid, Typography } from "@mui/material";
import React from "react";
import StarIcon from "@mui/icons-material/Star";
import { useNavigate, useParams } from "react-router-dom";
import AddressCard from "../Checkout/AddressCard";
import { deepPurple } from "@mui/material/colors";
import { useEffect } from "react";
import OrderTraker from "./OrderTracker";

const OrderDetails = () => {

    const item = {
        "imageUrl": "https://rukminim1.flixcart.com/image/612/612/xif0q/ethnic-set/b/4/m/m-na-kurta-set-010-rama-woxen-original-imagzkgg8mnyxmcg.jpeg?q=70",
        "brand": "WOXEN",
        "title": "Women Kurta and Pant Set Art Silk",
        "color": "",
        "selling_price": "₹699",
        "price": "₹2,899",
        "disscount": "75% off",
        "size": ""
    }


    return (
        <>
            <div className=" px-2 lg:px-36 space-y-7 ">


                <Grid container className="p-3 shadow-lg">
                    <Grid xs={12}>
                        <p className="font-bold text-lg py-2">Delivery Address</p>
                    </Grid>
                    <Grid item xs={6}>
                        <AddressCard />
                    </Grid>
                </Grid>






                <Box className="p-5 shadow-lg border rounded-md">
                    <Grid
                        container
                        sx={{ justifyContent: "space-between", alignItems: "center" }}
                    >
                        <Grid item xs={9}>
                            <OrderTraker
                                activeStep={3}
                            />
                        </Grid>
                        <Grid item>
                            <Button sx={{ color: "" }} color="error" variant="text" >
                                RETURN
                            </Button>

                            <Button sx={{ color: deepPurple[500] }} variant="text">
                                cancel order
                            </Button>
                        </Grid>
                    </Grid>
                </Box>



                <Grid container className="space-y-5">
                    <Grid
                        container
                        item
                        className="shadow-xl rounded-md p-5 border"
                        sx={{ alignItems: "center", justifyContent: "space-between" }}
                    >
                        <Grid item xs={6}>
                            {" "}
                            <div className="flex  items-center ">
                                <img
                                    className="w-[5rem] h-[5rem] object-cover object-top"
                                    src={item.imageUrl}
                                    alt=""
                                />
                                <div className="ml-5 space-y-2">
                                    <p className="">{item.title}</p>
                                    <p className="opacity-50 text-xs font-semibold space-x-5">
                                        <span>Color: pink</span> <span>Size: {item.size}</span>
                                    </p>
                                    <p>Seller: {item.brand}</p>
                                    <p>₹{item.price} </p>
                                </div>
                            </div>
                        </Grid>




                        <Grid item>
                            {
                                <Box
                                    sx={{ color: deepPurple[500] }}

                                    className="flex items-center cursor-pointer"
                                >
                                    <StarIcon
                                        sx={{ fontSize: "2rem" }}
                                        className="px-2 text-5xl"
                                    />
                                    <span>Rate & Review Product</span>
                                </Box>
                            }
                        </Grid>
                    </Grid>

                </Grid>


            </div>

        </>

    );
};

export default OrderDetails;