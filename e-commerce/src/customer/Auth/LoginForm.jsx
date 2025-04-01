import React from 'react'
import { Grid, Grid2, TextField } from '@mui/material'
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {

    const navigate = useNavigate();
    
    const handleSubmit = (event) => {
        event.preventDefault();
    
        const data = new FormData(event.currentTarget);
    
        const userData = {
          phoneNumber: data.get("phoneNumber"),
          password: data.get("password"),
        };
    
        console.log(userData);
      };
    
    return (
        <div>

        

          <form onSubmit={handleSubmit}>
            <Grid container spacing={3}>
              

              

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
                    sx={{padding: "0.8 rem 0"}}>
                    Login
                </Button> 
              </Grid>

            </Grid>
          </form>

          <div className="flex justify-center flex-col items-center">
                <div className="py-3 flex items-center">
                    <p>Don't already have an account ?</p>
                    <Button onClick={() => navigate("/register")} className="ml-5" size="small">
                        Register
                    </Button>
                </div>
            </div>
        </div>
      );
}


export default LoginForm