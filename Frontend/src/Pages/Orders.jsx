import { useEffect,useState } from "react";
import { Header } from "../components/Header";
import { Footer } from "../components/Footer";
import { Order } from "../components/OrderComponent/Order";
import { GetLocalStorage } from "../components/LocalStorage";


export const Orders = () => {
    const [orderData, setOrderData] = useState(null);
    
    useEffect(() => {
        const fetchData = async () => {
            try {
                const custId = GetLocalStorage("CustomerId");
                const orderResponse = await fetch(`http://localhost:8080/orders/getByCustomerId/${custId}`, {
                    method: "GET",
                });
                const data = await orderResponse.json();
                setOrderData(data);
            } catch (error) {
                console.error("Error fetching orders:", error);
            }
        };
       
        fetchData();
    }, []);

    return (
        <>
            <Header />
            <ul style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '1rem', justifyContent: 'center', alignItems: 'center' }}> 
                {orderData ? (
                    orderData.map(order => (
                        <Order key={order.orderId} order={order} />
                    ))
                ) : (
                    <p>Loading...</p>
                )}
            </ul>
            <p style={{marginTop:'2rem'}}></p>
            <Footer />
        </>
    );
};