import React, { useState } from "react";
import { toast } from 'react-toastify';
import { GetLocalStorage } from "../LocalStorage";

export const Items = ({ prop ,setLoading}) => {


  const imageMap = {
    1: './images/product-details-1.jpg',
    2: './images/fish-meat1.jpg',
    3: './images/healthy-fruits1.jpg',
    4: './images/dairy-products.jpg',
  };

  const imgSrc = imageMap[prop.categoryId];

  const onToast = () => {
    toast.success('Item Removed!!', {
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
 


  
  const handleRemove = async () =>{
    const custId = GetLocalStorage("CustomerId");
    const res = await  fetch(
      `http://localhost:8080/cart/deleteProducts/${custId}/${prop.productId}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
         
        },

      }

      );
      onToast();
      const t=await res.text();
      setLoading(t);
      

    
  }

  return (
    <>
      <tr>
        <th scope="row" className="border-0">
          <div className="p-2">
            <img
              src={imgSrc}
              alt=""
              width={70}
              className="img-fluid rounded shadow-sm d-inline "
            />
            <div className="ml-3 d-inline-block align-middle">
              <strong>
                {" "}
                  {prop.productName}
              </strong>
            </div>
          </div>
        </th>
        <td className="border-0 align-middle">
          <strong>{prop.productPrice}</strong>
        </td>
        <td className="border-0 align-middle">
          <a  className="text-dark" onClick={()=>handleRemove()}>
            <i className="fa fa-trash" />
          </a>
        </td>
      </tr>
    </>
  );
};
