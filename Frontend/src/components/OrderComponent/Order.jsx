import { useState,useEffect } from "react";
import './Order.css'

export const Order = ({ order }) => {
    const [products, setProducts] = useState([]);
    const date = new Date(order.date);
const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}:${date.getSeconds().toString().padStart(2, '0')}`;


    
    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const productPromises = order.cartItems.map(async (itemId) => {
                    const productResponse = await fetch(`http://localhost:8080/product/getById/${itemId}`, {
                        method: "GET",
                    });
                    const productData = await productResponse.json();
                    return productData.productName;
                });
                const productNames = await Promise.all(productPromises);
                setProducts(productNames);
            } catch (error) {
                console.error("Error fetching products:", error);
            }
        };
       
        fetchProducts();
    }, [order]);

    return (
    <>
    <li style={{paddingRight:'1rem',paddingBottom:'1rem'}} >
            <div class="card1">
                <div class="card-details1">
                    <p class="text-title1">Order ID: {order.orderId}</p>
                    <p class="text-body1">Delivery Status: {order.status}</p>
                    <p class="text-body1">Date of Order: {formattedDate}</p>
                    <p class="text-body1">Total Quantity: {order.totalQuantity}</p>
                    <p class="text-body1">Total Price: {order.totalPrice}</p>
                    <p class="text-body1">Ordered Products: {"["}
                    <ul style={{display:'flex',flexDirection:'row'}}>
                     {products.map((productName, index) => (
                      <li key={index} style={{paddingRight:'1rem'}}>{productName}</li>
                     ))}
                     </ul>
                     {"]"}
                    </p>
                </div>
            </div>
   
     </li>

     </>
    );
};