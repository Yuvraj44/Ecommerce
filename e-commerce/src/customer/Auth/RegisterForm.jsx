import { Grid, TextField } from '@mui/material'
import React from 'react'
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from "react-redux";
import { getUser, register } from "../../Redux/Auth/Action";
import { Fragment, useEffect, useState } from "react";

const RegisterForm = () => {

    const { auth } = useSelector((store) => store);

    const jwt = localStorage.getItem("jwt")?.trim();

    console.log("Stored token:", jwt);

    // useEffect(()=>{
    //     if(jwt){
    //       dispatch(getUser(jwt))
    //     }
      
    //   },[jwt, auth.jwt])

    const navigate = useNavigate();

    const dispatch=useDispatch();

    const handleSubmit = (event) => {
        event.preventDefault();

        const data = new FormData(event.currentTarget);

        const userData = {
            firstName: data.get("firstName"),
            lastName: data.get("lastName"),
            phoneNumber: data.get("phoneNumber"),
            password: data.get("password"),
        };
        dispatch(register(userData))
        console.log(userData);
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            id="firstName"
                            name="firstName"
                            label="First Name"
                            fullWidth
                            autoComplete="given-name"
                        />
                    </Grid>

                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            id="lastName"
                            name="lastName"
                            label="Last Name"
                            fullWidth
                            autoComplete="given-name"
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            required
                            id="phoneNumber"
                            name="phoneNumber"
                            label="Phone Number"
                            fullWidth
                            autoComplete="phoneNumber"
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <TextField
                            required
                            id="password"
                            name="password"
                            label="Password"
                            fullWidth
                            autoComplete="password"
                        />
                    </Grid>

                    <Grid item xs={12}>
                        <Button classname='bg to-blue-400 w-full'
                            fullWidth
                            type='submit'
                            variant='contained'
                            size='large'
                            sx={{ padding: "0.8 rem 0" }}>
                            Register
                        </Button>
                    </Grid>

                </Grid>
            </form>


            <div className="flex justify-center flex-col items-center">
                <div className="py-3 flex items-center">
                    <p>If you have already an account ?</p>
                    <Button onClick={() => navigate("/login")} className="ml-5" size="small">
                        Login
                    </Button>
                </div>
            </div>
        </div>
    );
}

export default RegisterForm