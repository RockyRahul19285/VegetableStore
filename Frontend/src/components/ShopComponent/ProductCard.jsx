import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import { GetLocalStorage } from "../LocalStorage";

export const ProductCard = (props) => {
  const navigate = useNavigate();

  const imageMap = {
    1: './images/product-details-1.jpg',
    2: './images/fish-meat1.jpg',
    3: './images/healthy-fruits1.jpg',
    4: './images/dairy-products.jpg',
  };

  const imgSrc = imageMap[props.imgState];


  const onToast = () => {
    toast.success('Added to cart!', {
      position: "bottom-center",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
      });
  }

  const handleCart = async (id) => {
    if(GetLocalStorage("CustomerId")===null){
      navigate("/login");
    } else{
      const custId = GetLocalStorage("CustomerId");
    const res = await fetch(`http://localhost:8080/cart/add/${custId}/${props.id}`, {
      method: "POST",
    });
    if(res.status===200){
      onToast();
      const data = await res.json();
    }else{
      navigate("/login");
    }

    }
  };
  return (
    <>
      <li>
        <div className="product-card">
          <figure className="card-banner">
            <img
              src={imgSrc}
              width={189}
              height={189}
              loading="lazy"
              alt="Organica Images"
            />
          </figure>
          <div className="rating-wrapper">
            <ion-icon name="star" />
            <ion-icon name="star" />
            <ion-icon name="star" />
            <ion-icon name="star" />
            <ion-icon name="star" />
          </div>
          <h3 className="h4 card-title">
            <p>{props.name}</p>
          </h3>
          <div className="price-wrapper">
            <del className="del">Rs {props.price+100}</del>
            <data className="price" value={85.0}>
              Rs {props.price}
            </data>
          </div>
          <button className="btn btn-primary" onClick={handleCart}>
            Add to Cart
          </button>
        </div>
      </li>

    </>
  );
};
