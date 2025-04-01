import React from 'react'
import AliceCarousel from 'react-alice-carousel';
import HomeSecCard from './HomeSecCard/HomeSecCard';
import { Button } from "@mui/material"
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import { useState } from "react";

const HomeSecCourosal = ({section, data}) => {
  const [activeIndex, setActiveIndex] = useState(0);
  const carouselRef = React.useRef(null); // Ref for carousel

  const slidePrev = () => {
    setActiveIndex(activeIndex - 1);
    carouselRef.current.slidePrev(); // Move to previous slide
  };
  
  const slideNext = () => {
    setActiveIndex(activeIndex + 1);
    carouselRef.current.slideNext(); // Move to next slide
  };

  const syncActiveIndex = ({ item }) => setActiveIndex(item);

  const responsive = { 0: { items: 1 }, 720: { items: 4 }, 1024: { items: 4 } };
  const items = data.slice(0, 10).map(item => <HomeSecCard product={item} />);


  return (
    <div className="relative px-4 sm:px-6 lg:px-8">
      <h2 className="text-2xl font-extrabold text-gray-900 py-5">{section}</h2>
      <div className="relative p-5">
        <AliceCarousel
          ref={carouselRef}
          items={items}
          responsive={responsive}
          disableButtonsControls
          disableDotsControls
          activeIndex={activeIndex}
          onSlideChanged={syncActiveIndex}
        />

        {activeIndex !== items.length - 5 && (
          <Button
            onClick={slideNext}
            variant="contained"
            className="z-50 bg-white"
            sx={{
              position: "absolute",
              top: "8rem",
              right: "0rem",
              transform: "translateX(50%) rotate(90deg)",
              bgcolor: 'white'
            }}
            color="white"
            aria-label="next"
          >
            <ArrowForwardIosIcon className="" sx={{ transform: "rotate(-90deg)" }} />
          </Button>
        )}

        {activeIndex !== 0 && (
          <Button
            onClick={slidePrev}
            variant="contained"
            className="z-50 bg-white"
            color="white"
            sx={{
              position: "absolute",
              top: "8rem",
              left: "0rem",
              transform: "translateX(-50%)  rotate(90deg)"
            }}
            aria-label="prev"
          >
            <ArrowForwardIosIcon className="" sx={{ transform: "rotate(90deg)" }} />
          </Button>
        )}
      </div>
    </div>
  );
};

export default HomeSecCourosal;
