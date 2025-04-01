'use client'



import { useState } from 'react'
import { StarIcon } from '@heroicons/react/20/solid'
import { Radio, RadioGroup } from '@headlessui/react'
import { Rating } from "@mui/material";
import { Grid, Box, LinearProgress } from '@mui/material';
import ProductReviewCard from './ProductReviewCard/ProductReviewCard';
import { Reviews } from '@mui/icons-material';
import { kurta_data } from "../../../ecommerce-products-data-master/Kurta/kurta"
import ProductCard from "../Product/ProductCard/ProductCard"
import { useNavigate } from "react-router-dom";











const product = {
    name: 'Basic Tee 6-Pack',
    price: '$192',
    href: '#',
    breadcrumbs: [
        { id: 1, name: 'Men', href: '#' },
        { id: 2, name: 'Clothing', href: '#' },
    ],
    images: [
        {
            src: 'https://m.media-amazon.com/images/I/41frwQMTwFL.jpg',
            alt: 'Plain white t-shirt on a hanger.',
        },
        {
            src: 'https://m.media-amazon.com/images/I/51hat7VWDUL.jpg',
            alt: 'Model wearing a black t-shirt outdoors.',
        },
        {
            src: 'https://m.media-amazon.com/images/I/518vMfsKcsL.jpg',
            alt: 'Stack of folded t-shirts in various colors.',
        },
        {
            src: 'https://rukminim2.flixcart.com/image/832/832/xif0q/t-shirt/r/i/n/xl-t852-mhg-eyebogler-original-imah4rmq2f5negac.jpeg?q=70&crop=false',
            alt: 'Model wearing a stylish gray t-shirt.',
        },
    
    
    ],
    colors: [
        { name: 'White', class: 'bg-white', selectedClass: 'ring-gray-400' },
        { name: 'Gray', class: 'bg-gray-200', selectedClass: 'ring-gray-400' },
        { name: 'Black', class: 'bg-gray-900', selectedClass: 'ring-gray-900' },
    ],
    sizes: [

        { name: 'S', inStock: true },
        { name: 'M', inStock: true },
        { name: 'L', inStock: false },
        { name: 'XL', inStock: true },

    ],
    description:
        'The Basic Tee 6-Pack allows you to fully express your vibrant personality with three grayscale options. Feeling adventurous? Put on a heather gray tee. Want to be a trendsetter? Try our exclusive colorway: "Black". Need to add an extra pop of color to your outfit? Our white tee has you covered.',
    highlights: [
        'Hand cut and sewn locally',
        'Dyed with our proprietary colors',
        'Pre-washed & pre-shrunk',
        'Ultra-soft 100% cotton',
    ],
    details:
        'The 6-Pack includes two black, two white, and two heather gray Basic Tees. Sign up for our subscription service and be the first to get new, exciting colors, like our upcoming "Charcoal Gray" limited release.',
}



















const reviews = { href: '#', average: 4, totalCount: 117 }

function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}
















export default function ProductDetail() {



    const [selectedColor, setSelectedColor] = useState(product.colors[0])
    const [selectedSize, setSelectedSize] = useState(product.sizes[2])

    const navigate = useNavigate();


    const handleNavigate = () => {
        navigate(`/cart`)
    }






    return (
        <div className="bg-white">
            <div className="pt-6">
                <nav aria-label="Breadcrumb">
                    <ol role="list" className="mx-auto flex max-w-2xl items-center space-x-2 px-4 sm:px-6 lg:max-w-7xl lg:px-8">
                        {product.breadcrumbs.map((breadcrumb) => (
                            <li key={breadcrumb.id}>
                                <div className="flex items-center">
                                    <a href={breadcrumb.href} className="mr-2 text-sm font-medium text-gray-900">
                                        {breadcrumb.name}
                                    </a>
                                    <svg
                                        fill="currentColor"
                                        width={16}
                                        height={20}
                                        viewBox="0 0 16 20"
                                        aria-hidden="true"
                                        className="h-5 w-4 text-gray-300"
                                    >
                                        <path d="M5.697 4.34L8.98 16.532h1.327L7.025 4.341H5.697z" />
                                    </svg>
                                </div>
                            </li>
                        ))}
                        <li className="text-sm">
                            <a href={product.href} aria-current="page" className="font-medium text-gray-500 hover:text-gray-600">
                                {product.name}
                            </a>
                        </li>
                    </ol>
                </nav>






                {/* product details */}
                <section className="grid grid-cols-1 gap-x-8 gap-y-10 lg:grid-cols-2 px-4 pt-10">


                    {/* Image gallery */}
                    <div className="flex flex-col items-center ">


                        <div className=" overflow-hidden rounded-lg max-w-[30rem] max-h-[35rem]">
                            <img
                                alt={product.images[0].alt}
                                src={product.images[0].src}
                                className="h-full w-full object-cover object-center"
                            />
                        </div>
                        <div className="flex flex-wrap space-x-5 justify-center">
                            {product.images.map((image) => (
                                <div
                                    // onClick={() => handleSetActiveImage(image)}
                                    className="aspect-h-2 aspect-w-3 overflow-hidden rounded-lg max-w-[5rem] max-h-[5rem] mt-4"
                                >
                                    <img
                                        src={image.src}
                                        alt={product.images[1].alt}
                                        className="h-full w-full object-cover object-center"
                                    />
                                </div>
                            ))}
                        </div>

                        <section className="pt-10">
                            <h1 className="py-5 text-xl font-bold">Similar Products</h1>
                            <div className="flex flex-wrap space-y-5">
                                {kurta_data.slice(0, 4).map((item) => (
                                    <ProductCard key={item.id} product={item} />
                                ))}
                            </div>
                        </section>


                    </div>




                    {/* Product info */}
                    <div className="lg:col-span-1 mx-auto max-w-2xl px-4 pb-16 sm:px-6  lg:max-w-7xl  lg:px-8 lg:pb-24">


                        {/* Product Name */}
                        <div className="lg:col-span-2">
                            <h1 className="text-lg lg:text-xl font-semibold tracking-tight text-gray-900  ">
                                Brand
                            </h1>
                            <h1 className="text-lg lg:text-xl tracking-tight text-gray-900 opacity-60 pt-1">
                                Title
                            </h1>
                        </div>


                        {/* Options */}
                        <div className="mt-4 lg:row-span-3 lg:mt-0">


                            <h2 className="sr-only">Product information</h2>
                            <div className="flex space-x-5 items-center text-lg lg:text-xl tracking-tight text-gray-900 mt-6">
                                <p className="font-semibold">
                                    ₹discountedPrice
                                </p>
                                <p className="opacity-50 line-through">
                                    ₹price
                                </p>
                                <p className="text-green-600 font-semibold">
                                    discountPersent% Off
                                </p>
                            </div>

                            {/* Star Rating */}
                            <div className="mt-6">
                                <h3 className="sr-only">Reviews</h3>
                                <div className="flex items-center space-x-3">
                                    <Rating
                                        name="read-only"
                                        value={4.6}
                                        precision={0.5}
                                        readOnly
                                    />
                                    <p className="opacity-60 text-sm">42807 Ratings</p>
                                    <p className="ml-3 text-sm font-medium text-indigo-600 hover:text-indigo-500">
                                        {reviews.totalCount} reviews
                                    </p>
                                </div>
                            </div>


                            <form className="mt-10">

                                {/* Sizes */}
                                <div className="mt-10">
                                    <div className="flex items-center justify-between">
                                        <h3 className="text-sm font-medium text-gray-900">Size</h3>
                                        <a href="#" className="text-sm font-medium text-indigo-600 hover:text-indigo-500">
                                            Size guide
                                        </a>
                                    </div>

                                    <fieldset aria-label="Choose a size" className="mt-4">
                                        <RadioGroup
                                            value={selectedSize}
                                            onChange={setSelectedSize}
                                            className="grid grid-cols-4 gap-4 sm:grid-cols-8 lg:grid-cols-4"
                                        >
                                            {product.sizes.map((size) => (
                                                <Radio
                                                    key={size.name}
                                                    value={size}
                                                    disabled={!size.inStock}
                                                    className={classNames(
                                                        size.inStock
                                                            ? 'cursor-pointer bg-white text-gray-900 shadow-sm'
                                                            : 'cursor-not-allowed bg-gray-50 text-gray-200',
                                                        'group relative flex items-center justify-center rounded-md border px-4 py-3 text-sm font-medium uppercase hover:bg-gray-50 focus:outline-none data-[focus]:ring-2 data-[focus]:ring-indigo-500 sm:flex-1 sm:py-6',
                                                    )}
                                                >
                                                    <span>{size.name}</span>
                                                    {size.inStock ? (
                                                        <span
                                                            aria-hidden="true"
                                                            className="pointer-events-none absolute -inset-px rounded-md border-2 border-transparent group-data-[focus]:border group-data-[checked]:border-indigo-500"
                                                        />
                                                    ) : (
                                                        <span
                                                            aria-hidden="true"
                                                            className="pointer-events-none absolute -inset-px rounded-md border-2 border-gray-200"
                                                        >
                                                            <svg
                                                                stroke="currentColor"
                                                                viewBox="0 0 100 100"
                                                                preserveAspectRatio="none"
                                                                className="absolute inset-0 h-full w-full stroke-2 text-gray-200"
                                                            >
                                                                <line x1={0} x2={100} y1={100} y2={0} vectorEffect="non-scaling-stroke" />
                                                            </svg>
                                                        </span>
                                                    )}
                                                </Radio>
                                            ))}
                                        </RadioGroup>
                                    </fieldset>
                                </div>





                                {/* Add to Cart */}
                                <button
                                    onClick={handleNavigate}
                                    type="submit"
                                    className="mt-10 flex w-full items-center justify-center rounded-md border border-transparent bg-indigo-600 px-8 py-3 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                                >
                                    Add to bag
                                </button>



                            </form>


                        </div>
















                        {/* Description and details */}
                        <div className="py-10 lg:col-span-2 lg:col-start-1 lg:border-r lg:border-gray-200 lg:pb-16 lg:pr-8 lg:pt-16 ">
                            <div>
                                <h3 className="sr-only">Description</h3>

                                <div className="space-y-6">
                                    <p className="text-base text-gray-900">{product.description}</p>
                                </div>
                            </div>

                            <div className="mt-10">
                                <h3 className="text-sm font-medium text-gray-900">Highlights</h3>

                                <div className="mt-4">
                                    <ul role="list" className="list-disc space-y-2 pl-4 text-sm">
                                        {product.highlights.map((highlight) => (
                                            <li key={highlight} className="text-gray-400">
                                                <span className="text-gray-600">{highlight}</span>
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            </div>

                            <div className="mt-10">
                                <h2 className="text-sm font-medium text-gray-900">Details</h2>

                                <div className="mt-4 space-y-6">
                                    <p className="text-sm text-gray-600">{product.details}</p>
                                </div>
                            </div>
                        </div>











                        {/* Add Rating and Review */}
                        <section className="">
                            <h1 className="font-semibold text-lg pb-4">
                                Recent Review & Ratings
                            </h1>

                            <div className="border p-5">
                                <Grid container spacing={7}>
                                    <Grid item xs={7}>
                                        <div className="space-y-5">
                                            <div className="space-y-5">

                                                {[1, 1, 1, 1].map((item) => <ProductReviewCard />)}

                                            </div>
                                        </div>
                                    </Grid>
                                </Grid>
                            </div>
                        </section>



                    </div>



                </section>


            </div>

        </div>
    )
}
