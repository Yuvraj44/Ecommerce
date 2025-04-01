import * as React from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { useLocation, useNavigate } from "react-router-dom";
import DeliveryAddressForm from './DeliveryAddressForm';
import OrderSummary from '../Order/OrderSummary';



const steps = ["Login", "Delivery Adress", "Order Summary", "Payment"];

export default function Checkout() {
  const [activeStep, setActiveStep] = React.useState(0);
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const step = queryParams.get('step');
  console.log("step", step)

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };


  return (
    <Box className="px-5 lg:px-32 " sx={{ width: "100%" }}>
      <Stepper activeStep={step}>
        {steps.map((label, index) => {
          const stepProps = {};
          const labelProps = {};

          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps}>{label}</StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep === steps.length ? (
        <React.Fragment>
          <Typography sx={{ mt: 2, mb: 1 }}>
            All steps completed - you&apos;re finished
          </Typography>

        </React.Fragment>
      ) : (
        <React.Fragment>

          {/* <Typography sx={{ my: 6 }}>Title</Typography> */}

          <div className="my-5">
            {step == 2 ? <DeliveryAddressForm handleNext={handleNext} /> : <OrderSummary />}
          </div>



        </React.Fragment>
      )}
    </Box>
  );
}