import React, { useEffect, useState } from "react";
import { ProductCard } from "../ShopComponent/ProductCard";
import axios from "axios";

export const ListProduct = () => {
  
  const [activeButton, setActiveButton] = useState(1); 
  const[data,setData]=useState([]);

  const handleClick = async(buttonNumber) => {
    setActiveButton(buttonNumber);
   
      const respone = await axios.get(`http://localhost:8080/product/getByCategoryId/${buttonNumber}`);
      setData(respone.data);

  };
 
  
  return (
    <>
      <section id="products" className="section product">
        <div className="container">
          <p className="section-subtitle"> -- Organic Products --</p>
          <h2 className="h2 section-title">All Organic Products</h2>
          <ul className="filter-list">
            <li>
              <button className={`filter-btn ${activeButton === 1 ? 'active' : ''}`} onClick={() => handleClick(1)}>
                <img
                  src="./images/filter-1.png"
                  width={20}
                  alt=""
                  className="default"
                />
                <img
                  src="./images/filter-1-active.png"
                  width={20}
                  alt=""
                  className="color"
                />
                <p className="filter-text">Fresh Vegetables</p>
              </button>
            </li>
            <li>
              <button className={`filter-btn ${activeButton === 2 ? 'active' : ''}`} onClick={() => handleClick(2)}>
                <img
                  src="./images/filter-2.png"
                  width={20}
                  alt=""
                  className="default"
                />
                <img
                  src="./images/filter-2-active.png"
                  width={20}
                  alt=""
                  className="color"
                />
                <p className="filter-text">Fish &amp; Meat</p>
              </button>
            </li>
            <li>
              <button className={`filter-btn ${activeButton === 3 ? 'active' : ''}`} onClick={() => handleClick(3)}>
                <img
                  src="./images/filter-3.png"
                  width={20}
                  alt=""
                  className="default"
                />
                <img
                  src="./images/filter-3-active.png"
                  width={20}
                  alt=""
                  className="color"
                />
                <p className="filter-text">Healthy Fruit</p>
              </button>
            </li>
            <li>
              <button className={`filter-btn ${activeButton === 4 ? 'active' : ''}`} onClick={() => handleClick(4)}>
                <img
                  src="./images/filter-1.png"
                  width={20}
                  alt=""
                  className="default"
                />
                <img
                  src="./images/filter-1-active.png"
                  width={20}
                  alt=""
                  className="color"
                />
                <p className="filter-text">Dairy Products</p>
              </button>
            </li>
          </ul>
          <ul className="grid-list">
            {data.map((item) => 

               <ProductCard key={item.productId} id={item.productId} name={item.productName} price={item.productPrice} imgState= {activeButton}/>
            
            
        
            )}
          </ul>
        </div>
      </section>
    </>
  );
};
